import java.util.Scanner;

import static java.lang.System.*;

public class UserInterfaceView {

    public String inputExpression() {
        Scanner scanner = new Scanner(System.in);
        String request;
        System.out.println("Введите выражение: ");
        request = scanner.nextLine();
        return request;
    }
}
