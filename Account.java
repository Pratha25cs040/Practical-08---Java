import exception.BankException;
import exception.InsufficientFundsException;
import exception.InvalidAmountException;

public class Account {
    private int accountNumber;
    private String holderName;
    private long balance;

    public Account(int accountNumber, String holderName, long balance) {
        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.balance = balance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public String getHolderName() {
        return holderName;
    }

    public long getBalance() {
        return balance;
    }

    public void deposit(long amount) throws InvalidAmountException {
        if (amount <= 0) {
            throw new InvalidAmountException(
                    "Deposit amount must be greater than zero.");
        }
        balance += amount;
    }

    public void withdraw(long amount)
            throws InsufficientFundsException, InvalidAmountException {
        if (amount <= 0) {
            throw new InvalidAmountException(
                    "Withdrawal amount must be greater than zero.");
        }

        if (amount > balance) {
            long shortfall = amount - balance;
            throw new InsufficientFundsException(
                    "Insufficient balance. You are short by " + shortfall + ".",
                    shortfall);
        }

        balance -= amount;
    }

    public void transfer(Account to, long amount) throws BankException {
        try {
            withdraw(amount);
            to.deposit(amount);
            System.out.println("Transfer successful.");
        } catch (BankException e) {
            throw e;
        } finally {
            System.out.println("Transfer attempt completed.");
        }
    }

    public void displayAccount() {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Holder Name: " + holderName);
        System.out.println("Balance: " + balance);
    }
}
