package com.example.demo;

import dk.cloudcreate.essentials.types.CharSequenceType;

import java.util.UUID;

public class AggregateId extends CharSequenceType<AggregateId> {

    private AggregateId(CharSequence value) {
        super(value);
    }

    public static AggregateId random() {
        return new AggregateId(UUID.randomUUID().toString());
    }

    public static AggregateId of(CharSequence id) {
        return new AggregateId(id);
    }



}
