package com.saarthak.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //0 = knot, 1 = cross
    int turn =0;
    boolean activegame=true;
    int[] gameplay = {2,2,2,2,2,2,2,2,2};
    int[][] winningpos = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    public void fill(View view)
    {
        ImageView move1 = (ImageView) view;
        int tag = Integer.parseInt(move1.getTag().toString());
        if(gameplay[tag]==2 && activegame==true) {
            gameplay[tag] = turn;
            move1.setTranslationY(-1000f);
            if (turn == 0) {
                move1.setImageResource(R.drawable.knot);
                turn = 1;
            } else {
                move1.setImageResource(R.drawable.cross);
                turn = 0;
            }
            move1.animate().translationY(0.01f).rotation(720).setDuration(1000);
            for(int[] win:winningpos)
            {
                if(gameplay[win[0]]==gameplay[win[1]] && gameplay[win[1]] == gameplay[win[2]] && gameplay[win[0]]!=2)
                {
                    activegame = false;
                    String winner;
                    if(gameplay[win[0]]==0)
                    {
                        winner = "Player 1";
                    }
                    else
                    {
                        winner = "Player 2";
                    }
                    TextView txt = (TextView) findViewById(R.id.textView2);
                    txt.setText(winner+" has won");
                    LinearLayout layout = (LinearLayout)findViewById(R.id.gameOver);
                    layout.setVisibility(View.VISIBLE);
                }
                else
                {
                    boolean gameover = true;
                    for(int state: gameplay)
                    {
                        if(state==2)
                            gameover = false;
                    }
                    if(gameover == true)
                    {
                        TextView txt = (TextView) findViewById(R.id.textView2);
                        txt.setText("It is a Tie");
                        LinearLayout layout = (LinearLayout)findViewById(R.id.gameOver);
                        layout.setVisibility(View.VISIBLE);
                    }
                }

            }
        }

    }
    public void playAgain(View view){
        LinearLayout layout = (LinearLayout)findViewById(R.id.gameOver);
        layout.setVisibility(View.INVISIBLE);
        turn=0;
        activegame = true;
        for(int i=0;i<gameplay.length;i++)
        {
            gameplay[i]=2;
        }
        GridLayout gl = (GridLayout)findViewById(R.id.gridLayout);
        for(int i=0; i<gl.getChildCount();i++)
        {
            ((ImageView)gl.getChildAt(i)).setImageResource(0);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
