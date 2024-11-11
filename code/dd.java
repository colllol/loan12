import com.mic.smsgame.suquan.f;

public class dd {
  public static int a = -1;
  
  public static int b = 0;
  
  public static int c = -1;
  
  public static int d = -1;
  
  public static c e;
  
  public static c f;
  
  public static int g = -1;
  
  public static boolean h = false;
  
  public static int i = 0;
  
  public static int j = 0;
  
  public static boolean k = false;
  
  private static final int[][] l = new int[][] { 
      { 1, 8, 30, 10 }, { 0, 0, -1 }, { 0, 1, -1, 40, 20, 1 }, { 0, 0, -1 }, { 2, 24, 42, 50, 30, 1 }, { 0, 0, -1 }, { 0, 3, 18, 70, 50, 1 }, { 0, 0, -1 }, { 2, 2, 22, 100, 80, 1 }, { 0, 0, -1 }, 
      { 2, 8, 27, 150, 120, 1 }, { 0, 8, -1 }, { 2, 3, 20, 150, 120, 1 }, { 0, 3, -1 }, { 2, 27, 21, 50, 50 }, { 2, 5, 20, 200, 150, 1 }, { 0, 3, -1 }, { 2, 25, 25, 50, 50 }, { 2, 4, 24, 300, 170, 1 }, { 0, 4, -1 }, 
      { 2, 4, 29, 400, 200, 1 }, { 0, 4, -1 }, { 2, 7, 29, 500, 250 }, { 2, 26, 28, 700, 300, 1 }, { 0, 7, -1 }, { 2, 18, 26, 900, 350, 1 }, { 0, 5, -1 }, { 2, 6, 31, 1200, 450 }, { 2, 19, 30, 1500, 500, 1 }, { 0, 6, -1 }, 
      { 2, 10, 32, 1800, 550, 1 }, { 0, 10, -1 }, { 2, 28, 35, 1600, 500, 1 }, { 2, 9, 34, 2100, 600, 1 }, { 0, 9, -1 }, { 2, 39, 37, 1800, 500 }, { 2, 11, 36, 2500, 650, 1 }, { 0, 11, -1 }, { 2, 1, 33, 3000, 750, 1 }, { 0, 12, -1 }, 
      { 2, 30, 41, 2500, 500 }, { 2, 13, 38, 4000, 850, 1 }, { 2, 13, 40, 5000, 950, 1 }, { 0, 13, -1 }, { 2, 74, 2 }, { 0, 0, -1 } };
  
  private static final int[][] m = new int[][] { 
      null, { 1, 32, 33 }, { 24 }, { 3, 34 }, { 22, 23, 31, 2 }, { 8, 18, 36, 8, 16, 15 }, { 5, 27 }, { 17 }, { 25, 4, 14 }, { 7, 35 }, 
      { 26 }, { 37, 6, 17 }, { 19 }, { 38, 10 }, { 9, 28, 20, 21 }, { 11, 39 }, { 12, 29 }, { 30, 13 }, { 40 } };
  
  private static final int[][] n = new int[][] { { 2, 23, 47, 10 }, { 2, 16, 9, 3 }, { 2, 15, 12, 20 }, { 2, 14, 14, 2, 20 }, { 2, 22, 13, 1, 10 }, { 0, 17, -1, 3 }, { 0, 20, -1 }, { 2, 21, 73, 3, 50 } };
  
  private static final int[][] o = new int[][] { 
      { 1, 1, 2 }, { 1, 3, 4, 5 }, { 1, 6, 7, 8 }, { 1, 1, 2 }, { 1, 3, 4, 5 }, { 1, 6, 7, 8 }, { 1, 60, 5, 6, 7, 8 }, { 1, 30, 2, 1, 2 }, { 1, 3, 4, 5 }, { 20, 60, 4, 3, 4, 5 }, 
      { 1, 1, 2 }, { 1, 1, 2 }, { 25, 75, 5, 6, 7 }, { 15, 45, 3, 3 }, { 30, 90, 6, 3, 4 }, { 1, 1, 2 }, { 1, 1, 2 }, { 1, 3, 4, 5 }, { 1, 1, 2 }, { 1, 1, 2 }, 
      { 40, 40, 4, 3, 4, 5 }, { 25, 3, 4 }, { 20, 20, 2, 3, 4 }, { 10, 6, 7 }, { 50, 50, 5, 7, 7, 8 }, { 30, 6, 7 }, { 40, 40, 4, 1, 2 }, { 30, 30, 3, 1 }, { 60, 60, 6, 1, 1, 2 }, { 45, 50, 5, 3, 4, 5 }, 
      { 70, 70, 7, 7, 7, 8 }, { 45, 3, 4, 5 }, { 80, 80, 8, 4, 4, 5 }, { 60, 60, 6, 4, 4, 5 }, { 90, 90, 9, 2, 2, 2 }, { 55, 6, 7, 8 }, { 70, 70, 7, 1, 1, 2 }, { 60, 60, 6, 1, 2 }, { 95, 300, 15, 5, 5, 5 }, { 65, 7, 7, 8 }, 
      { 99, 500, 20, 8, 8, 8 }, { 75, 7, 7, 8 }, { 7 }, { 3 }, { 6, 3 }, { 9, 6 }, { 12, 1 }, { 15, 3, 4 }, { 18, 5, 6 }, { 21, 1 }, 
      { 24, 3, 4 }, { 27, 5, 6 }, { 30, 1, 2 }, { 33, 3, 4, 5 }, { 36, 6, 7, 8 }, { 39, 1, 2 }, { 42, 3, 4, 5 }, { 45, 6, 7, 8 }, { 48, 1, 1, 2 }, { 51, 4, 4, 5 }, 
      { 54, 7, 7, 8 }, { 57, 1, 1, 2 }, { 60, 4, 4, 5 }, { 63, 7, 7, 8 }, { 66, 6, 7 }, { 69, 7, 8 }, { 72, 8, 8 }, { 75, 1, 2 }, { 78, 1, 2 }, { 81, 1, 2 }, 
      { 84, 4, 5 }, { 87, 4, 5 }, { 90, 4, 5 }, { 93, 6, 7 }, { 96, 1000, 40, 6, 7, 8 }, { 1 }, { 40, 1, 2 } };
  
  private static final int[] p = new int[] { 
      -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
      -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
      -1, -1, -1, -1, -1, 25, 75, 21, 35, -1, 
      41, 23, 75, 75, 75, 39, 75, 31, 76, 37, 
      -1 };
  
  private static final int[][] q = new int[][] { { 8, -5, 5, 50 }, { 7, 10, 80 }, { 9, 5, 20, 140 }, { 14, 10, 40, 230 }, { 6, 20, 100, 500 } };
  
  public static final void a(int paramInt) {
    f.a(101, paramInt);
  }
  
  public static final int a() {
    return f.b(101);
  }
  
  public static final void b(int paramInt) {
    f.a(103, paramInt);
  }
  
  public static final int b() {
    return f.b(103);
  }
  
  public static final String c() {
    String str = f.a(104);
    if (ao.a(str))
      str = "PLAYER"; 
    return str;
  }
  
  public static final void a(String paramString) {
    if (paramString == null)
      paramString = "PLAYER"; 
    f.a(104, paramString);
  }
  
  public static final int d() {
    return f.b(105);
  }
  
  public static final void c(int paramInt) {
    f.a(105, paramInt);
  }
  
  public static final int e() {
    return l.length;
  }
  
  public static final int d(int paramInt) {
    try {
      return l[paramInt][0];
    } catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
      return 0;
    } 
  }
  
  public static final int e(int paramInt) {
    try {
      return l[paramInt][1];
    } catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
      return 0;
    } 
  }
  
  public static final boolean f(int paramInt) {
    try {
      return (l[paramInt][5] == 1);
    } catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
      return false;
    } 
  }
  
  public static final boolean g(int paramInt) {
    try {
      return (l[paramInt][5] == 2);
    } catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
      return false;
    } 
  }
  
  public static final c h(int paramInt) {
    try {
      return (l[paramInt][2] < 0) ? null : t(l[paramInt][2]);
    } catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
      return null;
    } 
  }
  
  public static final int i(int paramInt) {
    try {
      return l[paramInt][3];
    } catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
      return 0;
    } 
  }
  
  public static final int j(int paramInt) {
    try {
      return l[paramInt][4];
    } catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
      return 0;
    } 
  }
  
  public static final boolean k(int paramInt) {
    try {
      return !(l[paramInt][3] == 0 && l[paramInt][4] == 0);
    } catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
      return false;
    } 
  }
  
  public static final int[] l(int paramInt) {
    return m[paramInt];
  }
  
  public static final int f() {
    return n.length;
  }
  
  public static final boolean m(int paramInt) {
    try {
      return (paramInt < 0 || paramInt >= n.length) ? false : (!(n[paramInt][3] == 0 && n[paramInt][4] == 0));
    } catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
      return false;
    } 
  }
  
  public static final int n(int paramInt) {
    try {
      return n[paramInt][3];
    } catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
      return 0;
    } 
  }
  
  public static final int o(int paramInt) {
    try {
      return n[paramInt][4];
    } catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
      return 0;
    } 
  }
  
  public static final int p(int paramInt) {
    try {
      return n[paramInt][0];
    } catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
      return 0;
    } 
  }
  
  public static final int q(int paramInt) {
    try {
      return n[paramInt][1];
    } catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
      return 0;
    } 
  }
  
  public static final int r(int paramInt) {
    return n[paramInt][2];
  }
  
  public static final c s(int paramInt) {
    try {
      return (n[paramInt][2] < 0) ? null : t(n[paramInt][2]);
    } catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
      return null;
    } 
  }
  
  public static final c a(int paramInt1, int paramInt2) {
    if (paramInt1 < 0 || paramInt1 >= p.length)
      return null; 
    if (p[paramInt1] < 0)
      return null; 
    c c1 = null;
    if (p[paramInt1] == 75) {
      c1 = y(paramInt2);
    } else {
      c1 = t(p[paramInt1]);
    } 
    return c1;
  }
  
  public static final c t(int paramInt) {
    return b(paramInt, -1);
  }
  
  public static final c b(int paramInt1, int paramInt2) {
    if (paramInt1 < 0 || paramInt1 >= o.length)
      return null; 
    c c1 = new c();
    c1.a = paramInt1;
    if (paramInt2 < 0) {
      c1.e = o[paramInt1][0];
    } else {
      c1.e = paramInt2;
    } 
    c1.c = c1.b = w(c1.e) + o[paramInt1][1];
    c1.d = v(c1.e) + o[paramInt1][2];
    int i = (o[paramInt1]).length - 3;
    c1.f = new int[i];
    for (byte b = 0; b < i; b++)
      c1.f[b] = o[paramInt1][b + 3]; 
    return c1;
  }
  
  public static final boolean c(int paramInt1, int paramInt2) {
    int i = u(paramInt1);
    return (paramInt2 >= i);
  }
  
  public static final int u(int paramInt) {
    return (paramInt <= 1) ? 0 : (16 * paramInt * paramInt - 25);
  }
  
  public static final int v(int paramInt) {
    return paramInt / 2 + 5;
  }
  
  public static final int w(int paramInt) {
    return 100 + paramInt * 5;
  }
  
  public static final int d(int paramInt1, int paramInt2) {
    return (paramInt2 < 42) ? (paramInt1 * paramInt1 * 2 / 10 + 10) : (paramInt1 * paramInt1 * 1 / 10 + 10);
  }
  
  public static final int a(c paramc1, c paramc2) {
    if (paramc2 == null)
      return 0; 
    int i = d(paramc2.e, paramc2.a);
    int j = paramc2.e - paramc1.e;
    if (j < -5)
      return 0; 
    if (j > 5)
      return i / 2; 
    if (j <= 0)
      return i; 
    int k = i;
    int m = paramc1.c * 100 / paramc1.b;
    if (m > 70)
      k += m * i / 100; 
    k += j * 5 * i / 100;
    return k;
  }
  
  public static final int a(c paramc1, c paramc2, int paramInt) {
    int i = u(paramc1.e);
    int j = 0;
    int k = paramc2.e - paramc1.e;
    if (k >= 0) {
      j = paramInt * 3 / 100;
    } else if (k < -5) {
      j = paramInt * 5 / 100;
    } else {
      j = (-k * 4 + 30) * paramInt / 1000;
    } 
    if (paramInt - j <= i)
      j = paramInt - i; 
    return j;
  }
  
  public static final c x(int paramInt) {
    int i = ay.a(43, 73);
    int j = ay.a(bq.d[paramInt][2], bq.d[paramInt][3]);
    if (j < 1)
      j = 1; 
    return b(i, j);
  }
  
  public static final c y(int paramInt) {
    int i = paramInt - ay.a(6);
    if (i < 1)
      i = 1; 
    return b(75, i);
  }
  
  public static final int z(int paramInt) {
    return p[paramInt];
  }
  
  public static final ae a(boolean paramBoolean) {
    return new cr(paramBoolean);
  }
  
  public static final ae g() {
    int i = b();
    int j = a(e, f, i);
    String str = "Bạn bị mất 1 mạng chơi.";
    if (a < 0) {
      if (j > 0)
        str = String.valueOf(str) + " Và bị phạt {e} kinh nghiệm."; 
      if (bg.l())
        str = String.valueOf(str) + " Bạn có muốn đánh lại không?"; 
    } 
    str = ao.a(str, "{e}", j);
    return cz.a("Chú ý", str, (String[])null, (int[])null, 1);
  }
  
  public static final void h() {
    a(1);
    k = true;
    bg.n();
  }
  
  public static final void i() {
    a(2);
    int i = b();
    if (a >= 0) {
      e.c = b;
      i += j;
    } else {
      e.c = (e.c > e.b) ? e.b : e.c;
      i += a(e, f) + j;
      bg.a(2, i, 6);
    } 
    b(i);
    while (c(e.e + 1, i))
      e.a(); 
    bg.c(1);
  }
  
  public static final void j() {
    a(3);
    if (a < 0) {
      int i = b();
      b(i - a(e, f, i));
      e.c = e.b;
    } else {
      e.c = b;
    } 
    j = 0;
    i = 0;
  }
  
  public static final int[] k() {
    int[] arrayOfInt = new int[q.length];
    for (byte b = 0; b < arrayOfInt.length; b++)
      arrayOfInt[b] = q[b][0]; 
    return arrayOfInt;
  }
  
  public static final c e(int paramInt1, int paramInt2) {
    paramInt2 += q[paramInt1][1];
    if (paramInt2 < 1)
      paramInt2 = 1; 
    return b(q[paramInt1][0], paramInt2);
  }
  
  public static final int A(int paramInt) {
    return q[paramInt][3];
  }
  
  public static final int f(int paramInt1, int paramInt2) {
    return (u(paramInt2 + 1) - u(paramInt2)) * q[paramInt1][2] / 100;
  }
  
  public static final int B(int paramInt) {
    return q[paramInt][2];
  }
}


/* Location:              C:\Users\bot-nosense\Downloads\Loan-12-Su-Quan.jar!\dd.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */