package com.gitwebgui.utils.parser.model;


import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class ObjectDest implements ModelInterface {

    private Map<String, Object> fields = new HashMap<>();
    private String type;
}
