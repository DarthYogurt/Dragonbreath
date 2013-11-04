package com.flipdog.filebrowser.preference;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import com.flipdog.h;

public class FileBrowserPreferenceActivity extends PreferenceActivity
{
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    addPreferencesFromResource(h.fbrowse_preferences);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.filebrowser.preference.FileBrowserPreferenceActivity
 * JD-Core Version:    0.6.2
 */