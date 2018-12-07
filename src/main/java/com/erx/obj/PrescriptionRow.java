package com.erx.obj;

import com.erx.beans.Medicine;
import java.util.Set;
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
public class PrescriptionRow {
	
	private long id;
	private Medicine medicine;
	private Set<Dose> doses;
	private int numUnits;
	
}
