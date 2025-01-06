package com.demo.evm.events;

import com.demo.evm.commands.StartCommand;

public class StartedEvent extends AppEvent {

    public StartedEvent(StartCommand cmd) {
        super(cmd.getAggregateId());
    }


}
