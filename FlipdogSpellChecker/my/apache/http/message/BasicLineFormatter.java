package my.apache.http.message;

import my.apache.http.FormattedHeader;
import my.apache.http.Header;
import my.apache.http.ProtocolVersion;
import my.apache.http.RequestLine;
import my.apache.http.StatusLine;
import my.apache.http.annotation.Immutable;
import my.apache.http.util.CharArrayBuffer;

@Immutable
public class BasicLineFormatter
  implements LineFormatter
{
  public static final BasicLineFormatter DEFAULT = new BasicLineFormatter();

  public static final String formatHeader(Header paramHeader, LineFormatter paramLineFormatter)
  {
    if (paramLineFormatter == null)
      paramLineFormatter = DEFAULT;
    return paramLineFormatter.formatHeader(null, paramHeader).toString();
  }

  public static final String formatProtocolVersion(ProtocolVersion paramProtocolVersion, LineFormatter paramLineFormatter)
  {
    if (paramLineFormatter == null)
      paramLineFormatter = DEFAULT;
    return paramLineFormatter.appendProtocolVersion(null, paramProtocolVersion).toString();
  }

  public static final String formatRequestLine(RequestLine paramRequestLine, LineFormatter paramLineFormatter)
  {
    if (paramLineFormatter == null)
      paramLineFormatter = DEFAULT;
    return paramLineFormatter.formatRequestLine(null, paramRequestLine).toString();
  }

  public static final String formatStatusLine(StatusLine paramStatusLine, LineFormatter paramLineFormatter)
  {
    if (paramLineFormatter == null)
      paramLineFormatter = DEFAULT;
    return paramLineFormatter.formatStatusLine(null, paramStatusLine).toString();
  }

  public CharArrayBuffer appendProtocolVersion(CharArrayBuffer paramCharArrayBuffer, ProtocolVersion paramProtocolVersion)
  {
    if (paramProtocolVersion == null)
      throw new IllegalArgumentException("Protocol version may not be null");
    CharArrayBuffer localCharArrayBuffer = paramCharArrayBuffer;
    int i = estimateProtocolVersionLen(paramProtocolVersion);
    if (localCharArrayBuffer == null)
      localCharArrayBuffer = new CharArrayBuffer(i);
    while (true)
    {
      localCharArrayBuffer.append(paramProtocolVersion.getProtocol());
      localCharArrayBuffer.append('/');
      localCharArrayBuffer.append(Integer.toString(paramProtocolVersion.getMajor()));
      localCharArrayBuffer.append('.');
      localCharArrayBuffer.append(Integer.toString(paramProtocolVersion.getMinor()));
      return localCharArrayBuffer;
      localCharArrayBuffer.ensureCapacity(i);
    }
  }

  protected void doFormatHeader(CharArrayBuffer paramCharArrayBuffer, Header paramHeader)
  {
    String str1 = paramHeader.getName();
    String str2 = paramHeader.getValue();
    int i = 2 + str1.length();
    if (str2 != null)
      i += str2.length();
    paramCharArrayBuffer.ensureCapacity(i);
    paramCharArrayBuffer.append(str1);
    paramCharArrayBuffer.append(": ");
    if (str2 != null)
      paramCharArrayBuffer.append(str2);
  }

  protected void doFormatRequestLine(CharArrayBuffer paramCharArrayBuffer, RequestLine paramRequestLine)
  {
    String str1 = paramRequestLine.getMethod();
    String str2 = paramRequestLine.getUri();
    paramCharArrayBuffer.ensureCapacity(1 + (1 + str1.length() + str2.length()) + estimateProtocolVersionLen(paramRequestLine.getProtocolVersion()));
    paramCharArrayBuffer.append(str1);
    paramCharArrayBuffer.append(' ');
    paramCharArrayBuffer.append(str2);
    paramCharArrayBuffer.append(' ');
    appendProtocolVersion(paramCharArrayBuffer, paramRequestLine.getProtocolVersion());
  }

  protected void doFormatStatusLine(CharArrayBuffer paramCharArrayBuffer, StatusLine paramStatusLine)
  {
    int i = 1 + (3 + (1 + estimateProtocolVersionLen(paramStatusLine.getProtocolVersion())));
    String str = paramStatusLine.getReasonPhrase();
    if (str != null)
      i += str.length();
    paramCharArrayBuffer.ensureCapacity(i);
    appendProtocolVersion(paramCharArrayBuffer, paramStatusLine.getProtocolVersion());
    paramCharArrayBuffer.append(' ');
    paramCharArrayBuffer.append(Integer.toString(paramStatusLine.getStatusCode()));
    paramCharArrayBuffer.append(' ');
    if (str != null)
      paramCharArrayBuffer.append(str);
  }

  protected int estimateProtocolVersionLen(ProtocolVersion paramProtocolVersion)
  {
    return 4 + paramProtocolVersion.getProtocol().length();
  }

  public CharArrayBuffer formatHeader(CharArrayBuffer paramCharArrayBuffer, Header paramHeader)
  {
    if (paramHeader == null)
      throw new IllegalArgumentException("Header may not be null");
    if ((paramHeader instanceof FormattedHeader))
      return ((FormattedHeader)paramHeader).getBuffer();
    CharArrayBuffer localCharArrayBuffer = initBuffer(paramCharArrayBuffer);
    doFormatHeader(localCharArrayBuffer, paramHeader);
    return localCharArrayBuffer;
  }

  public CharArrayBuffer formatRequestLine(CharArrayBuffer paramCharArrayBuffer, RequestLine paramRequestLine)
  {
    if (paramRequestLine == null)
      throw new IllegalArgumentException("Request line may not be null");
    CharArrayBuffer localCharArrayBuffer = initBuffer(paramCharArrayBuffer);
    doFormatRequestLine(localCharArrayBuffer, paramRequestLine);
    return localCharArrayBuffer;
  }

  public CharArrayBuffer formatStatusLine(CharArrayBuffer paramCharArrayBuffer, StatusLine paramStatusLine)
  {
    if (paramStatusLine == null)
      throw new IllegalArgumentException("Status line may not be null");
    CharArrayBuffer localCharArrayBuffer = initBuffer(paramCharArrayBuffer);
    doFormatStatusLine(localCharArrayBuffer, paramStatusLine);
    return localCharArrayBuffer;
  }

  protected CharArrayBuffer initBuffer(CharArrayBuffer paramCharArrayBuffer)
  {
    if (paramCharArrayBuffer != null)
    {
      paramCharArrayBuffer.clear();
      return paramCharArrayBuffer;
    }
    return new CharArrayBuffer(64);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.message.BasicLineFormatter
 * JD-Core Version:    0.6.2
 */