package com.eis.apiusers.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "providers")
@Getter @Setter
public class Provider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_provider", nullable = false, unique = true)
    private Long idProvider;
    private String name;
    private String email;
    private String password;
    @Column(name = "api_key")
    private String apiKey;

    @OneToMany(mappedBy = "provider")
    private List<Contract> providers;
}
