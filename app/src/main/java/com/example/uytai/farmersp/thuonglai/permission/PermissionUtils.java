package com.example.uytai.farmersp.thuonglai.permission;

import android.content.Context;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;


/**
 * Copyright (C) 2018 EdgeWorks Software.
 * All rights reserved.
 *
 * @author ruyn.
 * @since 06/07/2018
 */
public class PermissionUtils {
    private PermissionUtils() {}

    public static boolean isGranted(int permissionFlag) {
        return PackageManager.PERMISSION_GRANTED == permissionFlag;
    }

    public static boolean isGranted(int[] permissionFlags) {
        if (null == permissionFlags || permissionFlags.length == 0) {
            return false;
        }
        for (int flag : permissionFlags) {
            if (!isGranted(flag)) {
                return false;
            }
        }
        return true;
    }

    public static int getPermissionFlag(@NonNull Context context, @NonNull String permission) {
        return ContextCompat.checkSelfPermission(context, permission);
    }

    public static boolean hasPermission(@NonNull Context context, @NonNull String permission) {
        return isGranted(getPermissionFlag(context, permission));
    }

    public static String[] getPermission(Context activity, String... permissions) {
        List<String> permissionsList = new ArrayList<>();
        for (String permission : permissions) {
            if(!PermissionUtils.hasPermission(activity, permission)) {
                permissionsList.add(permission);
            }
        }
        return permissionsList.toArray(new String[permissionsList.size()]);
    }
}
