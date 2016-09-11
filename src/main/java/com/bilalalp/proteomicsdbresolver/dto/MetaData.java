package com.bilalalp.proteomicsdbresolver.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class MetaData implements Serializable {

    @JsonProperty("type")
    private String type;

    @JsonProperty("uri")
    private String uri;
}