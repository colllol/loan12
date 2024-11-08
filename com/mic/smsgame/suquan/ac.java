package com.mic.smsgame.suquan;

import ae;
import aj;
import ar;
import aw;
import az;
import bg;
import br;
import bz;
import cf;
import com.mg.smsgame.main.MGMIDlet;
import com.mg.smsgame.main.b;
import cx;
import cz;
import d;
import da;
import de;
import df;
import di;
import dj;
import ds;
import dt;
import dv;
import dy;
import dz;
import e;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.TextBox;
import x;
import y;

public class ac extends aj implements b, dy {
  protected da c;
  
  protected da d;
  
  final int e = 0;
  
  final int f = 1;
  
  final int t = 2;
  
  dv u;
  
  int[] v;
  
  private boolean w;
  
  private ds[] x;
  
  private c y;
  
  private Image z;
  
  private int A;
  
  private boolean B;
  
  private dz C;
  
  public ac() {
    cx.B = false;
    cx.N = true;
    b();
    this.u = new dv(true);
    this.u.a(this);
    E();
    this.C = (dz)new aw(az.b("/bkmenu"), cx.l, cx.m, 20);
    this.y = new c(((ar)this).b_ / 2, cx.m + 110);
    this.z = az.b("/swordfocus");
    this.w = !f.c();
    if (this.w) {
      e(0);
    } else {
      e(1);
    } 
    a(this.C);
    a(this);
    a(null);
    cf.a().a("menu", -1);
    d.a("+++++++++++Tempomenu=" + cf.a().f());
    cf.a().a(120000);
    cf.a().e();
    ((ar)this).q = 7;
  }
  
  private void E() {
    String[] arrayOfString = { "strcontinuegame", "strnewgame", "strrecord", "strshop", "strinformation", "strgivegame", "strchatola", "strothergame", "strguide", "strauthor" };
    this.x = (ds[])new v[arrayOfString.length];
    Image[] arrayOfImage = new Image[2];
    for (byte b1 = 0; b1 < this.x.length; b1++) {
      arrayOfImage = new Image[2];
      arrayOfImage[0] = az.b("/" + arrayOfString[b1]);
      arrayOfImage[1] = az.b("/" + arrayOfString[b1] + "focus");
      this.x[b1] = (ds)new v(arrayOfImage);
    } 
  }
  
  protected void e(int paramInt) {
    switch (paramInt) {
      case 0:
        this.v = new int[] { 1, 2, 3, 4 };
        break;
      case 1:
        this.v = new int[] { 0, 1, 2, 3, 4 };
        break;
      case 2:
        this.v = new int[] { 5, 6, 7, 8, 9 };
        break;
    } 
    if (paramInt == 2) {
      b((de)this.d);
    } else {
      b((de)this.c);
    } 
    u();
  }
  
  protected void b() {
    this.c = new da("Thoát", 1);
    this.d = new da("Trở về", 2);
  }
  
  protected void u() {
    ds[] arrayOfDs = new ds[this.v.length];
    int i = 130 + cx.m;
    int j = ((ar)this).b_ / 2;
    for (byte b1 = 0; b1 < arrayOfDs.length; b1++) {
      arrayOfDs[b1] = this.x[this.v[b1]];
      arrayOfDs[b1].d(j, i);
      arrayOfDs[b1].k(17);
      arrayOfDs[b1].a(false);
      i += 24;
    } 
    this.u.a(arrayOfDs, 0);
  }
  
  public final void a(Graphics paramGraphics) {
    b(paramGraphics);
    e(paramGraphics);
  }
  
  protected void b(Graphics paramGraphics) {
    this.y.a(paramGraphics);
  }
  
  protected void e(Graphics paramGraphics) {
    this.u.a(paramGraphics);
    if (!this.u.c()) {
      ds ds1 = this.u.a();
      int i = ds1.i();
      int j = ds1.j() + 3;
      y.a(paramGraphics, this.z, 0, 0, this.z.getWidth(), this.z.getHeight(), i - 2 - this.A, j, 24);
      paramGraphics.drawRegion(this.z, 0, 0, this.z.getWidth(), this.z.getHeight(), 2, i + ds1.k() + 2 + this.A, j, 20);
    } 
  }
  
  public void a() {
    if (this.y.m())
      this.y.a(); 
    this.u.b();
    if (this.B) {
      this.A--;
      if (this.A == 0)
        this.B = false; 
    } else {
      this.A++;
      if (this.A == 3)
        this.B = true; 
    } 
  }
  
  protected void v() {
    ae ae = cz.a("Chú ý", "Cập nhật game miễn phí mới nhất tại www.giaitri321.pro", "Có", 3, "Không", 4, 1);
    ae.a(this);
    cz.d().a(ae, true);
  }
  
  protected void w() {
    MGMIDlet.f().d();
  }
  
  protected void x() {
    (cz.d()).e.a(2, 3, true);
  }
  
  protected void y() {
    (cz.d()).e.a(2, 4, true);
  }
  
  protected void z() {
    TextBox textBox = new TextBox("Nhap so dien thoai nguoi nhan", "", 20, 3);
    textBox.addCommand(new Command("Gửi", 4, 1));
    textBox.addCommand(new Command("Đóng", 7, 1));
    textBox.setCommandListener(new r(this, textBox));
    (MGMIDlet.f()).a.setCurrent((Displayable)textBox);
  }
  
  protected void A() {
    cz.d().a(false);
    z();
  }
  
  protected void a(Image paramImage) {
    dj dj = new dj("Chat Ola", dj.a("007#sfl- Phần mềm chat trên điện thoại đi động với chi phí cực thấp.#sfl- Kết nối Yahoo.#sfl- Chia sẻ hình ảnh, video clip trực tiếp.#sfl- Đọc báo Online.#sfl- Tra từ điển tiếng anh trực tuyến.#sfl- Tra bài hát Karaoke.#sfl- Chơi game trực tuyến...", null, null, (br)bz.d, (br)bz.c));
    dj.b((de)new da("Đóng", 6));
    dj.a((de)new da("Tải về", 8));
    dj.a(null);
    dj.a(this.C);
    dj.a(this);
    cz.d().a((ae)dj);
  }
  
  protected void B() {
    try {
      try {
        if (Class.forName("javax.microedition.io.file.FileConnection") != null)
          MGMIDlet.f().a("http://chat.ola.vn/ola/olamobileext39.jad"); 
      } catch (Throwable throwable) {
        MGMIDlet.f().a("http://chat.ola.vn/ola/olamobile.jad");
      } 
    } catch (Exception exception) {
      exception.printStackTrace();
    } 
  }
  
  protected void a(String[][] paramArrayOfString, Image paramImage) {
    if (paramArrayOfString == null)
      return; 
    dt[] arrayOfDt = new dt[paramArrayOfString.length];
    for (byte b1 = 0; b1 < arrayOfDt.length; b1++) {
      arrayOfDt[b1] = new dt(paramArrayOfString[b1], true, 1);
      (arrayOfDt[b1]).d = -1;
    } 
    dj dj = new dj("Thông tin", (e[])arrayOfDt);
    dj.c((de)new da("Đóng", 6));
    dj.a(null);
    dj.a(this.C);
    dj.a(this);
    cz.d().a((ae)dj);
  }
  
  protected void h(int paramInt1, int paramInt2) {
    ae ae = cz.a("Chú ý", "bạn có muốn chơi lại từ đầu không?", "Có", paramInt1, "Không", paramInt2, 1);
    ae.a(this);
    cz.d().a(ae, true);
  }
  
  protected void C() {
    ae ae = cz.a("Chú ý", "Cập nhật game miễn phí mới nhất tại www.giaitri321.pro", "Có", 7, "Không", 4, 1);
    ae.a(this);
    cz.d().a(ae, true);
  }
  
  protected void D() {
    cz.d().a(true);
    try {
      MGMIDlet.f().a("http://giaitri321.pro");
    } catch (Exception exception) {
      exception.printStackTrace();
    } 
  }
  
  public void a(int paramInt) {
    this.u.a(paramInt);
  }
  
  public void a(int paramInt1, int paramInt2) {
    this.u.a(paramInt1, paramInt2);
  }
  
  public void b(int paramInt1, int paramInt2) {
    switch (paramInt2) {
      case 1:
        v();
        break;
      case 4:
        cz.d().a(true);
        break;
      case 6:
        cz.d().a(false);
        break;
      case 3:
        w();
        break;
      case 2:
        if (!this.w) {
          e(1);
          break;
        } 
        e(0);
        break;
      case 7:
        D();
        break;
      case 8:
        B();
        break;
      case 5:
        A();
        break;
      case 9:
        f.a();
        bg.k();
        bg.x();
        cz.d().a(false);
        cf.a().d();
        cz.d().a((aj)new df(true), false);
        break;
    } 
  }
  
  private void F() {
    di di = new di();
    di.a(new x(az.b("/sword")));
    di.a(new x(az.b("/yinyang")));
    di.a(new x(az.b("/rice")));
    di.a(new x(az.b("/gold")));
    di.a(new x(az.b("/book")));
    di.a(new x(az.b("/heart")));
    di.a(new x(az.b("/swordred")));
    dj dj = dj.a("Hướng dẫn", "**Tình tiết, cốt truyện và tên nhân vật phụ của trò chơi đều dựa vào sự kiện Loạn 12 Sứ Quân trong lịch sử Việt Nam ở thế kỷ X.@*Luật chơi:@- Một lượt đi phải xếp ít nhất 3 biểu tượng cùng loại nằm sát nhau theo hàng ngang hoặc dọc.@- Xếp 4 biểu tượng trở lên được thưởng thêm 1 lượt đi.@- Xếp 5 biểu tượng trở lên được thưởng 1 vật phẩm hỗ trợ.@{i}@- Xếp biểu tượng thanh kiếm để tấn công đối phương.@{i}@- Xếp biểu tượng âm dương để lấy năng lượng sử dụng tuyệt chiêu.@{i}@- Xếp biểu tượng bánh chưng để lấy lương thực.@{i}@- Xếp biểu tượng thỏi vàng để lấy tiền vàng.@{i}@- Xếp biểu tượng cuốn sách để lấy kinh nghiệm.@{i}@- Xếp biểu tượng trái tim để phục hồi sinh lực (máu).@{i}@- Kiếm lửa có thể xếp cùng với kiếm thường.@- Kiếm lửa có lực tấn công mạnh hơn và làm nổ 1 ô bao quanh nó.@- Hết sinh lực sẽ bị thua trận.@- Lương thực tiêu hao trong lượt đi của mỗi đối phương.@- Điểm kinh nghiệm dùng để tăng cấp độ.@- Cấp độ càng cao thì lực tấn công và sinh lực càng lớn.@- Tiền vàng dùng để mua vật dụng hỗ trợ trong cửa hàng.@- Nếu xếp sai biểu tượng sẽ bị mất lượt đi và mất sinh lực.@*Chú ý: Vật dụng trong cửa hàng rất hữu dụng trong các trận chiến. Bạn nên mua thường xuyên và sử dụng khi cần thiết.", null, di, null, null);
    dj.c((de)new da("Đóng", 4));
    dj.a(this);
    cz.d().a((ae)dj, true);
  }
  
  public void f(int paramInt1, int paramInt2) {}
  
  public void a(String paramString, int paramInt) {
    int i = this.v[paramInt];
    switch (i) {
      case 0:
        if (f.e()) {
          cf.a().d();
          (cz.d()).e.a(2, 5, false);
        } else {
          cf.a().d();
          (cz.d()).e.a(2, 7, false);
        } 
        return;
      case 1:
        if (!this.w) {
          h(9, 4);
          return;
        } 
        cf.a().d();
        bg.k();
        bg.x();
        cz.d().a((aj)new df(true), false);
        return;
      case 2:
        x();
        return;
      case 3:
        y();
        return;
      case 4:
        e(2);
        return;
      case 5:
        z();
        return;
      case 6:
        a((Image)null);
        return;
      case 7:
        C();
        return;
      case 8:
        F();
        return;
    } 
    a(h.f, (Image)null);
  }
}


/* Location:              C:\Users\bot-nosense\Downloads\Loan-12-Su-Quan.jar!\com\mic\smsgame\suquan\ac.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */