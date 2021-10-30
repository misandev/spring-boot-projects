package com.misandev.order.producer.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class ItemDto implements Serializable {

	private static final long serialVersionUID = -7648009902049466634L;

	private Long productId;

	private Integer quantity;

}