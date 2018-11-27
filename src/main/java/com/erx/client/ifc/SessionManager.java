package com.erx.client.ifc;

import java.util.List;

import com.erx.beans.Medicine;
import com.erx.obj.Patient;
import com.erx.ops.Session;
import com.erx.service.ifc.ERxProcessorOutput;

public interface SessionManager {
	Session createSession(Patient p, List<String> diagnosys);
	void completeSession(Session s);
	ERxProcessorOutput prepareSession(Session s);
	List<Medicine> getPossibleMedicines(Session s);
}
