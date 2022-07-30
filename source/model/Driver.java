package source.model;

public class Driver {
    public static void main(String[] args) {
        try {
        Customer customer = new Customer("John","Doe","j@doma");
        System.out.println(customer);
        }
        catch (IllegalArgumentException e) {
            System.out.println(String.format("Program threw exception. %s",e.getMessage()));
        }
    }
}
