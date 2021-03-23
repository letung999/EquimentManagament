package PracticeAfterLearn.Chuong2.Bai7.Controller;

import PracticeAfterLearn.Chuong2.Bai7.Model.Equipment;
import PracticeAfterLearn.Chuong2.Bai7.Model.EquipmentManagement;

import java.util.ArrayList;

public class ControllerUtility {
    public void updateFile(EquipmentManagement equipmentManagement, ArrayList<EquipmentManagement> eqms) {
        boolean isUpdate = false;
        for (int i = 0; i < eqms.size(); ++i) {
            if (equipmentManagement.getClassRoom().getRoomId() == eqms.get(i).getClassRoom().getRoomId()
                    && equipmentManagement.getEquipment().geteID() == eqms.get(i).getEquipment().geteID()) {
                isUpdate = true;
                eqms.set(i, equipmentManagement);
            }
        }
        if (isUpdate == false) {
            eqms.add(equipmentManagement);
        }
    }

    public ArrayList<Equipment> searchNameEquipment(String key, ArrayList<Equipment> equipments) {
        var results = new ArrayList<Equipment>();
        String regex = ".*" + key.toLowerCase() + ".*";
        for (int i = 0; i < equipments.size(); ++i) {
            if (equipments.get(i).geteName().toLowerCase().matches(regex)) {
                results.add(equipments.get(i));
            }
        }
        return results;
    }

    public void sortByNameClassRoom(ArrayList<EquipmentManagement> eqms) {
        for (int i = 0; i < eqms.size(); ++i) {
            for (int j = eqms.size() - 1; j > i; j--) {
                EquipmentManagement ej = eqms.get(j);
                EquipmentManagement eej = eqms.get(j - 1);
                if (ej.getClassRoom().getRoomName().compareTo(eej.getClassRoom().getRoomName()) < 0) {
                    eqms.set(j - 1, ej);
                    eqms.set(j, eej);
                }
            }
        }
    }

    public ArrayList<EquipmentManagement> getTotalEquipment(ArrayList<EquipmentManagement> eqms) {
        for (int i = 0; i < eqms.size(); ++i) {
            int count = 0;
            EquipmentManagement equipment = eqms.get(i);
            for (int j = 0; j < eqms.size(); ++j) {
                if (equipment.getClassRoom().getRoomId() == eqms.get(j).getClassRoom().getRoomId()) {
                    count += eqms.get(j).getNumOfSupply();
                    equipment.setTotalSupply(count);
                    eqms.set(i, equipment);
                }
            }
        }
        return eqms;
    }

    public void sortByTotalSupply(ArrayList<EquipmentManagement> eqms) {
        for (int i = 0; i < eqms.size(); ++i) {
            for (int j = eqms.size() - 1; j > i; j--) {
                EquipmentManagement ej = eqms.get(j);
                EquipmentManagement eej = eqms.get(j - 1);
                if (ej.getTotalSupply() < eej.getTotalSupply()) {
                    eqms.set(j - 1, ej);
                    eqms.set(j, eej);
                }
            }
        }
    }

    public void deleteElementInfile(int classRoomID, int equipmentID, ArrayList<EquipmentManagement> eqms) {
        for (int i = 0; i < eqms.size(); ++i) {
            if (classRoomID == eqms.get(i).getClassRoom().getRoomId()
                    && equipmentID == eqms.get(i).getEquipment().geteID()) {
                eqms.remove(i);
            }
        }
    }
}
