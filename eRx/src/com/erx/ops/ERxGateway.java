package com.erx.ops;

import com.erx.obj.Doctor;
import com.erx.service.ifc.ERxProcessorOutput;
import com.erx.service.input.ERxPossibleMedicinesOutput;

public interface ERxGateway {
	ERxProcessorOutput completeSession(Doctor d, Session s);
	ERxProcessorOutput prepareSession(Doctor d, Session s);
	ERxPossibleMedicinesOutput getPossibleMedicines(Doctor d, Session s);
}
