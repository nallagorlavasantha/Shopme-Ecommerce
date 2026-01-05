package com.shopme.admin.user;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.shopme.common.entity.Role;
import com.shopme.common.entity.User;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private TestEntityManager entityManager;
	
	@Disabled
	@Test
	public void testCreateUserWithOneRole() {
		Role adminRole = entityManager.find(Role.class, 1);
		User userVasantha = new User("vasantha@gmail.com","vasu2024","vasantha","nallagorla");
		//set a role to vasantha
		userVasantha.addRole(adminRole);
		User savedUser = userRepo.save(userVasantha);
		assertThat(savedUser.getId()).isGreaterThan(0);
	}
	
	@Disabled
	@Test
	public void testCreateUserWithTwoRoles() {
		Role roleEditor = new Role(3);
		Role roleAssistant = new Role(5);
		User userNavya = new User("navya@gmail.com", "navi2023", "Navya", "Dhana");
		userNavya.addRole(roleAssistant);
		userNavya.addRole(roleEditor);
		User savedUser = userRepo.save(userNavya);
		assertThat(savedUser.getId()).isGreaterThan(0);
	}
	
	@Disabled
	@Test
	public void testListAllUsers() {
		Iterable<User> allUsers = userRepo.findAll();
		allUsers.forEach(user -> System.out.println(user));
	}
	
	@Disabled
	@Test
	public void testFindUserById() {
		User user = userRepo.findById(1).get();
		System.out.println(user);
		assertThat(user).isNotNull();
	}
	
	@Test
	public void testUpdateUserDetails() {
		User userNavya = userRepo.findById(5).get();
		userNavya.setEnabled(true);
		userNavya.setEmail("navi12@gmail.com");
		
		userRepo.save(userNavya);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
