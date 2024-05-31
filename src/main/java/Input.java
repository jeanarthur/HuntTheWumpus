import java.util.Scanner;

public class Input {

    Scanner scanner = new Scanner(System.in);

    public int getInteger() throws NumberFormatException {
        return Integer.parseInt(scanner.nextLine());
    }

}
