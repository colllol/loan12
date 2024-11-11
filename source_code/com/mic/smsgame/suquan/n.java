package com.mic.smsgame.suquan;

import ay;
import cw;
import dq;
import ds;
import javax.microedition.lcdui.Graphics;

public class n extends dq {
  private boolean a = false;
  
  private int v;
  
  public void a(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5) {
    d(paramInt1, paramInt2);
    ((cw)this).i = paramInt3;
    ((cw)this).j = paramInt4;
    ((ds)this).u = true;
    ((cw)this).k = b(((ds)this).p, paramInt3, 12) + ay.a(3);
    ((cw)this).l = b(((ds)this).q, paramInt4, 12) + ay.a(3);
    this.a = false;
    this.v = paramInt5;
  }
  
  public void a() {
    if (this.v > 0) {
      this.v--;
      return;
    } 
    if (this.a) {
      ((cw)this).l++;
      ((cw)this).k++;
    } else {
      ((cw)this).k = b(((ds)this).p, ((cw)this).i, 4) + ay.a(2);
      ((cw)this).l = b(((ds)this).q, ((cw)this).j, 8) + ay.a(2);
    } 
    if (c(((cw)this).i, ((cw)this).j, ((cw)this).k, ((cw)this).l))
      if (this.a) {
        ((ds)this).u = false;
      } else {
        ((cw)this).i = ((ds)this).p + ay.a(20);
        ((cw)this).j = ((ds)this).q + 40 + ay.a(100);
        ((cw)this).k = b(((ds)this).p, ((cw)this).i, 14) + ay.a(2);
        ((cw)this).l = b(((ds)this).q, ((cw)this).j, 10) + ay.a(2);
        this.a = true;
        c(((cw)this).i, ((cw)this).j, ((cw)this).k, ((cw)this).l);
      }  
  }
  
  public void a(Graphics paramGraphics, int paramInt1, int paramInt2) {
    if (this.v > 0)
      return; 
    super.a(paramGraphics, paramInt1, paramInt2);
  }
}


/* Location:              C:\Users\bot-nosense\Downloads\Loan-12-Su-Quan.jar!\com\mic\smsgame\suquan\n.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */