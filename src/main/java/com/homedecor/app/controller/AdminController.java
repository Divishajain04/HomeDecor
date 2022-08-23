package com.homedecor.app.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.homedecor.app.dto.Admin;
import com.homedecor.app.exception.AdminException;
import com.homedecor.app.service.AdminService;
/************************************************************************************
 *          @author          Lucky Rathore
 *          Description      It is a controller class which provides RESTFUl API's to handle different HTTP requests.
 *          Version          1.0
 *          Created Date     16-AUG-2022
 ************************************************************************************/


@RestController
public class AdminController {

	@Autowired
	private AdminService adminService;

	
	/************************************************************************************
	 * Method: addAdmin
     * Description: To handle a Http POST request
     * 
     * @Object admin                 - Admin's object
	 * @returns String               - Admin added Successfully
     * Created By                    - Lucky Rathore
     * Created Date                  - 16-AUG-2022                           
	 
	 ************************************************************************************/

	@PostMapping("admin")
	public String addAdmin(@Valid @RequestBody Admin admin) throws AdminException {
		this.adminService.addAdmin(admin);
		return "Admin added Successfully";
	}
	
	
	/************************************************************************************
	 * Method: getAdminById
     * Description: To handle a Http GET request
     * 
     * @Param adminId                - admin's id
	 * @returns Optional<Admin>      - foundAdmin     - 
     * Created By                    - Lucky Rathore
     * Created Date                  - 16-AUG-2022                           
	 
	 ************************************************************************************/

	@GetMapping("admin/{adminid}")
	public Optional<Admin> getAdminById(@PathVariable("adminid") Integer adminId) throws AdminException {
		 
		return this.adminService.getAdminById(adminId);
	}

	/************************************************************************************
	 * Method: deleteAdminById
     * Description: To handle a Http DELETE request
     * 
     * @Param adminId               - admin's id 
	 * @returns String              - Admin deleted Successfully
     * Created By                   - Lucky Rathore
     * Created Date                 - 16-AUG-2022                           
	 
	 ************************************************************************************/

	@DeleteMapping("admin/{adminid}")
	public String deleteAdminById(@PathVariable("adminid") Integer adminId) throws AdminException {
		this.adminService.deleteAdminById(adminId);
		return "Admin deleted SuccessFully";
	}

}
