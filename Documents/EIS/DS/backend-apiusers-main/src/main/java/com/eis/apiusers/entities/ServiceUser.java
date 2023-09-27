package com.eis.apiusers.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "services")
@Getter @Setter
public class ServiceUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_service", nullable = false, unique = true)
    private Long idService;
    @Column(name = "name_service")
    private String nameService;
    private String frequency;

    @OneToMany(mappedBy = "service")
    private List<Contract> contracts;
}
