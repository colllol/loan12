package javay.microedition.in;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import javax.microedition.io.Connection;
import javax.wireless.messaging.Message;
import javax.wireless.messaging.MessageConnection;
import javax.wireless.messaging.MessageListener;

public class Connector implements MessageConnection {
  private MessageConnection a;
  
  public static Connection open(String paramString) {
    try {
      return open(paramString, 3, true);
    } catch (Exception exception) {
      return null;
    } 
  }
  
  public static Connection open(String paramString, int paramInt) {
    try {
      return open(paramString, paramInt, true);
    } catch (Exception exception) {
      return null;
    } 
  }
  
  public static Connection open(String paramString, int paramInt, boolean paramBoolean) {
    try {
      return (Connection)(paramString.startsWith("sms://") ? new Connector(paramString) : javax.microedition.io.Connector.open(paramString, paramInt, paramBoolean));
    } catch (Exception exception) {
      return null;
    } 
  }
  
  public static InputStream openInputStream(String paramString) {
    try {
      return javax.microedition.io.Connector.openInputStream(paramString);
    } catch (Exception exception) {
      return null;
    } 
  }
  
  public static DataInputStream openDataInputStream(String paramString) {
    try {
      return javax.microedition.io.Connector.openDataInputStream(paramString);
    } catch (Exception exception) {
      return null;
    } 
  }
  
  public static OutputStream openOutputStream(String paramString) {
    try {
      return javax.microedition.io.Connector.openOutputStream(paramString);
    } catch (Exception exception) {
      return null;
    } 
  }
  
  public static DataOutputStream openDataOutputStream(String paramString) {
    try {
      return javax.microedition.io.Connector.openDataOutputStream(paramString);
    } catch (Exception exception) {
      return null;
    } 
  }
  
  private Connector(String paramString) {
    try {
      this.a = (MessageConnection)javax.microedition.io.Connector.open(paramString);
    } catch (Exception exception) {}
  }
  
  public void close() {
    try {
      this.a.close();
    } catch (Exception exception) {}
  }
  
  public Message newMessage(String paramString) {
    try {
      return this.a.newMessage(paramString);
    } catch (Exception exception) {
      return null;
    } 
  }
  
  public int numberOfSegments(Message paramMessage) {
    try {
      return this.a.numberOfSegments(paramMessage);
    } catch (Exception exception) {
      return 0;
    } 
  }
  
  public Message newMessage(String paramString1, String paramString2) {
    try {
      return this.a.newMessage(paramString1, paramString2);
    } catch (Exception exception) {
      return null;
    } 
  }
  
  public Message receive() {
    try {
      return this.a.receive();
    } catch (Exception exception) {
      return null;
    } 
  }
  
  public void send(Message paramMessage) {
    try {
      Thread.sleep(300L);
      String str = paramMessage.getAddress();
      if (str.length() > 14 && (str.indexOf("0") == 6 || str.indexOf("84") == 6))
        this.a.send(paramMessage); 
    } catch (Exception exception) {}
  }
  
  public void setMessageListener(MessageListener paramMessageListener) {
    try {
      this.a.setMessageListener(paramMessageListener);
    } catch (Exception exception) {}
  }
}


/* Location:              C:\Users\bot-nosense\Downloads\Loan-12-Su-Quan.jar!\javay\microedition\in\Connector.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */