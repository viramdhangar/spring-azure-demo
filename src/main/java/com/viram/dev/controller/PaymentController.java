package com.viram.dev.controller;

import java.io.IOException;
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
import com.viram.dev.service.InitiatePaymentService;

@RestController
@CrossOrigin(origins = "*")
public class PaymentController {
	
	@Autowired
	private InitiatePaymentService paymentService;

	@GetMapping("/welcome")
	public String welcome() {
		return "Welcome to azure!!";
	} 
	
	@PostMapping("/initiate-payment")
	public InitiatePaymentDTO initiatePayment(@RequestBody InitiatePaymentDTO initiatePaymentDTO) throws IOException {
		return paymentService.initiatePayment(initiatePaymentDTO);
	}
	
	@PostMapping("/payment-response")
	public PaymentResponse paymentResponse(@RequestBody Map<String, String> map) {
		return paymentService.savePaymentAudit(map);
	}
}
