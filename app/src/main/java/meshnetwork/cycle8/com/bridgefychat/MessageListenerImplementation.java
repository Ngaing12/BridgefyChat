package meshnetwork.cycle8.com.bridgefychat;

import com.bridgefy.sdk.client.Message;
import com.bridgefy.sdk.client.MessageListener;
import com.bridgefy.sdk.framework.exceptions.MessageException;

import java.util.UUID;

/**
 * Created by Krot on 3/22/18.
 */

public class MessageListenerImplementation extends MessageListener {

    public MessageListenerImplementation() {
        super();
    }

    @Override
    public void onMessageReceived(Message message) {
        super.onMessageReceived(message);
    }

    @Override
    public void onMessageSent(Message message) {
        super.onMessageSent(message);
    }

    @Override
    public void onMessageDataProgress(UUID message, long progress, long fullSize) {
        super.onMessageDataProgress(message, progress, fullSize);
    }

    @Override
    public void onMessageReceivedException(String sender, MessageException e) {
        super.onMessageReceivedException(sender, e);
    }

    @Override
    public void onMessageFailed(Message message, MessageException e) {
        super.onMessageFailed(message, e);
    }

    @Override
    public void onBroadcastMessageReceived(Message message) {
        super.onBroadcastMessageReceived(message);
    }
}
