package com.sapient.couponclues.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CountryHolidays {
    @Id
    private String CountryCode;
    private Date HolidayDate;
    private String holidayDesc;
    private String isShoppingSpecial;

}
