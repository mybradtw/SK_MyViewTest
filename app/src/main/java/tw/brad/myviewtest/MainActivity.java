package tw.brad.myviewtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private MyView myView;
    private SeekBar seekBar;
    private TextView lineNum;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lineNum = findViewById(R.id.lineNumber);
        myView = findViewById(R.id.myView);
        seekBar= findViewById(R.id.seekBar);
        seekBar.setMax(16);
        seekBar.setProgress(8);
        myView.chLineWidth(8);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean fromUser) {
                myView.chLineWidth(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    public void clear(View view) {
        myView.clear();
    }
    public void undo(View view) {
        myView.undo();
    }
    public void redo(View view) {
        myView.redo();
    }

    public void showLineNum(int n){
        lineNum.setText("" + n);
    }
}
