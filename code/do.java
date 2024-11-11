import javax.microedition.lcdui.Image;

public class do extends ab {
  private int v;
  
  private int w;
  
  public do(Image paramImage, int paramInt) {
    a(paramImage, paramInt);
    this.t = 3;
  }
  
  public void a(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5) {
    d(paramInt1, paramInt2);
    this.u = true;
    this.w = 0;
  }
  
  public void a_(int paramInt) {
    super.a_(paramInt);
    if (paramInt == 0) {
      this.h = true;
    } else {
      this.h = false;
      this.g = 0;
    } 
  }
  
  public void d(int paramInt) {
    this.v = paramInt;
  }
  
  public void a() {
    if (this.v > 0) {
      this.v--;
      if (this.v == 0)
        this.u = false; 
    } 
    if (this.w > 0) {
      this.w--;
      if (this.w == 0)
        a_(1); 
    } 
    if (1 == this.c && this.g >= (this.d[1]).length - 1)
      a_(0); 
  }
  
  public void e(int paramInt) {
    this.w = paramInt;
  }
}


/* Location:              C:\Users\bot-nosense\Downloads\Loan-12-Su-Quan.jar!\do.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */