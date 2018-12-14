package domain;

import java.io.*;
import java.util.List;

import domain.XmlClassMetadata.Attribute.XmlAttributeInfo;
import domain.XmlClassMetadata.Tag.XmlTagInfo;
import domain.XmlClassMetadata.XmlClassMetadata;
import domain.XmlClassMetadata.XmlMetadataParser;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

public class XmlSerializer {

  private final XMLWriter writer;

  public XmlSerializer(String filePath, OutputFormat format) throws IOException {
    this(new FileWriter(filePath), format);
  }

  public XmlSerializer(FileWriter writer, OutputFormat format) {
    this.writer = new XMLWriter(writer, format);
  }

  public void writeObject(Object object) throws IllegalArgumentException, IOException {

    XmlClassMetadata metadata = (new XmlMetadataParser(object)).getMetadata();
    serialize(metadata, object);
    writer.flush();
  }

  public void close() throws IOException {
    writer.close();
  }


  private void serialize(XmlClassMetadata metadata, Object object) throws IllegalArgumentException, IOException {
    Document document = DocumentHelper.createDocument();
    Element rootElement = document.addElement(metadata.getXmlRootName());

    addTags(rootElement, metadata.getTags(), object);
    addAttributes(rootElement, metadata.getAttributes(), object);

    writer.write(document);
  }

  private void addTags(Element rootElement, List<XmlTagInfo> tags, Object object) {
    for (XmlTagInfo tag : tags) {
      Element newElement = rootElement.addElement(tag.getTagName());
      newElement.addText(tag.getValue(object));
    }
  }

  private void addAttributes(Element rootElement, List<XmlAttributeInfo> attributes, Object object) throws IllegalArgumentException {
    List<Element> elements = rootElement.elements();

    for (XmlAttributeInfo attributeInfo : attributes) {
      String tagName = attributeInfo.getTagName();
      String attrName = attributeInfo.getAttributeName();

      if (tagName.isEmpty() || tagName.equals(rootElement.getName())) {
        rootElement.addAttribute(attributeInfo.getAttributeName(), attributeInfo.getValue(object));
        continue;
      }

      Element element = findEl(elements, tagName);
      if (element == null) throw new IllegalArgumentException(
              "Missing tag with name \"" + tagName + "\" at attribute \"" + attrName + "\""
      );

      element.addAttribute(attrName, attributeInfo.getValue(object));
    }
  }

  private Element findEl(List<Element> elements, String name) {
    for (Element el : elements) {
      if (el.getName().equals(name)) return el;
    }
    return null;
  }
}
