package ru.lanit.bpm.coffeelogger.bot.longpolling.repository.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Operator {
    @Id
    private String id;
    @Basic
    private String name;
}
