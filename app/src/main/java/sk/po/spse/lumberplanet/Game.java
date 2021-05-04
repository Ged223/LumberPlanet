package sk.po.spse.lumberplanet;

public class Game {

    private long money;
    private long toothpicks;
    private long wood;
    private int vyrabac;
    private int predavac;
    private int vyrabacPrice;
    private int predavacPrice;
    private long leaveTime;
    private int craftButtonMod;
    private int sellButtonMod;
    private int vyrabacMod;
    private int predavacMod;
    private double nextVyrabacPriceMod;
    private double nextPredavacPriceMod;
    private int toothpickPrice;
    private int woodAmountBought;
    private long lastFoundWood;
    private boolean[] upgradesBought;
    private int[] upgradesPrices;
    private String[] upgradesText;


    public Game(int money, int toothpicks, int vyrabac, int predavac) {
        this.money = money;
        this.toothpicks = toothpicks;
        this.vyrabac = vyrabac;
        this.predavac = predavac;
        this.vyrabacPrice = 100;
        this.predavacPrice = 100;
        this.wood = 10;
        this.craftButtonMod = 1;
        this.sellButtonMod = 1;
        this.vyrabacMod = 1;
        this.predavacMod = 1;
        this.nextVyrabacPriceMod = 1.2;
        this.nextPredavacPriceMod = 1.2;
        this.toothpickPrice = 1;
        this.woodAmountBought = 2;
        this.lastFoundWood = System.currentTimeMillis();
        this.upgradesBought = new boolean[99];
        this.upgradesPrices = new int[]{
                100,
                200,
                300,
                100,
                100,
                100,
                100,
                1000,
        };//here set prices for upgrades
        this.upgradesText = new String[]{
                "Wood bought: x2\nPrice: " + upgradesPrices[0],
                "Manual crafting x2\nPrice: " + upgradesPrices[1],
                "Manual selling x2\nPrice: " + upgradesPrices[2],
                "Wood bought: x2\nPrice: " + upgradesPrices[3],
                "Wood bought: x2\nPrice: " + upgradesPrices[4],
                "Wood bought: x2\nPrice: " + upgradesPrices[5],
                "Wood bought: x2\nPrice: " + upgradesPrices[6],
                "Crafter and Seller 200%: " + upgradesPrices[7],
        };
    }


    public Game() {
        this(0, 0, 0, 0);
    }

    public void advance() {
        advance(1);
    }

    public void advance(long amount) {
        craftToothpick((vyrabac * vyrabacMod) * amount);
        sellToothpick((predavac * predavacMod) * amount);
    }

    public boolean isUpgradeVisible(int index) {
        switch (index) {
            case 0: //Wood bought: x2\nPrice: $100
                return (vyrabac >= 1 && predavac >= 1);//condition for being able to buy upgrade0
            case 1: //Manual crafting x2\nPrice: $200
                return (vyrabac >= 2 && predavac >= 2);
            case 2: //Manual selling x2\nPrice: $300
                return (vyrabac >= 3 && predavac >= 3);
            case 3: //Wood bought: x2\nPrice: $500
                return (vyrabac >= 2 && predavac >= 2);
            case 4: //Wood bought: x2\nPrice: $500
                return (vyrabac >= 3 && predavac >= 3);
            case 5: //Wood bought: x2\nPrice: $500
                return (vyrabac >= 4 && predavac >= 4);
            case 6: //Wood bought: x2\nPrice: $500
                return (vyrabac >= 5 && predavac >= 5);
            case 7:
                return (vyrabac >= 6 && predavac >=6);
            default:
                return false;
        }
    }

    public void buyUpgrade(int index) {
        if (payMoney(upgradesPrices[index])) {
            switch (index) {
                case 0: //Wood bought: x2\nPrice: $100
                    woodAmountBought = woodAmountBought * 2; //effect of buying upgrade
                    break;
                case 1: //Manual crafting x2\nPrice: $200
                    craftButtonMod = craftButtonMod * 2;
                    break;
                case 2: //Manual selling x2\nPrice: $200
                    sellButtonMod = sellButtonMod * 2;
                    break;
                case 3: //Wood bought: x2\nPrice: $500
                    woodAmountBought = woodAmountBought * 2;
                    break;
                case 4: //Wood bought: x2\nPrice: $500
                    woodAmountBought = woodAmountBought * 2;
                    break;
                case 5: //Wood bought: x2\nPrice: $500
                    woodAmountBought = woodAmountBought * 2;
                    break;
                case 6: //Wood bought: x2\nPrice: $500
                    woodAmountBought = woodAmountBought * 2;
                    break;
                case 7:
                    vyrabacMod = vyrabacMod*2;
                    predavacMod = predavacMod*2;
                    break;
            }
            upgradesBought[index] = true;
        }
    }

    public void craftToothpick(long amount) {
        if (amount > wood) {
            amount = wood;
        }
        toothpicks = toothpicks + amount;
        wood = wood - amount;
    }

    public void craftToothpick() {
        craftToothpick(1);
    }

    public void craftButtonPressed() {
        craftToothpick(1 * craftButtonMod);
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
        money = money + (amount * toothpickPrice);

    }

    public void sellToothpick() {
        sellToothpick(1);
    }

    public void sellButtonPressed() {
        sellToothpick(1 * sellButtonMod);
    }

    public void buyVyrabac() {
        if (payMoney(vyrabacPrice)) {
            vyrabac++;
            vyrabacPrice *= nextVyrabacPriceMod;
        }
    }

    public void buyPredavac() {
        if (payMoney(predavacPrice)) {
            predavac++;
            predavacPrice *= nextPredavacPriceMod;
        }
    }


    public void buyWood() {
        if (payMoney(1)) {//1 is the price of one wood buy
            wood = wood + woodAmountBought;
        }
    }

    public void findWoodButtonPressed() {
        if (lastFoundWood + 5000 < System.currentTimeMillis()) {
            this.wood += 1;
            lastFoundWood = System.currentTimeMillis();
        }
    }

    public int getVyrabacMod() {
        return vyrabacMod;
    }

    public int getPredavacMod() {
        return predavacMod;
    }

    public String[] getUpgradesText() {
        return upgradesText;
    }

    public int[] getUpgradesPrices() {
        return upgradesPrices;
    }

    public boolean[] getUpgradesBought() {
        return upgradesBought;
    }

    public long getLastFoundWood() {
        return lastFoundWood;
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
