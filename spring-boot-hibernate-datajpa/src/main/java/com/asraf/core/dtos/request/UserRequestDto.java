package com.asraf.core.dtos.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

import org.apache.catalina.startup.Tomcat.ExistingStandardWrapper;

import com.asraf.core.entities.User;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class UserRequestDto extends BaseRequestDto {
	
	@NotNull
	@Email
	private String email;
	
	@NotNull(groups = Existing.class)
	@Null(groups = New.class)
	@Size(min = 3, max = 10)
	private String name;
	
	public interface Existing{
		
	}
	
	public interface New{
		
	}
	
}
