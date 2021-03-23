package PracticeAfterLearn.Chuong2.Bai7;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class test {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String roomName;
        int numberOfSeats;
        do {
            String regex = "[0-9]{3}-[A-Z]{1}[0-9]{1}$";
            System.out.println("Input roomName");
            roomName = scanner.nextLine();
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(roomName);
            if(matcher.find()){
                break;
            }
            else {
                System.out.println("please Input roomName with format true");
            }

        }while (true);
    }
}
