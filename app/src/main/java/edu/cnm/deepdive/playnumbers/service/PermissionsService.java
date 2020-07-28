package edu.cnm.deepdive.playnumbers.service;

import android.app.Dialog;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import edu.cnm.deepdive.playnumbers.R;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Set;


public class PermissionsService {

  private final MutableLiveData<Set<String>> permissions;

  private PermissionsService() {
    permissions = new MutableLiveData<>(Collections.emptySet());
  }

  public static PermissionsService getInstance() {
    return InstanceHolder.INSTANCE;
  }

  public LiveData<Set<String>> getPermissions() {
    return permissions;
  }

  public void checkPermissions(AppCompatActivity activity, int requestCode) {
    try {
      PackageInfo info = activity.getPackageManager().getPackageInfo(
          activity.getPackageName(), PackageManager.GET_META_DATA | PackageManager.GET_PERMISSIONS);
      String[] permissions = info.requestedPermissions;
      Set<String> permissionsGranted = new HashSet<>();
      List<String> permissionsToRequest = new LinkedList<>();
      List<String> permissionsToExplain = new LinkedList<>();
      for (String permission : permissions) {
        if (ContextCompat.checkSelfPermission(activity, permission)
            != PackageManager.PERMISSION_GRANTED) {
          permissionsToRequest.add(permission);
          if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {
            permissionsToExplain.add(permission);
          }
        } else {
          permissionsGranted.add(permission);
        }
      }
      updatePermissions(permissionsGranted);
      if (!permissionsToExplain.isEmpty()) {
        PermissionsFragment fragment =
            PermissionsFragment.createInstance(permissionsToExplain.toArray(new String[0]),
                permissionsToRequest.toArray(new String[0]), requestCode);
        fragment.show(activity.getSupportFragmentManager(), fragment.getClass().getName());
      } else if (!permissionsToRequest.isEmpty()) {
        ActivityCompat.requestPermissions(
            activity, permissionsToRequest.toArray(new String[0]), requestCode);
      }
    } catch (NameNotFoundException e) {
      throw new RuntimeException(e);
    }
  }

  public void updatePermissions(@NonNull String[] permissions, @NonNull int[] grants) {
    //noinspection ConstantConditions
    Set<String> workingPermissions = new HashSet<>(this.permissions.getValue());
    for (int i = 0; i < permissions.length; i++) {
      if (grants[i] == PackageManager.PERMISSION_GRANTED) {
        workingPermissions.add(permissions[i]);
      } else {
        workingPermissions.remove(permissions[i]);
      }
    }
    updatePermissions(workingPermissions);
  }

  private void updatePermissions(Set<String> permissions) {
    if (!Objects.equals(this.permissions.getValue(), permissions)) {
      this.permissions.setValue(Collections.unmodifiableSet(permissions));
    }
  }

  private void explainPermissions(AppCompatActivity activity,
      String[] permissionsToExplain, String[] permissionsToRequest, int requestCode) {
    PermissionsFragment fragment =
        PermissionsFragment.createInstance(permissionsToExplain, permissionsToRequest, requestCode);
    fragment.show(activity.getSupportFragmentManager(), fragment.getClass().getName());
  }

  private static class InstanceHolder {

    private static final PermissionsService INSTANCE = new PermissionsService();

  }

  public static class PermissionsFragment extends DialogFragment {

    private static final String PERMISSIONS_TO_EXPLAIN_KEY = "permissions_to_explain";
    private static final String PERMISSIONS_TO_REQUEST_KEY = "permissions_to_request";
    private static final String REQUEST_CODE_KEY = "request_code";

    @NonNull
    private static PermissionsFragment createInstance(@NonNull String[] permissionsToExplain,
        String[] permissionsToRequest, int requestCode) {
      if (permissionsToExplain.length == 0) {
        throw new IllegalArgumentException();
      }
      Bundle args = new Bundle();
      args.putStringArray(PERMISSIONS_TO_EXPLAIN_KEY, permissionsToExplain);
      args.putStringArray(PERMISSIONS_TO_REQUEST_KEY, permissionsToRequest);
      args.putInt(REQUEST_CODE_KEY, requestCode);
      PermissionsFragment fragment = new PermissionsFragment();
      fragment.setArguments(args);
      return fragment;
    }
  }

}
