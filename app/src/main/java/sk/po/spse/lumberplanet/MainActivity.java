package sk.po.spse.lumberplanet;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Game game;
    Handler handler = new Handler();
    Runnable runnable;
    int delay = 1*1000; //Delay for 1 second. (start "run" method in "onResume" every x seconds)


    @Override
    protected void onResume() {
        //start handler as activity become visible

        handler.postDelayed( runnable = new Runnable() {
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
        super.onPause();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        game = new Game();
    }

    //implement handler instead of Timer
    //https://stackoverflow.com/questions/11434056/how-to-run-a-method-every-x-seconds

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
        buyPredavac.setText("Predavac, cena: "+game.getPredavacPrice());
    }
}