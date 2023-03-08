package Server;
import quickfix.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.concurrent.CountDownLatch;

public class Server {
    public static void main(String[] args) {
        SocketAcceptor socketAcceptor = null;
        try {
            SessionSettings executorSettings = new SessionSettings(
                    "src/serverExample.cfg");
            Application application = new ServerApp();
            FileStoreFactory fileStoreFactory = new FileStoreFactory(
                    executorSettings);
            MessageFactory messageFactory = new DefaultMessageFactory();
            FileLogFactory fileLogFactory = new FileLogFactory(executorSettings);
            socketAcceptor = new SocketAcceptor(application, fileStoreFactory,
                    executorSettings, fileLogFactory, messageFactory);
            socketAcceptor.start();
        } catch (ConfigError configError) {
            configError.printStackTrace();
        }
    }
}
