package SIMCard;
public class SIMCard {
    private String id;
    private String owner;
    private double balance;
    private int number;

    public SIMCard() {
    }

    public SIMCard(String id, String owner, double balance, int number) {
        this.id = id;
        this.owner = owner;
        this.balance = balance;
        this.number = number;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "SIMCard{" +
                "id='" + id + '\'' +
                ", owner='" + owner + '\'' +
                ", balance=" + balance +
                ", number='" + number + '\'' +
                '}';
    }
}
