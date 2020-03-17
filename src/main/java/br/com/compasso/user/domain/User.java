package br.com.compasso.user.domain;



import java.time.LocalDate;
import java.time.Period;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

import lombok.Data;


@Entity
@Data
public class User {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String username;
	private String email;
	private String phone;
	@Pattern(regexp = "^[M|F]{1}$", message ="Must be M or F")
	private String gender; 
	private LocalDate birthday;
	
	public int getAge() {
		return Period.between(getBirthday(), LocalDate.now()).getYears();
	}
}
