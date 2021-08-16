package ru.lanit.bpm.coffeelogger.bot.longpolling.coffee.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class CoffeeLog {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;
    @CreationTimestamp
    private LocalDateTime createDateTime;
    @Basic
    private int price;
    @Basic
    private String coffeeType;
    @Basic
    private String customer;
    @Basic
    private String comment;
    @Basic
    private String cafe;
}
