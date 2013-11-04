package com.flipdog.spellchecker.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.flipdog.commons.diagnostic.Track;

public class StartByUserActivity extends Activity
{
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    Track.it("StartByUserActivity.onCreate()", new String[] { "Eula" });
    startActivity(new Intent(this, SpellCheckerActivity.class));
    Track.it("StartByUserActivity.onCreate().finish()", new String[] { "Eula" });
    finish();
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.spellchecker.activity.StartByUserActivity
 * JD-Core Version:    0.6.2
 */