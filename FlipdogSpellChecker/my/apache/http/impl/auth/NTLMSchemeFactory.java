package my.apache.http.impl.auth;

import my.apache.http.annotation.Immutable;
import my.apache.http.auth.AuthScheme;
import my.apache.http.auth.AuthSchemeFactory;
import my.apache.http.params.HttpParams;

@Immutable
public class NTLMSchemeFactory
  implements AuthSchemeFactory
{
  public AuthScheme newInstance(HttpParams paramHttpParams)
  {
    return new NTLMScheme(new NTLMEngineImpl());
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.impl.auth.NTLMSchemeFactory
 * JD-Core Version:    0.6.2
 */