package com.bilalalp.proteomicsdbresolver.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class MainResult implements Serializable {

    @JsonProperty("d")
    private ResultDto resultDto;
}