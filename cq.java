import com.mic.smsgame.suquan.a;
import com.mic.smsgame.suquan.d;
import com.mic.smsgame.suquan.e;
import com.mic.smsgame.suquan.j;
import com.mic.smsgame.suquan.w;

public class cq implements e {
  private int[][] e;
  
  private bn[] f;
  
  private int g;
  
  private int k;
  
  private int l = 2;
  
  private int m = 2;
  
  public dx[] a;
  
  public int b;
  
  public int c;
  
  private j n;
  
  public boolean d = false;
  
  private boolean o;
  
  public cq(int paramInt, c paramc1, c paramc2) {
    this.g = paramInt;
    this.d = (paramInt == 1);
    this.e = new int[12][12];
    i();
    this.f = new bn[2];
    this.f[0] = a(paramc1);
    this.f[1] = a(paramc2);
    int i = (this.f[0].e() > this.f[1].e()) ? this.f[0].e() : this.f[1].e();
    int k = (this.f[0].m() < this.f[1].m()) ? this.f[0].m() : this.f[1].m();
    this.b = k / 2;
    this.c = 240;
    int m = i / k * 3 * 120;
    for (byte b = 0; b < this.f.length; b++)
      this.f[b].h(m); 
    b();
    if (this.d)
      this.f[0].e(9); 
  }
  
  public int a(byte[] paramArrayOfbyte, int paramInt) {
    int i = paramInt;
    byte b;
    for (b = 2; b < 10; b++) {
      for (byte b1 = 2; b1 < 10; b1++)
        paramArrayOfbyte[paramInt++] = (byte)this.e[b][b1]; 
    } 
    System.arraycopy(ai.a(this.l), 0, paramArrayOfbyte, paramInt, 4);
    paramInt += 4;
    System.arraycopy(ai.a(this.m), 0, paramArrayOfbyte, paramInt, 4);
    paramInt += 4;
    for (b = 0; b < this.f.length; b++)
      paramInt += this.f[b].a(paramArrayOfbyte, paramInt); 
    return paramInt - i;
  }
  
  public int b(byte[] paramArrayOfbyte, int paramInt) {
    int i = paramInt;
    byte b;
    for (b = 2; b < 10; b++) {
      for (byte b1 = 2; b1 < 10; b1++)
        this.e[b][b1] = paramArrayOfbyte[paramInt++]; 
    } 
    this.l = ai.a(paramArrayOfbyte, paramInt);
    paramInt += 4;
    this.m = ai.a(paramArrayOfbyte, paramInt);
    paramInt += 4;
    for (b = 0; b < this.f.length; b++)
      paramInt += this.f[b].b(paramArrayOfbyte, paramInt); 
    return paramInt - i;
  }
  
  public int a() {
    return this.k;
  }
  
  public void a(int paramInt) {
    this.k = paramInt;
  }
  
  public void b() {
    int[] arrayOfInt = bg.v();
    if (arrayOfInt == null)
      arrayOfInt = new int[6]; 
    arrayOfInt[5] = 0;
    this.a = new dx[arrayOfInt.length];
    byte b;
    for (b = 0; b < this.a.length; b++) {
      this.a[b] = new dx();
      (this.a[b]).a = b;
      (this.a[b]).b = arrayOfInt[b];
    } 
    d(5);
    c(5);
    for (b = 0; b < this.f.length; b++)
      this.f[b].a(); 
  }
  
  private bn a(c paramc) {
    return new bn(paramc);
  }
  
  public int c() {
    return this.g;
  }
  
  public bn[] d() {
    return this.f;
  }
  
  public bn b(int paramInt) {
    return this.f[paramInt];
  }
  
  public int a(int paramInt1, int paramInt2) {
    return this.e[paramInt1][paramInt2];
  }
  
  public int[][] e() {
    return this.e;
  }
  
  public int f() {
    return this.l;
  }
  
  public void c(int paramInt) {
    this.l = paramInt;
  }
  
  public int g() {
    return this.m;
  }
  
  public void d(int paramInt) {
    this.m = paramInt;
  }
  
  public boolean h() {
    return this.o;
  }
  
  public void a(boolean paramBoolean) {
    this.o = paramBoolean;
  }
  
  public boolean b(int paramInt1, int paramInt2) {
    if (paramInt1 < 2) {
      paramInt1 = 9;
    } else if (paramInt1 >= 10) {
      paramInt1 = 2;
    } 
    if (paramInt2 < 2) {
      paramInt2 = 9;
    } else if (paramInt2 >= 10) {
      paramInt2 = 2;
    } 
    if (g(paramInt1, paramInt2)) {
      this.l = paramInt1;
      this.m = paramInt2;
      return true;
    } 
    return false;
  }
  
  public boolean c(int paramInt1, int paramInt2) {
    return !this.o ? true : (!(paramInt1 < 2 || paramInt1 >= 10 || paramInt2 < 2 || paramInt2 >= 10));
  }
  
  private boolean g(int paramInt1, int paramInt2) {
    return (this.e[paramInt1][paramInt2] >= 0);
  }
  
  public void i() {
    if (this.d) {
      this.n = (j)new d();
    } else {
      this.n = (j)new w();
    } 
    byte b1 = 0;
    byte b2;
    for (b2 = 11; b2 >= 0; b2--)
      this.e[b1][b2] = 8; 
    b1 = 11;
    for (b2 = 11; b2 >= 0; b2--)
      this.e[b1][b2] = 8; 
    b2 = 0;
    for (b1 = 11; b1 >= 0; b1--)
      this.e[b1][b2] = 9; 
    b2 = 11;
    for (b1 = 11; b1 >= 0; b1--)
      this.e[b1][b2] = 9; 
    b1 = 1;
    for (b2 = 10; b2 >= 1; b2--)
      this.e[b1][b2] = 10; 
    b1 = 10;
    for (b2 = 10; b2 >= 1; b2--)
      this.e[b1][b2] = 10; 
    b2 = 1;
    for (b1 = 10; b1 >= 1; b1--)
      this.e[b1][b2] = 11; 
    b2 = 10;
    for (b1 = 10; b1 >= 1; b1--)
      this.e[b1][b2] = 11; 
  }
  
  public void j() {
    this.n.a(this.e);
    a(this.e);
  }
  
  public void d(int paramInt1, int paramInt2) {
    this.n.a(this.e, paramInt1, paramInt2);
  }
  
  public int e(int paramInt1, int paramInt2) {
    if (this.e[paramInt1][paramInt2] >= 7)
      return 0; 
    int i = -16777216;
    byte b1 = 1;
    byte b2 = 1;
    byte b = e.i[this.e[paramInt1][paramInt2]];
    while (b == e.i[this.e[paramInt1][paramInt2 - b2]]) {
      b2++;
      b1++;
    } 
    i |= (b2 - 1 & 0xFF) << 16;
    b2 = 1;
    while (b == e.i[this.e[paramInt1][paramInt2 + b2]]) {
      b2++;
      b1++;
    } 
    i |= (b2 - 1 & 0xFF) << 8;
    i |= b1 & 0xFF;
    return i;
  }
  
  public int f(int paramInt1, int paramInt2) {
    if (this.e[paramInt1][paramInt2] >= 7)
      return 0; 
    int i = 0;
    byte b1 = 1;
    byte b2 = 1;
    byte b = e.i[this.e[paramInt1][paramInt2]];
    while (b == e.i[this.e[paramInt1 - b2][paramInt2]]) {
      b2++;
      b1++;
    } 
    i |= (b2 - 1 & 0xFF) << 16;
    b2 = 1;
    while (b == e.i[this.e[paramInt1 + b2][paramInt2]]) {
      b2++;
      b1++;
    } 
    i |= (b2 - 1 & 0xFF) << 8;
    i |= b1 & 0xFF;
    return i;
  }
  
  public a a(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    return c(paramInt1, paramInt2, paramInt3, paramInt4);
  }
  
  public void b(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    a(this.e, paramInt1, paramInt2, paramInt3, paramInt4);
  }
  
  private void a(int[][] paramArrayOfint, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    int i = paramArrayOfint[paramInt1][paramInt2];
    paramArrayOfint[paramInt1][paramInt2] = paramArrayOfint[paramInt3][paramInt4];
    paramArrayOfint[paramInt3][paramInt4] = i;
  }
  
  private a c(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    try {
      if (this.e[paramInt1][paramInt2] == this.e[paramInt3][paramInt4])
        return null; 
      if (this.e[paramInt1][paramInt2] >= 7 || this.e[paramInt3][paramInt4] >= 7)
        return null; 
      a(this.e, paramInt1, paramInt2, paramInt3, paramInt4);
      boolean bool = false;
      int i = e(paramInt1, paramInt2);
      int k = f(paramInt1, paramInt2);
      int m = e(paramInt3, paramInt4);
      int n = f(paramInt3, paramInt4);
      if ((i & 0xFF) >= 3 || (k & 0xFF) >= 3 || (m & 0xFF) >= 3 || (n & 0xFF) >= 3)
        bool = true; 
      a(this.e, paramInt1, paramInt2, paramInt3, paramInt4);
      if (bool) {
        int[] arrayOfInt = { i, k, m, n };
        a a = new a();
        a.a = paramInt1;
        a.b = paramInt2;
        a.c = paramInt3;
        a.d = paramInt4;
        a.e = arrayOfInt;
        return a;
      } 
    } catch (Exception exception) {
      exception.printStackTrace();
    } 
    return null;
  }
  
  public di k() {
    di di = new di();
    for (byte b = 2; b < 10; b++) {
      for (byte b1 = 2; b1 < 10; b1++) {
        a a = c(b, b1, b + 1, b1);
        if (a != null)
          di.a(a); 
        a = c(b, b1, b, b1 + 1);
        if (a != null)
          di.a(a); 
      } 
    } 
    return di;
  }
  
  public static void a(int[][] paramArrayOfint) {
    for (byte b = 0; b < 12; b++) {
      for (byte b1 = 0; b1 < 12; b1++)
        System.out.print(String.valueOf(paramArrayOfint[b][b1]) + "\t"); 
      System.out.println();
    } 
  }
}


/* Location:              C:\Users\bot-nosense\Downloads\Loan-12-Su-Quan.jar!\cq.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */