package com.mic.smsgame.suquan;

import ak;
import be;
import dq;
import ds;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class b extends ds {
  dq[] a;
  
  int b = 3;
  
  int c = 100;
  
  private boolean e = true;
  
  private int f = 35;
  
  ak d;
  
  public boolean a() {
    return this.e;
  }
  
  public void a_(boolean paramBoolean) {
    this.e = paramBoolean;
  }
  
  public b(Image paramImage) {
    byte[][] arrayOfByte = { { 0, 1, 2 } };
    this.a = new dq[20];
    for (byte b1 = 0; b1 < this.a.length; b1++) {
      this.a[b1] = new dq(paramImage, 3);
      this.a[b1].a(arrayOfByte);
    } 
    this.d = new ak();
    this.d.a(12);
  }
  
  public void a(int paramInt1, int paramInt2, int paramInt3, String paramString) {
    this.b = 3;
    d(paramInt1, paramInt2);
    this.e = true;
    this.d.a(paramString, paramInt1, paramInt2 + 3);
    this.c = this.f + (paramInt3 - 1) * 5;
  }
  
  public void a(Graphics paramGraphics, int paramInt1, int paramInt2) {
    if (!this.e)
      return; 
    int i = 360 / this.a.length;
    int j = 0;
    for (byte b1 = 0; b1 < this.a.length; b1++) {
      int k = this.p + (this.b * be.b(j) >> 14);
      int m = this.q + (this.b * be.a(j) >> 14);
      this.a[b1].a(paramGraphics, k, m);
      j += i;
    } 
    if (this.b >= 7)
      this.d.a(paramGraphics, 0, 0); 
  }
  
  public void b() {
    if (!this.e)
      return; 
    for (byte b1 = 0; b1 < this.a.length; b1++)
      this.a[b1].e(); 
    this.b += 3;
    if (this.b >= this.c)
      this.e = false; 
    if (this.b >= 7)
      this.d.a(); 
  }
}


/* Location:              C:\Users\bot-nosense\Downloads\Loan-12-Su-Quan.jar!\com\mic\smsgame\suquan\b.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */