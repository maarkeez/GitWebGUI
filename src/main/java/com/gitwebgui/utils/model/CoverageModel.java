package com.gitwebgui.utils.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class CoverageModel {

    @JsonProperty("line-rate")
    private String lineRate;

    private List<CoveragePackage> packages;
}
