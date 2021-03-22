package sk.po.spse.lumberplanet;

public class Game{

    private int money;
    private int toothpicks;
    private int vyrabac;
    private int predavac;
    private int vyrabacPrice;
    private int predavacPrice;

    public Game(int money, int toothpicks, int vyrabac, int predavac) {
        this.money = money;
        this.toothpicks = toothpicks;
        this.vyrabac = vyrabac;
        this.predavac = predavac;
        this.vyrabacPrice = 250;
        this.predavacPrice = 100;
    }

    public Game() {
        this(0, 0, 0, 0);
    }

    public void advance(){
        craftToothpick(vyrabac);
        sellToothpick(predavac);
    }

    public void craftToothpick(int amount){
        toothpicks = toothpicks + amount;
    }
    public void craftToothpick() {
        craftToothpick(1);
    }

    public void sellToothpick(int amount){
        if(toothpicks < amount) {
            amount = toothpicks;
        }
        toothpicks = toothpicks - amount;
        money = money + amount;

    }
    public void sellToothpick() {
        sellToothpick(1);
    }

    public void buyVyrabac() {
        if (money<vyrabacPrice){
            return;
        }
        money-=vyrabacPrice;
        vyrabac++;
        vyrabacPrice*=1.2;
    }

    public void buyPredavac() {
        if (money<predavacPrice){
            return;
        }
        money-=predavacPrice;
        predavac++;
        predavacPrice*=1.2;
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

    public int getVyrabacPrice() {
        return vyrabacPrice;
    }

    public int getPredavacPrice() {
        return predavacPrice;
    }
}
