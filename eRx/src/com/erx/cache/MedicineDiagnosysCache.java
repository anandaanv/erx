package com.erx.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.erx.beans.Diagnosys;
import com.erx.beans.Medicine;


/* adds medicines and diagnosys mapping to list. 
* retrieves medicines that are mapped to diagnosys.
* hence class is medicine-diagnosys-cache.
**/
public enum MedicineDiagnosysCache {
	S_INSTANCE;
	
	private Map<Diagnosys, List<Medicine>> medicines = new HashMap<>();
	
	public void addMedicine(Diagnosys d, Medicine m){
		CacheHelper.getList(medicines, d).add(m);
	}
	
	public List<Medicine> getMedicines(Diagnosys d){
		return medicines.get(d);				// adds all medicines to the list which are mapped to d.
	}
	
}
