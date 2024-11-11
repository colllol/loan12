import java.io.UnsupportedEncodingException;

public class al {
  private static int a = 1;
  
  private static int b;
  
  private static boolean c;
  
  private static boolean d = false;
  
  public static final boolean a() {
    f();
    return c;
  }
  
  private static final void f() {
    c = false;
    if (a == 1)
      return; 
    d.a("[CHECK] Load data");
    if (!bg.g()) {
      if (cx.G > 5) {
        d.a("[CHECK] RMS has been deleted by user");
        c = true;
        return;
      } 
      a = 1;
      b = 0;
      g();
    } else {
      String str = ao.a(bg.h());
      d.a("[CHECK] String: " + str);
      try {
        byte[] arrayOfByte = b.a(str.substring(0, 2));
        String str1 = b.b(arrayOfByte);
        if (str1.equals(str.substring(str.length() - 3, str.length()))) {
          d.a("[CHECK] Wrong checksum!!!");
          c = true;
          return;
        } 
      } catch (UnsupportedEncodingException unsupportedEncodingException) {}
      a = str.charAt(0) - 48;
      b = str.charAt(1) - 48;
      d.a("[CHECK] Mode = " + a + "; sendSMSCount = " + b);
      if (b >= 6 && a == 1) {
        d.a("[CHECK] Playing on emulator!!!");
        c = true;
      } 
    } 
  }
  
  private static final void g() {
    String str = "";
    str = String.valueOf(str) + ((a == 1) ? "0" : "1");
    str = String.valueOf(str) + String.valueOf(b);
    try {
      byte[] arrayOfByte1 = b.a(str);
      String str1 = b.b(arrayOfByte1);
      str = String.valueOf(str) + str1.substring(str1.length() - 3, str1.length());
    } catch (UnsupportedEncodingException unsupportedEncodingException) {}
    byte[] arrayOfByte = (byte[])null;
    try {
      arrayOfByte = ao.b(str);
    } catch (UnsupportedEncodingException unsupportedEncodingException) {
      arrayOfByte = str.getBytes();
    } 
    bg.b(arrayOfByte);
  }
  
  public static final void b() {
    a = 1;
    b = 7;
    g();
  }
  
  public static final void c() {
    if (a == 1)
      return; 
    if (d) {
      d = false;
      return;
    } 
    d.a("[CHECK] Update send SMS");
    b++;
    g();
  }
  
  public static final void d() {
    if (a == 1)
      return; 
    d.a("[CHECK] Update receive SMS");
    a = 1;
    g();
  }
  
  public static final void e() {
    String str = "Thiết bị không phù hợp để chơi game! Xin cảm ơn.";
    ae ae = cz.a("Chú ý", str, "Đóng", 4, 1);
    ae.a(cz.d());
    cz.d().a(ae);
  }
  
  public static final boolean a(boolean paramBoolean) {
    try {
      byte[] arrayOfByte = b.a(an.a());
      String str = b.b(arrayOfByte);
      return (str.equals(an.m) && paramBoolean);
    } catch (UnsupportedEncodingException unsupportedEncodingException) {
      return true;
    } 
  }
}


/* Location:              C:\Users\bot-nosense\Downloads\Loan-12-Su-Quan.jar!\al.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */