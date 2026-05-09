public class Main {
    public static void main(String[] args) {
        BankAccount account1 = new BankAccount("Octavian", 1000);

        SavingsAccount savings =
                new SavingsAccount("Maria", 2000, 0.05);

        try {

            System.out.println(account1);

            account1.deposit(500);

            account1.withdraw(300);

            System.out.println(account1);

            account1.deposit(-100);

        } catch (InvalidAmountException e) {

            System.out.println("InvalidAmountException: " + e.getMessage());

        } catch (InsufficientFundsException e) {

            System.out.println("InsufficientFundsException: " + e.getMessage());
        }

        try {

            account1.withdraw(5000);

        } catch (InvalidAmountException | InsufficientFundsException e) {

            System.out.println(e.getMessage());
        }

        savings.addInterest();
    }
}
