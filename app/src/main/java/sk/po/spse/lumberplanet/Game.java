package sk.po.spse.lumberplanet;

public class Game {

    private long money;
    private long toothpicks;
    private long wood;
    private long woodCapacity;
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
        this.wood = 10;
        this.woodCapacity = 100;
    }

    public Game() {
        this(0, 0, 0, 0);
    }

    public void advance() {
        advance(1);
    }

    public void advance(long amount) {
        craftToothpick(vyrabac * amount);
        sellToothpick(predavac * amount);
    }

    public void craftToothpick(long amount) {
        if(amount > wood){
            amount = wood;
        }
        toothpicks = toothpicks + amount;
        wood = wood - amount;
    }

    public void craftToothpick() {
        craftToothpick(1);
    }

    private boolean payMoney(int amount) {
        if (amount > money) {
            return false;
        }

        money = money - amount;
        return true;
    }

    public void sellToothpick(long amount) {
        if (toothpicks < amount) {
            amount = toothpicks;
        }
        toothpicks = toothpicks - amount;
        money = money + amount;

    }

    public void sellToothpick() {
        sellToothpick(1);
    }

    public void buyVyrabac() {
        if (payMoney(vyrabacPrice)) {
            vyrabac++;
            vyrabacPrice *= 1.2;
        }
    }

    public void buyPredavac() {
        if (payMoney(predavacPrice)) {
            predavac++;
            predavacPrice *= 1.2;
        }
    }


    public void buyWood() {
        int amountToBuy = 2;// 2 is amount of wood bought with one button press
        while(wood + amountToBuy > woodCapacity || amountToBuy == 0){
            amountToBuy--;
        }

        if (payMoney(1)) {//1 is the price of one wood buy
            wood = wood + amountToBuy;
        }
    }

    public long getWood() {
        return wood;
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
