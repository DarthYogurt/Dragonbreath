package com.flipdog.spellchecker.activity;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.ActivityNotFoundException;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.text.ClipboardManager;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.BulletSpan;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LayoutAnimationController;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import com.flipdog.about.AboutActivity;
import com.flipdog.about.EulaActivity;
import com.flipdog.activity.MyActivity;
import com.flipdog.commons.a.ad;
import com.flipdog.commons.a.as;
import com.flipdog.commons.a.ax;
import com.flipdog.commons.diagnostic.Track;
import com.flipdog.commons.g.h;
import com.flipdog.editor.MyEditText;
import com.flipdog.editor.MyHorizontalScrollView;
import com.flipdog.editor.ak;
import com.flipdog.editor.an;
import com.flipdog.editor.aw;
import com.flipdog.errors.activity.ErrorActivity;
import com.flipdog.spellchecker.aa;
import com.flipdog.spellchecker.t;
import com.flipdog.spellchecker.v;
import com.flipdog.spellchecker.y;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SpellCheckerActivity extends MyActivity
  implements View.OnClickListener, an
{
  protected static final int c = 33;
  private static final String d = "com.flipdog.action.SPELL";
  private static final int e = 16908333;
  private i f = new i(null);
  private a g = new a();
  private ImageButton h;
  private MyEditText i;
  private ProgressBar j;
  private ClipboardManager k;
  private e l;
  private com.flipdog.speller.r m;
  private ak n = new ak();
  private g o;
  private boolean p = false;
  private String q;
  private h r;
  private com.flipdog.commons.s.b s;

  static
  {
    System.loadLibrary("ndkfoo");
  }

  private void A()
  {
    try
    {
      String str = I();
      Spanned localSpanned = null;
      if (str != null)
        localSpanned = a(str);
      a(localSpanned);
      return;
    }
    catch (Exception localException)
    {
      ErrorActivity.a(this, localException, "Can't load draft file.");
    }
  }

  private void B()
  {
    com.flipdog.speller.d locald = com.flipdog.speller.d.a();
    Intent localIntent = new Intent("android.speech.action.RECOGNIZE_SPEECH");
    localIntent.putExtra("android.speech.extra.LANGUAGE_MODEL", "free_form");
    localIntent.putExtra("android.speech.extra.PROMPT", com.flipdog.spellchecker.c.speech_recognition_prompt);
    localIntent.putExtra("android.speech.extra.LANGUAGE", locald.a);
    startActivityForResult(localIntent, 1);
  }

  private int C()
  {
    return Math.min(this.i.getSelectionStart(), this.i.getSelectionEnd());
  }

  private int D()
  {
    return Math.max(this.i.getSelectionStart(), this.i.getSelectionEnd());
  }

  private void E()
  {
    AboutActivity.a(this, getString(com.flipdog.spellchecker.c.about_action), t.about, t.release_notes, t.eula, 1);
  }

  private void F()
  {
    AboutActivity.a(this, getString(com.flipdog.spellchecker.c.help_action), t.help);
  }

  private void G()
  {
    if ("com.flipdog.action.SPELL".equals(this.g.b))
    {
      com.flipdog.commons.c.b localb = N();
      Intent localIntent = getIntent();
      localIntent.putExtra("android.intent.extra.TEXT", localb.b);
      setResult(-1, localIntent);
    }
  }

  private void H()
  {
    if (!this.p)
      return;
    com.flipdog.commons.c.b localb = N();
    try
    {
      com.flipdog.commons.a.n.a(localb.b, J());
      return;
    }
    catch (Exception localException)
    {
      ErrorActivity.a(this, localException, "Can't save draft file.");
    }
  }

  private String I()
    throws IOException
  {
    File localFile = J();
    if (!localFile.exists())
      return null;
    return com.flipdog.commons.a.n.a(localFile);
  }

  private File J()
  {
    return com.flipdog.spellchecker.w.b("draft.txt");
  }

  private void K()
  {
    this.o = new j(this);
    this.o.a();
  }

  private void L()
  {
    if (this.o != null)
      this.o.b();
  }

  private String M()
  {
    return k().toString();
  }

  private com.flipdog.commons.c.b N()
  {
    return com.flipdog.commons.c.c.a(k(), new Class[] { com.flipdog.speller.o.class });
  }

  private void O()
  {
    com.flipdog.commons.a.i.a(this.f.d, com.flipdog.spellchecker.c.clear_tooltip);
    com.flipdog.commons.a.i.a(this.f.g, com.flipdog.spellchecker.c.paste_tooltip);
    com.flipdog.commons.a.i.a(this.f.e, com.flipdog.spellchecker.c.select_all_tooltip);
    com.flipdog.commons.a.i.a(this.f.f, com.flipdog.spellchecker.c.copy_tooltip);
    com.flipdog.commons.a.i.a(this.f.h, com.flipdog.spellchecker.c.speak_tooltip);
    com.flipdog.commons.a.i.a(this.h, com.flipdog.spellchecker.c.spellcheck_tooltip);
    com.flipdog.commons.a.i.a(this.f.i, com.flipdog.spellchecker.c.save_as_tooltip);
    com.flipdog.commons.a.i.a(this.f.j, com.flipdog.spellchecker.c.open_tooltip);
  }

  private void P()
  {
    com.flipdog.ads.a.b localb = com.flipdog.ads.a.d.a(M(), 5, this.q);
    ((com.flipdog.ads.a.e)this.s.a(com.flipdog.ads.a.e.class)).a(localb);
  }

  private boolean Q()
  {
    return com.flipdog.about.i.a(1);
  }

  private void R()
  {
    new com.flipdog.f(this).a(3);
  }

  private void S()
  {
    com.flipdog.f localf = new com.flipdog.f(this);
    localf.a(as.e("Select file to open"));
    localf.a(4, false, "htm, html");
  }

  private void T()
  {
    aw.a(this.f.a, this.f.b, this.f.c);
  }

  private Spanned a(String paramString)
  {
    Editable localEditable = (Editable)com.flipdog.commons.a.o.a(paramString);
    Iterator localIterator;
    if (localEditable != null)
      localIterator = com.flipdog.editor.spans.j.a(localEditable, new Class[] { BulletSpan.class }).iterator();
    while (true)
    {
      if (!localIterator.hasNext())
      {
        com.flipdog.editor.b.d.a(new com.flipdog.editor.b.e(localEditable));
        return localEditable;
      }
      Object localObject = localIterator.next();
      localEditable.setSpan(localObject, localEditable.getSpanStart(localObject), localEditable.getSpanEnd(localObject), 18);
    }
  }

  private void a(Uri paramUri, String paramString)
  {
    com.flipdog.commons.c.b localb = N();
    if (!paramString.endsWith(".htm"))
      paramString = paramString + ".htm";
    com.flipdog.commons.e.u.a(this, this, localb.b, paramUri, paramString);
  }

  private void a(CharSequence paramCharSequence)
  {
    if ((paramCharSequence instanceof SpannableStringBuilder))
      paramCharSequence = new com.flipdog.commons.o.a(paramCharSequence);
    this.i.setText(paramCharSequence);
  }

  private void a(List<String> paramList)
  {
    String[] arrayOfString = ad.a(paramList);
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
    localBuilder.setTitle(com.flipdog.spellchecker.m.d.c);
    localBuilder.setSingleChoiceItems(arrayOfString, 0, new k(this, paramList));
    localBuilder.create().show();
  }

  private boolean a(int paramInt1, int paramInt2)
  {
    return paramInt1 == paramInt2;
  }

  private void b(CharSequence paramCharSequence)
  {
    int i1 = C();
    int i2 = D();
    if (paramCharSequence == null)
      paramCharSequence = "";
    this.i.getText().replace(i1, i2, paramCharSequence);
  }

  private boolean b(Uri paramUri)
  {
    if (paramUri == null);
    while (paramUri.getScheme() == null)
      return false;
    return true;
  }

  private void c(Uri paramUri)
  {
    com.flipdog.commons.e.a.a(this, this, as.e("Open"), new c(this, paramUri));
  }

  private InputStream d(Uri paramUri)
    throws IOException
  {
    if (paramUri.getScheme().equals("cloud"))
      try
      {
        InputStream localInputStream = com.flipdog.filebrowser.d.g.b(paramUri);
        return localInputStream;
      }
      catch (com.flipdog.a.g.b localb)
      {
        throw new IOException(localb.getMessage());
      }
    return com.flipdog.spellchecker.a.b.b().openInputStream(paramUri);
  }

  private void p()
  {
  }

  private void q()
  {
    com.flipdog.editor.w localw = new com.flipdog.editor.w();
    localw.a(this, y.editor_toolbar);
    this.n.a(this, this.i, localw);
    localw.m.setVisibility(8);
    localw.n.setVisibility(8);
    localw.j.setVisibility(8);
  }

  private void r()
  {
    this.q = ((com.flipdog.ads.a.f)com.flipdog.commons.i.b.a(com.flipdog.ads.a.f.class)).a();
  }

  private void s()
  {
    Intent localIntent = getIntent();
    this.g.b = localIntent.getAction();
    this.g.a = localIntent.getStringExtra("android.intent.extra.TEXT");
    this.g.c = localIntent.getType();
  }

  private void t()
  {
    AnimationSet localAnimationSet = new AnimationSet(true);
    AlphaAnimation localAlphaAnimation = new AlphaAnimation(0.0F, 1.0F);
    localAlphaAnimation.setDuration(10L);
    localAnimationSet.addAnimation(localAlphaAnimation);
    ((LinearLayout)findViewById(y.layout_main)).setLayoutAnimation(new LayoutAnimationController(localAlphaAnimation));
  }

  private void u()
  {
    View localView = as.a(this, y.main_toolbar);
    this.f.a = ((MyHorizontalScrollView)as.a(localView, y.horizontal_scroll));
    this.f.b = as.a(localView, y.scrollable_to_the_left);
    this.f.c = as.a(localView, y.scrollable_to_the_right);
    this.f.d = ((ImageButton)findViewById(y.clear));
    this.f.e = ((ImageButton)findViewById(y.select_all));
    this.f.f = ((ImageButton)findViewById(y.copy));
    this.f.g = ((ImageButton)findViewById(y.paste));
    this.f.i = ((ImageButton)findViewById(y.save));
    this.f.j = ((ImageButton)findViewById(y.open));
    this.f.h = ((ImageButton)findViewById(y.btn_speak));
  }

  private void v()
  {
    this.f.a.setOnScrollChanged(this);
    this.f.d.setOnClickListener(new r(this));
    this.f.e.setOnClickListener(new q(this));
    this.f.f.setOnClickListener(new p(this));
    this.f.g.setOnClickListener(new o(this));
    this.f.i.setOnClickListener(this);
    this.f.j.setOnClickListener(this);
  }

  private boolean w()
  {
    return a(C(), D());
  }

  private void x()
  {
    this.h = ((ImageButton)findViewById(y.spell_check_button));
    this.i = ((MyEditText)findViewById(y.text));
    this.j = ((ProgressBar)findViewById(y.spell_checker_spinner));
    this.h.setOnClickListener(new n(this));
    this.m = com.flipdog.speller.l.a(this, this.i, this.j);
  }

  private com.flipdog.commons.g.j y()
  {
    return ((h)com.flipdog.commons.i.b.a(h.class)).e();
  }

  private void z()
  {
    String[] arrayOfString1 = getResources().getStringArray(aa.language_values);
    String[] arrayOfString2 = getResources().getStringArray(aa.language_labels);
    int i1 = com.flipdog.spellchecker.a.b.a(arrayOfString1, com.flipdog.speller.d.a().a);
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
    localBuilder.setTitle(com.flipdog.spellchecker.m.d.c);
    localBuilder.setSingleChoiceItems(arrayOfString2, i1, new m(this, arrayOfString1));
    localBuilder.create().show();
  }

  protected void a(Uri paramUri)
    throws IOException
  {
    a(new d(this, com.flipdog.commons.a.n.a(d(paramUri), "utf-8")));
  }

  public void a(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if (paramView == this.f.a)
      T();
  }

  public Context c()
  {
    return this;
  }

  protected void d()
  {
    b(this.k.getText());
  }

  protected void e()
  {
    com.flipdog.commons.c.b localb = N();
    this.l.a(localb);
  }

  protected void f()
  {
    a("");
  }

  protected void g()
  {
    this.i.selectAll();
    this.i.onTextContextMenuItem(16908333);
  }

  protected void h()
  {
    if (w())
    {
      com.flipdog.spellchecker.a.a.a(com.flipdog.spellchecker.c.nothing_selected_to_copy);
      return;
    }
    int i1 = C();
    int i2 = D();
    Editable localEditable = k();
    this.k.setText(localEditable.subSequence(i1, i2));
    com.flipdog.spellchecker.a.a.a(com.flipdog.spellchecker.c.text_was_copied_to_clipboard);
  }

  protected void i()
  {
    com.flipdog.commons.c.b localb = N();
    Intent localIntent1 = new Intent("android.intent.action.SEND");
    localIntent1.setType("text/plain");
    localIntent1.putExtra("android.intent.extra.TEXT", localb.a);
    localIntent1.putExtra("com.flipdog.extra.TEXT_HTML", localb.b);
    Intent localIntent2 = Intent.createChooser(localIntent1, getString(com.flipdog.spellchecker.c.share_with_title));
    try
    {
      startActivity(localIntent2);
      return;
    }
    catch (ActivityNotFoundException localActivityNotFoundException)
    {
      com.flipdog.spellchecker.a.a.a(com.flipdog.spellchecker.c.no_send_application_found);
    }
  }

  protected void j()
  {
    P();
    this.m.b();
  }

  protected Editable k()
  {
    return this.i.getText();
  }

  protected StyleSpan l()
  {
    return new StyleSpan(1);
  }

  protected StyleSpan m()
  {
    return new StyleSpan(2);
  }

  protected UnderlineSpan n()
  {
    return new UnderlineSpan();
  }

  protected void o()
  {
    H();
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    ArrayList localArrayList;
    if ((paramInt1 == 1) && (paramInt2 == -1))
    {
      localArrayList = paramIntent.getStringArrayListExtra("android.speech.extra.RESULTS");
      if ((localArrayList.size() == 0) || (!ax.c("spell", (String)localArrayList.get(0))))
        break label240;
    }
    label146: label166: label238: label240: for (int i1 = 1; ; i1 = 0)
    {
      if (i1 != 0)
      {
        j();
        this.l.a(paramInt1, paramInt2, paramIntent);
        if (paramInt2 == -1)
        {
          if (paramInt1 != 3)
            break label146;
          Uri localUri2 = com.flipdog.f.a(paramInt2, paramIntent);
          if (localUri2 != null)
            new l(this, this, ".htm", localUri2);
        }
      }
      while (true)
      {
        super.onActivityResult(paramInt1, paramInt2, paramIntent);
        return;
        if (localArrayList.size() == 1)
        {
          b((CharSequence)localArrayList.get(0));
          break;
        }
        a(localArrayList);
        break;
        if (paramInt1 == 4)
        {
          Iterator localIterator = com.flipdog.f.b(paramInt2, paramIntent).iterator();
          Object localObject = null;
          if (!localIterator.hasNext());
          while (true)
          {
            if (localObject == null)
              break label238;
            com.flipdog.commons.a.a.a(this, (CharSequence)localObject);
            break;
            Uri localUri1 = (Uri)localIterator.next();
            if (!b(localUri1))
            {
              localObject = String.format("Selected file has invalid URI. %s", new Object[] { localUri1 });
              break label166;
            }
            c(localUri1);
          }
        }
      }
    }
  }

  public void onClick(View paramView)
  {
    if (paramView.getId() == y.btn_speak)
      B();
    do
    {
      return;
      if (paramView == this.f.i)
      {
        R();
        return;
      }
    }
    while (paramView != this.f.j);
    S();
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    Track.it("x.1", new String[] { "Eula" });
    setContentView(com.flipdog.spellchecker.u.spell_checker_layout);
    com.flipdog.errors.a.a(this);
    while (true)
    {
      try
      {
        Track.it("x.2", new String[] { "Eula" });
        if (!Q())
        {
          Track.it("x.3", new String[] { "Eula" });
          EulaActivity.a(this, t.eula, 1);
          Track.it("x.4", new String[] { "Eula" });
          finish();
          Track.it("x.5", new String[] { "Eula" });
          return;
        }
        Track.it("x.6", new String[] { "Eula" });
        com.flipdog.ads.l.a(this, y.layout_main);
        com.flipdog.spellchecker.c.a.a(this, y.layout_main);
        this.k = ((ClipboardManager)getSystemService("clipboard"));
        this.s = ((com.flipdog.commons.s.b)com.flipdog.commons.i.b.a(com.flipdog.commons.s.b.class));
        this.l = new e(this);
        x();
        u();
        p();
        O();
        A();
        s();
        q();
        aw.a(this.f.b, this.f.c);
        if (this.g.b == null)
        {
          this.p = true;
          if (("com.flipdog.action.SPELL".equals(this.g.b)) && (this.g.a != null))
          {
            if (!this.g.a())
              break label427;
            a(a(this.g.a));
          }
          v();
          if (getPackageManager().queryIntentActivities(new Intent("android.speech.action.RECOGNIZE_SPEECH"), 0).size() == 0)
            break label441;
          this.f.h.setOnClickListener(this);
          ((com.flipdog.ads.c.d)com.flipdog.commons.i.b.a(com.flipdog.ads.c.d.class)).a();
          this.i.requestFocus();
          r();
          new WebView(this).clearCache(true);
          Track.it("x.7", new String[] { "Eula" });
          return;
        }
      }
      catch (Exception localException)
      {
        Track.it(localException);
        Track.it("x.8", new String[] { "Eula" });
        ErrorActivity.a(this, localException);
        finish();
        return;
      }
      this.p = false;
      continue;
      label427: a(this.g.a);
      continue;
      label441: this.f.h.setEnabled(false);
    }
  }

  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    getMenuInflater().inflate(v.main_activity_options_menu, paramMenu);
    return super.onCreateOptionsMenu(paramMenu);
  }

  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if (paramInt == 4)
      G();
    return super.onKeyDown(paramInt, paramKeyEvent);
  }

  public boolean onKeyUp(int paramInt, KeyEvent paramKeyEvent)
  {
    this.n.a(paramInt, paramKeyEvent);
    return super.onKeyUp(paramInt, paramKeyEvent);
  }

  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    int i1 = paramMenuItem.getItemId();
    if (i1 == y.help)
    {
      F();
      return true;
    }
    if (i1 == y.about)
    {
      E();
      return true;
    }
    if (i1 == y.send)
    {
      i();
      return true;
    }
    if (i1 == y.print)
    {
      e();
      return true;
    }
    if (i1 == y.language)
    {
      z();
      return true;
    }
    return super.onOptionsItemSelected(paramMenuItem);
  }

  protected void onPause()
  {
    super.onPause();
    H();
    L();
  }

  protected void onResume()
  {
    super.onResume();
    K();
  }

  protected void onStart()
  {
    super.onStart();
  }

  protected void onStop()
  {
    super.onStop();
  }

  public void onWindowFocusChanged(boolean paramBoolean)
  {
    super.onWindowFocusChanged(paramBoolean);
    T();
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.spellchecker.activity.SpellCheckerActivity
 * JD-Core Version:    0.6.2
 */