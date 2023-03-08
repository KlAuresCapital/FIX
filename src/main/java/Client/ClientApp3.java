package Client;

import Server.ServerApp2;
import quickfix.*;
import quickfix.field.*;
import quickfix.fix44.Logon;

import java.io.*;
import java.util.logging.Logger;

public class ClientApp3 implements Application {

    private SessionID sessionId;
    private static final Logger logger = Logger.getLogger(ServerApp2.class.getName());

    public void onCreate(SessionID sessionId) {
        System.out.println("Session created: " + sessionId);

    }

    public void onLogon(SessionID sessionId) {
        this.sessionId = sessionId;
    }

    public void onLogout(SessionID sessionId) {
        System.out.println("Logged out: " + sessionId);
        this.sessionId = null;
    }

    public void toAdmin(Message message, SessionID sessionId) {
        // Handle outgoing admin messages
        if (message instanceof Logon) {
            message.setString(Username.FIELD, "PHSYJDEMOMD");
            message.setString(Password.FIELD, "Pnpl2022");

        }

        logger.info("toAdmin" + message.toString());

    }

    public void fromAdmin(Message message, SessionID sessionId) throws FieldNotFound, IncorrectDataFormat, IncorrectTagValue, RejectLogon {
        // Handle incoming admin messages
        logger.info("fromAdmin" + message.toString());
    }

    public void toApp(Message message, SessionID sessionId) throws DoNotSend {
        // Handle outgoing application messages
        logger.info("toApp " + message.toString());
    }

    public void fromApp(Message message, SessionID sessionId) throws FieldNotFound, IncorrectDataFormat, IncorrectTagValue, UnsupportedMessageType {
        // Handle incoming application messages
        logger.info("FromApp");
    }

    public void start() throws ConfigError {
        SessionSettings settings = new SessionSettings("src/philipConfig.cfg");
        MessageStoreFactory messageStoreFactory = new FileStoreFactory(settings);
        LogFactory logFactory = new ScreenLogFactory(settings);
        DefaultMessageFactory messageFactory = new DefaultMessageFactory();
        Initiator initiator = new SocketInitiator(this, messageStoreFactory, settings, logFactory, messageFactory);
        initiator.start();
    }

    public void stop() {
        // Stop the initiator
    }

    public void logon() throws SessionNotFound {
        logger.info("logon here");
        Session.lookupSession(sessionId).logon();
    }

    public static void main(String[] args) throws Exception {
        ClientApp3 clientApplication = new ClientApp3();
        clientApplication.start();
        clientApplication.logon();

        // ... code to send messages and receive responses
    }
    public void sendMessage(String symbol) throws SessionNotFound {
        Message message = new Message();
        message.getHeader().setField(new MsgType(MsgType.ORDER_SINGLE));
        message.setField(new Symbol(symbol));
        Session.sendToTarget(message, sessionId);
    }
}
