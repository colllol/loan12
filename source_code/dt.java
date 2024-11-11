import javax.microedition.lcdui.Graphics;

public class dt extends e {
  private String e = null;
  
  private String[] f;
  
  private boolean g;
  
  private int h;
  
  private br i;
  
  public int d = 41377;
  
  private br j;
  
  public void a(br parambr) {
    this.i = parambr;
  }
  
  private void c() {
    this.i = bz.d;
    this.j = bz.c;
  }
  
  public dt(String paramString, int paramInt) {
    this.e = paramString;
    this.h = paramInt;
    c();
  }
  
  public dt(String[] paramArrayOfString, boolean paramBoolean, int paramInt) {
    this.f = paramArrayOfString;
    this.g = paramBoolean;
    this.h = paramInt;
    c();
  }
  
  public void a(dl paramdl) {
    super.a(paramdl);
    if (this.e != null)
      this.f = bz.a(this.e, paramdl.c); 
    if (this.f != null) {
      this.b = 0;
      if (this.f != null)
        this.b = this.f.length * 15; 
      this.e = null;
    } 
  }
  
  public void a(Graphics paramGraphics, int paramInt1, int paramInt2) {
    if (this.f != null) {
      byte b1 = 0;
      int i = this.c.b + this.c.d;
      if (this.g) {
        b1 = 1;
        int j = this.j.a(this.f[0]);
        if (this.d >= 0)
          paramGraphics.setColor(this.d); 
        switch (this.h) {
          case 2:
            paramInt1 += this.c.c;
            if (this.d >= 0)
              paramGraphics.fillRect(paramInt1 - j, paramInt2 - 2, j, 16); 
            break;
          case 1:
            paramInt1 += this.c.c / 2;
            if (this.d >= 0)
              paramGraphics.fillRect(paramInt1 - j / 2, paramInt2 - 2, j, 16); 
            break;
          default:
            if (this.d >= 0)
              paramGraphics.fillRect(paramInt1, paramInt2 - 2, j, 16); 
            break;
        } 
        this.j.a(paramGraphics, this.f[0], paramInt1, paramInt2, this.h);
        paramInt2 += 15;
      } 
      for (byte b2 = b1; b2 < this.f.length && paramInt2 <= i; b2++) {
        this.i.a(paramGraphics, this.f[b2], paramInt1, paramInt2, this.h);
        paramInt2 += 15;
      } 
    } 
  }
  
  public void b() {}
}


/* Location:              C:\Users\bot-nosense\Downloads\Loan-12-Su-Quan.jar!\dt.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */