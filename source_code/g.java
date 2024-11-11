import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class g extends ds {
  private Image a = (bs.a()).j[0];
  
  private int b;
  
  private int c;
  
  private int d;
  
  public void a(int paramInt1, int paramInt2, int paramInt3) {
    d(paramInt1, paramInt2);
    this.b = -1;
    this.c = paramInt3;
  }
  
  public void a() {
    this.b = 0;
  }
  
  public void a(Graphics paramGraphics, int paramInt1, int paramInt2) {
    if (this.b != 0) {
      byte b = 8;
      if (this.d >= 3)
        b++; 
      if ((this.c & 0x1) == 1)
        paramGraphics.drawRegion(this.a, 0, 0, this.a.getWidth(), this.a.getHeight(), 6, this.p - b, this.q, 10); 
      if ((this.c & 0x2) == 2)
        paramGraphics.drawRegion(this.a, 0, 0, this.a.getWidth(), this.a.getHeight(), 5, this.p + b, this.q, 6); 
      if ((this.c & 0x4) == 4)
        paramGraphics.drawRegion(this.a, 0, 0, this.a.getWidth(), this.a.getHeight(), 0, this.p, this.q - b, 33); 
      if ((this.c & 0x8) == 8)
        paramGraphics.drawRegion(this.a, 0, 0, this.a.getWidth(), this.a.getHeight(), 3, this.p, this.q + b, 17); 
    } 
  }
  
  public void b() {
    if (this.b != 0) {
      if (this.b > 0)
        this.b--; 
      this.d++;
      if (this.d > 6)
        this.d = 0; 
    } 
  }
}


/* Location:              C:\Users\bot-nosense\Downloads\Loan-12-Su-Quan.jar!\g.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */