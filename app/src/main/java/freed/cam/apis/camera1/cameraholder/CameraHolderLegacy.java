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

package freed.cam.apis.camera1.cameraholder;

import android.hardware.Camera;
import android.hardware.Camera.Parameters;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import freed.cam.apis.basecamera.CameraWrapperInterface;
import freed.cam.apis.camera1.CameraHolder;
import freed.utils.Logger;

/**
 * Created by troop on 08.06.2016.
 */
public class CameraHolderLegacy extends CameraHolder
{
    private final String TAG = CameraHolderLegacy.class.getSimpleName();
    public CameraHolderLegacy(CameraWrapperInterface cameraUiWrapper, Frameworks frameworks) {
        super(cameraUiWrapper,frameworks);
    }

    @Override
    public boolean OpenCamera(int camera)
    {
        try {
            mCamera = openWrapper(camera);
            isRdy = true;
        }
        catch (RuntimeException ex)
        {
            cameraUiWrapper.onCameraError("Fail to connect to camera service");
            isRdy = false;
        }

        cameraUiWrapper.onCameraOpen("");
        return isRdy;
    }

    private  Camera openWrapper(int n) {
        Class[] arrclass = {Integer.TYPE, Integer.TYPE};
        try {
            Method method = Class.forName("android.hardware.Camera").getDeclaredMethod("openLegacy", arrclass);
            Object[] arrobject = {n, 256};
            return (Camera)method.invoke(null, arrobject);
        }
        catch (NoSuchMethodException e) {
            Logger.e(TAG, e.getMessage());
            return Camera.open(n);}
        catch (ClassNotFoundException e) {
            Logger.e(TAG, e.getMessage());
            return Camera.open(n);}
        catch (IllegalAccessException e) {
            Logger.e(TAG, e.getMessage());
            return Camera.open(n);}
        catch (InvocationTargetException e) {
            Logger.e(TAG, e.getMessage());
            return Camera.open(n);}
    }
}
