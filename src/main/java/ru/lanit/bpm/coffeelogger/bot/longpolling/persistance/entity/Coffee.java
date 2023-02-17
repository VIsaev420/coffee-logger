package ru.lanit.bpm.coffeelogger.bot.longpolling.persistance.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Coffee {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;
    @Basic
    private String type;
    @Basic
    private float size;
    @Basic
    private String cafe;
    @Basic
    private int price;
    @CreationTimestamp
    private LocalDateTime createDateTime;
    @ManyToOne
    @JoinColumn(name = "operator_id", referencedColumnName = "id", nullable = false)
    private Operator operator;
    @OneToOne
    @JoinColumn(name = "custom_order_id", referencedColumnName = "id")
    private Custom customOrder;
}
