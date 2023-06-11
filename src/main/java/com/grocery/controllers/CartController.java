package com.grocery.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.grocery.payloads.CartDto;
import com.grocery.services.CartService;

@RestController
@RequestMapping("/api/")
public class CartController {
	@Autowired
	private CartService cartService;
	
	@PostMapping("/Product/{productId}/User/{userId}/")
	public ResponseEntity<CartDto>createCart(@RequestBody CartDto cartDto,@PathVariable Integer productId,@PathVariable Integer userId){
		CartDto newCart=this.cartService.createCart(cartDto, productId,userId);
		return new ResponseEntity<>(newCart,HttpStatus.CREATED);
	}
	

}
