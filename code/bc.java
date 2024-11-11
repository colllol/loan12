import com.mg.smsgame.main.MGMIDlet;
import com.mg.smsgame.main.a;
import java.io.UnsupportedEncodingException;

class bc {
  String a;
  
  String b;
  
  a c;
  
  bc(String paramString1, String paramString2, a parama) {
    this.b = paramString1;
    this.c = parama;
    a(paramString2);
  }
  
  private void a(String paramString) {
    paramString = paramString.trim();
    if (paramString.length() == 1) {
      int i = Integer.parseInt(paramString.trim());
      String str = "8731";
      try {
        str = String.valueOf(8) + paramString + '\037';
        byte[] arrayOfByte1 = b.a(str);
        byte[] arrayOfByte2 = new byte[arrayOfByte1.length];
        System.arraycopy(cx.a[i], 0, arrayOfByte2, 0, 4);
        System.arraycopy(cx.b[i], 0, arrayOfByte2, 8, 4);
        System.arraycopy(cx.c[i], 0, arrayOfByte2, 4, 4);
        System.arraycopy(cx.d[i], 0, arrayOfByte2, 12, 4);
        if (!b.a(arrayOfByte1, arrayOfByte2)) {
          al.b();
          MGMIDlet.f().d();
          return;
        } 
        this.a = str;
      } catch (UnsupportedEncodingException unsupportedEncodingException) {
        this.a = str;
      } 
    } 
  }
}


/* Location:              C:\Users\bot-nosense\Downloads\Loan-12-Su-Quan.jar!\bc.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */