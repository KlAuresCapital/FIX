package Client;


import quickfix.*;
import quickfix.field.ClOrdID;
import quickfix.field.HandlInst;
import quickfix.fix44.NewOrderSingle;
import quickfix.field.*;

import java.io.FileNotFoundException;

import java.util.Date;
import java.util.concurrent.CountDownLatch;

public class ClientApp implements Application {

    public static volatile SessionID sessionID;

    @Override
    public void onCreate(SessionID sessionID) {
        System.out.println("--------- onCreateClient ---------");
        System.out.println(sessionID);
    }

    @Override
    public void onLogon(SessionID sessionID) {
        System.out.println("--------- onLogonClient ---------");
        System.out.println(sessionID);
        ClientApp.sessionID = sessionID;
    }

    @Override
    public void onLogout(SessionID sessionID) {
        System.out.println("--------- onLogoutClient ---------");
        ClientApp.sessionID = null;
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
    public void toApp(Message message, SessionID sessionID)  {
        System.out.println("--------- toAppClient ---------");
        System.out.println(message);
    }

    @Override
    public void fromApp(Message message, SessionID sessionID) throws FieldNotFound, IncorrectDataFormat, IncorrectTagValue, UnsupportedMessageType {
        System.out.println("--------- fromAppClient ---------");
        System.out.println(message);
    }



    public static void main(String[] args) throws ConfigError, FileNotFoundException, InterruptedException, SessionNotFound {
        SessionSettings settings = new SessionSettings("src/clientTest.cfg");

        Application application = new ClientApp();
        MessageStoreFactory messageStoreFactory = new FileStoreFactory(settings);
        LogFactory logFactory = new ScreenLogFactory( true, true, true);
        MessageFactory messageFactory = new DefaultMessageFactory();

        Initiator initiator = new SocketInitiator(application, messageStoreFactory, settings, logFactory, messageFactory);
        initiator.start();

        while (sessionID == null) {
            Thread.sleep(1000);
        }

        final String orderId = "342";
        NewOrderSingle newOrder = new NewOrderSingle(new ClOrdID(orderId),
                new Side(Side.BUY), new TransactTime(), new OrdType(OrdType.MARKET));
        Session.sendToTarget(newOrder, sessionID);
        Thread.sleep(5000);
    }
}