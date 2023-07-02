package cinema;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Scanner;

public class Cinema {

    static Scanner scan = new Scanner(System.in);
    static String[][] cinema;

    static Statistics stat = new Statistics();

    public static void main(String[] args) {
        // Write your code here
        start();

    }

    public static void start() {
        System.out.println("Enter the number of rows:");
        System.out.print("> ");
        int rows = Integer.valueOf(scan.nextLine());
        System.out.println("Enter the number of seats in each row:");
        System.out.print("> ");
        int seats = Integer.valueOf(scan.nextLine());

        cinema = new String[rows][seats];

        for (String[] arr: cinema) {
            for (int i = 0; i < arr.length; i++) {
                arr[i] = "S ";
            }
        }
        showMenu(cinema, rows, seats);
    }

    public static void printCinema(String[][] cinema) {
        System.out.println("Cinema:");
        String cinemaHeader = "  ";
        for (int i = 1; i <= cinema[0].length; i++) {
            cinemaHeader += " " + i;
        }
        System.out.println(" " + cinemaHeader);

        int rowStart = 1;
        for (String[] stringArr: cinema) {
            String cinemaRows = "  ";
            cinemaRows += rowStart + " ";

            for(int i = 0; i < stringArr.length; i++) {
                cinemaRows += stringArr[i];
            }
            System.out.println(cinemaRows);
            rowStart++;
        }
    }

    public static void askForRowAndSeatLessThan60(String[][] cinema) {
        int ticketPrice = 10;
        while(true) {

            try {
                System.out.println("Enter a row number:");
                System.out.print("> ");
                int rowNum = Integer.parseInt(scan.nextLine());
                System.out.println("Enter a seat number in that row:");
                System.out.print("> ");
                int seatNum = Integer.parseInt(scan.nextLine());
                if(cinema[rowNum -1][seatNum - 1].replaceAll(" ", "").equals("B")) {
                    System.out.println("That ticket has already been purchased!");
                } else {
                    System.out.println("");
                    System.out.println("Ticket price: $" + ticketPrice);
                    stat.increaseCurrentIncome(ticketPrice);
                    cinema[rowNum - 1][seatNum - 1] = "B ";
                    break;
                }
            } catch(ArrayIndexOutOfBoundsException e) {
                System.out.println("Wrong input!");
            }
        }

    }

    public static void askForRowAndSeatMoreThan60(String[][] cinema) {
        int ticketPrice = 10;

        while(true) {
            try {
                System.out.println("Enter a row number:");
                System.out.print("> ");
                int rowNum = Integer.parseInt(scan.nextLine());
                System.out.println("Enter a seat number in that row:");
                System.out.print("> ");
                int seatNum = Integer.parseInt(scan.nextLine());
                if(cinema[rowNum -1][seatNum - 1].replaceAll(" ", "").equals("B")) {
                    System.out.println("That ticket has already been purchased!");
                    System.out.println("");
                } else {
                    if (rowNum <= cinema.length / 2) {
                        System.out.println("");
                        System.out.println("Ticket price: $" + ticketPrice);
                        stat.increaseCurrentIncome(ticketPrice);
                        cinema[rowNum - 1][seatNum - 1] = "B ";
                        break;
                    } else if (rowNum >= cinema.length / 2) {
                        ticketPrice = 8;
                        System.out.println("Ticket price: $" + ticketPrice);
                        stat.increaseCurrentIncome(ticketPrice);
                        cinema[rowNum - 1][seatNum - 1] = "B ";
                        break;
                    }
                }
            } catch(ArrayIndexOutOfBoundsException e) {
                System.out.println("Wrong input!");
                System.out.println("");
            }
        }
    }

    public static void showMenu(String[][] cinema, int rows, int seats) {
        while(true) {
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");
            System.out.print("> ");
            int menu = Integer.valueOf(scan.nextLine());
            if (menu == 0) {
                break;
            } else if (menu == 1) {
                printCinema(cinema);
            } else if (menu == 2) {
                if (rows * seats <= 60) {
                    askForRowAndSeatLessThan60(cinema);
                } else {
                    askForRowAndSeatMoreThan60(cinema);
                }
            } else if (menu == 3) {
                stat.showStatistics(cinema);
            } else {
                System.out.println("Wrong input!");
            }
        }
    }
}