package com.sapient.couponclues.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductMaster {
	@Id
	private String prodId;
	private String prodName;
	private String relatedProdId;
	private String relatedProdName;
	private String csvProdId; //Flipkart=”prod1”,snapdeal=”prod22”	
	private String prodCategory;
    
}
