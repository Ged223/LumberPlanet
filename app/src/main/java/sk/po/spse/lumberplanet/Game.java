package sk.po.spse.lumberplanet;

public class Game {
    //in cents, 100 = $1.00
    int money;
    int toothpicks;

    public Game(int money, int toothpicks) {
        this.money = money;
        this.toothpicks = toothpicks;
    }

    public Game(){
        this(0,0);
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getToothpicks() {
        return toothpicks;
    }

    public void setToothpicks(int toothpicks) {
        this.toothpicks = toothpicks;
    }
}
