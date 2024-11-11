package com.mic.smsgame.suquan;

import az;
import cz;
import ds;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class c extends ds {
  private final int b = 0;
  
  private final int c = 1;
  
  private int[] d = new int[] { -2, -6, -3, -2, -1 };
  
  private int e;
  
  private Image f = az.b("/title");
  
  private int g;
  
  private int h;
  
  private int i;
  
  int a = 0;
  
  public c(int paramInt1, int paramInt2) {
    this.i = paramInt2;
    d(paramInt1, paramInt2 - 150);
    this.h = b(this.q, paramInt2, 4);
    this.g = 0;
  }
  
  public void a(Graphics paramGraphics, int paramInt1, int paramInt2) {
    paramGraphics.drawImage(this.f, this.p, this.q + this.a, 33);
  }
  
  public void a() {
    switch (this.g) {
      case 0:
        if (f(this.i, this.h)) {
          this.g = 1;
          cz.d().a(10);
          break;
        } 
        this.h++;
        break;
      case 1:
        this.a = this.d[this.e];
        this.e++;
        if (this.e >= this.d.length) {
          this.u = false;
          return;
        } 
        break;
    } 
  }
}


/* Location:              C:\Users\bot-nosense\Downloads\Loan-12-Su-Quan.jar!\com\mic\smsgame\suquan\c.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */