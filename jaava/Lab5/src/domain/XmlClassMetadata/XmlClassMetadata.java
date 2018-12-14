package domain.XmlClassMetadata;

import domain.XmlClassMetadata.Attribute.XmlAttributeInfo;
import domain.XmlClassMetadata.Tag.XmlTagInfo;

import java.util.ArrayList;
import java.util.List;

public class XmlClassMetadata {

  private String className;
  private Class objectClass;
  private String xmlRootName;

  private List<XmlTagInfo> tags;
  private List<XmlAttributeInfo> attributes;

  XmlClassMetadata() {
    tags = new ArrayList<>();
    attributes = new ArrayList<>();
  }

  public String getClassName() {
    return className;
  }

  public Class getObjectClass() {
    return objectClass;
  }

  public String getXmlRootName() {
    return xmlRootName;
  }

  public List<XmlTagInfo> getTags() {
    return tags;
  }

  public List<XmlAttributeInfo> getAttributes() {
    return attributes;
  }

  public void setClassName(String className) {
    this.className = className;
  }

  public void setObjectClass(Class objectClass) {
    this.objectClass = objectClass;
  }

  public void setXmlRootName(String xmlRootName) {
    this.xmlRootName = xmlRootName;
  }

  public void appendTag(XmlTagInfo tag) throws IllegalArgumentException {
    for (XmlTagInfo existingTag : tags) {
      if (existingTag.getTagName().equals(tag.getTagName()))
        throw new IllegalArgumentException(
              "Duplicating tag name \"" + tag.getTagName() + "\""
              );
    }

    tags.add(tag);
  }

  public void appendAttribute(XmlAttributeInfo attribute) throws IllegalArgumentException {
    for (XmlAttributeInfo existingAttr : attributes) {
      if (existingAttr.getTagName().equals(attribute.getTagName())
        && existingAttr.getAttributeName().equals(attribute.getAttributeName()))
        throw new IllegalArgumentException(
                "Duplicating attribute name \"" + attribute.getAttributeName() + "\" in \"" + attribute.getTagName() + "\" tag"
                );
    }

    attributes.add(attribute);
  }
}
