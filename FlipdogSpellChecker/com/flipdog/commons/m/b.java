package com.flipdog.commons.m;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

public class b
{
  private static a a;

  public static ByteArrayOutputStream a(Document paramDocument)
    throws IllegalArgumentException, IllegalStateException, IOException
  {
    return c(paramDocument);
  }

  public static Document a()
    throws FactoryConfigurationError, ParserConfigurationException
  {
    return DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
  }

  public static void a(Document paramDocument, Element paramElement, String paramString)
  {
    if (paramString == null)
      paramString = "";
    paramElement.appendChild(paramDocument.createTextNode(paramString));
  }

  private static void a(XmlSerializer paramXmlSerializer, Element paramElement)
    throws IllegalArgumentException, IllegalStateException, IOException
  {
    NodeList localNodeList = paramElement.getChildNodes();
    for (int i = 0; ; i++)
    {
      if (i >= localNodeList.getLength())
        return;
      a(paramXmlSerializer, localNodeList.item(i));
    }
  }

  private static void a(XmlSerializer paramXmlSerializer, Node paramNode)
    throws IllegalArgumentException, IllegalStateException, IOException
  {
    if (paramNode.getNodeType() == 1)
    {
      paramXmlSerializer.startTag(paramNode.getNamespaceURI(), paramNode.getNodeName());
      a(paramXmlSerializer, (Element)paramNode);
      paramXmlSerializer.endTag(paramNode.getNamespaceURI(), paramNode.getNodeName());
    }
    do
    {
      return;
      if (paramNode.getNodeType() == 2)
      {
        paramXmlSerializer.attribute(paramNode.getNamespaceURI(), paramNode.getNodeName(), paramNode.getNodeValue());
        return;
      }
      if (paramNode.getNodeType() == 4)
      {
        paramXmlSerializer.cdsect(paramNode.getNodeValue());
        return;
      }
    }
    while (paramNode.getNodeType() != 3);
    paramXmlSerializer.text(paramNode.getNodeValue());
  }

  public static String b(Document paramDocument)
    throws IllegalArgumentException, IllegalStateException, IOException
  {
    try
    {
      XmlSerializer localXmlSerializer2 = c();
      localXmlSerializer1 = localXmlSerializer2;
      StringWriter localStringWriter = new StringWriter();
      localXmlSerializer1.setOutput(localStringWriter);
      localXmlSerializer1.startDocument("UTF-8", Boolean.valueOf(true));
      a(localXmlSerializer1, paramDocument.getDocumentElement());
      localXmlSerializer1.endDocument();
      return localStringWriter.toString();
    }
    catch (XmlPullParserException localXmlPullParserException)
    {
      while (true)
      {
        localXmlPullParserException.printStackTrace();
        XmlSerializer localXmlSerializer1 = null;
      }
    }
  }

  public static Document b()
    throws FactoryConfigurationError, ParserConfigurationException
  {
    DocumentBuilderFactory localDocumentBuilderFactory = DocumentBuilderFactory.newInstance();
    localDocumentBuilderFactory.setNamespaceAware(true);
    return localDocumentBuilderFactory.newDocumentBuilder().newDocument();
  }

  private static ByteArrayOutputStream c(Document paramDocument)
    throws IllegalArgumentException, IllegalStateException, IOException
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    localByteArrayOutputStream.write(b(paramDocument).getBytes());
    return localByteArrayOutputStream;
  }

  private static XmlSerializer c()
    throws XmlPullParserException
  {
    if (a == null)
      a = (a)com.flipdog.commons.i.b.a(a.class);
    return a.a();
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.commons.m.b
 * JD-Core Version:    0.6.2
 */