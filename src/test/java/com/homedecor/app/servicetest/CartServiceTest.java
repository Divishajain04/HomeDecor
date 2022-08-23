package com.homedecor.app.servicetest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.homedecor.app.dto.Cart;
import com.homedecor.app.dto.Category;
import com.homedecor.app.dto.Customer;
import com.homedecor.app.dto.Product;
import com.homedecor.app.exception.CartException;
import com.homedecor.app.exception.CategoryException;
import com.homedecor.app.exception.CustomerException;
import com.homedecor.app.exception.ProductException;
import com.homedecor.app.service.CartService;
import com.homedecor.app.service.CategoryService;
import com.homedecor.app.service.CustomerService;
import com.homedecor.app.service.ProductService;

@SpringBootTest
class CartServiceTest {

	@Autowired
	private CartService cartService;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private ProductService productService;
	
	

	@Test
	void deleteCartByIdTest() throws CartException {
		Cart cart = new Cart(111, null, null);
		assertTrue(this.cartService.addCart(cart));
		assertEquals(true, this.cartService.deleteCartById(111));
		assertThrows(CartException.class, () -> this.cartService.deleteCartById(111));
	}

	@Test
	void addCartTest() throws CartException {
		assertThrows(CartException.class, () -> this.cartService.addCart(null));
		Cart cart = new Cart(111, null, null);
		assertTrue(this.cartService.addCart(cart));
		assertNotNull(this.cartService.getCartById(111));
		assertThrows(CartException.class, () -> this.cartService.addCart(cart));
		assertEquals(true, this.cartService.deleteCartById(111));
	}

	@Test
	void getCartByIdTest() throws CartException {
		Cart cart = new Cart(111, null, null);
		assertTrue(this.cartService.addCart(cart));
		assertNotNull(this.cartService.getCartById(111));
		assertEquals(true, this.cartService.deleteCartById(111));
		assertThrows(CartException.class, () -> this.cartService.getCartById(111));
	}

	@Test
	void getAllCartTest() throws CartException {
		Cart cart = new Cart(111, null, null);
		assertTrue(this.cartService.addCart(cart));
		assertNotNull(this.cartService.getAllCarts());
		assertEquals(true, this.cartService.deleteCartById(111));
		
	}

	@Test
	void updateCartTest() throws CartException {
		Cart cart = new Cart(111, null, null);
		assertTrue(this.cartService.addCart(cart));
		assertNotNull(this.cartService.updateCart(new Cart(111, null, null)));
		assertEquals(true, this.cartService.deleteCartById(111));
		assertThrows(CartException.class, () -> this.cartService.updateCart(new Cart(111, null, null)));
	}
	
	
	@Test
	void totalProductInCustomerCartByIdTest() throws CartException {
		Cart cart = new Cart(111, null, null);
		assertTrue(this.cartService.addCart(cart));
		assertNotNull(this.cartService.totalProductInCustomerCartById(111));
		assertEquals(true, this.cartService.deleteCartById(111));
		assertThrows(CartException.class, () -> this.cartService.totalProductInCustomerCartById(111));
	}
	
	@Test
	void totalAmountOfCustomerCartByIdTest() throws CartException {
		Cart cart = new Cart(111, null, null);
		assertTrue(this.cartService.addCart(cart));
		assertNotNull(this.cartService.totalAmountOfCustomerCartById(111));
		assertEquals(true, this.cartService.deleteCartById(111));
		assertThrows(CartException.class, () -> this.cartService.totalAmountOfCustomerCartById(111));
	}
	
	@Test
	void addProductToCartTest() throws CartException, CustomerException, CategoryException, ProductException {
		Customer customer = new Customer(111, "Prince", "prince123@gmail.com", "Prince0404", "9424499512", "Ayodhya",null, null, null, null);
		assertTrue(customerService.addCustomer(customer));
		Category category = new Category(111,"Furniture",null);
		assertTrue(this.categoryService.addCategory(category));
		assertThrows(ProductException.class, () -> this.cartService.addProductToCart(111,111,1));
		Product product = new Product(111, "Sofa", "Double layer Foam", 20000.0, 20,111);
		assertTrue(this.productService.addProducts(product));
		assertTrue(this.cartService.addProductToCart(111, 111, 2));
		assertEquals(true, this.customerService.deleteCustomer(111));
		assertEquals(true,categoryService.deleteCategoryById(111));
		
	}
	
	@Test
	void removeProductFromCartTest() throws CustomerException, CategoryException, ProductException, CartException {
		Customer customer = new Customer(15, "Prince", "prince123@gmail.com", "Prince0404", "9424499512", "Ayodhya",null, null, null, null);
		assertTrue(customerService.addCustomer(customer));
		Category category = new Category(15,"Furniture",null);
		assertTrue(this.categoryService.addCategory(category));
		Product product = new Product(15, "Sofa", "Double layer Foam", 20000.0, 20,15);
		assertTrue(this.productService.addProducts(product));
		assertTrue(this.cartService.addProductToCart(15, 15, 1));
		assertTrue(this.cartService.removeProductFromCart(15,15,1));
		assertEquals(true, this.customerService.deleteCustomer(15));
		assertEquals(true,categoryService.deleteCategoryById(15));
	}

}
