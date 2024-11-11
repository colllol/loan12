import com.mg.smsgame.main.MGMIDlet;
import com.mg.smsgame.main.b;
import javax.microedition.io.ConnectionNotFoundException;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class cz extends Canvas implements b, Runnable {
  public static cz a;
  
  public static int b = 0;
  
  private long g;
  
  private long h;
  
  private boolean i;
  
  private boolean j = false;
  
  private boolean k;
  
  private boolean l;
  
  private Object m = new Object();
  
  public static Image c;
  
  public static Image d;
  
  private static int n = -1;
  
  private Graphics o;
  
  private Image p;
  
  public bv e;
  
  public static int f = -1000;
  
  public static bv a() {
    return (d()).e;
  }
  
  protected cz() {
    bg.p();
    c = az.b("/_corner");
    d = az.b("/_arrow");
    this.p = Image.createImage(cx.f, cx.g);
    this.o = this.p.getGraphics();
    setFullScreenMode(true);
    cx.n = hasPointerEvents();
    String str = System.getProperty("microedition.platform");
    if (getKeyCode(8) == -20) {
      cx.v = 3;
    } else if (str == null || str.length() == 0) {
      cx.v = 4;
    } else if (str.indexOf("NokiaN") >= 0) {
      cx.v = 1;
    } else if (str.indexOf("Nokia") >= 0) {
      cx.v = 0;
    } else if (str.indexOf("Sony") >= 0) {
      cx.v = 2;
    } else if (str.indexOf("RIM") >= 0) {
      cx.v = 5;
    } else if (str.indexOf("SIE-EL71") >= 0) {
      cx.v = 6;
    } else {
      cx.v = 4;
    } 
    d.a("Vendor: " + str + " detected as " + String.valueOf(cx.v));
  }
  
  public void b() {
    Thread thread = new Thread(this);
    thread.start();
  }
  
  public static final void a(Graphics paramGraphics, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6) {
    int i = cx.R;
    paramGraphics.setColor(paramInt1);
    paramGraphics.fillRect(paramInt3 + 3, paramInt4 + 3, paramInt5 - 6, paramInt6 - 6);
    paramGraphics.setColor(i);
    paramGraphics.drawRect(paramInt3 + 2, paramInt4 + 2, paramInt5 - 5, paramInt6 - 5);
    paramGraphics.setColor(paramInt2);
    paramGraphics.drawRect(paramInt3 + 1, paramInt4 + 1, paramInt5 - 3, paramInt6 - 3);
    paramGraphics.drawRegion(c, 0, 0, c.getWidth(), c.getHeight(), 0, paramInt3, paramInt4, 20);
    paramGraphics.drawRegion(c, 0, 0, c.getWidth(), c.getHeight(), 2, paramInt3 + paramInt5, paramInt4, 24);
    paramGraphics.drawRegion(c, 0, 0, c.getWidth(), c.getHeight(), 1, paramInt3, paramInt4 + paramInt6, 36);
    paramGraphics.drawRegion(c, 0, 0, c.getWidth(), c.getHeight(), 3, paramInt3 + paramInt5, paramInt4 + paramInt6, 40);
  }
  
  public static final void b(Graphics paramGraphics, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6) {
    paramGraphics.setColor(paramInt2);
    paramGraphics.drawRect(paramInt3, paramInt4 + 1, paramInt5 - 1, paramInt6 - 1);
    paramGraphics.setColor(paramInt1);
    paramGraphics.fillRect(paramInt3 + 1, paramInt4 + 2, paramInt5 - 2, paramInt6 - 2);
  }
  
  protected void paint(Graphics paramGraphics) {
    if (!this.j) {
      paramGraphics.setClip(0, 0, cx.h, cx.i);
      paramGraphics.setColor(0);
      paramGraphics.fillRect(0, 0, cx.h, cx.i);
      this.j = true;
    } 
    if (this.e == null)
      return; 
    paramGraphics.setClip(cx.j, cx.k, cx.f, cx.g);
    this.e.a(this.o, this.p, paramGraphics);
  }
  
  protected void c() {
    if (b > 0)
      b--; 
    if (n > 0) {
      n--;
      if (n == 0)
        (MGMIDlet.f()).a.vibrate(0); 
    } 
    if (f > -1) {
      f--;
      if (f <= 0)
        MGMIDlet.f().notifyDestroyed(); 
    } 
    this.e.a();
  }
  
  public void run() {
    this.i = false;
    this.l = false;
    while (!this.i) {
      if (!this.l) {
        try {
          this.g = System.currentTimeMillis();
          if (this.e != null) {
            c();
            repaint();
          } 
          this.h = System.currentTimeMillis();
          if (this.h - this.g <= 40L)
            Thread.sleep(40L - this.h - this.g); 
        } catch (Exception exception) {
          d.a(exception);
          exception.printStackTrace();
        } 
        continue;
      } 
      synchronized (this.m) {
        try {
          this.m.wait();
        } catch (InterruptedException interruptedException) {
          interruptedException.printStackTrace();
        } 
      } 
    } 
    System.out.println("End GUIManager");
  }
  
  public void a(int paramInt) {
    if (this.e != null)
      this.e.c(paramInt); 
  }
  
  public static cz d() {
    if (a == null)
      a = new cz(); 
    return a;
  }
  
  public static ae a(String paramString1, String paramString2, String[] paramArrayOfString, int[] paramArrayOfint, int paramInt) {
    return d().b(paramString1, paramString2, paramArrayOfString, paramArrayOfint, paramInt);
  }
  
  public static ae a(String paramString1, String paramString2, String paramString3, int paramInt1, int paramInt2) {
    String[] arrayOfString = new String[1];
    arrayOfString[0] = paramString3;
    int[] arrayOfInt = new int[1];
    arrayOfInt[0] = paramInt1;
    return d().b(paramString1, paramString2, arrayOfString, arrayOfInt, paramInt2);
  }
  
  public static ae a(String paramString1, String paramString2, String paramString3, int paramInt1, String paramString4, int paramInt2, int paramInt3) {
    String[] arrayOfString = new String[2];
    arrayOfString[0] = paramString3;
    arrayOfString[1] = paramString4;
    int[] arrayOfInt = new int[2];
    arrayOfInt[0] = paramInt1;
    arrayOfInt[1] = paramInt2;
    return d().b(paramString1, paramString2, arrayOfString, arrayOfInt, paramInt3);
  }
  
  public static ae a(String paramString1, String paramString2, boolean paramBoolean) {
    boolean bool = true;
    if (paramBoolean)
      bool = false; 
    return d().b(paramString1, paramString2, null, null, bool);
  }
  
  public static ae b(String paramString1, String paramString2, boolean paramBoolean) {
    boolean bool = true;
    if (paramBoolean)
      bool = false; 
    return new du(paramString1, paramString2, null, null, bool);
  }
  
  private ae b(String paramString1, String paramString2, String[] paramArrayOfString, int[] paramArrayOfint, int paramInt) {
    return new du(paramString1, paramString2, paramArrayOfString, paramArrayOfint, paramInt);
  }
  
  public void a(aj paramaj, boolean paramBoolean) {
    a(paramaj, true, paramBoolean);
  }
  
  public void a(aj paramaj, boolean paramBoolean1, boolean paramBoolean2) {
    f();
    if (this.e != null)
      this.e.a(paramaj, paramBoolean1, paramBoolean2); 
  }
  
  public void a(ae paramae) {
    a(paramae, false);
  }
  
  public void a(ae paramae, boolean paramBoolean) {
    f();
    if (paramae != null) {
      paramae.a(paramBoolean);
      if (this.e != null)
        this.e.a(paramae); 
    } 
  }
  
  public void a(boolean paramBoolean) {
    if (this.e != null)
      this.e.a(paramBoolean); 
  }
  
  public void e() {
    if (this.e != null)
      this.e.e(); 
  }
  
  private int e(int paramInt) {
    int i = paramInt;
    switch (i) {
      case -21:
        i = -6;
        break;
      case -22:
        i = -7;
        break;
      case -23:
        i = -8;
        break;
      case -2:
        i = -3;
        break;
      case -5:
        i = -4;
        break;
      case -6:
        i = -2;
        break;
      case -20:
        i = -5;
        break;
    } 
    return i;
  }
  
  private int f(int paramInt) {
    int i = paramInt;
    switch (i) {
      case -59:
        i = -1;
        break;
      case -60:
        i = -2;
        break;
      case -61:
        i = -3;
        break;
      case -62:
        i = -4;
        break;
      case -26:
        i = -5;
        break;
      case -1:
        i = -6;
        break;
      case -4:
        i = -7;
        break;
    } 
    return i;
  }
  
  private int g(int paramInt) {
    int i = paramInt;
    switch (i) {
      case 50:
        i = -1;
        break;
      case 56:
        i = -2;
        break;
      case 52:
        i = -3;
        break;
      case 54:
        i = -4;
        break;
      case 53:
        i = -5;
        break;
    } 
    return i;
  }
  
  public static void f() {
    for (byte b1 = 0; b1 < 21; b1++)
      cx.u[b1] = 0; 
    cx.s = cx.t = -1;
    cx.q = cx.r = -1;
    cx.o = cx.p = -1;
  }
  
  public int b(int paramInt) {
    if (paramInt == 113 || paramInt == 35 || paramInt == 81) {
      paramInt = -6;
    } else if (paramInt == 112 || paramInt == 64 || paramInt == 80) {
      paramInt = -7;
    } 
    return paramInt;
  }
  
  public int c(int paramInt) {
    int i = -1;
    switch (paramInt) {
      case 48:
      case 49:
      case 50:
      case 51:
      case 52:
      case 53:
      case 54:
      case 55:
      case 56:
      case 57:
        i = paramInt - 48;
        if (cx.F && paramInt == 57)
          MGMIDlet.f().e(); 
        break;
      case 42:
        i = 10;
        break;
      case 35:
        i = 11;
        break;
      case -1:
      case 1:
        i = 12;
        break;
      case -2:
      case 6:
        i = 13;
        break;
      case -3:
      case 2:
        i = 14;
        break;
      case -4:
      case 5:
        i = 15;
        break;
      case -5:
      case 10:
      case 13:
        i = 16;
        break;
      case -6:
        if (cx.v == 1) {
          if (b == 0) {
            b = 5;
            i = 17;
          } 
          break;
        } 
        i = 17;
        break;
      case -7:
        if (cx.v == 1) {
          if (b == 0) {
            b = 5;
            i = 18;
          } 
          break;
        } 
        i = 18;
        break;
      case -8:
      case 8:
        i = 19;
        break;
      case -11:
        i = 20;
        break;
    } 
    return i;
  }
  
  protected void keyPressed(int paramInt) {
    if (this.e == null)
      return; 
    if (cx.v == 5) {
      paramInt = b(paramInt);
    } else if (cx.v == 3) {
      paramInt = e(paramInt);
    } else if (cx.v == 6) {
      paramInt = f(paramInt);
    } 
    if (this.e.b())
      paramInt = g(paramInt); 
    int i = c(paramInt);
    if (i > -1)
      if (i > 16) {
        cx.u[i] = 2;
      } else {
        if (i == 16 && this.e.d()) {
          cx.u[i] = 2;
          return;
        } 
        cx.u[i] = 3;
        this.e.a(i);
      }  
  }
  
  protected void keyReleased(int paramInt) {
    if (this.e == null)
      return; 
    if (cx.v == 5) {
      paramInt = b(paramInt);
    } else if (cx.v == 3) {
      paramInt = e(paramInt);
    } else if (cx.v == 6) {
      paramInt = f(paramInt);
    } 
    if (this.e.b())
      paramInt = g(paramInt); 
    int i = c(paramInt);
    if (i > -1) {
      cx.u[i] = 1;
      if (paramInt < 17 && this.e != null)
        this.e.b(i); 
    } 
  }
  
  protected void pointerPressed(int paramInt1, int paramInt2) {
    if (this.e != null)
      this.e.a(paramInt1 - cx.j, paramInt2 - cx.k); 
  }
  
  protected void pointerReleased(int paramInt1, int paramInt2) {
    if (this.e != null)
      this.e.c(paramInt1 - cx.j, paramInt2 - cx.k); 
  }
  
  protected void showNotify() {
    this.j = false;
    setFullScreenMode(true);
    f();
    this.l = false;
    synchronized (this.m) {
      this.m.notify();
    } 
    if (!this.k && this.e != null)
      this.e.h(); 
  }
  
  protected void hideNotify() {
    f();
    if (this.e != null)
      this.e.g(); 
    this.l = true;
    if (!this.k && cx.T && !cx.H) {
      this.k = true;
      du du = new du("Thông tin", "Bấm tiếp tục để chơi tiếp", new String[] { "Tiếp tục" }, new int[] { 1 }, 1);
      du.a(this);
      a(du, false);
    } 
  }
  
  public static void d(int paramInt) {
    n = (int)(paramInt / 40L);
    if (paramInt % 40L > 0L)
      n++; 
    (MGMIDlet.f()).a.vibrate(paramInt);
  }
  
  public void g() {
    if (this.e != null)
      this.e.i(); 
  }
  
  public void b(int paramInt1, int paramInt2) {
    if (paramInt2 == 1) {
      this.k = false;
      if (this.e != null)
        this.e.h(); 
      d().a(false);
    } else if (paramInt2 == 3) {
      d().a(true);
    } else if (paramInt2 == 2) {
      d().a(false);
    } else if (paramInt2 == 4) {
      MGMIDlet.f().d();
    } else if (paramInt2 == 6) {
      d().a(true);
    } else if (paramInt2 == 5) {
      try {
        MGMIDlet.f().a("http://game.ola.vn");
      } catch (ConnectionNotFoundException connectionNotFoundException) {}
      d().a(false);
    } 
  }
}


/* Location:              C:\Users\bot-nosense\Downloads\Loan-12-Su-Quan.jar!\cz.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */