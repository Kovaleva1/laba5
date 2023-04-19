package filelogic;
import java.util.Scanner;

public class Scanners {
    Scanner scanner;

    public Scanners() {
       scanner = new Scanner(System.in, "UTF-8");
    }
    public Scanners(Scanner scanner) {
        this.scanner = scanner;
    }

    public String readLine() {
            return scanner.nextLine();
    }

    public void printCommandText(String str) {
        System.out.print(str);
    }

    public void printCommandError(String str) {
        System.err.println(str);
    }
    public void printPreamble() {
        System.out.print(">");
    }
}

