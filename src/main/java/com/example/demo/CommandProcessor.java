package com.example.demo;

import com.example.demo.commands.StartCommand;
import dk.cloudcreate.essentials.components.eventsourced.eventstore.postgresql.eventstream.AggregateType;
import dk.cloudcreate.essentials.components.eventsourced.eventstore.postgresql.processor.EventProcessor;
import dk.cloudcreate.essentials.components.eventsourced.eventstore.postgresql.processor.EventProcessorDependencies;
import dk.cloudcreate.essentials.reactive.command.CmdHandler;
import dk.cloudcreate.essentials.shared.FailFast;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommandProcessor extends EventProcessor {

    private final Aggregates aggregates;

    protected CommandProcessor(Aggregates aggregates,
                               EventProcessorDependencies eventProcessorDependencies) {
        super(eventProcessorDependencies);
        FailFast.requireNonNull(aggregates, "aggregates must not be null");

        this.aggregates = aggregates;
    }

    @Override
    public String getProcessorName() {
        return "AppCommandProcessor";
    }

    @Override
    protected List<AggregateType> reactsToEventsRelatedToAggregateTypes() {
        return List.of(Aggregates.AGGREGATE_TYPE);
    }

    @CmdHandler
    public void handle(StartCommand cmd) {
        FailFast.requireNonNull(cmd, "cmd must not be null");

        if (aggregates.isCommandStarted(cmd.getAggregateId())) {
            throw new RuntimeException("Command is already started");
        }

        aggregates.startNewCommand(new AppAggregate(cmd));
    }
}
