package source.service;

public class ServiceExceptions {
    public static class NoCustomerFoundException extends Exception{
        public NoCustomerFoundException(String Message) {
                super(Message);
        }
        
    }
    
    public static class NoEmailFoundException extends Exception {
        public NoEmailFoundException(String message) {
            super(message);
        }
    }

    public static class NoRoomFoundException extends Exception {
        public NoRoomFoundException(String message) {
            super(message);
        }
    }

    public static class ReservationException extends Exception {
        public ReservationException(String message) {
            super(message);
        }
    }

}

