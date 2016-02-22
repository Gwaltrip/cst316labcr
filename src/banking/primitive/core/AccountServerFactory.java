package banking.primitive.core;


public class AccountServerFactory {

	protected static AccountServerFactory _singleton = null;

	protected AccountServerFactory() {

	}

	public static AccountServerFactory getMe() {
		if (_singleton == null) {
			_singleton = new AccountServerFactory();
		}

		return _singleton;
	}

	public AccountServer lookup() {
		return new ServerSolution();
	}
}
