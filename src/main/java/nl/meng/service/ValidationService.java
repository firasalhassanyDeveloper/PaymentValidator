package nl.meng.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nl.meng.service.model.Payment;
import nl.meng.service.util.CsvParserUtil;
import nl.meng.service.util.ReportUtil;
import nl.meng.service.util.XmlParserUtil;

@Service
public class ValidationService {
	
	
	@Autowired
	private XmlParserUtil xmlParserUtil;

	@Autowired
	private CsvParserUtil csvParserUtil;

	
	@Autowired 
	private PaymentValidator paymentValidator;
	
	public void processFile(File file) throws FileNotFoundException, IOException {
		List<ValidationError> errors = new ArrayList<>();
		List<Payment> listOfPayments = new ArrayList<>();
		String fileExtention = FilenameUtils.getExtension(file.getAbsolutePath());
		

		if (fileExtention.equals("xml")) {
			listOfPayments = xmlParserUtil.parseXml(file);
		} else if (fileExtention.equals("csv")) {
			listOfPayments = csvParserUtil.parseCsv(file);

		}
		if (listOfPayments.isEmpty()) {
			errors.add(new ValidationError(0, "Uploaded file is empty"));
		} else {
			paymentValidator.validate(listOfPayments, errors);
		}
		if (!errors.isEmpty()) {
			ReportUtil.generateValidationResult(errors, file);
		}
	}
}
