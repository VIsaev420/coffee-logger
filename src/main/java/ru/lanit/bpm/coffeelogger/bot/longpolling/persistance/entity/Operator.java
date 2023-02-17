package ru.lanit.bpm.coffeelogger.bot.longpolling.persistance.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Operator {
    @Id
    private String id;
    @Basic
    private String name;
    @OneToMany(mappedBy = "operator", targetEntity = Coffee.class, fetch = FetchType.EAGER)
    private Set<Coffee> coffeeOrderSet;
}
