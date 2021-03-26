package sk.po.spse.lumberplanet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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
        game = getSavedObjectFromPreference(this,"gamePreference","gameClassKey",Game.class);
        if(game == null){
            game = new Game();
        }
    }

    @Override
    protected void onStop() {
        saveObjectToSharedPreference(this,"gamePreference","gameClassKey", game);
        super.onStop();
    }

    @Override
    protected void onResume() {
        //advance for every second that the app was off
        long diff = ((System.currentTimeMillis()/1000) - game.getLeaveTime());
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

        super.onResume();
    }

    @Override
    protected void onPause() {
        handler.removeCallbacks(runnable); //stop handler when activity not visible
        game.setLeaveTime(System.currentTimeMillis()/1000);
        super.onPause();
    }

    public void craftButton(View view) {
        game.craftToothpick();
        updateDisplay();
    }

    public void sellButton(View view) {
        game.sellToothpick();
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

    private void updateDisplay() {
        TextView moneyText = findViewById(R.id.moneyText);
        moneyText.setText(getString(R.string.moneyText) + game.getMoney());

        TextView toothpicksText = findViewById(R.id.toothpicksText);
        toothpicksText.setText(getString(R.string.toothpicksText) + game.getToothpicks());

        TextView vyrabacText = findViewById(R.id.vyrabacText);
        vyrabacText.setText("Vyrabac: " + game.getVyrabac());

        TextView predavacText = findViewById(R.id.predavacText);
        predavacText.setText("Predavac: " + game.getPredavac());

        Button buyVyrabac = findViewById(R.id.buttonVyrabac);
        buyVyrabac.setText("Vyrabac, cena: " + game.getVyrabacPrice());

        Button buyPredavac = findViewById(R.id.buttonPredavac);
        buyPredavac.setText("Predavac, cena: " + game.getPredavacPrice());
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
    }}