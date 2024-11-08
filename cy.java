import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class cy extends bw {
  public void a() {
    this.f = this.c / 14 + 1;
    this.h = new int[this.f];
  }
  
  public void a(Graphics paramGraphics, Image paramImage) {
    int i = this.c;
    for (byte b = 0; b < this.f && this.h[b] > 0; b++) {
      int j = this.h[b];
      int k = i - j;
      if (b == this.f - 1)
        if (k - j > 0) {
          if (k < 0) {
            j = k + j;
            k = 0;
          } 
        } else {
          continue;
        }  
      y.a(paramGraphics, paramImage, k, 0, j, this.d, this.a + k, this.b, 20);
      i -= 14;
      continue;
    } 
  }
}


/* Location:              C:\Users\bot-nosense\Downloads\Loan-12-Su-Quan.jar!\cy.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */