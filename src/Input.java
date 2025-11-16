// Nitish Chawla, nkc47
import java.util.Scanner;

public class Input {
    private Scanner scanner;

    public Input() {
        this.scanner = new Scanner(System.in);
    }

    public String readLine() {
        return scanner.nextLine();
    }

    public String readLine(String prompt) {
        if (prompt!=null && !prompt.isEmpty()) {
            System.out.print(prompt);
        }
        return scanner.nextLine();
    }

    public int readInt() {
        return readInt("");
    }

    public int readInt(String prompt) {
        while (true){
            if (prompt!=null && !prompt.isEmpty()) {
                System.out.print(prompt);
            }
            if (scanner.hasNextInt()) {
                int val = scanner.nextInt();
                scanner.nextLine();
                return val;
            } else {
                scanner.nextLine();
                System.out.println("Invalid input");
            }
        }
    }

    public void close() {
        this.scanner.close();
    }
}
