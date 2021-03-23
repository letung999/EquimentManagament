package PracticeAfterLearn.Chuong2.Bai7.Model;

public class Equipment {
    /**
     * eID, eName, origin, year
     */
    private static int id = 10000000;
    private int eID;
    private String eName;
    private String origin;
    private String year;

    public Equipment(int eID) {
        this.eID = eID;
    }

    public Equipment(int eID, String eName, String origin, String year) {
        if(eID == 0) this.eID = id;
        else this.eID = eID;
        this.eName = eName;
        this.origin = origin;
        this.year = year;
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        Equipment.id = id;
    }

    public int geteID() {
        return eID;
    }

    public void seteID(int eID) {
        this.eID = eID;
    }

    public String geteName() {
        return eName;
    }

    public void seteName(String eName) {
        this.eName = eName;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Equipment{" +
                "eID=" + eID +
                ", eName='" + eName + '\'' +
                ", origin='" + origin + '\'' +
                ", year='" + year + '\'' +
                '}';
    }
}
