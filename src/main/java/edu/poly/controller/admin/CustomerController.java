package edu.poly.controller.admin;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.websocket.Session;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import edu.poly.domain.Account;
import edu.poly.domain.Category;
import edu.poly.domain.Customer;
import edu.poly.model.AccountDto;
import edu.poly.model.CustomerDto;
import edu.poly.service.AccountService;
import edu.poly.service.CategoryService;
import edu.poly.service.CustomerService;

@Controller
@RequestMapping("admin/customers")
public class CustomerController {
	@Autowired
	CustomerService customerService;
	
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	HttpSession session;


	@GetMapping("add")
	public String add(Model model ) {
		
		model.addAttribute("customer", new CustomerDto());
		return "admin/customers/addOrEdit";
	}
	
	@PostMapping("saveOrUpdate")
	public ModelAndView saveOrUpdate(ModelMap model,
		 @Valid @ModelAttribute("customer")	CustomerDto dto, BindingResult result) {
		if(result.hasErrors()) {
			return new ModelAndView("admin/customers/addOrEdit");
		}
		Customer entity = new Customer();
		BeanUtils.copyProperties(dto, entity);
		customerService.save(entity);
		model.addAttribute("message", "Customer is saved!");
		
		return new ModelAndView("redirect:/admin/customers", model) ;
	}
	
	@GetMapping("")
	public String list(ModelMap model) {
		List<Customer> list = customerService.findAll();
		model.addAttribute("customers", list);
		return "admin/customers/list";
	}
	@GetMapping("edit/{customerId}")
	public ModelAndView edit(ModelMap model, @PathVariable("customerId") Long customerId) {
		Optional<Customer> opt = customerService.findById(customerId);
		CustomerDto dto = new CustomerDto();
		
		if(opt.isPresent()) {
			Customer entity  = opt.get();
			
			BeanUtils.copyProperties(entity, dto);
			dto.setIsEdit(true);
			
			model.addAttribute("customer", dto);
			
			return new ModelAndView("admin/customers/addOrEdit", model);
		}
		model.addAttribute("message", "Customer is not existed");
		return new ModelAndView("forward:/admin/customers/searchpaginated", model);
	}
	@GetMapping("delete/{customerId}")
	public ModelAndView delete(ModelMap model, @PathVariable("customerId")Long customerId) {
		customerService.deleteById(customerId);
		
		model.addAttribute("message", "Customer is deleted!");
		return new ModelAndView("forward:/admin/customers", model) ;
	}
//	@GetMapping("login")
//	public String login() {
//		
//			String username = request.getParameter("username");
//			String password = request.getParameter("password");
//			
//			try {
//				Optional<Account> account = accountService.findById(username);
//				if (account.isPresent()) {
//					if (!account.get().getPassword().equals(password)) {
//						return "/admin/customers/login";
//					}else {
//						session.setAttribute(username, password);
//						return "admin/customers";
//					}
//				}
//			} catch (Exception e) {
//				// TODO: handle exception
//			}
//			
//			return "/admin/customers/login";
//	}
		
//	
//	
//	@GetMapping("search")
//	public String search(ModelMap model, 
//			@RequestParam(name = "name", required = false) String name ) {
//		List<Category> list = null;
//		
//		if(StringUtils.hasText(name)) {
//			list = accountService.findByName(name);
//		}else {
//			list = accountService.findAll();
//		}
//		
//		model.addAttribute("customers", list);
//		return "admin/customers/search";
//	}
//	
//	@GetMapping("searchpaginated")
//	public String search(ModelMap model, 
//			@RequestParam(name = "name", required = false) String name,
//			@RequestParam("page") Optional<Integer> page,
//			@RequestParam("size") Optional<Integer> size) {
//		
//		int currentPage = page.orElse(1);
//		int pageSize = size.orElse(5);
//		
//		Pageable pageable = PageRequest.of(currentPage-1, pageSize, Sort.by("name"));
//		Page<Category> resultPage = null;
//		
//		
//		
//		if(StringUtils.hasText(name)) {
//			resultPage = accountService.findByName(name, pageable);
//			model.addAttribute("name" , name);
//			
//		}else {
//			resultPage = accountService.findAll(pageable);
//		}
//		
//		int totalPages = resultPage.getTotalPages();
//		if(totalPages > 0 ) {
//			int start = Math.max(1, currentPage-2);
//			int end = Math.min(currentPage + 2, totalPages);
//			
//			if(totalPages > 5) {
//				if(end == totalPages) start = end - 5;
//				else if(start == 1) end = start + 5;
//			}
//			List<Integer> pageNumbers = IntStream.rangeClosed(start, end)
//					.boxed()
//					.collect(Collectors.toList());
//			
//			model.addAttribute("pageNumbers", pageNumbers);
//		}
//		model.addAttribute("accountPage", resultPage);
//		
//		
//		return "admin/customers/searchpaginated";
//	}

}
