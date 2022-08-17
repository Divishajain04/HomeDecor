package com.homedecor.app.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.homedecor.app.dao.CartRepository;
import com.homedecor.app.dao.CustomerRepository;
import com.homedecor.app.dao.ProductRepository;
import com.homedecor.app.dto.Cart;
import com.homedecor.app.dto.Customer;
import com.homedecor.app.dto.Product;
import com.homedecor.app.exception.CartException;
import com.homedecor.app.exception.CustomerException;
import com.homedecor.app.exception.ProductException;

/************************************************************************************
 *   @author           Prince Verma
 *   Description       It is a service class that provides the services for adding a new cart, 
                       updating cart, view one cart by cartId, view all carts, delete a cart by
                       cartId, total amount of a particular customer cart, total products in
                       a particular customer cart and adding products to cart
 *   Version          1.0
 *   Created Date     16-AUG-2022
 ************************************************************************************/

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private ProductRepository productRepository;

	/************************************************************************************
	 * Method:                  - addCart
     * Description:             - To add a new cart into the database
	 * @param Cart              - Cart's object
	 * @returns Boolean         - true, if cart added otherwise throws CartException
	 * @throws CartException    - It is raised due to mandatory details are not filled or cart already exist
     * Created By               - Prince Verma
     * Created Date             - 16-AUG-2022                           
	 
	 ************************************************************************************/

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

	/************************************************************************************
	 * Method:                  - updateCart
     * Description:             - To update an existing cart details
	 * @param Cart              - Cart's object
	 * @returns Cart            - Cart, if cart updated otherwise throws CartException
	 * @throws CartException    - It is raised due to cart not exist for the id which we have to update
     * Created By               - Prince Verma
     * Created Date             - 16-AUG-2022                           
	 
	 ************************************************************************************/
	@Override
	public Cart updateCart(Cart cart) throws CartException {
		Optional<Cart> foundCart = this.cartRepository.findById(cart.getCartId());
		if (foundCart.isEmpty())
			throw new CartException("Cart does not exist for this id :- "+cart.getCartId());
		return this.cartRepository.save(cart);
	}

	/************************************************************************************
	 * Method:                  - getCartById
     * Description:             - View cart details through cart Id
	 * @param cartId            - cart's Id
	 * @returns Optional        - Present, if cart exist otherwise throws CartException
	 * @throws CartException    - It is raised due to cart not found in database
     * Created By               - Prince Verma
     * Created Date             - 16-AUG-2022                           
	 
	 ************************************************************************************/
	@Override
	public Optional<Cart> getCartById(Integer cartId) throws CartException {
		Optional<Cart> foundCart = this.cartRepository.findById(cartId);
		if (foundCart.isEmpty())
			throw new CartException("Cart does not exist for this id" + cartId);
		return foundCart;
	}

	/************************************************************************************
	 * Method:                  - getAllCarts
     * Description:             - View all carts and its details
	 * @returns List            - Present, if carts exist otherwise throws CartException
	 * @throws CartException    - It is raised due to carts not found in database
     * Created By               - Prince Verma
     * Created Date             - 16-AUG-2022                           
	 
	 ************************************************************************************/
	@Override
	public List<Cart> getAllCarts() throws CartException {
		List<Cart> foundCarts = this.cartRepository.findAll();
		if (foundCarts.isEmpty())
			throw new CartException("No carts found");
		return this.cartRepository.findAll();
	}

	/************************************************************************************
	 * Method:                  - deleteCartById
     * Description:             - Delete cart from the database through cart Id
	 * @param cartId            - cart's Id
	 * @returns Boolean         - true, if cart deleted otherwise throws CartException
	 * @throws CartException    - It is raised due to cart not found in database
     * Created By               - Prince Verma
     * Created Date             - 16-AUG-2022                           
	 
	 ************************************************************************************/
	@Override
	public Boolean deleteCartById(Integer cartId) throws CartException {
		Optional<Cart> foundCart = this.cartRepository.findById(cartId);
		if (foundCart.isEmpty())
			throw new CartException("Cart does not exist for this id " + cartId);
		this.cartRepository.deleteById(cartId);
		return true;
	}

	/************************************************************************************
	 * Method:                  - totalAmountOfCustomerCartById
     * Description:             - View total amount of customer's cart through cart Id
	 * @param cartId            - cart's Id
	 * @returns Optional        - Present, if cart exist otherwise throws CartException
	 * @throws CartException    - It is raised due to cart not found in database
     * Created By               - Prince Verma
     * Created Date             - 16-AUG-2022                           
	 
	 ************************************************************************************/
	@Override
	public Optional<Double> totalAmountOfCustomerCartById(Integer cartId) throws CartException {
		Optional<Cart> getCart=this.cartRepository.findById(cartId);
		if(getCart.isEmpty())throw new CartException("Cart does not exist for this id " + cartId);
		Cart foundCart=getCart.get();
		List<Product> products =	foundCart.getProduct();
			return  products.stream().map(i-> i.getProductPrice()).reduce((e1,e2)-> e1+e2);
	}

	/************************************************************************************
	 * Method:                  - totalProductInCustomerCartById
     * Description:             - View total product in customer's cart through cart Id
	 * @param cartId            - cart's Id
	 * @returns Long            - Total products, if cart exist otherwise throws CartException
	 * @throws CartException    - It is raised due to cart not found in database
     * Created By               - Prince Verma
     * Created Date             - 16-AUG-2022                           
	 
	 ************************************************************************************/
	@Override
	public Long totalProductInCustomerCartById(Integer cartId) throws CartException {
		Optional<Cart> getCart=this.cartRepository.findById(cartId);
		if(getCart.isEmpty())throw new CartException("Cart does not exist for this id " + cartId);
		Cart foundCart=getCart.get();
		List<Product> products =	foundCart.getProduct();
		return products.stream().map(i->i.getProductId()).count();
	}

	/************************************************************************************
	 * Method:                  - addProductTocart
     * Description:             - Add products to customers's cart
	 * @param customerId        - Customer's Id
	 * @param productId         - Product's Id
	 * @param quantity          - Total no of products
	 * @returns Boolean         - true, if products added to the cart otherwise throws CartException. CartException, CustomerException
	 * @throws CustomerException- It is raised due to Invalid customer's Id
	 * @throws CartException    - It is raised due to Cart not present in the record
	 * @throws ProductException - It is raised due to Invalid product's Id
     * Created By               - Prince Verma
     * Created Date             - 16-AUG-2022                           
	 
	 ************************************************************************************/
	@Override
	public Boolean addProductToCart(Integer customerId, Integer productId, Integer quantity) throws ProductException, CustomerException, CartException {
		Optional<Customer> foundCustomer=this.customerRepository.findById(customerId);
		if (foundCustomer.isEmpty()) throw new CustomerException("Invalid Customer Id");
		Optional<Product> foundProduct=this.productRepository.findById(productId);
		if (foundProduct.isEmpty()) 
			throw new ProductException("Invalid Product Id");
		Customer getCustomer=foundCustomer.get();
		Product getProduct=foundProduct.get();
		Cart getCart=getCustomer.getCart();
		if(getCart==null)throw new CartException("Cart not present in the record");
		List<Product> allProducts=new ArrayList<>();
		allProducts.addAll(getCart.getProduct());
		for(int i=0;i<quantity;i++) {
			allProducts.add(getProduct);
		}
		getCart.setProduct(allProducts);
		this.cartRepository.save(getCart);
		return true;
	}

	/************************************************************************************
	 * Method:                  - removeProductTocart
     * Description:             - Remove products to customers's cart
	 * @param customerId        - Customer's Id
	 * @param productId         - Product's Id
	 * @param quantity          - Total no of products
	 * @returns Boolean         - true, if products removed from the cart otherwise throws CartException, CustomerException, ProductException
	 * @throws CustomerException- It is raised due to Invalid customer's Id
	 * @throws CartException    - It is raised due to Cart not present in the record
	 * @throws ProductException - It is raised due to Invalid product's Id or product availability 
	                              in cart of given product Id is less then the quantity
     * Created By               - Prince Verma
     * Created Date             - 16-AUG-2022                           
	 
	 ************************************************************************************/
	@Override
	public Boolean removeProductFromCart(Integer customerId, Integer productId, Integer quantity)
			throws ProductException, CustomerException, CartException {
		Optional<Customer> foundCustomer=this.customerRepository.findById(customerId);
		if (foundCustomer.isEmpty()) throw new CustomerException("Invalid Customer Id");
		Optional<Product> foundProduct=this.productRepository.findById(productId);
		if (foundProduct.isEmpty()) 
			throw new ProductException("Invalid Product Id");
		Customer getCustomer=foundCustomer.get();
		Product getProduct=foundProduct.get();
		Cart getCart=getCustomer.getCart();
		if(getCart==null)throw new CartException("Cart not present in the record");
		List<Product> allProducts=new ArrayList<>();
		allProducts.addAll(getCart.getProduct());
		Boolean isremove=false;
		for(int i=0;i<quantity;i++) {
		 isremove=allProducts.remove(getProduct);
		}
		if(isremove==false)throw new ProductException(quantity+" product are not avilable in cart for product id "+productId);
		getCart.setProduct(allProducts);
		this.cartRepository.save(getCart);
		return true;

	}
	
}
