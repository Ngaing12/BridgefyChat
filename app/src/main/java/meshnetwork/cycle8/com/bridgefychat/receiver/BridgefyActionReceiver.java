package meshnetwork.cycle8.com.bridgefychat.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.bridgefy.sdk.client.BFEnergyProfile;
import com.bridgefy.sdk.client.Bridgefy;
import com.bridgefy.sdk.client.Config;

import meshnetwork.cycle8.com.bridgefychat.MainActivity;
import meshnetwork.cycle8.com.bridgefychat.MessageListenerImplementation;
import meshnetwork.cycle8.com.bridgefychat.StateListenerImplementation;

/**
 * Created by Krot on 3/24/18.
 */

public class BridgefyActionReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent != null) {
            String action = intent.getAction();
            if (action != null) {
                if (TextUtils.equals(MainActivity.START_SERVICE_ACTION, action)) {
                    //start bridgefy
                    MessageListenerImplementation messageListenerImplementation = new MessageListenerImplementation();
                    StateListenerImplementation stateListenerImplementation = new StateListenerImplementation();
                    Config.Builder builder = new Config.Builder()
                            .setEnergyProfile(BFEnergyProfile.HIGH_PERFORMANCE)
                            .setEncryption(false);


                    Bridgefy.start(messageListenerImplementation, stateListenerImplementation, builder.build());
                    Bridgefy.setStateListener(stateListenerImplementation);
                    Bridgefy.setMessageListener(messageListenerImplementation);
                }
            }
        }
    }
}
