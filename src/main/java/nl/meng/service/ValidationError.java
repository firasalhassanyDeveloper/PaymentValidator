package nl.meng.service;

public class ValidationError {

	public ValidationError(Integer refernce, String errorMessage) {
		this.errorMessage = errorMessage;
		this.refernce = refernce;
	}

	private String errorMessage;
	private Integer refernce;

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public Integer getRefernce() {
		return refernce;
	}

	public void setRefernce(Integer refernce) {
		this.refernce = refernce;
	}

	@Override
	public String toString() {
		return "Validation Error [Reference  = " + refernce + ", " + errorMessage + "]";
	}

}
