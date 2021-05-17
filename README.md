# LumberPlanet
### Členovia tímu: 
Juraj Seman, Patrik Ištván

## Stručný opis: 
Clicker/Idle štýl hry, kde vyrábaš a predávaš rôzne výrobky z dreva. Hráč trávi čas klikaním na tlačidlo aby vyrobil výrobok, pozeraním na čísla a kupovaním vylepšení a výrobných materiálov za zarobené peniaze.

## Funkcionality aplikácie:
### Este neimplementovane napady: (hore najvyssia priorita/najskor rozdelit na implementovanie do verzie):
    -ine produkty
    -prestige (reset hry + nejaky significant upgrade co ostane po restarte)
    
## Verzia 3:
### Juraj:
    -opravit bug disablovanie tlacitka na seller - DONE
    -lifetime statistics pocitanie:
        -long moneyMade
        -int toothpicksCrafted
        -int woodBought
    -pridat dalsie upgrady par ktore pouzivaju lifetime statistics
### Patrik:
    -vizualne zmeny, pridanie ikoniek dreva a toothpickov a penazi
## Verzia 2: (DONE)
### Juraj:
    -material (Drevo): - DONE
        -zobrazenie poctu dreva (sklad dreva ma kapacitu 100 na zaciatku) - DONE
        -tlacitko na kupovanie dreva (2 dreva kupis za $1 na zaciatku) - DONE
    -Vytvorit premenne (v Game.java) na lahsie robenie upgradov neskor: - DONE
        -int craftButtonMod(-ifier), pocet crafteni po stlaceni tlacitka, default 1 - DONE
        -int sellButtonMod, pocet predani po stlaceni tlacitka, default 1 - DONE
        -int vyrabacMod, pocet vyrobenych produktov 1 vyrabacom za 1 sekundu, default 1 - DONE
        -int predavacMod, pocet predanych produktov 1 predavacom za 1 sekundu, default 1 - DONE
        -double nextVyrabacPriceMod, o kolko sa znasobi cena vyrabaca pri kupeni, default 1.2 - DONE
        -double nextPredavacPriceMod, o kolko sa znasobi cena vyrabaca pri kupeni, default 1.2 - DONE
        -int toothpickPrice, kolko sa prida penazi pri predani 1 toothpicku, default 1 - DONE
        -int woodBuyAmount, kolko dreva sa kupi za 1$, default 2 - DONE
    -Urobit system vytvarania, zobrazovania a kupovania jednorazovych vylepseni - DONE
        - dat celu aplikaciu do scrollview - DONE
        - pridat prvy upgrade na testovanie: woodBuyAmount * 2, da sa kupit ked ma hrac vyrabac&&predavac >= 1 - DONE
    -tlacitko je sive(disabled button) ak hrac ma menej penazi ako cena - DONE
    -zobrazit (vyrabacMod * vyrabac) v riadku kde sa zobrazuje pocet crafterov(rovnako aj pre seller) - DONE
    
### Patrik:
    -material (Drevo): - DONE
        -Drevo da sa velmi zdlhavo ziskat manualne - DONE
        -vyrobenim kazdeho toothpicku sa pouzije 1 drevo(nevyrobi sa nic ak nemas drevo) - DONE
    -pridat dalsie upgrady uz vymyslenim systemom - DONE
    
## Verzia 1: (DONE)
### Juraj:
    -Zobrazenie poctu peňazí - DONE
    -Zobrazenie poctu produktu (sparadla) - DONE
    -Kliknutím na tlačidlo sa vyrobí 1 produkt - DONE
    -Kliknutim na tlacidlo sa preda 1 produkt a prida sa 1 peniaz - DONE
    -Kazdy vyrabac ktory hrac ma, vyrobi 1 produkt za 1 sekundu - DONE
    -Kazdy predavac ktory hrac ma, preda 1 produkt za 1 sekundu - DONE
  
### Patrik:
    -Zobrazenie poctu kupenych vyrabacov - DONE
    -Kliknutim na tlacidlo sa kupi 1 automaticky "vyrabac" - prvy stoji 500, a kazdy dalsi stoji 120% ceny predchadzajuceho - DONE
    -Zobrazenie poctu kupenych predavacov - DONE
    -Kliknutim na tlacidlo sa kupi 1 automaticky "predavac" - prvy stoji 100, a kazdy dalsi stoji 120% ceny predchadzajuceho - DONE
    -Ukladanie postupu cez sharedPreferences - DONE
    -Offline progress(aby hra postupovala aj ked je aplikacia vypnuta) - DONE

