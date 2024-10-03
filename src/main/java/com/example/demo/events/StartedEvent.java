package com.example.demo.events;

import com.example.demo.commands.StartCommand;

public class StartedEvent extends AppEvent {

    public StartedEvent(StartCommand cmd) {
        super(cmd.getAggregateId());
    }


}
