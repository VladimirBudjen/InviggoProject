package com.inviggoproject.dto;

import com.inviggoproject.enums.MinMaxAll;
import lombok.Data;

import java.io.Serializable;

@Data
public class FindAllAdvertsByPageRequestDto implements Serializable {
    private String name;
    private String ownerUserName;
    private String categoryName;
    private Integer page;
    private Integer pageSize;
    private MinMaxAll minOrMax;
}
