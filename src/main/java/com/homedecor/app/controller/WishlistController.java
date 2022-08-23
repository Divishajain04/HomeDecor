package com.homedecor.app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.homedecor.app.dto.Wishlist;
import com.homedecor.app.exception.CartException;
import com.homedecor.app.exception.CustomerException;
import com.homedecor.app.exception.WishlistException;
import com.homedecor.app.service.WishlistServiceImpl;

/************************************************************************************
 *          @author          Nikhil Narwat
 *          Description      It is a controller class which provides RESTFUl API's to handle different HTTP requests.
 *          Version          1.0
 *          Created Date     16-AUG-2022
 ************************************************************************************/

@RestController
public class WishlistController {

	@Autowired
	WishlistServiceImpl wishlistServiceImpl;

	/************************************************************************************
	 * Method: addProductInWishlist
     * Description: To add product in the wishlist.
     * 
     * @Object wishlist              - Wishlist's object
	 * @returns Boolean              - true.
     * Created By                    - Nikhil Narwat
     * Created Date                  - 16-AUG-2022                           
	 
	 ************************************************************************************/
	
	@PostMapping("wishlist")
	public String addProductInWishlist(@RequestBody Wishlist wishlist) throws WishlistException {
		this.wishlistServiceImpl.addWishlist(wishlist);
		return "Wishlist added Successfully";
	}
	
	/************************************************************************************
	 * Method: getWishlistById
     * Description: To fetch a wishlist by it's id.
     * 
     * @Param wishlistId             - Wishlist's id
	 * @returns Optional<Wishlist>   - Wishlist with same id.
     * Created By                    - Nikhil Narwat
     * Created Date                  - 16-AUG-2022                           
	 
	 ************************************************************************************/
	
	@GetMapping("wishlist/{wishlistid}")
	public Optional<Wishlist> getWishlistById(@PathVariable("wishlistid") Integer wishlistId) throws WishlistException {
		return this.wishlistServiceImpl.getWishlistById(wishlistId);
	}
	
	/************************************************************************************
	 * Method: updateWishlist
     * Description: To update an existing wishlist.
     * 
     * @Object wishlist              - Wishlist's object
	 * @returns String               - Wishlist Updated Successfully.
     * Created By                    - Nikhil Narwat
     * Created Date                  - 16-AUG-2022                           
	 
	 ************************************************************************************/
	
	@PatchMapping("wishlist/product")
	public String updateWishlist(@RequestBody Wishlist wishlist) throws WishlistException {
		this.wishlistServiceImpl.updateWishlist(wishlist);
		return "Wishlist Updated Successfully";
	}
	
	/************************************************************************************
	 * Method: getAllWishlist
     * Description: To fetch every wishlist from the record.
     * 
     * @returns List<Wishlist>       - List of wishlists present in the database.
     * Created By                    - Nikhil Narwat
     * Created Date                  - 16-AUG-2022                           
	 
	 ************************************************************************************/
	
	@GetMapping("wishlists")
	public List<Wishlist> getAllWishlist() throws WishlistException {
		return this.wishlistServiceImpl.getAllWishlists();
	}
	
	/************************************************************************************
	 * Method: deleteWishlistById
     * Description: To delete a wishlist by it's id.
     * 
     * @Param wishlistId             - Wishlist's id
	 * @returns String               - Wishlist Deleted Successfully.
     * Created By                    - Nikhil Narwat
     * Created Date                  - 16-AUG-2022                           
	 
	 ************************************************************************************/
	
	@DeleteMapping("wishlist/{wishlistid}")
	public String deleteWishlistById(@PathVariable("wishlistid") Integer wishlistId) throws WishlistException {
		this.wishlistServiceImpl.deleteWishlistById(wishlistId);
		return "Wishlist Deleted Successfully";
	}
	
	/************************************************************************************
	 * Method: addProductToCart
	 * Description: To add products from wishlist to cart using customer's id.
	 * 
	 * @param customerId         - Customer's Id.
	 * @returns String           - Product added to Cart from Wishlist Successfully.
	 * By                        - Nikhil Narwat 
	 * Created Date              - 16-08-2022
	 ************************************************************************************/
	
	@PatchMapping("wishlist/product/cart/{customerid}")
	public String addProductToCart(@PathVariable("customerid") Integer customerId)
			throws CustomerException, WishlistException, CartException {
		this.wishlistServiceImpl.addWishlistProductToCart(customerId);
		return "Product added to Cart from Wishlist Successfully";
	}

}
