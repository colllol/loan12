public class ao {
  public static boolean a(String paramString) {
    if (paramString == null)
      return true; 
    String str = paramString.trim();
    return (str == null || str.length() == 0);
  }
  
  public static boolean a(String paramString1, String paramString2) {
    if (paramString1 == null || paramString2 == null)
      return false; 
    String str1 = paramString1.toLowerCase();
    String str2 = paramString2.toLowerCase();
    return str1.equals(str2);
  }
  
  public static String[] b(String paramString1, String paramString2) {
    return a(paramString1, paramString2, -1, false);
  }
  
  public static String[] a(String paramString1, String paramString2, int paramInt, boolean paramBoolean) {
    if (paramInt == 0 || paramString1 == null)
      return null; 
    if (paramString2 == null)
      return new String[] { paramString1 }; 
    di di = new di();
    String str = paramString1;
    int i = paramString2.length();
    int j = 0;
    if (paramBoolean) {
      j = str.toLowerCase().indexOf(paramString2.toLowerCase());
    } else {
      j = str.indexOf(paramString2);
    } 
    if (j >= 0) {
      byte b = 0;
      while (j >= 0) {
        if (paramInt == 1) {
          if (!a(str))
            di.a(str); 
          return a(di);
        } 
        if (j > b) {
          String str1 = str.substring(b, j);
          di.a(str1);
          if (paramInt > 0)
            paramInt--; 
        } 
        b = 0;
        str = str.substring(j + i);
        if (paramBoolean) {
          j = str.toLowerCase().indexOf(paramString2.toLowerCase());
          continue;
        } 
        j = str.indexOf(paramString2);
      } 
      if (!a(str))
        di.a(str); 
      return a(di);
    } 
    return new String[] { paramString1 };
  }
  
  public static String[] a(di paramdi) {
    if (paramdi == null || paramdi.b() == 0)
      return null; 
    int i = paramdi.b();
    String[] arrayOfString = new String[i];
    for (byte b = 0; b < i; b++)
      arrayOfString[b] = (String)paramdi.b(b); 
    return arrayOfString;
  }
  
  public static byte[] b(String paramString) {
    return c(paramString, null);
  }
  
  public static byte[] c(String paramString1, String paramString2) {
    if (paramString2 == null)
      paramString2 = "UTF-8"; 
    return paramString1.getBytes(paramString2);
  }
  
  public static String a(byte[] paramArrayOfbyte) {
    if (paramArrayOfbyte == null || paramArrayOfbyte.length == 0)
      return null; 
    try {
      return new String(paramArrayOfbyte, 0, paramArrayOfbyte.length, "UTF-8");
    } catch (Throwable throwable) {
      return new String(paramArrayOfbyte, 0, paramArrayOfbyte.length);
    } 
  }
  
  public static String a(String paramString1, String paramString2, String paramString3) {
    int i = 0;
    int j = 0;
    StringBuffer stringBuffer = new StringBuffer();
    while ((j = paramString1.indexOf(paramString2, i)) >= 0) {
      stringBuffer.append(paramString1.substring(i, j));
      stringBuffer.append(paramString3);
      i = j + paramString2.length();
    } 
    stringBuffer.append(paramString1.substring(i));
    return stringBuffer.toString();
  }
}


/* Location:              C:\Users\bot-nosense\Downloads\Loan-12-Su-Quan.jar!\ao.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */