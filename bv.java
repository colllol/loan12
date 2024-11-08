import com.mg.smsgame.main.b;
import java.util.Stack;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public abstract class bv implements at, b {
  public boolean a = false;
  
  private Stack m;
  
  public aj b;
  
  public aj c;
  
  public ae d;
  
  protected cv e;
  
  public bb f;
  
  protected bb[] g;
  
  protected boolean h;
  
  public by i;
  
  public by j;
  
  public by k;
  
  protected boolean l;
  
  private int n;
  
  private int o;
  
  private boolean p;
  
  private int q;
  
  private int r;
  
  private int s;
  
  public void a(cv paramcv) {
    this.e = paramcv;
  }
  
  public bv(boolean paramBoolean) {
    ci.a().a(5);
    l();
    m();
  }
  
  public final void a(Graphics paramGraphics1, Image paramImage, Graphics paramGraphics2) {
    if (this.l && this.i != null) {
      if (this.b != null)
        this.b.a(paramGraphics1, false); 
      this.i.a(paramGraphics1);
    } else if (this.d != null) {
      if (this.d.c()) {
        if (this.b != null)
          this.b.a(paramGraphics1, false); 
        if (this.d.d())
          this.d.b(false); 
      } 
      this.d.d(paramGraphics1);
    } else if (this.b != null) {
      this.b.d(paramGraphics1);
    } 
    if (this.h && this.f != null) {
      this.f.a(paramGraphics2, paramImage);
    } else {
      paramGraphics2.drawImage(paramImage, cx.j + this.r, cx.k + this.s, 20);
    } 
  }
  
  public final void a() {
    if (this.q > 0) {
      this.q--;
      this.r = ay.a() % 2;
      this.s = ay.a() % 2;
      if (this.q == 0)
        this.r = this.s = 0; 
    } 
    if (this.l && this.i != null) {
      if (this.h) {
        this.h = false;
        this.f = null;
      } 
      this.i.a();
      if (this.i.b()) {
        this.l = false;
        this.i = null;
        if (this.h) {
          this.h = false;
          this.f = null;
        } 
        this.a = false;
      } 
      return;
    } 
    if (this.d != null) {
      this.d.n();
      if (this.d != null && !this.d.d() && this.b != null)
        this.b.n(); 
    } else if (this.b != null) {
      if (this.h && this.f != null) {
        this.f.b();
        if (this.f.d()) {
          this.h = false;
          this.f = null;
          this.a = false;
        } 
        return;
      } 
      this.b.n();
    } 
  }
  
  public final void a(int paramInt) {
    if (this.a)
      return; 
    if (this.d == null && this.b != null && !this.b.c()) {
      cx.u[paramInt] = cx.u[paramInt] + 1;
      this.b.a(paramInt);
    } 
  }
  
  public final boolean b() {
    return (this.d != null) ? this.d.f() : ((this.b != null) ? this.b.f() : false);
  }
  
  public final boolean d() {
    return (this.d != null) ? this.d.q() : ((this.b != null) ? this.b.q() : false);
  }
  
  public final void b(int paramInt) {
    if (this.a)
      return; 
    if (this.d == null && this.b != null && !this.b.c()) {
      cx.u[paramInt] = 0;
      this.b.b(paramInt);
    } 
  }
  
  public final void a(int paramInt1, int paramInt2) {
    if (this.a)
      return; 
    int i = -1;
    if (this.b != null)
      i = this.b.c(paramInt1, paramInt2); 
    if (i > -1) {
      cx.u[i] = 3;
      cx.u[i] = cx.u[i] + 1;
      this.b.a(i);
    } else {
      cx.o = paramInt1;
      cx.p = paramInt2;
    } 
  }
  
  public final void c(int paramInt1, int paramInt2) {
    if (this.a)
      return; 
    int i = -1;
    if (this.b != null)
      i = this.b.d(); 
    if (i > -1) {
      cx.u[i] = 0;
      this.b.b(i);
    } else {
      cx.q = paramInt1;
      cx.r = paramInt2;
    } 
  }
  
  public void a(ae paramae) {
    if (paramae == null)
      return; 
    if (this.d != null) {
      ae ae1 = this.d;
      ae1.b(true);
      if (this.m == null)
        this.m = new Stack(); 
      this.m.push(ae1);
    } 
    this.d = paramae;
    if (this.d.b() && this.j != null) {
      if (this.h) {
        this.h = false;
        this.f = null;
      } 
      this.l = true;
      this.i = this.j;
      this.i.a(this.d.g, cx.g, this.d.g, this.d.a_, this.d.b_, this.d.c_);
      this.a = true;
    } 
  }
  
  public void a(boolean paramBoolean) {
    cx.a();
    if (this.d != null) {
      if (paramBoolean && this.k != null) {
        if (this.h) {
          this.h = false;
          this.f = null;
        } 
        this.l = true;
        this.i = this.k;
        this.i.a(this.d.g, this.d.a_, this.d.g, cx.g, this.d.b_, this.d.c_);
        this.a = true;
      } 
      this.d.r();
    } 
    this.d = null;
    if (this.m == null)
      return; 
    if (this.m.size() > 0) {
      this.d = this.m.pop();
      if (this.d != null)
        this.d.b(true); 
    } 
    System.gc();
  }
  
  public void e() {
    cx.a();
    if (this.d != null)
      this.d.r(); 
    this.d = null;
    if (this.m == null)
      return; 
    for (byte b1 = 0; b1 < this.m.size(); b1++)
      ((ae)this.m.elementAt(b1)).r(); 
    this.m.removeAllElements();
    this.i = null;
    this.l = false;
    System.gc();
  }
  
  public void a(aj paramaj, boolean paramBoolean1, boolean paramBoolean2) {
    if (paramaj == null)
      return; 
    if (paramBoolean1 && this.b != null)
      this.b.r(); 
    this.b = paramaj;
    if (this.f != null && paramBoolean2 && !this.l) {
      this.h = true;
      this.f.c();
      this.a = true;
    } 
  }
  
  public void a(int paramInt1, int paramInt2, boolean paramBoolean) {
    a(paramInt1, paramInt2, paramBoolean, false);
  }
  
  public void a(int paramInt1, int paramInt2, boolean paramBoolean1, boolean paramBoolean2) {
    this.o = paramInt1;
    this.n = paramInt2;
    this.p = paramBoolean1;
    if (paramBoolean2)
      this.c = this.b; 
    f();
    ci.a().a(this);
  }
  
  public void c() {
    try {
      Thread.sleep(100L);
    } catch (InterruptedException interruptedException) {
      interruptedException.printStackTrace();
    } 
    if (this.c != null) {
      this.c.e();
      this.c = null;
    } 
    aj aj1 = d(this.o, this.n);
    this.e.a(aj1, this.p);
  }
  
  public void f() {
    if (this.e == null)
      this.e = new cv(); 
    this.e.b();
    cx.T = false;
    a(this.e, true, false);
  }
  
  public void g() {
    this.h = false;
    this.l = false;
    this.f = null;
    this.i = null;
    if (this.b != null)
      this.b.t(); 
  }
  
  public void h() {
    if (this.b != null)
      this.b.s(); 
  }
  
  public void c(int paramInt) {
    this.q = paramInt;
  }
  
  public void i() {
    cz.d().a(true);
    ae ae1 = cz.a("Chú ý", "Mạng di động đang có vấn đề hoặc quá tải, vui lòng thử lại sau", "Đóng", 3, 1);
    ae1.a(cz.d());
    a(ae1);
  }
  
  public void a(int paramInt, String paramString1, String paramString2) {}
  
  public void a(int paramInt, String paramString1, String paramString2, String paramString3) {
    b(paramInt, paramString1, paramString2, paramString3);
  }
  
  private void b(int paramInt, String paramString1, String paramString2, String paramString3) {
    cx.w = false;
    cx.K = paramInt;
    cx.L = paramString1;
    cx.M = paramString2;
    if (paramString3 != null && paramString3.length() >= 4 && "reco".toLowerCase().equals(paramString3.toLowerCase().substring(0, 4)) && paramString3.length() >= 5) {
      String str = paramString3.trim().substring(4);
      int i = Integer.parseInt(str);
      bg.a(i, paramInt, paramString2, paramString1);
      dc dc = null;
      aj aj1 = (cz.d()).e.b;
      if (aj1 != null && aj1.k() == 3) {
        dc = (dc)aj1;
        dc.v();
      } else {
        dc = k();
      } 
      if (dc == null)
        return; 
      dc.e(i);
      cz.d().a(dc, false);
      cz.d().e();
    } 
  }
  
  public bb d(int paramInt) {
    return this.g[paramInt];
  }
  
  public int j() {
    return this.g.length;
  }
  
  protected abstract aj d(int paramInt1, int paramInt2);
  
  public abstract dc k();
  
  public abstract void l();
  
  public abstract void m();
}


/* Location:              C:\Users\bot-nosense\Downloads\Loan-12-Su-Quan.jar!\bv.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */