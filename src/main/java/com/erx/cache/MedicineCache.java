package com.erx.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.erx.beans.Brand;
import com.erx.beans.Medicine;
import com.erx.beans.MedicineType;
import com.erx.obj.Dose;

import eRxDB.persistence.PersistenceWrapper;

public enum MedicineCache {
	// contains all available medicines.
	S_INSTANCE;
	
	MedicineCache(){
		EntityManager entitymanager = PersistenceWrapper.getEntitymanager();
		entitymanager.getTransaction().begin();
		Query query = entitymanager.createQuery("select m from Medicine m");
		List<eRxDB.Medicine> meds = query.getResultList();
		for (eRxDB.Medicine medicine : meds) {
			if(medicine.getBrand() == null){
				continue;
			}
			addMedicine(adaptMedicine(medicine));
		}
		entitymanager.getTransaction().commit();
	}
	
	private Map<Brand, List<Medicine>> medicinesByBrand = new HashMap<>();
	private Map<String, List<Medicine>> medicines = new HashMap<>();
	
	public void addMedicine(Medicine med){
		CacheHelper.getList(medicines, med.getProprietaryName()).add(med);
		CacheHelper.getList(medicinesByBrand, med.getBrandName()).add(med);
	}

	public List<Medicine> getMedicinesByBrand(Brand b){
		return CacheHelper.getList(medicinesByBrand, b, false);
	}
	
	public List<Medicine> getMedicinesByName(String name){
		return CacheHelper.getList(medicines, name, false);
	}
	
	public Medicine adaptMedicine(eRxDB.Medicine med){
		Medicine m = new Medicine(med.getId(), med.getProprietaryName(), new Brand(med.getBrand()), MedicineType.OTHER, getDosage(med.getDosage_form()));
		return m;
		
	}

	private List<Dose> getDosage(String dosage) {
		List<Dose> doseList = new ArrayList<>();
		String[] doses = dosage.replace("[", "").replace("]", "").split(",");
		for (String d : doses) {
			//FIXME
			try{
				doseList.add(Dose.valueOf(d));
			}catch (Exception e){
				doseList.add(Dose.AFTER_BREAKFAST);
			}
		}
		return doseList;
		
	}

	public List<Medicine> getAllMedicines() {
		return CacheHelper.getAll(medicines);
	}
	
}
