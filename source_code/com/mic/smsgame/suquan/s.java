package com.mic.smsgame.suquan;

import ay;
import ds;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class s extends ds {
  private x[] a = new x[5];
  
  private byte[][] b = new byte[][] { { 0, 0, 0, 0, 1, 1, 1, 2, 2, 2 } };
  
  public s() {
    for (byte b = 0; b < this.a.length; b++) {
      this.a[b] = new x();
      this.a[b].a(this.b);
    } 
  }
  
  public void a(int paramInt1, int paramInt2, int paramInt3, int paramInt4, Image paramImage) {
    for (byte b = 0; b < this.a.length; b++) {
      this.a[b].a(paramImage, 3);
      this.a[b].b(ay.a((this.b[0]).length));
      this.a[b].b(paramInt1 + ay.a() % 13, paramInt2 + ay.a() % 13, paramInt3 + ay.a() % 20, paramInt4 + ay.a() % 3);
    } 
    this.u = true;
  }
  
  public void a(Graphics paramGraphics, int paramInt1, int paramInt2) {
    if (!this.u)
      return; 
    for (int i = this.a.length - 1; i >= 0; i--)
      this.a[i].a(paramGraphics); 
  }
  
  public void a() {
    if (!this.u)
      return; 
    byte b = 0;
    for (int i = this.a.length - 1; i >= 0; i--) {
      if (this.a[i].m()) {
        this.a[i].e();
      } else {
        b++;
      } 
    } 
    if (b >= this.a.length)
      this.u = false; 
  }
}


/* Location:              C:\Users\bot-nosense\Downloads\Loan-12-Su-Quan.jar!\com\mic\smsgame\suquan\s.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */