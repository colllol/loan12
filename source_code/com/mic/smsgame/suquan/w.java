package com.mic.smsgame.suquan;

import ay;

public class w extends j {
  public void a(int[][] paramArrayOfint) {
    b(paramArrayOfint);
    int i = e.h.length;
    boolean bool = false;
    int k;
    for (k = 2; k < 10; k++) {
      byte b1 = bool ? 3 : 2;
      bool = bool ? false : true;
      while (b1 < 10) {
        paramArrayOfint[k][b1] = ay.a(i);
        b1 += 2;
      } 
    } 
    k = ay.a(5) + 2 + 2;
    int m = ay.a(6) + 1 + 2;
    if (paramArrayOfint[k][m] != 7)
      k--; 
    int n = ay.a(4);
    switch (n) {
      case 0:
        paramArrayOfint[k][m + 1] = paramArrayOfint[k - 1][m];
        paramArrayOfint[k][m - 1] = paramArrayOfint[k - 1][m];
        break;
      case 1:
        paramArrayOfint[k][m + 1] = paramArrayOfint[k + 1][m];
        paramArrayOfint[k][m - 1] = paramArrayOfint[k + 1][m];
        break;
      case 2:
        paramArrayOfint[k + 1][m] = paramArrayOfint[k][m - 1];
        paramArrayOfint[k - 1][m] = paramArrayOfint[k][m - 1];
        break;
      default:
        paramArrayOfint[k + 1][m] = paramArrayOfint[k][m + 1];
        paramArrayOfint[k - 1][m] = paramArrayOfint[k][m + 1];
        break;
    } 
    bool = true;
    for (byte b = 2; b < 10; b++) {
      byte b1 = bool ? 3 : 2;
      bool = bool ? false : true;
      while (b1 < 10) {
        boolean[] arrayOfBoolean = new boolean[e.h.length];
        int i1 = paramArrayOfint[b][b1 - 1];
        int i2 = paramArrayOfint[b][b1 - 2];
        int i3 = paramArrayOfint[b][b1 + 1];
        int i4 = paramArrayOfint[b][b1 + 2];
        if (i1 == i2)
          arrayOfBoolean[i1] = true; 
        if (i3 == i4)
          arrayOfBoolean[i3] = true; 
        if (i3 == i1)
          arrayOfBoolean[i1] = true; 
        int i5 = paramArrayOfint[b - 1][b1];
        int i6 = paramArrayOfint[b - 2][b1];
        int i7 = paramArrayOfint[b + 1][b1];
        int i8 = paramArrayOfint[b + 2][b1];
        if (i5 == i6)
          arrayOfBoolean[i5] = true; 
        if (i7 == i8)
          arrayOfBoolean[i7] = true; 
        if (i5 == i7)
          arrayOfBoolean[i5] = true; 
        int i9 = ay.a(arrayOfBoolean.length);
        if (arrayOfBoolean[i9])
          do {
            i9 = (i9 + 1) % arrayOfBoolean.length;
          } while (arrayOfBoolean[i9]); 
        paramArrayOfint[b][b1] = i9;
        b1 += 2;
      } 
    } 
  }
  
  public void a(int[][] paramArrayOfint, int paramInt1, int paramInt2) {
    paramArrayOfint[paramInt1][paramInt2] = ay.a(e.h.length);
    if (paramArrayOfint[paramInt1][paramInt2] == 0 && ay.a(100) < 11)
      paramArrayOfint[paramInt1][paramInt2] = 6; 
  }
}


/* Location:              C:\Users\bot-nosense\Downloads\Loan-12-Su-Quan.jar!\com\mic\smsgame\suquan\w.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */