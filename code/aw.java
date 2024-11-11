import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class aw extends dz {
  private Image a;
  
  private int b;
  
  private int c;
  
  private int d;
  
  public aw(Image paramImage, int paramInt1, int paramInt2, int paramInt3) {
    this.a = paramImage;
    this.b = paramInt1;
    this.c = paramInt2;
    this.d = paramInt3;
  }
  
  public void a(Graphics paramGraphics) {
    if (this.a != null)
      paramGraphics.drawImage(this.a, this.b, this.c, this.d); 
  }
  
  public void a() {}
}


/* Location:              C:\Users\bot-nosense\Downloads\Loan-12-Su-Quan.jar!\aw.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */