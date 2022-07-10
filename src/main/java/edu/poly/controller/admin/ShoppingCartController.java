package edu.poly.controller.admin;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.poly.domain.Product;
import edu.poly.model.CartItemDto;
import edu.poly.model.ProductDto;
import edu.poly.service.ProductService;
import edu.poly.service.ShoppingCartService;

@Controller
@RequestMapping("shoppingCart")
public class ShoppingCartController {
	@Autowired
	ProductService productService;
	
	@Autowired
	ShoppingCartService shoppingCartService;
	
	@GetMapping("cartitem")
	public String list(Model model) {
		Collection<CartItemDto> cartItems = shoppingCartService.getCartItems();
		
		model.addAttribute("cartItem", cartItems);
		model.addAttribute("total", shoppingCartService.getAmount());
		model.addAttribute("NoOfItems", shoppingCartService.getCount());
		
		
		return "/admin/shoppingcart/cartitem";
	}
	@GetMapping("add/{productId}")
	public String add(@PathVariable("productId") Long productId) {
		Product product = productService.findById(productId).get();
		
		if(product != null) {
			CartItemDto item = new CartItemDto();
			BeanUtils.copyProperties(product, item);
			item.setQuantity(1);
			shoppingCartService.add(item);
		}
		
		return "redirect:/shoppingCart/cartitem";
	}
	@GetMapping("remove/{productId}")
	public String remove(@PathVariable("productId") Long productId) {
		shoppingCartService.remove(productId);
		return "redirect:/shoppingCart/cartitem";
	}
	@PostMapping("update")
	public String update(@RequestParam("productId") Long productId, 
			@RequestParam("quantity") Integer quantty) {
		shoppingCartService.update(productId, quantty);
		return "redirect:/shoppingCart/cartitem";
	}
	@GetMapping("clear")
	public String clear() {
		return "redirect:/shoppingCart/cartitem";
	}
	

}
