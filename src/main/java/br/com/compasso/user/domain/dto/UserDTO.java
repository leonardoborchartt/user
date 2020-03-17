package br.com.compasso.user.domain.dto;

import java.time.LocalDate;

import org.modelmapper.ModelMapper;

import br.com.compasso.user.domain.User;
import lombok.Data;

@Data
public class UserDTO {

	private Long id;
	private String name;
	private String username;
	private String email;
	private String phone;
	private String gender;
	private LocalDate birthday;
	private int age;
	

	/*public UserDTO create(User user) {
    this.id = user.getId();
   }*/
	
	public static UserDTO create(User user) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(user, UserDTO.class);
	}

}
