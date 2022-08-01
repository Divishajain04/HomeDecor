package com.example.homedecor.servicetest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.homedecor.dto.Admin;
import com.example.homedecor.exception.AdminException;
import com.example.homedecor.service.AdminService;

@SpringBootTest
public class AdminServiceTest {

	@Autowired
	private AdminService adminService;
	
	@AfterEach
	void deleteAdminByIdTest() throws AdminException {
		assertEquals(true,adminService.deleteAdminById(2));
	}
	
	@Test
	void addAdminTest() throws AdminException {
		Admin admin=new Admin(2,"Prince","prince");
		assertTrue(adminService.addAdmin(admin));
		assertNotNull(adminService != null);
	}
	
	@Test
	void getAdminByIdTest() throws AdminException {
		Admin admin=new Admin(2,"Prince","prince");
		assertTrue(adminService.addAdmin(admin));
		Admin admi=adminService.getAdminById(2).get();
		Integer id=admi.getAdminID();
		assertEquals(id,2);
		assertNotNull(adminService.getAdminById(id));
	}
	
	@Test
	void loginTest() throws AdminException {
		Admin admin=new Admin(2,"Prince","prince");
		assertTrue(adminService.addAdmin(admin));
		Admin admi=adminService.getAdminById(2).get();
		Integer id=admi.getAdminID();
		String savePassword=admi.getAdminPassword();
		
		assertTrue(adminService.login(id, savePassword));
	}
	
	@Test
	void updatePasswordTest() throws AdminException {
		Admin admin=new Admin(2,"Prince","prince");
		assertTrue(adminService.addAdmin(admin));
		Admin admi=this.adminService.getAdminById(2).get();
		Integer id=admi.getAdminID();
		String pass=admi.getAdminPassword();
		assertEquals(true,adminService.updatePassword(id,pass,"princever"));
	}

}
