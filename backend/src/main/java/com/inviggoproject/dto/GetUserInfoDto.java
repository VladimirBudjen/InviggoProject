package com.inviggoproject.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class GetUserInfoDto implements Serializable {
    private String phone;
    private Date registrationDate;

    public GetUserInfoDto(String phone, Date registrationDate) {
        this.phone = phone;
        this.registrationDate = registrationDate;
    }
}
