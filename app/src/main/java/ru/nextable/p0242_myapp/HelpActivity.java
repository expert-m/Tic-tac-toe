package ru.nextable.p0242_myapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HelpActivity extends Activity implements View.OnClickListener {

    Button btnMenuH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onStart();
        setContentView(R.layout.activity_help);

        btnMenuH = (Button) findViewById(R.id.btnMenuH);
        btnMenuH.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnMenuH) {
            Intent intent = new Intent(this, MenuActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
    }
}
