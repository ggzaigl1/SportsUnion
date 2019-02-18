package com.fy.video.scan;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;

import com.fy.baselibrary.application.BaseApplication;
import com.fy.baselibrary.utils.L;
import com.fy.video.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 使用 LoaderCallbacks 扫描本地 视频
 * Created by fangs on 2018/1/27.
 */
public class VideoDataSource implements LoaderManager.LoaderCallbacks<Cursor>{

    public static final int LOADER_ALL = 0;         //加载所有视频
    public static final int LOADER_CATEGORY = 1;    //分类加载视频

    private final String[] RESOURCE_PROJECTION = {     //查询视频需要的数据列
            MediaStore.Video.Media.DISPLAY_NAME,   //视频的显示名称  aaa.jpg
            MediaStore.Video.Media.DATA,           //视频的真实路径  /storage/emulated/0/pp/downloader/wallpaper/aaa.mp4
            MediaStore.Video.Media.SIZE,           //视频的大小，long型  132492
            MediaStore.Video.Media.WIDTH,          //视频的宽度，int型  1920
            MediaStore.Video.Media.HEIGHT,         //视频的高度，int型  1080
            MediaStore.Video.Media.MIME_TYPE,      //视频的类型     image/jpeg
            MediaStore.Video.Media.DATE_ADDED,     //视频被添加的时间，long型  1450518608
            MediaStore.Video.Media.DURATION        //视频时长
    };


    private OnVideoLoadedListener loadedListener;                       //图片加载完成的回调接口
    private List<ResourceFolder> resourceFolders = new ArrayList<>();      //所有的图片文件夹

    /**
     * @param activity       用于初始化LoaderManager，需要兼容到2.3
     * @param path           指定扫描的文件夹目录，可以为 null，表示扫描所有图片
     * @param loadedListener 图片加载完成的监听
     */
    public VideoDataSource(AppCompatActivity activity, String path, OnVideoLoadedListener loadedListener) {
        this.loadedListener = loadedListener;

        LoaderManager loaderManager = activity.getSupportLoaderManager();
        if (null == path) {
            loaderManager.initLoader(LOADER_ALL, null, this);//加载所有的图片
        } else {
            //加载指定目录的图片
            Bundle bundle = new Bundle();
            bundle.putString("path", path);
            loaderManager.initLoader(LOADER_CATEGORY, bundle, this);
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        CursorLoader cursorLoader = null;
        //扫描所有视频
        if (id == LOADER_ALL)
            cursorLoader = new CursorLoader(BaseApplication.getApplication(), MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                    RESOURCE_PROJECTION, null, null, RESOURCE_PROJECTION[7] + " DESC");

        //扫描某个文件夹
        if (id == LOADER_CATEGORY)
            cursorLoader = new CursorLoader(BaseApplication.getApplication(), MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                    RESOURCE_PROJECTION, RESOURCE_PROJECTION[1] + " like '%" + args.getString("path") + "%'", null,
                    RESOURCE_PROJECTION[7] + " DESC");

        return cursorLoader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        resourceFolders.clear();
        if (data != null) {
            ArrayList<ResourceItem> allImages = new ArrayList<>();   //所有图片的集合,不分文件夹
            while (data.moveToNext()) {
                //查询数据
                String resourceName = data.getString(data.getColumnIndexOrThrow(RESOURCE_PROJECTION[0]));
                String resourcePath = data.getString(data.getColumnIndexOrThrow(RESOURCE_PROJECTION[1]));
                long resourceSize = data.getLong(data.getColumnIndexOrThrow(RESOURCE_PROJECTION[2]));
                int resourceWidth = data.getInt(data.getColumnIndexOrThrow(RESOURCE_PROJECTION[3]));
                int resourceHeight = data.getInt(data.getColumnIndexOrThrow(RESOURCE_PROJECTION[4]));
                String resourceMimeType = data.getString(data.getColumnIndexOrThrow(RESOURCE_PROJECTION[5]));
                long resourceAddTime = data.getLong(data.getColumnIndexOrThrow(RESOURCE_PROJECTION[6]));
                long resourceDURATION = data.getLong(data.getColumnIndexOrThrow(RESOURCE_PROJECTION[7]));
                //封装实体
                ResourceItem resourceItem = new ResourceItem();
                resourceItem.name = resourceName;
                resourceItem.path = resourcePath;
                resourceItem.size = resourceSize;
                resourceItem.width = resourceWidth;
                resourceItem.height = resourceHeight;
                resourceItem.mimeType = resourceMimeType;
                resourceItem.addTime = resourceAddTime;

                if (resourceDURATION > 15000)continue;//过滤掉视频时长大于15秒的数据
                allImages.add(resourceItem);

                //根据父路径分类存放图片
                File resourceFile = new File(resourcePath);
                File resourceParentFile = resourceFile.getParentFile();
                ResourceFolder resourceFolder = new ResourceFolder();
                resourceFolder.name = resourceParentFile.getName();
                resourceFolder.path = resourceParentFile.getAbsolutePath();

                if (!resourceFolders.contains(resourceFolder)) {
                    ArrayList<ResourceItem> resources = new ArrayList<>();
                    resources.add(resourceItem);
                    resourceFolder.cover = resourceItem;
                    resourceFolder.resources = resources;
                    resourceFolders.add(resourceFolder);
                } else {
                    resourceFolders.get(resourceFolders.indexOf(resourceFolder)).resources.add(resourceItem);
                }
            }

            //防止没有图片报异常
            if (data.getCount() > 0 && allImages.size() > 0) {
                //构造所有图片的集合
                ResourceFolder allImagesFolder = new ResourceFolder();
                allImagesFolder.name = BaseApplication.getApplication().getResources().getString(R.string.all_video);
                allImagesFolder.path = "/";
                allImagesFolder.cover = allImages.get(0);
                allImagesFolder.resources = allImages;
                resourceFolders.add(0, allImagesFolder);  //确保第一条是所有图片
            }
        }

        loadedListener.onVideoLoaded(resourceFolders);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        L.i("VideoLibrary", "----扫描视频完成");
    }

    /** 所有视频资源加载完成 的回调接口 */
    public interface OnVideoLoadedListener {
        void onVideoLoaded(List<ResourceFolder> imageFolders);
    }
}
