package com.homedecor.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.homedecor.app.dao.CategoryRepository;
import com.homedecor.app.dto.Category;
import com.homedecor.app.exception.CategoryException;


/************************************************************************************
 * @author 		-   Divisha Jain 
 * Description: -	 It is a service class that provides the
 * 		        	services for adding a new category, delete category, information of
 *         			catgeory by Id, details of all categories and serach category by their name.
 * @version       - 1.0        
 ************************************************************************************/

@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private CategoryRepository categoryRepository;

	/************************************************************************************
	 * Method:			    	- 	Add category
	 *Description: 				-	Registered Category in the Home Decor Application.
	 * @object category 		-	Category detail
	 * @returns Boolean  		-	true, if category registered otherwise throws Category Exception
	 * @throws CategoryException- 	It is raised due to category details are invalid,
	 *                           	or category id is not present.                         
	 * Created By 	 			-	Divisha Jain
	 ************************************************************************************/
	@Override
	public Boolean addCategory(Category category) throws CategoryException {
		if(category==null)throw new CategoryException("Category not added please fill the mandatory details");
     	Optional<Category> foundCategory=this.categoryRepository.findById(category.getCategoryId());
		if(foundCategory.isPresent()) 
			throw new CategoryException("Category already exist");
		
		this.categoryRepository.save(category);
		return true;
	}

	/************************************************************************************
	 * Method:                       -	getCategoryById
	 * Description: 				 -	To get the detail of particular category by category Id.
	 * @param categoryId  			 -	CategoryId
	 * @returns Optional  			 -	Present, if detail exist otherwise throws Category Exception
	 * @throws CategoryException  	 -	It is raised due to invalid category Id.
	 * Created By  					 -	Divisha Jain
	 ************************************************************************************/
	@Override
	public Optional<Category> getCategoryById(Integer categoryId) throws CategoryException {
		Optional<Category> foundCategory=this.categoryRepository.findById(categoryId);
		if(foundCategory.isEmpty())throw new CategoryException("Category not exist for this Id"+categoryId);
		return this.categoryRepository.findById(categoryId);
	}

	/************************************************************************************
	 * Method 					 -	updateCategory
	 * Description	 			 -	To update the category's detail.
	 * @returns Category		 -	category, if detail update otherwise throws Category Exception
	 * @throws CategoryException - 	It is raised if category's Id is not present in the database.
	 * Created By 				 - 	Divisha Jain
	 ************************************************************************************/
	@Override
	public Category updateCategory(Category category) throws CategoryException {
     	Optional<Category> foundCategory=this.categoryRepository.findById(category.getCategoryId());
		if(foundCategory.isEmpty())throw new CategoryException("Category not found can't update");
		return this.categoryRepository.save(category);
	}
	
	
	/************************************************************************************
	 * Method:					 -	Delete Category
	 * Description: 			 -	to delete the category from record. 
	 * @param categoryId  		 -	Category Id
	 * @returns Optional  		 -	Present, if delete otherwise throws CategoryException
	 * @throws CategoryException - It is raised due to invalid category Id.
	 * Created By 				 -	Divisha Jain
	 ************************************************************************************/
	@Override
	public Boolean deleteCategoryById(Integer categoryId) throws CategoryException {
		Optional<Category> foundCategory=this.categoryRepository.findById(categoryId);
		if(foundCategory.isEmpty())throw new CategoryException("Category not exist for this Id"+categoryId);
		this.categoryRepository.deleteById(categoryId);
		return true;
	}

	
	/************************************************************************************
	 * Method: 					 -	getAllCategories
	 * Description: 			 -	To get the details of all categories.
	 * @returns List - 			 -	allCategories, if details exist otherwise throws CategoryException
	 * @throws CategoryException - 	It is raised if the database is empty or no one registered
	 * 								in the application.
	 * Created By  				 -	Divisha Jain
	 ************************************************************************************/
	@Override
	public List<Category> getAllCategories() throws CategoryException {
		List<Category> foundCategories=this.categoryRepository.findAll();
		if(foundCategories.isEmpty())throw new CategoryException("No Categories found");
		return this.categoryRepository.findAll();
	}
	
	/************************************************************************************
	 * Method: 					 -	getAllCategoryByName
	 * Description: 			 -	To get the details of all categories according to their name.
	 * @returns List - 			 -	allCategories, if details exist otherwise throws CategoryException
	 * @throws CategoryException - 	It is raised if the database is empty or no one registered
	 * 								in the application.
	 * Created By  				 -	Divisha Jain
	 ************************************************************************************/
	@Override
	public List<Category> getCategoryByName(String categoryName) throws CategoryException {
		List<Category> foundCategory=this.categoryRepository.findByCategoryNameStartingWith(categoryName);
		if(foundCategory==null)throw new CategoryException("No Category found by this name");
		return foundCategory;
	}

	
}
