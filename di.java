public class di {
  Object[] a;
  
  private int b = 0;
  
  public di() {}
  
  public di(int paramInt) {
    this();
    this.a = new Object[paramInt];
  }
  
  public void a(Object paramObject) {
    if (this.a == null)
      this.a = new Object[10]; 
    if (this.b >= this.a.length) {
      Object[] arrayOfObject = new Object[this.a.length + 10];
      System.arraycopy(this.a, 0, arrayOfObject, 0, this.a.length);
      this.a = arrayOfObject;
      arrayOfObject = (Object[])null;
    } 
    this.a[this.b] = paramObject;
    this.b++;
  }
  
  public Object a(int paramInt) {
    if (this.a == null)
      return null; 
    if (paramInt < 0 || paramInt > b())
      throw new ArrayIndexOutOfBoundsException(); 
    Object object = this.a[paramInt];
    this.a[paramInt] = null;
    this.b--;
    if (paramInt < this.a.length - 1)
      System.arraycopy(this.a, paramInt + 1, this.a, paramInt, this.b - paramInt); 
    return object;
  }
  
  public void a() {
    this.a = null;
    this.b = 0;
  }
  
  public void b(Object paramObject) {
    if (this.a == null)
      return; 
    for (byte b = 0; b < this.b; b++) {
      if (this.a[b].equals(paramObject)) {
        a(b);
        break;
      } 
    } 
  }
  
  public Object b(int paramInt) {
    return (this.a == null) ? null : ((paramInt >= this.b || paramInt < 0) ? null : this.a[paramInt]);
  }
  
  public int b() {
    return this.b;
  }
  
  public String toString() {
    String str = "";
    if (b() == 0) {
      str = "[Size]: 0";
    } else {
      for (byte b = 0; b < this.b; b++) {
        if (this.a[b] != null) {
          str = String.valueOf(str) + "\n[Index]: " + b + " [Object]: " + this.a[b].toString();
        } else {
          str = String.valueOf(str) + "\n[Index]: " + b + " [Object]: NULL";
        } 
      } 
    } 
    return str;
  }
}


/* Location:              C:\Users\bot-nosense\Downloads\Loan-12-Su-Quan.jar!\di.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */