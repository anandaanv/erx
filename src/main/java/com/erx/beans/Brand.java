package com.erx.beans;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Brand extends BaseBean{
	private String brandName;

    @Override
	public String toString() {
		return "Brand [brandName=" + brandName + ", id=" + id + "]";
	}
	
}
