import exception.BankException;
import exception.InsufficientFundsException;
import exception.InvalidAmountException;

public class MiniBank {
    public static void main(String[] args) {
        Account account1 = new Account(101, "Alice", 1000);
        Account account2 = new Account(102, "Bob", 500);

        try {
            account1.deposit(200);
            System.out.println("Deposit successful.");
        } catch (InvalidAmountException e) {
            System.out.println("Deposit failed: " + e.getMessage());
        } finally {
            System.out.println("Deposit operation completed.");
        }

        try {
            account1.withdraw(1500);
            System.out.println("Withdrawal successful.");
        } catch (InsufficientFundsException e) {
            System.out.println("Withdrawal failed: " + e.getMessage());
            System.out.println("Shortfall: " + e.getShortfall());
        } catch (InvalidAmountException e) {
            System.out.println("Invalid withdrawal: " + e.getMessage());
        } finally {
            System.out.println("Withdrawal operation completed.");
        }

        try {
            account1.transfer(account2, 300);
        } catch (BankException e) {
            System.out.println("Transfer failed: " + e.getMessage());
        } finally {
            System.out.println("Main transfer operation completed.");
        }

        try (BankSession session = new BankSession()) {
            System.out.println("Performing banking operations.");
            account1.withdraw(10000);
        } catch (BankException e) {
            System.out.println("Banking error: " + e.getMessage());
        } finally {
            System.out.println("Banking process finished.");
        }

        System.out.println("\\nFinal Account Details:");
        account1.displayAccount();
        account2.displayAccount();
    }
}
