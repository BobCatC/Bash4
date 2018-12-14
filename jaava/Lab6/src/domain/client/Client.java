package domain.client;

public class Client {

  private final String firstName;
  private final String lastName;
  private final String address;
  private final String passportId;

  private Client(String firstName, String lastName, String address, String passportId) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.address = address;
    this.passportId = passportId;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getAddress() {
    return address;
  }

  public String getPassportId() {
    return passportId;
  }

  public boolean addressNotSet() {
    return address == null || address.isEmpty();
  }

  public boolean passportIdNotSet() {
    return passportId == null || passportId.isEmpty();
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {
    private String firstName;
    private String lastName;
    private String address;
    private String passportId;

    private Builder() { }

    public Builder setFirstName(String firstName) {
      this.firstName = firstName;
      return this;
    }

    public Builder setLastName(String lastName) {
      this.lastName = lastName;
      return this;
    }

    public Builder setAddress(String address) {
      this.address = address;
      return this;
    }

    public Builder setPassportId(String passportId) {
      this.passportId = passportId;
      return this;
    }

    public Client build() throws IllegalArgumentException {
      if (firstName == null || firstName.isEmpty() || lastName == null || lastName.isEmpty()) {
        throw new IllegalArgumentException();
      }
      return new Client(firstName, lastName, address, passportId);
    }
  }


  public boolean equals(Client other) {
    return firstName.equals(other.firstName) && lastName.equals(other.lastName);
  }
}
