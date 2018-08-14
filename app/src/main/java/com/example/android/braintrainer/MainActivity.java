package com.example.android.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int pos,scr,tot;
    GridLayout Choices;
    CountDownTimer countDownTimer;
    Button start,PlayAgain,bch1,bch2,bch3,bch4;
    ArrayList<Integer>  Arr=new ArrayList<Integer>();
    TextView Timer,Score,Message,Question;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Initialize();
    }
    public void Initialize(){


        scr=tot=0;
        bch1=(Button)findViewById(R.id.FirstChoiceTextView);
        bch2=(Button)findViewById(R.id.SecondChoiceTextView);
        bch3=(Button)findViewById(R.id.ThirdChoiceTextView);
        bch4=(Button)findViewById(R.id.FourthChoiceTextView);

        start=(Button)findViewById(R.id.StartButton);
        PlayAgain=(Button)findViewById(R.id.PlayAgainButton);

        Timer=(TextView)findViewById(R.id.TimerTextview);
        Score=(TextView)findViewById(R.id.ScoreTextView);
        Message=(TextView)findViewById(R.id.MessageTextView);
        Question=(TextView)findViewById(R.id.QuestionTextView);

        Choices=(GridLayout)findViewById(R.id.ChoicesGridView);

        GenrateQuestion();
    }

    public void Start(View  view){

        start.setVisibility(View.INVISIBLE);

        Timer.setVisibility(View.VISIBLE);
        Score.setVisibility(View.VISIBLE);
        Message.setVisibility(View.VISIBLE);
        Question.setVisibility(View.VISIBLE);
        Choices.setVisibility(View.VISIBLE);

        Reapet(findViewById(R.id.PlayAgainButton));
    }

    public void Press(View view){

        Log.i("the tag",view.getTag().toString());
        tot++;
        String sc;
        if(view.getTag().toString().equals(Integer.toString(pos))) {
            Message.setText("Correct!");
            scr++;
        }
        else
            Message.setText("Wrong Answer");

        Score.setText(Integer.toString(scr)+"/"+Integer.toString(tot));
        GenrateQuestion();
    }
    public void Reapet(View view){

        scr=tot=0;
        PlayAgain.setVisibility(View.INVISIBLE);
        Score.setText("0/0");
        Timer.setText("30s");

        countDownTimer=new CountDownTimer(30100,1000){
            @Override
            public void onTick(long millisUntilFinished) {

                Timer.setText(Long.toString(millisUntilFinished/1000)+"s");
            }

            @Override
            public void onFinish() {

                Timer.setText("0s");
                Message.setText("Your Score "+Integer.toString(scr)+"/"+Integer.toString(tot));
                PlayAgain.setVisibility(View.VISIBLE);
            }
        }.start();
        GenrateQuestion();
    }
    public  void GenrateQuestion(){

        Arr.clear();
        Random r=new Random();
        pos=r.nextInt(4);

        int x=r.nextInt(21),y=r.nextInt(21);
        Question.setText(Integer.toString(x) + " + " +Integer.toString(y));


        for(int i=0;i<4;i++){

            if(i==pos){
                Arr.add(x+y);
            }
            else {
                int val=r.nextInt(46);
                while (val==(x+y))
                    val=r.nextInt(46);
                Arr.add(val);

            }

        }
        bch1.setText(Arr.get(0).toString());
        bch2.setText(Arr.get(1).toString());
        bch3.setText(Arr.get(2).toString());
        bch4.setText(Arr.get(3).toString());


    }

}
