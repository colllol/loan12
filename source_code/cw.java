public abstract class cw extends ds {
  protected int b = 0;
  
  protected int c;
  
  protected byte[][] d;
  
  protected int e;
  
  protected int f;
  
  protected int g;
  
  protected boolean h = true;
  
  protected int i;
  
  protected int j;
  
  protected int k;
  
  protected int l;
  
  public void c(int paramInt1, int paramInt2) {
    this.i = paramInt1;
    this.j = paramInt2;
  }
  
  public byte[][] d() {
    return this.d;
  }
  
  public void a(byte[][] paramArrayOfbyte) {
    this.d = paramArrayOfbyte;
  }
  
  public void b(int paramInt) {
    this.g = paramInt;
  }
  
  public void c(int paramInt) {
    this.e = paramInt;
    this.f = 0;
  }
  
  public void b(boolean paramBoolean) {
    this.h = paramBoolean;
  }
  
  public void a_(int paramInt) {
    this.c = paramInt;
    this.g %= (this.d[paramInt]).length;
  }
  
  public final void e() {
    if (!this.u)
      return; 
    a();
    if (this.f >= this.e) {
      if (this.d != null && this.d[this.c] != null) {
        if (this.h) {
          this.g = (this.g + 1) % (this.d[this.c]).length;
        } else {
          this.g++;
          if (this.g >= (this.d[this.c]).length)
            this.g = (this.d[this.c]).length - 1; 
        } 
      } else {
        this.g = -1;
      } 
      this.f = 0;
    } else {
      this.f++;
    } 
  }
  
  public abstract void a();
}


/* Location:              C:\Users\bot-nosense\Downloads\Loan-12-Su-Quan.jar!\cw.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */