package com.example.uytai.farmersp.thuonglai.permission;

import android.Manifest;
import android.app.Activity;



/**
 * Copyright (C) 2018 EdgeWorks Software.
 * All rights reserved.
 *
 * @author ruyn.
 * @since 04/07/2018
 */
public class CheckPermisstionUtils {

    public static final int LOCATION_PERMISSION_REQ_CODE = 0x006;
    public static final int CAMERA_PERMISSTION_CODE = 0x008;
    public static final int READ_EXTERNAL_STORAGE_PERMISSTION_CODE = 0x009;

    public static void checkLocation(Activity activity,
                                     PermissionUtil.ReqPermissionCallback callback) {
        PermissionUtil.checkPermission(activity,
                Manifest.permission.ACCESS_FINE_LOCATION,
                LOCATION_PERMISSION_REQ_CODE,
                "We need location permission to locate your position",
                "We can't get your location without location permission",
                callback);
    }

    public static void checkCameraPermisstion(Activity activity,
                                              PermissionUtil.ReqPermissionCallback callback) {
        PermissionUtil.checkPermission(activity,
                Manifest.permission.CAMERA, CAMERA_PERMISSTION_CODE,
                "We need camera permission to open your camera",
                "We can't open your camera without camera permission",
                callback);
    }

    public static void checkReadDataPermisstion(Activity activity,
                                                PermissionUtil.ReqPermissionCallback callback) {
        PermissionUtil.checkPermission(activity,
                Manifest.permission.READ_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE_PERMISSTION_CODE,
                "We need read external storage permission to get image from category",
                "We can't get image from category without read external storage permission",
                callback);
    }
}
