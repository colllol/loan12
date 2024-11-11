package com.mic.smsgame.suquan;

import ai;
import aj;
import ar;
import bs;
import bu;
import c;
import com.mg.smsgame.main.b;
import cq;
import cz;
import d;
import dd;
import i;
import javax.microedition.lcdui.Graphics;

public class ab extends aj implements e {
  cq c;
  
  i d;
  
  bu e;
  
  public ab() {
    long l = System.currentTimeMillis();
    try {
      bs.b();
      try {
        byte[] arrayOfByte = (byte[])null;
        int j = 0;
        if (f.e()) {
          System.out.println("khasc nullllllllllllll");
          arrayOfByte = f.d();
          f.f();
          dd.c = ai.a(arrayOfByte, j);
          j += true;
          dd.e = new c();
          j += dd.e.b(arrayOfByte, j);
          dd.f = new c();
          j += dd.f.b(arrayOfByte, j);
        } 
        this.c = new cq(dd.d(dd.c), dd.e, dd.f);
        this.d = new i(this.c);
        this.e = new bu(this.c, this.d);
        this.e.a(arrayOfByte, j);
      } catch (Exception exception) {
        exception.printStackTrace();
        d.a(exception);
        f.f();
        cz.a().a(5, 7, false);
        return;
      } 
      a((b)cz.a());
      a(null);
      dd.h();
      try {
        bs.a().c();
        bs.a().b(dd.e.f);
        bs.a().b(dd.f.f);
      } catch (Exception exception) {
        bs.a().j();
        exception.printStackTrace();
        d.a(exception);
      } 
    } catch (Exception exception) {
      exception.printStackTrace();
      d.a(exception);
      cz.a().a(5, 7, false);
    } 
    d.a("DONE LOADPLAY" + (System.currentTimeMillis() - l));
    ((ar)this).q = 5;
  }
  
  public void r() {
    bs.k();
  }
  
  public void a(int paramInt) {
    this.e.b(paramInt);
  }
  
  public void a(int paramInt1, int paramInt2) {
    this.e.a(paramInt1, paramInt2);
  }
  
  public void a() {
    this.e.c();
    this.d.d();
  }
  
  public void a(Graphics paramGraphics) {
    this.d.a(paramGraphics);
  }
  
  public void e() {
    this.e.t();
  }
  
  public void t() {
    this.d.a(false);
  }
  
  public void s() {
    this.d.a(true);
  }
}


/* Location:              C:\Users\bot-nosense\Downloads\Loan-12-Su-Quan.jar!\com\mic\smsgame\suquan\ab.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */