package meshnetwork.cycle8.com.bridgefychat;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import com.bridgefy.sdk.client.BridgefyClient;
import com.bridgefy.sdk.client.RegistrationListener;

/**
 * Created by Krot on 3/24/18.
 */

public class RegistrationListenerImplementation extends RegistrationListener {

    private Context context;

    public RegistrationListenerImplementation(Context context) {
        super();
        this.context = context;
    }

    @Override
    public void onRegistrationSuccessful(BridgefyClient bridgefyClient) {
        super.onRegistrationSuccessful(bridgefyClient);
        Log.i("WTF", "onRegistrationSuccessful: device name "+ Build.MANUFACTURER + " "+Build.MODEL);
        Log.i("WTF", "onRegistrationSuccessful: device evaluation "
                + bridgefyClient.getDeviceProfile().getDeviceEvaluation());
        context.sendBroadcast(new Intent(MainActivity.START_SERVICE_ACTION));
    }

    @Override
    public void onRegistrationFailed(int errorCode, String message) {
        super.onRegistrationFailed(errorCode, message);
    }
}

