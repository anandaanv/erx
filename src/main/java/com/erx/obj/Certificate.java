package com.erx.obj;


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
public class Certificate {
	private User user;

	private byte[] certificate;
	
	private byte[] signImage;
	
}
