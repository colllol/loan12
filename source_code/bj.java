import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class bj extends ds {
  protected Image[] a;
  
  protected int b;
  
  public bj(Image[] paramArrayOfImage) {
    this.a = paramArrayOfImage;
    if (paramArrayOfImage != null) {
      this.r = paramArrayOfImage[0].getWidth();
      this.s = paramArrayOfImage[0].getHeight();
    } 
  }
  
  public void a(Graphics paramGraphics, int paramInt1, int paramInt2) {
    paramGraphics.drawImage(this.a[this.b], paramInt1 + this.p, paramInt2 + this.q, this.t);
  }
  
  public void a(boolean paramBoolean) {
    super.a(paramBoolean);
    this.b = (this.b + 1) % this.a.length;
  }
}


/* Location:              C:\Users\bot-nosense\Downloads\Loan-12-Su-Quan.jar!\bj.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */