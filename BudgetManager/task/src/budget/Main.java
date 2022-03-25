package budget;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        // write your code here
        Scanner scan = new Scanner(System.in);
        UserInterface ui = new UserInterface(scan);
        ui.start();
        scan.close();
    }
}
