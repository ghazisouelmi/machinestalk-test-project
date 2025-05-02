package org.machinestalk.api.dto;
import java.util.List;

import org.machinestalk.domain.Address;
import org.machinestalk.domain.User;

public class UserDto {

  /** User id */
  private Long id;

  private UserInfos userInfos;

  public UserDto() {
  }

  public UserDto(final Long id, final UserInfos userInfos) {
    this.id = id;
    this.userInfos = userInfos;
  }

  public Long getId() {
    return id;
  }

  public void setId(final Long id) {
    this.id = id;
  }

  public UserInfos getUserInfos() {
    return userInfos;
  }

  public void setUserInfos(final UserInfos userInfos) {
    this.userInfos = userInfos;
  }

  public static class UserInfos {

    private String firstName;

    private String lastName;

    private String department;

    /**
     * List of all known user formatted addresses.<br>
     * Example of formatted address: "23 rue de voltaire, 75015 PARIS, FRANCE"
     */
    private List<String> adresses;

    public UserInfos() {}

    public UserInfos(
            final String firstName,
            final String lastName,
            final String department,
            final List<String> adresses) {
      this.firstName = firstName;
      this.lastName = lastName;
      this.department = department;
      this.adresses = adresses;
    }
  }
  

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    User user = (User) o;

    if (id != null ? !id.equals(user.getId()) : user.getId() != null) return false;
    if (this.userInfos.firstName != null ? !this.userInfos.firstName.equals(user.getFirstName()) : user.getFirstName() != null) return false;
    if (this.userInfos.lastName != null ? !this.userInfos.lastName.equals(user.getLastName()) : user.getLastName() != null) return false;
    if (this.userInfos.department != null ? !this.userInfos.department.equals(user.getDepartment()) : user.getDepartment() != null) return false;
    return this.userInfos.adresses != null ? this.userInfos.adresses.equals(user.getAddresses().stream().map(Address::toString)) : user.getAddresses() == null;
  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (this.userInfos.firstName != null ? this.userInfos.firstName.hashCode() : 0);
    result = 31 * result + (this.userInfos.lastName != null ? this.userInfos.lastName.hashCode() : 0);
    result = 31 * result + (this.userInfos.department != null ? this.userInfos.department.hashCode() : 0);
    result = 31 * result + (this.userInfos.adresses != null ? this.userInfos.adresses.hashCode() : 0);
    return result;
  }
}