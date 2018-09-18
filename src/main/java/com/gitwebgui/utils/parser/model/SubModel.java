package com.gitwebgui.utils.parser.model;

import lombok.Data;

@DMDObject("SubModel")
@Data
public class SubModel implements ModelInterface {

    @DMDField("SubModelName")
    private String name;

}
