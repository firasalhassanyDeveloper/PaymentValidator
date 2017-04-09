package nl.meng.service.util;

import java.io.File;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Component
public class FileUtil {
	private final static Logger LOGGER = LoggerFactory.getLogger(FileUtil.class);

	public static File getFileForProcess(final String fileName) {
		LOGGER.info("Request to process file : " + fileName);
	    ClassLoader classLoader = new FileUtil().getClass().getClassLoader();
	    File file = new File(classLoader.getResource(fileName).getFile());
	   	LOGGER.info("File Found : " + file.exists());
	   	return file;
	}
	
	public static boolean IsXmlFile(final File file) {
		String fileExtention = FilenameUtils.getExtension(file.getAbsolutePath());
		return StringUtils.equalsIgnoreCase(fileExtention, "xml");
		
	}
	
	public static boolean IsCsvFile(final File file) {
		String fileExtention = FilenameUtils.getExtension(file.getAbsolutePath());
		return StringUtils.equalsIgnoreCase(fileExtention, "xml");
		
	}

}
