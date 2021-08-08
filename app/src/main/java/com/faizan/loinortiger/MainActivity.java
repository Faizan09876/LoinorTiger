package com.faizan.loinortiger;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private ImageView imglion1;
    private GridLayout gridlayout;
    private ImageView imglion2;
    private ImageView imglion3;
    private ImageView imglion4;
    private ImageView imglion5;
    private ImageView imglion6;
    private ImageView imglion7;
    private ImageView imglion8;
    private ImageView imglion9;
    private Button btnreset;
    private boolean GameOver = false;

    enum Player {
        ONE, TWO, NO
    }

    Player currentPlayer = Player.ONE;

    Player[] PlayerChoices = new Player[9];

    int[][] WinnerRowsColumns = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imglion1 = findViewById(R.id.imglion1);
        imglion2 = findViewById(R.id.imglion2);
        imglion3 = findViewById(R.id.imglion3);
        imglion4 = findViewById(R.id.imglion4);
        imglion5 = findViewById(R.id.imglion5);
        imglion6 = findViewById(R.id.imglion6);
        imglion7 = findViewById(R.id.imglion7);
        imglion8 = findViewById(R.id.imglion8);
        imglion9 = findViewById(R.id.imglion9);
        btnreset = findViewById(R.id.btnreset);
        gridlayout = findViewById(R.id.gridLayout);

        btnreset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ResetTheGame();
            }
        });

        PlayerChoices[0] = Player.NO;
        PlayerChoices[1] = Player.NO;
        PlayerChoices[2] = Player.NO;
        PlayerChoices[3] = Player.NO;
        PlayerChoices[4] = Player.NO;
        PlayerChoices[5] = Player.NO;
        PlayerChoices[6] = Player.NO;
        PlayerChoices[7] = Player.NO;
        PlayerChoices[8] = Player.NO;


    }

    public void ImageViewisTapped(View ImageView) {

        ImageView TappedImageView = (ImageView) ImageView;

        int tiTag = Integer.parseInt(TappedImageView.getTag().toString());

        if (PlayerChoices[tiTag] == Player.NO && GameOver == false) {


            TappedImageView.setTranslationX(-2000);


            PlayerChoices[tiTag] = currentPlayer;

            if (currentPlayer == Player.ONE) {

                TappedImageView.setImageResource(R.drawable.lion);
                currentPlayer = Player.TWO;

            } else if (currentPlayer == Player.TWO) {
                TappedImageView.setImageResource(R.drawable.tiger);
                currentPlayer = Player.ONE;

            }
            TappedImageView.animate().translationXBy(2000).alpha(1f).rotation(3600).setDuration(1000);
            Toast.makeText(this, TappedImageView.getTag().toString(), Toast.LENGTH_SHORT).show();

            String WinnerofGame = "";
            for (int[] Winnercolumns : WinnerRowsColumns) {

                if (PlayerChoices[Winnercolumns[0]] == PlayerChoices[Winnercolumns[1]]
                        && PlayerChoices[Winnercolumns[1]] == PlayerChoices[Winnercolumns[2]] && PlayerChoices[Winnercolumns[0]] != Player.NO) {

                    if (currentPlayer == Player.ONE) {
                        WinnerofGame = "Player One";
                    } else if (currentPlayer == Player.TWO) {

                        WinnerofGame = " Player Two";

                    }
                    Toast.makeText(getApplicationContext(), WinnerofGame + "Player Two is Winner", Toast.LENGTH_LONG).show();

                    GameOver = true;
                    btnreset.setVisibility(View.VISIBLE);
                }
            }
        }


    }
        public void ResetTheGame(){

         for ( int index = 0 ; index < gridlayout.getChildCount(); index ++){

             ImageView imageView =  (ImageView) gridlayout.getChildAt(index);
             imageView.setImageDrawable(null);
         }

        }

}