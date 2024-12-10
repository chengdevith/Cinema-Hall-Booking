import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //Kdor Thom
        Scanner input = new Scanner(System.in);
        int rows, cols,options;
        String seatCode;
        String[] seats;
        char c ='A';
        System.out.println("Enter the number of Seats");
        System.out.print("[+] Insert row: ");
        rows = input.nextInt();
        System.out.print("[+] Insert column: ");
        cols = input.nextInt();
        String[][] hall = new String[rows][cols];
        String[][] bookingHistory = new String[rows*cols][2];
        int bookingCount = 0;
        System.out.println(" _     ______    ____  ____  _     _____   _____  ____    ____  _  _     ______     ____ \n" +
                "/ \\  //  __/ \\  /   _\\/  _ \\/ \\__//  __/  /__ __\\/  _ \\  /   _\\/ \\/ \\  //  __/ \\__//  _ \\\n" +
                "| |  ||  \\ | |  |  /  | / \\|| |\\/||  \\      / \\  | / \\|  |  /  | || |\\ ||  \\ | |\\/|| / \\|\n" +
                "| |/\\||  /_| |_/\\  \\_ | \\_/|| |  ||  /_     | |  | \\_/|  |  \\__| || | \\||  /_| |  || |-||\n" +
                "\\_/  \\\\____\\____|____/\\____/\\_/  \\\\____\\    \\_/  \\____/  \\____/\\_/\\_/  \\\\____\\_/  \\\\_/ \\|\n" +
                "                                                                                         ");
        for (int i = 0; i < rows; i++) {
            System.out.print("[");
            for (int j = 0; j < cols; j++) {
                hall[i][j] = c+"-"+(j+1)+": "+"AV";
                System.out.print(hall[i][j]);
                if (j<cols-1){
                    System.out.print(", ");
                }
            }
            System.out.println("]");
            c++;
        }
        do {
            System.out.println("1.Booking seat");
            System.out.println("2.Cancel booked seat");
            System.out.println("3.View Hall");
            System.out.println("4.View the history of booked seat");
            System.out.println("5.Exit");
            System.out.println("Choose an option: ");
            options = input.nextInt();
            switch (options) {
                case 1:{
                    System.out.println("Enter the seat code to booking the seat: ");
                    input.nextLine();
                    seatCode = input.nextLine();
                    boolean isFound = false;

                    for (int i = 0; i < rows; i++) {
                        for (int j = 0; j < cols; j++) {
                            seats = hall[i][j].split(":");
                            if (seatCode.equals(seats[0])) {
                                if (seats[1].trim().equals("AV")) {
                                    isFound = true;
                                    hall[i][j] = seats[0] +": BO";

                                    LocalDateTime now = LocalDateTime.now();
                                    String formattedDate = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                                    bookingHistory[bookingCount][0] =seats[0];
                                    bookingHistory[bookingCount][1] =formattedDate;
                                    bookingCount++;

                                    System.out.println("Seat " + seats[0] + " successfully booked. ");

                                }
                            }
                        }
                    }
                    if(!isFound){
                        System.out.println("Invalid Seat Code");
                    }
                    break;
                }
                case 2:{
                    System.out.println("Enter the seat code to canceling the seat: ");
                    input.nextLine();
                    seatCode = input.nextLine();
                    boolean isFound = false;
                    for (int i = 0; i < rows; i++) {
                        for (int j = 0; j < cols; j++) {
                            seats = hall[i][j].split(":");
                            if (seatCode.equals(seats[0])) {
                                if (seats[1].trim().equals("BO")) {
                                    isFound = true;
                                    hall[i][j] = seats[0] +": AV";
                                    System.out.println("Seat " + seats[0] + " successfully cancelled. ");
                                }
                            }
                        }
                    }
                    if(!isFound){
                        System.out.println("Invalid Seat Code");
                    }
                    break;}
                case 3:{
                    for (int i = 0; i < rows; i++) {
                        System.out.print("[");
                        for (int j = 0; j < cols; j++) {
                            System.out.print(hall[i][j]);
                            if (j<cols-1){
                                System.out.print(", ");
                            }
                        }
                        System.out.println("]");
                    }
                    break;
                }
                case 4:{
                    System.out.println("Booking History: ");
                    if (bookingCount == 0){
                        System.out.println("No booking yet.");
                    }else{
                        for (int i = 0; i < bookingCount; i++) {
                            System.out.println("Seat: " + bookingHistory[i][0] + ", Date: " + bookingHistory[i][1]);
                        }
                        System.out.println("Total Booking Count: " + bookingCount);
                    }
                    break;
                }

            }
        }while(options != 5);
    }

}