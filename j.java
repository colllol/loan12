import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class j extends bw {
  public void a() {
    this.f = this.d / 14 + 1;
    this.h = new int[this.f];
  }
  
  public void a(Graphics paramGraphics, Image paramImage) {
    int i = this.d;
    for (byte b = 0; b < this.f && this.h[b] > 0; b++) {
      int k = this.h[b];
      int m = i - k;
      if (b == this.f - 1)
        if (m + k > 0) {
          if (m < 0) {
            k = m + k;
            m = 0;
          } 
        } else {
          continue;
        }  
      y.a(paramGraphics, paramImage, 0, m, this.c, k, this.a, this.b + m, 20);
      i -= 14;
      continue;
    } 
  }
}


/* Location:              C:\Users\bot-nosense\Downloads\Loan-12-Su-Quan.jar!\j.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */