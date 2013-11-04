package org.json.simple.parser;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class JSONParser
{
  public static final int S_END = 6;
  public static final int S_INIT = 0;
  public static final int S_IN_ARRAY = 3;
  public static final int S_IN_ERROR = -1;
  public static final int S_IN_FINISHED_VALUE = 1;
  public static final int S_IN_OBJECT = 2;
  public static final int S_IN_PAIR_VALUE = 5;
  public static final int S_PASSED_PAIR_KEY = 4;
  private LinkedList handlerStatusStack;
  private Yylex lexer = new Yylex((Reader)null);
  private int status = 0;
  private Yytoken token = null;

  private List createArrayContainer(ContainerFactory paramContainerFactory)
  {
    Object localObject;
    if (paramContainerFactory == null)
      localObject = new JSONArray();
    do
    {
      return localObject;
      localObject = paramContainerFactory.creatArrayContainer();
    }
    while (localObject != null);
    return new JSONArray();
  }

  private Map createObjectContainer(ContainerFactory paramContainerFactory)
  {
    Object localObject;
    if (paramContainerFactory == null)
      localObject = new JSONObject();
    do
    {
      return localObject;
      localObject = paramContainerFactory.createObjectContainer();
    }
    while (localObject != null);
    return new JSONObject();
  }

  private void nextToken()
    throws ParseException, IOException
  {
    this.token = this.lexer.yylex();
    if (this.token == null)
      this.token = new Yytoken(-1, null);
  }

  private int peekStatus(LinkedList paramLinkedList)
  {
    if (paramLinkedList.size() == 0)
      return -1;
    return ((Integer)paramLinkedList.getFirst()).intValue();
  }

  public int getPosition()
  {
    return this.lexer.getPosition();
  }

  public Object parse(Reader paramReader)
    throws IOException, ParseException
  {
    return parse(paramReader, (ContainerFactory)null);
  }

  public Object parse(Reader paramReader, ContainerFactory paramContainerFactory)
    throws IOException, ParseException
  {
    reset(paramReader);
    LinkedList localLinkedList1 = new LinkedList();
    LinkedList localLinkedList2 = new LinkedList();
    label935: int i;
    do
    {
      while (true)
      {
        try
        {
          nextToken();
          switch (this.status)
          {
          default:
            if (this.status != -1)
              break label935;
            throw new ParseException(getPosition(), 1, this.token);
          case 0:
          case 1:
          case 2:
          case 4:
          case 3:
          case -1:
          }
        }
        catch (IOException localIOException)
        {
          throw localIOException;
        }
        switch (this.token.type)
        {
        case 2:
        default:
          this.status = -1;
          break;
        case 0:
          this.status = 1;
          localLinkedList1.addFirst(new Integer(this.status));
          localLinkedList2.addFirst(this.token.value);
          break;
        case 1:
          this.status = 2;
          localLinkedList1.addFirst(new Integer(this.status));
          localLinkedList2.addFirst(createObjectContainer(paramContainerFactory));
          break;
        case 3:
          this.status = 3;
          localLinkedList1.addFirst(new Integer(this.status));
          localLinkedList2.addFirst(createArrayContainer(paramContainerFactory));
          continue;
          if (this.token.type == -1)
            return localLinkedList2.removeFirst();
          throw new ParseException(getPosition(), 1, this.token);
          switch (this.token.type)
          {
          case 5:
          case 1:
          case 3:
          case 4:
          default:
            this.status = -1;
            break;
          case 0:
            if ((this.token.value instanceof String))
            {
              localLinkedList2.addFirst((String)this.token.value);
              this.status = 4;
              localLinkedList1.addFirst(new Integer(this.status));
            }
            else
            {
              this.status = -1;
            }
            break;
          case 2:
            if (localLinkedList2.size() > 1)
            {
              localLinkedList1.removeFirst();
              localLinkedList2.removeFirst();
              this.status = peekStatus(localLinkedList1);
            }
            else
            {
              this.status = 1;
              continue;
              switch (this.token.type)
              {
              case 6:
              case 2:
              case 4:
              case 5:
              default:
                this.status = -1;
                break;
              case 0:
                localLinkedList1.removeFirst();
                String str3 = (String)localLinkedList2.removeFirst();
                ((Map)localLinkedList2.getFirst()).put(str3, this.token.value);
                this.status = peekStatus(localLinkedList1);
                break;
              case 3:
                localLinkedList1.removeFirst();
                String str2 = (String)localLinkedList2.removeFirst();
                Map localMap4 = (Map)localLinkedList2.getFirst();
                List localList4 = createArrayContainer(paramContainerFactory);
                localMap4.put(str2, localList4);
                this.status = 3;
                localLinkedList1.addFirst(new Integer(this.status));
                localLinkedList2.addFirst(localList4);
                break;
              case 1:
                localLinkedList1.removeFirst();
                String str1 = (String)localLinkedList2.removeFirst();
                Map localMap2 = (Map)localLinkedList2.getFirst();
                Map localMap3 = createObjectContainer(paramContainerFactory);
                localMap2.put(str1, localMap3);
                this.status = 2;
                localLinkedList1.addFirst(new Integer(this.status));
                localLinkedList2.addFirst(localMap3);
                continue;
                switch (this.token.type)
                {
                case 5:
                case 2:
                default:
                  this.status = -1;
                  break;
                case 0:
                  ((List)localLinkedList2.getFirst()).add(this.token.value);
                  break;
                case 4:
                  if (localLinkedList2.size() > 1)
                  {
                    localLinkedList1.removeFirst();
                    localLinkedList2.removeFirst();
                    this.status = peekStatus(localLinkedList1);
                  }
                  else
                  {
                    this.status = 1;
                  }
                  break;
                case 1:
                  List localList3 = (List)localLinkedList2.getFirst();
                  Map localMap1 = createObjectContainer(paramContainerFactory);
                  localList3.add(localMap1);
                  this.status = 2;
                  localLinkedList1.addFirst(new Integer(this.status));
                  localLinkedList2.addFirst(localMap1);
                  break;
                case 3:
                  List localList1 = (List)localLinkedList2.getFirst();
                  List localList2 = createArrayContainer(paramContainerFactory);
                  localList1.add(localList2);
                  this.status = 3;
                  localLinkedList1.addFirst(new Integer(this.status));
                  localLinkedList2.addFirst(localList2);
                  continue;
                  throw new ParseException(getPosition(), 1, this.token);
                }
                break;
              }
            }
            break;
          }
          break;
        }
      }
      i = this.token.type;
    }
    while (i != -1);
    throw new ParseException(getPosition(), 1, this.token);
  }

  public Object parse(String paramString)
    throws ParseException
  {
    return parse(paramString, (ContainerFactory)null);
  }

  public Object parse(String paramString, ContainerFactory paramContainerFactory)
    throws ParseException
  {
    StringReader localStringReader = new StringReader(paramString);
    try
    {
      Object localObject = parse(localStringReader, paramContainerFactory);
      return localObject;
    }
    catch (IOException localIOException)
    {
      throw new ParseException(-1, 2, localIOException);
    }
  }

  public void parse(Reader paramReader, ContentHandler paramContentHandler)
    throws IOException, ParseException
  {
    parse(paramReader, paramContentHandler, false);
  }

  public void parse(Reader paramReader, ContentHandler paramContentHandler, boolean paramBoolean)
    throws IOException, ParseException
  {
    LinkedList localLinkedList;
    if (!paramBoolean)
    {
      reset(paramReader);
      this.handlerStatusStack = new LinkedList();
      localLinkedList = this.handlerStatusStack;
    }
    label963: int i;
    do
    {
      do
      {
        do
        {
          do
            while (true)
            {
              try
              {
                switch (this.status)
                {
                default:
                  if (this.status != -1)
                    break label963;
                  throw new ParseException(getPosition(), 1, this.token);
                case 0:
                case 1:
                case 2:
                case 4:
                case 5:
                case 3:
                case -1:
                case 6:
                }
              }
              catch (IOException localIOException)
              {
                this.status = -1;
                throw localIOException;
                if (this.handlerStatusStack != null)
                  break;
                reset(paramReader);
                this.handlerStatusStack = new LinkedList();
                break;
                paramContentHandler.startJSON();
                nextToken();
                switch (this.token.type)
                {
                case 2:
                default:
                  this.status = -1;
                  continue;
                case 0:
                case 1:
                case 3:
                }
              }
              catch (ParseException localParseException)
              {
                this.status = -1;
                throw localParseException;
                this.status = 1;
                localLinkedList.addFirst(new Integer(this.status));
                if (paramContentHandler.primitive(this.token.value))
                  continue;
                return;
                this.status = 2;
                localLinkedList.addFirst(new Integer(this.status));
                if (paramContentHandler.startObject())
                  continue;
                return;
                this.status = 3;
                localLinkedList.addFirst(new Integer(this.status));
                if (paramContentHandler.startArray())
                  continue;
                return;
                nextToken();
                if (this.token.type == -1)
                {
                  paramContentHandler.endJSON();
                  this.status = 6;
                  return;
                }
              }
              catch (RuntimeException localRuntimeException)
              {
                this.status = -1;
                throw localRuntimeException;
                this.status = -1;
                throw new ParseException(getPosition(), 1, this.token);
              }
              catch (Error localError)
              {
                this.status = -1;
                throw localError;
              }
              nextToken();
              switch (this.token.type)
              {
              case 5:
              case 1:
              case 3:
              case 4:
              default:
                this.status = -1;
                break;
              case 0:
                if ((this.token.value instanceof String))
                {
                  String str = (String)this.token.value;
                  this.status = 4;
                  localLinkedList.addFirst(new Integer(this.status));
                  if (paramContentHandler.startObjectEntry(str));
                }
                else
                {
                  this.status = -1;
                }
                break;
              case 2:
                if (localLinkedList.size() > 1)
                  localLinkedList.removeFirst();
                for (this.status = peekStatus(localLinkedList); !paramContentHandler.endObject(); this.status = 1)
                  return;
                continue;
                nextToken();
                switch (this.token.type)
                {
                case 6:
                case 2:
                case 4:
                case 5:
                default:
                  this.status = -1;
                  break;
                case 0:
                  localLinkedList.removeFirst();
                  this.status = peekStatus(localLinkedList);
                  if (!paramContentHandler.primitive(this.token.value))
                    return;
                  if (!paramContentHandler.endObjectEntry())
                    return;
                  break;
                case 3:
                  localLinkedList.removeFirst();
                  localLinkedList.addFirst(new Integer(5));
                  this.status = 3;
                  localLinkedList.addFirst(new Integer(this.status));
                  if (!paramContentHandler.startArray())
                    return;
                  break;
                case 1:
                  localLinkedList.removeFirst();
                  localLinkedList.addFirst(new Integer(5));
                  this.status = 2;
                  localLinkedList.addFirst(new Integer(this.status));
                  if (!paramContentHandler.startObject())
                  {
                    return;
                    localLinkedList.removeFirst();
                    this.status = peekStatus(localLinkedList);
                    if (!paramContentHandler.endObjectEntry())
                    {
                      return;
                      nextToken();
                      switch (this.token.type)
                      {
                      case 5:
                      case 2:
                      default:
                        this.status = -1;
                      case 0:
                      case 4:
                      case 1:
                      case 3:
                      }
                    }
                  }
                  break;
                }
                break;
              }
            }
          while (paramContentHandler.primitive(this.token.value));
          return;
          if (localLinkedList.size() > 1)
            localLinkedList.removeFirst();
          for (this.status = peekStatus(localLinkedList); !paramContentHandler.endArray(); this.status = 1)
            return;
          this.status = 2;
          localLinkedList.addFirst(new Integer(this.status));
        }
        while (paramContentHandler.startObject());
        return;
        this.status = 3;
        localLinkedList.addFirst(new Integer(this.status));
      }
      while (paramContentHandler.startArray());
      return;
      throw new ParseException(getPosition(), 1, this.token);
      i = this.token.type;
    }
    while (i != -1);
    this.status = -1;
    throw new ParseException(getPosition(), 1, this.token);
  }

  public void parse(String paramString, ContentHandler paramContentHandler)
    throws ParseException
  {
    parse(paramString, paramContentHandler, false);
  }

  public void parse(String paramString, ContentHandler paramContentHandler, boolean paramBoolean)
    throws ParseException
  {
    StringReader localStringReader = new StringReader(paramString);
    try
    {
      parse(localStringReader, paramContentHandler, paramBoolean);
      return;
    }
    catch (IOException localIOException)
    {
      throw new ParseException(-1, 2, localIOException);
    }
  }

  public void reset()
  {
    this.token = null;
    this.status = 0;
    this.handlerStatusStack = null;
  }

  public void reset(Reader paramReader)
  {
    this.lexer.yyreset(paramReader);
    reset();
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     org.json.simple.parser.JSONParser
 * JD-Core Version:    0.6.2
 */