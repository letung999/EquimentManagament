package PracticeAfterLearn.Chuong2.Bai7.Model;

public class ClassRoom {
    /**
     * roomID, roomName, numberOfSeats
     */
    private static int id = 100000;
    private int roomId;
    private String roomName;
    private int numberOfSeats;

    public ClassRoom(int roomId) {
        this.roomId = roomId;
    }

    public ClassRoom(int roomId, String roomName, int numberOfSeats) {
        if (roomId == 0) this.roomId = id;
        else this.roomId = roomId;
        this.roomName = roomName;
        this.numberOfSeats = numberOfSeats;
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        ClassRoom.id = id;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    @Override
    public String toString() {
        return "ClassRoom{" +
                "roomId=" + roomId +
                ", roomName='" + roomName + '\'' +
                ", numberOfSeats=" + numberOfSeats +
                '}';
    }
}
