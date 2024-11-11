import javax.microedition.lcdui.Graphics;

public class z extends by {
  private boolean j;
  
  public void a(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6) {
    if (paramInt6 < 18)
      paramInt6 = 18; 
    if (paramInt5 < 18)
      paramInt5 = 18; 
    this.a = paramInt1 + paramInt5 / 2;
    this.b = paramInt2 + paramInt6 / 2;
    this.c = paramInt3 + paramInt5 / 2;
    this.d = paramInt4;
    this.g = 18;
    this.h = 18;
    this.e = paramInt5;
    this.f = paramInt6;
    this.c = this.a;
    this.d = this.b;
    this.j = true;
    this.i = false;
  }
  
  public void a() {
    if (this.i)
      return; 
    if (this.j) {
      boolean bool = true;
      if (this.e != this.g) {
        int i = this.g - this.e;
        if (Math.abs(i) < 5) {
          this.e += i;
        } else {
          this.e += i / 2;
          bool = false;
        } 
      } 
      if (bool) {
        int i = this.h - this.f;
        if (Math.abs(i) < 5) {
          this.f += i;
          this.i = true;
        } else {
          this.f += i / 2;
        } 
      } 
    } else {
      int i = this.d;
      int j = i - this.b;
      if (Math.abs(j) < 2) {
        this.b += j;
        this.i = true;
      } else {
        this.b += j / 2;
      } 
    } 
  }
  
  public void a(Graphics paramGraphics) {
    cz.a(paramGraphics, cx.P, cx.Q, c(), d(), e(), f());
  }
}


/* Location:              C:\Users\bot-nosense\Downloads\Loan-12-Su-Quan.jar!\z.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */