package pl.javarun.mywebshop.model;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;


import java.sql.Timestamp;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

/**
 * @author: Maciej Kryger  [https://github.com/maciejkryger]
 * @date : 15.02.2020 17:38
 * *
 * @className: User
 * *
 * *
 ******************************************************/
@Entity
public class User implements UserDetails {

    @Id
//    @GeneratedValue(generator = "userSeq")
//    @SequenceGenerator(name = "userSeq", sequenceName = "user_seq", allocationSize = 1)
    private int id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    @Email
    private String email;

    @Nullable
    private String phone;

    @ManyToOne(targetEntity = Role.class)
    private Role role;
    @Nullable
//    @DateTimeFormat(pattern = "yyyy-MM-dd mm:ss:")
    private Timestamp creationDate;
    private boolean active;
    @Nullable
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date activationDate;
    private boolean deleted;
    @Nullable
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date deletingDate;

    private boolean rulesAccepted;
    private boolean marketingAgreed;
    private Timestamp updateAgreedDate;

    private String token;

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(role);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    @Nullable
    public String getPhone() {
        return phone;
    }

    public void setPhone(@Nullable String phone) {
        this.phone = phone;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Nullable
    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(@Nullable Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Nullable
    public Date getActivationDate() {
        return activationDate;
    }

    public void setActivationDate(@Nullable Date activationDate) {
        this.activationDate = activationDate;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    @Nullable
    public Date getDeletingDate() {
        return deletingDate;
    }

    public void setDeletingDate(@Nullable Date deletingDate) {
        this.deletingDate = deletingDate;
    }

    public boolean isRulesAccepted() {
        return rulesAccepted;
    }

    public void setRulesAccepted(boolean rulesAccepted) {
        this.rulesAccepted = rulesAccepted;
    }

    public boolean isMarketingAgreed() {
        return marketingAgreed;
    }

    public void setMarketingAgreed(boolean marketingAgreed) {
        this.marketingAgreed = marketingAgreed;
    }


    public Timestamp getUpdateAgreedDate() {
        return updateAgreedDate;
    }

    public void setUpdateAgreedDate(Timestamp updateAgreedDate) {
        this.updateAgreedDate = updateAgreedDate;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
