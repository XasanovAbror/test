import SIMCard.SIMCard;

import java.util.Scanner;

public class Main {
    static Scanner strScanner = new Scanner(System.in);
    static Scanner intScanner = new Scanner(System.in);
    public static SIMCard[] cards = new SIMCard[100];
    public static final Double price = 250.0;
    public static int index = 0;
    static SMS[] SMSArray = new SMS[500];
    public static int indexSMS = 0;
    public static void main(String[] args) {

        while (true) {
            switch(menu()) {
                case 0 ->{
                    System.out.println("See you soon");
                    return;
                }
                case 1 ->{
                    addSIM();
                }
                case 2 -> {
                    editSIM();
                }
                case 3 ->{
                    showSIMCards();
                }
                case 4 ->{
                    deleteSIMCard();
                }
                case 5 ->{
                    login();
                }
                default -> {
                    System.out.println("There is no functionality");
                }
            }
        }

    }
    public static int menu() {
        System.out.println("""
                0 -> Exit
                1 -> Add SIM
                2 -> Edit SIM cards
                3 -> Show
                4 -> Delete SIM
                5 -> Login
                """);
        return intScanner.nextInt();
    }
    public static void addSIM() {
        SIMCard simCard=new SIMCard();
        System.out.print("Enter SIM ID : ");
        simCard.setId(strScanner.nextLine());
        System.out.print("Enter User(owner) : ");
        simCard.setOwner(strScanner.nextLine());
        System.out.print("Enter balance : ");
        simCard.setBalance(intScanner.nextDouble());
        System.out.print("Enter SIM number : ");
        simCard.setNumber(intScanner.nextInt());
        cards[index++]=simCard;
    }

    public static void editSIM() {
        if (index<0) {
            System.out.println("No SIM card added yet");
            return;
        }
        System.out.print("Enter the SIM card index you want to edit : ");
        int n=intScanner.nextInt();
        if (n>index || n<=0) {
            System.out.println("There is no sim card in this index\n Please check the sim card slot");
            return;
        }
        System.out.println("Editing started");

        SIMCard simCard=new SIMCard();
        System.out.print("Enter SIM ID(edit) : ");
        simCard.setId(strScanner.nextLine());
        if (!simCard.getId().isBlank()) {
            cards[n-1].setId(simCard.getId());
        }
        System.out.print("Enter User(owner(edit)) : ");
        simCard.setOwner(strScanner.nextLine());
        if (!simCard.getOwner().isBlank()) {
            cards[n-1].setOwner(simCard.getOwner());
        }
        System.out.print("Enter balance(edit) : ");
        String balance=strScanner.nextLine();
        if (!balance.isBlank()) {
            cards[n-1].setBalance(Double.parseDouble(balance));
        }
        System.out.print("Enter SIM number(edit) : ");
        String number=strScanner.nextLine();
        if (!number.isBlank()) {
            cards[n-1].setNumber(Integer.parseInt(number));
        }
        System.out.println("Your SIM card has been changed successfully");
    }
    public static void showSIMCards() {
        for (int i = 0; i < index; i++) {
            System.out.println(cards[i].toString());
        }
    }
    public static void deleteSIMCard() {
        showSIMCards();
        System.out.println("Enter the index of the SIM card you want to delete");
        int n = intScanner.nextInt();
        if(n > index || n <= 0){
            System.out.println("There is no SIM card here\nPlease check the SIM card slot and re-insert");
            return;
        }
        SIMCard simCard = new SIMCard();
        cards[n - 1] = simCard;
        for (int i = n - 1; i < index; i++) {
            SIMCard temp;
            temp = cards[i];
            cards[i] = cards[i + 1];
            cards[i + 1]=temp;
            index--;
            System.out.println("Your SIM card has been deleted successfully");
        }
    }
    public static void login() {
        while (true) {
            switch (loginMenu()) {
                case 0 -> {
                    System.out.println("See you soon");
                    return;
                }
                case 1 -> {
                    writeSMS();
                }
                case 2 -> {
                    readSMS();
                }
                case 3 -> {
                    depositeBalance();
                }
                default -> {
                    System.out.println("There is no such function in the login menu");
                }
            }
        }
    }
    public static int loginMenu() {
        System.out.println("""
                0 -> Exit
                1 -> Write SMS
                2 -> Read SMS
                3 -> Deposite balance
                """);
        return intScanner.nextInt();
    }
    public static void writeSMS() {
        System.out.print("Enter the index of the sending SIM card : ");
        int senderIndex = intScanner.nextInt();

        System.out.print("Enter the index of the receiving SIM card : ");
        int receiverIndex = intScanner.nextInt();

        if (senderIndex <= 0 || senderIndex > index || cards[senderIndex - 1] == null ||
                receiverIndex <= 0 || receiverIndex > index || cards[receiverIndex - 1] == null) {
            System.out.println("One or both of the SIM cards do not exist.\nPlease check the SIM card indices and re-enter.");
            return;
        }
        System.out.print("Enter the message to send: ");
        String message = strScanner.nextLine();

        SMSArray[indexSMS++] = new SMS(senderIndex,receiverIndex,message);
        System.out.println("Message sent successfully.");
    }
    public static void readSMS() {
        boolean foundMessage=false;
        System.out.println("All received SMS messages:");
        for (SMS sms : SMSArray) {
            if (sms != null) {
                System.out.println("From SIM card " + sms.getSenderIndex() + " to SIM card " + sms.getReceiverIndex() + ": " + sms.getMessage());
                foundMessage = true;
            }
        }
        if (!foundMessage) {
            System.out.println("No new messages.");
        }
    }
    public static void depositeBalance() {
        System.out.print("Enter the index of the SIM card you want to deposit balance into: ");
        int indexDeposit = intScanner.nextInt();
        if (indexDeposit <= 0 || indexDeposit > index || cards[indexDeposit - 1] == null) {
            System.out.println("No SIM card found at this index.\nPlease check the SIM card index and re-enter.");
            return;
        }
        double deduction = 0;
        for (SMS sms : SMSArray) {
            if (sms != null && sms.getSenderIndex() == indexDeposit) {
                deduction += price;
            }
        }
        double currentBalance = cards[indexDeposit - 1].getBalance();
        if (currentBalance < deduction) {
            System.out.println("There are no funds in your account. Please complete your account.");
            return;
        }
        double newBalance = currentBalance - deduction;
        cards[indexDeposit - 1].setBalance(newBalance);
        System.out.println("Balance deducted for sent SMS messages. Current balance: " + newBalance);
    }
}