package Client;
import quickfix.*;
import quickfix.field.*;
import quickfix.fix44.NewOrderSingle;
import quickfix.fix44.Logon;
import quickfix.field.Username;


import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class Client {
    public static void main(String[] args) throws ConfigError {
        SessionSettings settings = new SessionSettings("src/client.cfg");
        Application application = new ClientApp2();
        MessageStoreFactory messageStoreFactory = new FileStoreFactory(settings);
        LogFactory logFactory = new FileLogFactory(settings);
        MessageFactory messageFactory = new DefaultMessageFactory();
        Initiator initiator = new SocketInitiator(application, messageStoreFactory, settings, logFactory, messageFactory);
        initiator.start();
    }
}


// the previous method


//    public static void main(String[] args) {
//        SocketInitiator socketInitiator = null;
//        try {
//            SessionSettings initiatorSettings = new SessionSettings(
//                    "src/clientExample.cfg");
//            Application initiatorApplication = new ClientApp();
//            FileStoreFactory fileStoreFactory = new FileStoreFactory(
//                    initiatorSettings);
//            FileLogFactory fileLogFactory = new FileLogFactory(
//                    initiatorSettings);
//            MessageFactory messageFactory = new DefaultMessageFactory();
//            socketInitiator = new SocketInitiator(initiatorApplication, fileStoreFactory, initiatorSettings, fileLogFactory, messageFactory);
//            socketInitiator.start();
//            SessionID sessionId =  (SessionID) socketInitiator.getSessions().get(0);
//            Session.lookupSession(sessionId).logon();
//
//            Logon logon = new Logon();
//
//            logon.set(new quickfix.field.HeartBtInt(30));
//            logon.set(new quickfix.field.ResetSeqNumFlag(true));
//            logon.set(new quickfix.field.EncryptMethod(0));
//
//
//            try {
//                Session.sendToTarget(logon, sessionId);
//            } catch (SessionNotFound sessionNotFound) {
//                sessionNotFound.printStackTrace();
//            }
//            for(int j = 0; j < 2; j ++){
//                try {
//                    Thread.sleep(10000);
//                    NewOrderSingle newOrderSingle = new NewOrderSingle(
//                            new ClOrdID("456"),
//                            new Side(Side.BUY),
//                            new TransactTime(),
//                            new OrdType(OrdType.MARKET)
//                    );
//                    System.out.println("####New Order Sent :" + newOrderSingle.toString());
//                    Session.sendToTarget(newOrderSingle, sessionId);
//
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                } catch (SessionNotFound sessionNotFound) {
//                    sessionNotFound.printStackTrace();
//                }
//            }
//        }  catch (ConfigError configError) {
//            configError.printStackTrace();
//        }
//}