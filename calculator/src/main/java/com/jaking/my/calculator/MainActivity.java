package com.jaking.my.calculator;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.jaking.my.calculator.util.Evaluator;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class MainActivity extends ActionBarActivity {


    @InjectView(R.id.textView)
    TextView tv;
    @InjectView(R.id.button10) Button button10;
    @InjectView(R.id.button11) Button button11;
    @InjectView(R.id.button12) Button button12;
    @InjectView(R.id.button13) Button button13;
    @InjectView(R.id.button14) Button button14;
    @InjectView(R.id.button15) Button button15;
    @InjectView(R.id.button16) Button button16;
    @InjectView(R.id.button17) Button button17;
    @InjectView(R.id.button18) Button button18;
    @InjectView(R.id.button19) Button button19;
    @InjectView(R.id.button20) Button button20;
    @InjectView(R.id.button2) Button button2;
    @InjectView(R.id.button3) Button button3;
    @InjectView(R.id.button4) Button button4;
    @InjectView(R.id.button5) Button button5;
    @InjectView(R.id.button6) Button button6;
    @InjectView(R.id.button7) Button button7;
    @InjectView(R.id.button8) Button button8;
    @InjectView(R.id.button9) Button button9;




    String str="",result;


    @OnClick(R.id.button)
    public  void calcute(){
        if (str.length()==0){
            Toast.makeText(this,"请输入内容",Toast.LENGTH_LONG).show();
        }else if (Evaluator.isMatch(str)){
            CalculatAsyncTask calculatAsyncTask=new CalculatAsyncTask(tv);
            calculatAsyncTask.execute();
        }else {
            Toast.makeText(this,"缺少圆括号",Toast.LENGTH_LONG).show();
        }
    }
    @OnClick(R.id.button10)
    public  void input10(){
        str=str+button10.getText().toString();
        tv.setText(str);
    }
    @OnClick(R.id.button11)
    public  void input11(){
        str=str+button11.getText().toString();
        tv.setText(str);
    }
    @OnClick(R.id.button12)
    public  void input12(){
        str=str+button12.getText().toString();
        tv.setText(str);
    }@OnClick(R.id.button13)
     public  void input13(){
        str=str+button13.getText().toString();
        tv.setText(str);
    }@OnClick(R.id.button14)
     public  void input14(){
        str=str+button14.getText().toString();
        tv.setText(str);
    }@OnClick(R.id.button15)
     public  void input15(){
        str=str+button15.getText().toString();
        tv.setText(str);
    }@OnClick(R.id.button16)
     public  void input16(){
        str=str+button16.getText().toString();
        tv.setText(str);
    }@OnClick(R.id.button17)
     public  void input17(){
        str=str+button17.getText().toString();
        tv.setText(str);
    }@OnClick(R.id.button18)
     public  void input18(){
        str=str+button18.getText().toString();
        tv.setText(str);
    }@OnClick(R.id.button19)
     public  void input19(){
        str=str+button19.getText().toString();
        tv.setText(str);
    }@OnClick(R.id.button20)
     public  void input20(){
        str=str+button20.getText().toString();
        tv.setText(str);
    }@OnClick(R.id.button2)
     public  void input2(){
        str=str+button2.getText().toString();
        tv.setText(str);
    }@OnClick(R.id.button3)
     public  void input3(){
        str=str+button3.getText().toString();
        tv.setText(str);
    }@OnClick(R.id.button4)
     public  void input4(){
        str=str+button4.getText().toString();
        tv.setText(str);
    }@OnClick(R.id.button5)
     public  void input5(){
        str=str+button5.getText().toString();
        tv.setText(str);
    }@OnClick(R.id.button6)
     public  void input6(){
        str=str+button6.getText().toString();
        tv.setText(str);
    }@OnClick(R.id.button7)
     public  void input7(){
        str=str+button7.getText().toString();
        tv.setText(str);
    }@OnClick(R.id.button8)
     public  void input8(){
        str=str+button8.getText().toString();
        tv.setText(str);
    }@OnClick(R.id.button9)
     public  void input9(){
        str=str+button9.getText().toString();
        tv.setText(str);
    }
    @OnClick(R.id.button21)
    public  void clean(){
        str="";
        tv.setText(str);
    }










    private class CalculatAsyncTask extends AsyncTask{
        private TextView textView;
        public CalculatAsyncTask(TextView tv){
            textView=tv;
        }

        @Override
        protected Object doInBackground(Object[] params) {
            Evaluator ev=new Evaluator(str);
            result=ev.getValue()+"";

            return result;
        }

        @Override
        protected void onPostExecute(Object result) {
            textView.setText(result+"");
            str=result+"";
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
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
