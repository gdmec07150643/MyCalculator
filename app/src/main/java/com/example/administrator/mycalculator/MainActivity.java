package com.example.administrator.mycalculator;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

/*  项目名称：个人标准身高计算器
  * 项目目标：(1)设计输入界面
  * (2)事件处理
  * (3)显示计算结果
  * (4)发布到手机 */
public class MainActivity extends Activity {
    //计算按钮
    private Button calculatorButton;
    //体重输入框
    private EditText weightEditText;
    //男性选择框
    private CheckBox manCheckBox;
    //女性选择框
    private CheckBox womanCheckBox;
    //显示结果
    private TextView resultTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //页面布局设置
        setContentView(R.layout.activity_height_calculator);
        //从activity_main.xml获取UI控件
        calculatorButton=(Button)findViewById(R.id.calculator);
        weightEditText=(EditText) findViewById(R.id.weight);
        manCheckBox=(CheckBox)findViewById(R.id.man);
        womanCheckBox=(CheckBox)findViewById(R.id.woman);
        resultTextView=(TextView)findViewById(R.id.result);
    }

    @Override
    protected void onStart() {
        super.onStart();
        //注册事件
        registerEvent();
    }

    private void registerEvent(){
        calculatorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //判断是否填写体重
                if(!weightEditText.getText().toString().trim().equals("")){
                    //判断是否选择性别
                    if(manCheckBox.isChecked()||womanCheckBox.isChecked()){
                        Double weight=Double.parseDouble(weightEditText.getText().toString());
                        StringBuffer sb=new StringBuffer();
                        sb.append("-----评估结果-----\n");
                        if(manCheckBox.isChecked()){
                            sb.append("男性标准身高：");
                            //执行运算
                            double result=evaluateHeight(weight,"男");
                            sb.append((int)result+"厘米\n");
                        }
                        if(womanCheckBox.isChecked()){
                            sb.append("女性标准身高：");
                            //执行运算
                            double result=evaluateHeight(weight,"女");
                            sb.append((int)result+"厘米\n");
                        }
                        //输出结果
                        resultTextView.setText(sb.toString());
                    }else{
                        showMessage("请选择性别！");
                    }
                }else{
                    showMessage("请输入体重！");
                }
            }
        });
    }

    /*  计算处理*/
    private double evaluateHeight(double weight,String sex){
        double height;
        if(sex=="男"){
            height = 170-(62-weight)/0.6;
        }else{
            height = 158-(52-weight)/0.5;
        }
        return height;
    }
    /* 信息提示
     *@param message
     */
    private void showMessage(String message){
        //提示框
           /* new AlertDialog.Builder(activity).setTitle(R.string.系统信息).setMessage(R.string.message)
                .setPositiveButton(R.string.确定, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        //这里定义对话框按钮单击之后要运行的事件
                    }
                }).show();

                alert.setButton(DialogInterface.BUTTON_NEGATIVE, "取消",
                new DialogInterface.OnClickListener() {
               @Override
               public void onClick(DialogInterface dialog,
               int which) {
}
});

                */
        AlertDialog alert=new AlertDialog.Builder(this).create();
        alert.setTitle("系统信息");
        alert.setMessage(message);
        alert.setButton(DialogInterface.BUTTON_NEGATIVE,"确定",new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int whichButton) {

            }
        });
        //显示窗口
        alert.show();
    }
    /* 创建菜单 */

    public boolean onCreateOptionsMenu(Menu menu){
        menu.add(Menu.NONE,1,Menu.NONE,"退出");
        return super.onCreateOptionsMenu(menu);
    }
    /* 菜单事件 */

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case 1://退出
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}

