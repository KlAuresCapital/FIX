package Client;
import quickfix.*;
import quickfix.field.*;
import quickfix.fix44.NewOrderSingle;


import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Client {
    public static void main(String[] args) throws FileNotFoundException, ConfigError, InterruptedException {
        String fileName = "src/client.cfg";
        ClientApp application = new ClientApp();
        SessionSettings settings = new SessionSettings(new FileInputStream(fileName));
        MessageStoreFactory storeFactory = new FileStoreFactory(settings);
        LogFactory logFactory = new FileLogFactory(settings);
        MessageFactory messageFactory = new DefaultMessageFactory();
        Initiator initiator = new SocketInitiator(application, storeFactory, settings, logFactory, messageFactory);
        initiator.start();
        while (ClientApp.sessionID == null) {
            Thread.sleep(1000);
        }


    }
}
