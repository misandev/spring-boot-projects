package com.misandev.order.consumer.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;

@Data
public class ProductDto implements Serializable {

	private static final long serialVersionUID = 6016979361351910385L;

	private Long id;

	private String name;

	private BigDecimal price;

	private Integer stock;

}