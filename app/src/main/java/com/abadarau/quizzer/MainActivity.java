package com.abadarau.quizzer;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    String answer1 = "DEEP BLUE";
    String answer2 = "Apollo 11";
    String answer3 = "Ukraine";
    String[] answer4 = {"Euro", "United States Dollar"};
    RadioGroup question2Container;
    RadioGroup question3Container;
    LinearLayout question4Container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addRadioButtonsToGroups();

        Button checkAnswers = findViewById(R.id.btn_finish);
        checkAnswers.setOnClickListener(handleCheckAnswers);

    }

    private void addRadioButtonsToGroups() {
        question2Container = findViewById(R.id.rg_question2_answer);
        String[] question2Answers = getResources().getStringArray(R.array.question2_answers);
        for (String q2Answer : question2Answers) {
            RadioButton q2Rb = new RadioButton(this);
            q2Rb.setText(q2Answer);
            question2Container.addView(q2Rb);
        }

        question3Container = findViewById(R.id.rg_question3_container);
        String[] question3Answers = getResources().getStringArray(R.array.question3_answers);
        for (String q3Answer : question3Answers) {
            RadioButton q3Rb = new RadioButton(this);
            q3Rb.setText(q3Answer);
            question3Container.addView(q3Rb);
        }

        question4Container = findViewById(R.id.question4_container);
        String[] question4Answers = getResources().getStringArray(R.array.question4_answers);
        for (String q4Answer : question4Answers) {
            CheckBox q4Cb = new CheckBox(this);
            q4Cb.setText(q4Answer);
            question4Container.addView(q4Cb);
        }

    }

    private View.OnClickListener handleCheckAnswers = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Context context = getApplicationContext();
            Toast msg = Toast.makeText(context, "You got " + getCorrectAnswers() + " questions right!", Toast.LENGTH_LONG);
            msg.setGravity(Gravity.CENTER, 0, 0);
            msg.show();
        }
    };

    private Integer getCorrectAnswers() {
        Integer correct = 0;

        String q1Answered = ((TextView) findViewById(R.id.et_question1_answer)).getText().toString();
        if (answer1.equals(q1Answered)) {
            correct++;
        }

        int q2SelectedId = question2Container.getCheckedRadioButtonId();
        RadioButton q2Selected = findViewById(q2SelectedId);
        if (answer2.equals(q2Selected.getText())) {
            correct++;
        }

        int q3SelectedId = question3Container.getCheckedRadioButtonId();
        RadioButton q3Selected = findViewById(q3SelectedId);
        if (answer3.equals(q3Selected.getText())) {
            correct++;
        }

        if (isQuestion4Correct()) {
            correct++;
        }

        return correct;
    }

    private Boolean isQuestion4Correct() {
        List<String> selectedAnswers = new ArrayList<>();
        for (int i = 0; i < question4Container.getChildCount(); i++) {
            View child = question4Container.getChildAt(i);
            if ((child instanceof CheckBox) && (((CheckBox) child).isChecked())) {
                selectedAnswers.add(((CheckBox) child).getText().toString());
            }
        }

        if (Arrays.equals(answer4, selectedAnswers.toArray())) {
            return true;
        }
        return false;
    }
}
