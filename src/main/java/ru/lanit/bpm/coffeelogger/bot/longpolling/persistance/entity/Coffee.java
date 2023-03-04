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
    private Float size;
    @Basic
    private String cafe;
    @Basic
    private Integer price;
    @CreationTimestamp
    private LocalDateTime createDateTime;
    @Basic
    private Long userId;
}
