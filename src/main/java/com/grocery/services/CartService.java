package com.grocery.services;

import java.util.List;
import com.grocery.payloads.CartDto;

public interface CartService {

	CartDto createCart(CartDto cartDto ,Integer productId,Integer quantity);
	
	CartDto updateCart(CartDto cartDto,Integer cartId);
	
	List<CartDto>getAllCart();
	
	CartDto getCartById(Integer cartId);
	
	void deleteItem(Integer cartId);
	
}
