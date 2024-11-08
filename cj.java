import com.mic.smsgame.suquan.t;
import javax.microedition.lcdui.Graphics;

public class cj extends ds {
  private boolean a;
  
  private int b;
  
  private t c;
  
  public int a() {
    return this.b;
  }
  
  public void a(int paramInt) {
    this.b = paramInt;
  }
  
  public cj(int paramInt1, int paramInt2) {
    this.p = paramInt1;
    this.q = paramInt2;
    this.c = new t((bs.a()).B);
  }
  
  public void b(int paramInt) {
    this.b = paramInt;
    this.a = true;
    if (paramInt < 0)
      this.a = false; 
  }
  
  public void a(Graphics paramGraphics, int paramInt1, int paramInt2) {
    if (this.a) {
      int i = this.b / 25;
      int j = i / 60;
      int k = i % 60;
      bz.c.a(paramGraphics, ":", this.p, this.q - 4, 1);
      this.c.a(paramGraphics, j, this.p - 2, this.q, 2);
      this.c.a(paramGraphics, k, this.p + 2, this.q, 0);
    } 
  }
  
  public void b() {
    if (this.b > 0) {
      this.b--;
      if (this.b == 0) {
        this.a = true;
      } else if (this.b < 250) {
        if (this.b % 3 == 0)
          this.a = !this.a; 
      } else if (this.b < 750 && this.b % 8 == 0) {
        this.a = !this.a;
      } 
    } 
  }
}


/* Location:              C:\Users\bot-nosense\Downloads\Loan-12-Su-Quan.jar!\cj.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */