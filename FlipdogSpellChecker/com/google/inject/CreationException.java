package com.google.inject;

import com.google.inject.internal.Errors;
import com.google.inject.internal.ImmutableSet;
import com.google.inject.internal.Preconditions;
import com.google.inject.spi.Message;
import java.util.Collection;

public class CreationException extends RuntimeException
{
  private static final long serialVersionUID;
  private final ImmutableSet<Message> messages;

  public CreationException(Collection<Message> paramCollection)
  {
    this.messages = ImmutableSet.copyOf(paramCollection);
    if (!this.messages.isEmpty());
    for (boolean bool = true; ; bool = false)
    {
      Preconditions.checkArgument(bool);
      initCause(Errors.getOnlyCause(this.messages));
      return;
    }
  }

  public Collection<Message> getErrorMessages()
  {
    return this.messages;
  }

  public String getMessage()
  {
    return Errors.format("Guice creation errors", this.messages);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.CreationException
 * JD-Core Version:    0.6.2
 */