package meshnetwork.cycle8.com.bridgefychat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.bridgefy.sdk.client.Bridgefy;
import com.bridgefy.sdk.client.BridgefyClient;
import com.bridgefy.sdk.client.Device;
import com.bridgefy.sdk.client.DeviceProfile;
import com.bridgefy.sdk.client.RegistrationListener;
import com.bridgefy.sdk.client.registration.RegistrationRequest;
import com.bridgefy.sdk.client.registration.RegistrationResponse;

import meshnetwork.cycle8.com.bridgefychat.service.BridgefyService;

public class MainActivity extends AppCompatActivity {

    public static final String START_SERVICE_ACTION = "meshnetwork.cycle8.com.bridgefychat.START_BRIDGEFY";
    private static final String PREFIX = MainActivity.class.getSimpleName();
    private static final int PERMISSION_CODE = 101;
    private static final int REQUEST_PERMISSION_SETTING = 102;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] permission = {Manifest.permission.ACCESS_COARSE_LOCATION};
        if (!hasPermission(this, permission)) {
            Log.i("WTF", PREFIX + ": NOT GRANTED");
            ActivityCompat.requestPermissions(this, permission, PERMISSION_CODE);
        } else {
            Log.i("WTF", PREFIX + ": GRANTED");
            //do something else
            startBridgefyService();

        }
    }

    private boolean hasPermission(Context context, String ... permissionQueue) {
        if (context != null && permissionQueue != null) {
            for (String permission : permissionQueue) {
                if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_CODE:
                if (grantResults.length > 0) {
                    if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        Log.i("WTF", PREFIX + ": GRANTED");
                        startBridgefyService();
                    } else if (grantResults[0] == PackageManager.PERMISSION_DENIED) {
                        Log.i("WTF", PREFIX + ": DENIED");
                        boolean shouldShowRationale = ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_COARSE_LOCATION);
                        if (shouldShowRationale) {
                            //show the reason why user must grant STORAGE permission
                            //show dialog
                            new AlertDialog.Builder(this).setTitle("Permission Denied").setMessage(R.string.permission_rationale).setPositiveButton("RE-TRY", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, PERMISSION_CODE);
                                }
                            }).setNegativeButton("I'M SURE", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            }).show();

                        } else {
                            //never ask again
                            //close dialog and do nothing
                            new AlertDialog.Builder(this)
                                    .setTitle("Grant permission")
                                    .setMessage(R.string.app_setting_permission)
                                    .setPositiveButton("Open", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            Intent appSettingIntent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                            Uri uri = Uri.fromParts("package", getPackageName(), null);
                                            appSettingIntent.setData(uri);
                                            startActivityForResult(appSettingIntent, REQUEST_PERMISSION_SETTING);
                                        }
                                    })
                                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            dialogInterface.dismiss();
                                        }
                                    }).show();
                        }
                    }
                }
                break;
        }
    }


    private void startBridgefyService() {
        Intent startBridgefyIntentService = new Intent(MainActivity.this, BridgefyService.class);
        startService(startBridgefyIntentService);
    }
}
