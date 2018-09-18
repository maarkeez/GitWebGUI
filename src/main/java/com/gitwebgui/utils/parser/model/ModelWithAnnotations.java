package com.gitwebgui.utils.parser.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@DMDObject("model_with_annotation")
@Data
public class ModelWithAnnotations implements ModelInterface{

    @DMDField("Model_name")
    private String name;

    @DMDField("list")
    private List<String> customList = new ArrayList<>();

    @DMDField("sub_model_list")
    private List<SubModel> subModelList = new ArrayList<>();
}
