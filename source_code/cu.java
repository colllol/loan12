import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class cu extends ab {
  private int[] v;
  
  private int[] w;
  
  private int x;
  
  private Image[] y;
  
  private int z;
  
  private int A;
  
  public cu(Image paramImage, int paramInt, Image[] paramArrayOfImage) {
    a(paramImage, paramInt);
    this.y = paramArrayOfImage;
    this.t = 3;
  }
  
  public void a(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5) {
    this.x = 1;
    this.u = true;
    this.z = paramInt5;
    this.A = 5;
  }
  
  public void a(int[] paramArrayOfint1, int[] paramArrayOfint2) {
    this.v = paramArrayOfint1;
    this.w = paramArrayOfint2;
  }
  
  public void a() {
    if (this.A <= 0) {
      if (this.x < this.v.length)
        this.x++; 
      this.A = 5;
    } else {
      this.A--;
    } 
    if (this.z > 0) {
      this.z--;
      if (this.z == 0)
        this.u = false; 
    } 
  }
  
  public void a(Graphics paramGraphics, int paramInt1, int paramInt2) {
    if (!this.u)
      return; 
    int i;
    for (i = 0; i < this.x; i++)
      super.a(paramGraphics, this.v[i], this.w[i]); 
    i = this.x - 1;
    for (byte b = 0; b < i; b++)
      a(paramGraphics, this.v[b], this.w[b], this.v[b + 1], this.w[b + 1]); 
  }
  
  private void a(Graphics paramGraphics, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    int i = paramInt1;
    int j = paramInt2;
    int k = 0;
    int m = 0;
    boolean bool1 = true;
    boolean bool2 = true;
    do {
      if (bool1) {
        bool1 = (i > paramInt3) ? true : false;
        if (bool1) {
          k = i - 21;
        } else {
          k = i + 21;
        } 
        bool1 = (bool1 == ((k > paramInt3) ? true : false)) ? true : false;
        if (!bool1)
          k = paramInt3; 
      } 
      if (bool2) {
        bool2 = (j > paramInt4) ? true : false;
        if (bool2) {
          m = j - 21;
        } else {
          m = j + 21;
        } 
        bool2 = (bool2 == ((m > paramInt4) ? true : false)) ? true : false;
        if (!bool2)
          m = paramInt4; 
      } 
      if (i < k) {
        if (j < m) {
          paramGraphics.drawImage(this.y[0], i, j, 20);
        } else if (j > m) {
          paramGraphics.drawRegion(this.y[0], 0, 0, this.y[0].getWidth(), this.y[0].getHeight(), 2, i, m, 20);
        } else {
          paramGraphics.drawImage(this.y[1], i, j, 6);
        } 
      } else if (i > k) {
        if (j < m) {
          paramGraphics.drawRegion(this.y[0], 0, 0, this.y[0].getWidth(), this.y[0].getHeight(), 2, k, j, 20);
        } else if (j > m) {
          paramGraphics.drawImage(this.y[0], k, m, 20);
        } else {
          paramGraphics.drawImage(this.y[1], k, m, 6);
        } 
      } else if (j < m) {
        paramGraphics.drawRegion(this.y[1], 0, 0, this.y[1].getWidth(), this.y[1].getHeight(), 5, i, j, 17);
      } else if (j > m) {
        paramGraphics.drawRegion(this.y[1], 0, 0, this.y[1].getWidth(), this.y[1].getHeight(), 5, i, m, 17);
      } 
      i = k;
      j = m;
    } while (bool1 || bool2);
  }
}


/* Location:              C:\Users\bot-nosense\Downloads\Loan-12-Su-Quan.jar!\cu.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */