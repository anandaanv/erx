package erx.test.util.gen;

import java.util.HashMap;
import java.util.Map;

import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.util.CsvContext;

import eRxDB.Brand;
import eRxDB.persistence.PersistenceWrapper;

public class BrandMapper implements CellProcessor {

	private Map<String, Brand> map = new HashMap<>();

	@Override
	public <T> T execute(Object value, CsvContext context) {
		String val = (String) value;
		if(map.containsKey(val)){
			return (T) map.get(val);
		}else{
			Brand b = new Brand();
			b.setName(val);
			PersistenceWrapper.save(b);
			map.put(val, b);
			return (T) b;
		}
	}
	
	

}
