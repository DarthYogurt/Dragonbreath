package com.flipdog.a.f;

import java.io.File;

public abstract class e extends f
  implements com.flipdog.a.a.a.c
{
  public e(String paramString)
  {
    super(paramString);
  }

  protected abstract c a();

  public void a(File paramFile, String paramString, com.flipdog.a.b.b.b paramb)
    throws com.flipdog.a.g.b
  {
    if (paramString == null)
      paramString = paramFile.getName();
    a("Delete file: %s", new Object[] { paramString });
    com.flipdog.a.b.b.c localc = com.flipdog.a.c.b.a(a().b(paramb.e()).e, paramString);
    if (localc == null)
    {
      a("File not exists: %s. Nothing to remove.", new Object[] { paramString });
      return;
    }
    b(localc);
  }

  public void b(com.flipdog.a.b.b.b paramb, String paramString)
    throws com.flipdog.a.g.b
  {
    a("Delete folder: %s", new Object[] { paramString });
    com.flipdog.a.b.b.b localb = com.flipdog.a.c.b.b(a().b(paramb.e()).e, paramString);
    if (localb == null)
    {
      a("Folder not exists: %s. Nothing to remove.", new Object[] { paramString });
      return;
    }
    a(localb);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.a.f.e
 * JD-Core Version:    0.6.2
 */