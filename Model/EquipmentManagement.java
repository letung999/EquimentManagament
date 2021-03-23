package PracticeAfterLearn.Chuong2.Bai7.Model;

public class EquipmentManagement {
    /**
     *
     */
    private ClassRoom classRoom;
    private Equipment equipment;
    private int numOfSupply;
    private String state;
    private int totalSupply;

    public EquipmentManagement(ClassRoom classRoom) {
        this.classRoom = classRoom;
    }

    public EquipmentManagement(Equipment equipment) {
        this.equipment = equipment;
    }

    public EquipmentManagement(ClassRoom classRoom, Equipment equipment, int numOfSupply, String state, int totalSupply) {
        this.classRoom = classRoom;
        this.equipment = equipment;
        this.numOfSupply = numOfSupply;
        this.state = state;
        this.totalSupply = totalSupply;
    }

    public ClassRoom getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(ClassRoom classRoom) {
        this.classRoom = classRoom;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    public int getNumOfSupply() {
        return numOfSupply;
    }

    public void setNumOfSupply(int numOfSupply) {
        this.numOfSupply = numOfSupply;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getTotalSupply() {
        return totalSupply;
    }

    public void setTotalSupply(int totalSupply) {
        this.totalSupply = totalSupply;
    }

    @Override
    public String toString() {
        return "EquipmentManagement{" +
                "classRoomID=" + classRoom.getRoomId() +
                ", classRoomName=" + classRoom.getRoomName() +
                ", equipmentID=" + equipment.geteID() +
                ", equipmentName=" + equipment.geteName() +
                ", numOfSupply=" + numOfSupply +
                ", state='" + state + '\'' +
                ", totalSupply=" + totalSupply +
                '}';
    }
}
