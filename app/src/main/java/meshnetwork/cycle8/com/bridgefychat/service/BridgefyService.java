package meshnetwork.cycle8.com.bridgefychat.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import com.bridgefy.sdk.client.BFEnergyProfile;
import com.bridgefy.sdk.client.Bridgefy;
import com.bridgefy.sdk.client.BridgefyClient;
import com.bridgefy.sdk.client.Config;

import meshnetwork.cycle8.com.bridgefychat.MainActivity;
import meshnetwork.cycle8.com.bridgefychat.MessageListenerImplementation;
import meshnetwork.cycle8.com.bridgefychat.RegistrationListenerImplementation;
import meshnetwork.cycle8.com.bridgefychat.StateListenerImplementation;

/**
 * Created by Krot on 3/22/18.
 */

public class BridgefyService extends Service {

    private static final String APP_API_KEY = "39a79cbd-96ee-4d9f-9099-0896c9a96ed7";
    private static final String PREFIX = BridgefyService.class.getSimpleName();
    private Context context = BridgefyService.this;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("WTF", PREFIX + ": onCreate");
        RegistrationListenerImplementation registrationListenerImplementation = new RegistrationListenerImplementation(context);
        Bridgefy.initialize(context, APP_API_KEY, registrationListenerImplementation);
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("WTF", PREFIX + ": onStartCommand");
        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("WTF", PREFIX + ": onDestroy");
        Bridgefy.stop();
    }

}
