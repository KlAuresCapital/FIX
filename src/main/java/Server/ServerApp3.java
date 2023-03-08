package Server;

import org.quickfixj.jmx.JmxExporter;
import quickfix.*;

public class ServerApp3 implements Application {

    public static void main(String[] args) throws Exception {

        SessionSettings settings = new SessionSettings("src/serverExample.cfg");
        MessageStoreFactory storeFactory = new FileStoreFactory(settings);
        LogFactory logFactory = new FileLogFactory(settings);
        MessageFactory messageFactory = new DefaultMessageFactory();

        SocketAcceptor acceptor = new SocketAcceptor(new ServerApp3(), storeFactory, settings, logFactory, messageFactory);
        JmxExporter exporter = new JmxExporter();
        exporter.register(acceptor);

        acceptor.start();

        System.out.println("Server started");

        // Wait for stop signal
        System.in.read();

        acceptor.stop();

    }

    @Override
    public void onCreate(SessionID sessionId) {
        System.out.println("Session created: " + sessionId);
    }

    @Override
    public void onLogon(SessionID sessionId) {
        System.out.println("Logon successful for session: " + sessionId);
    }

    @Override
    public void onLogout(SessionID sessionId) {
        System.out.println("Logout successful for session: " + sessionId);
    }

    @Override
    public void toAdmin(Message message, SessionID sessionId) {
        System.out.println("toAdmin: " + message);
    }

    @Override
    public void fromAdmin(Message message, SessionID sessionId) throws FieldNotFound, IncorrectDataFormat, IncorrectTagValue, RejectLogon {
        System.out.println("fromAdmin: " + message);
    }

    @Override
    public void toApp(Message message, SessionID sessionId) throws DoNotSend {
        System.out.println("toApp: " + message);
    }

    @Override
    public void fromApp(Message message, SessionID sessionId) throws FieldNotFound, IncorrectDataFormat, IncorrectTagValue, UnsupportedMessageType {
        System.out.println("fromApp: " + message);
    }

}

