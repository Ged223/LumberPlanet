package sk.po.spse.lumberplanet;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        game = new Game();
        updateDisplay();
    }

    public void craftButton(View view) {
        game.craftToothpick();
        updateDisplay();
    }

    public void sellButton(View view) {
        game.sellToothpick();
        updateDisplay();
    }

    private void updateDisplay() {
        TextView moneyText = findViewById(R.id.moneyText);
        moneyText.setText(getString(R.string.moneyText) + game.getMoney());

        TextView toothpicksText = findViewById(R.id.toothpicksText);
        toothpicksText.setText(getString(R.string.toothpicksText) + game.getToothpicks());
    }
}