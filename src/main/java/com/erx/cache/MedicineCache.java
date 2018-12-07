package com.erx.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.erx.beans.Brand;
import com.erx.beans.Medicine;

import erxdb.persistence.PersistenceWrapper;

public enum MedicineCache {
	// contains all available medicines.
	S_INSTANCE;
	
	MedicineCache(){
		EntityManager entitymanager = PersistenceWrapper.getEntitymanager();
		entitymanager.getTransaction().begin();
		Query query = entitymanager.createQuery("select m from Medicine m");
		List<erxdb.Medicine> meds = query.getResultList();
		for (erxdb.Medicine medicine : meds) {
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
	
	public Medicine adaptMedicine(erxdb.Medicine med){
		Medicine m = new Medicine(med.getId(), med.getProprietaryName(),
				Brand.builder().brandName(med.getBrand().getName()).build(),
				med.getDosage_form());
		return m;
		
	}

	public List<Medicine> getAllMedicines() {
		return CacheHelper.getAll(medicines);
	}
	
}
