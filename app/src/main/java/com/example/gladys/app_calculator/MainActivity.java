/*
* Last Edited April 11, 2018 by Hayder MA
**/


package com.example.gladys.app_calculator;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.fathzer.soft.javaluator.DoubleEvaluator;


public class MainActivity extends AppCompatActivity {

    String total = "";
    String INVALID_CONSEC_OPER = "Invalid, two consecutive operators";
    String INVALID_CHAR = "Input Includes Invalid Char";
    String INVALID_LAST = "Invalid, last char cannot be operator";
    Boolean cal_status = null; //check that parsing passed successfully before performing evaluation
    double v1, v2;
    String sign = "";
    Button zero = null;
    Button one = null;
    Button two = null;
    Button three = null;
    Button four = null;
    Button five = null;
    Button six = null;
    Button seven = null;
    Button eight = null;
    Button nine = null;
    Button add = null;
    Button substract = null;
    Button dot = null;
    Button mult = null;
    Button div = null;
    Button clear = null;
    Button equal = null;
    Button back = null;
    EditText edit = null;
    TextView tv = null;


    public MainActivity() {


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        zero = (Button) findViewById(R.id.button0);
        one = (Button) findViewById(R.id.button);
        two = (Button) findViewById(R.id.button2);
        three = (Button) findViewById(R.id.button3);
        four = (Button) findViewById(R.id.button4);
        five = (Button) findViewById(R.id.button5);
        six = (Button) findViewById(R.id.button6);
        seven = (Button) findViewById(R.id.button7);
        eight = (Button) findViewById(R.id.button8);
        nine = (Button) findViewById(R.id.button9);
        add = (Button) findViewById(R.id.buttonPlus);
        substract = (Button) findViewById(R.id.buttonSub);
        dot = (Button) findViewById(R.id.button11);
        mult = (Button) findViewById(R.id.button_mul);
        div = (Button) findViewById(R.id.buttondiv);
        clear = (Button) findViewById(R.id.buttonClear);
        equal = (Button) findViewById(R.id.buttonEq);
        back = (Button) findViewById(R.id.button12);
        edit = (EditText) findViewById(R.id.editText);
        tv = (TextView) findViewById(R.id.textView);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        zero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //edit.append("0");
                edit.append("0");
            }
        });

        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edit.append("1");
            }
        });

        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edit.append("2");
            }
        });

        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edit.append("3");
            }
        });

        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edit.append("4");
            }
        });

        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edit.append("5");
            }
        });

        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edit.append("6");
            }
        });

        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edit.append("7");
            }
        });

        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edit.append("8");
            }
        });

        nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edit.append("9");
            }
        });

        substract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edit.append("-");
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edit.append("+");
            }
        });

        mult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edit.append("*");
            }
        });

        div.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edit.append("/");
            }
        });
        dot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edit.append(".");
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edit.setText("");
                tv.setText("");
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input = edit.getText().toString();
                if (input.length() > 0) {
                    edit.setText(input.substring(0, input.length() - 1));
                }
            }
        });

        equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cal_status = true;
                String input = edit.getText().toString();
                try {

                    for (int i = 0; i < input.length(); i++) {

                        if (Parsing.validChar(input.charAt(i))) {

                            if (Parsing.isOperator(input.charAt(i)) && Parsing.isOperator(input.charAt(i + 1))) {
                                tv.setText(INVALID_CONSEC_OPER);
                                cal_status = false;
                                break;
                            }

                        } else {
                            tv.setText(INVALID_CHAR);
                            cal_status = false;
                            break;
                        }
                    }//for
                    System.out.println("last char =" + input.charAt(input.length() - 1));

                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println("Input String Index went off bound, but operation is running safely and correctly");
                }

                if (Parsing.isOperator(input.charAt(input.length() - 1))) {

                    tv.setText(INVALID_LAST);
                    cal_status = false;
                }
                if (cal_status) {
                    Double result = new DoubleEvaluator().evaluate(input);
                    tv.setText(result.toString());
                }

            }
        });


    }

    public void onClick(View v) {

        Button button = (Button) v;
        String str = button.getText().toString();
        total = total + str;
        EditText edit = (EditText) findViewById(R.id.editText);
        edit.setText(total);


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
