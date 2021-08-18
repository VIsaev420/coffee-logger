package ru.lanit.bpm.coffeelogger.bot.longpolling.repository.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Log {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;
    @CreationTimestamp
    private LocalDateTime createDateTime;
    @Basic
    private String comment;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "operator_id", referencedColumnName = "id")
    private Operator customer;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "coffee_id", referencedColumnName = "id")
    private Coffee coffeeId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "custom_id", referencedColumnName = "id")
    private Custom customId;
}
