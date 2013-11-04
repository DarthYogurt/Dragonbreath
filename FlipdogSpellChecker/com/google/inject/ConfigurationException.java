package com.google.inject;

import com.google.inject.internal.Errors;
import com.google.inject.internal.ImmutableSet;
import com.google.inject.internal.Preconditions;
import com.google.inject.spi.Message;
import java.util.Collection;

public final class ConfigurationException extends RuntimeException
{
  private static final long serialVersionUID;
  private final ImmutableSet<Message> messages;
  private Object partialValue = null;

  public ConfigurationException(Iterable<Message> paramIterable)
  {
    this.messages = ImmutableSet.copyOf(paramIterable);
    initCause(Errors.getOnlyCause(this.messages));
  }

  public Collection<Message> getErrorMessages()
  {
    return this.messages;
  }

  public String getMessage()
  {
    return Errors.format("Guice configuration errors", this.messages);
  }

  public <E> E getPartialValue()
  {
    return this.partialValue;
  }

  public ConfigurationException withPartialValue(Object paramObject)
  {
    if (this.partialValue == null);
    for (boolean bool = true; ; bool = false)
    {
      Object[] arrayOfObject = new Object[2];
      arrayOfObject[0] = this.partialValue;
      arrayOfObject[1] = paramObject;
      Preconditions.checkState(bool, "Can't clobber existing partial value %s with %s", arrayOfObject);
      ConfigurationException localConfigurationException = new ConfigurationException(this.messages);
      localConfigurationException.partialValue = paramObject;
      return localConfigurationException;
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.ConfigurationException
 * JD-Core Version:    0.6.2
 */