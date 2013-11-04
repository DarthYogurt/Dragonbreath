package com.google.inject;

import com.google.inject.internal.Errors;
import com.google.inject.spi.Message;
import java.util.logging.Level;
import java.util.logging.Logger;

class MessageProcessor extends AbstractProcessor
{
  private static final Logger logger = Logger.getLogger(Guice.class.getName());

  MessageProcessor(Errors paramErrors)
  {
    super(paramErrors);
  }

  public static String getRootMessage(Throwable paramThrowable)
  {
    Throwable localThrowable = paramThrowable.getCause();
    if (localThrowable == null)
      return paramThrowable.toString();
    return getRootMessage(localThrowable);
  }

  public Boolean visit(Message paramMessage)
  {
    if (paramMessage.getCause() != null)
    {
      String str = getRootMessage(paramMessage.getCause());
      logger.log(Level.INFO, "An exception was caught and reported. Message: " + str, paramMessage.getCause());
    }
    this.errors.addMessage(paramMessage);
    return Boolean.valueOf(true);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.MessageProcessor
 * JD-Core Version:    0.6.2
 */