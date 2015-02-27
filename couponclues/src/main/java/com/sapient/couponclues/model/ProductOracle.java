/**
 * 
 */
package com.sapient.couponclues.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Builder;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * @author grawat
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(indexName = "productoracle", type = "productoracle", refreshInterval = "-1")
public class ProductOracle {

    public static String getIndex(final String aproductId,final  String aProduct) {

        return aproductId + "_" + aProduct;
    }

    @Id
    private String prodCombIdx;

    private String productId1;

    private String productId2;

    private Double score;
}
