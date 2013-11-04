package org.json.simple.parser;

public class Yytoken
{
  public static final int TYPE_COLON = 6;
  public static final int TYPE_COMMA = 5;
  public static final int TYPE_EOF = -1;
  public static final int TYPE_LEFT_BRACE = 1;
  public static final int TYPE_LEFT_SQUARE = 3;
  public static final int TYPE_RIGHT_BRACE = 2;
  public static final int TYPE_RIGHT_SQUARE = 4;
  public static final int TYPE_VALUE;
  public int type = 0;
  public Object value = null;

  public Yytoken(int paramInt, Object paramObject)
  {
    this.type = paramInt;
    this.value = paramObject;
  }

  public String toString()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    switch (this.type)
    {
    default:
    case 0:
    case 1:
    case 2:
    case 3:
    case 4:
    case 5:
    case 6:
    case -1:
    }
    while (true)
    {
      return localStringBuffer.toString();
      localStringBuffer.append("VALUE(").append(this.value).append(")");
      continue;
      localStringBuffer.append("LEFT BRACE({)");
      continue;
      localStringBuffer.append("RIGHT BRACE(})");
      continue;
      localStringBuffer.append("LEFT SQUARE([)");
      continue;
      localStringBuffer.append("RIGHT SQUARE(])");
      continue;
      localStringBuffer.append("COMMA(,)");
      continue;
      localStringBuffer.append("COLON(:)");
      continue;
      localStringBuffer.append("END OF FILE");
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     org.json.simple.parser.Yytoken
 * JD-Core Version:    0.6.2
 */