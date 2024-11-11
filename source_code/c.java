public class c {
  public int a;
  
  public int b;
  
  public int c;
  
  public int d;
  
  public int e;
  
  public int[] f;
  
  public int g;
  
  public int h;
  
  public void a() {
    b();
    this.e++;
    this.d = this.h + dd.v(this.e);
    int i = dd.w(this.e) + this.g - this.b;
    this.b = this.g + dd.w(this.e);
    this.c += i;
  }
  
  public void b() {
    this.d = this.h + dd.v(this.e);
    this.b = this.g + dd.w(this.e);
  }
  
  public c c() {
    c c1 = new c();
    c1.a = this.a;
    c1.b = this.b;
    c1.c = this.c;
    c1.d = this.d;
    c1.e = this.e;
    c1.h = this.h;
    c1.g = this.g;
    return c1;
  }
  
  public int a(byte[] paramArrayOfbyte, int paramInt) {
    int i = paramInt;
    System.arraycopy(ai.a(this.a), 0, paramArrayOfbyte, paramInt, 4);
    paramInt += 4;
    System.arraycopy(ai.a(this.b), 0, paramArrayOfbyte, paramInt, 4);
    paramInt += 4;
    System.arraycopy(ai.a(this.c), 0, paramArrayOfbyte, paramInt, 4);
    paramInt += 4;
    System.arraycopy(ai.a(this.d), 0, paramArrayOfbyte, paramInt, 4);
    paramInt += 4;
    System.arraycopy(ai.a(this.e), 0, paramArrayOfbyte, paramInt, 4);
    paramInt += 4;
    System.arraycopy(ai.a(this.g), 0, paramArrayOfbyte, paramInt, 4);
    paramInt += 4;
    System.arraycopy(ai.a(this.h), 0, paramArrayOfbyte, paramInt, 4);
    paramInt += 4;
    System.arraycopy(ai.a(this.f.length), 0, paramArrayOfbyte, paramInt, 4);
    paramInt += 4;
    for (byte b = 0; b < this.f.length; b++) {
      System.arraycopy(ai.a(this.f[b]), 0, paramArrayOfbyte, paramInt, 4);
      paramInt += 4;
    } 
    return paramInt - i;
  }
  
  public int b(byte[] paramArrayOfbyte, int paramInt) {
    int i = paramInt;
    this.a = ai.a(paramArrayOfbyte, paramInt);
    paramInt += 4;
    this.b = ai.a(paramArrayOfbyte, paramInt);
    paramInt += 4;
    this.c = ai.a(paramArrayOfbyte, paramInt);
    paramInt += 4;
    this.d = ai.a(paramArrayOfbyte, paramInt);
    paramInt += 4;
    this.e = ai.a(paramArrayOfbyte, paramInt);
    paramInt += 4;
    this.g = ai.a(paramArrayOfbyte, paramInt);
    paramInt += 4;
    this.h = ai.a(paramArrayOfbyte, paramInt);
    paramInt += 4;
    this.f = new int[ai.a(paramArrayOfbyte, paramInt)];
    paramInt += 4;
    for (byte b = 0; b < this.f.length; b++) {
      this.f[b] = ai.a(paramArrayOfbyte, paramInt);
      paramInt += 4;
    } 
    return paramInt - i;
  }
}


/* Location:              C:\Users\bot-nosense\Downloads\Loan-12-Su-Quan.jar!\c.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */