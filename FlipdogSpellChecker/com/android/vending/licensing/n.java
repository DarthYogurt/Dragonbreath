package com.android.vending.licensing;

import android.text.TextUtils;
import android.util.Log;
import com.android.vending.licensing.a.b;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;

class n
{
  private static final String a = "LicenseValidator";
  private static final int b = 0;
  private static final int c = 1;
  private static final int d = 2;
  private static final int e = 3;
  private static final int f = 4;
  private static final int g = 5;
  private static final int h = 257;
  private static final int i = 258;
  private static final int j = 259;
  private static final String q = "SHA1withRSA";
  private final k k;
  private final e l;
  private final int m;
  private final String n;
  private final String o;
  private final a p;

  n(k paramk, a parama, e parame, int paramInt, String paramString1, String paramString2)
  {
    this.k = paramk;
    this.p = parama;
    this.l = parame;
    this.m = paramInt;
    this.n = paramString1;
    this.o = paramString2;
  }

  private void a(f paramf, u paramu, String paramString1, String paramString2)
  {
    this.k.a(paramf, paramu);
    this.l.a(paramString1, paramString2);
    if (this.k.e())
    {
      this.l.a();
      return;
    }
    this.l.b();
  }

  private void a(w paramw)
  {
    this.l.a(paramw);
  }

  private void d()
  {
    this.l.b();
  }

  public e a()
  {
    return this.l;
  }

  public void a(PublicKey paramPublicKey, int paramInt, String paramString1, String paramString2)
  {
    Object localObject = null;
    String str;
    if ((paramInt == 0) || (paramInt == 1) || (paramInt == 2))
    {
      try
      {
        Signature localSignature = Signature.getInstance("SHA1withRSA");
        localSignature.initVerify(paramPublicKey);
        localSignature.update(paramString1.getBytes());
        if (!localSignature.verify(com.android.vending.licensing.a.a.a(paramString2)))
        {
          Log.e("LicenseValidator", "Signature verification failed.");
          d();
          return;
        }
      }
      catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
      {
        throw new RuntimeException(localNoSuchAlgorithmException);
      }
      catch (InvalidKeyException localInvalidKeyException)
      {
        a(w.e);
        return;
      }
      catch (SignatureException localSignatureException)
      {
        throw new RuntimeException(localSignatureException);
      }
      catch (b localb)
      {
        Log.e("LicenseValidator", "Could not Base64-decode signature.");
        d();
        return;
      }
      try
      {
        u localu = u.a(paramString1);
        localObject = localu;
        if (localObject.a != paramInt)
        {
          Log.e("LicenseValidator", "Response codes don't match.");
          d();
          return;
        }
      }
      catch (IllegalArgumentException localIllegalArgumentException)
      {
        Log.e("LicenseValidator", "Could not parse response.");
        d();
        return;
      }
      if (localObject.b != this.m)
      {
        Log.e("LicenseValidator", "Nonce doesn't match.");
        d();
        return;
      }
      if (!localObject.c.equals(this.n))
      {
        Log.e("LicenseValidator", "Package name doesn't match.");
        d();
        return;
      }
      if (!localObject.d.equals(this.o))
      {
        Log.e("LicenseValidator", "Version codes don't match.");
        d();
        return;
      }
      str = localObject.e;
      if (TextUtils.isEmpty(str))
      {
        Log.e("LicenseValidator", "User identifier is empty.");
        d();
      }
    }
    else
    {
      str = null;
    }
    switch (paramInt)
    {
    default:
      Log.e("LicenseValidator", "Unknown response code for license check.");
      d();
      return;
    case 0:
    case 2:
      a(this.p.a(str), localObject, paramString1, paramString2);
      return;
    case 1:
      a(f.b, localObject, paramString1, paramString2);
      return;
    case 257:
      Log.w("LicenseValidator", "Error contacting licensing server.");
      a(f.c, localObject, paramString1, paramString2);
      return;
    case 4:
      Log.w("LicenseValidator", "An error has occurred on the licensing server.");
      a(f.c, localObject, paramString1, paramString2);
      return;
    case 5:
      Log.w("LicenseValidator", "Licensing server is refusing to talk to this device, over quota.");
      a(f.c, localObject, paramString1, paramString2);
      return;
    case 258:
      a(w.a);
      return;
    case 259:
      a(w.b);
      return;
    case 3:
    }
    a(w.c);
  }

  public int b()
  {
    return this.m;
  }

  public String c()
  {
    return this.n;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.android.vending.licensing.n
 * JD-Core Version:    0.6.2
 */