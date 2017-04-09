package nl.meng.service.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import nl.meng.service.ValidationError;
@Component
public class ReportUtil {
	final static Logger LOGGER = LoggerFactory.getLogger(ReportUtil.class);

	public static void generateValidationResult(List<ValidationError> errors, File file) throws IOException {
		FileWriter reportWriter = new FileWriter(generateErrorFileName(file.getName())); 
		BufferedWriter bufferedWriter = new BufferedWriter(reportWriter);
		
		for(ValidationError error: errors) {
			bufferedWriter.write(error.toString());
			bufferedWriter.newLine();
			
		}
		bufferedWriter.close();
		reportWriter.close();
	}
	
	
	private static String generateErrorFileName(String file) {
		LOGGER.info("Generate validation error file for file: "+ file);
		LocalDateTime currentTime = LocalDateTime.now();
		String validationErrorFileName = "ValidationError_"+ file + currentTime+ ".txt"; 
		return validationErrorFileName;
	}
}
