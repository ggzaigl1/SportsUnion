package com.netcompss.zip;

import android.content.Context;

import com.fy.baselibrary.utils.FileUtils;
import com.fy.baselibrary.utils.L;
import com.fy.baselibrary.utils.TransfmtUtils;
import com.netcompss.ffmpeg4android.CommandValidationException;
import com.netcompss.ffmpeg4android.GeneralUtils;
import com.netcompss.ffmpeg4android.Prefs;
import com.netcompss.ffmpeg4android.ProgressCalculator;
import com.netcompss.loader.LoadJNI;

import java.io.File;

/**
 * Created by caizhiming on 2016/11/8.
 * 视频压缩器
 */
public class VideoCompressor {
    private Context mContext;
    private static String mStrCmd = " -strict -2 -vcodec libx264 -preset ultrafast -acodec aac -ar 44100 -ac 1 -b:a 72k -s 480x480 -aspect 1:1 -r 24 ";
    private static String mStrCmdPre = "ffmpeg -y -i ";


    private static String workFolder = null;
    private static String demoVideoFolder = null;//压缩视频 日志目录
    private static String vkLogPath = null;//压缩视频 日志文件
    private static LoadJNI vk;
    private static boolean commandValidationFailedFlag = false;

    private VideoCompressor(Context context) {
        mContext = context;
        init(context);
    }

    private static void init(Context context) {
        demoVideoFolder = FileUtils.getPath("video.zip");

        workFolder = context.getApplicationContext().getFilesDir() + "/";
        vkLogPath = workFolder + "vk.log";

        GeneralUtils.copyLicenseFromAssetsToSDIfNeeded(context, workFolder);
        GeneralUtils.copyDemoVideoFromAssetsToSDIfNeeded(context, demoVideoFolder);
        int rc = GeneralUtils.isLicenseValid(context.getApplicationContext(), workFolder);
        L.e("License check RC: " + rc);
    }

    /**
     * 视频压缩工具方法
     *
     * @param context
     * @param inputFile
     * @param listener
     */
    public static void compress(final Context context, final String inputFile, final VideoCompressListener listener) {
        init(context);
        new Thread() {
            public void run() {
                try {
                    compressByFFmpeg(context, inputFile, listener);
                } catch (Exception e) {
                    L.e("compress exception:" + e.getMessage());
                }
            }
        }.start();

        getVideoDuration(inputFile);
        // Progress update thread
        new Thread() {
            ProgressCalculator pc = new ProgressCalculator(vkLogPath);

            public void run() {
                L.e("Progress update started");
                int progress = -1;
                try {
                    while (true) {
                        sleep(50);
                        progress = pc.calcProgress();
                        if (progress != 0 && progress < 100) {
                            //caizhiming
                            //todo  update progress;
                            listener.onProgress(progress);
                        } else if (progress >= 100) {
                            // compress finish;
//                            listener.onProgress(100);
                        }
                    }
                } catch (Exception e) {
                    L.e("threadmessage:" + e.getMessage());
                }
            }
        }.start();
    }

    private static void compressByFFmpeg(Context context, String inputFile, final VideoCompressListener listener) {
        L.e("runTranscodingUsingLoader started...");
        vk = new LoadJNI();
        String newFilename = null;
        String mOutputFile = null;
        try {
            //生成 压缩后的文件名称
            newFilename = TransfmtUtils.getMD5(new File(inputFile).getName()) + ".mp4";
            //生成 压缩后的文件路径
            mOutputFile = FileUtils.getPath("video.zip") + "/" + newFilename;
            String cmdStr = mStrCmdPre + inputFile + mStrCmd + mOutputFile;
            vk.run(GeneralUtils.utilConvertToComplex(cmdStr), workFolder, context.getApplicationContext());

            L.i(Prefs.TAG, "vk.run finished.");
            // copying vk.log (internal native log) to the videokit folder
            GeneralUtils.copyFileToFolder(vkLogPath, demoVideoFolder);
        } catch (CommandValidationException e) {
            L.e("vk run exeption." + e);
            commandValidationFailedFlag = true;
        } catch (Throwable e) {
            L.e("vk run exeption." + e);
        }
        // finished Toast
        String rc = null;
        if (commandValidationFailedFlag) {
            rc = "Command Vaidation Failed";
        } else {
            rc = GeneralUtils.getReturnCodeFromLog(vkLogPath);
        }
        final String status = rc;
        L.e("compress rc=" + rc);
        if (status.equals("Transcoding Status: Failed")) {
            String strFailInfo = "Check: " + vkLogPath + " for more information.";
            listener.onFail(strFailInfo);
        } else {
            listener.onSuccess(mOutputFile, newFilename, getVideoDuration(inputFile));
        }
    }

    /**
     * 视频时长
     */
    private static int mVideoDuration;

    private static int getVideoDuration(String path) {
        if (mVideoDuration <= 0) {
            mVideoDuration = VideoUtil.getVideoDuration(path);
        }
        return mVideoDuration;
    }
}
