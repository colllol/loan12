package com.mic.smsgame.suquan;

import ae;
import aj;
import an;
import ar;
import az;
import bg;
import br;
import bz;
import com.mg.smsgame.main.b;
import cx;
import cz;
import da;
import de;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import y;

public class q extends aj implements b {
  public static final int[] c = new int[] { 3, 3, 1000, 3, 3, 3 };
  
  private Image d;
  
  private Image e;
  
  private Image[] f;
  
  private Image t;
  
  private Image u;
  
  private Image v;
  
  private final int[] w = new int[] { -1, -1, -1, 1000, 250, 500 };
  
  private boolean x;
  
  private String[] y;
  
  private int z;
  
  private String[] A;
  
  private int B;
  
  private int C;
  
  private t D;
  
  private int[] E;
  
  private String F;
  
  public q(int paramInt) {
    b();
    ((ar)this).g = ((ar)this).b_ - this.f[0].getWidth() >> 1;
    ((ar)this).a_ = (((ar)this).c_ - 180 >> 1) - 14;
    this.A = h.d;
    v();
    u();
    this.C = paramInt;
    this.E = bg.v();
    if (this.E == null)
      this.E = new int[6]; 
    this.D = new t();
    ((ar)this).q = 4;
  }
  
  private void b() {
    this.d = Image.createImage(((ar)this).b_, ((ar)this).c_);
    Image image = az.b("/bkmenu");
    Graphics graphics = this.d.getGraphics();
    graphics.drawImage(image, cx.l, cx.m, 0);
    image = null;
    y.a(graphics, 8553090, 0, 0, ((ar)this).b_, ((ar)this).c_);
    this.e = az.b("/itemshops");
    this.f = new Image[2];
    this.f[0] = az.b("/frame1");
    this.f[1] = az.b("/frame2");
    this.t = az.b("/frame3");
    this.u = az.b("/focusitem");
    this.v = az.b("/gold");
    a(null);
    a(this);
  }
  
  public void a(Graphics paramGraphics) {
    paramGraphics.drawImage(this.d, 0, 0, 0);
    u.a(paramGraphics, ((ar)this).g, ((ar)this).a_, 200, 7814005, this.f);
    if (this.x)
      return; 
    int i = ((ar)this).g;
    int j = ((ar)this).a_ + 12;
    byte b1 = 0;
    for (byte b2 = 0; b2 < 2; b2++) {
      i = ((ar)this).g + 25;
      for (byte b3 = 0; b3 < 3; b3++) {
        if (this.z == b1) {
          y.a(paramGraphics, this.u, 0, 0, 36, 36, i, j, 0);
        } else {
          y.a(paramGraphics, this.u, 36, 0, 36, 36, i, j, 0);
        } 
        y.a(paramGraphics, this.e, b1 * 28, 0, 28, 28, i + 4, j + 4, 0);
        if (b1 != 2)
          this.D.a(paramGraphics, this.E[b1], i + 34, j + 29, 2); 
        i += 45;
        b1++;
      } 
      j += 38;
    } 
    paramGraphics.drawImage(this.t, ((ar)this).g + this.f[0].getWidth() / 2, j, 17);
    bz.c.a(paramGraphics, h.c[this.z], ((ar)this).g + this.f[0].getWidth() / 2, j + 8, 1);
    bz.a(paramGraphics, (br)bz.d, this.y, ((ar)this).g + 11, j + 22, ((ar)this).b_, 120, 0);
    bz.c.a(paramGraphics, this.F, ((ar)this).g + this.f[0].getWidth() / 2, ((ar)this).a_ + 180, 1);
    paramGraphics.drawImage(this.v, 0, -4, 0);
    bz.d.a(paramGraphics, "= " + this.E[2], this.v.getWidth() + 1, 0, 0);
  }
  
  private void u() {
    m();
    if (!this.x) {
      a((de)new da("Mua", -1));
      b((de)new da("Trở về", -3));
      c((de)new da(" ", -1));
    } 
  }
  
  public void a() {}
  
  public void a(int paramInt) {
    if (this.x)
      return; 
    int i = this.z;
    switch (paramInt) {
      case 14:
        this.z = (this.z - 1 + this.w.length) % this.w.length;
        break;
      case 15:
        this.z = (this.z + 1) % this.w.length;
        break;
      case 12:
        this.z = (this.z - 3 + this.w.length) % this.w.length;
        break;
      case 13:
        this.z = (this.z + 3) % this.w.length;
        break;
    } 
    if (this.z != i)
      v(); 
  }
  
  private void v() {
    this.y = bz.a(this.A[this.z], this.f[0].getWidth() - 15);
    if (this.w[this.z] > 0) {
      this.F = " Giá " + this.w[this.z] + " vàng";
    } else {
      this.F = " ";
    } 
  }
  
  public void a(int paramInt1, int paramInt2) {
    int i = ((ar)this).a_ + 12;
    int j = ((ar)this).g + 25;
    if (paramInt2 >= i && paramInt2 <= i + 76)
      for (byte b1 = 0; b1 < 3; b1++) {
        if (paramInt1 >= j && paramInt1 <= j + 36) {
          int k = (paramInt2 - i) / 38 * 3 + b1;
          if (k != this.z) {
            this.z = k;
            v();
          } 
        } 
        j += 45;
      }  
  }
  
  public void b(int paramInt1, int paramInt2) {
    String str;
    ae ae;
    switch (paramInt2) {
      case -1:
        str = String.valueOf(this.A[this.z]) + " " + "Bạn có muốn mua không?";
        if (this.z <= 2)
          str = String.valueOf(str) + " Tham khảo giá tại http://free.ola.vn."; 
        ae = cz.a("Thông tin", str, "Có", -4, "Không", -5, 1);
        ae.a(this);
        cz.d().a(ae, true);
        break;
      case -3:
        cz.a().a(4, this.C, false);
        break;
      case -4:
        cz.d().a(false);
        if (this.w[this.z] > 0) {
          if (this.E[2] < this.w[this.z]) {
            ae = cz.a("Thông tin", "Không đủ vàng để mua vật phẩm này? Bạn có muốn mua thêm vàng không? Tham khảo giá tại http://free.ola.vn.", "Có", -6, "Không", -5, 1);
            ae.a(this);
            cz.d().a(ae, true);
            break;
          } 
          this.E[2] = this.E[2] - this.w[this.z];
          bg.b(2, this.w[this.z]);
          this.B = this.z;
          w();
          break;
        } 
        this.B = this.z;
        e(this.B);
        break;
      case -5:
        cz.d().a(false);
        break;
      case -6:
        cz.d().a(false);
        this.B = 2;
        e(this.B);
        break;
    } 
  }
  
  private void e(int paramInt) {
    an.a("shop", cx.G, new o(this));
  }
  
  private void w() {
    this.E[this.B] = this.E[this.B] + c[this.B];
    bg.a(this.B, c[this.B], 6);
    ae ae = cz.a("Thông tin", "Đã mua vật phẩm thành công!", "Đóng", -5, 1);
    ae.a(this);
    cz.d().a(ae, true);
  }
  
  static void a(q paramq) {
    paramq.w();
  }
}


/* Location:              C:\Users\bot-nosense\Downloads\Loan-12-Su-Quan.jar!\com\mic\smsgame\suquan\q.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */