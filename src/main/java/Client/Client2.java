package Client;
import quickfix.*;
import quickfix.field.*;


public class Client2 {
    public static void main(String[] args) {
        try {
            SessionSettings settings = new SessionSettings("src/clientExample.cfg");
            Application application = new ClientApp2();
            MessageStoreFactory messageStoreFactory = new FileStoreFactory(settings);
            LogFactory logFactory = new FileLogFactory(settings);
            MessageFactory messageFactory = new DefaultMessageFactory();
            SocketInitiator initiator = new SocketInitiator(application, messageStoreFactory, settings, logFactory, messageFactory);
            initiator.start();
            SessionID sessionId = initiator.getSessions().get(0);
            Message message = new Message();
            message.getHeader().setString(MsgType.FIELD, "TEST");
            Session.sendToTarget(message, sessionId);

        } catch (ConfigError | SessionNotFound e) {
            e.printStackTrace();
        }
    }
}
