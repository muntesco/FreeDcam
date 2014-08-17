package com.troop.freecam.camera;

import android.hardware.Camera;
import android.util.Log;

import com.troop.freecam.camera.parameters.manual.BrightnessManualParameter;
import com.troop.freecam.camera.parameters.manual.ContrastManualParameter;
import com.troop.freecam.camera.parameters.manual.ConvergenceManualParameter;
import com.troop.freecam.camera.parameters.manual.ExposureManualParameter;
import com.troop.freecam.camera.parameters.manual.FocusManualParameter;
import com.troop.freecam.camera.parameters.manual.SaturationManualParameter;
import com.troop.freecam.camera.parameters.manual.SharpnessManualParameter;
import com.troop.freecam.camera.parameters.manual.ShutterManualParameter;
import com.troop.freecam.camera.parameters.modes.ColorModeParameter;
import com.troop.freecam.camera.parameters.modes.ExposureModeParameter;
import com.troop.freecam.camera.parameters.modes.FlashModeParameter;
import com.troop.freecam.camera.parameters.modes.IsoModeParameter;

/**
 * Created by troop on 17.08.2014.
 */
public class CamParametersHandler
{
    BaseCameraHolder cameraHolder;
    Camera.Parameters cameraParameters;


    public BrightnessManualParameter ManualBrightness;
    public SharpnessManualParameter ManualSharpness;
    public ContrastManualParameter ManualContrast;
    public SaturationManualParameter ManualSaturation;
    public ExposureManualParameter ManualExposure;
    public ConvergenceManualParameter ManualConvergence;
    public FocusManualParameter ManualFocus;
    public ShutterManualParameter ManualShutter;

    public ColorModeParameter ColorMode;
    public ExposureModeParameter ExposureMode;
    public FlashModeParameter FlashMode;
    public IsoModeParameter IsoMode;

    public CamParametersHandler(BaseCameraHolder cameraHolder)
    {
        this.cameraHolder = cameraHolder;
    }

    public void GetParametersFromCamera()
    {
        cameraParameters = cameraHolder.GetCameraParameters();
    }

    public void SetParametersToCamera()
    {
        cameraHolder.SetCameraParameters(cameraParameters);
        initParameters();
    }

    boolean supportManualWhiteBalance = false;
    public boolean getSupportWhiteBalance() { return supportManualWhiteBalance; }

    boolean supportFlash = false;
    public boolean getSupportFlash() { return  supportFlash;}

    boolean supportVNF = false;
    public boolean getSupportVNF() { return supportVNF;}

    boolean supportAfpPriorityModes = false;
    public boolean getSupportAfpPriority() { return supportAfpPriorityModes;}

    boolean supportIPP = false;
    public boolean getSupportIPP() { return supportIPP;}

    boolean supportZSL = false;
    public boolean getSupportZSL() { return supportZSL;}





    private void logParameters()
    {
        String[] paras =  cameraParameters.flatten().split(";");
        for(int i = 0; i < paras.length; i++)
            Log.d("freecam.CameraParametersHandler", paras[i]);
    }

    private void initParameters()
    {
        logParameters();
        ManualBrightness = new BrightnessManualParameter(cameraParameters, "","","");
        ManualContrast = new ContrastManualParameter(cameraParameters, "", "", "");
        ManualConvergence =new ConvergenceManualParameter(cameraParameters, "manual-convergence", "supported-manual-convergence-max", "supported-manual-convergence-min");
        ManualExposure = new ExposureManualParameter(cameraParameters,"","","");
        ManualFocus = new FocusManualParameter(cameraParameters,"","","");
        ManualSaturation = new SaturationManualParameter(cameraParameters,"","","");
        ManualSharpness = new SharpnessManualParameter(cameraParameters, "", "", "");
        ManualShutter = new ShutterManualParameter(cameraParameters,"","","");

        ColorMode = new ColorModeParameter(cameraParameters, "", "");
        ExposureMode = new ExposureModeParameter(cameraParameters,"","");
        FlashMode = new FlashModeParameter(cameraParameters,"","");
        IsoMode = new IsoModeParameter(cameraParameters,"","");


    }
}
