package my.apache.http.client.utils;

import my.apache.http.annotation.Immutable;

@Immutable
public class CloneUtils
{
  // ERROR //
  public static Object clone(Object paramObject)
    throws java.lang.CloneNotSupportedException
  {
    // Byte code:
    //   0: aload_0
    //   1: ifnonnull +5 -> 6
    //   4: aconst_null
    //   5: areturn
    //   6: aload_0
    //   7: instanceof 21
    //   10: ifeq +90 -> 100
    //   13: aload_0
    //   14: invokevirtual 25	java/lang/Object:getClass	()Ljava/lang/Class;
    //   17: astore_1
    //   18: aload_1
    //   19: ldc 26
    //   21: aconst_null
    //   22: invokevirtual 32	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   25: astore_3
    //   26: aload_3
    //   27: aload_0
    //   28: aconst_null
    //   29: invokevirtual 38	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   32: astore 7
    //   34: aload 7
    //   36: areturn
    //   37: astore_2
    //   38: new 40	java/lang/NoSuchMethodError
    //   41: dup
    //   42: aload_2
    //   43: invokevirtual 44	java/lang/NoSuchMethodException:getMessage	()Ljava/lang/String;
    //   46: invokespecial 47	java/lang/NoSuchMethodError:<init>	(Ljava/lang/String;)V
    //   49: athrow
    //   50: astore 5
    //   52: aload 5
    //   54: invokevirtual 51	java/lang/reflect/InvocationTargetException:getCause	()Ljava/lang/Throwable;
    //   57: astore 6
    //   59: aload 6
    //   61: instanceof 13
    //   64: ifeq +9 -> 73
    //   67: aload 6
    //   69: checkcast 13	java/lang/CloneNotSupportedException
    //   72: athrow
    //   73: new 53	java/lang/Error
    //   76: dup
    //   77: ldc 55
    //   79: aload 6
    //   81: invokespecial 58	java/lang/Error:<init>	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   84: athrow
    //   85: astore 4
    //   87: new 60	java/lang/IllegalAccessError
    //   90: dup
    //   91: aload 4
    //   93: invokevirtual 61	java/lang/IllegalAccessException:getMessage	()Ljava/lang/String;
    //   96: invokespecial 62	java/lang/IllegalAccessError:<init>	(Ljava/lang/String;)V
    //   99: athrow
    //   100: new 13	java/lang/CloneNotSupportedException
    //   103: dup
    //   104: invokespecial 63	java/lang/CloneNotSupportedException:<init>	()V
    //   107: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   18	26	37	java/lang/NoSuchMethodException
    //   26	34	50	java/lang/reflect/InvocationTargetException
    //   26	34	85	java/lang/IllegalAccessException
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.client.utils.CloneUtils
 * JD-Core Version:    0.6.2
 */