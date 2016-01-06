package component.interfaces;

public interface Billing {

	/**
	 * Notify the system that payment has been made for the most recent check out.
	 * @param verificationNr The Id received from the bank.
	 */
	public void payCredit(String verificationNr);
}
