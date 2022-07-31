package source.model;

public class FreeRoom extends Room {
    public FreeRoom(String roomNumberInp, RoomType roomTypeInp) {
        super(roomNumberInp,0.0, roomTypeInp);

    }

    public String toString() {
        String displayText = String.format("""
            This object represents a free room with properties: \n
            Room Number: %d \n
            Type of Room: %s \n
        """,roomNumber,price,roomType);
        return displayText;
    }

}