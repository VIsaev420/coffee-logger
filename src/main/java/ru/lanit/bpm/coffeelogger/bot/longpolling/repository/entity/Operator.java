package ru.lanit.bpm.coffeelogger.bot.longpolling.repository.entity;

import lombok.NoArgsConstructor;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
public class Operator {
    @Id
    private String id;
    @Basic
    private String name;
}
