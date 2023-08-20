package com.e.javatest.repository;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder(alphabetic = true)
public interface IdAndNameOnly {
    Object getId();

    String getName();
}
