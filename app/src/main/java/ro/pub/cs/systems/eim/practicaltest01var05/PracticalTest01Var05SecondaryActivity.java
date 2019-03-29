package ro.pub.cs.systems.eim.practicaltest01var05;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PracticalTest01Var05SecondaryActivity extends AppCompatActivity {

    private TextView scorText = null;
    private Button ok = null;

    private int scor = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var05_secondary);

        scorText = findViewById(R.id.scor);
        ok = findViewById(R.id.ok);

        Intent intent = getIntent();
        if (intent != null) {

            int firstNumber = intent.getIntExtra("firstNumber", -1);
            int secondNumber = intent.getIntExtra("secondNumber", -1);
            int thirdNumber = intent.getIntExtra("thirdNumber", -1);
            int bife = intent.getIntExtra("bife", -1);

            int jackpot = 0;
            if (firstNumber == secondNumber && firstNumber == thirdNumber) {
                switch (bife) {
                    case 0:
                        scor = 100;
                        break;
                    case 1:
                        scor = 50;
                        break;
                    case 2:
                        scor = 10;
                        break;
                }
            } else if (firstNumber == 0) {
                if (secondNumber == 0) {
                    switch (bife) {
                        case 0:
                            scor = 100;
                            break;
                        case 1:
                            scor = 50;
                            break;
                        case 2:
                            scor = 10;
                            break;
                    }
                } else if (thirdNumber == 0) {
                    switch (bife) {
                        case 0:
                            scor = 100;
                            break;
                        case 1:
                            scor = 50;
                            break;
                        case 2:
                            scor = 10;
                            break;
                    }
                } else if (secondNumber == thirdNumber) {
                    switch (bife) {
                        case 0:
                            scor = 100;
                            break;
                        case 1:
                            scor = 50;
                            break;
                        case 2:
                            scor = 10;
                            break;
                    }
                }
            } else if (secondNumber == 0) {
                if (thirdNumber == 0) {
                    switch (bife) {
                        case 0:
                            scor = 100;
                            break;
                        case 1:
                            scor = 50;
                            break;
                        case 2:
                            scor = 10;
                            break;
                    }
                } else if (firstNumber == thirdNumber) {
                    switch (bife) {
                        case 0:
                            scor = 100;
                            break;
                        case 1:
                            scor = 50;
                            break;
                        case 2:
                            scor = 10;
                            break;
                    }
                }
            } else if (thirdNumber == 0) {
                if (firstNumber == secondNumber) {
                    switch (bife) {
                        case 0:
                            scor = 100;
                            break;
                        case 1:
                            scor = 50;
                            break;
                        case 2:
                            scor = 10;
                            break;
                    }
                }
            }
        }

        scorText.setText(String.valueOf(scor));

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PracticalTest01Var05MainActivity.class);
                intent.putExtra("scor", scor);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

    }
}
