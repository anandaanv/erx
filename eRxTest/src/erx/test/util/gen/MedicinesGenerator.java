package erx.test.util.gen;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.ParseDate;
import org.supercsv.cellprocessor.ParseDouble;
import org.supercsv.cellprocessor.ParseInt;
import org.supercsv.cellprocessor.constraint.StrMinMax;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.prefs.CsvPreference;
import org.supercsv.util.CsvContext;

import eRxDB.Medicine;
import eRxDB.persistence.PersistenceWrapper;

public class MedicinesGenerator {
	
	
	private static final String[] header = 
		    new String[] {"id","applicantLicenseNo","brand","medRegNo","nappiCode","atc4","medicineSchedule","proprietaryName",
		    		"activeIngredients","strength","unit","dosage_form","pack_size","quantity","manuf_price",
		    		"logistics_fee","vat","sep","unit_price","effective_date","status","generic_or_originator","sales_volume"};

	private static final CellProcessor[] procs = new CellProcessor[] {
			new ParseInt(),
			new Optional(new ParseInt()),
			new Optional(new BrandMapper()),
			new Optional(new StrMinMax(0, 100)),
			new Optional(),
			new Optional(),
			new Optional(),
			new Optional(),
			new Optional(),
			new Optional(new ParseDouble()),
			new Optional(new Optional()),
			new Optional(new Optional()),
			new Optional(new ParseDouble()),
			new Optional(new ParseInt()),
			new Optional(new ParseDouble()),
			new Optional(new ParseDouble()),
			new Optional(new ParseDouble()),
			new Optional(new ParseDouble()),
			new Optional(new ParseDouble()),
			new Optional(new ParseDateCustom("dd-MMM-yy")),
			new Optional(),
			new Optional(),
			new Optional(new ParseDouble())
	};
	public static void main(String[] args) throws FileNotFoundException {
		PersistenceWrapper.getEntitymanager().getTransaction().begin();
		parse();
		PersistenceWrapper.getEntitymanager().getTransaction().commit();;
	}

	static class ParseDateCustom extends ParseDate{

		public ParseDateCustom(String dateFormat) {
			super(dateFormat);
		}

		@Override
		public Object execute(Object value, CsvContext context) {
			if(((String)value).length() < 9){
				value = "0" + value;
			}
			return super.execute(value, context);
		}
		
		
	}
	
	
	public static void parse(){
	    try {
	    	ICsvBeanReader reader = new CsvBeanReader(new BufferedReader(new FileReader("resources/Database Of Medicine Prices_modified.csv")), CsvPreference.STANDARD_PREFERENCE);
	        Medicine entry = null;
	        int count = 0;
	        while((entry = reader.read(Medicine.class, header, procs)) != null){
	        	System.out.println(count++);
	        	entry.setId(0);
	        	PersistenceWrapper.save(entry);
	        }
	        reader.close();
	    } catch (FileNotFoundException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    } catch (IOException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }
	}
	
//	private static Reader createReader() throws FileNotFoundException {
//		File file = new File("Database_Of_Medicine_Prices.csv");
//		System.out.println(file.getAbsolutePath());
//		return new FileReader("resources/Database_Of_Medicine_Prices.csv");
//	}
}
