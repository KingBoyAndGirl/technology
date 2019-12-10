package com.technologygarden.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Opinion {
    private Integer id;

    private String opinion;

    private int status;

    private Integer cId;

    private Role role=null;

}