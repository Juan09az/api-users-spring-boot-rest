package com.eis.apiusers.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@Getter @Setter
public class UserEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user", nullable = false, unique = true)
    private Long idUser;
    private String name;
    private String lastname;
    @Column(length = 80, nullable = false, unique = true)
    private String email;
    private String phone;
    private String password;
    private boolean enabled=true;
    @Column(name = "id_state")
    private Long idState;

    @ManyToOne
    @JoinColumn(name = "id_state", insertable = false, updatable = false)
    private State state;

    @OneToMany(mappedBy = "userEntity")
    private List<Contract> contracts;

    private String tokenPassword;

    public UserEntity(String name, String lastname, String email, String phone, String password, boolean enabled,Long idState) {
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.enabled = enabled;
        this.idState=idState;
    }

    public UserEntity(Long idUser, String name, String lastname, String email, String phone, boolean enabled, Long idState) {
        this.idUser = idUser;
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.phone = phone;
        this.enabled = enabled;
        this.idState = idState;
    }

    public UserEntity() {}

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked() {
        return true;
    }

    public boolean isCredentialsNonExpired() {
        return true;
    }
}
