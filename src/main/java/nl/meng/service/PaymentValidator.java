package nl.meng.service;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import nl.meng.service.model.Payment;
import nl.meng.service.ValidationError;

@Component
public class PaymentValidator {

	private static final String PAYMENT_MISSING_MANDATORY_BALANCE_VALUE = "Payment missing mandatory balance value.";
	private static final String INVALID_END_BALANCE = "End balance is not correct.";
	private static final String REFERENCE_APPEARS_MORE_THAN_ONCE = " Reference appears more than once.";
	private final static Logger LOGGER = LoggerFactory.getLogger(PaymentValidator.class);

	public List<ValidationError> validate(List<Payment> payments, List<ValidationError> errors) {
		LOGGER.info("Validate list of payment");
		validateBalance(payments, errors);
		validateUnique(payments, errors);
		return errors;
	}

	private static List<ValidationError> validateBalance(List<Payment> payments, List<ValidationError> errors) {
		LOGGER.info("Validate end balance for every payment");
		for (Payment payment : payments) {
			if (preValidation(payment)) {
				errors.add(new ValidationError(payment.getReference(), PAYMENT_MISSING_MANDATORY_BALANCE_VALUE));
			} else if (invalidateEndBalance(payment)) {
				errors.add(new ValidationError(payment.getReference(), INVALID_END_BALANCE));
			}
		}
		return errors;
	}

	private static List<ValidationError> validateUnique(List<Payment> payments, List<ValidationError> errors) {
		{
			final Set<Payment> setToReturn = new HashSet<>();
			final Set<Integer> set1 = new HashSet<>();
			for (Payment yourPayment : payments) {
				if (!set1.add(yourPayment.getReference())) {
					setToReturn.add(yourPayment);
				}
			}
			for (Payment payment : setToReturn) {
				errors.add(new ValidationError(payment.getReference(), REFERENCE_APPEARS_MORE_THAN_ONCE));
			}
			return errors;
		}
	}

	private static boolean preValidation(Payment toBeValidated) {
		return null == toBeValidated.getEndBalance() || null == toBeValidated.getMutation()
				|| null == toBeValidated.getStartBalance();

	}

	private static boolean invalidateEndBalance(Payment toBeValidated) {
		BigDecimal startBalance = toBeValidated.getStartBalance();
		BigDecimal mutation = toBeValidated.getMutation();
		BigDecimal newBalance = startBalance.add(mutation);
		return newBalance.compareTo(toBeValidated.getEndBalance()) != 0;
	}

}
