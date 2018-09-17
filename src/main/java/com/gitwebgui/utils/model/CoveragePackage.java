package com.gitwebgui.utils.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class CoveragePackage {


    private String name;

    @JsonProperty("line-rate")
    private String lineRate;

    private List<CoverageClass> classes;
}
