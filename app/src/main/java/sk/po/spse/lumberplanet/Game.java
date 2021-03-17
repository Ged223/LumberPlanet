package sk.po.spse.lumberplanet;

public class Game {

    private int money;
    private int toothpicks;
    private int vyrabac;
    private int predavac;

    public Game(int money, int toothpicks, int vyrabac, int predavac) {
        this.money = money;
        this.toothpicks = toothpicks;
        this.vyrabac = vyrabac;
        this.predavac = predavac;
    }

    public Game() {
        this(0, 0,0,0);
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

    public int getVyrabac() {
        return vyrabac;
    }

    public int getPredavac() {
        return predavac;
    }
}
