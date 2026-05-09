public class Main {
    public static void main(String[] args) {
        BankAccount account = new BankAccount("Ion", 4000);

        account.deposit(500);
        account.withdraw(300);

        System.out.println("Owner: " + account.getOwnerName());
        System.out.println("Balance: " + account.getBalance());
    }
}
