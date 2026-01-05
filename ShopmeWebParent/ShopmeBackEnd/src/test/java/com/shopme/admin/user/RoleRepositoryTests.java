package com.shopme.admin.user;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.shopme.common.entity.Role;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class RoleRepositoryTests {
	@Autowired
	private RoleRepository repo;
	@Test
	public void testCreateFirstRole() {
		Role roleAdmin = new Role("admin","manages everything");
		Role savedRole = repo.save(roleAdmin);
		assertThat(savedRole.getId()).isGreaterThan(0);
	}
	
	@Test
	public void testCreateRestRoles() {
		 List<Role> allRoles = new ArrayList<>();
		 Role roleSalesperson = new Role("SalesPerson","Manage product price, cutomers, shipping, orders and sales report");
		 allRoles.add(roleSalesperson);
		 Role  roleEditor = new Role("Editor","Manage categories, brands, products, aricles and menus");
		 allRoles.add(roleEditor);
		 Role roleShipper = new Role("Shipper","View products, view orders and update order status");
		 allRoles.add(roleShipper);
		 Role roleAssistant = new Role("Assistant", "Manage questions and reviews");
		 allRoles.add(roleAssistant);
		 Iterable<Role> savedRestRoles = repo.saveAll(allRoles);
		 for(Role r : savedRestRoles) {
			 assertThat(r.getId()).isGreaterThan(0);
		 }
	}
}
