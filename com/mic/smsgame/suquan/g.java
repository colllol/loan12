package com.mic.smsgame.suquan;

import ar;
import az;
import be;
import bz;
import cv;
import dl;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import y;

public class g extends cv {
  private Image v;
  
  private int w;
  
  private int x;
  
  private int[] y;
  
  private int[] z;
  
  private dl A;
  
  public g() {
    this.t = 0;
    this.v = az.b("/crystalblue");
    byte b1 = 50;
    this.A = new dl((((ar)this).b_ - b1 + 20) / 2, (((ar)this).c_ - b1 + 20) / 2, b1 + 20, b1 + 20);
    int i = this.A.a + this.A.c / 2;
    int j = this.A.b + this.A.d / 2;
    int k = b1 / 2;
    byte b2 = 8;
    int m = 360 / b2;
    int n = -90;
    this.y = new int[b2];
    this.z = new int[b2];
    for (byte b3 = 0; b3 < b2; b3++) {
      this.y[b3] = i + (k * be.b(n) >> 14);
      this.z[b3] = j + (k * be.a(n) >> 14);
      n += m;
    } 
    ((ar)this).q = -1000;
  }
  
  public void a() {
    this.x = this.w;
    this.w = (this.w + 1) % this.y.length;
    super.a();
  }
  
  protected void b(Graphics paramGraphics) {
    paramGraphics.setColor(0);
    paramGraphics.fillRect(this.A.a, this.A.b, this.A.c, this.A.d);
    bz.c.a(paramGraphics, (new StringBuffer(String.valueOf(this.c))).toString(), this.A.a + (this.A.c >> 1), this.A.b - 4 + (this.A.d >> 1), 1);
    int i = this.v.getWidth() / 3;
    int j = this.v.getHeight();
    for (int k = this.y.length - 1; k > -1; k--)
      y.a(paramGraphics, this.v, 0, 0, i, j, this.y[k], this.z[k], 3); 
    y.a(paramGraphics, this.v, i, 0, i, j, this.y[this.x], this.z[this.x], 3);
    y.a(paramGraphics, this.v, i + i, 0, i, j, this.y[this.w], this.z[this.w], 3);
  }
}


/* Location:              C:\Users\bot-nosense\Downloads\Loan-12-Su-Quan.jar!\com\mic\smsgame\suquan\g.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */