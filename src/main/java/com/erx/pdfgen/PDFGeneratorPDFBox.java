package com.erx.pdfgen;

import com.erx.pdfgen.sign.CreateVisibleSignature;
import com.erx.service.handler.ERxHelper;
import com.erx.service.input.ERxCreateOrderInput;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.List;
import java.util.UUID;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import com.erx.obj.PrescriptionRow;

import erxdb.Certificate;
import erxdb.Doctor;
import erxdb.persistence.PersistenceWrapper;

public class PDFGeneratorPDFBox extends PrescriptionGeneratorPDF {

	private static final BouncyCastleProvider BCPROVIDER = new BouncyCastleProvider();
	
	@Override
	protected byte[] generatePDF(ERxCreateOrderInput input) throws IOException {
		PDDocument doc = new PDDocument();
		PDPage page = new PDPage();
		doc.addPage(page);

		PDPageContentStream contentStream = new PDPageContentStream(doc, page);

		String[][] contentHeader = getTopContent(input);
		drawTable(page, contentStream, 700, 10, contentHeader, false, 100);

		int startX = 10;
		int startY = 635;
		int endY = 635;
		int endX = 500;
		contentStream.moveTo(startX, startY);
		contentStream.lineTo(endX, endY);
		contentStream.stroke();

		String[][] content = getMedicinesContent(input);
		drawTable(page, contentStream, 620, startX, content, false, 0);


		contentStream.close();
		String name = getTempDir() + UUID.randomUUID().toString();
		doc.save(name);
		try {
			byte[] signArray = signDoc(name, input);
			return signArray;
		} catch (UnrecoverableKeyException | KeyStoreException | NoSuchAlgorithmException | CertificateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			new File(name).delete();
		}
		return null;
	}

	private String getTempDir() {
		// TODO Auto-generated method stub
		return null;
	}

	private String[][] getTopContent(ERxCreateOrderInput input) {
		return new String[][] { { "Patient name", input.getPatient().getName() },
				{ "Phone number", input.getPatient().getPhoneNumber() },
				{ "Diagnosys", input.getDiagnosys().toString() } };

	}

	private String[][] getMedicinesContent(ERxCreateOrderInput input) {
		String[][] res = new String[input.getPrescriptions().size() + 1][2];
		res[0] = new String[] { "Medicines prescribed : ", "" };
		List<PrescriptionRow> meds = input.getPrescriptions();
		for (int i = 0; i < meds.size(); i++) {
			PrescriptionRow row = meds.get(i);
			res[i + 1] = getContent(row);
		}
		return res;
	}

	private String[] getContent(PrescriptionRow row) {
		return new String[] { row.getMedicine().getProprietaryName(), row.getDoses().toString() };
	}

	public static void drawTable(PDPage page, PDPageContentStream contentStream, float y, float margin,
			String[][] content, boolean drawline, float colWidth) throws IOException {
		final int rows = content.length;
		final int cols = content[0].length;
		final float rowHeight = 20f;
		final float tableWidth = page.getMediaBox().getWidth() - margin - margin;
		final float tableHeight = rowHeight * rows;
		if (colWidth == 0) {
			colWidth = tableWidth / (float) cols;
		}
		final float cellMargin = 5f;

		// draw the rows
		float nexty = y;
		for (int i = 0; i <= rows; i++) {
			if (drawline) {
				contentStream.drawLine(margin, nexty, margin + tableWidth, nexty);
			}
			nexty -= rowHeight;
		}

		// draw the columns
		float nextx = margin;
		for (int i = 0; i <= cols; i++) {
			if (drawline) {
				contentStream.drawLine(nextx, y, nextx, y - tableHeight);
			}
			nextx += colWidth;
		}

		// now add the text
		contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);

		float textx = margin + cellMargin;
		float texty = y - 15;
		for (int i = 0; i < content.length; i++) {
			for (int j = 0; j < content[i].length; j++) {
				String text = content[i][j];
				contentStream.beginText();
				contentStream.moveTextPositionByAmount(textx, texty);
				contentStream.drawString(text);
				contentStream.endText();
				textx += colWidth;
			}
			texty -= rowHeight;
			textx = margin + cellMargin;
		}
		
	}
	
	
	public byte[] signDoc(String docname, ERxCreateOrderInput input) throws IOException, UnrecoverableKeyException, KeyStoreException, NoSuchAlgorithmException, CertificateException{
		Certificate cert = getSignImage(input);

        KeyStore keystore = KeyStore.getInstance("PKCS12", BCPROVIDER);
        char[] pin = input.getCertPassword().toCharArray();
        keystore.load(new ByteArrayInputStream(cert.getValue()), pin);

        File documentFile = new File(docname);

        CreateVisibleSignature signing = new CreateVisibleSignature(keystore, pin.clone());

		InputStream image = new ByteArrayInputStream(cert.getSignImage());
        
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        
        
        
        // page is 1-based here
        int page = 1;
        signing.setVisibleSignatureProperties (docname, 100, 700, -50, image, page);
        signing.setSignatureProperties ("Anand Vaidya", "Pune", "RSA", 0, page, true);
        signing.signPDF(documentFile, out);        
        return out.toByteArray();
	}

	public Certificate getSignImage(ERxCreateOrderInput input) throws FileNotFoundException {
		Doctor res = PersistenceWrapper.getEntitymanager().find(Doctor.class, input.getDoctor().getId());
		Certificate cert = ERxHelper.getCertificate(res);
		return cert;
	}

	
	public static void main(String[] args) throws IOException {
		PDFGeneratorPDFBox box = new PDFGeneratorPDFBox();
		box.generatePDF(null, null);
	}
}
