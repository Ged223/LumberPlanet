package sk.po.spse.lumberplanet;

public class Game {

    private int money;
    private int toothpicks;

    public Game(int money, int toothpicks) {
        this.money = money;
        this.toothpicks = toothpicks;
    }

    public Game() {
        this(0, 0);
    }

    public void craftToothpick() {
        this.toothpicks++;
    }

    public boolean sellToothpick() {
        if (toothpicks <= 0) {
            return false;
        }
        this.toothpicks--;
        this.money++;
        return true;
    }

    public int getMoney() {
        return money;
    }

    public int getToothpicks() {
        return toothpicks;
    }
}
