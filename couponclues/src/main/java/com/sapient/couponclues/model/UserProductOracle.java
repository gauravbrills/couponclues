/**
 * 
 */
package com.sapient.couponclues.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * @author grawat
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "userproductoracle", type = "userproductoracle", shards = 1, replicas = 0, refreshInterval = "-1")
public class UserProductOracle {
    @Id
    private String id;

    private String userId;

    private String productId;

    private Double score;
}
