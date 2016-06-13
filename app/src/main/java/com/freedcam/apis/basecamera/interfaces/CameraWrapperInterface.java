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

package com.freedcam.apis.basecamera.interfaces;

import android.content.Context;
import android.view.SurfaceView;

import com.freedcam.apis.basecamera.AbstractFocusHandler;
import com.freedcam.apis.basecamera.modules.AbstractModuleHandler;
import com.freedcam.apis.basecamera.parameters.AbstractParameterHandler;
import com.freedcam.utils.AppSettingsManager;
import com.freedcam.utils.RenderScriptHandler;

/**
 * Created by troop on 09.12.2014.
 */
public interface CameraWrapperInterface extends CameraWrapperEventInterface
{
    /**
     * Start the Camera
     */
    void StartCamera();

    /**
     * Stop the Camera
     */
    void StopCamera();
    void StartPreview();
    void StopPreview();
    /**
     * Starts a new work with the current active module
     * the module must handle the workstate on its own if it gets hit twice while work is already in progress
     */
    void DoWork();

    /**
     * Get the current active CameraHolderSony
     * @return
     */
    CameraHolderInterface GetCameraHolder();

    /**
     * get the active parameterhandler
     * @return
     */
    AbstractParameterHandler GetParameterHandler();
    /**
     * get the appsettings
     */
    AppSettingsManager GetAppSettingsManager();
    AbstractModuleHandler GetModuleHandler();
    SurfaceView getSurfaceView();
    AbstractFocusHandler getFocusHandler();

    /**
     * set the listner that get notifyed when camera state has changed
     * @param cameraChangedListner to notify
     */
    void SetCameraChangedListner(CameraWrapperEventInterface cameraChangedListner);


    /**
     * get the left margine between display and preview
     * @return
     */
    int getMargineLeft();
    /**
     * get the right margine between display and preview
     * @return
     */
    int getMargineRight();
    /**
     * get the top margine between display and preview
     * @return
     */
    int getMargineTop();
    /**
     * get the preview width
     * @return
     */
    int getPreviewWidth();
    /**
     * get the preview height
     * @return
     */
    int getPreviewHeight();

    boolean isAeMeteringSupported();

    Context getContext();

    FocuspeakProcessor getFocusPeakProcessor();

    RenderScriptHandler getRenderScriptHandler();

}