package com.mic.smsgame.suquan;

import az;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import y;

public class t {
  private int[] a = new int[] { 0, 5, 10, 15, 20, 25, 30, 35, 40, 45 };
  
  private Image b = az.b("/numbersmall");
  
  public t() {}
  
  public t(Image paramImage) {}
  
  public void a(Graphics paramGraphics, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    int i = String.valueOf(paramInt1).length();
    int j = i * 6 - 1;
    switch (paramInt4) {
      case 0:
        paramInt2 += j;
        break;
      case 1:
        paramInt2 += j / 2;
        break;
    } 
    paramInt2 -= 5;
    for (byte b = 0; b < i; b++) {
      int k = paramInt1 % 10;
      y.a(paramGraphics, this.b, this.a[k], 0, 5, 7, paramInt2, paramInt3, 0);
      paramInt1 /= 10;
      paramInt2 -= 6;
    } 
  }
}


/* Location:              C:\Users\bot-nosense\Downloads\Loan-12-Su-Quan.jar!\com\mic\smsgame\suquan\t.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */