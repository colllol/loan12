import com.mg.smsgame.main.MGMIDlet;
import com.mg.smsgame.main.b;
import com.mic.smsgame.suquan.b;
import com.mic.smsgame.suquan.f;
import com.mic.smsgame.suquan.h;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class df extends aj implements b {
  private int c = -1;
  
  private static final int[][] d = new int[][] { 
      null, null, null, null, null, null, null, null, null, null, 
      null, null, null, null, null, null, null, null, null, null, 
      null, null, null, null, null, null, null, null, null, null, 
      null, null, null, null, null, null, null, null, null, null, 
      null, null, { 60, 338 }, { 186, 229 }, { 303, 287 }, { 103, 76 }, { 148, 116 }, { 108, 183 }, { 265, 160 }, { 318, 107 }, 
      { 243, 205 }, { 178, 170 }, { 29, 88 }, { 313, 228 }, { 170, 295 } };
  
  private Image e;
  
  private Image f;
  
  private Image t;
  
  private int u;
  
  private int v;
  
  private int w;
  
  private int x;
  
  private cl y;
  
  private dl z;
  
  private dl A;
  
  private dl B;
  
  private dl C;
  
  private int D;
  
  private b[] E;
  
  private int F;
  
  private boolean G;
  
  private boolean H = false;
  
  private int I;
  
  private int J;
  
  private dq K;
  
  public df(boolean paramBoolean) {
    a(this);
    a((h)null);
    this.b_ = 240;
    this.c_ = 320;
    this.g = cx.f - this.b_ >> 1;
    this.a_ = cx.g - this.c_ >> 1;
    this.t = az.b("/m/m");
    this.w = this.t.getWidth();
    this.x = this.t.getHeight();
    this.A = new dl(this.g + 20, this.a_ + 30, 200, 130);
    this.C = new dl(this.A.a + 100, this.A.b + 65, 0, 0);
    this.B = new dl(this.g, this.a_ + 165, this.b_, cx.g - 20 - this.a_ + 165);
    this.z = new dl();
    this.K = new dq(az.b("/m/ar"), 6);
    this.K.c(1);
    this.K.b(true);
    this.K.a(new byte[][] { { 
            0, 1, 2, 3, 4, 5, 5, 4, 3, 2, 
            1 } });
    this.K.a_(0);
    if (paramBoolean) {
      e(3);
    } else {
      e(0);
    } 
    if (paramBoolean) {
      cf.a().a("intro", -1);
      cf.a().a(120000);
    } else {
      cf.a().a("victory", -1);
      cf.a().a(120000);
    } 
    cf.a().e();
  }
  
  private boolean a(int paramInt1, int paramInt2, dl paramdl) {
    byte b1;
    byte b2;
    int i = (paramdl.c >> 1) + paramdl.a - this.u - this.g;
    int j = (paramdl.d >> 1) + paramdl.b - this.v - this.a_ + 15;
    int k = paramdl.c >> 1;
    int m = paramdl.d >> 1;
    if (paramInt1 > this.w - k)
      paramInt1 = this.w - k; 
    if (paramInt1 < k)
      paramInt1 = k; 
    if (paramInt2 > this.x - m)
      paramInt2 = this.x - m; 
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
    System.out.println(">>" + b1 + " " + b2);
    this.u -= b1;
    this.v -= b2;
    System.out.println(">>>>" + this.u + " " + this.v);
    return true;
  }
  
  private void e(int paramInt) {
    String[] arrayOfString;
    String str;
    byte b1;
    this.c = paramInt;
    switch (this.c) {
      case 3:
        this.u = this.A.a;
        this.v = this.A.b;
        break;
      case 4:
        this.y = new cl(h.h, this.B);
        this.D = 1;
        b(new da("Bỏ qua", 102));
        break;
      case 0:
        this.u = -43;
        this.v = -154;
        break;
      case 1:
        arrayOfString = h.i;
        str = dd.c();
        for (b1 = 0; b1 < arrayOfString.length; b1++)
          arrayOfString[b1] = ao.a(arrayOfString[b1], "{s}", str); 
        this.y = new cl(arrayOfString, this.B);
        this.E = new b[5];
        this.E[0] = new b(az.b("/crystalviolet"));
        this.E[1] = new b(az.b("/crystalblue"));
        this.E[2] = new b(az.b("/crystalgreen"));
        this.E[3] = new b(az.b("/crystalred"));
        this.E[4] = new b(az.b("/crystalyellow"));
        for (b1 = 0; b1 < this.E.length; b1++)
          this.E[b1].a_(false); 
        this.F = 30;
        b(new da("Bỏ qua", 103));
        break;
      case 2:
        this.e = az.b("/m/e1");
        this.f = az.b("/m/e2");
        this.F = 0;
        b((de)null);
        c(new da("Kết thúc", 101));
        break;
    } 
  }
  
  public void a() {
    boolean bool;
    int i;
    byte b1;
    switch (this.c) {
      case 3:
        bool = false;
        if (this.C.a > this.A.a) {
          bool = true;
          this.C.a -= 2;
          this.C.c += 4;
        } 
        if (this.C.b > this.A.b) {
          bool = true;
          this.C.b -= 2;
          this.C.d += 4;
        } 
        if (!bool)
          e(4); 
        break;
      case 4:
        this.y.b();
        if (this.y.a())
          e(5); 
        i = this.y.c();
        if (d[i] != null) {
          this.H = true;
          this.I = d[i][0];
          this.J = d[i][1];
        } 
        if (!this.H) {
          switch (this.D) {
            case 1:
              this.u--;
              if (this.g + this.u + this.t.getWidth() <= this.A.a + this.A.c)
                this.D = 2; 
              break;
            case 2:
              this.v--;
              if (this.a_ + this.v + this.t.getHeight() <= this.A.b + this.A.d)
                this.D = 3; 
              break;
            case 3:
              this.u++;
              if (this.g + this.u >= this.A.a)
                this.D = 4; 
              break;
            case 4:
              this.v++;
              if (this.a_ + this.v >= this.A.b)
                this.D = 1; 
              break;
          } 
          break;
        } 
        this.K.e();
        a(this.I, this.J, this.A);
        break;
      case 5:
        cz.a().a(-1, 8, false);
        break;
      case 0:
        bool = false;
        if (this.C.a > this.A.a) {
          bool = true;
          this.C.a -= 2;
          this.C.c += 4;
        } 
        if (this.C.b > this.A.b) {
          bool = true;
          this.C.b -= 2;
          this.C.d += 4;
        } 
        if (!bool)
          e(1); 
        break;
      case 1:
        this.y.b();
        if (this.y.a())
          e(2); 
        this.F--;
        if (this.F == 0) {
          this.F = 20;
          this.E[ay.a(5)].a(ay.a(cx.f), ay.a(cx.g), 6, "");
        } 
        for (b1 = 0; b1 < this.E.length; b1++)
          this.E[b1].b(); 
        break;
      case 2:
        this.F++;
        if (this.F % 3 == 0)
          this.G = !this.G; 
        break;
    } 
  }
  
  private void b(Graphics paramGraphics) {
    y.a(paramGraphics, this.z);
    y.a(paramGraphics, this.B.a, this.B.b, this.B.c, this.B.d);
    this.y.a(paramGraphics, this.g, this.a_);
    y.b(paramGraphics, this.z);
  }
  
  private void a(Graphics paramGraphics, dl paramdl) {
    y.a(paramGraphics, this.z);
    y.a(paramGraphics, paramdl.a, paramdl.b, paramdl.c, paramdl.d);
    paramGraphics.drawImage(this.t, this.g + this.u, this.a_ + this.v, 0);
    if (this.H)
      this.K.a(paramGraphics, this.g + this.u + this.I - 10, this.a_ + this.v + this.J - 30); 
    y.b(paramGraphics, this.z);
  }
  
  public void a(Graphics paramGraphics) {
    byte b1;
    paramGraphics.setColor(0);
    paramGraphics.fillRect(this.g, this.a_, this.b_, this.c_);
    switch (this.c) {
      case 3:
        a(paramGraphics, this.C);
        break;
      case 4:
        a(paramGraphics, this.A);
        b(paramGraphics);
        break;
      case 0:
        a(paramGraphics, this.C);
        break;
      case 1:
        a(paramGraphics, this.A);
        b(paramGraphics);
        for (b1 = 0; b1 < this.E.length; b1++)
          this.E[b1].a(paramGraphics); 
        break;
      case 2:
        if (this.F < 10) {
          if (this.F % 2 == 0) {
            paramGraphics.drawImage(this.e, (cx.f >> 1) + 2, (cx.g >> 1) - 2, 3);
          } else {
            paramGraphics.drawImage(this.e, (cx.f >> 1) - 2, (cx.g >> 1) + 2, 3);
          } 
        } else {
          paramGraphics.drawImage(this.e, cx.f >> 1, cx.g >> 1, 3);
        } 
        if (this.G)
          paramGraphics.drawImage(this.f, cx.f >> 1, (cx.g >> 1) + 40, 3); 
        break;
    } 
  }
  
  public void b(int paramInt1, int paramInt2) {
    ae ae;
    switch (paramInt2) {
      case 101:
        f.a();
        bg.k();
        ae = cz.a("Chúc mừng", "Bạn đã hoàn thành trò chơi. Bạn có muốn chơi các trò chơi khác không? (Trò chơi có khả năng bị đóng lại nếu chọn Có).", "Có", 105, "Không", 104, 1);
        ae.a(this);
        cz.d().a(ae, true);
        break;
      case 104:
        cz.d().a(false);
        (cz.d()).e.a(-1, 2, true);
        break;
      case 105:
        cz.d().a(false);
        try {
          MGMIDlet.f().a("http://game.ola.vn");
        } catch (Exception exception) {
          exception.printStackTrace();
        } 
        (cz.d()).e.a(-1, 2, true);
        break;
      case 102:
        e(5);
        break;
      case 103:
        e(2);
        break;
    } 
  }
  
  public void e() {}
  
  public void r() {
    cf.a().d();
  }
}


/* Location:              C:\Users\bot-nosense\Downloads\Loan-12-Su-Quan.jar!\df.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */