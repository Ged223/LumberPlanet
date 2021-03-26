package sk.po.spse.lumberplanet;

public class Game{

    private long money;
    private long toothpicks;
    private int vyrabac;
    private int predavac;
    private int vyrabacPrice;
    private int predavacPrice;
    private long leaveTime;

    public Game(int money, int toothpicks, int vyrabac, int predavac) {
        this.money = money;
        this.toothpicks = toothpicks;
        this.vyrabac = vyrabac;
        this.predavac = predavac;
        this.vyrabacPrice = 100;
        this.predavacPrice = 100;
    }

    public Game() {
        this(0, 0, 0, 0);
    }

    public void advance(){
        advance(1);
    }
    public void advance(long amount){
        craftToothpick(vyrabac*amount);
        sellToothpick(predavac*amount);
    }

    public void craftToothpick(long amount){
        toothpicks = toothpicks + amount;
    }
    public void craftToothpick() {
        craftToothpick(1);
    }

    public void sellToothpick(long amount){
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

    public long getMoney() {
        return money;
    }

    public long getToothpicks() {
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

    public long getLeaveTime() {
        return leaveTime;
    }

    public void setLeaveTime(long leaveTime) {
        this.leaveTime = leaveTime;
    }
}
