package org.exam.backend.entities;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="USERS")
public class User {

    @Id
    @NotBlank
    @Size(min=6, max=250)
    @Column(unique = true)
    @Email
    private String email;

    @NotBlank
    @Size(max = 125)
    private String firstname;

    @Size(max = 50)
    private String middleName;

    @NotBlank
    @Size(max = 125)
    private String surename;

    @NotBlank
    @Size(max = 250)
    private String address;

    @NotBlank
    @Size(max = 250)
    private String postalCode;

    @NotBlank
    @Size(max = 250)
    private String password;

    @Max(1000)
    private Integer numberOfVotes;

    @NotNull
    private Boolean enabled;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> roles;



    public User() {
    }

    public String getSurename() {
        return surename;
    }

    public void setSurename(String surename) {
        this.surename = surename;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String username) {
        this.firstname = username;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email_username) {
        this.email = email_username;
    }

    public String getSurname() {
        return surename;
    }

    public void setSurname(String surname) {
        this.surename = surname;
    }

    public String getAddress() {
        return address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public void setAddress(String adress) {
        this.address = adress;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public Integer getNumberOfVotes() {
        return numberOfVotes;
    }

    public void setNumberOfVotes(Integer numberOfVotes) {
        this.numberOfVotes = numberOfVotes;
    }

}
