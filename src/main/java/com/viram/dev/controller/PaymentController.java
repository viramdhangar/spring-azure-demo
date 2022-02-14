package com.viram.dev.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.viram.dev.dto.InitiatePaymentDTO;
import com.viram.dev.dto.PaymentResponse;

@RestController
@CrossOrigin(origins = {"*"}, maxAge = 3600)
@RequestMapping("/payment")
public class PaymentController {
	
	@Autowired
	private InitiatePaymentService paymentService;

	@PostMapping(value="/initiate-payment")
	public InitiatePaymentDTO initiatePayment(@RequestBody InitiatePaymentDTO initiatePaymentDTO) {
		return paymentService.initiatePayment(initiatePaymentDTO);
	}
	
	@PostMapping(value="/payment-response")
	public PaymentResponse paymentResponse(@RequestBody Map<String, String> map) {
		return paymentService.savePaymentAudit(map);
	}
}
