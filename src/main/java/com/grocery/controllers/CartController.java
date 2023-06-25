package com.grocery.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grocery.payloads.ApiResponse;
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
	
	@PutMapping("/Cart/{cartId}/")
	public ResponseEntity<CartDto>updateCart(@RequestBody CartDto cartDto,@PathVariable Integer cartId){
		CartDto updateCart=this.cartService.updateCart(cartDto, cartId);
		return ResponseEntity.ok(updateCart);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<CartDto>>getAll(){
		return ResponseEntity.ok(this.cartService.getAllCart());
	}
	@GetMapping("/Cart/{cartId}/")
	public ResponseEntity<CartDto>getById(@PathVariable Integer cartId){
		CartDto cart=this.cartService.getCartById(cartId);
		return ResponseEntity.ok(cart);
	}
     @DeleteMapping("/Cart/{cartId}/")
	public ResponseEntity<?>deleteCart(@PathVariable Integer cartId){
		this.cartService.deleteItem(cartId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Succesfully delete", true),HttpStatus.OK);
	}
     @GetMapping("/user/{userId}/")
     public ResponseEntity<List<CartDto>>getCartByUser(@PathVariable Integer userId){
    	 return ResponseEntity.ok(this.cartService.getCartByUser(userId));
     }
}











