package com.flipdog.internal.app;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;

public class ChooserActivity extends ResolverActivity
{
  private static final String b = "android.intent.extra.INITIAL_INTENTS";

  protected void onCreate(Bundle paramBundle)
  {
    Intent localIntent = getIntent();
    CharSequence localCharSequence1 = localIntent.getCharSequenceExtra("android.intent.extra.TITLE");
    if (localCharSequence1 == null);
    for (CharSequence localCharSequence2 = getResources().getText(c.a.b); ; localCharSequence2 = localCharSequence1)
    {
      Parcelable[] arrayOfParcelable = localIntent.getParcelableArrayExtra("android.intent.extra.INITIAL_INTENTS");
      Object localObject = (Intent[])null;
      Intent[] arrayOfIntent;
      if (arrayOfParcelable != null)
        arrayOfIntent = new Intent[arrayOfParcelable.length];
      for (int i = 0; ; i++)
      {
        if (i >= arrayOfParcelable.length)
        {
          localObject = arrayOfIntent;
          super.a(paramBundle, localCharSequence2, (Intent[])localObject, false);
          return;
        }
        if (!(arrayOfParcelable[i] instanceof Intent))
        {
          Log.w("ChooseActivity", "Initial intent #" + i + " not an Intent: " + arrayOfParcelable[i]);
          finish();
          return;
        }
        arrayOfIntent[i] = ((Intent)arrayOfParcelable[i]);
      }
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.internal.app.ChooserActivity
 * JD-Core Version:    0.6.2
 */