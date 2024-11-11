import java.io.IOException;
import javax.wireless.messaging.BinaryMessage;
import javax.wireless.messaging.Message;
import javax.wireless.messaging.MessageConnection;
import javax.wireless.messaging.TextMessage;

class ct implements Runnable {
  private Message a;
  
  private MessageConnection b;
  
  private String c;
  
  private String d = "";
  
  private ba e;
  
  ct(ba paramba, MessageConnection paramMessageConnection) {
    this.b = paramMessageConnection;
    this.e = paramba;
  }
  
  public void a() {
    Thread thread = new Thread(this);
    thread.start();
  }
  
  public void run() {
    while (!cx.N) {
      try {
        Thread.sleep(100L);
      } catch (Exception exception) {}
    } 
    b();
  }
  
  void b() {
    try {
      this.a = this.b.receive();
      if (this.a != null) {
        this.c = this.a.getAddress();
        String str = this.c;
        if (this.a instanceof TextMessage) {
          this.d = ((TextMessage)this.a).getPayloadText();
          if (this.e != null)
            this.e.a(this.d, str); 
        } else {
          byte[] arrayOfByte = ((BinaryMessage)this.a).getPayloadData();
          this.d = new String(arrayOfByte);
          if (this.e != null)
            this.e.a(this.d, str); 
        } 
      } 
    } catch (IOException iOException) {}
  }
}


/* Location:              C:\Users\bot-nosense\Downloads\Loan-12-Su-Quan.jar!\ct.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */