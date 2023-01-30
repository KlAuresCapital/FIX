package Client;


import quickfix.*;

public class ClientApp implements Application {

    @Override
    public void onCreate(SessionID sessionID) {
        System.out.println("--------- onCreateClient ---------");
        System.out.println(sessionID);
    }

    @Override
    public void onLogon(SessionID sessionID) {
        System.out.println("--------- onLogonClient ---------");
        System.out.println(sessionID);
    }

    @Override
    public void onLogout(SessionID sessionID) {
        System.out.println("--------- onLogoutClient ---------");
    }

    @Override
    public void toAdmin(Message message, SessionID sessionID) {
        System.out.println("--------- toAdminClient ---------");
        System.out.println(message);
    }

    @Override
    public void fromAdmin(Message message, SessionID sessionID) throws FieldNotFound, IncorrectDataFormat, IncorrectTagValue, RejectLogon {
        System.out.println("--------- fromAdminClient ---------");
        System.out.println(message);
    }

    @Override
    public void toApp(Message message, SessionID sessionID) throws DoNotSend {
        System.out.println("--------- toAppClient ---------");
        System.out.println(message);
    }

    @Override
    public void fromApp(Message message, SessionID sessionID) throws FieldNotFound, IncorrectDataFormat, IncorrectTagValue, UnsupportedMessageType {
        System.out.println("--------- fromAppClient ---------");
        System.out.println(message);
    }
}