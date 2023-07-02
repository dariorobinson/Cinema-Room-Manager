package cinema;

public class Statistics {
    private static int currentIncome;
    public static void showStatistics(String[][] cinema) {

        int purchaseCount = 0;
        int rowBySeats = cinema.length * cinema[0].length;
        int total_sales;

        int first_half = (cinema.length / 2) * cinema[0].length;

        int second_half = rowBySeats - first_half;

        if (rowBySeats <= 60) {
            total_sales = rowBySeats * 10;
        } else {
            total_sales = (first_half * 10) + (second_half * 8);
        }

        for(String[] row: cinema) {
            for(String seat: row) {
                if (seat.replaceAll(" ", "").equals("B")) {
                    purchaseCount++;
                }
            }
        }

        String percentage = String.format("%.2f", (double) purchaseCount / rowBySeats * 100);
        System.out.printf("Number of purchased tickets: %d", purchaseCount);
        System.out.println("");
        System.out.println("Percentage: " + percentage + "%");
        System.out.printf("Current income: $%d", getCurrentIncome());
        System.out.println("");
        System.out.printf("Total income: $%d", total_sales);
        System.out.println("");

    }

    public void increaseCurrentIncome(int num) {
        currentIncome += num;
    }

    public static int getCurrentIncome() {
        return currentIncome;
    }


}
