package ru.lanit.bpm.coffeelogger.bot.longpolling.persistance.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.lanit.bpm.coffeelogger.bot.longpolling.persistance.repository.OperatorRepository;
import ru.lanit.bpm.coffeelogger.bot.longpolling.persistance.entity.Operator;

import javax.validation.constraints.NotNull;

@Component
@RequiredArgsConstructor
public class OperatorAdapter {
    private final OperatorRepository repository;

    public Operator createOperator(String operatorName, @NotNull String operatorId) {
        var operator = new Operator();
        if (!repository.existsById(operatorId)) {
            operator.setId(operatorId);
            operator.setName(operatorName);

            save(operator);
        }
        return operator;
    }

    // ===================================================================================================================
    // = Implementation
    // ===================================================================================================================

    private void save(Operator operator) {
        repository.saveAndFlush(operator);
    }
}
