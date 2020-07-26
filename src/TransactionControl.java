import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class TransactionControl {
    public static void main(String[] args) throws IOException {
        String balanceF = "balance.txt";
        String transactionsF = "transactions.txt";
        Double balance;

        FileReader fReader1 = new FileReader(balanceF);
        BufferedReader bfReader1 = new BufferedReader(fReader1);
        String balanceAsString = bfReader1.readLine();
        balance = Double.parseDouble(balanceAsString);

        FileReader fReader2 = new FileReader(transactionsF);
        BufferedReader bfReader2 = new BufferedReader(fReader2);
        Scanner scanner = new Scanner(bfReader2);
        ArrayList<Double> transactions = new ArrayList<>();
        while(scanner.hasNext()) {
            String pM = scanner.next();
            if(pM.equals("I")) {
                transactions.add(scanner.nextDouble());
            } else {
                transactions.add(scanner.nextDouble()*(-1));
            }
        }
        scanner.close();
        bfReader1.close();
        bfReader2.close();

        double sumTrans = 0;
        for(Double i: transactions) {
            sumTrans += i;
        }

        System.out.printf("Initial balance: %.2f", balance);
        System.out.println();
        System.out.println("Transactions: " + transactions + " sum = " + sumTrans);

        if(balance + sumTrans >= 0) {
            FileWriter fWriter = new FileWriter(balanceF, false);
            PrintWriter printWriter = new PrintWriter(new BufferedWriter(fWriter));
            printWriter.printf("%.2f", (balance+sumTrans));
            printWriter.close();
            System.out.println("Transactions successful, new balance: ");
            System.out.printf("%.2f", (balance+sumTrans));
        } else {
            System.out.println("New balance is below 0, transactions cancelled.");
        }

    }
}
