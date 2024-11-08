import javax.microedition.lcdui.Graphics;

public class db extends aq {
  private boolean g;
  
  public void a() {
    super.a();
    if (this.e % 3 == 0)
      this.g = !this.g; 
  }
  
  public void a(Graphics paramGraphics, int paramInt1, int paramInt2) {
    if (!this.c)
      return; 
    if (this.g)
      bz.c.a(paramGraphics, this.d, paramInt1 + this.a, paramInt2 + this.b, 1); 
  }
}


/* Location:              C:\Users\bot-nosense\Downloads\Loan-12-Su-Quan.jar!\db.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */