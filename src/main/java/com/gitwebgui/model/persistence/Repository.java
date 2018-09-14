package com.gitwebgui.model.persistence;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table
@Getter
@Setter
public class Repository {

    @Id
    @GeneratedValue
    private long id;

    private String name;

    private String url;

    @Column(nullable=false)
    private String path;

    @ManyToOne
    @JoinColumn(nullable=false)
    @JsonIgnore
    private User user;
}
