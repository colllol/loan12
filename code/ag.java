import javax.microedition.lcdui.Graphics;

public class ag extends by {
  private boolean j;
  
  private boolean k;
  
  public void a(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6) {
    if (paramInt6 < 18)
      paramInt6 = 18; 
    if (paramInt5 < 18)
      paramInt5 = 18; 
    this.a = paramInt1 + paramInt5 / 2;
    this.b = paramInt2;
    this.c = paramInt3 + paramInt5 / 2;
    this.d = paramInt4 + paramInt6 / 2;
    this.g = paramInt5;
    this.h = paramInt6;
    this.f = 18;
    this.e = 18;
    this.a = this.c;
    this.b = this.d;
    if (this.b > paramInt4) {
      this.k = true;
    } else {
      this.k = false;
    } 
    this.j = false;
    this.i = false;
  }
  
  public void a() {
    if (this.i)
      return; 
    if (this.j) {
      this.b = this.d;
      boolean bool = true;
      if (this.f != this.h) {
        int i = this.h - this.f;
        if (Math.abs(i) < 5) {
          this.f += i;
        } else {
          this.f += i / 2;
          bool = false;
        } 
      } 
      if (bool) {
        int i = this.g - this.e;
        if (Math.abs(i) < 5) {
          this.e += i;
          this.i = true;
        } else {
          this.e += i / 2;
        } 
      } 
    } else {
      int i = this.k ? (this.d - 2) : (this.d + 2);
      int j = i - this.b;
      if (Math.abs(j) < 2) {
        this.b += j;
        this.j = true;
      } else {
        this.b += j / 2;
      } 
    } 
  }
  
  public void a(Graphics paramGraphics) {
    cz.a(paramGraphics, cx.P, cx.Q, c(), d(), e(), f());
  }
}


/* Location:              C:\Users\bot-nosense\Downloads\Loan-12-Su-Quan.jar!\ag.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */