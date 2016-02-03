package com.erx.ops;

import com.erx.obj.Doctor;
import com.erx.service.ifc.ERxProcessorOutput;

public interface ERxGateway {
	ERxProcessorOutput completeSession(Doctor d, Session s);
	ERxProcessorOutput prepareSession(Doctor d, Session s);
}
