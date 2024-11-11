public class b {
  dw a = new dw();
  
  dw b = null;
  
  static byte[] c = new byte[] { Byte.MIN_VALUE };
  
  private static final char[] d = new char[] { 
      '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 
      'a', 'b', 'c', 'd', 'e', 'f' };
  
  private void a(byte[] paramArrayOfbyte, int paramInt, int[] paramArrayOfint) {
    paramArrayOfint[0] = paramArrayOfbyte[paramInt] & 0xFF | (paramArrayOfbyte[paramInt + 1] & 0xFF) << 8 | (paramArrayOfbyte[paramInt + 2] & 0xFF) << 16 | paramArrayOfbyte[paramInt + 3] << 24;
    paramArrayOfint[1] = paramArrayOfbyte[paramInt + 4] & 0xFF | (paramArrayOfbyte[paramInt + 5] & 0xFF) << 8 | (paramArrayOfbyte[paramInt + 6] & 0xFF) << 16 | paramArrayOfbyte[paramInt + 7] << 24;
    paramArrayOfint[2] = paramArrayOfbyte[paramInt + 8] & 0xFF | (paramArrayOfbyte[paramInt + 9] & 0xFF) << 8 | (paramArrayOfbyte[paramInt + 10] & 0xFF) << 16 | paramArrayOfbyte[paramInt + 11] << 24;
    paramArrayOfint[3] = paramArrayOfbyte[paramInt + 12] & 0xFF | (paramArrayOfbyte[paramInt + 13] & 0xFF) << 8 | (paramArrayOfbyte[paramInt + 14] & 0xFF) << 16 | paramArrayOfbyte[paramInt + 15] << 24;
    paramArrayOfint[4] = paramArrayOfbyte[paramInt + 16] & 0xFF | (paramArrayOfbyte[paramInt + 17] & 0xFF) << 8 | (paramArrayOfbyte[paramInt + 18] & 0xFF) << 16 | paramArrayOfbyte[paramInt + 19] << 24;
    paramArrayOfint[5] = paramArrayOfbyte[paramInt + 20] & 0xFF | (paramArrayOfbyte[paramInt + 21] & 0xFF) << 8 | (paramArrayOfbyte[paramInt + 22] & 0xFF) << 16 | paramArrayOfbyte[paramInt + 23] << 24;
    paramArrayOfint[6] = paramArrayOfbyte[paramInt + 24] & 0xFF | (paramArrayOfbyte[paramInt + 25] & 0xFF) << 8 | (paramArrayOfbyte[paramInt + 26] & 0xFF) << 16 | paramArrayOfbyte[paramInt + 27] << 24;
    paramArrayOfint[7] = paramArrayOfbyte[paramInt + 28] & 0xFF | (paramArrayOfbyte[paramInt + 29] & 0xFF) << 8 | (paramArrayOfbyte[paramInt + 30] & 0xFF) << 16 | paramArrayOfbyte[paramInt + 31] << 24;
    paramArrayOfint[8] = paramArrayOfbyte[paramInt + 32] & 0xFF | (paramArrayOfbyte[paramInt + 33] & 0xFF) << 8 | (paramArrayOfbyte[paramInt + 34] & 0xFF) << 16 | paramArrayOfbyte[paramInt + 35] << 24;
    paramArrayOfint[9] = paramArrayOfbyte[paramInt + 36] & 0xFF | (paramArrayOfbyte[paramInt + 37] & 0xFF) << 8 | (paramArrayOfbyte[paramInt + 38] & 0xFF) << 16 | paramArrayOfbyte[paramInt + 39] << 24;
    paramArrayOfint[10] = paramArrayOfbyte[paramInt + 40] & 0xFF | (paramArrayOfbyte[paramInt + 41] & 0xFF) << 8 | (paramArrayOfbyte[paramInt + 42] & 0xFF) << 16 | paramArrayOfbyte[paramInt + 43] << 24;
    paramArrayOfint[11] = paramArrayOfbyte[paramInt + 44] & 0xFF | (paramArrayOfbyte[paramInt + 45] & 0xFF) << 8 | (paramArrayOfbyte[paramInt + 46] & 0xFF) << 16 | paramArrayOfbyte[paramInt + 47] << 24;
    paramArrayOfint[12] = paramArrayOfbyte[paramInt + 48] & 0xFF | (paramArrayOfbyte[paramInt + 49] & 0xFF) << 8 | (paramArrayOfbyte[paramInt + 50] & 0xFF) << 16 | paramArrayOfbyte[paramInt + 51] << 24;
    paramArrayOfint[13] = paramArrayOfbyte[paramInt + 52] & 0xFF | (paramArrayOfbyte[paramInt + 53] & 0xFF) << 8 | (paramArrayOfbyte[paramInt + 54] & 0xFF) << 16 | paramArrayOfbyte[paramInt + 55] << 24;
    paramArrayOfint[14] = paramArrayOfbyte[paramInt + 56] & 0xFF | (paramArrayOfbyte[paramInt + 57] & 0xFF) << 8 | (paramArrayOfbyte[paramInt + 58] & 0xFF) << 16 | paramArrayOfbyte[paramInt + 59] << 24;
    paramArrayOfint[15] = paramArrayOfbyte[paramInt + 60] & 0xFF | (paramArrayOfbyte[paramInt + 61] & 0xFF) << 8 | (paramArrayOfbyte[paramInt + 62] & 0xFF) << 16 | paramArrayOfbyte[paramInt + 63] << 24;
  }
  
  private void a(dw paramdw, byte[] paramArrayOfbyte, int paramInt, int[] paramArrayOfint) {
    int i = paramdw.a[0];
    int j = paramdw.a[1];
    int k = paramdw.a[2];
    int m = paramdw.a[3];
    int[] arrayOfInt = paramArrayOfint;
    a(paramArrayOfbyte, paramInt, paramArrayOfint);
    i += (j & k | (j ^ 0xFFFFFFFF) & m) + arrayOfInt[0] + -680876936;
    i = (i << 7 | i >>> 25) + j;
    m += (i & j | (i ^ 0xFFFFFFFF) & k) + arrayOfInt[1] + -389564586;
    m = (m << 12 | m >>> 20) + i;
    k += (m & i | (m ^ 0xFFFFFFFF) & j) + arrayOfInt[2] + 606105819;
    k = (k << 17 | k >>> 15) + m;
    j += (k & m | (k ^ 0xFFFFFFFF) & i) + arrayOfInt[3] + -1044525330;
    j = (j << 22 | j >>> 10) + k;
    i += (j & k | (j ^ 0xFFFFFFFF) & m) + arrayOfInt[4] + -176418897;
    i = (i << 7 | i >>> 25) + j;
    m += (i & j | (i ^ 0xFFFFFFFF) & k) + arrayOfInt[5] + 1200080426;
    m = (m << 12 | m >>> 20) + i;
    k += (m & i | (m ^ 0xFFFFFFFF) & j) + arrayOfInt[6] + -1473231341;
    k = (k << 17 | k >>> 15) + m;
    j += (k & m | (k ^ 0xFFFFFFFF) & i) + arrayOfInt[7] + -45705983;
    j = (j << 22 | j >>> 10) + k;
    i += (j & k | (j ^ 0xFFFFFFFF) & m) + arrayOfInt[8] + 1770035416;
    i = (i << 7 | i >>> 25) + j;
    m += (i & j | (i ^ 0xFFFFFFFF) & k) + arrayOfInt[9] + -1958414417;
    m = (m << 12 | m >>> 20) + i;
    k += (m & i | (m ^ 0xFFFFFFFF) & j) + arrayOfInt[10] + -42063;
    k = (k << 17 | k >>> 15) + m;
    j += (k & m | (k ^ 0xFFFFFFFF) & i) + arrayOfInt[11] + -1990404162;
    j = (j << 22 | j >>> 10) + k;
    i += (j & k | (j ^ 0xFFFFFFFF) & m) + arrayOfInt[12] + 1804603682;
    i = (i << 7 | i >>> 25) + j;
    m += (i & j | (i ^ 0xFFFFFFFF) & k) + arrayOfInt[13] + -40341101;
    m = (m << 12 | m >>> 20) + i;
    k += (m & i | (m ^ 0xFFFFFFFF) & j) + arrayOfInt[14] + -1502002290;
    k = (k << 17 | k >>> 15) + m;
    j += (k & m | (k ^ 0xFFFFFFFF) & i) + arrayOfInt[15] + 1236535329;
    j = (j << 22 | j >>> 10) + k;
    i += (j & m | k & (m ^ 0xFFFFFFFF)) + arrayOfInt[1] + -165796510;
    i = (i << 5 | i >>> 27) + j;
    m += (i & k | j & (k ^ 0xFFFFFFFF)) + arrayOfInt[6] + -1069501632;
    m = (m << 9 | m >>> 23) + i;
    k += (m & j | i & (j ^ 0xFFFFFFFF)) + arrayOfInt[11] + 643717713;
    k = (k << 14 | k >>> 18) + m;
    j += (k & i | m & (i ^ 0xFFFFFFFF)) + arrayOfInt[0] + -373897302;
    j = (j << 20 | j >>> 12) + k;
    i += (j & m | k & (m ^ 0xFFFFFFFF)) + arrayOfInt[5] + -701558691;
    i = (i << 5 | i >>> 27) + j;
    m += (i & k | j & (k ^ 0xFFFFFFFF)) + arrayOfInt[10] + 38016083;
    m = (m << 9 | m >>> 23) + i;
    k += (m & j | i & (j ^ 0xFFFFFFFF)) + arrayOfInt[15] + -660478335;
    k = (k << 14 | k >>> 18) + m;
    j += (k & i | m & (i ^ 0xFFFFFFFF)) + arrayOfInt[4] + -405537848;
    j = (j << 20 | j >>> 12) + k;
    i += (j & m | k & (m ^ 0xFFFFFFFF)) + arrayOfInt[9] + 568446438;
    i = (i << 5 | i >>> 27) + j;
    m += (i & k | j & (k ^ 0xFFFFFFFF)) + arrayOfInt[14] + -1019803690;
    m = (m << 9 | m >>> 23) + i;
    k += (m & j | i & (j ^ 0xFFFFFFFF)) + arrayOfInt[3] + -187363961;
    k = (k << 14 | k >>> 18) + m;
    j += (k & i | m & (i ^ 0xFFFFFFFF)) + arrayOfInt[8] + 1163531501;
    j = (j << 20 | j >>> 12) + k;
    i += (j & m | k & (m ^ 0xFFFFFFFF)) + arrayOfInt[13] + -1444681467;
    i = (i << 5 | i >>> 27) + j;
    m += (i & k | j & (k ^ 0xFFFFFFFF)) + arrayOfInt[2] + -51403784;
    m = (m << 9 | m >>> 23) + i;
    k += (m & j | i & (j ^ 0xFFFFFFFF)) + arrayOfInt[7] + 1735328473;
    k = (k << 14 | k >>> 18) + m;
    j += (k & i | m & (i ^ 0xFFFFFFFF)) + arrayOfInt[12] + -1926607734;
    j = (j << 20 | j >>> 12) + k;
    i += (j ^ k ^ m) + arrayOfInt[5] + -378558;
    i = (i << 4 | i >>> 28) + j;
    m += (i ^ j ^ k) + arrayOfInt[8] + -2022574463;
    m = (m << 11 | m >>> 21) + i;
    k += (m ^ i ^ j) + arrayOfInt[11] + 1839030562;
    k = (k << 16 | k >>> 16) + m;
    j += (k ^ m ^ i) + arrayOfInt[14] + -35309556;
    j = (j << 23 | j >>> 9) + k;
    i += (j ^ k ^ m) + arrayOfInt[1] + -1530992060;
    i = (i << 4 | i >>> 28) + j;
    m += (i ^ j ^ k) + arrayOfInt[4] + 1272893353;
    m = (m << 11 | m >>> 21) + i;
    k += (m ^ i ^ j) + arrayOfInt[7] + -155497632;
    k = (k << 16 | k >>> 16) + m;
    j += (k ^ m ^ i) + arrayOfInt[10] + -1094730640;
    j = (j << 23 | j >>> 9) + k;
    i += (j ^ k ^ m) + arrayOfInt[13] + 681279174;
    i = (i << 4 | i >>> 28) + j;
    m += (i ^ j ^ k) + arrayOfInt[0] + -358537222;
    m = (m << 11 | m >>> 21) + i;
    k += (m ^ i ^ j) + arrayOfInt[3] + -722521979;
    k = (k << 16 | k >>> 16) + m;
    j += (k ^ m ^ i) + arrayOfInt[6] + 76029189;
    j = (j << 23 | j >>> 9) + k;
    i += (j ^ k ^ m) + arrayOfInt[9] + -640364487;
    i = (i << 4 | i >>> 28) + j;
    m += (i ^ j ^ k) + arrayOfInt[12] + -421815835;
    m = (m << 11 | m >>> 21) + i;
    k += (m ^ i ^ j) + arrayOfInt[15] + 530742520;
    k = (k << 16 | k >>> 16) + m;
    j += (k ^ m ^ i) + arrayOfInt[2] + -995338651;
    j = (j << 23 | j >>> 9) + k;
    i += (k ^ (j | m ^ 0xFFFFFFFF)) + arrayOfInt[0] + -198630844;
    i = (i << 6 | i >>> 26) + j;
    m += (j ^ (i | k ^ 0xFFFFFFFF)) + arrayOfInt[7] + 1126891415;
    m = (m << 10 | m >>> 22) + i;
    k += (i ^ (m | j ^ 0xFFFFFFFF)) + arrayOfInt[14] + -1416354905;
    k = (k << 15 | k >>> 17) + m;
    j += (m ^ (k | i ^ 0xFFFFFFFF)) + arrayOfInt[5] + -57434055;
    j = (j << 21 | j >>> 11) + k;
    i += (k ^ (j | m ^ 0xFFFFFFFF)) + arrayOfInt[12] + 1700485571;
    i = (i << 6 | i >>> 26) + j;
    m += (j ^ (i | k ^ 0xFFFFFFFF)) + arrayOfInt[3] + -1894986606;
    m = (m << 10 | m >>> 22) + i;
    k += (i ^ (m | j ^ 0xFFFFFFFF)) + arrayOfInt[10] + -1051523;
    k = (k << 15 | k >>> 17) + m;
    j += (m ^ (k | i ^ 0xFFFFFFFF)) + arrayOfInt[1] + -2054922799;
    j = (j << 21 | j >>> 11) + k;
    i += (k ^ (j | m ^ 0xFFFFFFFF)) + arrayOfInt[8] + 1873313359;
    i = (i << 6 | i >>> 26) + j;
    m += (j ^ (i | k ^ 0xFFFFFFFF)) + arrayOfInt[15] + -30611744;
    m = (m << 10 | m >>> 22) + i;
    k += (i ^ (m | j ^ 0xFFFFFFFF)) + arrayOfInt[6] + -1560198380;
    k = (k << 15 | k >>> 17) + m;
    j += (m ^ (k | i ^ 0xFFFFFFFF)) + arrayOfInt[13] + 1309151649;
    j = (j << 21 | j >>> 11) + k;
    i += (k ^ (j | m ^ 0xFFFFFFFF)) + arrayOfInt[4] + -145523070;
    i = (i << 6 | i >>> 26) + j;
    m += (j ^ (i | k ^ 0xFFFFFFFF)) + arrayOfInt[11] + -1120210379;
    m = (m << 10 | m >>> 22) + i;
    k += (i ^ (m | j ^ 0xFFFFFFFF)) + arrayOfInt[2] + 718787259;
    k = (k << 15 | k >>> 17) + m;
    j += (m ^ (k | i ^ 0xFFFFFFFF)) + arrayOfInt[9] + -343485551;
    j = (j << 21 | j >>> 11) + k;
    paramdw.a[0] = paramdw.a[0] + i;
    paramdw.a[1] = paramdw.a[1] + j;
    paramdw.a[2] = paramdw.a[2] + k;
    paramdw.a[3] = paramdw.a[3] + m;
  }
  
  private void a(dw paramdw, byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    byte b1;
    this.b = null;
    if (paramInt2 - paramInt1 > paramArrayOfbyte.length)
      paramInt2 = paramArrayOfbyte.length - paramInt1; 
    int i = (int)(paramdw.b & 0x3FL);
    paramdw.b += paramInt2;
    int j = 64 - i;
    if (paramInt2 >= j) {
      int[] arrayOfInt = new int[16];
      if (j == 64) {
        j = 0;
      } else {
        for (byte b2 = 0; b2 < j; b2++)
          paramdw.c[b2 + i] = paramArrayOfbyte[b2 + paramInt1]; 
        a(paramdw, paramdw.c, 0, arrayOfInt);
      } 
      for (b1 = j; b1 + 63 < paramInt2; b1 += 64)
        a(paramdw, paramArrayOfbyte, b1 + paramInt1, arrayOfInt); 
      i = 0;
    } else {
      b1 = 0;
    } 
    if (b1 < paramInt2) {
      byte b2 = b1;
      while (b1 < paramInt2) {
        paramdw.c[i + b1 - b2] = paramArrayOfbyte[b1 + paramInt1];
        b1++;
      } 
    } 
  }
  
  private byte[] a(int[] paramArrayOfint, int paramInt) {
    byte[] arrayOfByte = new byte[paramInt];
    byte b2 = 0;
    byte b1 = b2;
    while (b2 < paramInt) {
      arrayOfByte[b2] = (byte)(paramArrayOfint[b1] & 0xFF);
      arrayOfByte[b2 + 1] = (byte)(paramArrayOfint[b1] >>> 8 & 0xFF);
      arrayOfByte[b2 + 2] = (byte)(paramArrayOfint[b1] >>> 16 & 0xFF);
      arrayOfByte[b2 + 3] = (byte)(paramArrayOfint[b1] >>> 24 & 0xFF);
      b1++;
      b2 += 4;
    } 
    return arrayOfByte;
  }
  
  private byte[] a() {
    if (this.b == null) {
      dw dw1 = new dw(this.a);
      int[] arrayOfInt = { (int)(dw1.b << 3L), (int)(dw1.b >> 29L) };
      byte[] arrayOfByte = a(arrayOfInt, 8);
      int i = (int)(dw1.b & 0x3FL);
      int j = (i < 56) ? (56 - i) : (120 - i);
      a(dw1, c, 0, j);
      a(dw1, arrayOfByte, 0, 8);
      this.b = dw1;
    } 
    return a(this.b.a, 16);
  }
  
  public static byte[] a(String paramString) {
    return a(paramString, (String)null);
  }
  
  public static byte[] a(String paramString1, String paramString2) {
    return a(ao.c(paramString1, paramString2));
  }
  
  public static byte[] a(byte[] paramArrayOfbyte) {
    return a(paramArrayOfbyte, 0, paramArrayOfbyte.length);
  }
  
  public static byte[] a(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    b b1 = new b();
    b1.a(b1.a, paramArrayOfbyte, 0, paramArrayOfbyte.length);
    return b1.a();
  }
  
  public static String b(byte[] paramArrayOfbyte) {
    char[] arrayOfChar = new char[paramArrayOfbyte.length * 2];
    byte b1 = 0;
    byte b2 = 0;
    while (b1 < paramArrayOfbyte.length) {
      arrayOfChar[b2++] = d[paramArrayOfbyte[b1] >>> 4 & 0xF];
      arrayOfChar[b2++] = d[paramArrayOfbyte[b1] & 0xF];
      b1++;
    } 
    return new String(arrayOfChar);
  }
  
  public static boolean a(byte[] paramArrayOfbyte1, byte[] paramArrayOfbyte2) {
    if (paramArrayOfbyte1 == null)
      return (paramArrayOfbyte2 == null); 
    if (paramArrayOfbyte2 == null)
      return false; 
    int i = 16;
    if (paramArrayOfbyte1.length < 16) {
      if (paramArrayOfbyte2.length != paramArrayOfbyte1.length)
        return false; 
      i = paramArrayOfbyte1.length;
    } else if (paramArrayOfbyte2.length < 16) {
      return false;
    } 
    for (byte b1 = 0; b1 < i; b1++) {
      if (paramArrayOfbyte1[b1] != paramArrayOfbyte2[b1])
        return false; 
    } 
    return true;
  }
}


/* Location:              C:\Users\bot-nosense\Downloads\Loan-12-Su-Quan.jar!\b.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */