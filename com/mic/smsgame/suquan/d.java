package com.mic.smsgame.suquan;

import ay;

public class d extends j {
  private int[][] a = new int[][] { 
      { 
        9, 8, 8, 8, 8, 8, 8, 8, 8, 8, 
        8, 9 }, { 
        9, 11, 10, 10, 10, 10, 10, 10, 10, 10, 
        11, 9 }, { 9, 11, 1, 4, 2, 3, 5, 5, 11, 9 }, { 9, 11, 1, 4, 2, 5, 4, 1, 11, 9 }, { 9, 11, 1, 3, 1, 11, 9 }, { 9, 11, 1, 5, 1, 1, 5, 11, 9 }, { 
        9, 11, 1, 3, 2, 4, 2, 5, 2, 6, 
        11, 9 }, { 
        9, 11, 3, 2, 3, 2, 4, 2, 4, 2, 
        11, 9 }, { 
        9, 11, 4, 4, 1, 3, 4, 2, 5, 11, 
        9 }, { 9, 11, 1, 1, 1, 1, 2, 3, 11, 9 }, 
      { 
        9, 11, 10, 10, 10, 10, 10, 10, 10, 10, 
        11, 9 }, { 
        9, 8, 8, 8, 8, 8, 8, 8, 8, 8, 
        8, 9 } };
  
  public void a(int[][] paramArrayOfint) {
    for (byte b = 2; b < 10; b++) {
      for (byte b1 = 2; b1 < 10; b1++)
        paramArrayOfint[b][b1] = this.a[b][b1]; 
    } 
    paramArrayOfint = this.a;
  }
  
  public void a(int[][] paramArrayOfint, int paramInt1, int paramInt2) {
    paramArrayOfint[paramInt1][paramInt2] = 1 + ay.a(e.h.length - 1);
  }
}


/* Location:              C:\Users\bot-nosense\Downloads\Loan-12-Su-Quan.jar!\com\mic\smsgame\suquan\d.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */