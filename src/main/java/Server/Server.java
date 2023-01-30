package Server;
import quickfix.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Server {
    public static void main(String[] args) throws FileNotFoundException, ConfigError, InterruptedException {
        String fileName = "src/server.cfg";
        ServerApp application = new ServerApp();
        SessionSettings settings = new SessionSettings(new FileInputStream(fileName));
        MessageStoreFactory storeFactory = new FileStoreFactory(settings);
        LogFactory logFactory = new FileLogFactory(settings);
        MessageFactory messageFactory = new DefaultMessageFactory();
        System.out.println(logFactory);
        System.out.println("test");
        Acceptor acceptor = new SocketAcceptor(application, storeFactory, settings, logFactory, messageFactory);
        acceptor.start();
        System.out.println("next");
        while (true) {
            Thread.sleep(2000);
        }




    }
}