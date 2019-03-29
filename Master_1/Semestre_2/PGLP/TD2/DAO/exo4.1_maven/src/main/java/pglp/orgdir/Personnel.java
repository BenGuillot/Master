package pglp.orgdir;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * La classe <code>Personnel</code> représente un membre d'une organisation.
 *
 * @author hal
 * @version 2019
 */
public class Personnel extends OrganizationElement {
  public static LocalDate DEFAULT_DATE = LocalDate.of(2000, 1, 1);

  private String firstname;
  private String lastname;
  private List<String> functions;
  private LocalDate dateOfBirth;

  private Personnel(Builder builder) {
    firstname = builder.firstname;
    lastname = builder.lastname;
    functions = builder.functions;
    dateOfBirth = builder.dateOfBirth;
  }

  public String getFirstname() {
    return firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public List<String> getFunctions() {
    return functions;
  }

  public LocalDate getDoB() {
    return dateOfBirth;
  }

  @Override
  public String toString() {
    return "Personnel{" +
            "lastname='" + lastname + '\'' +
            ", firstname='" + firstname + '\'' +
            '}';
  }

  @Override
  protected void addSubElementsDFS(List<OrganizationElement> list) {}

  @Override
  protected void addSubElementsBFS(List<OrganizationElement> list) {}

  @Override
  public String getDescription() {
    return firstname + " " + lastname;
  }

  public static class Builder {
    private String lastname;
    private String firstname;

    private List<String> functions;
    private LocalDate dateOfBirth;

    public Builder(String firstname, String lastname) {
      this.firstname = firstname;
      this.lastname = lastname;
      functions = new ArrayList();
      dateOfBirth = DEFAULT_DATE;
    }

    public Personnel build() {
      return new Personnel(this);
    }

    public Builder addFunction(String function) {
      functions.add(function);
      return this;
    }

    public Builder dateOfBirth(int year, int month, int dayOfMonth) {
      dateOfBirth = LocalDate.of(year, month, dayOfMonth);
      return this;
    }
  }
}
