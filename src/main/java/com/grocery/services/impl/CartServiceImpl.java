package com.grocery.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grocery.entities.Cart;
import com.grocery.entities.Product;
import com.grocery.entities.User;
import com.grocery.exceptions.ResourceNotFoundException;
import com.grocery.payloads.CartDto;
import com.grocery.repositories.CartRepo;
import com.grocery.repositories.ProductRepo;
import com.grocery.repositories.UserRepo;
import com.grocery.services.CartService;

@Service
public class CartServiceImpl implements CartService{
	@Autowired
	private CartRepo cartRepo;
	@Autowired
	private ProductRepo productRepo;
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CartDto createCart(CartDto cartDto,Integer productId,Integer userId) {
		Product product=this.productRepo.findById(productId).orElseThrow(()->new ResourceNotFoundException("Product", "ProductId", productId));
		User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "UserId", userId));
		Cart cart=this.modelMapper.map(cartDto,Cart.class);
		cart.setProduct(product);
		cart.setUser(user);
		Integer quant=cart.getCartQuantity();
		float proPri=product.getProductAmount();
		float total=this.totalAmt(quant, proPri);
		cart.setCartAmount(total);
		Cart newCart=this.cartRepo.save(cart);
		return this.modelMapper.map(newCart,CartDto.class);
	}

	@Override
	public CartDto updateCart(CartDto cartDto,Integer cartId) {
		Cart cart=this.cartRepo.findById(cartId).orElseThrow(()->new ResourceNotFoundException("Cart", "CartId", cartId));
		cart.setCartQuantity(cartDto.getCartQuantity());
		Cart update=this.cartRepo.save(cart);
		return this.modelMapper.map(update, CartDto.class);
	}

	@Override
	public List<CartDto> getAllCart() {
		List<Cart>list=this.cartRepo.findAll();
		List<CartDto>cart=list.stream().map(cart1->this.toDTO(cart1)).collect(Collectors.toList());
		return cart;
	}

	@Override
	public CartDto getCartById(Integer cartId) {
		Cart cart=this.cartRepo.findById(cartId).orElseThrow(()->new ResourceNotFoundException("Cart", "CartId", cartId));
		return this.modelMapper.map(cart, CartDto.class);
	}

	@Override
	public void deleteItem(Integer cartId) {
		this.cartRepo.deleteById(cartId);
		
	}
	
	private CartDto toDTO(Cart cart) {
		return this.modelMapper.map(cart, CartDto.class);
	}
	
	private float totalAmt(Integer quantity,float amt) {
		
		float amount=quantity*amt;
		
		return amount;
	}


	@Override
	public List<CartDto> getCartByUser(Integer userId) {
		User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "UserId", userId));
		List<Cart>list=this.cartRepo.findByUser(user);
		List<CartDto>cart=list.stream().map(cart1->this.toDTO(cart1)).collect(Collectors.toList());
		return cart;
	}

}
