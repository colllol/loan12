import com.mg.smsgame.main.a;
import com.mg.smsgame.main.b;
import java.util.Vector;
import javax.wireless.messaging.MessageConnection;

public class an implements ba, b {
  public static String a = "TESTGAME";
  
  public static String b = "3";
  
  public static String c = "1";
  
  public static String d = "19003";
  
  public static int e = 0;
  
  public static int f = 6;
  
  public static int g = 5;
  
  public static int h = 5;
  
  public static int i = 5;
  
  public static int j = 7;
  
  public static String k = String.valueOf(a) + b;
  
  public static String l = null;
  
  public static String m = "3ef9f437ab2ed01315d84fc1d7c6718a";
  
  private static an n;
  
  private static a o;
  
  public static String a() {
    return (cx.F ? 1 : 0) + e + f + g + h + i + j;
  }
  
  public static void a(String paramString, int paramInt, a parama) {
    String str1 = null;
    String str2 = System.getProperty("microedition.platform");
    if (str2 == null) {
      str2 = "Unknown";
    } else if (str2.length() >= 80) {
      str2 = str2.substring(0, 80);
    } 
    String str3 = " \"m" + str2 + "\"" + " e" + String.valueOf(System.currentTimeMillis());
    str1 = String.valueOf(k) + " v" + String.valueOf(2) + " a2" + " o" + String.valueOf(paramInt) + " d" + b + " p" + l;
    if (paramString != null)
      str1 = String.valueOf(str1) + " r" + paramString; 
    str1 = String.valueOf(str1) + str3;
    dm.a(str1, (new StringBuffer(String.valueOf(g))).toString(), new o(parama));
  }
  
  public static void b(String paramString, int paramInt, a parama) {
    try {
      String str1 = null;
      String str2 = System.getProperty("microedition.platform");
      if (str2 == null) {
        str2 = "Unknown";
      } else if (str2.length() >= 80) {
        str2 = str2.substring(0, 80);
      } 
      String str3 = " \"m" + str2 + "\"" + " e" + String.valueOf(System.currentTimeMillis());
      str1 = String.valueOf(k) + " v" + String.valueOf(2) + " a2" + " o" + String.valueOf(paramInt) + " d" + b + " p" + l;
      if (paramString != null)
        str1 = String.valueOf(str1) + " r" + paramString; 
      str1 = String.valueOf(str1) + str3;
      dm.a(str1, (new StringBuffer(String.valueOf(h))).toString(), new n(parama));
    } catch (Exception exception) {
      exception.printStackTrace();
    } 
  }
  
  public static void c(String paramString, int paramInt, a parama) {
    String str1 = null;
    String str2 = System.getProperty("microedition.platform");
    if (str2 == null) {
      str2 = "Unknown";
    } else if (str2.length() >= 80) {
      str2 = str2.substring(0, 80);
    } 
    String str3 = " \"m" + str2 + "\"" + " e" + String.valueOf(System.currentTimeMillis());
    str1 = String.valueOf(k) + " v" + String.valueOf(2) + " a4" + " o" + String.valueOf(paramInt) + " d" + b + " p" + l;
    if (paramString != null)
      str1 = String.valueOf(str1) + " r" + paramString; 
    str1 = String.valueOf(str1) + str3;
    dm.a(str1, (new StringBuffer(String.valueOf(i))).toString(), new p(parama));
  }
  
  public static void a(String paramString, a parama) {
    String str1 = null;
    String str2 = System.getProperty("microedition.platform");
    if (str2 == null) {
      str2 = "Unknown";
    } else if (str2.length() >= 80) {
      str2 = str2.substring(0, 80);
    } 
    String str3 = " \"m" + str2 + "\"" + " e" + String.valueOf(System.currentTimeMillis());
    str1 = String.valueOf(k) + " v" + String.valueOf(2) + " a5" + " c" + paramString + " p" + l + str3;
    dm.a(str1, (new StringBuffer(String.valueOf(e))).toString(), new r(parama));
  }
  
  public static void a(String paramString) {
    dm.a("CSE " + c + " " + paramString, (new StringBuffer(String.valueOf(j))).toString(), new q());
  }
  
  public static an b() {
    if (n == null)
      n = new an(); 
    return n;
  }
  
  private static String[] b(String paramString) {
    return a(paramString, " ", "\"", "\"", -1);
  }
  
  public static String[] a(String paramString1, String paramString2, String paramString3, String paramString4, int paramInt) {
    boolean bool = false;
    if (paramInt == 0)
      return null; 
    String[] arrayOfString = (String[])null;
    int i = 0;
    Vector vector = new Vector();
    int j;
    for (j = 0; j < paramString1.length() - paramString2.length() + 1; j++) {
      if (paramString2.equals(paramString1.substring(j, j + paramString2.length())) && !bool) {
        vector.addElement(paramString1.substring(i, j));
        i = j + paramString2.length();
        if (paramInt > 0 && vector.size() >= paramInt - 1) {
          vector.addElement(paramString1.substring(i));
          break;
        } 
        j = i - 1;
      } else if (paramString3 != null && paramString4 != null) {
        if (paramString3.equals(paramString1.substring(j, j + paramString3.length())) && !bool) {
          vector.addElement(paramString1.substring(i, j));
          i = j + paramString3.length();
          if (paramInt > 0 && vector.size() >= paramInt - 1) {
            vector.addElement(paramString1.substring(i));
            break;
          } 
          j = i - 1;
          bool = true;
        } else if (paramString4.equals(paramString1.substring(j, j + paramString4.length())) && bool) {
          vector.addElement(paramString1.substring(i, j));
          i = j + paramString4.length();
          if (paramInt > 0 && vector.size() >= paramInt - 1) {
            vector.addElement(paramString1.substring(i));
            break;
          } 
          j = i - 1;
          bool = false;
        } 
      } 
    } 
    if (i < paramString1.length())
      if (paramInt < 0) {
        vector.addElement(paramString1.substring(i));
      } else if (vector.size() < paramInt) {
        vector.addElement(paramString1.substring(i));
      }  
    if (vector.size() > 0) {
      arrayOfString = new String[vector.size()];
      for (j = 0; j < vector.size(); j++)
        arrayOfString[j] = vector.elementAt(j); 
      return arrayOfString;
    } 
    return null;
  }
  
  public void a(String paramString1, String paramString2) {
    String[] arrayOfString = b(paramString1);
    if (!arrayOfString[0].toUpperCase().equals(k.toUpperCase()))
      return; 
    try {
      int i = 0;
      String str1 = null;
      String str2 = null;
      String str3 = null;
      String str4 = null;
      int j = 0;
      int k;
      for (k = 1; k < arrayOfString.length; k++) {
        if (arrayOfString[k].length() != 0) {
          char c = arrayOfString[k].charAt(0);
          String str = null;
          if (arrayOfString[k].length() > 1)
            str = arrayOfString[k].substring(1); 
          switch (c) {
            case 'a':
              i = Integer.parseInt(str);
              break;
            case 'l':
              str1 = str;
              break;
            case 'c':
              str2 = str;
              break;
            case 'q':
              str3 = str;
              break;
            case 'r':
              str4 = str;
              break;
            case 'i':
              try {
                j = Integer.parseInt(str);
              } catch (Exception exception) {
                j = 0;
              } 
              break;
          } 
        } 
      } 
      al.d();
      if (i == 2) {
        (cz.d()).e.a(j, str4, str3);
        return;
      } 
      k = i;
      String str5 = str1;
      String str6 = str2;
      String str7 = str4;
      if (!cx.B) {
        (cz.d()).e.a(k, str5, str6, str7);
        cx.B = false;
      } 
    } catch (Exception exception) {
      exception.printStackTrace();
      cz.d().a(false);
      ae ae = cz.a("Có lỗi", "Tin sai cú pháp", "Có", 3, 1);
      ae.a(b());
      cz.d().a(ae);
    } 
  }
  
  public void b(int paramInt1, int paramInt2) {
    if (paramInt2 == 1) {
      if (o != null)
        o.a(); 
      cz.d().a(true);
    } else if (paramInt2 == 2) {
      cz.d().a(true);
    } else if (paramInt2 == 3) {
      cz.d().a(true);
    } 
  }
  
  public static void a(MessageConnection paramMessageConnection) {
    dm.a(paramMessageConnection);
  }
  
  static void a(a parama) {
    o = parama;
  }
}


/* Location:              C:\Users\bot-nosense\Downloads\Loan-12-Su-Quan.jar!\an.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */