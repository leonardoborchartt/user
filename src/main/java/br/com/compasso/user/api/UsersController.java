package br.com.compasso.user.api;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.compasso.user.domain.User;
import br.com.compasso.user.domain.UserService;
import br.com.compasso.user.domain.dto.UserDTO;

@RestController
@RequestMapping("/api/v1/users")
public class UsersController {
	@Autowired
	private UserService service;

	@GetMapping()
	public ResponseEntity<List<UserDTO>> get() {

		return ResponseEntity.ok(service.getUsers());
	}

	@GetMapping("/name")
	public ResponseEntity search(@RequestParam("name") String name) {
		List<UserDTO> users = service.name(name);
		return users.isEmpty() ?
				ResponseEntity.noContent().build() :
				ResponseEntity.ok(users);
	}

	@GetMapping("/{id}")
	public ResponseEntity getId(@PathVariable("id") Long id) {
		Optional<UserDTO> user = service.getUserById(id);
		return user.isPresent() ? 
				ResponseEntity.ok(user.get()) :
				ResponseEntity.notFound().build();

	}

	@PostMapping
	public ResponseEntity post(@RequestBody User user) {
		try {
			UserDTO u = service.insert(user);
			URI location = getUri(u.getId());
			return ResponseEntity.created(location).build();
		} catch (Exception ex) {
			return ResponseEntity.badRequest().build();
		}
	}

	private URI getUri(Long id) {
		return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
	}

	@PutMapping("/{id}")
	public ResponseEntity put(@PathVariable("id") Long id, @RequestBody User user) {
//		try {
		user.setId(id);
		UserDTO u = service.update(user, id);
		return u != null ? ResponseEntity.ok(u) : ResponseEntity.notFound().build();

//		} catch (Exception e) {
//			 return ResponseEntity.badRequest().build();
//		}

	}

	@DeleteMapping("/{id}")
	public ResponseEntity delete(@PathVariable("id") Long id) {
		boolean ok = service.delete(id);
		return ok ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
	}

}
