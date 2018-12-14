package domain.XmlClassMetadata.Tag;

import domain.XmlAnnotations.XmlTag;
import domain.XmlClassMetadata.AvailableField;

import java.lang.reflect.Field;


class XmlTagField extends AvailableField implements XmlTagInfo {

  private final String tagName;

  XmlTagField(Field field) throws IllegalArgumentException {
    super(field);
    XmlTag tagAnnotation = field.getAnnotation(XmlTag.class);
    if (tagAnnotation == null) throw new IllegalArgumentException("Field " + field.getName() + " has not @XmlTag annotation");

    tagName = getNormalizedReturnName(tagAnnotation.name());
  }

  @Override
  public String getTagName() {
    return tagName;
  }

  static boolean isTag(Field field) {
    return field.isAnnotationPresent(XmlTag.class);
  }
}
