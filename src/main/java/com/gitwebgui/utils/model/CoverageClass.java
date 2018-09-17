package com.gitwebgui.utils.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class CoverageClass {


    private String name;

    @JsonProperty("line-rate")
    private String lineRate;

    @JsonProperty("branch-rate")
    private String branchRate;

    private String complexity;

    private String filename;
}
