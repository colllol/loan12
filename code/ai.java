public class ai {
  public static int a(byte[] paramArrayOfbyte) {
    return a(paramArrayOfbyte[0], paramArrayOfbyte[1], paramArrayOfbyte[2], paramArrayOfbyte[3]);
  }
  
  public static int a(byte paramByte1, byte paramByte2, byte paramByte3, byte paramByte4) {
    return ((paramByte1 & 0xFF) << 24) + ((paramByte2 & 0xFF) << 16) + ((paramByte3 & 0xFF) << 8) + (paramByte4 & 0xFF);
  }
  
  public static int a(byte[] paramArrayOfbyte, int paramInt) {
    return a(paramArrayOfbyte[paramInt], paramArrayOfbyte[paramInt + 1], paramArrayOfbyte[paramInt + 2], paramArrayOfbyte[paramInt + 3]);
  }
  
  public static byte[] a(int paramInt) {
    return new byte[] { (byte)(paramInt >>> 24 & 0xFF), (byte)(paramInt >>> 16 & 0xFF), (byte)(paramInt >>> 8 & 0xFF), (byte)(paramInt >>> 0 & 0xFF) };
  }
}


/* Location:              C:\Users\bot-nosense\Downloads\Loan-12-Su-Quan.jar!\ai.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */