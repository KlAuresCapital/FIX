package Server;
import quickfix.*;

public class ServerApp implements Application {

    @Override
    public void onCreate(SessionID sessionID) {
        System.out.println("--------- onCreateServer ---------");
        System.out.println(sessionID);
    }

    @Override
    public void onLogon(SessionID sessionID) {
        System.out.println("--------- onLogonServer ---------");
        System.out.println(sessionID);
    }

    @Override
    public void onLogout(SessionID sessionID) {
        System.out.println("--------- onLogoutServer ---------");
        System.out.println(sessionID);
    }

    @Override
    public void toAdmin(Message message, SessionID sessionID) {
        System.out.println("--------- toAdminServer ---------");
        System.out.println(sessionID);
        System.out.println(message);
    }

    @Override
    public void fromAdmin(Message message, SessionID sessionID) throws FieldNotFound, IncorrectDataFormat, IncorrectTagValue, RejectLogon {
        System.out.println("--------- fromAdminServer ---------");
        System.out.println(message);
    }

    @Override
    public void toApp(Message message, SessionID sessionID) throws DoNotSend {
        System.out.println("--------- toAppServer ---------");
        System.out.println(message);
    }

    @Override
    public void fromApp(Message message, SessionID sessionID) throws FieldNotFound, IncorrectDataFormat, IncorrectTagValue, UnsupportedMessageType {
        System.out.println("--------- fromAppServer ---------");
        System.out.println(message);
    }
}
