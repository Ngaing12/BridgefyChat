package meshnetwork.cycle8.com.bridgefychat;

import android.util.Log;

import com.bridgefy.sdk.client.Device;
import com.bridgefy.sdk.client.Session;
import com.bridgefy.sdk.client.StateListener;

/**
 * Created by Krot on 3/22/18.
 */

public class StateListenerImplementation extends StateListener {

    public StateListenerImplementation() {
        super();
    }

    @Override
    public void onStarted() {
        super.onStarted();
        Log.i("WTF", "StateListener: onStarted");
    }


    @Override
    public void onStartError(String message, int errorCode) {
        super.onStartError(message, errorCode);
        Log.i("WTF", "StateListener: onStartError - errorCode = " + errorCode + " - message = " + message);
    }

    @Override
    public void onStopped() {
        super.onStopped();
        Log.i("WTF", "StateListener: onStarted");
    }

    @Override
    public void onDeviceConnected(Device device, Session session) {
        super.onDeviceConnected(device, session);
        Log.i("WTF", "StateListener: onDeviceConnected - deviceName = " + device.getDeviceName() + " - session = " + session);
    }

    @Override
    public void onDeviceLost(Device device) {
        super.onDeviceLost(device);
        Log.i("WTF", "StateListener: onDeviceLost - deviceName = " + device.getDeviceName());
    }
}
