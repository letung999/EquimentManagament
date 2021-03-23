package PracticeAfterLearn.Chuong2.Bai7.Controller;

import PracticeAfterLearn.Chuong2.Bai7.Model.ClassRoom;
import PracticeAfterLearn.Chuong2.Bai7.Model.Equipment;
import PracticeAfterLearn.Chuong2.Bai7.Model.EquipmentManagement;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class DataController {
    private FileWriter fileWriter;
    private BufferedWriter bufferedWriter;
    private PrintWriter printWriter;
    private Scanner scanner;

    /**
     * write
     */
    public void openFileToWrite(String fileName) {
        try {
            fileWriter = new FileWriter(fileName, true);
            bufferedWriter = new BufferedWriter(fileWriter);
            printWriter = new PrintWriter(bufferedWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeFileAfterWrite(String fileName) {
        try {
            printWriter.close();
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeEquipmentToFile(Equipment equipment, String fileName) {
        /**
         * int eID, String eName, String origin, String year
         */
        openFileToWrite(fileName);
        printWriter.println(equipment.geteID() + "|" + equipment.geteName() + "|" + equipment.getOrigin() + "|" + equipment.getYear());
        closeFileAfterWrite(fileName);

    }

    public void writeClassRomToFile(ClassRoom classRoom, String fileName) {
        /**
         * roomID, roomName, numberOfSeats
         */
        openFileToWrite(fileName);
        printWriter.println(classRoom.getRoomId() + "|" + classRoom.getRoomName() + "|" + classRoom.getNumberOfSeats());
        closeFileAfterWrite(fileName);
    }

    public void writeEQMToFile(EquipmentManagement eqm, String fileName) {
        /**
         * ClassRoom classRoom, Equipment equipment, int numOfSupply, String state, int totalSupply
         */
        openFileToWrite(fileName);
        printWriter.println(eqm.getClassRoom().getRoomId()
                + "|" + eqm.getEquipment().geteID() + "|" + eqm.getNumOfSupply() + "|" + eqm.getState());
        closeFileAfterWrite(fileName);
    }

    /**
     * read
     */
    public void openFileToRead(String fileName) {
        try {
            File file = new File(fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
            scanner = new Scanner(Paths.get(fileName), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeFileAfterRead(String fileName) {
        scanner.close();
    }

    public ArrayList<Equipment> readEquipmentFromFile(String fileName) {
        var equipments = new ArrayList<Equipment>();
        openFileToRead(fileName);
        while (scanner.hasNextLine()) {
            String data = scanner.nextLine();
            Equipment equipment = convertDataFromEquipment(data);
            equipments.add(equipment);
        }
        closeFileAfterRead(fileName);
        return equipments;
    }

    private Equipment convertDataFromEquipment(String data) {
        /**
         * int eID, String eName, String origin, String year
         */
        String[] datas = data.split("\\|");
        Equipment equipment = new Equipment(Integer.parseInt(datas[0]), datas[1], datas[2], datas[3]);
        return equipment;

    }

    public ArrayList<ClassRoom> readClassRoomFromFile(String fileName) {
        var classRooms = new ArrayList<ClassRoom>();
        openFileToRead(fileName);
        while (scanner.hasNextLine()) {
            String data = scanner.nextLine();
            ClassRoom classRoom = convertDataFromClassRoom(data);
            classRooms.add(classRoom);
        }
        closeFileAfterRead(fileName);
        return classRooms;
    }

    private ClassRoom convertDataFromClassRoom(String data) {
        /**
         * int roomId, String roomName, int numberOfSeats
         */
        String[] datas = data.split("\\|");
        ClassRoom classRoom = new ClassRoom(Integer.parseInt(datas[0]), datas[1], Integer.parseInt(datas[2]));
        return classRoom;
    }

    public ArrayList<EquipmentManagement> readEQMFromFile(String fileName) {
        var eqms = new ArrayList<EquipmentManagement>();
        var equipments = readEquipmentFromFile("EQUIPMENT.DAT");
        var classRooms = readClassRoomFromFile("CLASSROOM.DAT");
        openFileToRead(fileName);
        while (scanner.hasNextLine()) {
            String data = scanner.nextLine();
            EquipmentManagement equipmentManagement = convertDataFromEQM(data, classRooms, equipments);
            eqms.add(equipmentManagement);
        }
        closeFileAfterRead(fileName);
        return eqms;
    }

    private EquipmentManagement convertDataFromEQM(String data, ArrayList<ClassRoom> classRooms, ArrayList<Equipment> equipments) {
        /**
         * eqm.getClassRoom().getRoomId()
         *                 + "|" + eqm.getEquipment().geteID() + "|" + eqm.getNumOfSupply() + "|" + eqm.getState()
         */
        String[] datas = data.split("\\|");
        EquipmentManagement equipmentManagement = new EquipmentManagement(getClassRoom(Integer.parseInt(datas[0]), classRooms),
                getEquipment(Integer.parseInt(datas[1]), equipments), Integer.parseInt(datas[2]), datas[3], 0);
        return equipmentManagement;
    }

    public void updateFile(ArrayList<EquipmentManagement> eqms, String eqmFileName) {
        File file = new File(eqmFileName);
        if (file.exists()) {
            file.delete();
        }
        openFileToWrite(eqmFileName);
        for (var eqm : eqms) {
            printWriter.println(eqm.getClassRoom().getRoomId()
                    + "|" + eqm.getEquipment().geteID() + "|" + eqm.getNumOfSupply() + "|" + eqm.getState());
        }
        closeFileAfterWrite(eqmFileName);
    }

    private static Equipment getEquipment(int equipmentId, ArrayList<Equipment> equipments) {
        for (int i = 0; i < equipments.size(); ++i) {
            if (equipmentId == equipments.get(i).geteID()) {
                return equipments.get(i);
            }
        }
        return null;
    }

    private static ClassRoom getClassRoom(int classRoomID, ArrayList<ClassRoom> classRooms) {
        for (int i = 0; i < classRooms.size(); ++i) {
            if (classRoomID == classRooms.get(i).getRoomId()) {
                return classRooms.get(i);
            }
        }
        return null;
    }


}
