package edu.poly.service.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import edu.poly.model.CartItemDto;
import edu.poly.service.ShoppingCartService;

@Service
@SessionScope
public class ShoppingCartServiceImpl implements ShoppingCartService {
	private Map<Long, CartItemDto> map = new HashMap<Long, CartItemDto>();
	
	@Override
	public void add (CartItemDto item) {
		CartItemDto existedItem = map.get(item.getProductId());
		
		if(existedItem != null) {
			existedItem.setQuantity(item.getQuantity() + existedItem.getQuantity());
		}else {
			map.put(item.getProductId(), item);
		}
		
	}
	
	@Override
	public void remove(Long productId) {
		map.remove(productId);
	}
	
	@Override
	public Collection<CartItemDto> getCartItems(){
		return map.values();
	}
	@Override
	public void clear() {
		map.clear();
	}
	@Override
	public void update(Long productId, int quantity) {
		CartItemDto item = map.get(productId);
		
		item.setQuantity(quantity);
//		item.setQuantity(quantity + item.getQuantity());
		
//		if(item.getQuantity() <= 0) {
//			map.remove(productId);
//		}
		
		
	}
	@Override
	public double getAmount() {
		return map.values().stream().mapToDouble(item->item.getQuantity() * item.getUnitPrice()).sum();
	}
	@Override
	public int getCount() {
		if(map.isEmpty())
			return 0; 
//		return map.values().size();
		return map.values().size();
	}

}
