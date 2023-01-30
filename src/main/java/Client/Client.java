package Client;
import quickfix.*;


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
        System.out.println(logFactory);
        System.out.println("test1");
        Initiator initiator = new SocketInitiator(application, storeFactory, settings, logFactory, messageFactory);
        initiator.start();
        System.out.println("next1");
        while (true) {
            Thread.sleep(2000);
        }

    }
}
