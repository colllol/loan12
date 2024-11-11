import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class ap extends br {
  protected Image a;
  
  protected int[] b;
  
  protected int[] c;
  
  protected int[] d;
  
  protected int[] e;
  
  protected int[] f;
  
  protected String g = " 0123456789.,:!?()+-*/#$%abcdefghijklmnopqrstuvwxyzáàảãạăắằẳẵặâấầẩẫậéèẻẽẹêếềểễệíìỉĩịóòỏõọôốồổỗộơớờởỡợúùủũụưứừửữựýỳỷỹỵđABCDĐEFGHIJKLMNOPQRSTUVWXYZÁÀẢÃẠĂẮẰẲẴẶÂẤẦẨẪẬÉÈẺẼẸÊẾỀỂỄỆÍÌỈĨỊÓÒỎÕỌÔỐỒỔỖỘƠỚỜỞỠỢÚÙỦŨỤƯỨỪỬỮỰÝỲỶỸỴ\\\"@<=>;_&'`^~{}";
  
  protected String h = "";
  
  protected int i;
  
  public void a(Graphics paramGraphics, String paramString, int paramInt1, int paramInt2, int paramInt3) {
    int i;
    int j = paramString.length();
    int k = a(paramString);
    if (paramInt3 == 0) {
      i = paramInt1;
    } else if (paramInt3 == 2) {
      i = paramInt1 - k;
    } else {
      i = paramInt1 - (k >> 1);
    } 
    for (byte b = 0; b < j; b++) {
      int m = this.g.indexOf(paramString.charAt(b));
      if (m == -1)
        m = this.h.indexOf(paramString.charAt(b)); 
      if (m == -1)
        m = 0; 
      if (m > -1)
        y.a(paramGraphics, this.a, this.b[m], this.c[m], this.d[m], this.e[m], i, paramInt2 - 1 + this.f[m], 20); 
      i += this.d[m] + 1;
    } 
  }
  
  public int a() {
    return this.i;
  }
  
  public int a(String paramString) {
    int i = 0;
    for (byte b = 0; b < paramString.length(); b++)
      i = i + 1 + a(paramString.charAt(b)); 
    return i;
  }
  
  public int a(char paramChar) {
    int i = this.g.indexOf(paramChar);
    if (i == -1)
      i = this.h.indexOf(paramChar); 
    return (i > -1) ? this.d[i] : this.d[0];
  }
}


/* Location:              C:\Users\bot-nosense\Downloads\Loan-12-Su-Quan.jar!\ap.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */