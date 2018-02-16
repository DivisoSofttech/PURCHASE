package com.diviso.purchase.service.model;


import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Contact entity.
 */
public class ContactModel implements Serializable {

    private Long id;

    private String mailId;

    private Long phoneNumber1;

    private Long phoneNumber2;

    private String companyName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMailId() {
        return mailId;
    }

    public void setMailId(String mailId) {
        this.mailId = mailId;
    }

    public Long getPhoneNumber1() {
        return phoneNumber1;
    }

    public void setPhoneNumber1(Long phoneNumber1) {
        this.phoneNumber1 = phoneNumber1;
    }

    public Long getPhoneNumber2() {
        return phoneNumber2;
    }

    public void setPhoneNumber2(Long phoneNumber2) {
        this.phoneNumber2 = phoneNumber2;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ContactModel contactDTO = (ContactModel) o;
        if(contactDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), contactDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ContactDTO{" +
            "id=" + getId() +
            ", mailId='" + getMailId() + "'" +
            ", phoneNumber1=" + getPhoneNumber1() +
            ", phoneNumber2=" + getPhoneNumber2() +
            ", companyName='" + getCompanyName() + "'" +
            "}";
    }
}
