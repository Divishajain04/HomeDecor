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
		Admin admin=new Admin(111,"Lucky","lucky@gmail.com","lucky@0888");
		assertTrue(adminService.addAdmin(admin));
		assertEquals(true,adminService.deleteAdminById(111));
		assertThrows(AdminException.class,()->this.adminService.deleteAdminById(111));
	}
	
	@Test
	void addAdminTest() throws AdminException {
		assertThrows(AdminException.class,()->this.adminService.addAdmin(null));
		Admin admin=new Admin(111,"Lucky","lucky@gmail.com","lucky@0888");
		assertTrue(adminService.addAdmin(admin));
		assertNotNull(adminService);
		assertEquals(true,adminService.deleteAdminById(111));
		
	}
	
	@Test
	void getAdminByIdTest() throws AdminException {
		Admin admin=new Admin(111,"Lucky","lucky@gmail.com","lucky@0888");
		assertTrue(adminService.addAdmin(admin));
		Admin admi=adminService.getAdminById(111).get();
		Integer id=admi.getAdminId();
		assertEquals(111,id);
		assertNotNull(adminService.getAdminById(id));
		assertEquals(true,adminService.deleteAdminById(111));
		assertThrows(AdminException.class,()->this.adminService.getAdminById(111));

	}
	
	@Test
	void loginTest() throws AdminException {
		Admin admin=new Admin(111,"Lucky","lucky@gmail.com","lucky@0888");
		assertTrue(adminService.addAdmin(admin));
		Admin admi=adminService.getAdminById(111).get();
		String emailId=admi.getAdminEmailId();
		String savePassword=admi.getAdminPassword();
		assertTrue(adminService.login(emailId, savePassword));
		assertEquals(true,adminService.deleteAdminById(111));
		assertThrows(AdminException.class,()->this.adminService.login(emailId, "lucky@0888"));

	}
	
	@Test
	void updatePasswordTest() throws AdminException {
		Admin admin=new Admin(111,"Lucky","lucky@gmail.com","lucky@0888");
		assertTrue(adminService.addAdmin(admin));
		Admin admi=this.adminService.getAdminById(111).get();
		String emailId=admi.getAdminEmailId();
		String pass=admi.getAdminPassword();
		assertEquals(true,adminService.updatePassword(emailId,pass,"luck00888"));
		assertEquals(true,adminService.deleteAdminById(111));
	
	
	}

}
