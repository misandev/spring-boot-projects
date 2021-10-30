package com.misandev.order.producer.dto;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class OrderDto implements Serializable {

	private static final long serialVersionUID = -7717545594354269241L;

	@NotEmpty(message = "Please add a valid product.")
	private List<ItemDto> items;

}