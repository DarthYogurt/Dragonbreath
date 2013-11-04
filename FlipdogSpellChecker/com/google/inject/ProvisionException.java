package com.google.inject;

import com.google.inject.internal.Errors;
import com.google.inject.internal.ImmutableList;
import com.google.inject.internal.ImmutableSet;
import com.google.inject.internal.Preconditions;
import com.google.inject.spi.Message;
import java.util.Collection;

public final class ProvisionException extends RuntimeException
{
  private static final long serialVersionUID;
  private final ImmutableSet<Message> messages;

  public ProvisionException(Iterable<Message> paramIterable)
  {
    this.messages = ImmutableSet.copyOf(paramIterable);
    if (!this.messages.isEmpty());
    for (boolean bool = true; ; bool = false)
    {
      Preconditions.checkArgument(bool);
      initCause(Errors.getOnlyCause(this.messages));
      return;
    }
  }

  public ProvisionException(String paramString)
  {
    this.messages = ImmutableSet.of(new Message(paramString));
  }

  public ProvisionException(String paramString, Throwable paramThrowable)
  {
    super(paramThrowable);
    this.messages = ImmutableSet.of(new Message(ImmutableList.of(), paramString, paramThrowable));
  }

  public Collection<Message> getErrorMessages()
  {
    return this.messages;
  }

  public String getMessage()
  {
    return Errors.format("Guice provision errors", this.messages);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.ProvisionException
 * JD-Core Version:    0.6.2
 */