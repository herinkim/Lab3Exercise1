package th.ac.tu.siit.its333.lab3exercise1;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    int cr = 0;         // Credits
    double gp = 0.0;    // Grade points
    double gpa = 0.0;   // Grade point average

    List<String> listCodes;
    List<Integer> listCredits;
    List<String> listGrades;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listCodes = new ArrayList<String>();
        listCredits = new ArrayList<Integer>();
        listGrades = new ArrayList<String>();

        //Use listCodes.add("ITS333"); to add a new course code
        //Use listCodes.size() to refer to the number of courses in the list
    }

    public void buttonClicked(View v) {
        // Check which button was clicked
        int btnID = v.getId();
        switch(btnID)
        {
            case R.id.button:
                // Reset
                resetAll();
                break;
            case R.id.button2:
                // Add course
                Intent addCourseAct = new Intent(this, CourseActivity.class);
                startActivityForResult(addCourseAct, 111);
                break;
            case R.id.button4:
                // Show course list
                Intent showCourseAct = new Intent(this, CourseListActivity.class);

                String showCourseList = "";
                for (int i = 0; i<listCodes.size(); i++)
                {
                    showCourseList += String.format("%s (%d credits) = %s \n", listCodes.get(i), listCredits.get(i), listGrades.get(i));
                }

                System.out.println(showCourseList);
                showCourseAct.putExtra("listCourse", showCourseList);

                startActivity(showCourseAct);
                break;
        }
    }

    public void resetAll()
    {
        cr = 0;
        gp = 0.0;
        gpa = 0.0;

        listCodes = new ArrayList<String>();
        listCredits = new ArrayList<Integer>();
        listGrades = new ArrayList<String>();

        TextView tvGP = (TextView) findViewById(R.id.tvGP);
        tvGP.setText(Double.toString(gp));

        TextView tvCredits = (TextView) findViewById(R.id.tvCR);
        tvCredits.setText(Integer.toString(cr));

        TextView tvGPA = (TextView) findViewById(R.id.tvGPA);
        tvGPA.setText(Double.toString(gpa));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Values from child activity

        if(requestCode == 111)
        {
            // User adds a new course grade
            if(resultCode == RESULT_OK)
            {
                String courseCode = data.getStringExtra("code");
                listCodes.add(courseCode);

                int courseCredit = data.getIntExtra("credit", 0);
                listCredits.add(courseCredit);

                String courseGrade = data.getStringExtra("grade");
                listGrades.add(courseGrade);

                showData();
            }
        }
    }

    public void showData()
    {
        // Calculate total grade points and total credits
        cr = 0;
        gp = 0.0;
        gpa = 0.0;

        for (int i = 0; i < listGrades.size(); i++)
        {
            double credit = listCredits.get(i);

            switch(listGrades.get(i))
            {
                case "A":
                    gp += (4.0 * credit);
                    break;
                case "B+":
                    gp += (3.5 * credit);
                    break;
                case "B":
                    gp += (3.0 * credit);
                    break;
                case "C+":
                    gp += (2.5 * credit);
                    break;
                case "C":
                    gp += (2.0 * credit);
                    break;
                case "D+":
                    gp += (1.5 * credit);
                    break;
                case "D":
                    gp += (1.0 * credit);
                    break;
                case "F":
                    gp += 0.0;
                    break;
            }

            cr+=credit;
        }

        // Show total number of credits
        TextView tvGP = (TextView) findViewById(R.id.tvGP);
        tvGP.setText(Double.toString(gp));

        // Show total number of credits
        TextView tvCredits = (TextView) findViewById(R.id.tvCR);
        tvCredits.setText(Integer.toString(cr));

        // Calculate and show to GPA
        gpa = gp/(cr);
        TextView tvGPA = (TextView) findViewById(R.id.tvGPA);
        tvGPA.setText(String.format("%.2f", gpa));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
