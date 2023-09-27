package com.eis.apiusers.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
@Entity
@Table(name = "states")
@Getter @Setter
public class State {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_state", nullable = false, unique = true)
    private Long idState;
    @Column(name = "name_state", nullable = false, unique = true)
    private String nameState;

    @OneToMany(mappedBy = "state")
    private List<UserEntity> users;

}
