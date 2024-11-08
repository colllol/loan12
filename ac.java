public class ac extends ch {
  public ac(bu parambu, int paramInt) {
    super(parambu, paramInt);
  }
  
  public void a(int paramInt) {
    switch (paramInt) {
      case 12:
        this.b.m();
        break;
      case 13:
        this.b.n();
        break;
      case 14:
        this.b.o();
        break;
      case 15:
        this.b.p();
        break;
      case 16:
        this.b.i();
        break;
      case 17:
        this.b.r();
        break;
      case 18:
        this.b.g();
        break;
      case 11:
        this.b.s();
        break;
    } 
  }
  
  public void a(int paramInt1, int paramInt2) {
    if (paramInt2 >= cx.g - 15) {
      int i = cx.f / 3;
      int j = i + i;
      if (paramInt1 > j) {
        this.b.g();
      } else if (paramInt1 > i) {
        this.b.s();
      } else {
        this.b.r();
      } 
      return;
    } 
    dl dl = this.b.h();
    if (dl.b(paramInt1, paramInt2)) {
      int i = (paramInt1 - dl.a) / 21 + 2;
      int j = (paramInt2 - dl.b) / 21 + 2;
      int k = this.b.k();
      int m = this.b.l();
      if (j == k && i == m) {
        this.b.i();
      } else if (this.b.j()) {
        int n = j - k;
        int i1 = i - m;
        if (n == 1) {
          this.b.n();
        } else if (n == -1) {
          this.b.m();
        } else if (i1 == 1) {
          this.b.p();
        } else if (i1 == -1) {
          this.b.o();
        } 
      } else {
        this.b.c(j, i);
      } 
    } 
  }
  
  public void a() {}
  
  public void b() {
    this.b.c(1);
  }
}


/* Location:              C:\Users\bot-nosense\Downloads\Loan-12-Su-Quan.jar!\ac.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */