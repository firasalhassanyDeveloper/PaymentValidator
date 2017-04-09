package nl.meng;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import nl.meng.service.ValidationService;

public class FileValidatorDemo {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.register(PaymentValidatorApplication.class);
		ctx.refresh();
		ValidationService validationService = ctx.getBean(ValidationService.class);

		ClassLoader classLoader = new FileValidatorDemo().getClass().getClassLoader();
		File file = new File(classLoader.getResource("csv/records.csv").getFile());
		System.out.println(file.exists());
		validationService.processFile(file);
		ctx.close();

	}

}
