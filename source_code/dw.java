class dw {
  int[] a = new int[4];
  
  long b = 0L;
  
  byte[] c = new byte[64];
  
  public dw() {
    this.a[0] = 1732584193;
    this.a[1] = -271733879;
    this.a[2] = -1732584194;
    this.a[3] = 271733878;
  }
  
  public dw(dw paramdw) {
    this();
    byte b;
    for (b = 0; b < this.c.length; b++)
      this.c[b] = paramdw.c[b]; 
    for (b = 0; b < this.a.length; b++)
      this.a[b] = paramdw.a[b]; 
    this.b = paramdw.b;
  }
}


/* Location:              C:\Users\bot-nosense\Downloads\Loan-12-Su-Quan.jar!\dw.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */