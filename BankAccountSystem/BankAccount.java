public class BankAccount {
    private String owner;
    private double balance;

    public BankAccount(String owner, double balance) {
        this.owner = owner;
        this.balance = balance;
    }

    public String getOwner() {
        return owner;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) throws InvalidAmountException {
        if (amount <= 0) {
            throw new InvalidAmountException("Deposit amount must be greater than 0.");
        }

        balance += amount;

        System.out.println(amount + " deposited successfully.");
    }

    public void withdraw(double amount)
            throws InvalidAmountException, InsufficientFundsException {

        if (amount <= 0) {
            throw new InvalidAmountException("Withdraw amount must be greater than 0.");
        }

        if (amount > balance) {
            throw new InsufficientFundsException("Not enough money in account.");
        }

        balance -= amount;

        System.out.println(amount + " withdrawn successfully.");
    }

    @Override
    public String toString() {
        return "Owner: " + owner + ", Balance: " + balance;
    }
}
