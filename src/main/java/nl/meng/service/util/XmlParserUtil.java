package nl.meng.service.util;

import java.io.File;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import nl.meng.service.model.Payment;
import nl.meng.service.model.PaymentsList;

@Component
public class XmlParserUtil {
	private final static Logger LOGGER = LoggerFactory.getLogger(XmlParserUtil.class);

	public List<Payment> parseXml(File file) {
		LOGGER.info("Start parsing xml file");
			JAXBContext jaxbContext;
			PaymentsList paymets = null;
			try {
				jaxbContext = JAXBContext.newInstance(PaymentsList.class);
				Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
				paymets = (PaymentsList) jaxbUnmarshaller.unmarshal(file);
			} catch (JAXBException e) {
				LOGGER.error(e.getMessage());;
			}
			LOGGER.info("Number of Payments " + paymets.getPaymentsList().size());
			return paymets.getPaymentsList();
		}
}
