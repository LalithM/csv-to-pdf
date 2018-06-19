package com.lalith.file.common;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class CommonUtility {
	
	public static final Logger logger = LoggerFactory.getLogger(CommonUtility.class);
	
	public static void createPdfFile(Integer columns,List<String[]> reader,String fileName,String path){
		Path location = Paths.get(path + fileName);
		
			Document myPdf = new Document(PageSize.A4.rotate());
			try {
				PdfWriter.getInstance(myPdf, new FileOutputStream(location+".pdf"));
				myPdf.open();            
	            PdfPTable table = new PdfPTable(columns);
	            table.setWidthPercentage(100);
	            table.getDefaultCell().setUseAscender(true);
	            table.getDefaultCell().setUseDescender(true);	
	            if(!CollectionUtils.isEmpty(reader)){
 	            	reader.forEach(read->{
	            		for(int i=0;i<read.length;i++){
	            			PdfPCell cells = new PdfPCell(new Phrase(read[i]));
	            			cells.setColspan(5);
	            	        cells.setHorizontalAlignment(Element.ALIGN_CENTER);
	            	        cells.setVerticalAlignment(Element.ALIGN_LEFT);
	            	        cells.setPadding(5.0f);
		            		table.addCell(cells);
		            	}
	            	});
	            	 myPdf.add(table);                       
	                 myPdf.close(); 
            }
			} catch (DocumentException | IOException e) {
					logger.error("Pdf writer creates error"+e);
			}
	
	}

}
