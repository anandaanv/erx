package com.erx.beans;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Builder
public class Brand extends BaseBean{
	long id;
	private String brandName;

    @Override
	public String toString() {
		return "Brand [brandName=" + brandName + ", id=" + id + "]";
	}
	
}
