package PracticeAfterLearn.Chuong2.Bai7.View;

import PracticeAfterLearn.Chuong2.Bai7.Controller.ControllerUtility;
import PracticeAfterLearn.Chuong2.Bai7.Controller.DataController;
import PracticeAfterLearn.Chuong2.Bai7.Model.ClassRoom;
import PracticeAfterLearn.Chuong2.Bai7.Model.Equipment;
import PracticeAfterLearn.Chuong2.Bai7.Model.EquipmentManagement;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class View {
    public static void main(String[] args) {
        var equipmentFileName = "EQUIPMENT.DAT";
        var classRomFileName = "CLASSROOM.DAT";
        var eqmFileName = "EQM.DAT";
        var dataController = new DataController();
        var controllerUtility = new ControllerUtility();
        var equipments = new ArrayList<Equipment>();
        var classRooms = new ArrayList<ClassRoom>();
        var eqms = new ArrayList<EquipmentManagement>();
        Scanner scanner = new Scanner(System.in);
        int option;
        do {
            System.out.println("****************************MENU****************************");
            System.out.println("1.Add Equipment To File");
            System.out.println("2.Show Information Equipment In File");
            System.out.println("3.Add ClassRoom To File");
            System.out.println("4.Show Information ClassRoom In File");
            System.out.println("5.Conduct Equipment Management In File");
            System.out.println("6.Show Information EQM in file");
            System.out.println("7.Sort, Search, Delete");
            System.out.println("0.exit!");
            System.out.println("you choose");
            option = scanner.nextInt();
            scanner.nextLine();
            switch (option) {
                case 0: {
                    System.out.println("exit!");
                    break;
                }
                case 1: {
                    checkAscendingIDEquipment(dataController, equipmentFileName);
                    String eName, origin, year;
                    int or;
                    System.out.println("Input equipment Name");
                    eName = scanner.nextLine();
                    do {
                        System.out.println("Input Origin:");
                        System.out.println("1.Import\n2.inland\n3.venture");
                        System.out.println("your option");
                        String[] ors = {"Import", "inland", "venture"};
                        or = scanner.nextInt();
                        if (or > 3 || or < 1) {
                            System.out.println("please choose options in List");
                        } else {
                            origin = ors[or - 1];
                            break;
                        }
                    } while (true);
                    System.out.println("Input year");
                    scanner.nextLine();
                    year = scanner.nextLine();
                    Equipment equipment = new Equipment(0, eName, origin, year);
                    dataController.writeEquipmentToFile(equipment, equipmentFileName);
                    break;
                }
                case 2: {
                    equipments = dataController.readEquipmentFromFile(equipmentFileName);
                    System.out.println("*****************Information Equipment In File************************");
                    show(equipments);
                    break;
                }
                case 3: {
                    /**
                     *  roomID, roomName, numberOfSeats
                     */
                    checkAscendingIDClassRoom(dataController, classRomFileName);
                    String roomName;
                    int numberOfSeats;
                    do {
                        String regex = "[0-9]{3}-[A-Z]{1}[0-9]{1}$";
                        System.out.println("Input roomName");
                        roomName = scanner.nextLine();
                        Pattern pattern = Pattern.compile(regex);
                        Matcher matcher = pattern.matcher(roomName);
                        if (matcher.find()) {
                            break;
                        } else {
                            System.out.println("please Input roomName with format true");
                        }

                    } while (true);
                    System.out.println("Input numberOfSeats");
                    numberOfSeats = scanner.nextInt();
                    ClassRoom classRoom = new ClassRoom(0, roomName, numberOfSeats);
                    dataController.writeClassRomToFile(classRoom, classRomFileName);
                    break;
                }
                case 4: {
                    System.out.println("*****************Information Classroom In File**********************");
                    classRooms = dataController.readClassRoomFromFile(classRomFileName);
                    show(classRooms);
                    break;
                }
                case 5: {
                    /**
                     * ClassRoom classRoom, Equipment equipment, int numOfSupply, String state, int totalSupply
                     */
                    classRooms = dataController.readClassRoomFromFile(classRomFileName);
                    equipments = dataController.readEquipmentFromFile(equipmentFileName);
                    eqms = dataController.readEQMFromFile(eqmFileName);
                    int classRoomID, equipmentId;
                    boolean checkExitClassRoomID = false;
                    boolean checkExitEquipmentID = false;
                    do {
                        System.out.println("**************Information classRoom In File****************");
                        show(classRooms);
                        System.out.println("input classroomID, input 0 to break");
                        classRoomID = scanner.nextInt();
                        if (classRoomID == 0) {
                            break;
                        }
                        checkExitClassRoomID = checkExitCl(classRoomID, classRooms);
                        if (checkExitClassRoomID == false) {
                            System.out.println("please choose Id exited in file");
                        } else {
                            break;
                        }
                    } while (true);
                    do {
                        equipments = dataController.readEquipmentFromFile(equipmentFileName);
                        System.out.println("*************Information Equipment In File****************");
                        show(equipments);
                        System.out.println("input equipmentID, input 0 to break");
                        equipmentId = scanner.nextInt();
                        if (equipmentId == 0) {
                            break;
                        }
                        checkExitEquipmentID = checkExitE(equipmentId, equipments);
                        if (checkExitEquipmentID == false) {
                            System.out.println("please choose Id exited in file");
                        } else {
                            break;
                        }
                    } while (true);
                    int totalOfSupply = getTotal(classRoomID, equipmentId, eqms);
                    System.out.println("input numOfSupply Equipment (supplyed " + totalOfSupply + ")");
                    int numOfSupply = scanner.nextInt();
                    totalOfSupply += numOfSupply;
                    System.out.println("Input state Equipment ");
                    scanner.nextLine();
                    String state = scanner.nextLine();
                    ClassRoom currentClassRoom = getClassRoom(classRoomID, classRooms);
                    Equipment currentEquipment = getEquipment(equipmentId, equipments);
                    EquipmentManagement equipmentManagement = new EquipmentManagement(currentClassRoom,
                            currentEquipment, totalOfSupply, state, 0);
                    controllerUtility.updateFile(equipmentManagement, eqms);
                    dataController.updateFile(eqms, eqmFileName);
                    show(eqms);
                    break;
                }
                case 6: {
                    System.out.println("********************Information EQM In File **********************");
                    eqms = dataController.readEQMFromFile(eqmFileName);
                    show(eqms);
                    break;
                }
                case 7: {
                    eqms = dataController.readEQMFromFile(eqmFileName);
                    equipments = dataController.readEquipmentFromFile(equipmentFileName);
                    int choose;
                    do {
                        System.out.println("***********************Menu********************");
                        System.out.println("1.Search Name equipment");
                        System.out.println("2.Sort By Name ClassRoom");
                        System.out.println("3.Sort By Total Equipment");
                        System.out.println("4.Delete classRoom");
                        System.out.println("0.return main menu");
                        System.out.println("your option!");
                        choose = scanner.nextInt();
                        scanner.nextLine();
                        if (choose == 0) {
                            break;
                        }
                        switch (choose) {
                            case 1: {
                                System.out.println("input name equipment to Search");
                                String key = scanner.nextLine();
                                var result = new ArrayList<Equipment>();
                                result = controllerUtility.searchNameEquipment(key, equipments);
                                if (result.size() == 0) {
                                    System.out.println("no find equipment");
                                } else {
                                    show(result);
                                }
                                break;
                            }
                            case 2:{
                                controllerUtility.sortByNameClassRoom(eqms);
                                show(eqms);
                                break;
                            }
                            case 3:{
                              controllerUtility.getTotalEquipment(eqms);
                              controllerUtility.sortByTotalSupply(eqms);
                              show(eqms);
                              break;
                            }
                            case 4:{
                                System.out.println("Input classRoomID and Equipment to delete");
                                int classRoomID, equipmentID;
                                classRoomID = scanner.nextInt();
                                equipmentID = scanner.nextInt();
                                controllerUtility.deleteElementInfile(classRoomID, equipmentID, eqms);
                                break;
                            }
                        }
                    }while (choose != 0);


                }
            }

        } while (option != 0);

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

    private static int getTotal(int classRoomID, int equipmentId, ArrayList<EquipmentManagement> eqms) {
        for (var x : eqms) {
            if (x.getEquipment().geteID() == equipmentId && x.getClassRoom().getRoomId() == classRoomID) {
                return x.getNumOfSupply();
            }
        }
        return 0;

    }

    private static boolean checkExitE(int equipmentId, ArrayList<Equipment> equipments) {
        for (var x : equipments) {
            if (x.geteID() == equipmentId) {
                return true;
            }

        }
        return false;
    }

    private static boolean checkExitCl(int classRoomID, ArrayList<ClassRoom> classRooms) {
        for (var x : classRooms) {
            if (x.getRoomId() == classRoomID) {
                return true;
            }
        }
        return false;
    }

    private static void checkAscendingIDClassRoom(DataController dataController, String classRomFileName) {
        var classRooms = dataController.readClassRoomFromFile(classRomFileName);
        if (classRooms.size() == 0) {
            /**
             * do nothing
             */
        } else {
            ClassRoom.setId(classRooms.get(classRooms.size() - 1).getRoomId() + 1);
        }
    }

    private static void checkAscendingIDEquipment(DataController dataController, String equipmentFileName) {
        var equipments = dataController.readEquipmentFromFile(equipmentFileName);
        if (equipments.size() == 0) {
            /**
             * do nothing
             */
        } else {
            Equipment.setId(equipments.get(equipments.size() - 1).geteID() + 1);
        }
    }

    public static void show(ArrayList list) {
        for (var x : list) {
            System.out.println(x);
        }
    }
}
