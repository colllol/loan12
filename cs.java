import com.mg.smsgame.main.b;
import java.io.IOException;
import javax.wireless.messaging.MessageConnection;
import javax.wireless.messaging.TextMessage;
import javay.microedition.in.Connector;

class cs implements b, Runnable {
  private bc a;
  
  private bc b;
  
  private static cs c;
  
  private boolean d;
  
  private Object e = new Object();
  
  public static cs a() {
    if (c == null)
      c = new cs(); 
    cz.f();
    return c;
  }
  
  protected cs() {
    Thread thread = new Thread(this);
    thread.start();
  }
  
  public void run() {
    this.d = false;
    while (!this.d) {
      if (this.a == null) {
        try {
          synchronized (this.e) {
            this.e.wait();
          } 
        } catch (Throwable throwable) {
          throwable.printStackTrace();
        } 
        continue;
      } 
      cx.H = true;
      cz.d().a(cz.b("Chú ý", "Đang gửi tin nhắn SMS", false), false);
      String str = "sms://" + this.a.a;
      MessageConnection messageConnection = null;
      try {
        messageConnection = (MessageConnection)Connector.open(str);
        TextMessage textMessage = (TextMessage)messageConnection.newMessage("text");
        textMessage.setAddress(str);
        textMessage.setPayloadText(this.a.b);
        try {
          al.c();
        } catch (Throwable throwable) {
          throwable.printStackTrace();
        } 
        cz.d().a(false);
        if (this.a.c != null)
          this.a.c.a(); 
        cx.H = false;
        this.a = null;
      } catch (Throwable throwable) {
        throwable.printStackTrace();
        this.b = this.a;
        this.a = null;
        cz.d().a(false);
        ae ae = cz.a("Chú ý", "Không thể gửi tin SMS. Bạn có muốn gửi lại không?", "Có", 1, "Không", 2, 1);
        ae.a(this);
        cz.d().a(ae);
        continue;
      } finally {
        if (messageConnection != null)
          try {
            messageConnection.close();
          } catch (IOException iOException) {
            iOException.printStackTrace();
          }  
        if (!cx.F && al.a())
          al.b(); 
      } 
    } 
  }
  
  void a(bc parambc) {
    if (parambc == null)
      return; 
    this.a = parambc;
    synchronized (this.e) {
      this.e.notify();
    } 
  }
  
  public void b(int paramInt1, int paramInt2) {
    if (paramInt2 == 2) {
      cx.H = false;
      cz.d().a(false);
      cz.d().a(true);
    } else if (paramInt2 == 1) {
      cz.d().a(false);
      a(this.b);
      this.b = null;
    } 
  }
}


/* Location:              C:\Users\bot-nosense\Downloads\Loan-12-Su-Quan.jar!\cs.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */