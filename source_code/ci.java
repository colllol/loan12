public class ci implements Runnable {
  private static ci a;
  
  private static Object b;
  
  private boolean c = true;
  
  private at[] d;
  
  private int e;
  
  private ci() {
    b = new Object();
    this.c = false;
  }
  
  public void a(int paramInt) {
    if (paramInt <= 0)
      throw new RuntimeException("Size of pool cannot be less than or equal 0"); 
    this.d = new at[paramInt];
    this.c = false;
    (new Thread(this)).start();
  }
  
  public void a(at paramat) {
    if (this.d == null)
      return; 
    for (byte b = 0; b < this.d.length; b++) {
      if (this.d[b] == null) {
        this.d[b] = paramat;
        this.e++;
        try {
          synchronized (b) {
            b.notify();
          } 
        } catch (Exception exception) {
          try {
            synchronized (b) {
              b.notify();
            } 
          } catch (Exception exception1) {}
        } 
        break;
      } 
    } 
  }
  
  public void run() {
    while (!this.c) {
      for (byte b = 0; b < this.d.length; b++) {
        if (this.d[b] != null) {
          this.d[b].c();
          this.d[b] = null;
          this.e--;
        } 
      } 
      if (this.e <= 0)
        try {
          synchronized (b) {
            b.wait();
          } 
        } catch (Exception exception) {} 
    } 
  }
  
  public static ci a() {
    if (a == null)
      a = new ci(); 
    return a;
  }
  
  public void b() {
    this.c = true;
    synchronized (b) {
      b.notify();
    } 
  }
}


/* Location:              C:\Users\bot-nosense\Downloads\Loan-12-Su-Quan.jar!\ci.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */