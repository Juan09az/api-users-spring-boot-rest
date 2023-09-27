package com.eis.apiusers.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "contracts")
@Getter @Setter
@RequiredArgsConstructor
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_contract", nullable = false, unique = true)
    private Long idContract;
    private String description;
    private BigDecimal value;
    @Column(name = "id_user")
    private Long idUser;
    @Column(name = "id_service")
    private Long idService;
    @Column(name = "subscription_code")
    private String subscriptionCode;
    @Column(name = "due_date")
    private LocalDateTime dueDate;
    @Column(name = "notification_setting")
    private Integer notificationSetting;
    @Column(name = "id_provider")
    @JoinColumn(nullable = true)
    private Long idProvider;
    @Column(name = "paid", columnDefinition = "boolean default false")
    private boolean paid=false;
    @Column(name = "type")
    private String type;

    @OneToMany(mappedBy = "contract")
    private List<Payment> payments;

    @ManyToOne
    @JoinColumn(name = "id_user", insertable = false, updatable = false)
    private UserEntity userEntity;

    @ManyToOne
    @JoinColumn(name = "id_service", insertable = false, updatable = false)
    private ServiceUser service;

    @ManyToOne
    @JoinColumn(name = "id_provider", insertable = false, updatable = false)
    private Provider provider;

    public Contract(Long idContract, Long idProvider, BigDecimal value, LocalDateTime dueDate, Integer notificationSetting) {
        this.idContract = idContract;
        this.value = value;
        this.dueDate = dueDate;
        this.notificationSetting = notificationSetting;
        this.idProvider = idProvider;
    }
}
