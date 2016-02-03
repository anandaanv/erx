package eRxTest;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.erx.beans.Brand;
import com.erx.beans.Diagnosys;
import com.erx.beans.Medicine;
import com.erx.beans.MedicineType;
import com.erx.obj.Dose;

import eRxDB.persistence.PersistenceWrapper;

public class CacheInitializer {
	public static Brand BUYER = new Brand(1, "BUYER");
	public static Brand RANBAXY = new Brand(2, "RANBAXY");
	public static Brand CIPLA = new Brand(3, "CIPLA");
	
	
	public static Medicine MED1 = new Medicine(1, "MED1", BUYER, MedicineType.CAPSULE, Collections.singletonList(Dose.AFTER_BREAKFAST));
	public static Medicine MED2 = new Medicine(1, "MED1", CIPLA, MedicineType.CAPSULE, Collections.singletonList(Dose.AFTER_BREAKFAST));
	public static Medicine MED3 = new Medicine(1, "MED1", CIPLA, MedicineType.LIQUID, Collections.singletonList(Dose.BEFORE_SLEEPING));
	public static Medicine MED4 = new Medicine(1, "MED1", BUYER, MedicineType.OTHER, Collections.singletonList(Dose.BEFORE_LUNCH));
	public static Medicine MED5 = new Medicine(1, "MED1", RANBAXY, MedicineType.CAPSULE, Collections.singletonList(Dose.BEFORE_BREAKFAST));
	public static Medicine MED6 = new Medicine(1, "MED1", BUYER, MedicineType.CAPSULE, Collections.singletonList(Dose.EMPTY_STOMACH));
	public static Medicine MED7 = new Medicine(1, "MED1", RANBAXY,MedicineType.CAPSULE, Collections.singletonList(Dose.AFTER_LUNCH));
	public static Medicine MED8 = new Medicine(1, "MED1", CIPLA, MedicineType.CAPSULE, Collections.singletonList(Dose.THRICE_A_DAY));
	public static Medicine MED9 = new Medicine(1, "MED1", BUYER, MedicineType.TABLET, Collections.singletonList(Dose.TWICE_A_DAY));
	public static Medicine MED10 = new Medicine(1, "MED1", RANBAXY, MedicineType.TABLET, Collections.singletonList(Dose.AFTER_DINNER));
	public static Medicine MED11 = new Medicine(1, "MED1", BUYER, MedicineType.LIQUID, Collections.singletonList(Dose.AFTER_MEAL));
	
	
	public static Diagnosys D1 = new Diagnosys(1, "D1");
	public static Diagnosys D2 = new Diagnosys(1, "D2");
	public static Diagnosys D3 = new Diagnosys(1, "D3");
	public static Diagnosys D4 = new Diagnosys(1, "D4");
	public static Diagnosys D5 = new Diagnosys(1, "D5");
	public static Diagnosys D6 = new Diagnosys(1, "D6");
	
	public static void initialize() {
//		MedicineCache.S_INSTANCE.addMedicine(MED1);
//		MedicineCache.S_INSTANCE.addMedicine(MED2);
//		MedicineCache.S_INSTANCE.addMedicine(MED3);
//		MedicineCache.S_INSTANCE.addMedicine(MED4);
//		MedicineCache.S_INSTANCE.addMedicine(MED5);
//		MedicineCache.S_INSTANCE.addMedicine(MED6);
//		MedicineCache.S_INSTANCE.addMedicine(MED7);
//		MedicineCache.S_INSTANCE.addMedicine(MED8);
//		MedicineCache.S_INSTANCE.addMedicine(MED9);
//		MedicineCache.S_INSTANCE.addMedicine(MED10);
//		MedicineCache.S_INSTANCE.addMedicine(MED11);
		
		addMedicine(D1);
		addMedicine(D2);
		addMedicine(D6);
		addMedicine(D4);
		addMedicine(D2);
		addMedicine(D1);
		addMedicine(D3);
		addMedicine(D4);
		addMedicine(D2);
		addMedicine(D5);
		addMedicine(D4);
		addMedicine(D3);
		addMedicine(D1);
		
		
	}

	private static void addMedicine(Diagnosys diagnosys) {
		//DiagnosysMoleculeCache.S_INSTANCE.addMedicine(diagnosys);
		/*
		 * add your own logic.
		 */
	}


	static Map<Medicine, eRxDB.Medicine> cachedMeds = new HashMap<>();
	static Map<Brand, eRxDB.Brand> cachedbrands = new HashMap<>();
	
	private static eRxDB.Brand getBrand(Brand brand) {
		if(cachedbrands.containsKey(brand)){
			return cachedbrands.get(brand);
		}else{
			eRxDB.Brand m = new eRxDB.Brand();
			m.setName(brand.getBrandName());
			PersistenceWrapper.save(m);
			cachedbrands.put(brand, m);
			return m;
		}
	}
}
