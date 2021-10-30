package com.misandev.order.producer.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ProductDto implements Serializable {

	private static final long serialVersionUID = 6927191550446953601L;

	private Long id;

	@NotEmpty(message = "Please provide a valid name.")
	private String name;

	@NotNull(message = "Please provide a valid price.")
	private BigDecimal price;

	@NotNull(message = "Please provide a valid stock.")
	private Integer stock;

}