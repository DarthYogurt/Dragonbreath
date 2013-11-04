package com.b;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import com.flipdog.commons.diagnostic.Track;

public class g extends ListFragment
{
  public g()
  {
    a("ctor");
  }

  protected void a(String paramString)
  {
    Object[] arrayOfObject = new Object[3];
    arrayOfObject[0] = getClass().getName();
    arrayOfObject[1] = Integer.toHexString(hashCode());
    arrayOfObject[2] = paramString;
    Track.me("Fragment", "[%s (%s)] %s", arrayOfObject);
  }

  public void onActivityCreated(Bundle paramBundle)
  {
    a("onActivityCreated");
    super.onActivityCreated(paramBundle);
  }

  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    a("onActivityResult");
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
  }

  public void onAttach(Activity paramActivity)
  {
    a("onAttach");
    super.onAttach(paramActivity);
  }

  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    a("onConfigurationChanged");
    super.onConfigurationChanged(paramConfiguration);
  }

  public void onCreate(Bundle paramBundle)
  {
    a("onCreate");
    super.onCreate(paramBundle);
  }

  public void onDestroy()
  {
    a("onDestroy");
    super.onDestroy();
  }

  public void onDestroyView()
  {
    a("onDestroyView");
    super.onDestroyView();
  }

  public void onDetach()
  {
    a("onDetach");
    super.onDetach();
  }

  public void onHiddenChanged(boolean paramBoolean)
  {
    a("onHiddenChanged");
    super.onHiddenChanged(paramBoolean);
  }

  public void onPause()
  {
    a("onPause");
    super.onPause();
  }

  public void onResume()
  {
    a("onResume");
    super.onResume();
  }

  public void onSaveInstanceState(Bundle paramBundle)
  {
    a("onSaveInstanceState");
    super.onSaveInstanceState(paramBundle);
  }

  public void onStart()
  {
    a("onStart");
    super.onStart();
  }

  public void onStop()
  {
    a("onStop");
    super.onStop();
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.b.g
 * JD-Core Version:    0.6.2
 */