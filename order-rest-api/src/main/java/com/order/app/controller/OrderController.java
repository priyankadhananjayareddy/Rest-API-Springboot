package com.order.app.controller;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.order.app.entity.Order;
import com.order.app.modal.*;
import com.order.app.repository.OrderRepository;
import com.order.app.service.Sender;
import com.order.app.validation.ValidateData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.order.app.entity.OrderDetail;
import com.order.app.repository.OrderDetailRepository;

@RestController
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	OrderDetailRepository orderDetailRepository;

	@Autowired
	ValidateData validateData;

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	Sender sender;

	@GetMapping("/getAllOrders") 
	public List<OrderDetail> getAllOrders() {
		List<OrderDetail> orderDetailList = orderDetailRepository.findAll();
		return orderDetailList;
	}
	
	@PostMapping("/createOrder")
	public String createOrder(@RequestBody NewOrderInfo newOrderInfo) throws JsonProcessingException {
		String message = "Order Created Successfully";

		CustomerValidationResponse customerValidationResponse = validateData.validateCustomerInfo(newOrderInfo.getCustomerName(), newOrderInfo.getFullAddress());
		ProductValidationResponse productValidationResponse = validateData.validateProductInfo(newOrderInfo.getProductName(), newOrderInfo.getPrice());
		PaymentValidationResponse paymentValidationResponse = validateData.validatePaymentType(newOrderInfo.getPaymentType());

		if(customerValidationResponse.getErrorMessage() == null && productValidationResponse.getErrorMessage() == null
				&& paymentValidationResponse.getErrorMessage() == null) {
			// Customer Entered Data is Valid.
			Order order = new Order();
			order.setAddressId(customerValidationResponse.getAddressId());
			order.setCustomerId(customerValidationResponse.getCustomerId());
			order.setPaymentId(paymentValidationResponse.getPaymentId());
			order.setProductId(productValidationResponse.getProductId());
			order.setStatusId(1L);
			Order maxTransactionIdOrder = orderRepository.getOrderDetailsByMaxTransactionId();
			order.setTransactionId(maxTransactionIdOrder.getTransactionId() + 1);
			Order createdOrder = orderRepository.save(order);

			OrderDetailsForMQ orderDetailsForMQ = new OrderDetailsForMQ(newOrderInfo.getCustomerName(),
					newOrderInfo.getFullAddress(), newOrderInfo.getProductName(),newOrderInfo.getPrice(),
					newOrderInfo.getPaymentType(), createdOrder.getOrderId(),createdOrder.getTransactionId(), customerValidationResponse.getEmailId());
			sender.sendEmailDetailsToMQ(orderDetailsForMQ);

		} else {
//			message = ((customerValidationResponse.getErrorMessage() != null) ? customerValidationResponse.getErrorMessage() : "")
//			+ ((productValidationResponse.getErrorMessage() != null) ? productValidationResponse.getErrorMessage() : "")
//					+ ((paymentValidationResponse.getErrorMessage() != null) ? paymentValidationResponse.getErrorMessage() : "");

			String customerErrorMessage = (customerValidationResponse.getErrorMessage() != null) ? customerValidationResponse.getErrorMessage() : "";
			String productErrorMessage = (productValidationResponse.getErrorMessage() != null) ? productValidationResponse.getErrorMessage() : "";
			String paymentErrorMessage = (paymentValidationResponse.getErrorMessage() != null) ? paymentValidationResponse.getErrorMessage() : "";

			message = customerErrorMessage + productErrorMessage + paymentErrorMessage;
		}
		return message;
	}
}
