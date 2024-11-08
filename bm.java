import com.mic.smsgame.suquan.e;
import com.mic.smsgame.suquan.i;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class bm extends ds implements e {
  private Image[] a;
  
  private Image[] b;
  
  private Image[] c;
  
  private i[][] d;
  
  private byte[][] e;
  
  private byte[] f;
  
  private int[] g;
  
  public bm(int paramInt1, int paramInt2) {
    b();
    this.d = new i[8][8];
    this.e = new byte[][] { { 
          0, 0, 0, 1, 1, 1, 2, 2, 2, 3, 
          3, 3 }, new byte[1] };
    this.g = new int[] { 4, 4, 4, 4, 4, 4, 4 };
    this.f = new byte[] { 0, 0, 1, 1, 2, 2, 3, 3 };
    byte[][] arrayOfByte = { { -1 }, new byte[1], { 0, 0, 1, 1, 2, 2, 3, 3 }, new byte[1], new byte[1], new byte[1], new byte[1] };
    this.p = paramInt1;
    this.q = paramInt2;
    this.r = 168;
    this.s = 168;
    paramInt2 += 10;
    byte b1;
    for (b1 = 0; b1 < 8; b1++) {
      int m = paramInt1 + 10;
      for (byte b = 0; b < 8; b++) {
        this.d[b1][b] = new i(m, paramInt2);
        this.d[b1][b].j(3);
        this.d[b1][b].a(arrayOfByte);
        m += 21;
      } 
      paramInt2 += 21;
    } 
    b1 = 4;
    byte b2 = 4;
    byte b3 = 0;
    char c = 'Ϩ';
    int j = c + 32 + 3;
    for (int k = b1 - 1; k > -1; k--) {
      byte b = 0;
      char c1 = c;
      for (int m = b2 - 1; m > -1; m--) {
        this.d[k][m].a(c1, j);
        this.d[b1 + b3][m].a(c1, j);
        this.d[k][b2 + b].a(c1, j);
        this.d[b1 + b3][b2 + b].a(c1, j);
        c1 += '\002';
        b++;
      } 
      b3++;
      c += '\002';
    } 
  }
  
  public void a(int paramInt1, int paramInt2, int paramInt3) {
    paramInt1 -= 2;
    paramInt2 -= 2;
    this.d[paramInt1][paramInt2].a(this.p + paramInt2 * 21, this.q + paramInt1 * 21, this.a[paramInt3]);
  }
  
  public void a(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6) {
    paramInt1 -= 2;
    paramInt2 -= 2;
    paramInt3 -= 2;
    paramInt4 -= 2;
    int j = this.p + paramInt2 * 21;
    int k = this.q + paramInt1 * 21;
    int m = this.p + paramInt4 * 21;
    int n = this.q + paramInt3 * 21;
    this.d[paramInt1][paramInt2].a(m, n, j, k, this.a[paramInt6]);
    this.d[paramInt3][paramInt4].a(j, k, m, n, this.a[paramInt5]);
  }
  
  public void b(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6) {
    paramInt1 -= 2;
    paramInt2 -= 2;
    paramInt3 -= 2;
    paramInt4 -= 2;
    int j = this.p + paramInt2 * 21;
    int k = this.q + paramInt1 * 21;
    int m = this.p + paramInt4 * 21;
    int n = this.q + paramInt3 * 21;
    this.d[paramInt3][paramInt4].a(j, k, m, n, this.a[paramInt5], paramInt6);
  }
  
  public int a(cc paramcc, int paramInt1, int paramInt2) {
    int j = paramcc.a - 2;
    int k = paramcc.b - 2;
    int m = 0;
    int n = this.q + j * 21;
    int i1 = (paramcc.d > paramcc.c) ? paramcc.d : paramcc.c;
    byte[] arrayOfByte = (byte[])null;
    int i2 = 4;
    Image image = null;
    if (i1 > 3 || paramcc.f >= 7) {
      arrayOfByte = this.e[0];
      i2 = this.g[paramcc.e];
      image = this.c[paramcc.e];
    } else {
      arrayOfByte = this.f;
      image = this.b[paramcc.e];
      i2 = 4;
    } 
    int i3 = j + paramcc.c;
    int i4 = k + paramcc.d;
    for (int i5 = j; i5 < i3; i5++) {
      m = this.p + k * 21;
      for (int i6 = k; i6 < i4; i6++) {
        byte[][] arrayOfByte1 = this.d[i5][i6].d();
        arrayOfByte1[2] = arrayOfByte;
        this.d[i5][i6].a(m, n, image, i2);
        m += 21;
      } 
      n += 21;
    } 
    return arrayOfByte.length;
  }
  
  public i a(int paramInt1, int paramInt2) {
    return this.d[paramInt1][paramInt2];
  }
  
  private void b() {
    this.a = (bs.a()).d;
    this.b = (bs.a()).e;
    this.c = (bs.a()).f;
  }
  
  public void a(Graphics paramGraphics, int paramInt1, int paramInt2) {
    for (byte b = 0; b < 8; b++) {
      for (byte b1 = 0; b1 < 8; b1++)
        this.d[b][b1].a(paramGraphics); 
    } 
  }
  
  public void a() {
    for (byte b = 0; b < 8; b++) {
      for (byte b1 = 0; b1 < 8; b1++)
        this.d[b][b1].e(); 
    } 
  }
}


/* Location:              C:\Users\bot-nosense\Downloads\Loan-12-Su-Quan.jar!\bm.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */