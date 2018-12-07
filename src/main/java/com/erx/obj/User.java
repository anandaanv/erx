package com.erx.obj;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	private String name;

	private String encryptedPassword;

	private String status;
	
	private String email;
	
	private boolean enabled;
	
	private String phoneNumber;
	
	private String userId;

}