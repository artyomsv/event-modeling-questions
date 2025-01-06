package com.demo.evm.events;

import com.demo.evm.AggregateId;
import dk.cloudcreate.essentials.shared.FailFast;

import java.util.Objects;

public abstract class AppEvent {

    private final AggregateId aggregateId;

    protected AppEvent(AggregateId aggregateId) {
        FailFast.requireNonNull(aggregateId, "aggregateId cannot be null");
        this.aggregateId = aggregateId;
    }

    @Override
    public String toString() {
        return "AppEvent{" +
                "aggregateId=" + aggregateId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppEvent appEvent = (AppEvent) o;
        return Objects.equals(aggregateId, appEvent.aggregateId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(aggregateId);
    }
}
