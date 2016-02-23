package banking.primitive.core;


public class AccountServerFactory {

	protected static AccountServerFactory SINGLETON = null;

	protected AccountServerFactory() {

	}

	public static AccountServerFactory getMe() {
		if (SINGLETON == null) {
			SINGLETON = new AccountServerFactory();
		}

		return SINGLETON;
	}

	public AccountServer lookup() {
		return new ServerSolution();
	}
}
