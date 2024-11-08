import javax.microedition.rms.RecordStore;

public class dr {
  private String a;
  
  private int b;
  
  private int c;
  
  private di d;
  
  private di e;
  
  private di f;
  
  private int g;
  
  private dr(String paramString, int paramInt) {
    this(paramString, paramInt, 2048);
  }
  
  private dr(String paramString, int paramInt1, int paramInt2) {
    this.c = paramInt2;
    this.a = paramString;
    this.b = paramInt1;
    this.d = new di();
    this.e = new di();
    this.f = new di();
    a();
  }
  
  private byte[] a(RecordStore paramRecordStore) {
    byte[] arrayOfByte = new byte[this.c];
    l.a(paramRecordStore, arrayOfByte);
    l.a(paramRecordStore, arrayOfByte);
    l.a(paramRecordStore, new byte[] { 1 });
    return arrayOfByte;
  }
  
  private void a() {
    RecordStore recordStore = l.a(this.a, false);
    try {
      byte[] arrayOfByte = (byte[])null;
      if (recordStore == null || l.b(recordStore) < 3) {
        l.a(this.a);
        recordStore = l.a(this.a, true);
        arrayOfByte = a(recordStore);
        this.g = 1;
      } else {
        this.g = 0;
        byte[] arrayOfByte1 = l.a(recordStore, 3);
        if (arrayOfByte1 != null)
          this.g = arrayOfByte1[0]; 
        if (this.g < 1 || this.g > 2)
          this.g = 1; 
        arrayOfByte = l.a(recordStore, this.g);
      } 
      int i = l.b(recordStore);
      int j = 3;
      l.a(recordStore);
      int k = 0;
      int m = ai.a(arrayOfByte, k);
      k += true;
      int n;
      for (n = 0; n < m; n++) {
        bd bd = new bd();
        k += bd.a(arrayOfByte, k);
        this.d.a(bd);
        j += bd.c.length;
      } 
      n = ai.a(arrayOfByte, k);
      k += 4;
      for (byte b = 0; b < n; b++) {
        this.e.a(new Integer(ai.a(arrayOfByte, k)));
        k += 4;
      } 
      j += this.e.b();
      if (i - 3 > j)
        a(i); 
    } catch (Throwable throwable) {
      l.a(recordStore);
      throwable.printStackTrace();
    } 
  }
  
  private void a(int paramInt) {
    boolean[] arrayOfBoolean = new boolean[paramInt];
    int i;
    for (i = this.d.b() - 1; i > -1; i--) {
      bd bd = (bd)this.d.b(i);
      for (int j = bd.c.length - 1; j > -1; j--)
        arrayOfBoolean[bd.c[j]] = true; 
    } 
    for (i = this.e.b() - 1; i > -1; i--) {
      int j = ((Integer)this.e.b(i)).intValue();
      arrayOfBoolean[j] = true;
    } 
    for (i = arrayOfBoolean.length - 1; i > 2; i--) {
      if (!arrayOfBoolean[i])
        this.e.a(new Integer(i)); 
    } 
  }
  
  private synchronized byte[] b(int paramInt) {
    RecordStore recordStore = l.a(this.a, false);
    try {
      bd bd = d(paramInt);
      if (bd == null || bd.c == null || recordStore == null)
        return null; 
      if (bd.d != null)
        return bd.d; 
      byte[] arrayOfByte = new byte[bd.b];
      int i = 0;
      int[] arrayOfInt = bd.c;
      for (byte b = 0; b < arrayOfInt.length; b++) {
        byte[] arrayOfByte1 = l.a(recordStore, arrayOfInt[b]);
        if (arrayOfByte1 == null) {
          this.d.b(bd);
          b(bd.c, 0, bd.c.length);
          return null;
        } 
        int j = arrayOfByte1.length;
        if (j > arrayOfByte.length - i)
          j = arrayOfByte.length - i; 
        System.arraycopy(arrayOfByte1, 0, arrayOfByte, i, j);
        i += j;
      } 
      l.a(recordStore);
      return arrayOfByte;
    } catch (Throwable throwable) {
      l.a(recordStore);
      throwable.printStackTrace();
      return null;
    } 
  }
  
  private synchronized void a(int paramInt1, byte[] paramArrayOfbyte, int paramInt2, int paramInt3) {
    if (paramArrayOfbyte == null)
      return; 
    try {
      bd bd = d(paramInt1);
      if (bd != null) {
        if (bd.c != null)
          b(bd.c, 0, bd.c.length); 
      } else {
        bd = new bd();
        this.d.a(bd);
      } 
      bd.a = paramInt1;
      bd.b = paramInt3;
      int i = paramInt3 / this.b;
      if (paramInt3 % this.b != 0)
        i++; 
      int[] arrayOfInt = new int[i];
      for (byte b = 0; b < arrayOfInt.length; b++) {
        int j = this.e.b() - 1;
        if (j > -1) {
          arrayOfInt[b] = ((Integer)this.e.b(j)).intValue();
          this.e.a(j);
        } else {
          arrayOfInt[b] = -1;
        } 
      } 
      bd.c = arrayOfInt;
      bd.d = new byte[paramInt3];
      System.arraycopy(paramArrayOfbyte, paramInt2, bd.d, 0, paramInt3);
    } catch (Throwable throwable) {
      c(paramInt1);
      throwable.printStackTrace();
    } 
  }
  
  private synchronized void b() {
    RecordStore recordStore = l.a(this.a, false);
    try {
      if (recordStore == null) {
        recordStore = l.a(this.a, true);
        a(recordStore);
      } 
      for (int i = this.d.b() - 1; i > -1; i--) {
        bd bd = (bd)this.d.b(i);
        if (bd.d != null) {
          a(recordStore, bd.d, bd.c);
          bd.d = null;
        } 
      } 
      byte[] arrayOfByte = new byte[this.c];
      int j = 0;
      System.arraycopy(ai.a(this.d.b()), 0, arrayOfByte, j, 4);
      j += true;
      int k;
      for (k = this.d.b() - 1; k > -1; k--) {
        bd bd = (bd)this.d.b(k);
        byte[] arrayOfByte1 = bd.a();
        System.arraycopy(arrayOfByte1, 0, arrayOfByte, j, arrayOfByte1.length);
        j += arrayOfByte1.length;
      } 
      for (k = this.f.b() - 1; k > -1; k--)
        this.e.a(this.f.b(k)); 
      this.f.a();
      System.arraycopy(ai.a(this.e.b()), 0, arrayOfByte, j, 4);
      j += 4;
      for (k = this.e.b() - 1; k > -1; k--) {
        int m = ((Integer)this.e.b(k)).intValue();
        System.arraycopy(ai.a(m), 0, arrayOfByte, j, 4);
        j += 4;
      } 
      k = (this.g == 1) ? 2 : 1;
      l.a(recordStore, k, arrayOfByte);
      l.a(recordStore, 3, new byte[] { (byte)k });
      l.a(recordStore);
      this.g = k;
    } catch (Throwable throwable) {
      l.a(recordStore);
      throwable.printStackTrace();
    } 
  }
  
  private void c(int paramInt) {
    try {
      for (int i = this.d.b() - 1; i > -1; i--) {
        bd bd = (bd)this.d.b(i);
        if (bd.a == paramInt) {
          this.d.a(i);
          if (bd.c != null)
            a(bd.c, 0, bd.c.length); 
        } 
      } 
    } catch (Throwable throwable) {
      throwable.printStackTrace();
    } 
  }
  
  private bd d(int paramInt) {
    try {
      for (int i = this.d.b() - 1; i > -1; i--) {
        bd bd = (bd)this.d.b(i);
        if (bd.a == paramInt)
          return bd; 
      } 
    } catch (Throwable throwable) {
      throwable.printStackTrace();
    } 
    return null;
  }
  
  private void a(int[] paramArrayOfint, int paramInt1, int paramInt2) {
    try {
      if (paramArrayOfint == null)
        return; 
      paramInt2 = paramInt1 + paramInt2;
      for (int i = paramInt1; i < paramInt2; i++)
        this.e.a(new Integer(paramArrayOfint[i])); 
    } catch (Throwable throwable) {
      throwable.printStackTrace();
    } 
  }
  
  private void b(int[] paramArrayOfint, int paramInt1, int paramInt2) {
    try {
      if (paramArrayOfint == null)
        return; 
      paramInt2 = paramInt1 + paramInt2;
      for (int i = paramInt1; i < paramInt2; i++)
        this.f.a(new Integer(paramArrayOfint[i])); 
    } catch (Throwable throwable) {
      throwable.printStackTrace();
    } 
  }
  
  private void a(RecordStore paramRecordStore, byte[] paramArrayOfbyte, int[] paramArrayOfint) {
    try {
      byte[] arrayOfByte = new byte[this.b];
      int i = 0;
      for (byte b = 0; b < paramArrayOfint.length; b++) {
        int j = paramArrayOfbyte.length - i;
        if (j > arrayOfByte.length)
          j = arrayOfByte.length; 
        System.arraycopy(paramArrayOfbyte, i, arrayOfByte, 0, j);
        i += j;
        if (paramArrayOfint[b] > 3) {
          l.a(paramRecordStore, paramArrayOfint[b], arrayOfByte);
        } else {
          paramArrayOfint[b] = l.a(paramRecordStore, arrayOfByte);
        } 
      } 
    } catch (Throwable throwable) {
      throwable.printStackTrace();
    } 
  }
  
  private boolean e(int paramInt) {
    try {
      for (int i = this.d.b() - 1; i > -1; i--) {
        bd bd = (bd)this.d.b(i);
        if (bd.a == paramInt)
          return true; 
      } 
    } catch (Throwable throwable) {
      throwable.printStackTrace();
    } 
    return false;
  }
  
  public static dr a(String paramString, int paramInt) {
    return new dr(paramString, paramInt);
  }
  
  public static boolean a(dr paramdr, int paramInt) {
    return paramdr.e(paramInt);
  }
  
  public static void a(dr paramdr, int paramInt, byte[] paramArrayOfbyte) {
    paramdr.a(paramInt, paramArrayOfbyte, 0, paramArrayOfbyte.length);
  }
  
  public static void a(dr paramdr, int paramInt1, byte[] paramArrayOfbyte, int paramInt2, int paramInt3) {
    paramdr.a(paramInt1, paramArrayOfbyte, paramInt2, paramInt3);
  }
  
  public static byte[] b(dr paramdr, int paramInt) {
    return paramdr.b(paramInt);
  }
  
  public static void c(dr paramdr, int paramInt) {
    paramdr.c(paramInt);
  }
  
  public static void a(dr paramdr) {
    paramdr.b();
  }
}


/* Location:              C:\Users\bot-nosense\Downloads\Loan-12-Su-Quan.jar!\dr.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */