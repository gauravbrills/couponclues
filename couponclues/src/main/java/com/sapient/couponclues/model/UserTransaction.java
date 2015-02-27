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
@Document(indexName = "transactions", type = "transaction", refreshInterval = "-1")
public class UserTransaction {
    @Id
    private String transactionId;

    private String userId;

    private String productId;

    private Date transactionDt;

    private Integer quantity;
}
