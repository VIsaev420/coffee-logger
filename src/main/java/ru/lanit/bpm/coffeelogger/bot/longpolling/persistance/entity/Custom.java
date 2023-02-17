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
public class Custom {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;
    @Basic
    private String type;
    @Basic
    private int price;
    @CreationTimestamp
    private LocalDateTime createDateTime;
    @ManyToOne
    @JoinColumn(name = "operator_id", referencedColumnName = "id", nullable = false)
    private Operator operatorId;
    @OneToOne
    @JoinColumn(name = "coffee_order_id", referencedColumnName = "id")
    private Coffee coffeeOrder;
}
