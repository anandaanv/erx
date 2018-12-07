package erxdb.persistence;

import javax.persistence.Persistence;

public class CreateScripts {
	
	public static void main(String[] args) {
	    Persistence.generateSchema("erxdb", null);
	}

}
