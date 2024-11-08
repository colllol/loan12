import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class af extends bw {
  public void a() {
    this.f = this.d / 14 + 1;
    this.h = new int[this.f];
  }
  
  public void a(Graphics paramGraphics, Image paramImage) {
    byte b1 = 0;
    for (byte b2 = 0; b2 < this.f && this.h[b2] > 0; b2++) {
      int i = this.h[b2];
      byte b = b1;
      if (b2 == this.f - 1)
        if (b < this.d) {
          if (b + i > this.d)
            i = this.d - b; 
        } else {
          continue;
        }  
      y.a(paramGraphics, paramImage, 0, b, this.c, i, this.a, this.b + b, 20);
      b1 += 14;
      continue;
    } 
  }
}


/* Location:              C:\Users\bot-nosense\Downloads\Loan-12-Su-Quan.jar!\af.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */