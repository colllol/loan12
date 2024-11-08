package com.mic.smsgame.suquan;

import ai;
import bg;
import dr;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class f extends bg {
  public static final void a() {
    dr.c(bg.b, 100);
    dr.c(bg.b, 101);
    dr.c(bg.b, 103);
    dr.c(bg.b, 104);
    dr.c(bg.b, 105);
    f();
    bg.a();
  }
  
  public static final void a(int paramInt, String paramString) {
    try {
      ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
      DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
      dataOutputStream.writeUTF(paramString);
      dataOutputStream.flush();
      dr.a(bg.b, paramInt, byteArrayOutputStream.toByteArray());
      dr.a(bg.b);
      dataOutputStream.close();
    } catch (IOException iOException) {
      iOException.printStackTrace();
    } 
  }
  
  public static final String a(int paramInt) {
    try {
      byte[] arrayOfByte = dr.b(bg.b, 104);
      if (arrayOfByte == null)
        return ""; 
      DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(arrayOfByte));
      String str = dataInputStream.readUTF();
      dataInputStream.close();
      return str;
    } catch (IOException iOException) {
      iOException.printStackTrace();
      return null;
    } 
  }
  
  public static final void a(int paramInt1, int paramInt2) {
    dr.a(bg.b, paramInt1, ai.a(paramInt2));
    dr.a(bg.b);
  }
  
  public static final int b(int paramInt) {
    byte[] arrayOfByte = dr.b(bg.b, paramInt);
    return (arrayOfByte == null) ? 0 : ai.a(arrayOfByte);
  }
  
  public static final void a(byte[] paramArrayOfbyte) {
    dr.a(bg.b, 100, paramArrayOfbyte);
    dr.a(bg.b);
  }
  
  public static final byte[] b() {
    byte[] arrayOfByte = dr.b(bg.b, 100);
    return (arrayOfByte == null) ? null : arrayOfByte;
  }
  
  public static final boolean c() {
    return dr.a(bg.b, 100);
  }
  
  public static final void a(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    dr.a(bg.c, 106, paramArrayOfbyte, paramInt1, paramInt2);
    dr.a(bg.c);
  }
  
  public static final byte[] d() {
    return dr.b(bg.c, 106);
  }
  
  public static final boolean e() {
    return dr.a(bg.c, 106);
  }
  
  public static final void f() {
    dr.c(bg.c, 106);
    dr.a(bg.c);
  }
}


/* Location:              C:\Users\bot-nosense\Downloads\Loan-12-Su-Quan.jar!\com\mic\smsgame\suquan\f.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */