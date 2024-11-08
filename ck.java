import com.mg.smsgame.main.b;
import com.mic.smsgame.suquan.h;
import com.mic.smsgame.suquan.t;
import com.mic.smsgame.suquan.u;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class ck extends ae implements b {
  private Image[] e;
  
  private Image f;
  
  private Image t;
  
  private Image u;
  
  private dy v;
  
  private int w;
  
  private String[] x;
  
  private String[] y;
  
  private int z;
  
  private di A;
  
  private int B;
  
  private t C;
  
  public ck() {
    super(1);
    u();
    this.A = new di();
    this.b_ = this.e[0].getWidth();
    this.c_ = 180;
    this.g = cx.f - 176 >> 1;
    this.a_ = cx.g - 180 >> 1;
    this.a_ -= 25;
    this.w = 0;
    this.x = h.d;
    this.C = new t((bs.a()).B);
    a(this);
  }
  
  private void u() {
    this.e = (bs.a()).k;
    this.f = (bs.a()).z;
    this.t = (bs.a()).A;
    this.u = (bs.a()).E;
  }
  
  protected void e() {
    if (this.z < 1) {
      this.z = 8;
    } else {
      this.z--;
    } 
  }
  
  public void a(dx[] paramArrayOfdx, dy paramdy) {
    this.v = paramdy;
    this.A.a();
    m();
    this.w = 0;
    for (byte b1 = 0; b1 < paramArrayOfdx.length; b1++) {
      if ((paramArrayOfdx[b1]).b > 0 && (paramArrayOfdx[b1]).a != 2)
        this.A.a(paramArrayOfdx[b1]); 
    } 
    this.B = (this.b_ - 10 - this.A.b() * 36) / (this.A.b() + 1);
    if (this.A.b() > 0) {
      dx dx1 = (dx)this.A.b(this.w);
      this.y = bz.a(this.x[dx1.a], this.b_ - 15);
    } else {
      this.y = bz.a("Hiện tại không có dụng cụ hỗ trợ nào.", this.b_ - 15);
    } 
    b(new da("Đóng", -2));
    this.c = true;
    if (this.A.b() > 0) {
      dx dx1 = (dx)this.A.b(this.w);
      this.y = bz.a(this.x[dx1.a], this.b_ - 15);
    } 
    if (this.A.b() > 0) {
      a(new da("Dùng", -1));
      c(new da(" ", -1));
    } else {
      a((de)null);
      c((de)null);
    } 
    this.c = true;
    cz.d().a(this, false);
  }
  
  public void a(int paramInt) {
    if (this.A.b() < 1)
      return; 
    int i = this.w;
    switch (paramInt) {
      case 14:
        this.w = (this.w - 1 + this.A.b()) % this.A.b();
        break;
      case 15:
        this.w = (this.w + 1) % this.A.b();
        break;
      case 12:
        this.w -= 4;
        if (this.w < 0)
          this.w = i; 
        break;
      case 13:
        this.w += 4;
        if (this.w >= this.A.b())
          this.w = i; 
        break;
    } 
    if (i != this.w)
      v(); 
  }
  
  private void v() {
    dx dx = (dx)this.A.b(this.w);
    this.y = bz.a(this.x[dx.a], this.b_ - 15);
  }
  
  public void a(int paramInt1, int paramInt2) {
    if (this.A.b() < 1)
      return; 
    int i = this.g + this.B + 6;
    int j = this.a_ + 13;
    if (paramInt2 >= j && paramInt2 <= j + 36)
      for (byte b1 = 0; b1 < this.A.b(); b1++) {
        if (paramInt1 >= i && paramInt1 <= i + 36 && b1 != this.w) {
          this.w = b1;
          v();
        } 
        i += 36 + this.B;
      }  
  }
  
  public void a(Graphics paramGraphics) {
    int i = this.g;
    int j = this.a_;
    u.a(paramGraphics, i, j, 180, 7814005, this.e);
    int k = this.g;
    int m = this.a_ + 13;
    byte b1 = 0;
    int n = this.A.b() / 4;
    n += (this.A.b() % 4 != 0) ? 1 : 0;
    for (byte b2 = 0; b2 < n; b2++) {
      k = this.g + 6 + this.B;
      for (byte b3 = 0; b3 < 4 && b1 < this.A.b(); b3++) {
        dx dx = (dx)this.A.b(b1);
        if (this.w == b1) {
          y.a(paramGraphics, this.t, 0, 0, 36, 36, k, m, 0);
        } else {
          y.a(paramGraphics, this.t, 36, 0, 36, 36, k, m, 0);
        } 
        y.a(paramGraphics, this.u, dx.a * 28, 0, 28, 28, k + 4, m + 4, 0);
        this.C.a(paramGraphics, dx.b, k + 34, m + 29, 2);
        k += 36 + this.B;
        b1++;
      } 
      m += 38;
    } 
    if (this.A.b() > 0) {
      paramGraphics.drawImage(this.f, this.g + this.e[0].getWidth() / 2, m, 17);
      dx dx = (dx)this.A.b(this.w);
      bz.c.a(paramGraphics, h.c[dx.a], this.g + this.e[0].getWidth() / 2, m + 8, 1);
      m += 15;
    } else {
      m += 45;
    } 
    bz.a(paramGraphics, bz.d, this.y, this.g + 11, m + 15, this.b_, 120, 0);
  }
  
  public void b(int paramInt1, int paramInt2) {
    dx dx = (dx)this.A.b(this.w);
    switch (paramInt2) {
      case -1:
        dx.a();
        bg.g(dx.a);
        this.v.a("item", dx.a);
      case -2:
        cz.d().a(false);
        break;
    } 
  }
}


/* Location:              C:\Users\bot-nosense\Downloads\Loan-12-Su-Quan.jar!\ck.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */