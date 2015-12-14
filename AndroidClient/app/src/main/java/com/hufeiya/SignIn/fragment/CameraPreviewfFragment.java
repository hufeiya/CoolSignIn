package com.hufeiya.SignIn.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.faceplusplus.api.FaceDetecter;
import com.hufeiya.SignIn.R;
import com.hufeiya.SignIn.activity.QuizActivity;
import com.hufeiya.SignIn.helper.FaceMask;

import java.io.IOException;

/**
 *  create by hufeiya in 2015-12-13 22:25:21
 */


public class CameraPreviewfFragment extends Fragment implements SurfaceHolder.Callback, Camera.PreviewCallback, Camera.PictureCallback{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private SurfaceView camerasurface = null;
    private FaceMask mask = null;
    private Camera camera = null;
    private HandlerThread handleThread = null;
    private Handler detectHandler = null;
    private Runnable detectRunnalbe = null;
    private int width = 320;
    private int height = 240;
    private FaceDetecter facedetecter = null;
    private OnRecognizedFaceListener onRecognizedFaceListener;

    @Override
    public void onPictureTaken(byte[] data, Camera camera) {
        onRecognizedFaceListener.onPhotoTaken(data);
    }

    public interface OnRecognizedFaceListener{
        public void onRecognizedFace();
        public void onNotRedcognizedFace();
        public void onPhotoTaken(byte[] data);
    }

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CameraPreviewfFragment() {
        // Required empty public constructor
    }


    public static CameraPreviewfFragment newInstance() {
        CameraPreviewfFragment fragment = new CameraPreviewfFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_camera_preview, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        camerasurface = (SurfaceView) view.findViewById(R.id.camera_preview);
        mask = (FaceMask) view.findViewById(R.id.mask);
        RelativeLayout.LayoutParams para = new RelativeLayout.LayoutParams(480, 800);
        handleThread = new HandlerThread("dt");
        handleThread.start();
        detectHandler = new Handler(handleThread.getLooper());
        para.addRule(RelativeLayout.CENTER_IN_PARENT);
        camerasurface.setLayoutParams(para);
        mask.setLayoutParams(para);
        camerasurface.getHolder().addCallback(this);
        camerasurface.setKeepScreenOn(true);

        facedetecter = new FaceDetecter();
        if (!facedetecter.init(getActivity(), "2af33a8a684f670a9c5fcc83afb7d0ca")) {
            Log.e("diff", "有错误 ");
        }
        facedetecter.setTrackingMode(true);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        try {
            camera.setPreviewDisplay(holder);
        } catch (IOException e) {
            e.printStackTrace();
        }
        camera.setDisplayOrientation(90);
        camera.startPreview();
        camera.setPreviewCallback(this);
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    @Override
    public void onPreviewFrame(final byte[] data, Camera camera) {
        camera.setPreviewCallback(null);
        detectHandler.post(new Runnable() {

            @Override
            public void run() {
                byte[] ori = new byte[width * height];
                int is = 0;
                for (int x = width - 1; x >= 0; x--) {

                    for (int y = height - 1; y >= 0; y--) {

                        ori[is] = data[y * width + x];

                        is++;
                    }

                }
                final FaceDetecter.Face[] faceinfo = facedetecter.findFaces( ori, height,
                        width);
                //send face found or not found msg to activity.
                if (faceinfo != null && faceinfo.length != 0){
                    onRecognizedFaceListener.onRecognizedFace();
                }else{
                    onRecognizedFaceListener.onNotRedcognizedFace();
                }
                getActivity().runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        mask.setFaceInfo(faceinfo);
                    }
                });
                CameraPreviewfFragment.this.camera.setPreviewCallback(CameraPreviewfFragment.this);
            }
        });
    }
    public void shooting(){
        if(camera != null){
            camera.takePicture(null,null,this);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        onRecognizedFaceListener = (QuizActivity)activity;
    }

    @Override
    public void onResume() {
        super.onResume();
        camera = Camera.open(1);
        Camera.Parameters para = camera.getParameters();
        para.setPreviewSize(width, height);
        camera.setParameters(para);
    }

    @Override
    public void onPause() {
        super.onPause();
        if (camera != null) {
            camera.setPreviewCallback(null);
            camera.stopPreview();
            camera.release();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        facedetecter.release(getActivity());
        handleThread.quit();
    }
}
