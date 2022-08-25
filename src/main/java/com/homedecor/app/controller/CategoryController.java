package com.homedecor.app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.homedecor.app.dto.Category;
import com.homedecor.app.exception.CategoryException;
import com.homedecor.app.service.CategoryService;

/************************************************************************************
 *          @author          Divisha Jain
 *          Description      It is a controller class which provides RESTFUl API's to handle different HTTP requests.
 *          Version          1.0
 *          Created Date     16-AUG-2022
 ************************************************************************************/

@RestController
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;

	/************************************************************************************
	 * Method: addCategory
     * Description: To handle a Http POST request
     * 
     * @Object category              - Category's object
	 * @returns String               - Category added Successfully
     * Created By                    - Divisha Jain
     * Created Date                  - 16-AUG-2022                           
	 
	 ************************************************************************************/

	@PostMapping("category")
	public String addCategory(@RequestBody Category category) throws CategoryException  {
			this.categoryService.addCategory(category);
		return "Category added Successfully";
	}

	/************************************************************************************
	 * Method: updateCategory
     * Description: To handle a Http PATCH request
     * 
     * @Object category              - Category's object
	 * @returns Category             - CategoryUpdated
     * Created By                    - Divisha Jain
     * Created Date                  - 16-AUG-2022                           
	 
	 ************************************************************************************/

	@PutMapping("category")
	public Category updateCategory(@RequestBody Category category) throws CategoryException {
		
		 return this.categoryService.updateCategory(category);
	}
	
	/************************************************************************************
	 * Method: getCategoryById
     * Description: To handle a Http GET request
     * 
     * @Param categoryId             - Category's Id
	 * @returns Optional<Category>   - foundCategory
     * Created By                    - Divisha Jain
     * Created Date                  - 16-AUG-2022                           
	 
	 ************************************************************************************/


	@GetMapping("category/{categoryid}")
	public Optional<Category> getCategoryById(@PathVariable ("categoryid") Integer categoryId) throws CategoryException  {
		 return this.categoryService.getCategoryById(categoryId);
	}
	
	/************************************************************************************
	 * Method: getAllCategory
     * Description: To handle a Http GET request
     * 
 	 * @returns List<Category>       - foundAllCategory
     * Created By                    - Divisha Jain
     * Created Date                  - 16-AUG-2022                           
	 
	 ************************************************************************************/

	@GetMapping("categories")
	public List<Category> getAllCategory() throws CategoryException {
		 return this.categoryService.getAllCategories();
	}
	
	/************************************************************************************
	 * Method: deleteCategoryById
     * Description: To handle a Http DELETE request
     * 
     * @Param categoryId             - Category's Id
	 * @returns String               - Category deleted Successfully
     * Created By                    - Divisha Jain
     * Created Date                  - 16-AUG-2022                           
	 
	 ************************************************************************************/

	@DeleteMapping("category/{categoryid}")
	public String deleteCategoryById(@PathVariable ("categoryid") Integer categoryId) throws CategoryException {
			this.categoryService.deleteCategoryById(categoryId);
		return "Category deleted Successfully";
	}
	
	/************************************************************************************
	 * Method: getCategoryByName
     * Description: To handle a Http GET request
     * 
     * @Param categoryName           - Category's Name
	 * @returns Category             - foundCategory
     * Created By                    - Divisha Jain
     * Created Date                  - 16-AUG-2022                           
	 
	 ************************************************************************************/

	@GetMapping("category/name/{categoryname}")
	public List<Category> getCategoryByName(@PathVariable ("categoryname") String categoryName) throws CategoryException  {
		 return this.categoryService.getCategoryByName(categoryName);
	}

}
