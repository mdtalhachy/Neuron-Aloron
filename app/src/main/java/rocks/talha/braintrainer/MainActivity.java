package rocks.talha.braintrainer;

import android.annotation.SuppressLint;
import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Random;


/**
 * For the brave souls who get this far: You are the chosen ones,
 * the valiant knights of programming who toil away, without rest,
 * fixing our most awful code. To you, true saviors, kings of men,
 * I say this: never gonna give you up, never gonna let you down,
 * never gonna run around and desert you. Never gonna make you cry,
 * never gonna say goodbye. Never gonna tell a lie and hurt you.
 * - someone
 */

/**
 * Developed by MD Talha Chowdhury
 */

public class MainActivity extends AppCompatActivity {

    Button goButton;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationOfAnswers;
    TextView resultTextView;
    TextView scoreTextView;
    TextView sumTextView;
    TextView counterTextView;
    Button playAgainButton;
    ConstraintLayout gameLayout;
    android.support.v7.widget.GridLayout gridLayout;

    int score = 0;
    int numberOfQuestions = 0;

    Button button0;
    Button button1;
    Button button2;
    Button button3;

    //called when game is over
    public void playAgain(View view){
        score = 0;
        numberOfQuestions = 0;
        counterTextView.setText("30s");
        scoreTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
        newQuestion();
        playAgainButton.setVisibility(View.INVISIBLE);
        resultTextView.setText("");
        gridLayout.setVisibility(View.VISIBLE);

        new CountDownTimer(30100,100){
            @Override
            public void onTick(long millisUntilFinished) {
                counterTextView.setText(String.valueOf(millisUntilFinished/1000) + "s");
            } //end of onTick

            @Override
            public void onFinish() {
                resultTextView.setText("Time Up!");
                playAgainButton.setVisibility(View.VISIBLE);
                gridLayout.setVisibility(View.INVISIBLE);
               sumTextView.setText("");
            } //end of onFinish
        }.start();
    } //end of PlayAgain


    //called when answering
    public void answerButton(View view){


        if(Integer.toString(locationOfAnswers).equals(view.getTag().toString())){
            resultTextView.setText("Correct!");
            score++;
        }else{
            resultTextView.setText("Wrong! :(");
        }
        numberOfQuestions++;
        scoreTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));

        newQuestion();
    } //end of answerButton


    //called when user needs a new question
    public void newQuestion(){
        Random rand = new Random();

        int a = rand.nextInt(51);
        int b = rand.nextInt(51);

        sumTextView.setText(Integer.toString(a) + "+" + Integer.toString(b));

        locationOfAnswers = rand.nextInt(4);

        answers.clear();

        for(int i=0;i<4;i++){
            if(i == locationOfAnswers){
                answers.add(a+b);
            }else{
                int wrongAnswer = rand.nextInt(101);
                while(wrongAnswer == a+b){
                    wrongAnswer = rand.nextInt(101);
                }
                answers.add(wrongAnswer);
            }
        }


        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));

    } //end of newQuestion

    public void start(View view){
        goButton.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(View.VISIBLE);
        playAgain(findViewById(R.id.timerTextView));
    } //end of start

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        goButton = findViewById(R.id.goButton);
        goButton.setVisibility(View.VISIBLE);
        resultTextView = findViewById(R.id.resultTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        counterTextView = findViewById(R.id.timerTextView);
        playAgainButton = findViewById(R.id.playAgainButton);
        resultTextView.setText("");
        gameLayout = findViewById(R.id.gameLayout);
        gridLayout = findViewById(R.id.gridLayout);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);

        setSupportActionBar(toolbar);
        mTitle.setText(toolbar.getTitle());

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);

        sumTextView = findViewById(R.id.sumTextView);
        gameLayout.setVisibility(View.INVISIBLE);
    } //end of onCreate

} //end of MainActivity
