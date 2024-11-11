package com.mic.smsgame.suquan;

import ae;
import aj;
import ao;
import ar;
import az;
import br;
import bz;
import com.mg.smsgame.main.MGMIDlet;
import com.mg.smsgame.main.b;
import cx;
import cz;
import da;
import dd;
import de;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.TextBox;
import y;

public class z extends aj implements b, CommandListener {
  private Image[] c;
  
  private Image d;
  
  private Image e;
  
  private int f;
  
  private Image[] t;
  
  private Image u;
  
  private String[] v;
  
  private String[] w;
  
  private TextBox x;
  
  private int y;
  
  public z() {
    b();
    ((ar)this).g = ((ar)this).b_ - this.t[0].getWidth() >> 1;
    ((ar)this).a_ = (((ar)this).c_ - 180 >> 1) - 18;
    this.w = h.e;
    this.v = bz.a(this.w[this.f], this.t[0].getWidth() - 15);
    a((de)new da("Chọn", -1));
    c((de)new da(" ", -1));
    b((de)new da("Trở về", -2));
    a(null);
    a(this);
    this.x = new TextBox("Nhap ten nhan vat", "", 10, 1048576);
    this.x.addCommand(new Command("Xong", 4, 1));
    this.x.addCommand(new Command("Trở về", 2, 1));
    this.x.setCommandListener(this);
  }
  
  private void b() {
    Image image1 = az.b("/avatar");
    this.c = new Image[6];
    for (byte b1 = 0; b1 < 6; b1++)
      this.c[b1] = Image.createImage(image1, b1 * 30, 0, 30, 30, 0); 
    image1 = null;
    this.d = az.b("/focusitem");
    this.t = new Image[2];
    this.t[0] = az.b("/frame1");
    this.t[1] = az.b("/frame2");
    this.u = az.b("/frame3");
    this.e = Image.createImage(((ar)this).b_, ((ar)this).c_);
    Image image2 = az.b("/bkmenu");
    Graphics graphics = this.e.getGraphics();
    graphics.drawImage(image2, cx.l, cx.m, 0);
    image2 = null;
    y.a(graphics, 8553090, 0, 0, ((ar)this).b_, ((ar)this).c_);
  }
  
  public void a(int paramInt) {
    int i = this.f;
    switch (paramInt) {
      case 14:
        this.f = (this.f - 1 + this.w.length) % this.w.length;
        break;
      case 15:
        this.f = (this.f + 1) % this.w.length;
        break;
      case 12:
        this.f = (this.f - 3 + this.w.length) % this.w.length;
        break;
      case 13:
        this.f = (this.f + 3) % this.w.length;
        break;
    } 
    if (this.f != i)
      this.v = bz.a(this.w[this.f], this.t[0].getWidth() - 15); 
  }
  
  public void a(int paramInt1, int paramInt2) {
    int i = ((ar)this).g;
    int j = ((ar)this).a_ + 12;
    byte b1 = 0;
    for (byte b2 = 0; b2 < 2; b2++) {
      i = ((ar)this).g + 25;
      for (byte b3 = 0; b3 < 3; b3++) {
        if (paramInt2 >= j && paramInt2 <= j + 36 && paramInt1 >= i && paramInt1 <= i + 38 && b1 != this.f) {
          this.f = b1;
          this.v = bz.a(this.w[this.f], this.t[0].getWidth() - 15);
          return;
        } 
        i += 45;
        b1++;
      } 
      j += 38;
    } 
  }
  
  public void a(Graphics paramGraphics) {
    paramGraphics.drawImage(this.e, 0, 0, 0);
    u.a(paramGraphics, ((ar)this).g, ((ar)this).a_, 200, 7814005, this.t);
    int i = ((ar)this).g;
    int j = ((ar)this).a_ + 12;
    byte b1 = 0;
    for (byte b2 = 0; b2 < 2; b2++) {
      i = ((ar)this).g + 25;
      for (byte b3 = 0; b3 < 3; b3++) {
        if (this.f == b1 && this.y % 2 == 0) {
          y.a(paramGraphics, this.d, 0, 0, 36, 36, i, j, 0);
        } else {
          y.a(paramGraphics, this.d, 36, 0, 36, 36, i, j, 0);
        } 
        paramGraphics.drawImage(this.c[b1], i + 3, j + 3, 0);
        i += 45;
        b1++;
      } 
      j += 38;
    } 
    paramGraphics.drawImage(this.u, ((ar)this).g + this.t[0].getWidth() / 2, j, 17);
    bz.a(paramGraphics, (br)bz.d, this.v, ((ar)this).g + 11, j + 15, ((ar)this).b_, 120, 0);
  }
  
  public void a() {
    if (this.y < 1) {
      this.y = 8;
    } else {
      this.y--;
    } 
  }
  
  private void u() {
    this.x.setString("");
    (MGMIDlet.f()).a.setCurrent((Displayable)this.x);
  }
  
  public void b(int paramInt1, int paramInt2) {
    switch (paramInt2) {
      case -1:
        u();
        break;
      case -2:
        (cz.d()).e.a(8, 2, true);
        break;
      case -3:
        cz.d().a(false);
        u();
        break;
      case -4:
        cz.d().a(false);
        (MGMIDlet.f()).a.setCurrent((Displayable)cz.d());
        break;
    } 
  }
  
  public void commandAction(Command paramCommand, Displayable paramDisplayable) {
    if (ao.a(paramCommand.getLabel(), "Xong")) {
      (MGMIDlet.f()).a.setCurrent((Displayable)cz.d());
      String str = this.x.getString().trim();
      if (str == null || str.length() < 2) {
        ae ae = cz.a("Thông tin", "Nhập tên nhân vật có ít nhất 2 kí tự và nhiều nhất 10 kí tự. Bạn có muốn tiếp tục không?", "Có", -3, "Không", -4, 1);
        ae.a(this);
        cz.d().a(ae, false);
        return;
      } 
      dd.a(str.toUpperCase());
      dd.c(this.f);
      (cz.d()).e.a(8, 7, true);
    } else if (ao.a(paramCommand.getLabel(), "Trở về")) {
      (MGMIDlet.f()).a.setCurrent((Displayable)cz.d());
    } 
  }
}


/* Location:              C:\Users\bot-nosense\Downloads\Loan-12-Su-Quan.jar!\com\mic\smsgame\suquan\z.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */