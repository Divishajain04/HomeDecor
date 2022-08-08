package com.homedecor.app.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.homedecor.app.dao.CartRepository;
import com.homedecor.app.dao.CustomerRepository;
import com.homedecor.app.dao.ProductRepository;
import com.homedecor.app.dao.WishlistRepository;
import com.homedecor.app.dto.Cart;
import com.homedecor.app.dto.Customer;
import com.homedecor.app.dto.Product;
import com.homedecor.app.dto.Wishlist;
import com.homedecor.app.exception.CartException;
import com.homedecor.app.exception.CustomerException;
import com.homedecor.app.exception.ProductException;


@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private WishlistRepository wishlistRepository;

	@Override
	public Boolean addCart(Cart cart) throws CartException {

		if (cart == null)
			throw new CartException("cart not added please fill the mandatory details");
		Optional<Cart> foundCart = this.cartRepository.findById(cart.getCartId());
		if (foundCart.isPresent()) {
			throw new CartException("Cart already exist");
		} else {
			this.cartRepository.save(cart);
		}
		return true;
	}

	@Override
	public Cart updateCart(Cart cart) throws CartException {
		Optional<Cart> foundCart = this.cartRepository.findById(cart.getCartId());
		if (foundCart.isEmpty())
			throw new CartException("Cart does not exist for this id :- "+cart.getCartId());
		return this.cartRepository.save(cart);
	}

	@Override
	public Optional<Cart> getCartById(Integer cartId) throws CartException {
		Optional<Cart> foundCart = this.cartRepository.findById(cartId);
		if (foundCart.isEmpty())
			throw new CartException("Cart does not exist for this id" + cartId);
		return foundCart;
	}

	@Override
	public List<Cart> getAllCarts() throws CartException {
		List<Cart> foundCarts = this.cartRepository.findAll();
		if (foundCarts.isEmpty())
			throw new CartException("No carts found");
		return this.cartRepository.findAll();
	}

	@Override
	public Boolean deleteCartById(Integer cartId) throws CartException {
		Optional<Cart> foundCart = this.cartRepository.findById(cartId);
		if (foundCart.isEmpty())
			throw new CartException("Cart does not exist for this id " + cartId);
		this.cartRepository.deleteById(cartId);
		return true;
	}

	@Override
	public Optional<Double> totalAmountOfCustomerCartById(Integer cartId) throws CartException {
		Optional<Cart> getCart=this.cartRepository.findById(cartId);
		if(getCart.isEmpty())throw new CartException("Cart does not exist for this id " + cartId);
		Cart foundCart=getCart.get();
		List<Product> products =	foundCart.getProduct();
			return  products.stream().map(i-> i.getProductPrice()).reduce((e1,e2)-> e1+e2);
	}

	@Override
	public Long totalProductInCustomerCartById(Integer cartId) throws CartException {
		Optional<Cart> getCart=this.cartRepository.findById(cartId);
		if(getCart.isEmpty())throw new CartException("Cart does not exist for this id " + cartId);
		Cart foundCart=getCart.get();
		List<Product> products =	foundCart.getProduct();
		return products.stream().map(i->i.getProductId()).count();
	}

	@Override
	public Boolean addProductTocart(Integer customerId, Integer productId, Integer quantity) throws ProductException, CustomerException {
		Optional<Customer> foundCustomer=this.customerRepository.findById(customerId);
		if (foundCustomer.isEmpty()) throw new CustomerException("Invalid Customer Id");
		Optional<Product> foundProduct=this.productRepository.findById(productId);
		if (foundProduct.isEmpty()) 
			throw new ProductException("Invalid Product Id");
		Customer getCustomer=foundCustomer.get();
		Product getProduct=foundProduct.get();
		List<Product> allProducts=new ArrayList<>();
		for(int i=0;i<quantity;i++) {
			allProducts.add(getProduct);
		}
		Cart getCart=getCustomer.getCart();
		getCart.setProduct(allProducts);
		this.cartRepository.save(getCart);
		return true;
	}

	

	
	
	
	
	

}
