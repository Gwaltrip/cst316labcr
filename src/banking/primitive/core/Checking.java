package banking.primitive.core;
/**
 * 
 * @author Author
 */
public class Checking extends Account {

	private static final long serialVersionUID = 11L;
	private int numWithdraws = 0;
	private static float WITHDRAW_FEE = 2.0f;
	private static int WITHDRAW_LIMIT = 10;
	private static float OVERDRAWN = -100.f;
	private static float MINIMUM = 0.0f;
	
	private Checking(String name) {
		super(name);
	}

    public static Checking createChecking(String name) {
        return new Checking(name);
    }

	public Checking(String name, float balance) {
		super(name, balance);
	}

	/**
	 * A deposit may be made unless the Checking account is closed
	 * @param float is the deposit amount
	 */
	public boolean deposit(float amount) {
		if (getState() != State.CLOSED && amount > MINIMUM) {
			balance = balance + amount;
			if (balance >= MINIMUM) {
				setState(State.OPEN);
			}
			return true;
		}
		return false;
	}

	/**
	 * Withdrawal. After 10 withdrawals a fee of $2 is charged per transaction You may 
	 * continue to withdraw an overdrawn account until the balance is below -$100
	 */
	public boolean withdraw(float amount) {
		if (amount > MINIMUM) {		
			// KG: incorrect, last balance check should be >=
			if (getState() == State.OPEN || (getState() == State.OVERDRAWN && balance > OVERDRAWN)) {
				balance = balance - amount;
				numWithdraws++;
				if (numWithdraws > WITHDRAW_LIMIT)
					balance = balance - WITHDRAW_FEE;
				if (balance < MINIMUM) {
					setState(State.OVERDRAWN);
				}
				return true;
			}
		}
		return false;
	}

	public String getType() { return "Checking"; }
	
	public String toString() {
		return "Checking: " + getName() + ": " + getBalance();
	}
}
