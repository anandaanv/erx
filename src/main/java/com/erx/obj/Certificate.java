package com.erx.obj;


import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@Builder
public class Certificate {
	private User user;

	private byte[] certificate;
	
	private byte[] signImage;
	
}
