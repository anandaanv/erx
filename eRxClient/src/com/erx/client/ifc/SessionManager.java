package com.erx.client.ifc;

import com.erx.obj.Patient;
import com.erx.ops.Session;
import com.erx.service.ifc.ERxProcessorOutput;

public interface SessionManager {
	Session createSession(Patient p);
	void completeSession(Session s);
	ERxProcessorOutput prepareSession(Session s);
}
