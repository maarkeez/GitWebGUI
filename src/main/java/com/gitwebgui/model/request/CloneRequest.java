package com.gitwebgui.model.request;

import lombok.Data;

@Data
public class CloneRequest {

    private String path;
    private String repositoryUrl;

}
