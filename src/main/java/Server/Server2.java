package Server;
import quickfix.*;
import quickfix.field.*;

public class Server2 {
    public static void main(String[] args) throws ConfigError {
        SessionSettings settings = new SessionSettings("src/serverExample.cfg");
        Application application = new ServerApp2();
        MessageStoreFactory messageStoreFactory = new FileStoreFactory(settings);
        LogFactory logFactory = new FileLogFactory(settings);
        MessageFactory messageFactory = new DefaultMessageFactory();
        Acceptor acceptor = new SocketAcceptor(application, messageStoreFactory, settings, logFactory, messageFactory);
        acceptor.start();
    }
}
