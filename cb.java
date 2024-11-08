import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class cb extends ds {
  private Image a;
  
  private int b;
  
  private int c;
  
  public cb(Image paramImage, int paramInt1, int paramInt2) {
    this.p = paramInt1;
    this.q = paramInt2;
    this.r = paramImage.getWidth();
    this.s = paramImage.getHeight();
    this.a = paramImage;
  }
  
  public void a(Graphics paramGraphics, int paramInt1, int paramInt2) {
    paramGraphics.drawImage(this.a, this.p, this.q + this.b, this.c);
  }
  
  public void a() {}
}


/* Location:              C:\Users\bot-nosense\Downloads\Loan-12-Su-Quan.jar!\cb.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */