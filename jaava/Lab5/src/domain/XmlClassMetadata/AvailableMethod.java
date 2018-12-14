package domain.XmlClassMetadata;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class AvailableMethod {

  protected final Method method;


  public AvailableMethod(Method method) {
    this.method = method;
    this.method.setAccessible(true);
  }

  public String getValue(Object object) {
    try {
      Object value = method.invoke(object);
      if (value == null) return "null";
      return value.toString();
    } catch (IllegalAccessException | InvocationTargetException error) { }
    return null;
  }

  public boolean isAvailable() {
    return !nonZeroParamCount() && !voidReturn();
  }

  public String reasonNonAvailable() {
    if (nonZeroParamCount()) return "can not have parameters";
    if (voidReturn()) return "can not return void";
    return null;
  }

  public String getNormalizedReturnName(String name) {
    if (name.isEmpty()) {
      name = method.getName();
      if (name.length() > 3 && name.substring(0, 3).equals("get")) {
        name = name.substring(3);
        name = name.substring(0, 1).toLowerCase() + name.substring(1);
      }
    }
    return name;
  }

  private boolean nonZeroParamCount() {
    return method.getParameterCount() > 0;
  }

  private boolean voidReturn() {
    Class returnType = method.getReturnType();
    return (returnType == Void.class || returnType == void.class);
  }
}
