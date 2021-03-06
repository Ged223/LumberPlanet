package sk.po.spse.lumberplanet;

public class Game {

    private long money;
    private long moneyMade;
    private long toothpicks;
    private long toothpicksCrafted;
    private long wood;
    private long woodBought;
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
    private int crafterAndSellersNeededForWoodBuyUpgrade;

    public Game(int money, int toothpicks, int vyrabac, int predavac) {
        this.money = money;
        this.moneyMade = money;
        this.toothpicks = toothpicks;
        this.vyrabac = vyrabac;
        this.predavac = predavac;
        this.vyrabacPrice = 100;
        this.predavacPrice = 100;
        this.wood = 10;
        this.woodBought = wood;
        this.craftButtonMod = 1;
        this.sellButtonMod = 1;
        this.vyrabacMod = 1;
        this.predavacMod = 1;
        this.nextVyrabacPriceMod = 1.2;
        this.nextPredavacPriceMod = 1.2;
        this.toothpickPrice = 1;
        this.toothpicksCrafted = 0;
        this.woodAmountBought = 2;
        this.crafterAndSellersNeededForWoodBuyUpgrade = 1;
        this.lastFoundWood = System.currentTimeMillis();
        this.upgradesBought = new boolean[99];
        this.upgradesPrices = new int[]{
                100,
                200,
                300,
                1000,
                0,
                0,
        };//here set prices for upgrades
        this.upgradesText = new String[]{
                "Wood bought: x2\nPrice: " + upgradesPrices[0],
                "Manual crafting x2\nPrice: " + upgradesPrices[1],
                "Manual selling x2\nPrice: " + upgradesPrices[2],
                "Crafter and Seller 200%: " + upgradesPrices[3],
                "Gift from sponsor: $10,000" + upgradesPrices[4],
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
                return (vyrabac >= crafterAndSellersNeededForWoodBuyUpgrade && predavac >= crafterAndSellersNeededForWoodBuyUpgrade);//condition for being able to buy upgrade0
            case 1: //Manual crafting x2\nPrice: $200
                return (vyrabac >= 2 && predavac >= 2);
            case 2: //Manual selling x2\nPrice: $3 00
                return (vyrabac >= 3 && predavac >= 3);
            case 3:
                return (vyrabac >= 6 && predavac >=6);
            case 4:
                return (moneyMade >= 1000);
            default:
                return false;
        }
    }

    public void buyUpgrade(int index) {
        if (payMoney(upgradesPrices[index])) {
            switch (index) {
                case 0: //Wood bought: x2\nPrice: $100
                    woodAmountBought *= 2; //effect of buying upgrade
                    crafterAndSellersNeededForWoodBuyUpgrade += 1;
                    upgradesPrices[0] *= 2;
                    upgradesText[0] = "Wood bought: x2\nPrice: " + upgradesPrices[0];
                    break;
                case 1: //Manual crafting x2\nPrice: $200
                    craftButtonMod = craftButtonMod * 2;
                    break;
                case 2: //Manual selling x2\nPrice: $200
                    sellButtonMod = sellButtonMod * 2;
                    break;
                case 3:
                    vyrabacMod = vyrabacMod*2;
                    predavacMod = predavacMod*2;
                    break;
                case 4:
                    money += 10000;
                    break;
            }
            upgradesBought[index] = true;
            upgradesBought[0] = false;
        }
    }

    public void craftToothpick(long amount) {
        if (amount > wood) {
            amount = wood;
        }
        toothpicks = toothpicks + amount;
        toothpicksCrafted += amount;
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
        moneyMade += (amount * toothpickPrice);
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
            woodBought += woodAmountBought;
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
