package day07;

public class Day07 {
    public static void main(String[] args) {
        CrabController crabController = new CrabController();
        System.out.println(crabController.guessSmallestCost(true));
        System.out.println(crabController.guessSmallestCost(false));
    }
}
