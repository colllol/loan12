package com.mic.smsgame.suquan;

import ad;
import ae;
import aj;
import an;
import ao;
import ar;
import ay;
import az;
import bg;
import bk;
import bl;
import bo;
import bp;
import bq;
import bz;
import c;
import ca;
import cf;
import co;
import com.mg.smsgame.main.b;
import cp;
import cx;
import cz;
import d;
import da;
import dd;
import de;
import df;
import dg;
import di;
import dj;
import dk;
import dl;
import dq;
import ea;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import m;
import s;
import u;
import y;

public class aa extends aj implements b {
  private int f = -1;
  
  private Image t;
  
  private Image u;
  
  private Image v;
  
  private Image w;
  
  private int x;
  
  private int y;
  
  private bo[] z;
  
  private bl[] A;
  
  private int B = 0;
  
  private int C;
  
  private int D;
  
  private int E;
  
  private int F;
  
  private int G;
  
  private int H;
  
  private int I;
  
  private int J;
  
  private int K;
  
  private int L;
  
  private int M;
  
  private bp N;
  
  private ca O;
  
  private co P;
  
  private dq Q;
  
  private int R;
  
  private int[] S;
  
  private int T;
  
  private dl U;
  
  private int V;
  
  private int W;
  
  private int X;
  
  private int Y;
  
  private s Z;
  
  private boolean aa;
  
  private int ab;
  
  private int ac;
  
  private int ad;
  
  private int ae;
  
  private dq af;
  
  private int ag;
  
  private boolean ah = true;
  
  private boolean ai;
  
  private dk aj;
  
  private boolean ak;
  
  private boolean al;
  
  private boolean am;
  
  private int an;
  
  public static boolean c;
  
  private Image ao;
  
  private int ap;
  
  private boolean[] aq;
  
  private bk ar;
  
  public static boolean d = false;
  
  public static boolean e = false;
  
  public aa() {
    System.gc();
    c = false;
    c(false);
    a(null);
    a(this);
    this.t = az.b("/m/hm");
    this.u = az.b("/m/m");
    this.v = az.b("/m/b");
    this.w = az.b("/m/r");
    this.x = this.w.getWidth() / 4;
    this.y = this.w.getHeight();
    ((ar)this).b_ = this.u.getWidth();
    ((ar)this).c_ = this.u.getHeight();
    this.U = new dl(0, 0, cx.f, cx.g);
    this.Q = new dq(az.b("/m/mt1"), 3);
    this.Q.c(3);
    this.Q.b(true);
    this.Q.a(new byte[][] { { 0, 1, 2, 1 } });
    this.Q.a_(0);
    this.af = new dq(az.b("/m/ar"), 6);
    this.af.c(1);
    this.af.b(true);
    this.af.a(new byte[][] { { 
            0, 1, 2, 3, 4, 5, 5, 4, 3, 2, 
            1 } });
    this.af.a_(0);
    u.a(null);
    this.ak = false;
    this.al = false;
    this.am = false;
    this.ar = new bk("/m/vk", cx.f - 55, 0);
    this.ap = bg.m();
    this.ao = az.b("/m/l");
    this.ah = false;
    this.ag = 0;
    u();
    v();
    D();
    f(7);
    this.aj = new dk("vs");
    cf.a().a("m", -1);
    cf.a().a(180184);
    cf.a().e();
    ((ar)this).q = 7;
  }
  
  private void u() {
    this.z = new bo[bq.a.length];
    byte b1;
    for (b1 = 0; b1 < this.z.length; b1++) {
      this.z[b1] = new bo();
      (this.z[b1]).b = bq.b[b1][0];
      (this.z[b1]).c = bq.b[b1][1];
      (this.z[b1]).a = bq.b[b1][2];
      (this.z[b1]).d = false;
    } 
    (this.z[0]).d = true;
    (this.z[0]).e = 0;
    b1 = 0;
    int i = dd.f();
    while (b1 < i) {
      (this.z[dd.q(b1)]).f = b1;
      b1++;
    } 
    this.A = new bl[bq.d.length];
    for (b1 = 0; b1 < this.A.length; b1++) {
      this.A[b1] = new bl();
      (this.A[b1]).a = bq.d[b1][0];
      (this.A[b1]).b = bq.d[b1][1];
      (this.A[b1]).c = false;
    } 
    this.S = new int[bq.a()];
    for (b1 = 0; b1 < this.S.length; b1++)
      this.S[b1] = dd.z(b1); 
    this.B = 0;
    this.D = -1;
    this.C = -1;
    this.R = dd.a();
    this.T = 0;
    this.ae = ay.a(3, 10);
    this.W = dd.b();
    this.X = bg.f(2);
    this.ai = false;
    this.aq = new boolean[(dd.k()).length];
    for (b1 = 0; b1 < this.aq.length; b1++)
      this.aq[b1] = false; 
    this.aq[1] = true;
    this.aq[0] = true;
    this.ab = -1;
    this.ac = -1;
  }
  
  private void a(int[] paramArrayOfint) {
    if (paramArrayOfint == null)
      return; 
    byte b1;
    for (b1 = 0; b1 < paramArrayOfint.length; b1++)
      (this.z[paramArrayOfint[b1]]).d = true; 
    for (b1 = 0; b1 < this.A.length; b1++) {
      if (!(this.A[b1]).c && (this.z[(this.A[b1]).a]).d && (this.z[(this.A[b1]).b]).d)
        (this.A[b1]).c = true; 
    } 
  }
  
  private void v() {
    if (!f.c()) {
      dd.e = dd.t(dd.d());
      return;
    } 
    try {
      byte[] arrayOfByte = f.b();
      ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(arrayOfByte);
      DataInputStream dataInputStream = new DataInputStream(byteArrayInputStream);
      this.B = dataInputStream.readInt();
      this.E = dataInputStream.readByte();
      this.D = dataInputStream.readInt();
      this.T = dataInputStream.readInt();
      byte b1;
      for (b1 = 0; b1 < this.z.length; b1++) {
        (this.z[b1]).d = dataInputStream.readBoolean();
        (this.z[b1]).e = dataInputStream.readByte();
        (this.z[b1]).f = dataInputStream.readByte();
        this.S[b1] = dataInputStream.readByte();
      } 
      for (b1 = 0; b1 < this.A.length; b1++)
        (this.A[b1]).c = dataInputStream.readBoolean(); 
      this.ae = dataInputStream.readByte();
      this.L = dataInputStream.readByte();
      this.M = dataInputStream.readByte();
      this.J = dataInputStream.readInt();
      this.K = dataInputStream.readInt();
      this.F = dataInputStream.readInt();
      this.G = dataInputStream.readInt();
      c c = new c();
      c.e = dataInputStream.readByte();
      c.c = dataInputStream.readInt();
      c.g = dataInputStream.readInt();
      c.h = dataInputStream.readInt();
      dd.b = dataInputStream.readInt();
      this.ai = dataInputStream.readBoolean();
      for (byte b2 = 0; b2 < this.aq.length; b2++)
        this.aq[b2] = dataInputStream.readBoolean(); 
      if (dd.e == null) {
        dd.e = dd.t(dd.d());
        dd.e.e = c.e;
        dd.e.c = c.c;
        dd.e.h = c.h;
        dd.e.g = c.g;
        dd.e.b();
      } 
      dataInputStream.close();
    } catch (IOException iOException) {
      iOException.printStackTrace();
    } 
  }
  
  private void w() {
    try {
      ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
      DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
      dataOutputStream.writeInt(this.B);
      dataOutputStream.writeByte(this.E);
      dataOutputStream.writeInt(this.D);
      dataOutputStream.writeInt(this.T);
      byte b1;
      for (b1 = 0; b1 < this.z.length; b1++) {
        dataOutputStream.writeBoolean((this.z[b1]).d);
        dataOutputStream.writeByte((this.z[b1]).e);
        dataOutputStream.writeByte((this.z[b1]).f);
        dataOutputStream.writeByte(this.S[b1]);
      } 
      for (b1 = 0; b1 < this.A.length; b1++)
        dataOutputStream.writeBoolean((this.A[b1]).c); 
      dataOutputStream.writeByte(this.ae);
      dataOutputStream.writeByte(this.L);
      dataOutputStream.writeByte(this.M);
      dataOutputStream.writeInt(this.J);
      dataOutputStream.writeInt(this.K);
      dataOutputStream.writeInt(this.F);
      dataOutputStream.writeInt(this.G);
      dataOutputStream.writeByte(dd.e.e);
      dataOutputStream.writeInt(dd.e.c);
      dataOutputStream.writeInt(dd.e.g);
      dataOutputStream.writeInt(dd.e.h);
      dataOutputStream.writeInt(dd.b);
      dataOutputStream.writeBoolean(this.ai);
      for (b1 = 0; b1 < this.aq.length; b1++)
        dataOutputStream.writeBoolean(this.aq[b1]); 
      dataOutputStream.flush();
      f.a(byteArrayOutputStream.toByteArray());
      dataOutputStream.close();
    } catch (IOException iOException) {
      iOException.printStackTrace();
    } 
  }
  
  private void x() {
    if (this.L == 0) {
      if (this.D < 0)
        return; 
      switch (this.D) {
        case 0:
          this.C = 1;
          break;
        case 1:
          this.C = 0;
          break;
        case 2:
          this.C = 3;
          break;
        case 3:
          this.C = 2;
          break;
      } 
      if (this.C >= 0) {
        this.Y = bq.c[this.B][this.C];
        if (this.Y >= 0 && (this.A[this.Y]).c) {
          this.ae++;
          f(1);
        } 
      } else {
        f(0);
      } 
    } else {
      this.J = -this.J;
      this.K = -this.K;
      this.E = this.B;
      this.ae++;
      f(1);
    } 
  }
  
  private void a(Graphics paramGraphics, bo parambo1, bo parambo2) {
    if (this.U.a(parambo1.b + ((ar)this).g, parambo1.c + ((ar)this).a_) || this.U.a(parambo2.b + ((ar)this).g, parambo2.c + ((ar)this).a_)) {
      int i = dg.a(parambo1.b);
      int j = dg.a(parambo1.c);
      int k = dg.a(parambo2.b) - i;
      int m = dg.a(parambo2.c) - j;
      int n = dg.c(dg.a(k, k) + dg.a(m, m));
      int i1 = dg.b(dg.a(dg.a(10), k), n);
      int i2 = dg.b(dg.a(dg.a(10), m), n);
      int i3 = dg.b(dg.b(n, dg.a(10)));
      for (byte b1 = 0; b1 < i3; b1++) {
        i += i1;
        j += i2;
        y.a(paramGraphics, this.w, 0, 0, this.x, this.y, dg.b(i) + ((ar)this).g, dg.b(j) + ((ar)this).a_, 3);
      } 
    } 
  }
  
  private void b(Graphics paramGraphics) {
    byte b1;
    for (b1 = 0; b1 < this.A.length; b1++) {
      if ((this.A[b1]).c)
        a(paramGraphics, this.z[(this.A[b1]).a], this.z[(this.A[b1]).b]); 
    } 
    for (b1 = 0; b1 < this.z.length; b1++) {
      if ((this.z[b1]).d)
        if ((this.z[b1]).a == 1) {
          if (this.S[b1] < 0) {
            y.a(paramGraphics, this.w, 9, 0, this.x, this.y, ((ar)this).g + (this.z[b1]).b, ((ar)this).a_ + (this.z[b1]).c, 3);
          } else {
            y.a(paramGraphics, this.w, 18, 0, this.x, this.y, ((ar)this).g + (this.z[b1]).b, ((ar)this).a_ + (this.z[b1]).c, 3);
          } 
        } else if ((this.z[b1]).a == 0) {
          y.a(paramGraphics, this.w, 27, 0, this.x, this.y, ((ar)this).g + (this.z[b1]).b, ((ar)this).a_ + (this.z[b1]).c, 3);
        }  
    } 
  }
  
  private boolean h(int paramInt1, int paramInt2) {
    byte b1;
    byte b2;
    int i = (cx.f >> 1) - ((ar)this).g;
    int j = (cx.g >> 1) - ((ar)this).a_;
    int k = cx.f >> 1;
    int m = cx.g >> 1;
    if (paramInt1 > ((ar)this).b_ - k)
      paramInt1 = ((ar)this).b_ - k; 
    if (paramInt1 < k)
      paramInt1 = k; 
    if (paramInt2 > ((ar)this).c_ - m)
      paramInt2 = ((ar)this).c_ - m; 
    if (paramInt2 < m)
      paramInt2 = m; 
    if (paramInt1 - i > 4 || paramInt1 - i < 4) {
      b1 = (paramInt1 - i) / 4;
    } else if (paramInt1 == i) {
      b1 = 0;
    } else {
      b1 = (paramInt1 - i > 0) ? 1 : -1;
    } 
    if (paramInt2 - j > 4 || paramInt2 - j < 4) {
      b2 = (paramInt2 - j) / 4;
    } else if (paramInt2 == j) {
      b2 = 0;
    } else {
      b2 = (paramInt2 - j > 0) ? 1 : -1;
    } 
    if (!b1 && !b2)
      return false; 
    ((ar)this).g -= b1;
    ((ar)this).a_ -= b2;
    return true;
  }
  
  public void a(int paramInt) {
    if (this.f == 1)
      return; 
    switch (this.f) {
      case 0:
        this.C = -1;
        this.Y = -1;
        switch (paramInt) {
          case 14:
            this.C = 2;
            break;
          case 15:
            this.C = 3;
            break;
          case 12:
            this.C = 0;
            break;
          case 13:
            this.C = 1;
            break;
        } 
        if (this.C >= 0) {
          this.Y = bq.c[this.B][this.C];
          if (this.Y >= 0 && (this.A[this.Y]).c)
            f(1); 
        } 
        break;
      case 9:
        if (this.P != null)
          this.P.a(paramInt); 
        break;
    } 
  }
  
  private boolean e(int paramInt) {
    bo bo1 = this.z[paramInt];
    return (bo1.e >= 0) ? true : ((bo1.f >= 0) ? true : ((this.S[paramInt] >= 0)));
  }
  
  private void y() {
    this.O = null;
    switch (this.R) {
      case 0:
        if ((this.z[this.B]).e >= 0) {
          int i = dd.d((this.z[this.B]).e);
          switch (i) {
            case 0:
              this.R = 2;
              f(2);
              dd.a(this.R);
              break;
          } 
          dd.f = dd.h((this.z[this.B]).e);
          f(3);
          break;
        } 
        if ((this.z[this.B]).f >= 0) {
          int i = dd.p((this.z[this.B]).f);
          switch (i) {
            case 0:
              this.R = 2;
              f(2);
              dd.a(this.R);
              break;
          } 
          dd.f = dd.s((this.z[this.B]).f);
          f(3);
          break;
        } 
        if (this.S[this.B] >= 0) {
          dd.f = dd.a(this.B, dd.e.e);
          f(3);
        } 
        break;
      case 2:
        f(5);
        break;
      case 3:
        f(6);
        break;
    } 
  }
  
  private void z() {
    ae ae = cz.a("Chúc mừng", "Bạn đã mở thêm được 1 mức luyện tập tại đấu trường. Hãy vào đó thử sức!", "Đồng ý", 5, 1);
    ae.a(this);
    cz.d().a(ae, true);
  }
  
  private void A() {
    this.R = 0;
    dd.a(this.R);
    this.W = dd.b();
    int i = 0;
    this.ab = -1;
    this.ac = -1;
    if (this.L == 0) {
      if ((this.z[this.B]).e >= 0) {
        this.W += dd.i((this.z[this.B]).e);
        i += dd.j((this.z[this.B]).e);
        this.ak = dd.f((this.z[this.B]).e);
        this.am = dd.g((this.z[this.B]).e);
        if (this.T == 0)
          e = true; 
        C();
      } else if ((this.z[this.B]).f >= 0) {
        dd.e.h += dd.n((this.z[this.B]).f);
        dd.e.g += dd.o((this.z[this.B]).f);
        dd.e.c += dd.o((this.z[this.B]).f);
        dd.e.b();
        if (dd.r((this.z[this.B]).f) == 9) {
          if (!this.aq[2]) {
            this.aq[2] = true;
            this.al = true;
          } 
        } else if (dd.r((this.z[this.B]).f) == 14 && !this.aq[3]) {
          this.aq[3] = true;
          this.al = true;
        } 
        (this.z[this.B]).f = -1;
      } 
      this.S[this.B] = -1;
    } 
    dd.b(this.W);
    bg.a(2, i, 6);
    this.X += i;
    cz.a().a(true);
    if (dd.c(dd.e.e + 1, this.W)) {
      while (dd.c(dd.e.e + 1, this.W))
        dd.e.a(); 
      f(7);
    } else if (this.L > 0) {
      f(1);
    } else {
      f(7);
    } 
    dd.k = false;
    dd.e.b();
    dd.j = 0;
    dd.i = 0;
    if (dd.e.e >= 30 && !this.aq[4]) {
      this.aq[4] = true;
      this.al = true;
    } 
    if (this.T >= dd.e()) {
      f(10);
    } else if (this.L == 0) {
      w();
    } 
  }
  
  private void B() {
    this.R = 0;
    dd.a(this.R);
    this.W = dd.b();
    x();
    dd.k = false;
    cz.a().a(true);
    if (this.L == 0)
      w(); 
  }
  
  private void C() {
    if (this.T < dd.e()) {
      a(dd.l((this.z[this.B]).e));
      (this.z[this.B]).e = -1;
      (this.z[dd.e(this.T)]).e = ++this.T;
      d = true;
      D();
    } 
  }
  
  private void D() {
    if (!d) {
      this.ab = -1;
      this.ac = -1;
    } else if (this.T >= 0 && this.T < dd.e()) {
      this.ab = (this.z[dd.e(this.T)]).b;
      this.ac = (this.z[dd.e(this.T)]).c;
      if (this.U.b(((ar)this).g + this.ab, ((ar)this).a_ + this.ac)) {
        this.ab = -1;
        this.ac = -1;
      } 
    } 
  }
  
  private void f(int paramInt) {
    String[][] arrayOfString;
    E();
    this.f = paramInt;
    switch (paramInt) {
      case 7:
        if (this.ab >= 0 && this.ac >= 0)
          f(8); 
        if (this.L == 0) {
          this.F = (this.z[this.B]).b;
          this.G = (this.z[this.B]).c;
        } 
        this.V = 20;
        break;
      case 0:
        a(this.ar);
        if (this.L > 0) {
          if (this.R == 1) {
            this.R = 0;
            dd.a(this.R);
            this.L = 0;
            this.F = (this.z[this.B]).b;
            this.G = (this.z[this.B]).c;
            f(0);
            break;
          } 
          if (this.R == 2) {
            f(5);
            break;
          } 
          if (this.R == 3) {
            f(6);
            break;
          } 
          f(1);
          break;
        } 
        if (this.R == 1) {
          this.R = 0;
          dd.a(this.R);
          if (this.D >= 0) {
            x();
            break;
          } 
          f(0);
          break;
        } 
        a((de)new da("C.Hàng", 0));
        c((de)new da("T.Tin", 8));
        b((de)new da("Thoát", 7));
        if (e(this.B)) {
          dd.d = (this.z[this.B]).f;
          dd.c = (this.z[this.B]).e;
          f(2);
          break;
        } 
        if (this.B == 24) {
          this.R = 0;
          dd.a(this.R);
          f(9);
        } 
        break;
      case 2:
        this.O = null;
        arrayOfString = (String[][])null;
        if ((this.z[this.B]).e >= 0) {
          switch (this.R) {
            case 0:
              arrayOfString = ea.b[(this.z[this.B]).e];
              break;
            case 2:
              arrayOfString = ea.c[(this.z[this.B]).e];
              break;
            case 3:
              arrayOfString = ea.d[(this.z[this.B]).e];
              break;
          } 
        } else if ((this.z[this.B]).f >= 0) {
          switch (this.R) {
            case 0:
              arrayOfString = ea.e[(this.z[this.B]).f];
              break;
            case 2:
              arrayOfString = ea.f[(this.z[this.B]).f];
              break;
            case 3:
              arrayOfString = ea.g[(this.z[this.B]).f];
              break;
          } 
        } 
        if (arrayOfString != null) {
          b((de)new da("Bỏ qua", 3));
          c((de)new da("Tiếp", 13));
          this.O = new ca(arrayOfString);
          break;
        } 
        y();
        break;
      case 8:
        this.aa = true;
        this.ad = 15;
        a(null);
        c(null);
        b((de)new da("Bỏ qua", 6));
        break;
      case 1:
        d = false;
        this.H = this.F * 10000;
        this.I = this.G * 10000;
        if (this.ah) {
          this.ag = 0;
          this.ah = false;
        } else {
          this.ag = 75;
        } 
        if (this.L <= 0) {
          this.D = this.C;
          this.E = (this.A[this.Y]).a;
          if (this.E == this.B)
            this.E = (this.A[this.Y]).b; 
          this.H = this.F * 10000;
          this.I = this.G * 10000;
          long l1 = (((this.z[this.E]).b - this.F) * 10000);
          long l2 = (((this.z[this.E]).c - this.G) * 10000);
          long l3 = m.a(l1 * l1 + l2 * l2);
          this.J = (int)(2L * l1 * 10000L / l3);
          this.K = (int)(2L * l2 * 10000L / l3);
          this.L = (int)(l3 / 20000L);
          this.M = this.L >> 1;
          this.ae--;
        } 
        break;
      case 3:
        this.Z = new s();
        this.Z.d(this.F, this.G);
        this.Z.a();
        if (bg.f(5) > 0) {
          dd.h = true;
          bg.g(5);
        } else if (dd.e.e > dd.f.e) {
          dd.h = true;
        } else if (dd.e.e < dd.f.e) {
          dd.h = false;
        } else {
          dd.h = (ay.a(2) == 0);
        } 
        this.N = new bp(dd.e, dd.f);
        this.aj.b();
        break;
      case 9:
        this.P = new co(this.aq);
        if (this.P.a()) {
          a((de)new da("Đánh", 2));
          c((de)new da(" ", 2));
        } 
        b((de)new da("Rút lui", 4));
        break;
      case 6:
        B();
        break;
    } 
  }
  
  private void E() {
    a(null);
    c(null);
    b(null);
    dd.a = -1;
    switch (this.f) {
      case 2:
        this.O = null;
        break;
      case 3:
        this.N = null;
        this.Z = null;
        break;
      case 9:
        this.P = null;
        break;
    } 
  }
  
  public void b() {
    ad ad = new ad(dd.e, this.W, this.X);
    ad.a(this);
    ad.c((de)new da("Đóng", 5));
    cz.d().a((ae)ad, true);
  }
  
  private void F() {
    dd.c = this.T;
    if (this.P != null) {
      dd.b = dd.e.c;
      dd.e.c = dd.e.b;
      this.P.b();
    } 
    u.a(null);
    (cz.d()).e.a(7, 5, true, true);
    cf.a().d();
    this.aj.a();
  }
  
  public void a() {
    String str;
    if (this.ak) {
      this.ak = false;
      this.an = bg.t();
      if (this.an >= 0) {
        String str1 = "Bạn đã nhận được 1 vật phẩm. Bạn có muốn mở vật phẩm ngay không? (Tham khảo giá tại http://free.ola.vn.)";
        if (this.am)
          str1 = "Bạn đã nhận được một vật phẩm. Bạn có muốn mở hay không? (*Chú ý* Đây là vật phẩm cuối cùng. Hãy mở hết các vật phẩm còn lại trước khi chọn Chơi mới)"; 
        ae ae = cz.a("Chúc mừng", str1, "Có", 11, "Không", 5, 1);
        ae.a(this);
        cz.d().a(ae, true);
      } 
    } 
    if (this.al) {
      this.al = false;
      z();
    } 
    this.Q.e();
    if (this.ag == 0)
      this.af.e(); 
    switch (this.f) {
      case 7:
        this.V--;
        if (this.V <= 0)
          f(0); 
        break;
      case 0:
        if (e) {
          e = false;
          G();
        } 
        if (this.ag > 0)
          this.ag--; 
        break;
      case 1:
        this.H += this.J;
        this.I += this.K;
        this.F = this.H / 10000;
        this.G = this.I / 10000;
        this.L--;
        if (this.ae == 0 && this.L == this.M) {
          dd.d = -1;
          dd.c = -1;
          this.ae = ay.a(3, 10);
          dd.f = dd.x(this.Y);
          f(3);
        } 
        if (this.L <= 0) {
          this.M = -1;
          this.B = this.E;
          f(0);
        } 
        break;
      case 8:
        if (this.aa) {
          this.aa = h(this.ab, this.ac);
          break;
        } 
        this.ad--;
        if (this.ad <= 0)
          f(0); 
        break;
      case 3:
        if (this.Z != null) {
          this.Z.b();
          if (this.Z.c()) {
            this.Z = null;
            a((de)new da("Đánh", 2));
            c((de)new da(" ", 2));
            if (this.D >= 0)
              b((de)new da("Rút lui", 4)); 
          } 
          break;
        } 
        if (this.N != null)
          this.N.a(); 
        break;
      case 9:
        if (!this.ai) {
          this.ai = true;
          dj dj = dj.a("Đấu trường", "Từ bây giờ, bạn có thể vào đấu trường để luyện điểm kinh nghiệm.@Nếu bạn đánh thắng, bạn sẽ được một số điểm kinh nghiệm tùy theo cấp độ hiện tại.@Nếu thua, bạn sẽ bị mất 1 mạng chơi, nhưng không bị trừ điểm kinh nghiệm.@*Lưu ý:@*-Bạn phải trả vàng để có thể tham gia đấu trường.@*-Vàng và điểm kinh nghiệm thu thập được trong quá trình đánh sẽ không có hiệu lực.@*-Máu của bạn sẽ không bị ảnh hưởng khi tham gia đấu trường.@*-Hiện tại chỉ có 2 mức luyện tập, hãy tìm khám phá để unlock thêm 3 mức luyện tập cao hơn.", null, null, null, null);
          dj.c((de)new da("Đồng ý", 5));
          dj.a(this);
          dj.a(null);
          cz.d().a((ae)dj, true);
        } 
        if (this.P != null)
          this.P.c(); 
        break;
      case 2:
        if (this.O != null)
          this.O.b(); 
        break;
      case 5:
        str = "";
        if (this.L == 0)
          if (dd.k((this.z[this.B]).e)) {
            int i = dd.i((this.z[this.B]).e);
            int j = dd.j((this.z[this.B]).e);
            str = String.valueOf(str) + "Bạn đã hoàn thành nhiệm vụ. Bạn được thưởng thêm {e} kinh nghiệm, {g} vàng.";
            str = ao.a(str, "{e}", i);
            str = ao.a(str, "{g}", j);
          } else if (dd.m((this.z[this.B]).f)) {
            int i = dd.o((this.z[this.B]).f);
            int j = dd.n((this.z[this.B]).f);
            str = String.valueOf(str) + "Bạn đã hoàn thành sự kiện. Bạn được thưởng thêm ";
            boolean bool = false;
            if (i > 0) {
              str = String.valueOf(str) + "{h} máu";
              bool = true;
            } 
            if (j > 0) {
              if (bool)
                str = String.valueOf(str) + ", "; 
              str = String.valueOf(str) + "{a} tấn công";
            } 
            str = ao.a(str, "{h}", i);
            str = ao.a(str, "{a}", j);
          }  
        if (!str.equals("")) {
          ae ae = cz.a("Chúc mừng", str, "Đồng ý", 9, 1);
          ae.a(this);
          cz.d().a(ae, true);
          break;
        } 
        A();
        break;
      case 10:
        cz.d().a((aj)new df(false), false);
        break;
    } 
    if (this.f != 8)
      h(this.F, this.G); 
  }
  
  private void G() {
    di di = new di();
    cp cp = new cp(az.b("/m/mt1"), 3);
    cp.a(new byte[][] { { 0, 1, 2, 1 } });
    cp.a(4);
    cp.a(true);
    cp.b(0);
    di.a(cp);
    dj dj = dj.a("Hướng dẫn", "- Địa điểm có nhiệm vụ sẽ có dấu hiệu nhận biết:@{i}@- Di chuyển nhân vật bằng cách bấm các phím 2, 4, 6, 8 tương ứng với hướng muốn đi trên bản đồ.@*Hãy vào của hàng để mua thêm vật phẩm hỗ trợ, giúp tăng thêm sức tấn công, và máu cho bạn.", null, di, null, null);
    dj.c((de)new da("Đóng", 5));
    dj.a(this);
    cz.d().a((ae)dj, true);
  }
  
  private void e(Graphics paramGraphics) {
    int i = 0;
    int j = 0;
    int k = this.v.getWidth();
    int m = this.v.getHeight();
    while (j < cx.g) {
      for (i = 0; i < cx.f; i += k)
        paramGraphics.drawImage(this.v, i, j, 0); 
      j += m;
    } 
  }
  
  public void a(Graphics paramGraphics) {
    paramGraphics.setClip(0, 0, cx.f, cx.g);
    paramGraphics.drawImage(this.u, ((ar)this).g, ((ar)this).a_, 0);
    b(paramGraphics);
    for (byte b1 = 0; b1 < this.z.length; b1++) {
      if ((this.z[b1]).d && (this.z[b1]).e != -1)
        this.Q.a(paramGraphics, ((ar)this).g + (this.z[b1]).b, ((ar)this).a_ + (this.z[b1]).c - 27); 
    } 
    paramGraphics.drawImage(this.t, ((ar)this).g + this.F, ((ar)this).a_ + this.G, 3);
    paramGraphics.drawImage(this.ao, 1, 2, 0);
    bz.d.a(paramGraphics, "x" + this.ap, 13, 1, 0);
    switch (this.f) {
      case 0:
        if (cx.n) {
          this.ar.a(((ar)this).g + this.F - (this.ar.a() >> 1), ((ar)this).a_ + this.G - (this.ar.b() >> 1));
          this.ar.a(paramGraphics);
        } 
      case 7:
        if (this.ag == 0)
          this.af.a(paramGraphics, ((ar)this).g + this.F - 5, ((ar)this).a_ + this.G - 20); 
        break;
      case 2:
        e(paramGraphics);
        if (this.O != null)
          this.O.a(paramGraphics); 
        break;
      case 3:
        if (this.Z != null) {
          this.Z.a(paramGraphics, ((ar)this).g, ((ar)this).a_);
          break;
        } 
        e(paramGraphics);
        if (this.N != null)
          this.N.a(paramGraphics); 
        break;
      case 9:
        e(paramGraphics);
        if (this.P != null)
          this.P.a(paramGraphics); 
        break;
    } 
  }
  
  public void b(int paramInt1, int paramInt2) {
    ae ae1;
    ae ae2;
    switch (paramInt2) {
      case 0:
        u.a(null);
        cz.a().a(7, 4, true, true);
        break;
      case 1:
        cz.d().a(false);
        u.a(null);
        cz.a().a(7, 2, true, true);
        break;
      case 3:
        y();
        break;
      case 6:
        this.ab = this.ac = -1;
        f(0);
        break;
      case 2:
        if (!bg.l()) {
          ae ae = cz.a("Chú ý", "Bạn đã hết mạng chơi. Bạn có muốn mua thêm mạng chơi không?. Tham khảo giá tại http://free.ola.vn.", "Có", 12, "Không", 14, 1);
          ae.a(this);
          cz.d().a(ae, true);
          break;
        } 
        F();
        break;
      case 4:
        if (this.L == 0) {
          x();
          break;
        } 
        this.J = -this.J;
        this.K = -this.K;
        this.E = this.B;
        f(1);
        break;
      case 5:
        cz.d().a(true);
        break;
      case 7:
        ae1 = cz.a("Chú ý", "Bạn có muốn trở về menu hay không?", "Có", 1, "Không", 5, 1);
        ae1.a(this);
        cz.d().a(ae1, true);
        break;
      case 8:
        b();
        break;
      case 9:
        if (dd.m(dd.d)) {
          A();
          break;
        } 
        ae2 = dd.a(true);
        ae2.a(this);
        ae2.c((de)new da("Đóng", 10));
        cz.d().a(true);
        cz.d().a(ae2, true);
        break;
      case 10:
        cz.d().a(true);
        A();
        break;
      case 11:
        c = true;
        bg.e(this.an);
        break;
      case 12:
        cz.d().a(false);
        an.b("", cx.G, new ae(this));
        break;
      case 13:
        if (!this.O.a())
          y(); 
        break;
      case 14:
        cz.d().a(false);
        break;
    } 
  }
  
  public void e() {
    long l = System.currentTimeMillis();
    if (this.f != 10)
      w(); 
    d.a("DONE SAVEMAP." + (System.currentTimeMillis() - l));
  }
  
  static void a(aa paramaa) {
    paramaa.F();
  }
}


/* Location:              C:\Users\bot-nosense\Downloads\Loan-12-Su-Quan.jar!\com\mic\smsgame\suquan\aa.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */