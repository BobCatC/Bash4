import domain.XmlSerializer;
import org.dom4j.io.OutputFormat;

import java.io.IOException;

public class Main {

  public static void main(String[] args) {

    try {
      XmlSerializer serializer = new XmlSerializer("output.xml", OutputFormat.createPrettyPrint());
      serializer.writeObject(new Person());
    } catch (IllegalArgumentException | IOException error) {
      System.out.println(error);
    }
  }
}
