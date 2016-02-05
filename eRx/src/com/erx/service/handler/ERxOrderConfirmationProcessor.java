package com.erx.service.handler;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.persistence.EntityManager;

import org.apache.pdfbox.pdmodel.PDDocument;

import com.erx.pdfgen.PDFGeneratorPDFBox;
import com.erx.pdfgen.PrescriptionGeneratorPDF;
import com.erx.service.BaseERxOrderProcessor;
import com.erx.service.ifc.OrderStatus;
import com.erx.service.input.ERxCreateOrderInput;
import com.erx.service.input.ERxCreateOrderOutput;

import eRxDB.Prescription;
import eRxDB.persistence.PersistenceWrapper;

public class ERxOrderConfirmationProcessor extends BaseERxOrderProcessor<ERxCreateOrderInput, ERxCreateOrderOutput> {

	public ERxOrderConfirmationProcessor(ERxCreateOrderInput input) {
		super(input);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ERxCreateOrderOutput processOrder() {
		//TODO update order status to orderstatus.confirmed.
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
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ERXRequestProcessorFactory.getRequestprocessor(input).processOrder();
		return null; //for now
	}

}
