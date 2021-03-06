/*
 *
 *     Copyright (C) 2015 Ingo Fuchs
 *     This program is free software; you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation; either version 2 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License along
 *     with this program; if not, write to the Free Software Foundation, Inc.,
 *     51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 * /
 */

package freed.cam.ui.themesample.settings.childs;

import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

import freed.ActivityInterface;
import freed.cam.apis.basecamera.CameraWrapperInterface;
import freed.cam.apis.basecamera.parameters.modes.ModeParameterInterface;
import freed.cam.apis.camera1.Camera1Fragment;
import freed.cam.apis.camera1.CameraHolder;
import freed.utils.Logger;
import freed.utils.StringUtils;

/**
 * Created by Ingo on 06.09.2015.
 */
public class SettingsChildMenuSaveCamParams extends SettingsChildMenu
{
    private CameraWrapperInterface cameraUiWrapper;
    public SettingsChildMenuSaveCamParams(Context context) {
        super(context);
    }

    public SettingsChildMenuSaveCamParams(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void init(Context context) {
        super.init(context);
    }

    @Override
    protected void inflateTheme(LayoutInflater inflater) {
        super.inflateTheme(inflater);
    }

    @Override
    public void onParameterValueChanged(String val) {
    }

    @Override
    public void onClick(View v)
    {
        try {


            saveCamParameters();
            Toast.makeText(context, "Saved CameraParameters", Toast.LENGTH_LONG).show();
        }
        catch (Exception ex) {
            Logger.d("Freedcam", ex.getMessage());
        }
    }

    @Override
    public void SetStuff(ActivityInterface fragment_activityInterface, String settingvalue ) {

    }

    @Override
    public void SetUiItemClickListner(SettingsChildClick menuItemClick) {

    }

    @Override
    public void SetParameter(ModeParameterInterface parameter) {

    }

    @Override
    public void setTextToTextBox(ModeParameterInterface parameter) {

    }

    @Override
    public String[] GetValues() {
        return null;
    }

    @Override
    public void SetValue(String value) {

    }

    @Override
    public void onParameterIsSupportedChanged(boolean isSupported) {

    }

    @Override
    public void onParameterIsSetSupportedChanged(boolean isSupported) {

    }

    @Override
    public void onModuleChanged(String module) {
    }

    @Override
    public void ParametersLoaded()
    {
    }


    public void setCameraUiWrapper(CameraWrapperInterface cameraUiWrapper)
    {
        this.cameraUiWrapper = cameraUiWrapper;
        if (cameraUiWrapper instanceof Camera1Fragment)
            setVisibility(View.VISIBLE);
        else
            setVisibility(View.GONE);
    }

    private void saveCamParameters()
    {
        String[] paras = null;
        CameraHolder holder = (CameraHolder) cameraUiWrapper.GetCameraHolder();

        paras = holder.GetCamera().getParameters().flatten().split(";");

        Arrays.sort(paras);

        FileOutputStream outputStream;
        File freedcamdir = new File(Environment.getExternalStorageDirectory() + StringUtils.freedcamFolder);
        if (!freedcamdir.exists())
            freedcamdir.mkdirs();
        File file = new File(Environment.getExternalStorageDirectory() + StringUtils.freedcamFolder+ Build.MODEL + "_CameraParameters.txt");
        try {
            //file.mkdirs();
            file.createNewFile();
        } catch (IOException e) {
            Logger.exception(e);
        }

        try {
            outputStream = new FileOutputStream(file);
            outputStream.write((Build.MODEL + "\r\n").getBytes());
            outputStream.write((System.getProperty("os.version") + "\r\n").getBytes());
            for (String s : paras)
            {
                outputStream.write((s+"\r\n").getBytes());
            }

            outputStream.close();
        } catch (Exception e) {
            Logger.exception(e);
        }
    }
}
