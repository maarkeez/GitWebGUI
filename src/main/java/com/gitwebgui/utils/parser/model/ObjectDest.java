package com.gitwebgui.utils.parser.model;


import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class ObjectDest {

    private Map<String, Object> fields = new HashMap<>();
    private String type;
}
