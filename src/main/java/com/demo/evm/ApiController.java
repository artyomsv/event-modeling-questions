package com.demo.evm;

import com.demo.evm.commands.StartCommand;
import dk.cloudcreate.essentials.reactive.command.CommandBus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api")
public class ApiController {

    private final CommandBus commandBus;
    private final Aggregates aggregates;


    public ApiController(CommandBus commandBus, Aggregates aggregates) {
        this.commandBus = commandBus;
        this.aggregates = aggregates;
    }

    @GetMapping("/command/{id}")
    public ResponseEntity<?> command(@PathVariable("id") String id) {
        return aggregates.find(AggregateId.of(id))
                .map(it -> ResponseEntity.ok(it.aggregateId()))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/command")
    public ResponseEntity<Void> startCommand(@RequestBody StartComandApiBody body) {
        AggregateId aggregateId = AggregateId.random();
        commandBus.sendAndDontWait(new StartCommand(aggregateId));
        return ResponseEntity.created(URI.create("/api/command/" + aggregateId)).build();
    }

    class StartComandApiBody {
        private String data;

        public StartComandApiBody(String data) {
            this.data = data;
        }

        public String getId() {
            return data;
        }
    }
}
