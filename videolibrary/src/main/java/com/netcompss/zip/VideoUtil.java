package com.netcompss.zip;

import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.media.ThumbnailUtils;
import android.provider.MediaStore;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * Created by caizhiming on 2016/11/10.
 */

public class VideoUtil {

    /**
     * 获取视频缩略图
     *
     * @param videoPath
     * @return
     */
    public static String getVideoCover(String videoPath) {
        Bitmap bmpCover = ThumbnailUtils.createVideoThumbnail(videoPath, MediaStore.Images.Thumbnails.MINI_KIND);
        String strCover = AppUtil.getAppDir() + "/video_cover.jpg";
        if (bmp2File(bmpCover, strCover)) {
            File file = new File(strCover);
            if (file.exists()) {
                return strCover;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * convert Bitmap to File
     *
     * @param bmp
     * @param file
     * @return
     */
    public static boolean bmp2File(Bitmap bmp, String file) {
        Bitmap.CompressFormat format = Bitmap.CompressFormat.JPEG;
        int quality = 100;
        OutputStream stream = null;
        try {
            stream = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return bmp.compress(format, quality, stream);
    }

    /**
     * 获取视频时长
     *
     * @param mUri
     */
    public static int getVideoDuration(String mUri) {
        MediaMetadataRetriever mmr = new MediaMetadataRetriever();
        try {
            mmr.setDataSource(mUri);

            String duration = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);//时长(毫秒)
            return (Integer.parseInt(duration) / 1000);

        } catch (Exception ex) {
            Log.e("TAG", "MediaMetadataRetriever exception " + ex);
        } finally {
            mmr.release();
        }
        return 0;

    }
}
