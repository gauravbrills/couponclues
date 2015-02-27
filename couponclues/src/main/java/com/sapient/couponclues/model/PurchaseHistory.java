package com.sapient.couponclues.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseHistory {
	@Id
	private String prodId;
	private String purchaseDate; 
	private int qty; 
	private String purchasedFrom; 
	private String couponId;
	private String onlinePurchase; //(y/n)
}
