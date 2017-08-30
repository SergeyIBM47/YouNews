package com.template.security;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.template.domain.model.enums.OnlineStatus;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Created by svolokh@rightandabove.com at ${DATE}
 * @author Last modified by $Author: $author $ <br>
 * @author Last modified on $Date: $date $ at revision $Revision: $revision $ <br>
 */
public class UserDetails implements org.springframework.security.core.userdetails.UserDetails {

    private Long id;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private String photoUrl;
    private OnlineStatus onlineStatus;


    public static Builder getBuilder() {
        return new Builder();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public OnlineStatus getOnlineStatus() {
        return onlineStatus;
    }

    public void setOnlineStatus(OnlineStatus onlineStatus) {
        this.onlineStatus = onlineStatus;
    }
//
//    @Override
//    public String toString() {
//        return new (this)
//                .append("id", id)
//                .append("homeId", homeId)
//                .append("username", getUsername())
//                .append("firstName", firstName)
//                .append("lastName", lastName)
//                .append("role", userRoles)
//                .append("socialSignInProvider", socialSignInProvider)
//                .toString();
//    }

    public static class Builder {
        private Long id;
        private String firstName;
        private String lastName;
//        private String password;
        private String email;
        private String photoUrl;
        private OnlineStatus onlineStatus;

        @JsonIgnore
        private String password;

        private byte[] photo;

        private Set<GrantedAuthority> authorities;

        public Builder() {
            this.authorities = new HashSet<>();
        }

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder password(String password) {
            if (password == null) {
                password = "SocialUser";
            }

            this.password = password;
            return this;
        }

        public Builder photo(byte[] photo) {
            this.photo = photo;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

//
//        public Builder role(UserRoles userRoles) {
//            this.userRoles = userRoles;
//
//            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(userRoles.toString());
//            this.authorities.add(authority);
//
//            return this;
//        }

        public Builder userStatus(OnlineStatus onlineStatus) {
            this.onlineStatus = onlineStatus;
            return this;
        }

        public UserDetails build() {
            UserDetails user = new UserDetails();
            user.id = id;
            user.email = email;
            user.password = password;
            user.firstName = firstName;
            user.lastName = lastName;
            user.photoUrl = photoUrl;
            user.onlineStatus = onlineStatus;
            return user;
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
