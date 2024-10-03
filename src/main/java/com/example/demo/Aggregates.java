package com.example.demo;

import com.example.demo.events.AppEvent;
import dk.cloudcreate.essentials.components.eventsourced.aggregates.stateful.StatefulAggregateInstanceFactory;
import dk.cloudcreate.essentials.components.eventsourced.aggregates.stateful.StatefulAggregateRepository;
import dk.cloudcreate.essentials.components.eventsourced.eventstore.postgresql.ConfigurableEventStore;
import dk.cloudcreate.essentials.components.eventsourced.eventstore.postgresql.eventstream.AggregateType;
import dk.cloudcreate.essentials.components.eventsourced.eventstore.postgresql.persistence.table_per_aggregate_type.SeparateTablePerAggregateEventStreamConfiguration;
import dk.cloudcreate.essentials.components.eventsourced.eventstore.postgresql.types.EventOrder;
import dk.cloudcreate.essentials.types.LongRange;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class Aggregates {

    static final AggregateType AGGREGATE_TYPE = AggregateType.of("AppAggregate");
    private final ConfigurableEventStore<SeparateTablePerAggregateEventStreamConfiguration> eventStore;
    private final StatefulAggregateRepository<AggregateId, AppEvent, AppAggregate> repository;

    public Aggregates(ConfigurableEventStore<SeparateTablePerAggregateEventStreamConfiguration> eventStore) {
        this.eventStore = eventStore;
        this.repository = StatefulAggregateRepository.from(
                eventStore,
                AGGREGATE_TYPE,
                StatefulAggregateInstanceFactory.reflectionBasedAggregateRootFactory(),
                AppAggregate.class
        );
    }

    public AppAggregate startNewCommand(AppAggregate appAggregate) {
        return repository.save(appAggregate);
    }

    public boolean isCommandStarted(AggregateId id) {
        return eventStore.fetchStream(AGGREGATE_TYPE, id, LongRange.only(EventOrder.FIRST_EVENT_ORDER.longValue())).isPresent();
    }

    public Optional<AppAggregate> find(AggregateId id) {
        return repository.tryLoad(id);
    }
}
