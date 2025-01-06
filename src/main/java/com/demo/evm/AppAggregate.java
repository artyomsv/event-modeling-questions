package com.demo.evm;

import com.demo.evm.commands.StartCommand;
import com.demo.evm.events.AppEvent;
import com.demo.evm.events.StartedEvent;
import dk.cloudcreate.essentials.components.eventsourced.aggregates.stateful.modern.AggregateRoot;

public class AppAggregate extends AggregateRoot<AggregateId, AppEvent, AppAggregate> {

    public AppAggregate(AggregateId aggregateId) {
        super(aggregateId);
    }

    public AppAggregate(StartCommand cmd) {
        super(cmd.getAggregateId());
        apply(new StartedEvent(cmd));
    }
}
