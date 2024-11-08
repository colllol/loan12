import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;

public class as extends br {
  Font a = Font.getDefaultFont();
  
  public int a() {
    return this.a.getHeight();
  }
  
  public int a(String paramString) {
    return this.a.stringWidth(paramString);
  }
  
  public void a(Graphics paramGraphics, String paramString, int paramInt1, int paramInt2, int paramInt3) {
    if (paramInt3 == 0) {
      paramGraphics.drawString(paramString, paramInt1, paramInt2, 0);
    } else if (paramInt3 == 2) {
      paramGraphics.drawString(paramString, paramInt1, paramInt2, 24);
    } else if (paramInt3 == 1) {
      paramGraphics.drawString(paramString, paramInt1, paramInt2, 17);
    } 
  }
  
  public int a(char paramChar) {
    return this.a.charWidth(paramChar);
  }
}


/* Location:              C:\Users\bot-nosense\Downloads\Loan-12-Su-Quan.jar!\as.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */