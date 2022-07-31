package source.model;


public class Room implements iRoomInterface {
String roomNumber;
Double price;
RoomType roomType;


    public Room(String roomNumberInp, Double priceInp, RoomType roomTypeInp) {
        roomNumber = roomNumberInp;
        price = priceInp;
        roomType = roomTypeInp;
    }

    public String toString() {
        String displayText = String.format("""
            Room Number: %s 
            Price: %.2f
            Type of Room: %s
        """,roomNumber,price,roomType);
        return displayText;
    }

    public String getRoomNumber() {
        return this.roomNumber;
    }

    public Double getRoomPrice() {
        return this.price;
    }

    public RoomType getRoomType() {
        return this.roomType;
    }

    public boolean isFree() {
        return !(this.price > 0.0);
    }

    public boolean equals(Room room) {
        if (this.roomNumber.matches(room.getRoomNumber())
        & (this.price == room.getRoomPrice()) 
        & (this.roomType == room.getRoomType())) {
            return true;
        }
        else {
            return false;
        }
    }
}