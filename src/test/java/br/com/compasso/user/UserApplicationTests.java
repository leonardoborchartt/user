package br.com.compasso.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static junit.framework.TestCase.*;

import java.util.List;
import java.util.Optional;

import br.com.compasso.user.domain.User;
import br.com.compasso.user.domain.UserService;
import br.com.compasso.user.domain.dto.UserDTO;


@SpringBootTest
class UserApplicationTests {

	@Autowired
	private UserService service;
	
	@Test
	public void testeSave() {
		User user = new User();
		user.setName("joao");
		user.setEmail("joao@hotmail.com");
		user.setUsername("jo");
		UserDTO u = service.insert(user);
		
		assertNotNull(u);
		Long id = u.getId();
		assertNotNull(id);
		//buscar o objeto
		Optional<UserDTO> op = service.getUserById(id);
		assertTrue(op.isPresent());
		
		u = op.get();
		assertEquals("joao", u.getName());
		assertEquals("joao@hotmail.com", u.getEmail());
		assertEquals("jo", u.getUsername());
		
		//deleta o usuario
		service.delete(id);
		
		///verifica se deletou
		assertFalse(service.getUserById(id).isPresent());		
		
	}
	
	@Test
	public void testLista() {
		List<UserDTO> users = service.getUsers();
		assertEquals(7, users.size());
	}
	
	
	@Test
	public void testGet() {
		Optional<UserDTO> op = service.getUserById(3L);
		assertTrue(op.isPresent());
		UserDTO c = op.get();
		assertEquals("User", c.getName());
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	 
}
