package edu.poly.service;

import java.util.Collection;

import edu.poly.model.CartItemDto;

public interface ShoppingCartService {

	int getCount();

	double getAmount();

//	void update(long productId, int quantity);

	void clear();

	Collection<CartItemDto> getCartItems();

//	void remove(long productId);

	void add(CartItemDto item);

	void remove(Long productId);

	void update(Long productId, int quantity);

	

}
