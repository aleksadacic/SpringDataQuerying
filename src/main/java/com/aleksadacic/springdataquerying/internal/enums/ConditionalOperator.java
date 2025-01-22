package com.aleksadacic.springdataquerying.internal.enums;

import com.aleksadacic.springdataquerying.internal.deserializers.ConditionalOperatorDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(using = ConditionalOperatorDeserializer.class)
public enum ConditionalOperator {
    AND("AND"),
    OR("OR");

    public final String operator;

    ConditionalOperator(String operator) {
        this.operator = operator;
    }
}
