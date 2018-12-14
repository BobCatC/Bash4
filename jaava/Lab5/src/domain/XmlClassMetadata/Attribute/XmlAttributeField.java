package domain.XmlClassMetadata.Attribute;

import domain.XmlAnnotations.XmlAttribute;
import domain.XmlClassMetadata.AvailableField;

import java.lang.reflect.Field;


class XmlAttributeField extends AvailableField implements XmlAttributeInfo {

  private final String tagName;
  private final String attributeName;

  XmlAttributeField(Field field) throws IllegalArgumentException {
    super(field);
    XmlAttribute attributeAnnotation = field.getAnnotation(XmlAttribute.class);
    if (attributeAnnotation == null) throw new IllegalArgumentException("Field " + field.getName() + " has not @XmlAttribute annotation");

    tagName = attributeAnnotation.tag();
    attributeName = getNormalizedReturnName(attributeAnnotation.name());
  }

  @Override
  public String getTagName() {
    return tagName;
  }

  @Override
  public String getAttributeName() {
    return attributeName;
  }

  static boolean isAttribute(Field field) {
    return field.isAnnotationPresent(XmlAttribute.class);
  }
}
