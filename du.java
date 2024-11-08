import javax.microedition.lcdui.Graphics;

public class du extends ae {
  private dp e;
  
  public du(String paramString1, String paramString2, String[] paramArrayOfString, int[] paramArrayOfint, int paramInt) {
    super(paramInt);
    this.e = new dp(paramString1, paramString2, 1);
    if (paramArrayOfString != null)
      if (paramArrayOfString.length == 1) {
        c(new da(paramArrayOfString[0], paramArrayOfint[0]));
      } else if (paramArrayOfString.length == 2) {
        a(new da(paramArrayOfString[0], paramArrayOfint[0]));
        b(new da(paramArrayOfString[1], paramArrayOfint[1]));
      } else {
        a(new da(paramArrayOfString[0], paramArrayOfint[0]));
        c(new da(paramArrayOfString[1], paramArrayOfint[1]));
        b(new da(paramArrayOfString[1], paramArrayOfint[2]));
      }  
    this.g = this.e.g();
    this.a_ = this.e.h();
    this.b_ = this.e.i();
    this.c_ = this.e.j();
    a(l());
  }
  
  public void a(h paramh) {
    if (paramh != null)
      paramh.a(cx.P); 
    super.a(paramh);
  }
  
  public void a(int paramInt) {
    this.e.a(paramInt);
  }
  
  public void a(int paramInt1, int paramInt2) {
    this.e.a(paramInt1, paramInt2);
  }
  
  protected void e() {
    this.e.a();
  }
  
  public void a(Graphics paramGraphics) {
    this.e.a(paramGraphics);
  }
}


/* Location:              C:\Users\bot-nosense\Downloads\Loan-12-Su-Quan.jar!\du.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */