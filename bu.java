import com.mg.smsgame.main.b;
import com.mic.smsgame.suquan.a;
import com.mic.smsgame.suquan.e;
import com.mic.smsgame.suquan.f;

public class bu implements b, e, dy {
  final int a = 0;
  
  final int b = 6;
  
  final int c = 10;
  
  final int d = 7;
  
  final int e = 8;
  
  final int f = 1;
  
  final int g = 2;
  
  final int k = 3;
  
  final int l = 4;
  
  final int m = 5;
  
  final int n = 11;
  
  final int o = 9;
  
  final int p = 12;
  
  final int q = 13;
  
  final int r = 14;
  
  private cq s;
  
  private i t;
  
  private ch[] u;
  
  private int v;
  
  private int[] w;
  
  private int x;
  
  private cc[] y;
  
  private int z;
  
  private a A;
  
  private di B;
  
  private int C;
  
  private int D;
  
  private boolean E = false;
  
  private int F;
  
  private int G;
  
  private int H;
  
  private int[] I;
  
  private int[] J;
  
  private boolean K;
  
  private boolean L = false;
  
  private int[] M = new int[] { -1, -1, 1, 1, 1, -1 };
  
  private int[] N = new int[] { 0, 1, 1, 1, -1, -1, -1 };
  
  public void a(byte[] paramArrayOfbyte, int paramInt) {
    if (paramArrayOfbyte == null)
      return; 
    this.s.a(0);
    f(7);
    paramInt += this.s.b(paramArrayOfbyte, paramInt);
    this.t.l();
    this.B = this.s.k();
    this.t.e(ai.a(paramArrayOfbyte, paramInt));
    paramInt += 4;
    this.G = ai.a(paramArrayOfbyte, paramInt);
    paramInt += 4;
    this.H = ai.a(paramArrayOfbyte, paramInt);
    paramInt += 4;
    if (this.H > 0)
      this.t.c(this.H, 6, 6); 
    byte b1;
    for (b1 = 0; b1 < this.I.length; b1++) {
      this.I[b1] = ai.a(paramArrayOfbyte, paramInt);
      paramInt += 4;
      if (this.I[b1] > 0) {
        this.t.a(4, b1, this.y, 0);
        this.s.b(b1).a(true);
      } 
    } 
    for (b1 = 0; b1 < this.J.length; b1++) {
      this.J[b1] = ai.a(paramArrayOfbyte, paramInt);
      paramInt += 4;
      if (this.J[b1] > 0)
        this.t.a(8, (b1 + 1) % 2, (cc[])null, 0); 
    } 
    this.K = (ai.a(paramArrayOfbyte, paramInt) == 1);
    paramInt += 4;
    this.L = (ai.a(paramArrayOfbyte, paramInt) == 1);
    dx[] arrayOfDx = (this.s.b(0)).a;
    if ((arrayOfDx[3]).b >= 1)
      this.t.e(true); 
    if ((arrayOfDx[0]).b >= 1)
      this.t.d(true); 
  }
  
  private void u() {
    if (this.v != 8)
      return; 
    int j = 0;
    byte[] arrayOfByte = new byte[400];
    System.arraycopy(ai.a(dd.c), 0, arrayOfByte, j, 4);
    j += true;
    j += dd.e.a(arrayOfByte, j);
    j += dd.f.a(arrayOfByte, j);
    j += this.s.a(arrayOfByte, j);
    System.arraycopy(ai.a(this.t.o()), 0, arrayOfByte, j, 4);
    j += 4;
    System.arraycopy(ai.a(this.G), 0, arrayOfByte, j, 4);
    j += 4;
    System.arraycopy(ai.a(this.H), 0, arrayOfByte, j, 4);
    j += 4;
    byte b1;
    for (b1 = 0; b1 < this.I.length; b1++) {
      System.arraycopy(ai.a(this.I[b1]), 0, arrayOfByte, j, 4);
      j += 4;
    } 
    for (b1 = 0; b1 < this.J.length; b1++) {
      System.arraycopy(ai.a(this.J[b1]), 0, arrayOfByte, j, 4);
      j += 4;
    } 
    System.arraycopy(ai.a(this.K ? 1 : 0), 0, arrayOfByte, j, 4);
    j += 4;
    System.arraycopy(ai.a(this.L ? 1 : 0), 0, arrayOfByte, j, 4);
    j += 4;
    f.a(arrayOfByte, 0, j);
    bg.c(1);
  }
  
  public bu(cq paramcq, i parami) {
    this.s = paramcq;
    this.t = parami;
    this.w = new int[64];
    this.y = new cc[64];
    for (byte b1 = 0; b1 < this.y.length; b1++)
      this.y[b1] = new cc(); 
    this.I = new int[2];
    this.J = new int[2];
    b();
    if (!this.s.d) {
      this.t.a("T.Dừng", 2);
      this.t.a("T.Chiêu", 0);
      if (cx.n)
        this.t.a("Túi", 1); 
    } 
    v();
    f(0);
  }
  
  public void a() {
    this.I = new int[2];
    this.J = new int[2];
    this.L = false;
    bn[] arrayOfBn = this.s.d();
    for (byte b1 = 0; b1 < arrayOfBn.length; b1++)
      arrayOfBn[b1].h(); 
    this.s.b();
    this.t.n();
    this.t.e(dd.g);
    this.G = this.H = 0;
    this.D = 0;
    dd.j = 0;
    dd.i = 0;
    v();
    f(10);
    dd.h();
  }
  
  private void v() {
    this.s.a(dd.h ? 0 : 1);
    this.G = this.H = 1;
  }
  
  public void b() {
    bn[] arrayOfBn = this.s.d();
    this.u = new ch[2];
    this.u[0] = g(arrayOfBn[0].b(), 0);
    this.u[1] = g(arrayOfBn[1].b(), 1);
    byte b1 = 3;
    arrayOfBn[0].c(arrayOfBn[1].m() + b1);
    arrayOfBn[1].c(arrayOfBn[0].m() + b1);
  }
  
  private ch g(int paramInt1, int paramInt2) {
    return (ch)((paramInt1 < 6) ? new ac(this, paramInt2) : new t(this, paramInt2));
  }
  
  private void w() {
    this.z = 0;
    int j = 0;
    int k = 0;
    boolean bool1 = false;
    boolean bool2 = false;
    int m = 0;
    int n = this.A.a;
    int i1 = this.A.b;
    j = this.A.e[0];
    bool1 = ((j & 0xFF) >= 3) ? true : false;
    if (bool1)
      m += j & 0xFF; 
    k = this.A.e[1];
    bool2 = ((k & 0xFF) >= 3) ? true : false;
    if (bool2)
      m += k & 0xFF; 
    if (bool1)
      a(this.s.a(n, i1), n, i1 - ((j & 0xFF0000) >> 16), 1, j & 0xFF, m); 
    if (bool2)
      a(this.s.a(n, i1), n - ((k & 0xFF0000) >> 16), i1, k & 0xFF, 1, m); 
    j = 0;
    k = 0;
    bool1 = false;
    bool2 = false;
    m = 0;
    n = this.A.c;
    i1 = this.A.d;
    j = this.A.e[2];
    bool1 = ((j & 0xFF) >= 3) ? true : false;
    if (bool1)
      m += j & 0xFF; 
    k = this.A.e[3];
    bool2 = ((k & 0xFF) >= 3) ? true : false;
    if (bool2)
      m += k & 0xFF; 
    if (bool1)
      a(this.s.a(n, i1), n, i1 - ((j & 0xFF0000) >> 16), 1, j & 0xFF, m); 
    if (bool2)
      a(this.s.a(n, i1), n - ((k & 0xFF0000) >> 16), i1, k & 0xFF, 1, m); 
  }
  
  public void c() {
    ae ae;
    int j;
    bn bn;
    if (this.C > 0) {
      this.C--;
      return;
    } 
    switch (this.v) {
      case 9:
        f(12);
        this.C = this.t.b(this.E);
        if (this.E) {
          bs.a().d();
        } else {
          bs.a().e();
        } 
        return;
      case 12:
        this.s.b(0).i();
        dd.i = this.s.b(0).f();
        dd.j = this.s.b(0).g();
        if (this.E) {
          ae ae1 = dd.a(false);
          ae1.c(new da("Đóng", -5));
          ae1.a(this);
          cz.d().a(ae1, true);
          break;
        } 
        ae = dd.g();
        if (dd.a >= 0 || !bg.l()) {
          ae.c(new da("Trở về", -4));
        } else {
          ae.a(new da("Có", -3));
          ae.b(new da("Không", -4));
        } 
        ae.a(this);
        cz.d().a(ae, true);
        break;
      case 0:
        if (this.s.d) {
          f(13);
          break;
        } 
        f(6);
        break;
      case 14:
        if (!this.t.g.m())
          f(13); 
        break;
      case 11:
        f(3);
        break;
      case 10:
        f(0);
        break;
      case 7:
        this.t.c(this.s.a());
        this.t.c(false);
        f(8);
        if (this.s.a() == 0)
          this.t.f(); 
        break;
      case 1:
        w();
        if (this.z > 0) {
          f(3);
          break;
        } 
        f(2);
        break;
      case 2:
        f(6);
        break;
      case 8:
        if (this.t.o() == 1 && y())
          return; 
        j = this.s.a();
        bn = this.s.b(j);
        if (!this.s.d && bn.p() <= 0) {
          if (j == 0) {
            this.E = false;
          } else {
            this.E = true;
          } 
          f(9);
        } 
        this.u[j].b();
        if (j == 0 && this.B != null) {
          this.F++;
          if (this.F == 250) {
            a a1 = (a)this.B.b(ay.a(this.B.b()));
            this.t.b(a1);
          } 
        } 
        break;
      case 3:
        x();
        f(4);
        break;
      case 4:
        f(5);
        break;
      case 5:
        f(3);
        break;
    } 
  }
  
  private void a(bn parambn, int paramInt) {
    parambn.k(paramInt);
  }
  
  private void b(bn parambn, int paramInt) {
    parambn.l(paramInt);
  }
  
  private void x() {
    int j = this.s.a();
    bn[] arrayOfBn = this.s.d();
    for (byte b1 = 0; b1 < this.z; b1++) {
      boolean bool;
      int n;
      cc cc1 = this.y[b1];
      int k = (cc1.d > cc1.c) ? cc1.d : cc1.c;
      int m = k * k - 2 * k;
      if (m < 0)
        m = 1; 
      byte b2 = 0;
      switch (e.i[cc1.e]) {
        case 0:
          bool = (j == 1) ? false : true;
          a(arrayOfBn[bool], (m + b2) * this.D + arrayOfBn[j].m());
          if (this.s.d && j == 0 && arrayOfBn[j].r() <= 0)
            arrayOfBn[j].m(1); 
          break;
        case 2:
          b(arrayOfBn[j], m * this.D + this.s.b);
          break;
        case 1:
          arrayOfBn[j].i(m * this.D + this.s.c);
          break;
        case 3:
          n = arrayOfBn[j].o() / 12;
          arrayOfBn[j].f(m * this.D);
          if (j == 0 && arrayOfBn[j].o() / 12 != n)
            this.t.m(); 
          break;
        case 4:
          arrayOfBn[j].a(m * this.D);
          break;
        case 5:
          arrayOfBn[j].b(m * this.D);
          break;
      } 
    } 
  }
  
  private void f(int paramInt) {
    int[] arrayOfInt;
    w w;
    byte b1;
    dx[] arrayOfDx1;
    dx[] arrayOfDx2;
    int j;
    bn bn;
    int[][] arrayOfInt1;
    int k;
    int m;
    byte b2;
    this.t.a();
    this.t.h();
    this.t.i();
    switch (paramInt) {
      case 9:
        this.C = 20;
        this.t.c(true);
        break;
      case 0:
        this.t.c(true);
        this.s.j();
        this.C = this.t.k();
        break;
      case 10:
        this.t.c(true);
        this.C = this.t.j();
        break;
      case 6:
        if (y())
          return; 
        this.B = this.s.k();
        if (this.B.b() <= 0) {
          f(10);
        } else {
          f(7);
        } 
        return;
      case 13:
        this.D = 0;
        this.t.c();
        this.t.a("Bỏ qua", 2);
        arrayOfInt = this.t.g.b();
        this.s.c(arrayOfInt[0]);
        this.s.d(arrayOfInt[1]);
        w = this.t.g;
        if (w != null) {
          a a1;
          int n = w.g();
          switch (n) {
            case 0:
              w.d();
              f(14);
              w.c();
              break;
            case 6:
              this.B = null;
              this.G = 1;
              this.s.a(0);
              f(7);
              break;
            case 7:
              this.s.a(true);
              this.G = 1;
              this.s.a(0);
              a1 = new a();
              a1.a = arrayOfInt[0];
              a1.b = arrayOfInt[1];
              a1.c = arrayOfInt[2];
              a1.d = arrayOfInt[3];
              this.B = new di(1);
              this.B.a(a1);
              f(8);
              this.t.g();
              break;
            case 1:
              a1 = new a();
              a1.a = arrayOfInt[0];
              a1.b = arrayOfInt[1];
              a1.c = arrayOfInt[2];
              a1.d = arrayOfInt[3];
              this.B = new di(1);
              this.B.a(a1);
              this.G = 1;
              this.s.a(0);
              f(7);
              this.F = 249;
              break;
            case 2:
              a1 = new a();
              a1.a = arrayOfInt[0];
              a1.b = arrayOfInt[1];
              a1.c = arrayOfInt[2];
              a1.d = arrayOfInt[3];
              this.B = new di(1);
              this.B.a(a1);
              this.G = 1;
              this.s.a(1);
              f(7);
              break;
            case 3:
            case 5:
              this.B = null;
              this.G = 1;
              this.s.a(0);
              f(8);
              this.t.i();
              if (w.g() == 3) {
                this.t.a("T.Chiêu", 0);
                break;
              } 
              if (cx.n)
                this.t.a("Túi", 1); 
              break;
            case 4:
              this.E = true;
              f(12);
              break;
          } 
          return;
        } 
        break;
      case 7:
        this.F = 0;
        for (b1 = 0; b1 < 2; b1++) {
          if (this.J[b1] > 0) {
            this.J[b1] = this.J[b1] - 1;
            if (this.J[b1] == 0) {
              this.C = 8;
              this.t.a(b1);
            } 
          } 
          if (this.I[b1] > 0) {
            this.I[b1] = this.I[b1] - 1;
            if (this.I[b1] == 0) {
              bn bn1 = this.s.b(b1);
              bn1.a(false);
              this.t.b(b1);
            } 
          } 
        } 
        this.K = false;
        arrayOfDx1 = (this.s.b(0)).a;
        arrayOfDx2 = (this.s.b(1)).a;
        for (j = 0; j < arrayOfDx1.length; j++) {
          arrayOfDx1[j].a();
          arrayOfDx2[j].a();
        } 
        if ((arrayOfDx1[3]).b < 1)
          this.t.e(false); 
        if ((arrayOfDx1[0]).b < 1)
          this.t.d(false); 
        this.s.a(false);
        if (this.G <= 0) {
          this.G = 0;
          j = (this.s.a() + 1) % this.u.length;
          this.s.a(j);
          this.C = 8;
        } else {
          if (this.G > 1 || this.H > 1)
            this.t.c(this.G, 6, 6); 
          this.H = this.G;
          this.G--;
        } 
        this.D = 0;
        break;
      case 8:
        if (this.s.a() == 0)
          this.t.b(); 
        this.u[this.s.a()].a();
        break;
      case 1:
        this.t.a(this.A.a, this.A.b, this.A.c, this.A.d, this.s.a(this.A.a, this.A.b), this.s.a(this.A.c, this.A.d));
        this.C = 10;
        this.s.b(this.A.a, this.A.b, this.A.c, this.A.d);
        this.s.a(false);
        break;
      case 2:
        this.t.a(this.A.a, this.A.b, this.A.c, this.A.d, this.s.a(this.A.a, this.A.b), this.s.a(this.A.c, this.A.d));
        this.s.b(this.A.a, this.A.b, this.A.c, this.A.d);
        this.C = 10;
        bn = this.s.b(this.s.a());
        a(bn, 3 + bn.m());
        this.t.b(this.s.a(), 0, 3);
        break;
      case 3:
        System.out.println("********************************getScore**************************************");
        if (this.z <= 0) {
          if (this.s.d) {
            this.t.g.c();
            this.t.g.a();
            f(10);
            return;
          } 
          f(6);
          return;
        } 
        this.C = 0;
        if (this.z > 0)
          this.D++; 
        arrayOfInt1 = this.s.e();
        k = this.z;
        for (m = 0; m < k; m++) {
          cc cc1 = this.y[m];
          for (int n = cc1.a + cc1.c - 1; n >= cc1.a; n--) {
            for (int i1 = cc1.b + cc1.d - 1; i1 >= cc1.b; i1--) {
              if (arrayOfInt1[n][i1] == 6)
                b(6, n, i1, 1, 1, 1); 
              arrayOfInt1[n][i1] = 7;
            } 
          } 
        } 
        for (m = k; m < this.z; m++) {
          cc cc1 = this.y[m];
          if (cc1.e == 6) {
            for (byte b3 = 0; b3 < this.M.length; b3++) {
              int n = cc1.a + this.M[b3];
              int i1 = cc1.b + this.N[b3];
              if (arrayOfInt1[n][i1] < 7) {
                b(arrayOfInt1[n][i1], n, i1, 1, 1, 1);
                arrayOfInt1[n][i1] = 7;
              } 
            } 
            cc1.e = 0;
          } 
          arrayOfInt1[cc1.a][cc1.b] = 7;
        } 
        m = this.s.a();
        for (b2 = 0; b2 < this.z; b2++) {
          cc cc1 = this.y[b2];
          int n = 0;
          n = this.t.a(cc1, this.s.a());
          if (n > this.C)
            this.C = n; 
          if (this.D > 1)
            this.t.b(this.D, cc1.a, cc1.b, cc1.c, cc1.d); 
          if (m == 0) {
            int i1 = (cc1.d > cc1.c) ? cc1.d : cc1.c;
            if (i1 >= 5) {
              int i2 = g(i1);
              if (i2 >= 0)
                this.t.d(i2, cc1.a + cc1.c / 2, cc1.b + cc1.d / 2); 
            } 
          } 
        } 
        bs.a().g();
        break;
      case 4:
        e();
        break;
      case 5:
        f();
        break;
    } 
    this.v = paramInt;
  }
  
  private int g(int paramInt) {
    byte b1 = -1;
    if (this.s.d) {
      b1 = 1;
      (this.s.a[b1]).b++;
    } else {
      int j = (paramInt - 5) * (paramInt - 5) * 15;
      int k = ay.a(100);
      if (k < 40 + j) {
        k = ay.a(100);
        if (k < 10 + j) {
          if (ay.a(100) < 50) {
            b1 = 1;
          } else {
            b1 = 0;
          } 
        } else if (k < 25 + j) {
          b1 = 3;
        } else if (k < 45 + j) {
          b1 = 5;
        } else if (k < 65 + j) {
          b1 = 4;
        } 
      } 
      if (b1 >= 0) {
        (this.s.a[b1]).b++;
        bg.a(b1, 1, 6);
      } 
    } 
    return b1;
  }
  
  public int d() {
    int j = this.s.a();
    bn bn = this.s.b(j);
    return ((bn.d()).length < 1) ? 0 : (bn.o() * 3 / bn.n());
  }
  
  public void a(int paramInt) {
    int n;
    int i1;
    int i2;
    byte b1;
    int i3;
    int i4;
    int i5;
    int j = this.s.a();
    bn bn1 = this.s.b(j);
    int k = (this.s.a() + 1) % 2;
    bn bn2 = this.s.b(k);
    this.z = 0;
    int[] arrayOfInt = bn1.d();
    if (paramInt > arrayOfInt.length - 1)
      paramInt = arrayOfInt.length - 1; 
    if (paramInt < 0)
      return; 
    int m = arrayOfInt[paramInt];
    bn1.a(bn1.n() * (paramInt + 1) / 3, true);
    switch (m) {
      case 0:
        a(bn2, bn1.m());
        n = ay.a(6) + 2;
        i1 = ay.a(6) + 2;
        for (i2 = n + 2; i2 >= n; i2--) {
          for (int i6 = i1 + 2; i6 >= i1; i6--)
            a(this.s.a(i2, i6), i2, i6, 1, 1, 1); 
        } 
        break;
      case 1:
        a(bn2, bn1.m() * 3 / 2);
        i2 = 0;
        b1 = 2;
        i1 = 2;
        for (i3 = 3; i3 >= 0; i3--) {
          i2 = ay.a(4);
          n = i2 * 2 + 2;
          int i6;
          for (i6 = n + 1; i6 >= n; i6--) {
            for (int i7 = i1 + 1; i7 >= i1; i7--)
              a(this.s.a(i6, i7), i6, i7, 1, 1, 1); 
          } 
          if ((ay.a(2) == 0 && b1 > 0) || b1 > i3) {
            b1--;
            i2 = (i2 + 1 + ay.a(3)) % 4;
            n = i2 * 2 + 2;
            for (i6 = n + 1; i6 >= n; i6--) {
              for (int i7 = i1 + 1; i7 >= i1; i7--)
                a(this.s.a(i6, i7), i6, i7, 1, 1, 1); 
            } 
          } 
          i1 += 2;
        } 
        i3 = 3 + ay.a(3);
        h(i3, 4);
        break;
      case 2:
        a(bn2, bn1.m() * 5 / 2);
        n = 0;
        i1 = 2;
        for (i4 = 0; i4 < 2; i4++) {
          n = ay.a(5) + 2;
          for (int i6 = n + 3; i6 >= n; i6--) {
            for (int i7 = i1 + 3; i7 >= i1; i7--)
              a(this.s.a(i6, i7), i6, i7, 1, 1, 1); 
          } 
          i1 += 4;
        } 
        i3 = 2;
        h(i3, 16);
        break;
      case 3:
        a(bn2, bn1.m() * 8 / 10);
        for (i4 = ay.a(e.h.length);; i4 = (i4 + 1 + ay.a(e.h.length - 2)) % e.h.length) {
          byte b2 = 0;
          int[][] arrayOfInt1 = this.s.e();
          for (byte b3 = 2; b3 < 10; b3++) {
            for (byte b4 = 2; b4 < 10; b4++) {
              if (arrayOfInt1[b3][b4] == i4) {
                a(i4, b3, b4, 1, 1, 1);
                b2++;
              } 
            } 
          } 
          if (b2 > 0) {
            i3 = 4 + ay.a(5);
            h(i3, 1);
            break;
          } 
        } 
        break;
      case 4:
        this.I[j] = this.I[j] + 6;
        bn1.a(true);
        break;
      case 5:
        a(bn2, bn1.m() * 2);
        for (i5 = 0; i5 < 8; i5 += 4) {
          for (byte b2 = 0; b2 < 8; b2 += 4) {
            n = 2 + i5 + ay.a(2);
            i1 = 2 + b2 + ay.a(2);
            for (int i6 = n + 2; i6 >= n; i6--) {
              for (int i7 = i1 + 2; i7 >= i1; i7--)
                a(this.s.a(i6, i7), i6, i7, 1, 1, 1); 
            } 
          } 
        } 
        i3 = 3 + ay.a(3);
        h(i3, 9);
        break;
      case 6:
        a(bn2, bn1.m() * 8 / 10);
        bn2.a(8, false);
        b1 = 0;
        i3 = ay.a(2) + 2;
        while (b1 < i3) {
          i5 = ay.a(8) + 2;
          int i6 = ay.a(8) + 2;
          boolean bool = true;
          for (int i7 = this.z - 1; i7 >= 0; i7--) {
            if ((this.y[i7]).a == i5 && (this.y[i7]).b == i6) {
              bool = false;
              break;
            } 
          } 
          if (bool) {
            a(this.s.a(i5, i6), i5, i6, 1, 1, 1);
            b1++;
          } 
        } 
        break;
      case 7:
        b(bn1, bn1.s() / 10 * 2);
        for (i5 = 2; i5 < 10; i5++) {
          for (byte b2 = 2; b2 < 10; b2++) {
            if (this.s.a(i5, b2) == 2)
              a(2, i5, b2, 1, 1, 1); 
          } 
        } 
        break;
      case 8:
        a(bn2, bn1.m() * 3 / 2);
        bn2.a(18, false);
        b1 = 0;
        i3 = ay.a(3) + 3;
        while (b1 < i3) {
          i5 = ay.a(8) + 2;
          int i6 = ay.a(8) + 2;
          boolean bool = true;
          for (int i7 = this.z - 1; i7 >= 0; i7--) {
            if ((this.y[i7]).a == i5 && (this.y[i7]).b == i6) {
              bool = false;
              break;
            } 
          } 
          if (bool) {
            a(this.s.a(i5, i6), i5, i6, 1, 1, 1);
            b1++;
          } 
        } 
        if (!bn2.k()) {
          this.G += 2;
          if (this.J[k] > 0) {
            this.J[k] = this.J[k] + 2;
            break;
          } 
          this.J[k] = this.J[k] + 3;
        } 
        break;
    } 
    this.C = this.t.a(m, j, this.y, this.z);
    f(11);
    bs.a().a(m);
  }
  
  private void h(int paramInt1, int paramInt2) {
    int j = this.z / paramInt2;
    for (int k = 0; k < this.z; k += paramInt2) {
      int m = ay.a(j) * paramInt2;
      for (byte b1 = 0; b1 < paramInt2; b1++) {
        int n = k + b1;
        int i1 = m + b1;
        cc cc1 = this.y[n];
        this.y[n] = this.y[i1];
        this.y[i1] = cc1;
      } 
    } 
    if (paramInt1 < j)
      this.z = paramInt1 * paramInt2; 
  }
  
  private boolean y() {
    bn[] arrayOfBn = this.s.d();
    boolean bool = (arrayOfBn[0].r() > 0 && arrayOfBn[1].r() > 0) ? false : true;
    if (bool || this.t.o() == 1) {
      if (this.s.d) {
        this.E = true;
      } else if (bool) {
        if (arrayOfBn[0].r() <= 0) {
          this.E = false;
        } else {
          this.E = true;
        } 
      } else {
        switch (this.s.c()) {
          case 2:
            this.E = false;
            break;
          case 3:
            this.E = true;
            break;
        } 
      } 
      f(9);
      return true;
    } 
    return false;
  }
  
  public void e() {
    this.x = 0;
    int[][] arrayOfInt = this.s.e();
    int[] arrayOfInt1 = new int[12];
    int[] arrayOfInt2 = new int[12];
    byte b1;
    for (b1 = 2; b1 < 10; b1++)
      arrayOfInt1[b1] = 9; 
    b1 = 0;
    byte b2;
    for (b2 = 2; b2 < 10; b2++) {
      boolean bool = false;
      for (byte b3 = 9; b3 >= 2; b3--) {
        if (arrayOfInt[b3][b2] < 7) {
          if (b3 != arrayOfInt1[b2]) {
            this.w[this.x++] = arrayOfInt1[b2] << 8 | b2;
            arrayOfInt[arrayOfInt1[b2]][b2] = arrayOfInt[b3][b2];
            arrayOfInt[b3][b2] = 7;
            this.t.b(b3, b2, arrayOfInt1[b2], b2, arrayOfInt[arrayOfInt1[b2]][b2], arrayOfInt2[b2] + b1);
            arrayOfInt2[b2] = arrayOfInt2[b2] + 1;
            bool = true;
          } 
          arrayOfInt1[b2] = arrayOfInt1[b2] - 1;
        } 
      } 
      if (bool)
        b1++; 
    } 
    b1 = 0;
    for (b2 = 2; b2 < 10; b2++) {
      byte b3 = 0;
      if (arrayOfInt1[b2] >= 2) {
        for (int j = arrayOfInt1[b2]; j >= 2; j--) {
          this.w[this.x++] = j << 8 | b2;
          this.s.d(j, b2);
          this.t.b(b3, b2, j, b2, arrayOfInt[j][b2], arrayOfInt2[b2] + b1);
          arrayOfInt2[b2] = arrayOfInt2[b2] + 1;
          b3--;
          this.C = 10;
        } 
        b1++;
      } 
    } 
  }
  
  public void f() {
    this.z = 0;
    int[][] arrayOfInt = new int[12][12];
    for (byte b1 = 0; b1 < this.x; b1++) {
      int j = (this.w[b1] & 0xFF00) >> 8;
      int k = this.w[b1] & 0xFF;
      int m = 0;
      int n = 0;
      boolean bool1 = false;
      boolean bool2 = false;
      int i1 = 0;
      if ((arrayOfInt[j][k] & 0xFF) != 255) {
        m = this.s.e(j, k);
        bool1 = ((m & 0xFF) >= 3) ? true : false;
        if (bool1)
          i1 += m & 0xFF; 
        a(arrayOfInt, j, k - ((m & 0xFF0000) >> 16), m & 0xFF);
      } 
      if ((arrayOfInt[j][k] & 0xFF00) != 65280) {
        n = this.s.f(j, k);
        bool2 = ((n & 0xFF) >= 3) ? true : false;
        if (bool2)
          i1 += n & 0xFF; 
        b(arrayOfInt, j - ((n & 0xFF0000) >> 16), k, n & 0xFF);
      } 
      if (bool1)
        a(this.s.a(j, k), j, k - ((m & 0xFF0000) >> 16), 1, m & 0xFF, i1); 
      if (bool2)
        a(this.s.a(j, k), j - ((n & 0xFF0000) >> 16), k, n & 0xFF, 1, i1); 
      arrayOfInt[j][k] = 65535;
    } 
  }
  
  private void a(int[][] paramArrayOfint, int paramInt1, int paramInt2, int paramInt3) {
    int j = paramInt2 + paramInt3;
    for (int k = paramInt2; k < j; k++)
      paramArrayOfint[paramInt1][k] = paramArrayOfint[paramInt1][k] | 0xFF; 
  }
  
  private void b(int[][] paramArrayOfint, int paramInt1, int paramInt2, int paramInt3) {
    int j = paramInt1 + paramInt3;
    for (int k = paramInt1; k < j; k++)
      paramArrayOfint[k][paramInt2] = paramArrayOfint[k][paramInt2] | 0xFF00; 
  }
  
  private void a(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6) {
    b(e.i[paramInt1], paramInt2, paramInt3, paramInt4, paramInt5, paramInt6);
    if (paramInt4 >= 4 || paramInt5 >= 4)
      this.G++; 
  }
  
  private void b(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6) {
    cc cc1 = this.y[this.z];
    cc1.e = paramInt1;
    cc1.a = paramInt2;
    cc1.c = paramInt4;
    cc1.b = paramInt3;
    cc1.d = paramInt5;
    cc1.f = paramInt6;
    this.z++;
  }
  
  public void b(int paramInt) {
    if (this.v == 14) {
      this.t.g.f();
    } else if (this.v == 8 && this.s.a() == 0) {
      this.u[this.s.a()].a(paramInt);
    } 
  }
  
  public void a(int paramInt1, int paramInt2) {
    if (this.v == 14) {
      this.t.g.f();
    } else if (this.v == 8 && this.s.a() == 0) {
      this.u[this.s.a()].a(paramInt1, paramInt2);
    } 
  }
  
  public void g() {
    if (this.s.d) {
      ae ae1 = cz.a("Thông tin", "Bạn có muốn bỏ qua phần luyện tập không?", "Có", -6, "Không", -2, 1);
      ae1.a(this);
      this.t.a(false);
      cz.d().a(ae1, true);
      return;
    } 
    ae ae = cz.a("Thông tin", "Bạn muốn dừng game lại?", "Có", -1, "Không", -2, 1);
    ae.a(this);
    this.t.a(false);
    cz.d().a(ae, true);
  }
  
  public dl h() {
    return this.t.f;
  }
  
  public boolean a(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    if (this.s.d) {
      a a2 = (a)this.B.b(0);
      if ((a2.a != paramInt1 || a2.b != paramInt2 || a2.c != paramInt3 || a2.d != paramInt4) && (a2.a != paramInt3 || a2.b != paramInt4 || a2.c != paramInt1 || a2.d != paramInt2)) {
        this.t.g.e();
        f(14);
        return false;
      } 
    } 
    a a1 = this.s.a(paramInt1, paramInt2, paramInt3, paramInt4);
    this.A = a1;
    if (this.A == null) {
      this.A = new a();
      this.A.b = paramInt2;
      this.A.a = paramInt1;
      this.A.d = paramInt4;
      this.A.c = paramInt3;
    } 
    f(1);
    return (a1 != null);
  }
  
  public void i() {
    if (this.s.d) {
      if (this.t.g.g() == 6) {
        this.t.g.c();
        f(13);
        return;
      } 
      if (this.t.g.g() != 1) {
        this.t.g.e();
        f(14);
        return;
      } 
    } 
    if (this.s.h()) {
      this.s.a(false);
      this.t.e();
    } else {
      this.s.a(true);
      this.t.g();
      this.t.h();
    } 
  }
  
  public void c(int paramInt1, int paramInt2) {
    if (this.s.d && !this.s.h()) {
      this.t.g.e();
      f(14);
      return;
    } 
    this.s.c(paramInt1);
    this.s.d(paramInt2);
    this.s.a(false);
    this.t.e();
  }
  
  public boolean j() {
    return this.s.h();
  }
  
  public int k() {
    return this.s.f();
  }
  
  public int l() {
    return this.s.g();
  }
  
  public boolean m() {
    int j = this.s.f();
    int k = this.s.g();
    return b(j, k, j - 1, k);
  }
  
  public boolean n() {
    int j = this.s.f();
    int k = this.s.g();
    return b(j, k, j + 1, k);
  }
  
  public boolean o() {
    int j = this.s.f();
    int k = this.s.g();
    return b(j, k, j, k - 1);
  }
  
  public boolean p() {
    int j = this.s.f();
    int k = this.s.g();
    return b(j, k, j, k + 1);
  }
  
  public boolean b(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    if (this.s.d && !this.s.h()) {
      this.t.g.e();
      f(14);
      return false;
    } 
    if (!this.s.c(paramInt3, paramInt4))
      return false; 
    if (this.s.h())
      return a(paramInt1, paramInt2, paramInt3, paramInt4); 
    int j = this.s.f();
    int k = this.s.g();
    boolean bool = this.s.b(paramInt3, paramInt4);
    if (bool)
      this.t.a(j, k); 
    return bool;
  }
  
  public void a(a parama) {
    this.t.a(parama);
  }
  
  public di q() {
    return this.B;
  }
  
  public int d(int paramInt1, int paramInt2) {
    return this.s.a(paramInt1, paramInt2);
  }
  
  public int e(int paramInt1, int paramInt2) {
    int m;
    int n;
    int j = this.s.a();
    boolean bool = (j == 0) ? true : false;
    bn[] arrayOfBn = this.s.d();
    int k = 0;
    switch (paramInt1) {
      case 0:
      case 6:
        if (paramInt1 == 0) {
          k = 9;
        } else {
          k = 10;
        } 
        if (arrayOfBn[bool].r() <= paramInt2 * paramInt2 - 2 * paramInt2 + arrayOfBn[j].m())
          return 120; 
        k += e.j[paramInt2];
        return k;
      case 2:
        k = 4;
        m = arrayOfBn[j].r() * 100 / arrayOfBn[j].s();
        if (m <= 10) {
          k += 4;
        } else if (m <= 30) {
          k += 2;
        } 
        k += e.j[paramInt2];
        return k;
      case 1:
        k = 3;
        n = arrayOfBn[j].p() * 100 / arrayOfBn[j].q();
        if (n <= 10) {
          k += 4;
        } else if (n <= 30) {
          k += 2;
        } 
        k += e.j[paramInt2];
        return k;
      case 3:
        k = 2;
        k += e.j[paramInt2];
        return k;
    } 
    k = 1;
    k += e.j[paramInt2];
    return k;
  }
  
  public void c(int paramInt) {
    bn bn = this.s.b(this.s.a());
    bn.j(paramInt);
  }
  
  public void f(int paramInt1, int paramInt2) {}
  
  public void a(String paramString, int paramInt) {
    if (paramString.equals("skill")) {
      a(paramInt);
    } else {
      d(paramInt);
      this.K = true;
      if (this.s.d) {
        this.t.g.c();
        f(13);
        System.out.println("Usinggggggggggsssssssssssssssssssssssssssssssssss");
      } 
    } 
  }
  
  public void d(int paramInt) {
    bn bn = this.s.b(this.s.a());
    dx[] arrayOfDx = bn.a;
    for (byte b1 = 0; b1 < arrayOfDx.length; b1++) {
      if (paramInt == (arrayOfDx[b1]).a) {
        arrayOfDx[b1].a();
        if ((arrayOfDx[b1]).a == 1) {
          bn.n(bn.e() * 2);
          bn.l(bn.e());
          this.L = true;
          break;
        } 
        if ((arrayOfDx[b1]).a == 4) {
          b(bn, bn.s() * 1 / 10);
          this.t.d(this.s.a());
          break;
        } 
        if (paramInt == 3) {
          (arrayOfDx[b1]).b = 2;
          this.t.e(true);
          break;
        } 
        if (paramInt == 0) {
          (arrayOfDx[b1]).b = 1;
          this.t.d(true);
        } 
        break;
      } 
    } 
    bs.a().h();
  }
  
  public void r() {
    if (this.s.d && this.t.g.g() != 3)
      return; 
    int j = d();
    this.t.a(j, this, this.s.b(this.s.a()));
  }
  
  public void s() {
    int j = this.s.c();
    if (j == 1 && this.t.g.g() != 5)
      return; 
    if (this.K)
      return; 
    if (this.L)
      (this.s.a[1]).b = 0; 
    this.t.e.a(this.s.a, this);
  }
  
  public void b(int paramInt1, int paramInt2) {
    switch (paramInt2) {
      case -1:
        cz.d().a(false);
        u();
        cz.a().a(5, 2, true, false);
        break;
      case -2:
        this.t.a(true);
        cz.d().a(true);
        break;
      case -3:
        dd.j();
        cz.d().a(false);
        a();
        break;
      case -5:
      case -4:
        cz.d().a(false);
        cz.a().a(5, 7, true, true);
        break;
      case -6:
        cz.d().a(false);
        this.E = true;
        f(12);
        break;
    } 
  }
  
  public void t() {
    bn[] arrayOfBn = this.s.d();
    for (byte b1 = 0; b1 < arrayOfBn.length; b1++)
      arrayOfBn[b1].i(); 
    f.f();
    if (this.E) {
      dd.i();
    } else {
      dd.j();
    } 
  }
  
  public int a(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5) {
    if (paramInt1 != 0)
      return paramInt1; 
    int[][] arrayOfInt = this.s.e();
    int j;
    for (j = paramInt3 - paramInt4; j < paramInt3; j++) {
      if (arrayOfInt[paramInt2][j] == 6)
        return 6; 
    } 
    for (j = paramInt3 + paramInt5; j > paramInt3; j--) {
      if (arrayOfInt[paramInt2][j] == 6)
        return 6; 
    } 
    return 0;
  }
  
  public int b(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5) {
    if (paramInt1 != 0)
      return paramInt1; 
    int[][] arrayOfInt = this.s.e();
    int j;
    for (j = paramInt2 - paramInt4; j < paramInt2; j++) {
      if (arrayOfInt[j][paramInt3] == 6)
        return 6; 
    } 
    for (j = paramInt2 + paramInt5; j > paramInt2; j--) {
      if (arrayOfInt[j][paramInt3] == 6)
        return 6; 
    } 
    return 0;
  }
  
  public boolean e(int paramInt) {
    return (ay.a(100) <= this.s.b(paramInt).c());
  }
}


/* Location:              C:\Users\bot-nosense\Downloads\Loan-12-Su-Quan.jar!\bu.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */