package com.viram.dev.service;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.viram.dev.dto.InitiatePaymentDTO;
import com.viram.dev.dto.Order;
import com.viram.dev.dto.PaymentResponse;
import com.viram.dev.dto.SecretDTO;
import com.viram.dev.dto.Token;
import com.viram.dev.repository.InitiatePaymentRepo;
import com.viram.dev.repository.OrderRepo;
import com.viram.dev.repository.PaymentResponseRepo;
import com.viram.dev.repository.SecretRepo;
import com.viram.dev.util.Constants;

@Component
public class InitiatePaymentService {

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private SecretRepo secretRepo;
	
	@Autowired
	private OrderRepo orderRepo;
	
	@Autowired
	private InitiatePaymentRepo initiatePaymentRepo;
	
	@Autowired
	private PaymentResponseRepo paymentResponseRepo;
	

	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}
	
	public Token getToken(InitiatePaymentDTO paymentDTO, SecretDTO secret, Order savedOrder) {
		
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.set("x-client-id", secret.getIdType()); //4568cb305ede27b930ed22b58654
		headers.set("x-client-secret", secret.getIdValue()); //3537a7696f8183ae8f2cfaf7b2f6ffed825c77cf
		HttpEntity<Order> entity = new HttpEntity<Order>(savedOrder, headers);

		return restTemplate
				.exchange("https://test.cashfree.com/api/v2/cftoken/order", HttpMethod.POST, entity, Token.class)
				.getBody();
	}

	public InitiatePaymentDTO initiatePayment(InitiatePaymentDTO paymentDTO) {

		// get secret
		SecretDTO conf = secretRepo.findByType(Constants.SECRET);
		
		
		Order order = new Order();
		Order savedOrder = orderRepo.save(order);
		savedOrder.setOrderId(savedOrder.getId()+"ORD"+paymentDTO.getCustomerPhone().substring(0, 2)+paymentDTO.getCustomerName().substring(0, 3).toUpperCase());
		savedOrder.setOrderAmount(paymentDTO.getOrderAmount());
		savedOrder.setOrderCurrency("INR");
		orderRepo.save(savedOrder);
		
		Token tokenGenerated = getToken(paymentDTO, conf, savedOrder);
		
		paymentDTO.setAppId(conf.getIdType());
		paymentDTO.setOrderCurrency("INR");
		paymentDTO.setOrderId(savedOrder.getOrderId());
		paymentDTO.setOrderNote("Payment to dev");
		paymentDTO.setStage(conf.getType());
		initiatePaymentRepo.save(paymentDTO);
		
		paymentDTO.setTokenData(tokenGenerated.getCftoken());
		
		return paymentDTO;
	}
	
	/*
	 * public SecretDTO getSecret(String type) { SecretDTO conf =
	 * configRepo.findByType(type); if(conf.isPresent()) { return conf.get(); }
	 * return new SecretDTO(); }
	 */
	
	public PaymentResponse savePaymentAudit(Map<String, String> map) {
		return paymentResponseRepo.save(convertToObject(map));
	}
	
	private PaymentResponse convertToObject(Map<String, String> map) {
		PaymentResponse paymentResponse = new PaymentResponse();
		paymentResponse.setOrderId(map.get("orderId"));
		paymentResponse.setOrderAmount(map.get("orderAmount"));
		paymentResponse.setPaymentMode(map.get("paymentMode"));
		paymentResponse.setReferenceId(map.get("referenceId"));
		paymentResponse.setSignature(map.get("signature"));
		paymentResponse.setTxMsg(map.get("txMsg"));
		paymentResponse.setTxStatus(map.get("txStatus"));
		paymentResponse.setTxTime(map.get("txTime"));
		paymentResponse.setType(map.get("type"));
		return paymentResponse;
	}
}
