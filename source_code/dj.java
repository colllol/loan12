import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class dj extends ae {
  private e[] e;
  
  private String f;
  
  private int t;
  
  private int u;
  
  private dl v;
  
  private int w;
  
  private Image x;
  
  public void a(e[] paramArrayOfe) {
    this.e = paramArrayOfe;
    u();
  }
  
  private static e[] a(String paramString, di paramdi1, di paramdi2) {
    String[] arrayOfString = new String[50];
    byte b1 = 1;
    byte b2 = 0;
    if (paramdi1 != null)
      for (byte b = 0; b < paramdi1.b(); b++) {
        int i = paramString.indexOf("{s}");
        paramString = String.valueOf(paramString.substring(0, i)) + (String)paramdi1.b(b) + paramString.substring(i + 2, paramString.length());
      }  
    byte b3 = 0;
    arrayOfString[0] = paramString;
    while (b3 < b1) {
      int i = arrayOfString[b3].indexOf("@");
      if (i >= 0) {
        for (int j = b1 - 1; j > b3; j--)
          arrayOfString[j + 1] = arrayOfString[j]; 
        arrayOfString[b3 + 1] = arrayOfString[b3].substring(i + 1);
        arrayOfString[b3] = arrayOfString[b3].substring(0, i);
        b1++;
      } 
      b3++;
    } 
    e[] arrayOfE = new e[b1];
    for (b3 = 0; b3 < b1; b3++) {
      if ("{i}".equals(arrayOfString[b3])) {
        arrayOfE[b3] = (e)paramdi2.b(b2++);
      } else {
        boolean bool = false;
        if (arrayOfString[b3].length() > 0 && arrayOfString[b3].charAt(0) == '*')
          bool = true; 
        if (bool)
          arrayOfString[b3] = arrayOfString[b3].substring(1); 
        arrayOfE[b3] = new dt(arrayOfString[b3], 0);
        if (arrayOfString[b3].length() > 0 && bool)
          ((dt)arrayOfE[b3]).a(bz.c); 
      } 
    } 
    return arrayOfE;
  }
  
  public static e[] a(String paramString, String[] paramArrayOfString, e[] paramArrayOfe, br parambr1, br parambr2) {
    int i = Integer.parseInt(paramString.substring(0, 3));
    e[] arrayOfE = new e[i];
    paramString = paramString.substring(4);
    if (paramArrayOfString != null)
      for (byte b = 0; b < paramArrayOfString.length; b++) {
        int j = paramString.indexOf("&r");
        paramString = String.valueOf(paramString.substring(0, j)) + paramArrayOfString[b] + paramString.substring(j + 2, paramString.length());
      }  
    byte b1 = 0;
    byte b2 = 0;
    while (paramString != null) {
      String str1;
      int j = paramString.indexOf("#");
      if (j > -1) {
        str1 = paramString.substring(0, j);
        paramString = paramString.substring(j + 1);
      } else {
        str1 = paramString;
      } 
      String str2 = (new StringBuffer(String.valueOf(str1.charAt(0)))).toString();
      if (str2.equals("s")) {
        String str3 = (new StringBuffer(String.valueOf(str1.charAt(1)))).toString();
        String str4 = (new StringBuffer(String.valueOf(str1.charAt(2)))).toString();
        byte b = 0;
        if (str4.equals("r")) {
          b = 2;
        } else if (str4.equals("c")) {
          b = 1;
        } 
        if ((new StringBuffer(String.valueOf(str1.charAt(3)))).toString().equals("$")) {
          int k = Integer.parseInt(str1.substring(4, 6));
          boolean bool = (new StringBuffer(String.valueOf(str1.charAt(4)))).toString().equals("t");
          str1 = str1.substring(7);
          String[] arrayOfString = new String[k];
          for (byte b3 = 0; b3 < arrayOfString.length; b3++) {
            int m = str1.indexOf("|");
            if (m > -1) {
              arrayOfString[b3] = str1.substring(0, m);
              str1 = str1.substring(m + 1);
            } else {
              arrayOfString[b3] = str1;
            } 
          } 
          arrayOfE[b1] = new dt(arrayOfString, bool, b);
        } else {
          arrayOfE[b1] = new dt(str1.substring(3), b);
        } 
        if (str3.equals("t"))
          ((dt)arrayOfE[b1]).a(bz.c); 
      } else if (str2.equals("i")) {
        arrayOfE[b1] = paramArrayOfe[b2++];
      } 
      if (++b1 >= i)
        break; 
    } 
    return arrayOfE;
  }
  
  public static final dj a(String paramString1, String paramString2, di paramdi1, di paramdi2, de paramde1, de paramde2) {
    dj dj1 = new dj(paramString1, a(paramString2, paramdi1, paramdi2));
    if (paramde2 == null) {
      dj1.c(paramde1);
    } else if (paramde1 == null) {
      dj1.c(paramde2);
    } else {
      dj1.a(paramde1);
      dj1.b(paramde2);
    } 
    return dj1;
  }
  
  public dj(String paramString) {
    this(paramString, null);
  }
  
  public dj(String paramString, e[] paramArrayOfe) {
    super(1);
    this.b = false;
    v();
    this.v = new dl(10, 41, this.b_ - 15, this.c_ - 69);
    this.f = paramString;
    this.e = paramArrayOfe;
    u();
  }
  
  private void u() {
    if (this.e == null)
      return; 
    this.w = 0;
    this.t = this.u = 0;
    for (byte b = 0; b < this.e.length; b++) {
      this.e[b].a(this.v);
      this.w += this.e[b].a() + 8;
    } 
  }
  
  private void v() {
    try {
      this.x = cz.d;
    } catch (Exception exception) {
      exception.printStackTrace();
    } 
  }
  
  public void a(int paramInt1, int paramInt2) {
    super.a(paramInt1, paramInt2);
    if (this.t > 0) {
      int i = this.b_ / 2 + 2 - 5;
      int j = this.v.b - 2 - 15;
      if (paramInt1 > i && paramInt1 < i + 10 && paramInt2 > j && paramInt2 < j + 15) {
        cx.u[12] = 2;
        return;
      } 
    } 
    if (this.w > this.v.d && this.w - this.t - this.v.d > 0) {
      int i = this.b_ / 2 + 2 - 5;
      int j = this.v.b + this.v.d + 2;
      if (paramInt1 > i && paramInt1 < i + 10 && paramInt2 > j && paramInt2 < j + 15) {
        cx.u[13] = 2;
        return;
      } 
    } 
  }
  
  public void a(int paramInt) {
    super.a(paramInt);
    if (paramInt == 12 || paramInt == 2) {
      this.t -= this.v.d / 2;
      if (this.t < 0)
        this.t = 0; 
    } else if (paramInt == 13 || paramInt == 8) {
      this.t += this.v.d / 2;
      if (this.w - this.t < this.v.d)
        this.t = this.w - this.v.d; 
      if (this.t < 0)
        this.t = 0; 
    } 
  }
  
  protected void e() {
    if (this.u != this.t) {
      int i = this.t - this.u;
      if (Math.abs(i) < 3) {
        this.u += i;
      } else {
        this.u += i / 2;
      } 
    } 
    if (this.e != null)
      for (byte b = 0; b < this.e.length; b++)
        this.e[b].b();  
  }
  
  public void a(Graphics paramGraphics) {
    if (this.r == null) {
      paramGraphics.setColor(cx.S);
      paramGraphics.fillRect(0, 0, this.b_, this.c_);
    } 
    if (this.f != null) {
      bz.c.a(paramGraphics, this.f, 2, 2, 0);
      paramGraphics.setColor(16711680);
      paramGraphics.fillRect(2, 4 + bz.c.a(), bz.c.a(this.f) + 2, 2);
    } 
    if (this.e != null) {
      paramGraphics.setClip(0, this.v.b, this.b_, this.v.d);
      int i = this.v.b - this.u;
      int j = this.v.b + this.v.d;
      dl dl1 = new dl();
      for (byte b = 0; b < this.e.length && i <= j; b++) {
        dl1.a(this.v.a, i, this.v.c, this.e[b].a());
        if (this.v.a(dl1))
          this.e[b].a(paramGraphics, this.v.a, i); 
        i += this.e[b].a() + 8;
      } 
      paramGraphics.setClip(0, 0, this.b_, this.c_);
    } 
    if (this.t > 0)
      paramGraphics.drawRegion(this.x, 0, 0, this.x.getWidth(), this.x.getHeight(), 6, this.b_ / 2 + 2, this.v.b - 2, 33); 
    if (this.w > this.v.d && this.w - this.t - this.v.d > 0)
      paramGraphics.drawRegion(this.x, 0, 0, this.x.getWidth(), this.x.getHeight(), 5, this.b_ / 2 + 2, this.v.b + this.v.d + 2, 17); 
  }
}


/* Location:              C:\Users\bot-nosense\Downloads\Loan-12-Su-Quan.jar!\dj.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */