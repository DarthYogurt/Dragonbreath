package com.flipdog.spellchecker.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class StartByIntentActivity extends Activity
{
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    setResult(paramInt2, paramIntent);
    finish();
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    Intent localIntent1 = getIntent();
    Intent localIntent2 = new Intent(this, SpellCheckerActivity.class);
    localIntent2.setAction(localIntent1.getAction());
    localIntent2.setType(localIntent1.getType());
    localIntent2.putExtra("android.intent.extra.TEXT", localIntent1.getStringExtra("android.intent.extra.TEXT"));
    localIntent2.putExtra("com.flipdog.extra.TEXT_HTML", localIntent1.getStringExtra("com.flipdog.extra.TEXT_HTML"));
    startActivityForResult(localIntent2, 0);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.spellchecker.activity.StartByIntentActivity
 * JD-Core Version:    0.6.2
 */