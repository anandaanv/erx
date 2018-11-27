package com.erx.obj;

import com.erx.beans.Medicine;
import java.util.Set;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@RequiredArgsConstructor
@Builder
public class PrescriptionRow {
	
	private int id;
	private Medicine medicine;
	private Set<Dose> doses;
	private int numUnits;
	
}
