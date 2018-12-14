package domain.XmlClassMetadata;

import java.lang.reflect.Field;


public class AvailableField {

  protected final Field field;

  public AvailableField(Field field) {
    this.field = field;
    this.field.setAccessible(true);
  }

  public String getValue(Object object) {
    try {
      Object value = field.get(object);
      if (value == null) return "null";
      return value.toString();
    } catch (IllegalAccessException error) { }
    return null;
  }

  public String getNormalizedReturnName(String name) {
    if (name.isEmpty()) {
      name = this.field.getName();
    }
    return name;
  }
}
