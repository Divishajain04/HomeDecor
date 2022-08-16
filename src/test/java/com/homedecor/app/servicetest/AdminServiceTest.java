package com.homedecor.app.servicetest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.homedecor.app.dto.Admin;
import com.homedecor.app.exception.AdminException;
import com.homedecor.app.service.AdminService;

@SpringBootTest
 class AdminServiceTest {

	@Autowired
	private AdminService adminService;
	
	@Test
	void deleteAdminByIdTest() throws AdminException {
		Admin admin=new Admin(8,"Lucky","lucky@gmail.com","luck");
		assertTrue(adminService.addAdmin(admin));
		assertEquals(true,adminService.deleteAdminById(8));
		assertThrows(AdminException.class,()->this.adminService.deleteAdminById(8));
	}
	
	@Test
	void addAdminTest() throws AdminException {
		assertThrows(AdminException.class,()->this.adminService.addAdmin(null));
		Admin admin=new Admin(8,"Lucky","lucky@gmail.com","luck");
		assertTrue(adminService.addAdmin(admin));
		assertNotNull(adminService);
		assertThrows(AdminException.class,()->this.adminService.addAdmin(admin));
		assertEquals(true,adminService.deleteAdminById(8));
		
	}
	
	@Test
	void getAdminByIdTest() throws AdminException {
		Admin admin=new Admin(8,"Lucky","lucky@gmail.com","luck");
		assertTrue(adminService.addAdmin(admin));
		Admin admi=adminService.getAdminById(8).get();
		Integer id=admi.getAdminId();
		assertEquals(8,id);
		assertNotNull(adminService.getAdminById(id));
		assertEquals(true,adminService.deleteAdminById(8));
		assertThrows(AdminException.class,()->this.adminService.getAdminById(8));

	}
	
	@Test
	void loginTest() throws AdminException {
		Admin admin=new Admin(8,"Lucky","lucky@gmail.com","luck");
		assertTrue(adminService.addAdmin(admin));
		Admin admi=adminService.getAdminById(8).get();
		String emailId=admi.getAdminPassword();
		String savePassword=admi.getAdminPassword();
		assertTrue(adminService.login(emailId, savePassword));
		assertEquals(true,adminService.deleteAdminById(8));
		assertThrows(AdminException.class,()->this.adminService.login(emailId, "luck"));

	}
	
	@Test
	void updatePasswordTest() throws AdminException {
		Admin admin=new Admin(8,"Lucky","lucky@gmail.com","luck");
		assertTrue(adminService.addAdmin(admin));
		Admin admi=this.adminService.getAdminById(8).get();
		String emailId=admi.getAdminPassword();
		String pass=admi.getAdminPassword();
		assertEquals(true,adminService.updatePassword(emailId,pass,"luck008"));
		assertEquals(true,adminService.deleteAdminById(8));
	
	
	}

}
