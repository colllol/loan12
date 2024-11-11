package com.mic.smsgame.suquan;

import ae;
import an;
import com.mg.smsgame.main.MGMIDlet;
import cz;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.TextBox;

class r implements CommandListener {
  final ac a;
  
  private final TextBox b;
  
  r(ac paramac, TextBox paramTextBox) {
    this.a = paramac;
    this.b = paramTextBox;
  }
  
  public void commandAction(Command paramCommand, Displayable paramDisplayable) {
    if (paramCommand.getLabel().equals("Gửi")) {
      if (this.b.getString() == null || "".equals(this.b.getString())) {
        ae ae = cz.a("Chú ý", "Bạn phải nhập vào số điện thoại người nhận!", "Đóng", 5, 1);
        ae.a(this.a);
        cz.d().a(ae, false);
      } else {
        an.a(this.b.getString());
      } 
      (MGMIDlet.f()).a.setCurrent((Displayable)cz.d());
    } else {
      (MGMIDlet.f()).a.setCurrent((Displayable)cz.d());
    } 
  }
}


/* Location:              C:\Users\bot-nosense\Downloads\Loan-12-Su-Quan.jar!\com\mic\smsgame\suquan\r.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */