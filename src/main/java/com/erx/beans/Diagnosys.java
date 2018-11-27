package com.erx.beans;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode
@Getter
@Setter
@AllArgsConstructor
public class Diagnosys extends BaseBean {
	private String name;

	public Diagnosys(int id, String name) {
		super(id);
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}

}
