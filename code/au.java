import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class au extends ab {
  private int v;
  
  private static int[] w = new int[] { 0, 17, 51, 68, 131 };
  
  private static int[] x = new int[] { 17, 34, 17, 63, 62 };
  
  private static int[] y = new int[] { 19, 20, 17, 12 };
  
  private static int[] z = new int[] { 12, 11, 4, 27, 56 };
  
  private static int[] A = new int[] { 91, 74, 56, 1 };
  
  public au(Image paramImage, int paramInt) {
    this.n = paramImage;
  }
  
  public void a(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5) {
    d(paramInt1, paramInt2);
    c(paramInt3, paramInt4);
    this.k = b(paramInt1, paramInt3, 35);
    this.l = b(paramInt2, paramInt4, 35);
    this.v = paramInt5;
    this.u = true;
    this.t = 33;
    this.g = 0;
  }
  
  public void a() {
    if (this.v > 0) {
      this.v--;
      return;
    } 
    if (this.g >= (this.d[0]).length - 1)
      this.u = false; 
  }
  
  public void a(Graphics paramGraphics, int paramInt1, int paramInt2) {
    if (this.v < 1) {
      if (!this.u)
        return; 
      byte b = this.d[this.c][this.g];
      if (this.n != null && this.g > -1 && b > -1)
        y.a(paramGraphics, this.n, y[b], w[b], z[b], x[b], this.p + paramInt1, this.q - A[b] + paramInt2, this.t); 
    } 
  }
}


/* Location:              C:\Users\bot-nosense\Downloads\Loan-12-Su-Quan.jar!\au.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */