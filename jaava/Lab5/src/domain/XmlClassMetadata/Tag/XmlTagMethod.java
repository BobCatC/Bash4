package domain.XmlClassMetadata.Tag;

import domain.XmlAnnotations.XmlTag;
import domain.XmlClassMetadata.AvailableMethod;

import java.lang.reflect.Method;


class XmlTagMethod extends AvailableMethod implements XmlTagInfo {

  private final String tagName;

  XmlTagMethod(Method method) throws IllegalArgumentException {
    super(method);
    XmlTag tagAnnotation = method.getAnnotation(XmlTag.class);
    if (tagAnnotation == null) throw new IllegalArgumentException("Method " + method.getName() + " has not @XmlTag annotation");

    tagName = getNormalizedReturnName(tagAnnotation.name());

    if (!isAvailable()) throw new IllegalArgumentException("@XmlTag \"" + tagName + "\" (method \"" + method.getName() + "\") " + reasonNonAvailable());
  }

  @Override
  public String getTagName() {
    return tagName;
  }

  static boolean isTag(Method method) {
    return method.isAnnotationPresent(XmlTag.class);
  }
}
