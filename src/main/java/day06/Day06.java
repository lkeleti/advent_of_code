package day06;

public class Day06 {
    public static void main(String[] args) {
        LanternfishController lanternfishController = new LanternfishController();
        System.out.println(lanternfishController.guessNumberOfFish());

        Lanternv2 lanternv2 = new Lanternv2();
        System.out.println(lanternv2.guessNumberOfFish());
    }
}
