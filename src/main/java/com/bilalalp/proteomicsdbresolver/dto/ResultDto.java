package com.bilalalp.proteomicsdbresolver.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class ResultDto implements Serializable {

    @JsonProperty("results")
    private List<ProteotypicityDto> proteotypicityDtoList;
}