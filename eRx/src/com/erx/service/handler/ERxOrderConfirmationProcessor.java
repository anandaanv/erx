package com.erx.service.handler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.erx.pdfgen.PDFGeneratorPDFBox;
import com.erx.pdfgen.PrescriptionGeneratorPDF;
import com.erx.service.BaseERxOrderProcessor;
import com.erx.service.handler.sms.ERxSMSGateway;
import com.erx.service.ifc.OrderStatus;
import com.erx.service.input.ERxCreateOrderInput;
import com.erx.service.input.ERxCreateOrderOutput;

import eRxDB.Medicine;
import eRxDB.MedicineDiagnosys;
import eRxDB.Prescription;
import eRxDB.Prescriptionrow;
import eRxDB.persistence.PersistenceWrapper;

public class ERxOrderConfirmationProcessor extends BaseERxOrderProcessor<ERxCreateOrderInput, ERxCreateOrderOutput> {

	public ERxOrderConfirmationProcessor(ERxCreateOrderInput input) {
		super(input);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ERxCreateOrderOutput processOrder() {
		EntityManager em = PersistenceWrapper.getEntitymanager();
		Prescription ps = ERxHelper.findOrder(input.getOrderId());
		ps.setOrderstatus(OrderStatus.CONFIRMED.ordinal());
		
		em.getTransaction().begin();
		PersistenceWrapper.save(ps);
		em.getTransaction().commit();
		//trigger further processing.
		ERxSMSGateway.getInstance().sendOrderConfirmation(ps, input.getPatient());
		
		PrescriptionGeneratorPDF gen = new PDFGeneratorPDFBox();
		try {
			byte[] doc = gen.generatePDF(null, input);
			em.refresh(ps);
			ps.setPrescriptionDoc(doc);
			em.getTransaction().begin();
			PersistenceWrapper.save(ps);
			em.getTransaction().commit();
			
			//update medicine cache
			em.getTransaction().begin();
			List<Prescriptionrow> pRows = ps.getMedicines();
			List<MedicineDiagnosys> diagnosysList = new ArrayList<>();
			for (Prescriptionrow prescriptionrow : pRows) {
				Query query = PersistenceWrapper.getEntitymanager().createNamedQuery("MedicineDiagnosys.findByKey", MedicineDiagnosys.class);
				MedicineDiagnosys res;
				try{
					query.setParameter("doc", ps.getDoctor());
					query.setParameter("med", prescriptionrow.getMedicineid());
					query.setParameter("dyn", ps.getDiagnosys());
					res = (MedicineDiagnosys) query.getSingleResult();
					res.setUpdated(new Date());
					em.persist(res);
				}catch(NoResultException e){
					res = new MedicineDiagnosys();
					res.setDiagnosys(ps.getDiagnosys());
					Medicine transientMedicine = em.find(Medicine.class, prescriptionrow.getMedicineid());
					res.setMedicine(transientMedicine);
					res.setDoctor(ps.getDoctor());
					em.persist(res);
				}
			}
			em.getTransaction().commit();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		ERXRequestProcessorFactory.getRequestprocessor(input).processOrder();
		return null; //for now
	}

}
