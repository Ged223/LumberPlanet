package sk.po.spse.lumberplanet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {
    private Game game;
    Handler handler = new Handler();
    Runnable runnable;
    int delay = 1 * 1000; //Delay for 1 second. (start "run" method in "onResume" every x seconds)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        game = getSavedObjectFromPreference(this, "gamePreference", "gameClassKey", Game.class);
        if (game == null) {
            game = new Game();
        }

        //remove the app name bar on top of app
        if (this.getSupportActionBar() != null) {
            this.getSupportActionBar().hide();
        }

    }

    @Override
    protected void onStop() {
        saveObjectToSharedPreference(this, "gamePreference", "gameClassKey", game);
        super.onStop();
    }

    @Override
    protected void onResume() {
        //advance for every second that the app was off
        long diff = ((System.currentTimeMillis() / 1000) - game.getLeaveTime());
        game.advance(diff);
        //start handler as activity become visible
        handler.postDelayed(runnable = new Runnable() {
            public void run() {
                //this code runs every X seconds
                game.advance();
                updateDisplay();

                handler.postDelayed(runnable, delay);
            }
        }, delay);

        setUpgradeButtonsText();

        super.onResume();
    }

    @Override
    protected void onPause() {
        handler.removeCallbacks(runnable); //stop handler when activity not visible
        game.setLeaveTime(System.currentTimeMillis() / 1000);
        super.onPause();
    }

    public void craftButton(View view) {
        game.craftButtonPressed();
        updateDisplay();
    }

    public void sellButton(View view) {
        game.sellButtonPressed();
        updateDisplay();
    }

    public void buyVyrabac(View view) {
        game.buyVyrabac();
        updateDisplay();
    }

    public void buyPredavac(View view) {
        game.buyPredavac();
        updateDisplay();
    }

    public void buyWoodButton(View view) {
        game.buyWood();
        updateDisplay();
    }

    public void findWoodButton(View view) {
        game.findWoodButtonPressed();
        Button findWoodButton = findViewById(R.id.findWoodButton);
        findWoodButton.setEnabled(false);
        updateDisplay();
    }

    private void updateDisplay() {
        long currentMoney = game.getMoney();

        TextView moneyText = findViewById(R.id.moneyText);
        moneyText.setText("" + game.getMoney());

        TextView toothpicksText = findViewById(R.id.toothpicksText);
        toothpicksText.setText("" + game.getToothpicks());

        TextView vyrabacText = findViewById(R.id.vyrabacText);
        vyrabacText.setText("Crafter: " + game.getVyrabac()+" ("+(game.getVyrabacMod()*game.getVyrabac())+")");

        TextView predavacText = findViewById(R.id.predavacText);
        predavacText.setText("Seller: " + game.getPredavac()+" ("+(game.getPredavacMod()*game.getPredavac())+")");

        Button buyVyrabac = findViewById(R.id.buttonVyrabac);
        buyVyrabac.setText("Crafter\nprice: " + game.getVyrabacPrice());
        buyVyrabac.setEnabled(currentMoney >= game.getVyrabacPrice());

        Button buyPredavac = findViewById(R.id.buttonPredavac);
        buyPredavac.setText("Seller\nprice: " + game.getPredavacPrice());
        buyPredavac.setEnabled(currentMoney >= game.getPredavacPrice());

        TextView woodText = findViewById(R.id.woodText);
        woodText.setText("" + game.getWood());

        Button findWoodButton = findViewById(R.id.findWoodButton);
        if (!findWoodButton.isEnabled() && game.getLastFoundWood() + 5000 < System.currentTimeMillis()) {
            findWoodButton.setEnabled(true);
        }
        Button[] buttons = getUpgradeButtonsList();
        String[] texts = game.getUpgradesText();
        buttons[0].setText(texts[0]);

        updateUpgradeButtons();


    }

    private Button[] getUpgradeButtonsList(){
        Button[] buttons = {
                findViewById(R.id.upgrade0),
                findViewById(R.id.upgrade1),
                findViewById(R.id.upgrade2),
                findViewById(R.id.upgrade3),
                findViewById(R.id.upgrade4),

        };//add new buttons here
        return buttons;
    }

    private void updateUpgradeButtons() {
        boolean[] upgradesBought = game.getUpgradesBought();
        int[] upgradesPrices = game.getUpgradesPrices();
        long currentMoney = game.getMoney();

        Button[] buttons = getUpgradeButtonsList();

        for (int i = 0; i < buttons.length; i++) {
            Button button = buttons[i];

            if (game.isUpgradeVisible(i)) {
                button.setVisibility(View.VISIBLE);
            }else{
                button.setVisibility(View.GONE);
            }
            if (upgradesBought[i]) {
                button.setVisibility(View.GONE);
            }
            button.setEnabled(currentMoney >= upgradesPrices[i]);

        }
    }

    private void setUpgradeButtonsText(){
        Button[] buttons = getUpgradeButtonsList();
        String[] texts = game.getUpgradesText();
        for (int i = 0; i < buttons.length; i++) {
            Button button = buttons[i];
            button.setText(texts[i]);
        }
    }

    public void buyUpgrade(View view) {
        game.buyUpgrade(Integer.parseInt(view.getTag().toString()));
        updateDisplay();
    }

    //https://stackoverflow.com/a/39435730
    public static void saveObjectToSharedPreference(Context context, String preferenceFileName, String serializedObjectKey, Object object) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(preferenceFileName, 0);
        SharedPreferences.Editor sharedPreferencesEditor = sharedPreferences.edit();
        final Gson gson = new Gson();
        String serializedObject = gson.toJson(object);
        sharedPreferencesEditor.putString(serializedObjectKey, serializedObject);
        sharedPreferencesEditor.apply();
    }

    public static <GenericClass> GenericClass getSavedObjectFromPreference(Context context, String preferenceFileName, String preferenceKey, Class<GenericClass> classType) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(preferenceFileName, 0);
        if (sharedPreferences.contains(preferenceKey)) {
            final Gson gson = new Gson();
            return gson.fromJson(sharedPreferences.getString(preferenceKey, ""), classType);
        }
        return null;
    }


}