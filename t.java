import com.mic.smsgame.suquan.a;
import com.mic.smsgame.suquan.e;

public class t extends ch implements at, e {
  public t(bu parambu, int paramInt) {
    super(parambu, paramInt);
  }
  
  public void a() {
    System.out.println("start AI");
    ci.a().a(this);
  }
  
  public void b() {}
  
  public void c() {
    try {
      try {
        Thread.sleep(1000L);
      } catch (InterruptedException interruptedException) {
        interruptedException.printStackTrace();
      } 
      this.b.c(120);
      di di = this.b.q();
      if (di != null) {
        int i = 0;
        int j = 0;
        for (int k = di.b() - 1; k > -1; k--) {
          int n = 0;
          a a1 = (a)di.b(k);
          int i1 = this.b.d(a1.a, a1.b);
          int i2 = this.b.d(a1.c, a1.d);
          int[] arrayOfInt = { i2, i2, i1, i1 };
          for (int i3 = a1.e.length - 1; i3 > -1; i3--) {
            int i4 = a1.e[i3] & 0xFF;
            if (i4 >= 3) {
              if (i3 == 0 || i3 == 2) {
                arrayOfInt[i3] = this.b.a(arrayOfInt[i3], a1.a, a1.b, (a1.e[i3] & 0xFF0000) >> 16, (a1.e[i3] & 0xFF00) >> 8);
              } else {
                arrayOfInt[i3] = this.b.b(arrayOfInt[i3], a1.a, a1.b, (a1.e[i3] & 0xFF0000) >> 16, (a1.e[i3] & 0xFF00) >> 8);
              } 
              int i5 = this.b.e(arrayOfInt[i3], i4);
              n += i5;
            } 
          } 
          if (n >= i && !this.b.e(this.a)) {
            j = k;
            i = n;
          } 
        } 
        a a = (a)di.b(j);
        int m = this.b.d();
        if (m >= 1 && ay.a(100) < 50) {
          try {
            Thread.sleep(1000L);
          } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
          } 
          this.b.a(ay.a(m));
        } else {
          this.b.a(a);
          try {
            Thread.sleep(1000L);
          } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
          } 
          this.b.a(a.a, a.b, a.c, a.d);
        } 
      } 
    } catch (Exception exception) {
      d.a(exception);
      exception.printStackTrace();
    } 
  }
}


/* Location:              C:\Users\bot-nosense\Downloads\Loan-12-Su-Quan.jar!\t.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */