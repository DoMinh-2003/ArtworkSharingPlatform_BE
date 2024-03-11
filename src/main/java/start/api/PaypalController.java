//package start.api;
//
//import com.paypal.api.payments.Payment;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import start.config.paypal.PaypalPaymentIntent;
//import start.config.paypal.PaypalPaymentMethod;
//import start.dto.request.RechargeRequestDTO;
//import start.service.PaypalService;
//import start.utils.ResponseHandler;
//
//import java.util.logging.Logger;
//
//public class PaypalController {
//    package com.qlam.controller;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import com.paypal.api.payments.Links;
//import com.paypal.api.payments.Payment;
//import com.paypal.base.rest.PayPalRESTException;
//    @Controller
//    public class PaymentController{
//
//    public static final String URL_PAYPAL_SUCCESS = "pay/success";
//    public static final String URL_PAYPAL_CANCEL = "pay/cancel";
//
//        @Autowired
//        ResponseHandler responseHandler;
//    @Autowired
//    private PaypalService paypalService;
//    @GetMapping("/")
//
//    @PostMapping("/pay")
//    public ResponseEntity createUrl(@RequestBody RechargeRequestDTO rechargeRequestDTO){
//        Payment payment = paypalService.createPayment(
//                price,
//                "USD",
//                PaypalPaymentMethod.paypal,
//                PaypalPaymentIntent.sale,
//                "payment description");
//        for(Links links : payment.getLinks())
//            if(links.getRel().equals("approval_url"))
//                return "redirect:" + links.getHref();
//        return  responseHandler.response(200, "Create Url Successfully!", url);
//    }
//
//
//
//
//
//    @GetMapping(URL_PAYPAL_CANCEL)
//    public String cancelPay()
//		return "cancel";
//
//
//    @GetMapping(URL_PAYPAL_SUCCESS)
//    public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId)
//		try
//    Payment payment = paypalService.executePayment(paymentId, payerId);
//			if(payment.getState().equals("approved"))
//            return "success";
//
//		 catch (PayPalRESTException e)
//            log.error(e.getMessage());
//
//		return "redirect:/";
//
//}
//}
