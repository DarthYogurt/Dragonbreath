package my.apache.http.concurrent;

public abstract interface FutureCallback<T>
{
  public abstract void cancelled();

  public abstract void completed(T paramT);

  public abstract void failed(Exception paramException);
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.concurrent.FutureCallback
 * JD-Core Version:    0.6.2
 */