package com.erx.obj;


import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@Builder
public class Patient {
	private String id;
	private String name;
	private String phoneNumber;
}
