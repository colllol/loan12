import com.mic.smsgame.suquan.ad;
import com.mic.smsgame.suquan.k;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class bs {
  private static bs K;
  
  public Image a;
  
  public Image b = Image.createImage(cx.f, cx.g);
  
  public Image[] c;
  
  public Image[] d;
  
  public Image[] e;
  
  public Image[] f;
  
  public Image[] g;
  
  public Image[] h;
  
  public Image[] i;
  
  public Image[] j;
  
  public Image[] k;
  
  public Image l;
  
  public Image m;
  
  public Image n;
  
  public Image o;
  
  public Image p;
  
  public Image q;
  
  public Image r;
  
  public Image s;
  
  public Image t;
  
  public Image u;
  
  public Image v;
  
  public Image w;
  
  public Image x;
  
  public Image y;
  
  public Image z;
  
  public Image A;
  
  public Image B;
  
  public Image C;
  
  public Image D;
  
  public Image E;
  
  public ad[] F;
  
  public ad G;
  
  public ad H;
  
  public ad I;
  
  public k J;
  
  public static bs a() {
    return K;
  }
  
  public static void b() {
    K = new bs();
  }
  
  public void a(int[] paramArrayOfint) {
    for (byte b = 0; b < paramArrayOfint.length; b++) {
      switch (paramArrayOfint[b]) {
        case 0:
        case 1:
          if (this.r == null)
            this.r = az.b("/fireball"); 
          break;
        case 2:
          if (this.s == null)
            this.s = az.b("/hellfire"); 
          break;
        case 5:
          if (this.t == null)
            this.t = az.b("/blastlighting"); 
          break;
        case 8:
          if (this.v == null)
            this.v = az.b("/frozen"); 
        case 6:
          if (this.u == null)
            this.u = az.b("/icebolt"); 
          break;
        case 7:
          if (this.w == null)
            this.w = az.b("/healing"); 
          break;
        case 4:
          if (this.x == null)
            this.x = az.b("/shieldlighting"); 
          break;
        case 3:
          if (this.y == null) {
            this.y = az.b("/chainlighting");
            this.h = new Image[2];
            this.h[0] = az.b("/chainlightingpart1");
            this.h[1] = az.b("/chainlightingpart2");
          } 
          break;
      } 
    } 
  }
  
  public void c() {
    this.G = new ad("explodechess");
    if (!cx.V) {
      this.I = new ad("attack");
      this.H = new ad("useitem");
    } 
    this.J = new k();
    this.J.a("win", 1);
  }
  
  public void d() {
    i();
    if (this.J != null) {
      this.J.a("win", 1);
      this.J.a(123015);
      this.J.d();
    } 
  }
  
  public void e() {
    i();
    if (this.J != null) {
      this.J.a("lose", 1);
      this.J.d();
      this.J.a(155041);
    } 
  }
  
  public void b(int[] paramArrayOfint) {
    if (cx.V)
      return; 
    for (byte b = 0; b < paramArrayOfint.length; b++) {
      switch (paramArrayOfint[b]) {
        case 0:
          if (this.F[0] == null)
            this.F[0] = new ad("fire1"); 
          break;
        case 1:
          if (this.F[1] == null)
            this.F[1] = new ad("fire2"); 
          break;
        case 2:
          if (this.F[2] == null)
            this.F[2] = new ad("fire3"); 
          break;
        case 4:
          if (this.F[4] == null)
            this.F[4] = new ad("lightning2"); 
          break;
        case 3:
          if (this.F[3] == null)
            this.F[3] = new ad("lightning1"); 
          break;
        case 5:
          if (this.F[5] == null)
            this.F[5] = new ad("lightning3"); 
          break;
        case 6:
          if (this.F[6] == null)
            this.F[6] = new ad("ice1"); 
          break;
        case 7:
          if (this.F[7] == null)
            this.F[7] = new ad("ice2"); 
          break;
        case 8:
          if (this.F[8] == null)
            this.F[8] = new ad("ice3"); 
          break;
      } 
    } 
  }
  
  private void c(ad paramad) {
    if (paramad != null && paramad.e())
      paramad.d(); 
  }
  
  private boolean l() {
    for (int i = this.F.length - 1; i >= 0; i--) {
      if (this.F[i] != null && this.F[i].e())
        return true; 
    } 
    return false;
  }
  
  public void a(int paramInt) {
    if (this.F == null)
      return; 
    if (d(this.F[paramInt]))
      return; 
    i();
    a(this.F[paramInt]);
  }
  
  public void f() {
    if (l())
      return; 
    if (d(this.I))
      return; 
    c(this.H);
    c(this.G);
    a(this.I);
  }
  
  public void g() {
    if (l())
      return; 
    if (d(this.I))
      return; 
    if (d(this.H))
      return; 
    if (!d(this.G))
      a(this.G); 
  }
  
  public void h() {
    if (l())
      return; 
    if (d(this.I))
      return; 
    if (d(this.H))
      return; 
    c(this.G);
    a(this.H);
  }
  
  public void i() {
    for (int i = this.F.length - 1; i >= 0; i--)
      c(this.F[i]); 
    c(this.I);
    c(this.G);
    c(this.H);
  }
  
  private void m() {
    for (int i = this.F.length - 1; i >= 0; i--)
      b(this.F[i]); 
    b(this.I);
    b(this.H);
    this.F = null;
    this.I = null;
    this.G = null;
    this.H = null;
  }
  
  public void j() {
    m();
    if (this.J != null)
      this.J.b(); 
  }
  
  private boolean d(ad paramad) {
    return (paramad == null) ? false : paramad.e();
  }
  
  public void a(ad paramad) {
    if (paramad == null)
      return; 
    paramad.b();
  }
  
  public void b(ad paramad) {
    if (paramad == null)
      return; 
    paramad.a();
  }
  
  public void c(int[] paramArrayOfint) {
    this.i = new Image[paramArrayOfint.length];
    for (byte b = 0; b < paramArrayOfint.length; b++) {
      switch (paramArrayOfint[b]) {
        case 0:
          this.i[b] = az.b("/fireballicon");
          break;
        case 1:
          this.i[b] = az.b("/meteoricon");
          break;
        case 2:
          this.i[b] = az.b("/hellfireicon");
          break;
        case 5:
          this.i[b] = az.b("/blastlightingicon");
          break;
        case 6:
          this.i[b] = az.b("/icebolticon");
          break;
        case 8:
          this.i[b] = az.b("/frozenicon");
          break;
        case 7:
          this.i[b] = az.b("/healingicon");
          break;
        case 4:
          this.i[b] = az.b("/shieldlightingicon");
          break;
        case 3:
          this.i[b] = az.b("/chainlightingicon");
          break;
      } 
    } 
  }
  
  protected bs() {
    Graphics graphics = this.b.getGraphics();
    Image image = az.b("/bkboard");
    graphics.drawImage(image, cx.l, cx.m, 0);
    image = null;
    String[] arrayOfString1 = { "/crystalgray", "/crystalgreen", "/crystalred", "/crystalblue", "/crystalyellow", "/crystalviolet", "" };
    String[] arrayOfString2 = { "/sword", "/rice", "/heart", "/yinyang", "/gold", "/book", "/swordred" };
    String[] arrayOfString3 = { "/explodesword", "/exploderice", "/explodeheart", "/explodeyinyang", "/explodegold", "/explodebook", "" };
    String[] arrayOfString4 = { "", "/explodericex2", "/explodeheartx2", "/explodeyinyangx2", "/explodegoldx2", "/explodebookx2", "" };
    this.e = new Image[7];
    this.f = new Image[7];
    this.d = new Image[7];
    this.c = new Image[7];
    int i;
    for (i = this.e.length - 1; i > -1; i--) {
      this.d[i] = az.b(arrayOfString2[i]);
      this.e[i] = az.b(arrayOfString3[i]);
      this.f[i] = az.b(arrayOfString4[i]);
      this.c[i] = az.b(arrayOfString1[i]);
    } 
    this.c[6] = this.c[0];
    this.e[6] = this.e[0];
    this.f[6] = this.e[0];
    this.f[0] = this.e[0];
    this.a = az.b("/star");
    this.l = az.b("/blood");
    this.m = az.b("/bloodext");
    this.n = az.b("/mana");
    this.o = az.b("/food");
    this.p = az.b("/bloodthrowaround");
    this.j = new Image[2];
    this.g = new Image[2];
    for (i = 0; i < this.j.length; i++) {
      this.j[i] = az.b("/arrowfocus" + (i + 1));
      this.g[i] = az.b("/focus" + (i + 1));
    } 
    this.q = az.b("/zoomfocus1");
    this.k = new Image[2];
    this.k[0] = az.b("/frame1");
    this.k[1] = az.b("/frame2");
    this.z = az.b("/frame3");
    this.A = az.b("/focusitem");
    this.B = az.b("/numbersmall");
    this.D = az.b("/powerattack");
    this.C = az.b("/defenceshield");
    this.E = az.b("/itemshops");
    this.F = new ad[9];
  }
  
  public Image[] a(int paramInt1, int paramInt2) {
    Image[] arrayOfImage = new Image[2];
    Image image = az.b("/avatar");
    arrayOfImage[0] = Image.createImage(image, paramInt1 % 10 * 30, paramInt1 / 10 * 30, 30, 30, 2);
    arrayOfImage[1] = Image.createImage(image, paramInt2 % 10 * 30, paramInt2 / 10 * 30, 30, 30, 0);
    return arrayOfImage;
  }
  
  public static void k() {
    System.out.println("clear");
    K.j();
    K = null;
  }
}


/* Location:              C:\Users\bot-nosense\Downloads\Loan-12-Su-Quan.jar!\bs.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */