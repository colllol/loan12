public class dx {
  public int a;
  
  public int b;
  
  public boolean a() {
    if (this.b > 0) {
      this.b--;
      return true;
    } 
    return false;
  }
  
  public int a(byte[] paramArrayOfbyte, int paramInt) {
    int i = paramInt;
    System.arraycopy(ai.a(this.a), 0, paramArrayOfbyte, paramInt, 4);
    paramInt += 4;
    System.arraycopy(ai.a(this.b), 0, paramArrayOfbyte, paramInt, 4);
    paramInt += 4;
    return paramInt - i;
  }
  
  public int b(byte[] paramArrayOfbyte, int paramInt) {
    int i = paramInt;
    this.a = ai.a(paramArrayOfbyte, paramInt);
    paramInt += 4;
    this.b = ai.a(paramArrayOfbyte, paramInt);
    paramInt += 4;
    return paramInt - i;
  }
}


/* Location:              C:\Users\bot-nosense\Downloads\Loan-12-Su-Quan.jar!\dx.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */