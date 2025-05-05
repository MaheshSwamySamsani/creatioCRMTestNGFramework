package com.creatio.crm.frameWork.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class PdfUtil {
	
	public static String getText(String fileName) {
		
		String text = "";
		
		try {
			//Read the PDF File
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\PdfFiles\\"+fileName);
			
			//Load Pdf file and analyze the PDF file
			PDDocument document = PDDocument.load(fis);
			
			//Read and copy the text 
			PDFTextStripper pdf = new PDFTextStripper();
			
//			pdf.setStartPage(2);
//			pdf.setEndPage(2);
			
			text = pdf.getText(document);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	
		
		return text;		
	}

}
