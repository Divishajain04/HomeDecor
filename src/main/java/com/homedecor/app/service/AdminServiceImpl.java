package com.homedecor.app.service;



import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.homedecor.app.dao.AdminRepository;
import com.homedecor.app.dto.Admin;
import com.homedecor.app.exception.AdminException;

/************************************************************************************
 *          @author          Lucky Rathore
 *          Description      It is a service class that provides the services for adding a new admin, 
                             getting an admin by id, login into an existing admin account, updating admin password, delete admin by id.  
  *         Version          1.0
  *         Created Date     16-AUG-2022
 ************************************************************************************/



@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminRepository adminRepository;
	
	/************************************************************************************
	 * Method: addAdmin
     * Description: To register a new admin account in our app
     * @Object admin                 - adding admin by providing mandatory admin details
	 * @returns Boolean              - true, if admin added successfully otherwise throws AdminException
	 * @throws AdminException        - It is raised if admin Id is already registered in the app
     * Created By                    - Lucky Rathore
     * Created Date                  - 16-AUG-2022                           
	 
	 ************************************************************************************/

	@Override
	public Boolean addAdmin(Admin admin) throws AdminException {
		if (admin == null) {
			throw new AdminException("Admin not added please fill the mandatory feilds");
		}
			this.adminRepository.save(admin);
		return true;
	}

	/************************************************************************************
	 * Method: getAdminById
     * Description: To get an admin by AdminId
     * 
     * @param AdminId              - admin's Id
	 * @returns Boolean            - isPresent, if admin is already present on the given Id in our database otherwise throws AdminException
	 * @throws AdminException      - It is raised if admin Id is not present or we provide an invalid admin Id                                                   
     * Created By                  - Lucky Rathore
     * Created Date                - 16-AUG-2022                           
	 
	 ************************************************************************************/


	@Override
	public Optional<Admin> getAdminById(Integer adminId) throws AdminException {
		Optional<Admin> foundAdminById = this.adminRepository.findById(adminId);
		if (foundAdminById.isEmpty()) {
			throw new AdminException(adminId+" Admin Id is not present in the record");
		}
		return foundAdminById;
	}
	
	/************************************************************************************
	 * Method: Login
     * Description: To login into an existing admin account
     * 
	 * @param adminEmailId         - admin's Email 
	 * @param password             - admin's password
	 * @returns Boolean            - true, if Admin logged in successfully otherwise throws AdminException 
	 * @throws AdminException      - It is raised due to incorrect Admin Id or password                                                      
     * Created By                  - Lucky Rathore
     * Created Date                - 16-AUG-2022                         
	 
	 ************************************************************************************/

	
	@Override
	public Boolean login(String adminEmailId, String password) throws AdminException {
		Boolean isLoginBoolean = false;
		Optional<Admin> foundAdmin = this.adminRepository.findByAdminEmailIdAndAdminPassword(adminEmailId, password);
		if (foundAdmin.isEmpty()) {
			throw new AdminException("Invalid Id or password");
		} else {
			isLoginBoolean = true;
		}
		return isLoginBoolean;
	}
	
	/************************************************************************************
	 * Method: updatePassword
     * Description: To update an admin account password
     * 
	 * @param adminEmailId      - Admin's EmailId
	 * @param oldPassword       - old adminPassword
	 * @param newPassword       - new password to be updated
	 * @returns Boolean         - true, if password for that adminId updated successfully otherwise throws AdminException
	 * @throws AdminException   - It is raised if we provide invalid adminId or password                                                        
     * Created By               - Lucky Rathore
     * Created Date             - 16-AUG-2022                           
	 
	 ************************************************************************************/



	@Override
	public Boolean updatePassword(String adminEmailId, String oldPassword, String newPassword) throws AdminException {
		Optional<Admin> getAdmin=this.adminRepository.findByAdminEmailIdAndAdminPassword(adminEmailId, oldPassword);
		if(getAdmin.isEmpty())throw new AdminException("Invalid admin email id or password");
		Admin foundAdmin  = getAdmin.get();
			
			foundAdmin.setAdminPassword(newPassword);
			adminRepository.save(foundAdmin);
			
		return true;
	}
	

	/************************************************************************************
	 * Method: deleteAdminById
     * Description: To delete an existing admin by AdminId
     * 
     * @param AdminId              - admin's Id
	 * @returns Boolean            - true, if admin is present in our database otherwise throws AdminException
	 * @throws AdminException      - It is raised if admin Id is not present or we provide an invalid admin Id                                                   
     * Created By                  - Lucky Rathore
     * Created Date                - 16-AUG-2022                           
	 
	 ************************************************************************************/
	

	@Override
	public Boolean deleteAdminById(Integer adminId) throws AdminException {
		Optional<Admin> foundAdmin = this.adminRepository.findById(adminId);
		if (foundAdmin.isEmpty())throw new AdminException("Admin not exist for this Id "+adminId);
		this.adminRepository.deleteById(adminId);
		return true;
	}

}
