class bd {
  int a;
  
  int b;
  
  int[] c;
  
  byte[] d;
  
  byte[] a() {
    int i = 12 + this.c.length * 4;
    byte[] arrayOfByte = new byte[i];
    boolean bool = false;
    System.arraycopy(ai.a(this.a), 0, arrayOfByte, bool, 4);
    bool += true;
    System.arraycopy(ai.a(this.b), 0, arrayOfByte, bool, 4);
    bool += true;
    System.arraycopy(ai.a(this.c.length), 0, arrayOfByte, bool, 4);
    bool += true;
    for (byte b = 0; b < this.c.length; b++) {
      System.arraycopy(ai.a(this.c[b]), 0, arrayOfByte, bool, 4);
      bool += true;
    } 
    return arrayOfByte;
  }
  
  int a(byte[] paramArrayOfbyte, int paramInt) {
    int i = paramInt;
    this.a = ai.a(paramArrayOfbyte, paramInt);
    paramInt += 4;
    this.b = ai.a(paramArrayOfbyte, paramInt);
    paramInt += 4;
    this.c = new int[ai.a(paramArrayOfbyte, paramInt)];
    paramInt += 4;
    for (byte b = 0; b < this.c.length; b++) {
      this.c[b] = ai.a(paramArrayOfbyte, paramInt);
      paramInt += 4;
    } 
    return paramInt - i;
  }
}


/* Location:              C:\Users\bot-nosense\Downloads\Loan-12-Su-Quan.jar!\bd.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */