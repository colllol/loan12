import javax.microedition.lcdui.Graphics;

public class bz {
  private static bz e = null;
  
  public static br a;
  
  public static as b;
  
  public static dh c;
  
  public static dh d;
  
  private bz() {
    b = new as();
    c = new dh("/_font1");
    d = new dh("/_font2");
    a(c);
  }
  
  public static void a() {
    e = new bz();
  }
  
  public static void a(br parambr) {
    a = parambr;
  }
  
  public static String[] a(String paramString, int paramInt) {
    di di = new di();
    byte b1 = 0;
    String str1 = "";
    boolean bool = false;
    while (b1 < paramString.length()) {
      while (true) {
        if (b1 >= paramString.length() || paramString.charAt(b1) != ' ') {
          if (b1 > 0 && di.b() == 0 && b1 * a.a(' ') < paramInt) {
            String str = "";
            for (byte b = 0; b < b1; b++)
              str = String.valueOf(str) + ' '; 
            di.a(str);
          } 
          break;
        } 
        b1++;
      } 
      while (true) {
        if (b1 >= paramString.length() || paramString.charAt(b1) == ' ') {
          if (str1 != null && str1.length() > 0) {
            str1 = String.valueOf(str1) + " ";
            int j = a.a(str1);
            if (j < paramInt) {
              di.a(str1);
            } else {
              byte b = 0;
              String str = "";
              int k = 0;
              while (b < str1.length()) {
                if (k + a.a(str1.charAt(b)) + a.a(' ') < paramInt) {
                  k += a.a(str1.charAt(b));
                  str = String.valueOf(str) + str1.charAt(b);
                  if (++b >= str1.length()) {
                    if (str != null && str.length() > 0)
                      di.a(str); 
                    break;
                  } 
                  continue;
                } 
                di.a(str);
                k = 0;
                str = "";
              } 
            } 
          } 
          str1 = "";
          break;
        } 
        str1 = String.valueOf(str1) + paramString.charAt(b1);
        b1++;
      } 
    } 
    String[] arrayOfString1 = new String[di.b()];
    for (byte b2 = 0; b2 < arrayOfString1.length; b2++)
      arrayOfString1[b2] = (String)di.b(b2); 
    di = new di();
    String str2 = "";
    int i = 0;
    b1 = 0;
    while (true) {
      if (b1 >= arrayOfString1.length) {
        if (str2 != null && str2.length() > 0)
          di.a(str2); 
        break;
      } 
      int j = a.a(arrayOfString1[b1]);
      if (i + j - a.a(' ') < paramInt) {
        i += j;
        str2 = String.valueOf(str2) + arrayOfString1[b1];
        if (bool) {
          di.a(str2);
          str2 = "";
          i = 0;
        } 
        b1++;
        continue;
      } 
      if (j > paramInt) {
        di.a(arrayOfString1[b1]);
        b1++;
      } else {
        di.a(str2);
      } 
      str2 = "";
      i = 0;
    } 
    String[] arrayOfString2 = new String[di.b()];
    for (byte b3 = 0; b3 < arrayOfString2.length; b3++)
      arrayOfString2[b3] = (String)di.b(b3); 
    return arrayOfString2;
  }
  
  public static void a(Graphics paramGraphics, br parambr, String[] paramArrayOfString, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7) {
    int i = paramInt3;
    if (paramInt7 == 1) {
      i += paramInt5 / 2;
    } else if (paramInt7 == 2) {
      i += paramInt5;
    } 
    int j = paramInt1 + paramInt2;
    int k = 0;
    for (int m = paramInt1; m < j && k + parambr.a() <= paramInt6; m++) {
      parambr.a(paramGraphics, paramArrayOfString[m], i, paramInt4 + k, paramInt7);
      k += parambr.a();
    } 
  }
  
  public static void a(Graphics paramGraphics, br parambr, String[] paramArrayOfString, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5) {
    a(paramGraphics, parambr, paramArrayOfString, 0, paramArrayOfString.length, paramInt1, paramInt2, paramInt3, paramInt4, paramInt5);
  }
}


/* Location:              C:\Users\bot-nosense\Downloads\Loan-12-Su-Quan.jar!\bz.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */