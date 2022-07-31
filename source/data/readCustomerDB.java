package source.data;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class readCustomerDB {
    public static void main(String args[]) throws FileNotFoundException{
    Scanner inputFileScanner = new Scanner(new File(args[0]));
    inputFileScanner.nextLine();
    inputFileScanner.useDelimiter(",");
    while(inputFileScanner.hasNext()) {
            System.out.println(inputFileScanner.next());
    }
}

}
