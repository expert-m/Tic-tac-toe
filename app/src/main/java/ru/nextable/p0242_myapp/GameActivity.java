package ru.nextable.p0242_myapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class GameActivity extends Activity implements View.OnClickListener {

    ImageButton[] ncArr;
    Bitmap xBitmap, oBitmap;
    int[] intArr;
    Button btnStartGame, btnMenu;
    boolean stop;
    int gameMode;
    int umove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onStart();
        setContentView(R.layout.activity_game);

        ncArr = new ImageButton [9];

        ncArr[0] = (ImageButton) findViewById(R.id.nc0);
        ncArr[1] = (ImageButton) findViewById(R.id.nc1);
        ncArr[2] = (ImageButton) findViewById(R.id.nc2);
        ncArr[3] = (ImageButton) findViewById(R.id.nc3);
        ncArr[4] = (ImageButton) findViewById(R.id.nc4);
        ncArr[5] = (ImageButton) findViewById(R.id.nc5);
        ncArr[6] = (ImageButton) findViewById(R.id.nc6);
        ncArr[7] = (ImageButton) findViewById(R.id.nc7);
        ncArr[8] = (ImageButton) findViewById(R.id.nc8);

        ncArr[0].setOnClickListener(this);
        ncArr[1].setOnClickListener(this);
        ncArr[2].setOnClickListener(this);
        ncArr[3].setOnClickListener(this);
        ncArr[4].setOnClickListener(this);
        ncArr[5].setOnClickListener(this);
        ncArr[6].setOnClickListener(this);
        ncArr[7].setOnClickListener(this);
        ncArr[8].setOnClickListener(this);

        xBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.x);
        oBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.o);

        intArr = new int [9];

        for (int i = 0; i < 9; i++) {
            intArr[i] = 0;
        }

        btnMenu = (Button) findViewById(R.id.btnMenu);
        btnStartGame = (Button) findViewById(R.id.btnStartGame);
        btnMenu.setOnClickListener(this);
        btnStartGame.setOnClickListener(this);

        stop = false;

        gameMode = getIntent().getIntExtra("game_mode", 1);
        umove = 1;
    }

    @Override
    public void onClick(View v) {
        Intent intent;

        switch (v.getId()) {
            case R.id.nc0:
                move(0);
                break;
            case R.id.nc1:
                move(1);
                break;
            case R.id.nc2:
                move(2);
                break;
            case R.id.nc3:
                move(3);
                break;
            case R.id.nc4:
                move(4);
                break;
            case R.id.nc5:
                move(5);
                break;
            case R.id.nc6:
                move(6);
                break;
            case R.id.nc7:
                move(7);
                break;
            case R.id.nc8:
                move(8);
                break;
            case R.id.btnMenu:
                intent = new Intent(this, MenuActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;
            case R.id.btnStartGame:
                intent = new Intent(this, GameActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("game_mode", gameMode);
                startActivity(intent);
                break;
        }
    }

    private void move(int n) {
        if(stop) return;

        if(intArr[n] == 0) {
            if(gameMode == 1) {
                ncArr[n].setImageBitmap(xBitmap);
                intArr[n] = 1;
            } else if(gameMode == 2) {
                if(umove == 1) {
                    ncArr[n].setImageBitmap(xBitmap);
                    status("Ход ноликов");
                    intArr[n] = 1;
                    umove = 2;
                } else {
                    ncArr[n].setImageBitmap(oBitmap);
                    status("Ход крестиков");
                    intArr[n] = 2;
                    umove = 1;
                }
            }
        } else {
            return;
        }

        checkMove();
        if(stop) return;

        if(gameMode == 1) {
            int nf = 0;

            for (int i = 0; i < 9; i++) {
                if (intArr[i] == 0) {
                    nf++;
                }
            }

            if (nf != 0) {
                int nm = (int) (Math.random() * nf + 1);

                for (int i = 0, j = 0; i < 9; i++) {
                    if (intArr[i] == 0) {
                        j++;
                    }

                    if (j == nm) {
                        ncArr[i].setImageBitmap(oBitmap);
                        intArr[i] = 2;
                        break;
                    }
                }
            }

            checkMove();
        }
    }

    private void checkMove() {
        int nf = 0;

        for (int i = 0; i < 9; i++) {
            if (intArr[i] == 0) {
                nf++;
            }
        }

        if (check(1)) {
            status("Победили крестики!");
            stop = true;
        } else if (check(2)) {
            status("Победили нолики!");
            stop = true;
        } else if (nf == 0) {
            status("Ничья");
            stop = true;
        }
    }

    private boolean check(int n) {
        int t = 0, g = 0, v = 0, s = 0;

        for (int i = 0; i < 3; i++) {
            g = 0;
            for (int j = 0; j < 3; j++) {
                if(j != 3 && intArr[t] == n) g++;
                t++;
            }
            if(g == 3) {
                return true;
            }
        }

        t = 0;
        for (int i = 0; i <= 3; i++) {
            v = 0;
            for (int j = 0; j < 3; j++) {
                if(j != 3 && intArr[t] == n) v++;
                t += 3;
            }
            t = i;
            if(v == 3) {
                return true;
            }
        }

        t = 0;
        for (int j = 0; j < 3; j++) {
            if(j != 3 && intArr[t] == n) s++;
            t += 4;
        }
        if(s == 3) {
            return true;
        }

        s = 0;
        t = 0;
        for (int j = 0; j < 3; j++) {
            t += 2;
            if(j != 3 && intArr[t] == n) s++;
        }
        if(s == 3) {
            return true;
        }

        return false;
    }

    private void status(String str) {
        TextView tvStatus = (TextView) findViewById(R.id.tvStatus);
        tvStatus.setText(str);
    }

}
