package th.ac.tu.siit.its333.lab3exercise1;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;


public class CourseActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
    }

    public void addCourse(View v)
    {
        Intent res = new Intent();

        EditText etCode = (EditText)findViewById(R.id.etCode);
        res.putExtra("code", etCode.getText().toString());

        EditText etCR = (EditText)findViewById(R.id.etCR);
        res.putExtra("credit", Integer.parseInt(etCR.getText().toString()));

        RadioGroup rgGrade = (RadioGroup)findViewById(R.id.rgGrade);
        int selGrade = rgGrade.getCheckedRadioButtonId();

        RadioButton rb = (RadioButton)findViewById(selGrade);
        String letterGrade= rb.getText().toString();

        /*
        switch(selGrade){
            case R.id.rbA:
                letterGrade = "A";
                break;
            case R.id.rbBP:
                letterGrade = "B+";
                break;
            case R.id.rbB:
                letterGrade = "B";
                break;
            case R.id.rbCP:
                letterGrade = "C+";
                break;
            case R.id.rbC:
                letterGrade = "C";
                break;
            case R.id.rbDP:
                letterGrade = "D+";
                break;
            case R.id.rbD:
                letterGrade = "D";
                break;
            case R.id.rbF:
                letterGrade = "F";
                break;
        }
        */

        res.putExtra("grade", letterGrade);


        setResult(RESULT_OK, res);
        finish();
    }


        @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_course, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
