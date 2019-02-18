package com.fy.video.recorder;

import android.content.Context;
import android.content.Intent;
import android.hardware.Camera;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import com.fy.baselibrary.utils.FileUtils;
import com.fy.baselibrary.utils.L;
import com.fy.baselibrary.utils.PhotoUtils;
import com.fy.baselibrary.utils.T;
import com.fy.baselibrary.utils.TimeUtils;
import com.fy.baselibrary.utils.media.UpdateMedia;
import com.fy.video.R;
import com.netcompss.zip.VideoCompressListener;
import com.netcompss.zip.VideoCompressor;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

/**
 * PS  小视频录制控件
 */
public class VideoInputDialog extends DialogFragment {

    private static final String TAG = "VideoInputDialog";
    private Camera mCamera;
    private CameraPreview mPreview;
    private ProgressBar mProgressRight,mProgressLeft;
    private MediaRecorder mMediaRecorder;
    private Timer mTimer;
    private final int MAX_TIME = 1000;
    private int mTimeCount;
    private long time;
    private boolean isRecording = false;
    private String fileName;

    public static int Q480 = CamcorderProfile.QUALITY_480P;
    public static int Q720 = CamcorderProfile.QUALITY_720P;
    public static int Q1080 = CamcorderProfile.QUALITY_1080P;
    public static int Q21600 = CamcorderProfile.QUALITY_2160P;
    private int quality =CamcorderProfile.QUALITY_480P;

    Context mContext;

    private Handler mainHandler = new Handler(Looper.getMainLooper());
    private Runnable updateProgress = new Runnable() {
        @Override
        public void run() {
            mProgressRight.setProgress(mTimeCount);
            mProgressLeft.setProgress(mTimeCount);
        }
    };

    private Runnable sendVideo = new Runnable() {
        @Override
        public void run() {
            recordStop();
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dialog_video_input, container, false);
        //打开相机
        mCamera = getCameraInstance();
        mPreview = new CameraPreview(getActivity(), mCamera);
        FrameLayout preview = (FrameLayout) v.findViewById(R.id.camera_preview);
        mProgressRight = (ProgressBar) v.findViewById(R.id.progress_right);
        mProgressLeft = (ProgressBar) v.findViewById(R.id.progress_left);
        mProgressRight.setMax(MAX_TIME);
        mProgressLeft.setMax(MAX_TIME);
        mProgressLeft.setRotation(180);
        ImageButton record = (ImageButton) v.findViewById(R.id.btn_record);
        record.setOnTouchListener((v1, event) -> {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    //按下 开始录像
                    if (!isRecording) {
                        if (prepareVideoRecorder()) {
                            time = Calendar.getInstance().getTimeInMillis(); //倒计时
                            mMediaRecorder.start();
                            isRecording = true;
                            mTimer = new Timer();
                            mTimer.schedule(new TimerTask() {
                                @Override
                                public void run() {
                                    mTimeCount++;
                                    mainHandler.post(updateProgress);
                                    if (mTimeCount == MAX_TIME) {
                                        mainHandler.post(sendVideo);
                                    }
                                }
                                }, 0, 10);
                        } else {
                            releaseMediaRecorder();
                        }
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    //抬起 停止录像
                    recordStop();
                    break;
            }
            return true;
        });
        preview.addView(mPreview);
        return v;
    }

    @Override
    public void onPause() {
        super.onPause();
        recordStop();
        releaseMediaRecorder();
        releaseCamera();
    }

    /**
     * 停止录制
     */
    private void recordStop() {
        if (mMediaRecorder != null) {
            try {
                if (isRecording) {
                    isRecording = false;
                    if (isLongEnough()) {
                        mMediaRecorder.stop();
                    }
                    releaseMediaRecorder();
                    mCamera.lock();
                    if (mTimer != null) mTimer.cancel();
                    mTimeCount = 0;
                    mainHandler.post(updateProgress);
                }
            } catch (IllegalStateException e) {
                // TODO 如果当前java状态和jni里面的状态不一致，
                //e.printStackTrace();
                mMediaRecorder = null;
                mMediaRecorder = new MediaRecorder();
                mMediaRecorder.stop();

                mMediaRecorder.release();
                mMediaRecorder = null;
            }
        }
    }

    /** A safe way to get an instance of the Camera object. */
    private static Camera getCameraInstance(){
        Camera c = null;
        try {
            c = Camera.open();
        }
        catch (Exception e){
            // Camera is not available (in use or does not exist)
        }
        return c; // returns null if camera is unavailable
    }

    private void releaseMediaRecorder(){
        if (mMediaRecorder != null) {
            mMediaRecorder.reset();   // clear recorder configuration
            mMediaRecorder.release(); // release the recorder object
            mMediaRecorder = null;
            mCamera.lock();           // lock camera for later use
            if (isLongEnough()){
                new UpdateMedia(fileName).runUpdate();
            }else{
                T.showLong(R.string.chat_video_too_short);
            }

            //TODO 弹出 保存和取消按钮 保存则 通知系统媒体库更新，取消则删除录制的文件
            dismiss();
        }
    }

    private void releaseCamera(){
        if (mCamera != null){
            mCamera.release();        // release the camera for other applications
            mCamera = null;
        }
    }
    //初始化 mMediaRecorder 用于录像
    private boolean prepareVideoRecorder(){
        if (mCamera==null) return false;
        mMediaRecorder = new MediaRecorder();
        mCamera.unlock();
        mMediaRecorder.setCamera(mCamera);
        //声音
        mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.CAMCORDER);
        //视频
        mMediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
        //设置分辨率为480P
        mMediaRecorder.setProfile(CamcorderProfile.get(quality));
        //路径
        mMediaRecorder.setOutputFile(getOutputMediaFile());
        mMediaRecorder.setPreviewDisplay(mPreview.getHolder().getSurface());

        try {
            //旋转90度 保持竖屏
            mMediaRecorder.setOrientationHint(90);
            mMediaRecorder.prepare();
        } catch (IllegalStateException e) {
            Log.d(TAG, "IllegalStateException preparing MediaRecorder: " + e.getMessage());
            releaseMediaRecorder();
            return false;
        } catch (IOException e) {
            Log.d(TAG, "IOException preparing MediaRecorder: " + e.getMessage());
            releaseMediaRecorder();
            return false;
        }
        return true;
    }

    /**
     * 生成录制视频文件 路径
     * @return
     */
    private String getOutputMediaFile() {
        File dir = new File(FileUtils.getPath("video"));
        fileName = dir +  "/video_" + TimeUtils.Long2DataString(System.currentTimeMillis(), "yyyyMMdd_HHmmss") + ".mp4";
        L.i("filePath",fileName);

        return fileName;
    }

    /**
     * 判断录制时间
     * @return
     */
    private boolean isLongEnough(){
        return Calendar.getInstance().getTimeInMillis() - time > 3000;
    }


    public void setQuality(int quality) {
        this.quality = quality;
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }

    /**
     * 工具方法 显示录制视频dialog
     * @param context
     * @param ft
     * @param quality 分辨率
     */
    public static void show(Context context, FragmentManager ft, int quality){
        DialogFragment newFragment = VideoInputDialog.newInstance(quality, context);
        newFragment.show(ft, "VideoInputDialog");
    }

    /**
     * 工具方法 生成 录制视频dialog
     * @param quality
     * @param context
     * @return
     */
    public static VideoInputDialog newInstance(int quality,Context context) {
        VideoInputDialog dialog = new VideoInputDialog();
        dialog.setQuality(quality);
        dialog.setmContext(context);
        dialog.setStyle(DialogFragment.STYLE_NORMAL, R.style.maskDialog);
        return dialog;
    }
}
