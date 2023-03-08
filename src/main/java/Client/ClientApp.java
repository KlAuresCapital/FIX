package Client;


import quickfix.*;
import quickfix.field.ClOrdID;
import quickfix.field.HandlInst;
import quickfix.fix44.NewOrderSingle;
import quickfix.field.*;

import java.io.FileNotFoundException;

import java.util.Date;
import java.util.concurrent.CountDownLatch;

import quickfix.*;
public class ClientApp extends MessageCracker implements Application{
    @Override
    public void onCreate(SessionID sessionID) {
        System.out.println("-----onCreate------");
    }
    @Override
    public void onLogon(SessionID sessionID) {
        System.out.println("-----onLogon------");
        System.out.println(sessionID);
    }
    @Override
    public void onLogout(SessionID sessionID) {
        System.out.println("-----onLogout------");
    }
    @Override
    public void toAdmin(Message message, SessionID sessionID) {
        System.out.println("-----toAdmin------");
        System.out.println(message);
        System.out.println(sessionID);
        try {
            final String msgType = message.getHeader().getString(MsgType.FIELD);
            if(MsgType.LOGON.compareTo(msgType) == 0)
            {
                System.out.println("sent logon");
                message.setString(quickfix.field.Username.FIELD, "PHSYJDEMOMD");
                message.setString(quickfix.field.Password.FIELD, "Pnpl2022");
            }
            System.out.println(message);

        } catch (FieldNotFound e) {
            throw new RuntimeException(e);
        }

    }
    @Override
    public void fromAdmin(Message message, SessionID sessionID) throws FieldNotFound, IncorrectDataFormat, IncorrectTagValue, RejectLogon {
        System.out.println("Admin Message Received (Initiator) :" + message.toString());
    }
    @Override
    public void toApp(Message message, SessionID sessionID) throws DoNotSend {
        System.out.println("-----toApp------");
    }
    @Override
    public void fromApp(Message message, SessionID sessionID) throws FieldNotFound, IncorrectDataFormat, IncorrectTagValue, UnsupportedMessageType {
        System.out.println("Application Response Received (Initiator) :" +  message.toString());
    }
}