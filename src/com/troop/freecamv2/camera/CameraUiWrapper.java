package com.troop.freecamv2.camera;

import android.hardware.Camera;
import android.view.SurfaceHolder;

import com.troop.freecamv2.camera.modules.ModuleHandler;

import com.troop.freecamv2.camera.parameters.CamParametersHandler;
import com.troop.freecamv2.camera.parameters.I_ParametersLoaded;
import com.troop.freecamv2.ui.AppSettingsManager;
import com.troop.freecamv2.ui.TextureView.ExtendedSurfaceView;

/**
 * Created by troop on 16.08.2014.
 */
public class CameraUiWrapper implements SurfaceHolder.Callback, I_ParametersLoaded
{
    private ExtendedSurfaceView preview;
    public ModuleHandler moduleHandler;
    public BaseCameraHolder cameraHolder;
    AppSettingsManager appSettingsManager;
    public CamParametersHandler camParametersHandler;
    public FocusHandler Focus;


    public CameraUiWrapper(ExtendedSurfaceView preview, AppSettingsManager appSettingsManager)
    {
        this.preview = preview;
        this.appSettingsManager = appSettingsManager;
        //attache the callback to the Campreview
        preview.getHolder().addCallback(this);
        cameraHolder = new BaseCameraHolder();
        camParametersHandler = new CamParametersHandler(cameraHolder);
        cameraHolder.ParameterHandler = camParametersHandler;
        camParametersHandler.ParametersEventHandler.AddParametersLoadedListner(this);
        preview.ParametersHandler = camParametersHandler;

        moduleHandler = new ModuleHandler(cameraHolder, appSettingsManager);
        Focus = new FocusHandler(this);
        cameraHolder.Focus = Focus;
    }



//Module Handler START
    public void SwitchModule(String moduleName)
    {
        moduleHandler.SetModule(moduleName);
    }

    public void DoWork()
    {
        moduleHandler.DoWork();
    }

//Module Handler END


    private boolean openCamera()
    {
        if (Camera.getNumberOfCameras() > 0) {
            cameraHolder.OpenCamera(appSettingsManager.GetCurrentCamera());
            return true;
        }

        return false;
    }

    public void StartPreviewAndCamera() {
        if (openCamera())
        {
            cameraHolder.SetSurface(preview.getHolder());
            camParametersHandler.LoadParametersFromCamera();

        }
    }


    public void StopPreviewAndCamera() {
        cameraHolder.StopPreview();
        cameraHolder.CloseCamera();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        StartPreviewAndCamera();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        StopPreviewAndCamera();
    }

    @Override
    public void ParametersLoaded() {
        cameraHolder.StartPreview();
    }
}
