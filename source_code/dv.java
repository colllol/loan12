import javax.microedition.lcdui.Graphics;

public class dv extends cd {
  protected int[] e;
  
  protected boolean f;
  
  protected boolean g = true;
  
  protected dl h;
  
  protected dl i = new dl();
  
  protected int j;
  
  protected int k;
  
  private boolean n;
  
  private boolean o;
  
  protected int[] l = new int[] { 0, -1, 1, -2, 2, -3, 3 };
  
  protected int m;
  
  public boolean c() {
    return this.n;
  }
  
  public dv() {
    this(false);
  }
  
  public dv(boolean paramBoolean) {
    this(paramBoolean, null);
  }
  
  public dv(boolean paramBoolean, dl paramdl) {
    this.f = paramBoolean;
    if (paramBoolean) {
      if (paramdl == null)
        paramdl = new dl(0, 0, cx.f, cx.g); 
      this.h = paramdl;
    } 
  }
  
  public void a(ds[] paramArrayOfds, int paramInt) {
    super.a(paramArrayOfds, paramInt);
    if (this.f)
      d(); 
  }
  
  public void d() {
    this.n = true;
    this.o = true;
    this.e = new int[this.a.length];
    int i;
    for (i = 0; i < this.e.length; i++) {
      this.e[i] = this.a[i].i();
      System.out.println("xStop=" + this.e[i]);
    } 
    i = 0;
    int j = Integer.MAX_VALUE;
    int k;
    for (k = 0; k < this.a.length; k++) {
      if (this.a[k].k() > i)
        i = this.a[k].k(); 
      if (this.a[k].i() < j)
        j = this.a[k].i(); 
    } 
    k = this.h.a + this.h.c - j;
    if (j + i - k > this.h.a)
      k += j + i - k - this.h.a; 
    this.j = j - k;
    this.k = j + k;
    boolean bool = true;
    for (byte b = 0; b < this.a.length; b++) {
      if (bool) {
        this.a[b].f(this.j);
      } else {
        this.a[b].f(this.k);
      } 
      bool = bool ? false : true;
    } 
  }
  
  protected void b(int paramInt1, int paramInt2) {
    if (this.g)
      this.m = 0; 
    super.b(paramInt1, paramInt2);
  }
  
  public void a(int paramInt) {
    if (this.n)
      return; 
    super.a(paramInt);
  }
  
  public void a(int paramInt1, int paramInt2) {
    if (this.n)
      return; 
    super.a(paramInt1, paramInt2);
  }
  
  public void b() {
    if (this.n) {
      byte b1 = 0;
      for (byte b2 = 0; b2 < this.a.length; b2++) {
        int i = this.a[b2].i();
        int j = this.e[b2] - i;
        if (Math.abs(j) < 2) {
          i += j;
          b1++;
        } else {
          i += j / 2;
        } 
        this.a[b2].f(i);
      } 
      if (b1 >= this.a.length) {
        this.n = false;
        if (!this.o)
          b(this.b); 
      } 
    } 
    if (this.m < this.l.length - 1)
      this.m++; 
  }
  
  protected void b(Graphics paramGraphics) {
    y.a(paramGraphics, this.i);
    y.a(paramGraphics, this.i, this.h.a, this.h.b, this.h.c, this.h.d);
    if (this.n) {
      super.b(paramGraphics);
    } else {
      for (byte b = 0; b < this.a.length; b++) {
        if (this.b != b) {
          this.a[b].a(paramGraphics);
        } else {
          this.a[b].a(paramGraphics, this.l[this.m], 0);
        } 
      } 
    } 
    y.b(paramGraphics, this.i);
  }
}


/* Location:              C:\Users\bot-nosense\Downloads\Loan-12-Su-Quan.jar!\dv.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */