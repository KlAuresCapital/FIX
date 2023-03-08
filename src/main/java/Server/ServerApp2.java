package Server;

import quickfix.*;
import quickfix.field.*;
import quickfix.fix44.Logon;
import java.util.logging.Logger;

public class ServerApp2 implements Application {
    private static final Logger logger = Logger.getLogger(ServerApp2.class.getName());
    public void onCreate(SessionID sessionId) {
        // This method is called when a new session is created.
        // You can use this method to perform any necessary initialization.
        logger.info("Session created: " + sessionId.toString());
    }

    public void onLogon(SessionID sessionId) {
        // This method is called when a session is successfully logged on.
        // You can use this method to perform any actions that should occur after the session is established.
        logger.info("Session logged on: " + sessionId.toString());
    }

    public void onLogout(SessionID sessionId) {
        // This method is called when a session is disconnected or logged off.
        // You can use this method to perform any actions that should occur when the session is disconnected.
        logger.info("Session logged out: " + sessionId.toString());
    }

    public void toAdmin(Message message, SessionID sessionId) {
        // This method is called before an administrative message is sent.
        // You can use this method to modify or inspect the administrative message.
        // For example, you could modify the message to add a password or other security information.
        logger.info("toAdmin Ran: " + sessionId.toString() + message.toString());
//        if (message instanceof Logon) {
//            // Set the Username and Password fields for the Logon message
//            message.setString(Username.FIELD, "myUsername");
//            message.setString(Password.FIELD, "myPassword");
//        }
    }

    public void toApp(Message message, SessionID sessionId) throws DoNotSend {
        // This method is called before an application message is sent.
        // You can use this method to modify or inspect the application message.
    }

    public void fromAdmin(Message message, SessionID sessionId) throws FieldNotFound, IncorrectDataFormat, IncorrectTagValue, RejectLogon {
        // This method is called when an administrative message is received.
        // You can use this method to inspect or handle administrative messages.
        logger.info("SERVER fromAdmin " + message.toString() + sessionId.toString());
    }

    public void fromApp(Message message, SessionID sessionId) throws FieldNotFound, IncorrectDataFormat, IncorrectTagValue, UnsupportedMessageType {
        // This method is called when an application message is received.
        // You can use this method to handle incoming application messages.
        logger.info("SERVER fromApp " + message.toString() + sessionId.toString());
        if (message.getHeader().getString(MsgType.FIELD).equals(MsgType.REJECT)) {
            // Handle reject message
        } else if (message.getHeader().getString(MsgType.FIELD).equals(MsgType.ORDER_SINGLE)) {
            // Handle order message
        }
    }

}

