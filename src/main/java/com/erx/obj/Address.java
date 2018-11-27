package com.erx.obj;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@Builder
public class Address {
	private int id;
	private String name;
	private String altName;
	private String address1;
	private String address2;
	private String address3;
	private String address4;
	private String landmark;
	private String city;
	private String zipcode;
	private String state;
	private String country = "INDIA";
}
