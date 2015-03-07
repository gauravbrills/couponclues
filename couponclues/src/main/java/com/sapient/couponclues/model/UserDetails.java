package com.sapient.couponclues.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "userdetails", type = "userdetails", refreshInterval = "-1")
public class UserDetails {
    @Id
    private String userId;

    private String userName;

    private String userAddress;

    private String userEmail;

    private String sex;

    private String kidId;

    private String partnerId;

    private String likes;

    private String country_id;

    private String dob;

    private String anniversary;

    private String registration_date;

    private Date update_ts;

    private String update_by;

    private String ins_ts;

}