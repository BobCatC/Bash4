package domain.XmlClassMetadata.Attribute;

import domain.XmlAnnotations.XmlAttribute;
import domain.XmlClassMetadata.AvailableMethod;

import java.lang.reflect.Method;


class XmlAttributeMethod extends AvailableMethod implements XmlAttributeInfo {

  private final String tagName;
  private final String attributeName;

  XmlAttributeMethod(Method method) throws IllegalArgumentException {
    super(method);

    XmlAttribute attributeAnnotation = method.getAnnotation(XmlAttribute.class);
    if (attributeAnnotation == null) throw new IllegalArgumentException("Method " + method.getName() + " has not @XmlAttribute annotation");

    attributeName = getNormalizedReturnName(attributeAnnotation.name());
    tagName = attributeAnnotation.tag();

    if (!isAvailable()) throw new IllegalArgumentException("@XmlAttribute \"" + tagName + "::" + attributeName + "\" (method \"" + method.getName() + "\") " + reasonNonAvailable());
  }

  @Override
  public String getAttributeName() {
    return attributeName;
  }

  @Override
  public String getTagName() {
    return tagName;
  }

  static boolean isAttribute(Method method) {
    return method.isAnnotationPresent(XmlAttribute.class);
  }
}
