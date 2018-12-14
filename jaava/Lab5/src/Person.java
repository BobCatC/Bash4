import domain.XmlAnnotations.XmlAttribute;
import domain.XmlAnnotations.XmlObject;
import domain.XmlAnnotations.XmlTag;

@XmlObject(name = "root")
public class Person {

  @XmlTag(name = "public_int")
  public int publicIntTag = 1;

  @XmlTag
  private double privateDoubleTag = 11.0;

  @XmlAttribute
  public String publicStringAttr = "attr";

  @XmlAttribute(name = "private_float_attr", tag = "public_int")
  private float privateFloatAttr = (float) 12.2;



  @XmlTag
  private int getSome() {
    return 123;
  }

  @XmlAttribute(name = "function", tag = "root")
  protected Integer function1() {
    return 23;
  }
}
