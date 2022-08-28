package com.inviggoproject.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class GetUserInfoResponseDto implements Serializable {

    private String phone;
    private Date registrationDate;

    public GetUserInfoResponseDto(String phone, Date registrationDate) {
        this.phone = phone;
        this.registrationDate = registrationDate;
    }
}
