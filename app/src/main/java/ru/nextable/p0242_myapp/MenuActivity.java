package ru.nextable.p0242_myapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends Activity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onStart();
        setContentView(R.layout.activity_menu);

        Button btnGame = (Button) findViewById(R.id.btnGame);
        Button btnGame2 = (Button) findViewById(R.id.btnGame2);
        Button btnHelp = (Button) findViewById(R.id.btnHelp);

        btnGame.setOnClickListener(this);
        btnGame2.setOnClickListener(this);
        btnHelp.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;

        switch (v.getId()) {
            case R.id.btnGame:
                intent = new Intent(this, GameActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("game_mode", 1);
                startActivity(intent);
                break;

            case R.id.btnGame2:
                intent = new Intent(this, GameActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("game_mode", 2);
                startActivity(intent);
                break;

            case R.id.btnHelp:
                intent = new Intent(this, HelpActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;
        }
    }
}
