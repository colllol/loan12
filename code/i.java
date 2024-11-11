import com.mic.smsgame.suquan.a;
import com.mic.smsgame.suquan.b;
import com.mic.smsgame.suquan.e;
import com.mic.smsgame.suquan.i;
import com.mic.smsgame.suquan.p;
import com.mic.smsgame.suquan.s;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class i implements e {
  cq a;
  
  bm b;
  
  bh c;
  
  private Image k;
  
  private di l;
  
  private Image m;
  
  private Image[] n;
  
  private di o;
  
  private di p;
  
  private g q;
  
  public bx d;
  
  private dl r;
  
  private di s;
  
  private dn t;
  
  private k u;
  
  private String[] v = new String[3];
  
  private int w;
  
  private int x;
  
  private db y;
  
  private ah z;
  
  public ck e;
  
  private cj A;
  
  private boolean B = true;
  
  public dl f;
  
  public w g;
  
  public void a(String paramString, int paramInt) {
    this.v[paramInt] = paramString;
  }
  
  public void a() {
    this.w = 0;
  }
  
  public void b() {
    this.x = 15;
    this.w = 30 + this.x;
    if (cx.n)
      this.w = -1; 
  }
  
  public void c() {
    this.v = new String[3];
  }
  
  public i(cq paramcq) {
    try {
      this.a = paramcq;
      p();
      this.r = new dl();
    } catch (Exception exception) {
      exception.printStackTrace();
    } 
  }
  
  private void p() {
    this.l = new di();
    this.o = new di();
    this.p = new di();
    this.s = new di();
    this.u = new k(cx.f / 2, cx.g / 2 - 15);
    this.y = new db();
    this.y.a(30);
    this.m = (bs.a()).b;
    this.n = (bs.a()).c;
    this.b = new bm(cx.l + 36 + 10, cx.m + 54 + 10);
    this.c = new bh();
    i i1 = this.b.a(0, 0);
    this.c.a(i1.i(), i1.j());
    bn[] arrayOfBn = this.a.d();
    this.d = new bx(arrayOfBn);
    this.d.d(this.b.i() - 18, this.b.j() + 167);
    ax[] arrayOfAx = this.d.a();
    Image[] arrayOfImage = bs.a().a(arrayOfBn[0].b(), arrayOfBn[1].b());
    Graphics graphics = this.m.getGraphics();
    for (byte b = 0; b < arrayOfImage.length; b++)
      graphics.drawImage(arrayOfImage[b], arrayOfAx[b].i(), arrayOfAx[b].j(), 0); 
    arrayOfImage = (Image[])null;
    this.k = (bs.a()).a;
    this.q = new g();
    bs.a().a(arrayOfBn[0].d());
    bs.a().a(arrayOfBn[1].d());
    bs.a().c(arrayOfBn[0].d());
    this.z = new ah(this.a.b(0).d());
    this.e = new ck();
    this.A = new cj(cx.f >> 1, this.b.j() + 163);
    this.A.b(dd.g);
    this.t = new dn((bs.a()).E);
    this.f = new dl(this.b.i() - 10, this.b.j() - 10, this.b.k(), this.b.l());
    System.out.println("+++++++idMath+++++++++=" + this.a.c());
    if (this.a.d)
      this.g = new w(); 
  }
  
  public void d() {
    if (!this.B)
      return; 
    this.b.a();
    this.c.a();
    int j;
    for (j = 0; j < this.l.b(); j++)
      ((b)this.l.b(j)).b(); 
    for (j = 0; j < this.o.b(); j++)
      ((p)this.o.b(j)).a(); 
    for (j = 0; j < this.p.b(); j++)
      ((ab)this.p.b(j)).e(); 
    for (j = this.s.b() - 1; j >= 0; j--)
      ((s)this.s.b(j)).a(); 
    this.q.b();
    this.d.b();
    this.u.e();
    if (this.w > 0) {
      this.w--;
      if (this.w < 15)
        this.x--; 
    } 
    this.y.a();
    this.A.b();
    this.t.a();
    if (this.g != null)
      this.g.h(); 
  }
  
  public void a(Graphics paramGraphics) {
    paramGraphics.drawImage(this.m, 0, 0, 0);
    if (this.B) {
      y.a(paramGraphics, this.r);
      y.a(paramGraphics, this.r, cx.l + 30, cx.m + 51, 180, cx.g);
      this.b.a(paramGraphics);
      y.b(paramGraphics, this.r);
      this.c.a(paramGraphics);
      this.q.a(paramGraphics);
    } 
    this.d.a(paramGraphics);
    if (!this.B)
      return; 
    this.y.a(paramGraphics, 0, 0);
    int j;
    for (j = 0; j < this.l.b(); j++)
      ((b)this.l.b(j)).a(paramGraphics, 0, 0); 
    for (j = this.s.b() - 1; j >= 0; j--)
      ((s)this.s.b(j)).a(paramGraphics); 
    for (j = 0; j < this.o.b(); j++)
      ((p)this.o.b(j)).a(paramGraphics, 0, 0); 
    for (j = 0; j < this.p.b(); j++)
      ((ab)this.p.b(j)).a(paramGraphics); 
    this.u.a(paramGraphics);
    this.A.a(paramGraphics);
    this.t.a(paramGraphics);
    if (this.g != null)
      this.g.a(paramGraphics); 
    if (this.w != 0) {
      if (this.v[0] != null)
        bz.d.a(paramGraphics, this.v[0], 0, cx.g - this.x, 0); 
      if (this.v[1] != null)
        bz.d.a(paramGraphics, this.v[1], cx.f / 2, cx.g - this.x, 1); 
      if (this.v[2] != null)
        bz.d.a(paramGraphics, this.v[2], cx.f, cx.g - this.x, 2); 
    } 
  }
  
  public void e() {
    this.c.b(0);
    i i1 = this.b.a(this.a.f() - 2, this.a.g() - 2);
    this.c.a(i1.i(), i1.j());
  }
  
  public void f() {
    this.c.b(0);
    i i1 = this.b.a(this.a.f() - 2, this.a.g() - 2);
    this.c.b(i1.i(), i1.j());
  }
  
  public void g() {
    this.c.b(0);
    int j = this.a.f();
    int m = this.a.g();
    i i1 = this.b.a(j - 2, m - 2);
    int n = 0;
    if (m > 2)
      n |= 0x1; 
    if (m < 9)
      n |= 0x2; 
    if (j > 2)
      n |= 0x4; 
    if (j < 9)
      n |= 0x8; 
    this.c.a(n, i1.i(), i1.j());
  }
  
  private int a(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    byte b = 0;
    if (paramInt2 < paramInt4) {
      b = 2;
    } else if (paramInt2 > paramInt4) {
      b = 1;
    } else if (paramInt1 < paramInt3) {
      b = 8;
    } else if (paramInt1 > paramInt3) {
      b = 4;
    } 
    return b;
  }
  
  public void a(int paramInt1, int paramInt2) {
    this.c.b(0);
    int j = this.a.f();
    int m = this.a.g();
    i i1 = this.b.a(j - 2, m - 2);
    i i2 = this.b.a(paramInt1 - 2, paramInt2 - 2);
    this.c.b(i2.i(), i2.j(), i1.i(), i1.j());
  }
  
  public void a(a parama) {
    int n;
    int j = parama.a;
    int m = parama.b;
    if ((parama.e[0] & 0xFF) >= 3 || (parama.e[1] & 0xFF) >= 3) {
      n = a(parama.c, parama.d, parama.a, parama.b);
      j = parama.c;
      m = parama.d;
    } else {
      n = a(parama.a, parama.b, parama.c, parama.d);
    } 
    this.c.b(1);
    i i1 = this.b.a(j - 2, m - 2);
    this.c.a(n, i1.i(), i1.j());
  }
  
  public void b(a parama) {
    int n;
    int j = parama.a;
    int m = parama.b;
    if ((parama.e[0] & 0xFF) >= 3 || (parama.e[1] & 0xFF) >= 3) {
      n = a(parama.c, parama.d, parama.a, parama.b);
      j = parama.c;
      m = parama.d;
    } else {
      n = a(parama.a, parama.b, parama.c, parama.d);
    } 
    int i1 = (m - 2) * 21 + this.b.i();
    int i2 = (j - 2) * 21 + this.b.j();
    this.q.a(i1, i2, n);
  }
  
  public void h() {
    this.q.a();
  }
  
  public void i() {
    this.c.b();
  }
  
  public int j() {
    byte b1 = 0;
    for (byte b2 = 0; b2 < 8; b2++) {
      int j = 0 + b1;
      for (byte b = 0; b < 8; b++) {
        i i1 = this.b.a(b2, b);
        i1.b(21, j);
        j++;
      } 
      b1++;
    } 
    return 22;
  }
  
  public int k() {
    byte b1 = 0;
    int[][] arrayOfInt = this.a.e();
    int[] arrayOfInt1 = new int[12];
    for (byte b2 = 2; b2 < 10; b2++) {
      byte b3 = 0;
      for (byte b4 = 9; b4 >= 2; b4--) {
        b(b3, b2, b4, b2, arrayOfInt[b4][b2], arrayOfInt1[b2] + b1);
        arrayOfInt1[b2] = arrayOfInt1[b2] + 1;
        b3--;
      } 
      b1++;
    } 
    return 21;
  }
  
  public void l() {
    int[][] arrayOfInt = this.a.e();
    for (byte b = 2; b < 10; b++) {
      for (byte b1 = 9; b1 >= 2; b1--)
        a(b1, b, arrayOfInt[b1][b]); 
    } 
  }
  
  public void a(boolean paramBoolean) {
    this.B = paramBoolean;
  }
  
  public void a(int paramInt1, int paramInt2, int paramInt3) {
    this.b.a(paramInt1, paramInt2, paramInt3);
  }
  
  public void a(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6) {
    this.b.a(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6);
  }
  
  public void b(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6) {
    this.b.b(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6);
  }
  
  public int a(cc paramcc, int paramInt) {
    if (e.i[paramcc.e] == 0) {
      paramInt = (paramInt + 1) % 2;
      b(paramcc, paramInt);
    } 
    ax ax = this.d.a()[paramInt];
    int j = ax.i() + ax.k() / 2;
    byte b = 35;
    if (paramcc.e != 4 && paramcc.e != 5)
      if (paramInt == 0) {
        j += b;
      } else {
        j -= b + 20;
      }  
    int m = ax.j();
    switch (paramcc.e) {
      case 4:
      case 5:
        m += ax.l() / 2;
        break;
      case 2:
        this.d.a(12);
        m += 0;
        break;
      case 1:
        m += 8;
        break;
      case 3:
        m += 16;
        break;
    } 
    int n = this.b.i();
    int i1 = this.b.j();
    int i2 = paramcc.a - 2;
    int i3 = paramcc.b - 2;
    int i4 = 0;
    int i5 = i1 + i2 * 21;
    int i6 = i2 + paramcc.c;
    int i7 = i3 + paramcc.d;
    for (int i8 = i2; i8 < i6; i8++) {
      i4 = n + i3 * 21;
      for (int i9 = i3; i9 < i7; i9++) {
        a(i4, i5, j, m, paramcc.e);
        i4 += 21;
      } 
      i5 += 21;
    } 
    return this.b.a(paramcc, j, m);
  }
  
  public int b(boolean paramBoolean) {
    Image image;
    if (paramBoolean) {
      image = az.b("/strwin");
    } else {
      image = az.b("/strlose");
    } 
    return this.u.a(image);
  }
  
  public void a(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5) {
    if (paramInt5 == 0 || paramInt5 > 5)
      return; 
    s s = null;
    Image image = this.n[paramInt5];
    if (image == null)
      return; 
    for (int j = this.s.b() - 1; j > -1; j--) {
      s s1 = (s)this.s.b(j);
      if (!s1.m()) {
        s = s1;
        break;
      } 
    } 
    if (s == null) {
      s = new s();
      this.s.a(s);
    } 
    s.a(paramInt1, paramInt2, paramInt3, paramInt4, image);
  }
  
  public void b(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5) {
    b b = null;
    int j;
    for (j = this.l.b() - 1; j > -1; j--) {
      b b1 = (b)this.l.b(j);
      if (!b1.a()) {
        b = b1;
        break;
      } 
    } 
    if (b == null) {
      b = new b(this.k);
      this.l.a(b);
    } 
    j = (paramInt3 + paramInt5 / 2 - 2) * 21 - ((paramInt5 % 2 == 0) ? 10 : 4) + this.b.i();
    int m = (paramInt2 + paramInt4 / 2 - 2) * 21 - ((paramInt4 % 2 == 0) ? 10 : 5) + this.b.j();
    int n = paramInt1 - 1;
    b.a(j, m, n, "x" + paramInt1);
  }
  
  public void b(cc paramcc, int paramInt) {
    Image image = this.n[0];
    ax[] arrayOfAx = this.d.a();
    int j = arrayOfAx[paramInt].i() + 3 + ay.a(arrayOfAx[paramInt].k() - 6);
    int m = arrayOfAx[paramInt].j() + 3 + ay.a(arrayOfAx[paramInt].l() - 6);
    p p = null;
    int n = paramcc.a + paramcc.c;
    int i1 = paramcc.b + paramcc.d;
    for (int i2 = paramcc.a; i2 < n; i2++) {
      for (int i4 = paramcc.b; i4 < i1; i4++) {
        p = null;
        i i5 = this.b.a(i2 - 2, i4 - 2);
        i5.a_(0);
        for (int i6 = this.o.b() - 1; i6 > -1; i6--) {
          p p1 = (p)this.o.b(i6);
          if (!p1.m()) {
            p = p1;
            break;
          } 
        } 
        if (p == null) {
          p = new p(image);
          this.o.a(p);
        } 
        p.b(i5.i(), i5.j(), j, m);
      } 
    } 
    this.d.b(paramInt, 36);
    this.d.a(26);
    ax ax = arrayOfAx[paramInt];
    int i3 = ax.i() + ax.k() / 2;
    for (byte b = 0; b < this.p.b(); b++) {
      ab ab = (ab)this.p.b(b);
      if (ab.b() == 4 && ab.i() == i3)
        ((do)ab).e(26); 
    } 
  }
  
  public void a(int paramInt) {
    ax ax = this.d.a()[paramInt];
    int j = ax.i() + ax.k() / 2;
    for (byte b = 0; b < this.p.b(); b++) {
      ab ab = (ab)this.p.b(b);
      if (ab.m() && ab.b() == 8 && ab.i() == j)
        ab.a_(1); 
    } 
  }
  
  public void b(int paramInt) {
    ax ax = this.d.a()[paramInt];
    int j = ax.i() + ax.k() / 2;
    for (byte b = 0; b < this.p.b(); b++) {
      ab ab = (ab)this.p.b(b);
      if (ab.b() == 4 && ab.i() == j)
        ((do)ab).d(15); 
    } 
  }
  
  public void b(int paramInt1, int paramInt2, int paramInt3) {
    this.d.b(paramInt1, paramInt2);
    this.d.a(paramInt3);
  }
  
  public int a(int paramInt1, int paramInt2, cc[] paramArrayOfcc, int paramInt3) {
    short s;
    char c;
    int i1;
    int i2;
    ax ax;
    i i3;
    int i4;
    byte b1;
    byte b2;
    int i5;
    int i6;
    byte b3;
    bn bn;
    byte b4;
    int[] arrayOfInt1;
    int[] arrayOfInt2;
    int i7;
    byte b5;
    cu cu;
    int j = 0;
    int m = 0;
    int n = (paramInt2 + 1) % 2;
    switch (paramInt1) {
      case 0:
        m = 0;
        j = 0;
        s = -300;
        c = 'Ĭ';
        if (paramInt2 == 0)
          s = -s; 
        ax = this.d.a()[n];
        i1 = ax.i() + ax.k() / 2;
        i2 = ax.j() + ax.l() / 2;
        j = a(j, paramInt1, i1 - s, i2 - c, i1, i2, m);
        b(n, m + 28, m + 18);
        m = ay.a(5) + 7;
        i3 = this.b.a((paramArrayOfcc[4]).a - 2, (paramArrayOfcc[4]).b - 2);
        i1 = i3.i();
        i2 = i3.j();
        j = a(j, paramInt1, i1 - s, i2 - c, i1, i2, m);
        m += 18;
        break;
      case 2:
        m = 0;
        j = 0;
        ax = this.d.a()[n];
        i1 = ax.i() + ax.k() / 2;
        i2 = ax.j() + ax.l() / 2;
        j = a(j, paramInt1, i1, i2, i1, i2, m);
        b(n, m + 10, m + 4);
        i4 = paramInt3 / 16;
        b1 = 5;
        for (b2 = 0; b2 < i4; b2++) {
          i3 = this.b.a((paramArrayOfcc[b1]).a - 2, (paramArrayOfcc[b1]).b - 2);
          i1 = i3.i();
          i2 = i3.j();
          int i8 = ay.a(15) + 4;
          j = a(j, paramInt1, i1, i2, i1, i2, i8);
          if (i8 > m)
            m = i8; 
          b1 += 16;
        } 
        m += 6;
        break;
      case 1:
        m = 0;
        j = 0;
        s = -300;
        c = 'Ĭ';
        i1 = 0;
        i2 = 0;
        if (paramInt2 == 0)
          s = -s; 
        ax = this.d.a()[n];
        i1 = ax.i() + ax.k() / 2;
        i2 = ax.j() + ax.l() / 2;
        j = a(j, paramInt1, i1 - s, i2 - c, i1, i2, m);
        b(n, m + 28, m + 18);
        b2 = 0;
        i5 = paramInt3 / 4;
        for (i6 = 0; i6 < i5; i6++) {
          i3 = this.b.a((paramArrayOfcc[b2]).a - 2, (paramArrayOfcc[b2]).b - 2);
          i1 = i3.i() - 10;
          i2 = i3.j() - 10;
          int i8 = ay.a(15) + 7;
          j = a(j, paramInt1, i1 - s, i2 - c, i1, i2, i8);
          if (i8 > m)
            m = i8; 
          b2 += 4;
        } 
        m += 16;
        break;
      case 5:
        m = 0;
        j = 0;
        i1 = 0;
        i2 = 0;
        ax = this.d.a()[n];
        i1 = ax.i() + ax.k() / 2;
        i2 = ax.j() + ax.l() / 2;
        j = a(j, paramInt1, i1, i2, i1, i2, m);
        b(n, m + 12, m + 10);
        b2 = 4;
        i6 = paramInt3 / 9;
        for (b3 = 0; b3 < i6; b3++) {
          i3 = this.b.a((paramArrayOfcc[b2]).a - 2, (paramArrayOfcc[b2]).b - 2);
          i1 = i3.i();
          i2 = i3.j();
          int i8 = ay.a(20) + 6;
          j = a(j, paramInt1, i1, i2, i1, i2, i8);
          if (i8 > m)
            m = i8; 
          b2 += 9;
        } 
        m += 5;
        break;
      case 6:
        m = 0;
        j = 0;
        s = -55;
        c = 'Ĭ';
        i1 = 0;
        i2 = 0;
        if (paramInt2 == 0)
          s = -s; 
        ax = this.d.a()[n];
        i1 = ax.i() + ax.k() / 2;
        i2 = ax.j() + ax.l() / 2;
        j = a(j, 6, i1 - s, i2 - c, i1, i2, m);
        b(n, m + 28, m + 18);
        m = ay.a(5) + 7;
        i2 = ax.j() + 28;
        bn = this.a.b(n);
        if (n == 0) {
          i1 = ax.i() - 1 + ax.k() + 44 * bn.o() / bn.n();
        } else {
          i1 = ax.i() - 7 - 44 * bn.o() / bn.n();
        } 
        j = a(j, 6, i1 - s, i2 - c, i1, i2, m);
        m += 18;
        this.d.a(n, m - 4);
        for (b4 = 0; b4 < paramInt3; b4++) {
          i3 = this.b.a((paramArrayOfcc[b4]).a - 2, (paramArrayOfcc[b4]).b - 2);
          i1 = i3.i() - 10;
          i2 = i3.j() - 10;
          int i8 = ay.a(15) + 7;
          j = a(j, 6, i1 - s, i2 - c, i1, i2, i8);
          if (i8 > m)
            m = i8; 
        } 
        m += 3;
        break;
      case 8:
        m = 0;
        j = 0;
        s = -55;
        c = 'Ĭ';
        i1 = 0;
        i2 = 0;
        if (paramInt2 == 0)
          s = -s; 
        ax = this.d.a()[n];
        i1 = ax.i() + ax.k() / 2;
        i2 = ax.j() + ax.l() / 2;
        if (paramArrayOfcc != null) {
          j = a(j, 6, i1 - s, i2 - c, i1, i2, m);
          b(n, m + 23, m + 13);
        } 
        j = a(j, 8, i1, i2, i1, i2, m + 28);
        bn = this.a.b(n);
        if (bn.k())
          ((bf)this.p.b(j)).c(); 
        if (paramArrayOfcc != null) {
          m = ay.a(5) + 7;
          i2 = ax.j() + 27;
          if (n == 0) {
            i1 = ax.i() - 3 + ax.k() + 44 * bn.o() / bn.n();
          } else {
            i1 = ax.i() - 5 - 44 * bn.o() / bn.n();
          } 
          j = a(j, 6, i1 - s, i2 - c, i1, i2, m);
          m += 18;
          this.d.a(n, m - 4);
        } 
        for (b4 = 0; b4 < paramInt3; b4++) {
          i3 = this.b.a((paramArrayOfcc[b4]).a - 2, (paramArrayOfcc[b4]).b - 2);
          i1 = i3.i() - 10;
          i2 = i3.j() - 10;
          int i8 = ay.a(15) + 7;
          j = a(j, 6, i1 - s, i2 - c, i1, i2, i8);
          if (i8 > m)
            m = i8; 
        } 
        m = 36;
        break;
      case 7:
        j = 0;
        m = 14;
        this.d.a(m - 2);
        m = 0;
        ax = this.d.a()[paramInt2];
        i1 = ax.i() + ax.k() / 2;
        i2 = ax.j() + ax.l() / 2;
        a(j, paramInt1, i1, i2 - 15, i1, i2, 0);
        break;
      case 4:
        m = 0;
        j = 0;
        ax = this.d.a()[paramInt2];
        i1 = ax.i() + ax.k() / 2;
        i2 = ax.j() + ax.l() / 2;
        a(j, paramInt1, i1, i2, i1, i2, 0);
        break;
      case 3:
        m = 0;
        j = 0;
        arrayOfInt1 = new int[paramInt3 + 1];
        arrayOfInt2 = new int[paramInt3 + 1];
        i7 = arrayOfInt2.length - 1;
        for (b5 = 0; b5 < i7; b5++) {
          i3 = this.b.a((paramArrayOfcc[b5]).a - 2, (paramArrayOfcc[b5]).b - 2);
          arrayOfInt1[b5] = i3.i();
          arrayOfInt2[b5] = i3.j();
        } 
        m = arrayOfInt2.length * 5 + 10;
        b(n, m, m - 6);
        ax = this.d.a()[n];
        i1 = ax.i() + ax.k() / 2;
        i2 = ax.j() + ax.l() / 2;
        arrayOfInt1[arrayOfInt1.length - 1] = i1;
        arrayOfInt2[arrayOfInt1.length - 1] = i2;
        j = a(j, paramInt1, i1, i2, i1, i2, m);
        cu = (cu)this.p.b(j);
        cu.a(arrayOfInt1, arrayOfInt2);
        break;
    } 
    return m;
  }
  
  private int b(int paramInt1, int paramInt2) {
    if (paramInt1 < 0)
      return -1; 
    for (int j = paramInt1; j < this.p.b(); j++) {
      ab ab = (ab)this.p.b(j);
      if (!ab.m() && ab.b() == paramInt2)
        return j; 
    } 
    return -1;
  }
  
  private int a(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7) {
    paramInt1 = b(paramInt1, paramInt2);
    ab ab = null;
    if (paramInt1 > 0) {
      ab = (ab)this.p.b(paramInt1);
    } else {
      switch (paramInt2) {
        case 0:
        case 1:
          ab = new bt((bs.a()).r, 9);
          ab.a(new byte[][] { { 0, 1, 2, 3 }, { 4, 4, 5, 5, 6, 6, 7, 7, 8, 8 } });
          break;
        case 2:
          ab = new bt((bs.a()).s, 4);
          ab.a(new byte[][] { new byte[1], { 0, 0, 1, 1, 2, 2, 3, 3, 3 } });
          break;
        case 5:
          ab = new au((bs.a()).t, 5);
          ab.a(new byte[][] { { 
                  0, 0, 1, 1, 2, 2, 2, 3, 3, 3, 
                  4, 4, 4 } });
          break;
        case 6:
          ab = new ce((bs.a()).u, 5);
          ab.a(new byte[][] { new byte[1], { 1, 1, 2, 2, 3, 3, 4, 4 } });
          break;
        case 8:
          ab = new bf((bs.a()).v, 9);
          ab.a(new byte[][] { { 0, 0, 1, 1, 2, 2, 3, 3, 4, 4 }, { 5, 5, 6, 6, 7, 7, 8, 8 } });
          break;
        case 7:
          ab = new am((bs.a()).w, 3);
          ab.a(new byte[][] { { 
                  0, 0, 0, 1, 1, 1, 2, 2, 1, 1, 
                  2, 2 } });
          break;
        case 4:
          ab = new do((bs.a()).x, 7);
          ab.a(new byte[][] { { 
                  0, 0, 1, 1, 2, 2, 3, 3, 4, 4, 
                  4 }, { 5, 5, 6, 6, 5, 5, 6, 6 } });
          break;
        case 3:
          ab = new cu((bs.a()).y, 3, (bs.a()).h);
          ab.a(new byte[][] { { 0, 1, 2, -1 } });
          break;
      } 
      ab.a(paramInt2);
      this.p.a(ab);
      paramInt1 = this.p.b() - 1;
    } 
    ab.a(paramInt3, paramInt4, paramInt5, paramInt6, paramInt7);
    return paramInt1;
  }
  
  public void c(boolean paramBoolean) {
    this.d.d(paramBoolean);
  }
  
  public void c(int paramInt) {
    this.d.b(paramInt);
  }
  
  public void a(int paramInt, dy paramdy, bn parambn) {
    this.z.a(paramInt, paramdy, parambn);
  }
  
  public void m() {
    this.d.c();
  }
  
  public void n() {
    for (int j = this.p.b() - 1; j > -1; j--) {
      ab ab = (ab)this.p.b(j);
      ab.a(false);
    } 
    this.A.b(dd.g);
    this.d.b(false);
    this.d.c(false);
  }
  
  public void c(int paramInt1, int paramInt2, int paramInt3) {
    int j = (paramInt3 - 2) * 21 + this.b.i();
    int m = (paramInt2 - 2) * 21 + this.b.j();
    this.y.a("Còn " + paramInt1 + " lượt", j, m);
  }
  
  public void d(boolean paramBoolean) {
    this.d.b(paramBoolean);
  }
  
  public void e(boolean paramBoolean) {
    this.d.c(paramBoolean);
  }
  
  public void d(int paramInt) {
    ax ax = this.d.a()[paramInt];
    int j = ax.i() + ax.k() / 2;
    byte b1 = 35;
    if (paramInt == 0) {
      j += b1;
    } else {
      j -= b1 + 20;
    } 
    int m = ax.j();
    int[] arrayOfInt1 = { -20, -10, 10, 20, -10, 10 };
    int[] arrayOfInt2 = { 0, -20, -20, 20, 20 };
    for (byte b2 = 0; b2 < arrayOfInt2.length; b2++)
      a(j + arrayOfInt1[b2], m + arrayOfInt2[b2], j, m, 2); 
  }
  
  public int o() {
    return this.A.a();
  }
  
  public void e(int paramInt) {
    this.A.a(paramInt);
  }
  
  public void d(int paramInt1, int paramInt2, int paramInt3) {
    int j = (paramInt3 - 2) * 21 + this.b.i();
    int m = (paramInt2 - 2) * 21 + this.b.j();
    this.t.a(paramInt1, j, m);
  }
}


/* Location:              C:\Users\bot-nosense\Downloads\Loan-12-Su-Quan.jar!\i.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */