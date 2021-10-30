package com.misandev.order.consumer.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@Entity
@Table(name = "orders")
public class Order implements Serializable {

	private static final long serialVersionUID = -8363827523448808947L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "order_number")
	private String orderNumber;

	@Temporal(TemporalType.DATE)
	@Column(name = "order_date")
	private Date orderDate;

	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "order_id")
	private List<Item> items;

	@Transient
	private Random random = new Random();

	@PrePersist
	public void prePersist() {
		this.orderNumber = generateOrderNumber();
		this.orderDate = new Date();
	}

	private String generateOrderNumber() {
		int initialLimit = 48;
		int finalLimit = 90;
		int stringLength = 10;
		return random.ints(initialLimit, finalLimit + 1).filter(i -> (i <= 57 || i >= 65)).limit(stringLength)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
	}

}