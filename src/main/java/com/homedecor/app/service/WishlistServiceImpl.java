package com.homedecor.app.service;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.homedecor.app.dao.CartRepository;
import com.homedecor.app.dao.CustomerRepository;
import com.homedecor.app.dao.WishlistRepository;
import com.homedecor.app.dto.Cart;
import com.homedecor.app.dto.Customer;
import com.homedecor.app.dto.Product;
import com.homedecor.app.dto.Wishlist;
import com.homedecor.app.exception.CartException;
import com.homedecor.app.exception.CustomerException;
import com.homedecor.app.exception.WishlistException;

/************************************************************************************
 * @author      Nikhil Narwat 
 * Description: It is a service class that provides the
 *              services for adding, deleting, updating, get all wishlist's and get
 *              wishlist by id. 
 * Version      1.0 
 * Created Date 16-08-2022
 ************************************************************************************/

@Service
public class WishlistServiceImpl implements WishlistService {

	@Autowired
	private WishlistRepository wishlistRepository;

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private CustomerRepository customerRepository;

	/************************************************************************************
	 * Method: addWishlist 
	 * Description: To add a wishlist
	 * 
	 * @object wishlist          - Adding wishlist by providing proper wishlist details.
	 * @returns Boolean          - true, if wishlist is added otherwise throws WishlistException
	 * @throws WishlistException - It is raised if the wishlist id is already present.
	 * By                        - Nikhil Narwat 
	 * Created Date              - 16-08-2022
	 ************************************************************************************/

	@Override
	public Boolean addWishlist(Wishlist wishlist) throws WishlistException {
		if (wishlist == null) {
			throw new WishlistException("Wishlist is not added!Please fill the mandatory field.");
		}
		Optional<Wishlist> addNewWishlist = this.wishlistRepository.findById(wishlist.getWishlistId());
		if (addNewWishlist.isPresent()) {
			throw new WishlistException("Wishlist Id is already present");
		} else {
			this.wishlistRepository.save(wishlist);
		}
		return true;
	}

	/************************************************************************************
	 * Method: getWishlistById 
	 * Description: To fetch a wishlist by it's id.
	 * 
	 * @param wishlistId         - wishlistId
	 * @returns Optional         - present, if wishlist is already present in the database otherwise throws WishlistException
	 * @throws WishlistException - It is raised if the wishlist id is not present or if we provide invalid wishlistId.
	 * By                        - Nikhil Narwat 
	 * Created Date              - 16-08-2022
	 ************************************************************************************/
	
	@Override
	public Optional<Wishlist> getWishlistById(Integer wishlistId) throws WishlistException {
		Optional<Wishlist> findWishlist = this.wishlistRepository.findById(wishlistId);
		if (findWishlist.isEmpty()) {
			throw new WishlistException("This wishlist Id is not present in the record");
		}
		return findWishlist;
	}

	/************************************************************************************
	 * Method: getAllWishlists
	 * Description: To fetch every wishlist from the record.
	 * 
	 * @returns List             - wishlists list, if wishlist is already present in the database otherwise throws WishlistException
	 * @throws WishlistException - It is raised if the wishlist table is empty.
	 * By                        - Nikhil Narwat 
	 * Created Date              - 16-08-2022
	 ************************************************************************************/
	
	@Override
	public List<Wishlist> getAllWishlists() throws WishlistException {
		List<Wishlist> getList = this.wishlistRepository.findAll();
		if (getList.isEmpty()) {
			throw new WishlistException("Wishlist is Empty");
		}
		return getList;
	}

	/************************************************************************************
	 * Method: getAllWishlists
	 * Description: To fetch every wishlist from the record.
	 * 
	 * @returns List             - wishlists list, if wishlist is already present in the database otherwise throws WishlistException
	 * @throws WishlistException - It is raised if the wishlist table is empty.
	 * By                        - Nikhil Narwat 
	 * Created Date              - 16-08-2022
	 ************************************************************************************/
	
	@Override
	public Boolean deleteWishlistById(Integer wishlistId) throws WishlistException {
		Optional<Wishlist> findWishlist = this.wishlistRepository.findById(wishlistId);
		if (findWishlist.isEmpty()) {
			throw new WishlistException("Wishlist Id is not present in the record.");
		} else {
			this.wishlistRepository.deleteById(wishlistId);
		}
		return true;
	}

	@Override
	public Wishlist updateWishlist(Wishlist wishlist) throws WishlistException {
		Optional<Wishlist> findWishlist = this.wishlistRepository.findById(wishlist.getWishlistId());
		if (findWishlist.isEmpty()) {
			throw new WishlistException("Wishlist Id doesn't exist");
		} else {
			this.wishlistRepository.save(wishlist);
		}
		return wishlist;
	}

	/************************************************************************************
	 * Method: addWishlistProductToCart
	 * Description: To add products from wishlist to cart using customer id.
	 * 
	 * @returns Boolean          - true, if customer, cart and wishlist are present otherwise throws CustomerException, WishlistException, CartException
	 * @throws CustomerException - It is raised if invalid customer Id
	 * @throws CartException     - It is raised if cart not found
	 * @throws WishlistException - It is raised if wishlist not found
	 * By                        - Nikhil Narwat 
	 * Created Date              - 16-08-2022
	 ************************************************************************************/
	
	@Override
	public Boolean addWishlistProductToCart(Integer customerId) throws CustomerException, WishlistException, CartException {

		Optional<Customer> foundCustomer = this.customerRepository.findById(customerId);
		if (foundCustomer.isEmpty())
			throw new CustomerException("Invalid Customer Id");

		Customer getCustomer = foundCustomer.get();

		Wishlist getWishlist = getCustomer.getWishlist();
		if (getWishlist == null) {
			throw new WishlistException("Wishlist not found.");
		}

		List<Product> getProducts = getWishlist.getProduct();

		Cart getCart = getCustomer.getCart();
		if(getCart == null) {
			throw new CartException("Cart not found.");
		}
		getCart.setProduct(getProducts);

		getWishlist.setProduct(null);

		this.wishlistRepository.save(getWishlist);

		this.cartRepository.save(getCart);

		this.customerRepository.save(getCustomer);

		return true;
	}

}
