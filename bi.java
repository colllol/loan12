import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class bi extends bw {
  public void a() {
    this.f = this.c / 14 + 1;
    this.h = new int[this.f];
  }
  
  public void a(Graphics paramGraphics, Image paramImage) {
    byte b1 = 0;
    for (byte b2 = 0; b2 < this.f && this.h[b2] > 0; b2++) {
      int i = this.h[b2];
      byte b = b1;
      if (b2 == this.f - 1)
        if (b < this.c) {
          if (b + i > this.c)
            i = this.c - b; 
        } else {
          continue;
        }  
      y.a(paramGraphics, paramImage, b, 0, i, this.d, this.a + b, this.b, 20);
      b1 += 14;
      continue;
    } 
  }
}


/* Location:              C:\Users\bot-nosense\Downloads\Loan-12-Su-Quan.jar!\bi.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */