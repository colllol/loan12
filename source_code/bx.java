import com.mic.smsgame.suquan.n;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class bx extends ds {
  bn[] a;
  
  Image b;
  
  Image c;
  
  Image d;
  
  Image e;
  
  private int g;
  
  private int[] h = new int[] { 81208, 85576, 23637 };
  
  private int[] i = new int[] { 5046585, 6095173, 7209297 };
  
  ax[] f;
  
  private int j;
  
  private int k;
  
  private int l;
  
  private Image m;
  
  private int n = 0;
  
  private boolean o;
  
  private int v;
  
  private n[] w;
  
  private n[] x;
  
  private int y;
  
  private int z;
  
  private int[] A;
  
  private dq[] B;
  
  public bx(bn[] paramArrayOfbn) {
    this.a = paramArrayOfbn;
    d();
    this.f = new ax[2];
    this.f[0] = new ax();
    this.f[0].d(cx.l + 35, cx.m + 237);
    this.f[1] = new ax();
    this.f[1].d(cx.l + 175, cx.m + 237);
    byte[][] arrayOfByte = { { 0, 0, 0, 0, 1, 1, 1, 2, 2, 1 } };
    Image image = (bs.a()).c[2];
    this.w = new n[25];
    byte b;
    for (b = 0; b < this.w.length; b++) {
      this.w[b] = new n();
      this.w[b].a(arrayOfByte);
      this.w[b].a(image, 3);
      this.w[b].a(false);
    } 
    image = (bs.a()).c[3];
    this.x = new n[10];
    for (b = 0; b < this.x.length; b++) {
      this.x[b] = new n();
      this.x[b].a(arrayOfByte);
      this.x[b].a(image, 3);
      this.x[b].a(false);
    } 
    this.A = new int[2];
    this.B = new dq[2];
    this.B[0] = new dq((bs.a()).C, 3);
    this.B[0].d(this.f[0].i() + this.f[0].k() / 2, this.f[0].j() + this.f[0].l() / 2);
    this.B[0].k(3);
    this.B[1] = new dq((bs.a()).D, 3);
    this.B[1].d(this.f[0].i() + this.f[0].k() / 2, this.f[0].j() + this.f[0].l());
    this.B[1].k(33);
    arrayOfByte = new byte[][] { { 0, 0, 1, 1, 2, 2 } };
    for (b = 0; b < this.B.length; b++) {
      this.B[b].a(false);
      this.B[b].a(arrayOfByte);
    } 
  }
  
  private void d() {
    this.b = (bs.a()).l;
    this.d = (bs.a()).n;
    this.e = (bs.a()).o;
    this.c = (bs.a()).m;
    this.m = (bs.a()).p;
  }
  
  public ax[] a() {
    return this.f;
  }
  
  public void b(boolean paramBoolean) {
    this.B[1].a(paramBoolean);
  }
  
  public void c(boolean paramBoolean) {
    this.B[0].a(paramBoolean);
  }
  
  public void a(Graphics paramGraphics, int paramInt1, int paramInt2) {
    bn bn1 = this.a[0];
    int i = this.f[0].i() + this.f[0].k() + 6;
    int j = this.f[0].j();
    int k = bn1.l();
    if (k > bn1.j() || this.A[0] % 2 == 0)
      if (k > bn1.e()) {
        y.a(paramGraphics, this.b, 0, 0, this.b.getWidth(), this.b.getHeight(), i, j, 0);
        int i2 = k - bn1.e();
        int i3 = this.b.getWidth() * i2 / bn1.e();
        y.a(paramGraphics, this.c, 0, 0, i3, this.c.getHeight(), i, j, 0);
      } else {
        int i2 = this.b.getWidth() * k / bn1.e();
        if (bn1.l() > 0 && i2 < 1)
          i2 = 1; 
        y.a(paramGraphics, this.b, 0, 0, i2, this.b.getHeight(), i, j, 0);
      }  
    j += 11;
    y.a(paramGraphics, this.e, 0, 0, this.e.getWidth() * bn1.p() / bn1.q(), this.e.getHeight(), i, j, 0);
    j += 11;
    if (this.z % 2 == 0)
      y.a(paramGraphics, this.d, 0, 0, this.d.getWidth() * bn1.o() / bn1.n(), this.d.getHeight(), i, j, 0); 
    bn1 = this.a[1];
    i = this.f[1].i() - 5;
    j = this.f[0].j();
    int m = 0;
    k = bn1.l();
    if (k > bn1.j() || this.A[1] % 2 == 0)
      if (k > bn1.e()) {
        paramGraphics.drawRegion(this.b, 0, 0, this.b.getWidth(), this.b.getHeight(), 2, i - this.b.getWidth(), j, 0);
        int i2 = k - bn1.e();
        m = this.b.getWidth() * i2 / bn1.e();
        paramGraphics.drawRegion(this.c, 0, 0, m, this.c.getHeight(), 2, i - m, j, 0);
      } else {
        m = this.b.getWidth() * k / bn1.e();
        if (bn1.l() > 0 && m < 1)
          m = 1; 
        paramGraphics.drawRegion(this.b, 0, 0, m, this.b.getHeight(), 2, i - m, j, 0);
      }  
    j += 11;
    m = this.e.getWidth() * bn1.p() / bn1.q();
    paramGraphics.drawRegion(this.e, 0, 0, m, this.e.getHeight(), 2, i - m, j, 0);
    j += 11;
    m = this.d.getWidth() * bn1.o() / bn1.n();
    paramGraphics.drawRegion(this.d, 0, 0, m, this.d.getHeight(), 2, i - m, j, 0);
    if (this.k > 0 && this.k <= 10) {
      ax ax1 = this.f[this.j];
      if (this.k > 6) {
        if (this.k % 2 == 0) {
          paramGraphics.setColor(16777215);
          paramGraphics.fillRect(ax1.i(), ax1.j(), ax1.k(), ax1.l());
        } 
      } else {
        int i2 = this.m.getWidth() / 5;
        int i3 = this.m.getHeight();
        int i4 = (6 - this.k) % 5;
        byte b = 2;
        if (this.j == 0)
          b = 0; 
        paramGraphics.drawRegion(this.m, i4 * i2, 0, i2, i3, b, ax1.i() + ax1.k() / 2, ax1.j() + ax1.l() / 2 - 7, 3);
      } 
    } 
    i = this.f[0].i() + this.f[0].k() + 5;
    j = this.f[0].j();
    m = 0;
    if (!this.o) {
      i = this.f[this.n].i() - 2;
      j = this.f[this.n].j() - 2;
      int[] arrayOfInt = this.h;
      m = 83;
      if (this.n == 1) {
        i = i + this.f[this.n].k() - m + 5;
        arrayOfInt = this.i;
      } 
      m = 82;
      char c = '"';
      int i2;
      for (i2 = 0; i2 < arrayOfInt.length; i2++) {
        y.b(paramGraphics, arrayOfInt[i2], i, j, m, c);
        i--;
        j--;
        m += 2;
        c += 2;
      } 
      i2 = 0;
      if (this.g < 10) {
        if (this.g % 2 == 0)
          i2 = 3; 
      } else if (this.g < 18) {
        i2 = (this.g - 3) % 4;
      } else {
        i2 = 3;
      } 
      i = this.f[0].i() - 2;
      j = this.f[0].j() - 186;
      m = 172;
      c = '¬';
      for (byte b = 0; b < i2; b++) {
        paramGraphics.setColor(arrayOfInt[b]);
        paramGraphics.drawRect(i, j, m, c);
        i--;
        j--;
        m += 2;
        c += '\002';
      } 
    } 
    int i1;
    for (i1 = 0; i1 < this.B.length; i1++)
      this.B[i1].a(paramGraphics); 
    for (i1 = this.x.length - 1; i1 > -1; i1--)
      this.x[i1].a(paramGraphics); 
    for (i1 = this.w.length - 1; i1 > -1; i1--)
      this.w[i1].a(paramGraphics); 
  }
  
  public void b() {
    if (!this.o) {
      this.v++;
      if (this.v == 10)
        this.v = 0; 
      if (this.g < 20)
        this.g++; 
    } 
    if (this.k > 0) {
      this.k--;
      if (this.k == 7) {
        bs.a().f();
        if (this.j == 0)
          cz.d(250); 
      } else if (this.k == 0) {
        this.k = this.l;
        this.l = 0;
      } 
    } 
    int i;
    for (i = 0; i < this.a.length; i++) {
      if (this.j == i && this.y > 0) {
        this.y--;
      } else {
        int j = this.a[i].l();
        int k = this.a[i].r() - j;
        if (k != 0) {
          if (Math.abs(k) >= 6)
            k /= 6; 
          this.a[i].d(j + k);
        } 
      } 
    } 
    for (i = 0; i < this.A.length; i++) {
      if (this.a[i].l() <= this.a[i].j()) {
        this.A[i] = this.A[i] + 1;
        if (this.A[i] > 1)
          this.A[i] = 0; 
      } 
    } 
    for (i = this.w.length - 1; i > -1; i--)
      this.w[i].e(); 
    for (i = this.x.length - 1; i > -1; i--)
      this.x[i].e(); 
    if (this.z > 0)
      this.z--; 
    for (i = 0; i < this.B.length; i++)
      this.B[i].e(); 
  }
  
  public void a(int paramInt) {
    if (this.y == 0) {
      this.y = paramInt;
    } else if (this.y < paramInt) {
      this.y = this.y + paramInt >> 1;
    } 
  }
  
  public void a(int paramInt1, int paramInt2) {
    bn bn1 = this.a[this.j];
    int i = this.f[paramInt1].j() + 10;
    if (paramInt1 == 0) {
      int j = this.f[paramInt1].i() - 3 + this.f[paramInt1].k() + this.d.getWidth() * bn1.o() / bn1.n();
      for (byte b = 0; b < this.x.length; b++) {
        if (!this.x[b].m()) {
          this.x[b].a(j, i, j - 5 + ay.a(80), i - 80 + ay.a(100), paramInt2);
          this.x[b].b(ay.a(10));
        } 
      } 
    } else {
      int j = this.f[paramInt1].i() - 5 - this.d.getWidth() * bn1.o() / bn1.n();
      for (byte b = 0; b < this.x.length; b++) {
        if (!this.x[b].m()) {
          this.x[b].a(j, i, j + 5 - ay.a(80), i - 80 + ay.a(100), paramInt2);
          this.x[b].b(ay.a(10));
        } 
      } 
    } 
  }
  
  public void b(int paramInt1, int paramInt2) {
    a(this.y);
    bn bn1 = this.a[paramInt1];
    if (bn1.k())
      return; 
    if (this.k < 1)
      this.k = paramInt2; 
    if (this.k < paramInt2) {
      this.l = paramInt2 - this.k;
      if (this.l < 6)
        this.l = 0; 
    } else {
      this.l = this.k - paramInt2;
      this.k = paramInt2;
    } 
    paramInt2 -= 5;
    if (paramInt2 < 0)
      paramInt2 = 0; 
    this.j = paramInt1;
    int i = this.f[paramInt1].j();
    if (paramInt1 == 0) {
      int j = this.f[paramInt1].i() - 3 + this.f[paramInt1].k() + this.b.getWidth() * bn1.r() / bn1.s();
      byte b1 = 15;
      for (byte b2 = 0; b2 < this.w.length; b2++) {
        if (!this.w[b2].m()) {
          this.w[b2].a(j, i, j - 5 + ay.a(80), i - 80 + ay.a(100), paramInt2);
          this.w[b2].b(ay.a(10));
          if (--b1 <= 0)
            break; 
        } 
      } 
    } else {
      int j = this.f[paramInt1].i() - 5 - this.b.getWidth() * bn1.r() / bn1.s();
      byte b1 = 15;
      for (byte b2 = 0; b2 < this.w.length; b2++) {
        if (!this.w[b2].m()) {
          this.w[b2].a(j, i, j + 5 - ay.a(80), i - 80 + ay.a(100), paramInt2);
          this.w[b2].b(ay.a(10));
          if (--b1 <= 0)
            break; 
        } 
      } 
    } 
  }
  
  public void b(int paramInt) {
    if (this.n != paramInt)
      this.g = 0; 
    this.n = paramInt;
  }
  
  public void d(boolean paramBoolean) {
    this.o = paramBoolean;
  }
  
  public void c() {
    this.z = 40;
  }
}


/* Location:              C:\Users\bot-nosense\Downloads\Loan-12-Su-Quan.jar!\bx.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */