package com.example.demo.commands;

import com.example.demo.AggregateId;
import dk.cloudcreate.essentials.shared.FailFast;

import java.util.Objects;

public class StartCommand {

    private final AggregateId aggregateId;

    public StartCommand(AggregateId aggregateId) {
        FailFast.requireNonNull(aggregateId, "commandId cannot be null");
        this.aggregateId = aggregateId;
    }

    public AggregateId getAggregateId() {
        return aggregateId;
    }

    @Override
    public String toString() {
        return "StartCommand{" +
                "commandId=" + aggregateId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StartCommand that = (StartCommand) o;
        return Objects.equals(aggregateId, that.aggregateId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(aggregateId);
    }
}
