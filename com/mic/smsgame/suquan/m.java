package com.mic.smsgame.suquan;

import ar;
import aw;
import az;
import cx;
import dc;
import dz;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import y;

public class m extends dc {
  private Image E = az.b("/r/br");
  
  private int F = 240 - this.E.getWidth() >> 1;
  
  private int G = 320 - this.E.getHeight() >> 1;
  
  public m(int paramInt) {
    super(paramInt, h.g, 3);
    this.C.a(null);
    this.C.a(null);
    ((ar)this).q = 3;
  }
  
  protected void b() {
    this.B = new Image[4];
    try {
      this.z = az.b("/r/q");
      this.A = az.b("/r/g");
      for (byte b = 0; b < this.B.length; b++)
        this.B[b] = az.b("/r/" + (b + 1)); 
      Image image1 = Image.createImage(((ar)this).b_, ((ar)this).c_);
      Image image2 = az.b("/bkmenu");
      Graphics graphics = image1.getGraphics();
      graphics.drawImage(image2, cx.l, cx.m, 0);
      image2 = null;
      y.a(graphics, 8553090, 0, 0, ((ar)this).b_, ((ar)this).c_);
      a((dz)new aw(image1, ((ar)this).b_ >> 1, ((ar)this).c_ >> 1, 3));
    } catch (Exception exception) {
      exception.printStackTrace();
    } 
  }
  
  protected void a(Graphics paramGraphics, int paramInt1, int paramInt2) {
    paramInt1 = this.w[this.e].i() + this.w[this.e].k() / 2;
    paramInt2 = this.w[this.e].j() + this.w[this.e].l() / 2;
    paramGraphics.setColor(16776960);
    paramGraphics.fillTriangle(paramInt1, paramInt2 - 10 - this.f, paramInt1 - 4, paramInt2 - 14 - this.f, paramInt1 + 4, paramInt2 - 14 - this.f);
    paramGraphics.fillTriangle(paramInt1, paramInt2 + 10 + this.f, paramInt1 - 5, paramInt2 + 15 + this.f, paramInt1 + 4, paramInt2 + 15 + this.f);
    paramGraphics.fillTriangle(paramInt1 - 10 - this.f, paramInt2, paramInt1 - 14 - this.f, paramInt2 - 4, paramInt1 - 14 - this.f, paramInt2 + 4);
    paramGraphics.fillTriangle(paramInt1 + 10 + this.f, paramInt2, paramInt1 + 15 + this.f, paramInt2 - 5, paramInt1 + 15 + this.f, paramInt2 + 4);
  }
  
  protected void b(Graphics paramGraphics) {
    paramGraphics.drawImage(this.E, ((ar)this).g + this.F, ((ar)this).a_ + this.G, 0);
    super.b(paramGraphics);
  }
  
  protected void u() {
    this.t = new int[10][4];
    (new int[4])[0] = -1;
    (new int[4])[1] = 1;
    (new int[4])[2] = 4;
    (new int[4])[3] = 9;
    this.t[0] = new int[4];
    (new int[4])[0] = -1;
    (new int[4])[1] = 2;
    (new int[4])[2] = 5;
    this.t[1] = new int[4];
    (new int[4])[0] = -1;
    (new int[4])[1] = 3;
    (new int[4])[2] = 6;
    (new int[4])[3] = 1;
    this.t[2] = new int[4];
    (new int[4])[0] = -1;
    (new int[4])[1] = 4;
    (new int[4])[2] = 7;
    (new int[4])[3] = 2;
    this.t[3] = new int[4];
    (new int[4])[1] = 5;
    (new int[4])[2] = -1;
    (new int[4])[3] = 3;
    this.t[4] = new int[4];
    (new int[4])[0] = 1;
    (new int[4])[1] = 6;
    (new int[4])[2] = 8;
    (new int[4])[3] = 4;
    this.t[5] = new int[4];
    (new int[4])[0] = 2;
    (new int[4])[1] = 7;
    (new int[4])[2] = 9;
    (new int[4])[3] = 5;
    this.t[6] = new int[4];
    (new int[4])[0] = 3;
    (new int[4])[1] = 8;
    (new int[4])[2] = -1;
    (new int[4])[3] = 6;
    this.t[7] = new int[4];
    (new int[4])[0] = 5;
    (new int[4])[1] = 9;
    (new int[4])[2] = -1;
    (new int[4])[3] = 7;
    this.t[8] = new int[4];
    (new int[4])[0] = 6;
    (new int[4])[2] = -1;
    (new int[4])[3] = 8;
    this.t[9] = new int[4];
    byte b1 = 34;
    byte b2 = 34;
    ((ar)this).g = cx.l;
    ((ar)this).a_ = cx.m;
    int i = ((ar)this).g + 68;
    int j = ((ar)this).a_ + 124;
    this.x = new int[this.t.length];
    this.y = new int[this.t.length];
    int k;
    for (k = 0; k < this.x.length; k++) {
      this.x[k] = i + k % 4 * b1;
      this.y[k] = j + k / 4 * b2;
    } 
    for (k = this.x.length - 2; k < this.x.length; k++)
      this.x[k] = this.x[k] + b1; 
  }
}


/* Location:              C:\Users\bot-nosense\Downloads\Loan-12-Su-Quan.jar!\com\mic\smsgame\suquan\m.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */