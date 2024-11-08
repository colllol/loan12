import java.io.InputStream;
import javax.microedition.lcdui.Image;

public class az {
  public static boolean a = false;
  
  private static byte[] b = new byte[] { -119, 80, 78, 71 };
  
  private static Image c(String paramString) {
    paramString = String.valueOf(paramString) + cx.U;
    try {
      InputStream inputStream = "".getClass().getResourceAsStream(paramString);
      byte[] arrayOfByte1 = new byte[b.length];
      inputStream.read(arrayOfByte1, 0, arrayOfByte1.length);
      int i = ai.a(arrayOfByte1);
      byte[] arrayOfByte2 = new byte[i];
      bg.a(arrayOfByte2, inputStream, b.length);
      inputStream.close();
      inputStream = null;
      for (byte b = 0; b < b.length; b++)
        arrayOfByte2[b] = b[b]; 
      return Image.createImage(arrayOfByte2, 0, i);
    } catch (Exception exception) {
      exception.printStackTrace();
      return null;
    } 
  }
  
  private static Image d(String paramString) {
    paramString = String.valueOf(paramString) + ".png";
    try {
      return Image.createImage(paramString);
    } catch (Throwable throwable) {
      throwable.printStackTrace();
      return null;
    } 
  }
  
  public static Image a(String paramString) {
    return c(paramString);
  }
  
  public static Image b(String paramString) {
    return a ? d(paramString) : c(paramString);
  }
}


/* Location:              C:\Users\bot-nosense\Downloads\Loan-12-Su-Quan.jar!\az.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */