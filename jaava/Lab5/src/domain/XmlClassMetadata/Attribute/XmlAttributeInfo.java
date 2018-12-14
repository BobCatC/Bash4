package domain.XmlClassMetadata.Attribute;

import java.lang.reflect.Field;
import java.lang.reflect.Method;



public interface XmlAttributeInfo {

  String getValue(Object object);

  String getTagName();

  String getAttributeName();

  static boolean fieldIsAttribute(Field field) {
    return XmlAttributeField.isAttribute(field);
  }

  static boolean methodIsAttribute(Method method) {
    return XmlAttributeMethod.isAttribute(method);
  }

  static XmlAttributeInfo create(Field field) throws IllegalArgumentException {
    return new XmlAttributeField(field);
  }

  static XmlAttributeInfo create(Method method) throws IllegalArgumentException {
    return new XmlAttributeMethod(method);
  }
}
