import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class dn extends ds {
  Image a;
  
  int b;
  
  private int c;
  
  private int d;
  
  public dn(Image paramImage) {
    this.u = false;
    this.a = paramImage;
    this.r = paramImage.getWidth() / 6;
    this.s = paramImage.getHeight();
  }
  
  public void a(int paramInt1, int paramInt2, int paramInt3) {
    this.p = paramInt2;
    this.q = paramInt3;
    this.u = true;
    this.b = 0;
    this.c = paramInt1;
    this.d = 5;
  }
  
  public void a(Graphics paramGraphics, int paramInt1, int paramInt2) {
    if (!this.u)
      return; 
    if (this.d > 0)
      return; 
    if (this.b > 15) {
      if (this.b % 2 == 0)
        y.a(paramGraphics, this.a, this.c * this.r, 0, this.r, this.s, this.p, this.q - 7, 3); 
    } else {
      y.a(paramGraphics, this.a, this.c * this.r, 0, this.r, this.s, this.p, this.q - (this.b >> 1), 3);
    } 
  }
  
  public void a() {
    if (!this.u)
      return; 
    if (this.d > 0) {
      this.d--;
      return;
    } 
    this.b++;
    if (this.b > 24)
      this.u = false; 
  }
}


/* Location:              C:\Users\bot-nosense\Downloads\Loan-12-Su-Quan.jar!\dn.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */