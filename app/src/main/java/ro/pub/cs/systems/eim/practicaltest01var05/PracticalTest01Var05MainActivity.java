package ro.pub.cs.systems.eim.practicaltest01var05;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class PracticalTest01Var05MainActivity extends Activity {

    String[] choices = {"1", "2", "3", "0"};

    private int finalScor = 0;
    private boolean SERVICE_STARTED = false;

    private TextView firstText = null;
    private TextView secondText = null;
    private TextView thirdText = null;
    private Button play = null;
    private CheckBox firstCheck = null;
    private CheckBox secondCheck = null;
    private CheckBox thirdCheck = null;

    private Random random = new Random();
    private IntentFilter intentFilter = new IntentFilter();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstText = findViewById(R.id.firstText);
        secondText = findViewById(R.id.secondText);
        thirdText = findViewById(R.id.thirdText);
        intentFilter.addAction("ACTION");

        firstText.setText("");
        secondText.setText("");
        thirdText.setText("");

        firstCheck = findViewById(R.id.firstCheck);
        secondCheck = findViewById(R.id.secondCheck);
        thirdCheck = findViewById(R.id.thirdCheck);

        play = findViewById(R.id.play);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!firstCheck.isChecked()) {
                    firstText.setText(choices[random.nextInt(choices.length)]);
                }

                if (!secondCheck.isChecked()) {
                    secondText.setText(choices[random.nextInt(choices.length)]);
                }

                if (!thirdCheck.isChecked()) {
                    thirdText.setText(choices[random.nextInt(choices.length)]);
                }

                showNumbers();

                Intent intent = new Intent(getApplicationContext(), PracticalTest01Var05SecondaryActivity.class);
                intent.putExtra("firstNumber", Integer.parseInt(firstText.getText().toString()));
                intent.putExtra("secondNumber", Integer.parseInt(secondText.getText().toString()));
                intent.putExtra("thirdNumber", Integer.parseInt(thirdText.getText().toString()));

                int bife = 0;
                if (firstCheck.isChecked()) bife++;
                if (secondCheck.isChecked()) bife++;
                if (thirdCheck.isChecked()) bife++;

                intent.putExtra("bife", bife);
                startActivityForResult(intent, 1);
            }
        });

        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey("finalScore")) {
                finalScor = savedInstanceState.getInt("finalScore");
            } else {
                finalScor = 0;
            }
        }

        registerReceiver(messageBroadcastReceiver, intentFilter);

    }

    private void showNumbers() {
        Toast.makeText(this, "Numbers are: "
                + firstText.getText().toString()
                + ", " + secondText.getText().toString()
                + ", " + thirdText.getText().toString()
                , Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == 1) {
            int temp = intent.getIntExtra("scor", 0);
            finalScor += temp;
            Toast.makeText(this, "You won " + finalScor, Toast.LENGTH_SHORT).show();
            startService();
        }
    }

    private void startService() {
        if ((finalScor > 0 && !SERVICE_STARTED) || finalScor > 110) {
            Intent service = new Intent(getApplicationContext(), PracticalTest01Var05Service.class);
            service.putExtra("finalScor", String.valueOf(finalScor));
            getApplicationContext().startService(service);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("finalScor", finalScor);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState.containsKey("finalScor")) {
            finalScor = savedInstanceState.getInt("finalScor");
            Toast.makeText(this, "Restored score  " + finalScor, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        Intent intent = new Intent(getApplicationContext(), PracticalTest01Var05Service.class);
        stopService(intent);
        super.onDestroy();
    }

    private void makeToast(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();

    }

    private MessageBroadcastReceiver messageBroadcastReceiver = new MessageBroadcastReceiver();
    private class MessageBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("[Message Received]", intent.getStringExtra("message"));

            makeToast(intent.getStringExtra("message"));
        }
    }

}
