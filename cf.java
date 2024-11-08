import javax.microedition.media.Manager;
import javax.microedition.media.MediaException;
import javax.microedition.media.Player;
import javax.microedition.media.PlayerListener;
import javax.microedition.media.control.TempoControl;
import javax.microedition.media.control.VolumeControl;

public class cf implements at, PlayerListener {
  public static cf a;
  
  private Player b;
  
  private String c = "";
  
  private VolumeControl d;
  
  private TempoControl e;
  
  private int f = 100;
  
  private boolean g;
  
  public static cf a() {
    if (a == null)
      a = new cf(); 
    return a;
  }
  
  public void a(String paramString, int paramInt) {
    try {
      b(paramString, paramInt);
    } catch (Throwable throwable) {
      throwable.printStackTrace();
      d.a("Create Back ground 1: " + throwable);
      d();
      try {
        b(paramString, paramInt);
      } catch (Exception exception) {
        d.a("Back ground 2: " + exception);
      } 
    } 
  }
  
  private void b(String paramString, int paramInt) {
    if (!cx.x)
      return; 
    if (this.b != null && this.c.equals(paramString)) {
      this.f = cx.z;
      return;
    } 
    d();
    this.b = Manager.createPlayer("".getClass().getResourceAsStream("/audio/" + paramString + ".mid"), "audio/midi");
    this.b.setLoopCount(paramInt);
    this.b.realize();
    this.b.prefetch();
    this.b.addPlayerListener(this);
    this.d = (VolumeControl)this.b.getControl("VolumeControl");
    if (this.d != null) {
      this.d.setLevel(cx.y);
      this.d.setMute(!cx.x);
    } 
    this.e = (TempoControl)this.b.getControl("TempoControl");
    if (this.e != null) {
      cx.z = this.e.getTempo();
      this.f = cx.z;
      this.e.setTempo(this.f);
      d.a("Temp when create=" + this.f);
    } 
    this.c = paramString;
    this.g = false;
  }
  
  public void b() {
    if (this.b != null)
      try {
        this.b.stop();
      } catch (Throwable throwable) {
        d();
        d.a("[STOP BK Music]: " + throwable);
      }  
  }
  
  public void d() {
    if (this.b != null)
      try {
        this.b.stop();
        this.b.deallocate();
        this.b.close();
        this.b = null;
        this.d = null;
      } catch (MediaException mediaException) {
        this.b = null;
        this.d = null;
        d.a("[Destroy BK Music]: " + mediaException);
      }  
    this.c = "";
  }
  
  public void e() {
    if (!cx.x)
      return; 
    ci.a().a(this);
  }
  
  public void c() {
    try {
      if (this.b != null) {
        if (this.d != null) {
          this.d.setMute(!cx.x);
          this.d.setLevel(cx.y);
        } 
        this.b.start();
      } 
    } catch (Exception exception) {
      d.a("[Player BK Music]: " + exception);
      d();
    } 
  }
  
  public int f() {
    return this.f;
  }
  
  public void a(int paramInt) {
    this.f = paramInt;
    if (this.e != null)
      try {
        this.e.setTempo(paramInt);
      } catch (Exception exception) {
        this.f = cx.z;
        this.e.setTempo(this.f);
      }  
  }
  
  public void playerUpdate(Player paramPlayer, String paramString, Object paramObject) {
    if (paramString.equals("endOfMedia")) {
      if (this.d != null) {
        this.d.setLevel(cx.y);
        this.d.setMute(!cx.x);
      } 
      if (this.e != null)
        try {
          this.e.setTempo(this.f);
        } catch (Exception exception) {
          this.f = cx.z;
          this.e.setTempo(this.f);
        }  
    } else if (paramString.equals("started")) {
      this.g = true;
    } else if (paramString.equals("stopped")) {
      this.g = false;
    } 
  }
}


/* Location:              C:\Users\bot-nosense\Downloads\Loan-12-Su-Quan.jar!\cf.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */