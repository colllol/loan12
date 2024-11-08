import javax.microedition.rms.RecordStore;

public class l {
  public static RecordStore a(String paramString, boolean paramBoolean) {
    try {
      return RecordStore.openRecordStore(paramString, paramBoolean);
    } catch (Throwable throwable) {
      throwable.printStackTrace();
      return null;
    } 
  }
  
  public static void a(RecordStore paramRecordStore) {
    try {
      paramRecordStore.closeRecordStore();
    } catch (Throwable throwable) {
      throwable.printStackTrace();
    } 
  }
  
  public static byte[] a(RecordStore paramRecordStore, int paramInt) {
    byte[] arrayOfByte = (byte[])null;
    try {
      arrayOfByte = paramRecordStore.getRecord(paramInt);
    } catch (Throwable throwable) {
      throwable.printStackTrace();
      arrayOfByte = (byte[])null;
    } 
    return arrayOfByte;
  }
  
  public static int a(RecordStore paramRecordStore, int paramInt1, byte[] paramArrayOfbyte, int paramInt2, int paramInt3) {
    try {
      paramRecordStore.setRecord(paramInt1, paramArrayOfbyte, paramInt2, paramInt3);
      return paramInt1;
    } catch (Throwable throwable) {
      throwable.printStackTrace();
      return -1;
    } 
  }
  
  public static int a(RecordStore paramRecordStore, int paramInt, byte[] paramArrayOfbyte) {
    return a(paramRecordStore, paramInt, paramArrayOfbyte, 0, paramArrayOfbyte.length);
  }
  
  public static int a(RecordStore paramRecordStore, byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    try {
      return paramRecordStore.addRecord(paramArrayOfbyte, paramInt1, paramInt2);
    } catch (Throwable throwable) {
      throwable.printStackTrace();
      return -1;
    } 
  }
  
  public static int a(RecordStore paramRecordStore, byte[] paramArrayOfbyte) {
    return a(paramRecordStore, paramArrayOfbyte, 0, paramArrayOfbyte.length);
  }
  
  public static int b(RecordStore paramRecordStore) {
    try {
      return paramRecordStore.getNumRecords();
    } catch (Throwable throwable) {
      throwable.printStackTrace();
      return 0;
    } 
  }
  
  public static void a(String paramString) {
    try {
      RecordStore.deleteRecordStore(paramString);
    } catch (Throwable throwable) {
      throwable.printStackTrace();
    } 
  }
}


/* Location:              C:\Users\bot-nosense\Downloads\Loan-12-Su-Quan.jar!\l.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */