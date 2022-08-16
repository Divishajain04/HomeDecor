package com.homedecor.app.service;



import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.homedecor.app.dao.AdminRepository;
import com.homedecor.app.dto.Admin;
import com.homedecor.app.exception.AdminException;


@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminRepository adminRepository;

	@Override
	public Boolean addAdmin(Admin admin) throws AdminException {
		if (admin == null) {
			throw new AdminException("Admin not added please fill the mandatory feilds");
		}
			this.adminRepository.save(admin);
		return true;
	}

	@Override
	public Optional<Admin> getAdminById(Integer adminId) throws AdminException {
		Optional<Admin> foundAdminById = this.adminRepository.findById(adminId);
		if (foundAdminById.isEmpty()) {
			throw new AdminException(adminId+" Admin Id is not present in the record");
		}
		return foundAdminById;
	}

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

	@Override
	public Boolean updatePassword(String adminEmailId, String oldPassword, String newPassword) throws AdminException {
		Optional<Admin> getAdmin=this.adminRepository.findByAdminEmailIdAndAdminPassword(adminEmailId, oldPassword);
		if(getAdmin.isEmpty())throw new AdminException("Invalid admin email id or password");
		Admin foundAdmin  = getAdmin.get();
			foundAdmin.getAdminPassword().replaceAll(oldPassword, newPassword);
			foundAdmin.setAdminPassword(newPassword);
			adminRepository.save(foundAdmin);
			
		return true;
	}

	@Override
	public Boolean deleteAdminById(Integer adminId) throws AdminException {
		Optional<Admin> foundAdmin = this.adminRepository.findById(adminId);
		if (foundAdmin.isEmpty())throw new AdminException("Admin not exist for this Id "+adminId);
		this.adminRepository.deleteById(adminId);
		return true;
	}

}
