package edu.poly.model;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {
	@NotEmpty
	private String username;
	@NotEmpty
	private String password;
	
	private Boolean isEdit = false;

//	public void setIsEdit(boolean b) {
//		// TODO Auto-generated method stub
//		
//	}

}
