public class cx {
  public static final byte[][] a = new byte[][] { { 103, 3, -118, -86 }, { -67, -99, -59, -116 }, { 32, 4, -32, -14 }, { 7, 108, -51, -109 }, { -10, -107, 67, -96 }, { 116, -102, -114, 108 }, { 71, 56, -88, -10 }, { Byte.MIN_VALUE, -87, -17, -45 }, { -65, 67, 52, -94 }, { 50, -72, -110, 61 } };
  
  public static final byte[][] b = new byte[][] { { -125, 12, -1, -80 }, { -70, -61, 100, -82 }, { -110, -45, -90, -81 }, { -14, 55, 7, -104 }, { -37, -45, -18, -18 }, { 119, 86, -37, 35 }, { -103, -82, -106, 49 }, { -30, 53, 17, -110 }, { -86, 23, 98, -98 }, { -102, 103, 86, 74 } };
  
  public static final byte[][] c = new byte[][] { { 5, -1, -84, 81 }, { 122, -7, 6, -51 }, { -73, 70, 85, -18 }, { -83, 104, -66, 81 }, { -15, -79, -56, 68 }, { 35, 24, 49, -17 }, { -6, -71, 55, -40 }, { 124, 98, -53, -34 }, { 66, 28, 84, 78 }, { -62, 32, 35, -48 } };
  
  public static final byte[][] d = new byte[][] { { 68, 65, -8, -61 }, { -35, 76, 8, 72 }, { 107, -37, 102, 38 }, { -114, -109, 73, 6 }, { 48, -22, 4, 4 }, { 11, 67, 89, -56 }, { -66, -85, 17, 111 }, { -104, 58, 67, -42 }, { 82, 2, -100, -95 }, { 29, 69, -46, 16 } };
  
  public static boolean e = false;
  
  public static int f;
  
  public static int g;
  
  public static int h;
  
  public static int i;
  
  public static int j = 0;
  
  public static int k = 0;
  
  public static int l;
  
  public static int m;
  
  public static boolean n = false;
  
  public static int o = -1;
  
  public static int p = -1;
  
  public static int q = -1;
  
  public static int r = -1;
  
  public static int s = -1;
  
  public static int t = -1;
  
  public static int[] u = new int[21];
  
  public static int v = 0;
  
  public static boolean w = true;
  
  public static boolean x = true;
  
  public static int y = 80;
  
  public static int z = 100;
  
  public static boolean A = true;
  
  public static boolean B = false;
  
  public static boolean C = true;
  
  public static boolean D = false;
  
  public static boolean E = false;
  
  public static boolean F = false;
  
  public static int G = 0;
  
  public static boolean H = false;
  
  public static int I = 10;
  
  public static int J = 4;
  
  public static int K;
  
  public static String L;
  
  public static String M = null;
  
  public static boolean N = false;
  
  public static boolean O = false;
  
  public static int P;
  
  public static int Q;
  
  public static int R = 0;
  
  public static int S = 200038;
  
  public static boolean T = false;
  
  public static String U = ".mg";
  
  public static boolean V = false;
  
  public static void a() {
    for (byte b = 0; b < 21; b++)
      u[b] = 0; 
  }
  
  public static void a(int paramInt) {
    P = paramInt;
    int i = (paramInt & 0xFF0000) >> 16;
    int j = (paramInt & 0xFF00) >> 8;
    int k = paramInt & 0xFF;
    byte b = 6;
    Q = (255 - i) / b + i << 16 | (255 - j) / b + j << 8 | (255 - k) / b + k;
  }
  
  static {
    a(S);
  }
}


/* Location:              C:\Users\bot-nosense\Downloads\Loan-12-Su-Quan.jar!\cx.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */