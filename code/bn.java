public class bn {
  private int b;
  
  private int c;
  
  private int d;
  
  private boolean e = false;
  
  private int f = 7500;
  
  private int g = 0;
  
  private int h = 36;
  
  private int i = 0;
  
  public dx[] a;
  
  private int j;
  
  private int k;
  
  private int l;
  
  private c m;
  
  private int n;
  
  public int a(byte[] paramArrayOfbyte, int paramInt) {
    int i = paramInt;
    System.arraycopy(ai.a(this.c), 0, paramArrayOfbyte, paramInt, 4);
    paramInt += 4;
    System.arraycopy(ai.a(this.b), 0, paramArrayOfbyte, paramInt, 4);
    paramInt += 4;
    System.arraycopy(ai.a(this.i), 0, paramArrayOfbyte, paramInt, 4);
    paramInt += 4;
    System.arraycopy(ai.a(this.g), 0, paramArrayOfbyte, paramInt, 4);
    paramInt += 4;
    System.arraycopy(ai.a(this.j), 0, paramArrayOfbyte, paramInt, 4);
    paramInt += 4;
    System.arraycopy(ai.a(this.k), 0, paramArrayOfbyte, paramInt, 4);
    paramInt += 4;
    for (byte b = 0; b < this.a.length; b++) {
      paramInt += this.a[b].a(paramArrayOfbyte, paramInt);
      System.out.println("offset=" + paramInt);
    } 
    return paramInt - i;
  }
  
  public int b(byte[] paramArrayOfbyte, int paramInt) {
    int i = paramInt;
    this.c = this.d = ai.a(paramArrayOfbyte, paramInt);
    paramInt += 4;
    this.b = ai.a(paramArrayOfbyte, paramInt);
    paramInt += 4;
    this.i = ai.a(paramArrayOfbyte, paramInt);
    paramInt += 4;
    this.g = ai.a(paramArrayOfbyte, paramInt);
    paramInt += 4;
    this.j = ai.a(paramArrayOfbyte, paramInt);
    paramInt += 4;
    this.k = ai.a(paramArrayOfbyte, paramInt);
    paramInt += 4;
    for (byte b = 0; b < this.a.length; b++)
      paramInt += this.a[b].b(paramArrayOfbyte, paramInt); 
    return paramInt - i;
  }
  
  public bn(c paramc) {
    this.m = paramc;
    int i = paramc.e;
    if (i < 30) {
      this.n = 70;
    } else if (i < 60) {
      this.n = 50;
    } else {
      this.n = 30;
    } 
    a();
  }
  
  public void a() {
    this.b = this.m.b;
    this.c = this.d = this.m.c;
    this.i = 0;
    this.g = this.f;
    this.e = false;
    this.a = new dx[6];
    for (byte b = 0; b < this.a.length; b++) {
      this.a[b] = new dx();
      (this.a[b]).a = b;
    } 
    this.k = 0;
    this.j = 0;
  }
  
  public int b() {
    return this.m.a;
  }
  
  public int c() {
    return this.n;
  }
  
  public int[] d() {
    return this.m.f;
  }
  
  public int e() {
    return this.m.b;
  }
  
  public int f() {
    return this.j;
  }
  
  public void a(int paramInt) {
    this.j += paramInt;
  }
  
  public int g() {
    return this.k;
  }
  
  public void b(int paramInt) {
    this.k += paramInt;
  }
  
  public void h() {
    this.m.c = this.m.b;
  }
  
  public void i() {
    if (this.c > this.m.b)
      this.c = this.m.b; 
    this.m.c = this.c;
  }
  
  public int j() {
    return this.l;
  }
  
  public void c(int paramInt) {
    this.l = paramInt;
  }
  
  public boolean k() {
    return this.e;
  }
  
  public void a(boolean paramBoolean) {
    this.e = paramBoolean;
  }
  
  public int l() {
    return this.d;
  }
  
  public void d(int paramInt) {
    this.d = paramInt;
  }
  
  public int m() {
    return ((this.a[0]).b > 0) ? (this.m.d + this.m.d) : this.m.d;
  }
  
  public int n() {
    return this.h;
  }
  
  public int o() {
    return this.i;
  }
  
  public void e(int paramInt) {
    if (paramInt < 0) {
      paramInt = 0;
    } else if (paramInt > this.h) {
      paramInt = this.h;
    } 
    this.i = paramInt;
  }
  
  public void f(int paramInt) {
    e(this.i + paramInt);
  }
  
  public void a(int paramInt, boolean paramBoolean) {
    if (this.e && !paramBoolean)
      return; 
    e(this.i - paramInt);
  }
  
  public int p() {
    return this.g;
  }
  
  public void g(int paramInt) {
    if (paramInt < 0) {
      paramInt = 0;
    } else if (paramInt > this.f) {
      paramInt = this.f;
    } 
    this.g = paramInt;
  }
  
  public int q() {
    return this.f;
  }
  
  public void h(int paramInt) {
    this.f = paramInt;
    this.g = this.f;
  }
  
  public void i(int paramInt) {
    g(this.g + paramInt);
  }
  
  public void j(int paramInt) {
    g(this.g - paramInt);
  }
  
  public int r() {
    return this.c;
  }
  
  public void k(int paramInt) {
    if (this.e)
      return; 
    if ((this.a[3]).b > 0)
      paramInt = paramInt * 1 / 4; 
    m(this.c - paramInt);
  }
  
  public void l(int paramInt) {
    m(this.c + paramInt);
  }
  
  public void m(int paramInt) {
    if (paramInt < 0) {
      paramInt = 0;
    } else if (paramInt > this.b) {
      paramInt = this.b;
    } 
    this.c = paramInt;
  }
  
  public int s() {
    return this.b;
  }
  
  public void n(int paramInt) {
    this.b = paramInt;
  }
}


/* Location:              C:\Users\bot-nosense\Downloads\Loan-12-Su-Quan.jar!\bn.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */