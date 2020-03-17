package br.com.compasso.user.domain;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import br.com.compasso.user.domain.dto.UserDTO;

@Service
public class UserService {

	@Autowired
	private UserRepository rep;

	public List<UserDTO> getUsers() {
		return rep.findAll().stream().map(UserDTO::create).collect(Collectors.toList());
	}

	public Optional<UserDTO> getUserById(Long id) {
		return rep.findById(id).map(UserDTO::create);
	}

	public List<User> getUserByName(String name) {
		return rep.findByName(name);
		
	}

	public List<UserDTO> name(String name) {
		return rep.findByName(name).stream().map(UserDTO::create).collect(Collectors.toList());
	}
	
	public UserDTO insert(User user) {
		Assert.isNull(user.getId(), "não foi possivel atualizar o registro");
		return UserDTO.create(rep.save(user));
	}

	public UserDTO update(User user, Long id) {
		Assert.notNull(id, "Não foi possivel atualizar");

		Optional<User> optional = rep.findById(id);
		if (optional.isPresent()) {
			User db = optional.get();
			db.setName(user.getName());
			db.setEmail(user.getEmail());
			db.setPhone(user.getPhone());
			db.setGender(user.getGender());
			db.setBirthday(user.getBirthday());


			System.out.println("User id " + db.getId());
			rep.save(db);
			return UserDTO.create(db);
		} else {
			throw new RuntimeException("Não foi possivel registro");
		}

//		getUserById(id).map(db-> {
//			
//			db.setNome(user.getNome());
//			db.setEmail(user.getEmail());
//			db.setLogin(user.getLogin());
//			System.out.println("User id " + db.getId());
//			rep.save(db);
//			return db
//		}).orElseThrow((-> new RuntimeException("dasd")));
	}

	public boolean delete(Long id) {
		if (getUserById(id).isPresent()) {
			rep.deleteById(id);
			return true;
		}
		return false;
	}


}
