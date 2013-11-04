package org.json.simple.parser;

public class ParseException extends Exception
{
  public static final int ERROR_UNEXPECTED_CHAR = 0;
  public static final int ERROR_UNEXPECTED_EXCEPTION = 2;
  public static final int ERROR_UNEXPECTED_TOKEN = 1;
  private static final long serialVersionUID = -7880698968187728548L;
  private int errorType;
  private int position;
  private Object unexpectedObject;

  public ParseException(int paramInt)
  {
    this(-1, paramInt, null);
  }

  public ParseException(int paramInt1, int paramInt2, Object paramObject)
  {
    this.position = paramInt1;
    this.errorType = paramInt2;
    this.unexpectedObject = paramObject;
  }

  public ParseException(int paramInt, Object paramObject)
  {
    this(-1, paramInt, paramObject);
  }

  public int getErrorType()
  {
    return this.errorType;
  }

  public int getPosition()
  {
    return this.position;
  }

  public Object getUnexpectedObject()
  {
    return this.unexpectedObject;
  }

  public void setErrorType(int paramInt)
  {
    this.errorType = paramInt;
  }

  public void setPosition(int paramInt)
  {
    this.position = paramInt;
  }

  public void setUnexpectedObject(Object paramObject)
  {
    this.unexpectedObject = paramObject;
  }

  public String toString()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    switch (this.errorType)
    {
    default:
      localStringBuffer.append("Unkown error at position ").append(this.position).append(".");
    case 0:
    case 1:
    case 2:
    }
    while (true)
    {
      return localStringBuffer.toString();
      localStringBuffer.append("Unexpected character (").append(this.unexpectedObject).append(") at position ").append(this.position).append(".");
      continue;
      localStringBuffer.append("Unexpected token ").append(this.unexpectedObject).append(" at position ").append(this.position).append(".");
      continue;
      localStringBuffer.append("Unexpected exception at position ").append(this.position).append(": ").append(this.unexpectedObject);
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     org.json.simple.parser.ParseException
 * JD-Core Version:    0.6.2
 */