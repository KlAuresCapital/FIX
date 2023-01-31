package Server;
import quickfix.*;
import quickfix.fix44.NewOrderSingle;
import quickfix.field.*;

public class ServerApp extends MessageCracker implements Application {

    @Override
    public void onCreate(SessionID sessionID) { // when FIX creates a new session
        System.out.println("--------- onCreateServer ---------");
        System.out.println(sessionID);
    }

    @Override
    public void onLogon(SessionID sessionID) { //when a valid logon has been established, this is called
        System.out.println("--------- onLogonServer ---------");
        System.out.println(sessionID);
    }

    @Override
    public void onLogout(SessionID sessionID) { // FIX session is no longer available
        System.out.println("--------- onLogoutServer ---------");
        System.out.println(sessionID);
    }

    @Override
    public void toAdmin(Message message, SessionID sessionID) { // when an administrative message is sent to the initiator
        System.out.println("--------- toAdminServer ---------");
        System.out.println(sessionID);
        System.out.println(message);
    }

    @Override // when an administrative message is sent from the initator to us
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

    public void onMessage(quickfix.fix42.NewOrderSingle order, SessionID sessionID) throws FieldNotFound, IncorrectTagValue {
        System.out.println("###NewOrder Received:" + order.toString());
        System.out.println("###Symbol" + order.getSymbol().toString());
        System.out.println("###Side" + order.getSide().toString());
        System.out.println("###Type" + order.getOrdType().toString());
        System.out.println("###TransactioTime" + order.getTransactTime().toString());
        sendMessageToClient(order, sessionID);

    }

    public void sendMessageToClient(quickfix.fix42.NewOrderSingle order, SessionID sessionID) {
        try {
            OrderQty orderQty = null;
            orderQty = new OrderQty(56.0);
            quickfix.fix40.ExecutionReport accept = new quickfix.fix40.ExecutionReport(new OrderID("133456"), new ExecID("789"),
                    new ExecTransType(ExecTransType.NEW), new OrdStatus(OrdStatus.NEW), order.getSymbol(), order.getSide(),
                    orderQty, new LastShares(0), new LastPx(0), new CumQty(0), new AvgPx(0));
            accept.set(order.getClOrdID());
            System.out.println("###Sending Order Acceptance:" + accept.toString() + "sessionID:" + sessionID.toString());
            Session.sendToTarget(accept, sessionID);
        } catch (RuntimeException e) {
            LogUtil.logThrowable(sessionID, e.getMessage(), e);
        } catch (FieldNotFound | SessionNotFound fieldNotFound) {
            fieldNotFound.printStackTrace();
        }
    }
}
