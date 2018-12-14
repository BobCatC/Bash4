package domain.XmlClassMetadata;

import domain.XmlAnnotations.XmlObject;
import domain.XmlClassMetadata.Attribute.XmlAttributeInfo;
import domain.XmlClassMetadata.Tag.XmlTagInfo;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class XmlMetadataParser {

  private final XmlClassMetadata metadata;

  public XmlMetadataParser(Object object) throws IllegalArgumentException {
    metadata = parse(object);
  }

  public XmlClassMetadata getMetadata() {
    return metadata;
  }


  private XmlClassMetadata parse(Object object) throws IllegalArgumentException {
    XmlClassMetadata metadata = new XmlClassMetadata();
    Class objectClass = object.getClass();

    parseObjectLayer(objectClass, metadata);
    parseObjectFields(objectClass, metadata);
    parseObjectMethods(objectClass, metadata);

    return metadata;
  }

  private void parseObjectLayer(Class objectClass, XmlClassMetadata metadata) throws IllegalArgumentException {

    XmlObject xmlObjectAnnotation = (XmlObject) objectClass.getDeclaredAnnotation(XmlObject.class);
    if (xmlObjectAnnotation == null) throw new IllegalArgumentException("Object \"" + objectClass.getName() + "\" has not @XmlObject annotation");
    String rootName = xmlObjectAnnotation.name();
    if (rootName.isEmpty()) {
      rootName = objectClass.getName();
    }

    metadata.setObjectClass(objectClass);
    metadata.setClassName(objectClass.getName());
    metadata.setXmlRootName(rootName);
  }

  private void parseObjectFields(Class objectClass, XmlClassMetadata metadata) throws IllegalArgumentException {
    Field[] fields = objectClass.getDeclaredFields();

    for (Field field : fields) {
      boolean isTag = XmlTagInfo.fieldIsXmlTag(field);
      boolean isAttribute = XmlAttributeInfo.fieldIsAttribute(field);
      if (isTag && isAttribute) throw new IllegalArgumentException("Field \"" + field.getName() + "\" is marked as tag and as attribute");
      if (!isTag && !isAttribute) continue;

      if (isTag) {
        XmlTagInfo tag = XmlTagInfo.create(field);
        metadata.appendTag(tag);
      } else {
        XmlAttributeInfo attribute = XmlAttributeInfo.create(field);
        metadata.appendAttribute(attribute);
      }
    }
  }

  private void parseObjectMethods(Class objectClass, XmlClassMetadata metadata) throws IllegalArgumentException {
    Method[] methods = objectClass.getDeclaredMethods();

    for (Method method : methods) {
      boolean isTag = XmlTagInfo.methodIsXmlTag(method);
      boolean isAttribute = XmlAttributeInfo.methodIsAttribute(method);
      if (isTag && isAttribute) throw new IllegalArgumentException("Method \"" + method.getName() + "\" is marked as tag and as attribute");
      if (!isTag && !isAttribute) continue;

      if (isTag) {
        XmlTagInfo tag = XmlTagInfo.create(method);
        metadata.appendTag(tag);
      } else {
        XmlAttributeInfo attribute = XmlAttributeInfo.create(method);
        metadata.appendAttribute(attribute);
      }
    }
  }
}
