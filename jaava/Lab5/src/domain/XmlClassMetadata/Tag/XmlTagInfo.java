package domain.XmlClassMetadata.Tag;

import java.lang.reflect.Field;
import java.lang.reflect.Method;



public interface XmlTagInfo {

  String getValue(Object object);

  String getTagName();

  static boolean fieldIsXmlTag(Field field) {
    return XmlTagField.isTag(field);
  }

  static boolean methodIsXmlTag(Method method) {
    return XmlTagMethod.isTag(method);
  }

  static XmlTagInfo create(Field field) throws IllegalArgumentException {
    return new XmlTagField(field);
  }

  static XmlTagInfo create(Method method) throws IllegalArgumentException {
    return new XmlTagMethod(method);
  }
}
