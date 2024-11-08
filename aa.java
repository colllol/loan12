import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public abstract class aa {
  protected int a;
  
  protected int[] b;
  
  protected int[] c;
  
  protected Image d;
  
  protected aa(String paramString) {
    this.d = az.b(paramString);
  }
  
  public int a() {
    return this.d.getHeight();
  }
  
  public int a(String paramString) {
    int i = 0;
    for (int j = paramString.length() - 1; j >= 0; j--) {
      int k = " 0123456789.,:!?()+-*/#$%abcdefghijklmnopqrstuvwxyzáàảãạăắằẳẵặâấầẩẫậéèẻẽẹêếềểễệíìỉĩịóòỏõọôốồổỗộơớờởỡợúùủũụưứừửữựýỳỷỹỵđABCDEFGHIJKLMNOPQRSTUVWXYZÁÀẢÃẶĂẮẰẲẴẶÂẤẦẨẪẬÉÈẺẼẸÊẾỀỂỄỆÍÌỈĨỊÓÒỎÕỌÔỐỒỔỖỘƠỚỜỞỠỢÚÙỦŨỤƯỨỪỬỮỰÝỲỶỸỴĐ".indexOf(paramString.charAt(j));
      if (k == -1)
        k = 0; 
      i += this.b[k] + this.a;
    } 
    return i - this.a;
  }
  
  public void a(Graphics paramGraphics, String paramString, int paramInt1, int paramInt2, int paramInt3) {
    int i = 0;
    int j = paramString.length();
    switch (paramInt3) {
      case 1:
        i = paramInt1 - a(paramString);
        break;
      case 2:
        i = paramInt1 - (a(paramString) >> 1);
        break;
      default:
        i = paramInt1;
        break;
    } 
    for (byte b = 0; b < j; b++) {
      int k = " 0123456789.,:!?()+-*/#$%abcdefghijklmnopqrstuvwxyzáàảãạăắằẳẵặâấầẩẫậéèẻẽẹêếềểễệíìỉĩịóòỏõọôốồổỗộơớờởỡợúùủũụưứừửữựýỳỷỹỵđABCDEFGHIJKLMNOPQRSTUVWXYZÁÀẢÃẶĂẮẰẲẴẶÂẤẦẨẪẬÉÈẺẼẸÊẾỀỂỄỆÍÌỈĨỊÓÒỎÕỌÔỐỒỔỖỘƠỚỜỞỠỢÚÙỦŨỤƯỨỪỬỮỰÝỲỶỸỴĐ".indexOf(paramString.charAt(b));
      if (k == -1) {
        k = 0;
      } else {
        paramGraphics.drawRegion(this.d, this.c[k], 0, this.b[k], a(), 0, i, paramInt2, 20);
      } 
      i += this.b[k] + this.a;
    } 
  }
  
  public int a(char paramChar) {
    int i = " 0123456789.,:!?()+-*/#$%abcdefghijklmnopqrstuvwxyzáàảãạăắằẳẵặâấầẩẫậéèẻẽẹêếềểễệíìỉĩịóòỏõọôốồổỗộơớờởỡợúùủũụưứừửữựýỳỷỹỵđABCDEFGHIJKLMNOPQRSTUVWXYZÁÀẢÃẶĂẮẰẲẴẶÂẤẦẨẪẬÉÈẺẼẸÊẾỀỂỄỆÍÌỈĨỊÓÒỎÕỌÔỐỒỔỖỘƠỚỜỞỠỢÚÙỦŨỤƯỨỪỬỮỰÝỲỶỸỴĐ".indexOf(paramChar);
    return (i > -1) ? this.b[i] : this.b[0];
  }
  
  public String[] a(String paramString, int paramInt) {
    int i = a(' ');
    di di = new di();
    String str1 = "";
    boolean bool = false;
    byte b1 = 0;
    while (b1 < paramString.length()) {
      while (true) {
        if (b1 >= paramString.length() || paramString.charAt(b1) != ' ') {
          if (b1 > 0 && di.b() == 0 && b1 * i < paramInt) {
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
            int k = a(str1);
            if (k < paramInt) {
              di.a(str1);
            } else {
              byte b = 0;
              String str = "";
              int m = 0;
              while (b < str1.length()) {
                if (m + a(str1.charAt(b)) + i < paramInt) {
                  m += a(str1.charAt(b));
                  str = String.valueOf(str) + str1.charAt(b);
                  if (++b >= str1.length()) {
                    if (str != null && str.length() > 0)
                      di.a(str); 
                    break;
                  } 
                  continue;
                } 
                di.a(str);
                m = 0;
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
    int j = 0;
    b1 = 0;
    while (true) {
      if (b1 >= arrayOfString1.length) {
        if (str2 != null && str2.length() > 0)
          di.a(str2); 
        break;
      } 
      int k = a(arrayOfString1[b1]);
      if (j + k - i < paramInt) {
        str2 = String.valueOf(str2) + arrayOfString1[b1];
        j = a(str2);
        if (bool) {
          di.a(str2);
          str2 = "";
          j = 0;
        } 
        b1++;
        continue;
      } 
      if (k > paramInt) {
        di.a(arrayOfString1[b1]);
        b1++;
      } else {
        di.a(str2);
      } 
      str2 = "";
      j = 0;
    } 
    String[] arrayOfString2 = new String[di.b()];
    for (byte b3 = 0; b3 < arrayOfString2.length; b3++)
      arrayOfString2[b3] = (String)di.b(b3); 
    return arrayOfString2;
  }
}


/* Location:              C:\Users\bot-nosense\Downloads\Loan-12-Su-Quan.jar!\aa.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */