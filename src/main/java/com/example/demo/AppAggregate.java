package com.example.demo;

import com.example.demo.commands.StartCommand;
import com.example.demo.events.AppEvent;
import com.example.demo.events.StartedEvent;
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
