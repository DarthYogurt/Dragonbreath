package com.google.inject.spi;

import com.google.inject.Binder;
import com.google.inject.internal.Errors;
import com.google.inject.internal.ImmutableList;
import com.google.inject.internal.Objects;
import com.google.inject.internal.Preconditions;
import com.google.inject.internal.SourceProvider;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.List;

public final class Message
  implements Serializable, Element
{
  private static final long serialVersionUID;
  private final Throwable cause;
  private final String message;
  private final List<Object> sources;

  public Message(Object paramObject, String paramString)
  {
    this(ImmutableList.of(paramObject), paramString, null);
  }

  public Message(String paramString)
  {
    this(ImmutableList.of(), paramString, null);
  }

  public Message(List<Object> paramList, String paramString, Throwable paramThrowable)
  {
    this.sources = ImmutableList.copyOf(paramList);
    this.message = ((String)Preconditions.checkNotNull(paramString, "message"));
    this.cause = paramThrowable;
  }

  private Object writeReplace()
    throws ObjectStreamException
  {
    Object[] arrayOfObject = this.sources.toArray();
    for (int i = 0; i < arrayOfObject.length; i++)
      arrayOfObject[i] = Errors.convert(arrayOfObject[i]).toString();
    return new Message(ImmutableList.of(arrayOfObject), this.message, this.cause);
  }

  public <T> T acceptVisitor(ElementVisitor<T> paramElementVisitor)
  {
    return paramElementVisitor.visit(this);
  }

  public void applyTo(Binder paramBinder)
  {
    paramBinder.withSource(getSource()).addError(this);
  }

  public boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof Message));
    Message localMessage;
    do
    {
      return false;
      localMessage = (Message)paramObject;
    }
    while ((!this.message.equals(localMessage.message)) || (!Objects.equal(this.cause, localMessage.cause)) || (!this.sources.equals(localMessage.sources)));
    return true;
  }

  public Throwable getCause()
  {
    return this.cause;
  }

  public String getMessage()
  {
    return this.message;
  }

  public String getSource()
  {
    if (this.sources.isEmpty())
      return SourceProvider.UNKNOWN_SOURCE.toString();
    return Errors.convert(this.sources.get(-1 + this.sources.size())).toString();
  }

  public List<Object> getSources()
  {
    return this.sources;
  }

  public int hashCode()
  {
    return this.message.hashCode();
  }

  public String toString()
  {
    return this.message;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.spi.Message
 * JD-Core Version:    0.6.2
 */