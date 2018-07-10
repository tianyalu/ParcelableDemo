package com.sty.parcelable;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.sty.parcelable.model.MainBean;
import com.sty.parcelable.model.SecondBean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 参考：https://www.jianshu.com/p/3783bdf9cabe
 */
public class MainActivity extends AppCompatActivity {
    private TextView tvContent;
    private Button btnSecond;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvContent = findViewById(R.id.tv_content);
        btnSecond = findViewById(R.id.btn_second);
        final MainBean mainBean = createData();
        tvContent.setText(mainBean.toString());

        btnSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("MainBean", mainBean);
                startActivity(intent);
            }
        });
    }

    private MainBean createData(){
        MainBean mainBean = new MainBean();
        mainBean.setId(10);
        mainBean.setNormalStatus(true);
        mainBean.setGmtCreated(1000000000L);
        mainBean.setName("这是Parcelable测试实例");
        mainBean.setPrice(new BigDecimal(2000000000));

        SecondBean secondBean = new SecondBean();
        secondBean.setId(22);
        mainBean.setSecondBean(secondBean);

        List<SecondBean> secondBeanList = new ArrayList<>();
        for(int i = 300; i < 303; i++){
            SecondBean secondBean1 = new SecondBean();
            secondBean1.setId(i);
            secondBeanList.add(secondBean1);
        }
        mainBean.setSecondBeanList(secondBeanList);

        int[] numberArray = new int[10];
        for(int i = 0; i < 10; i++){
            numberArray[i] = i;
        }
        mainBean.setNumberArray(numberArray);

        return mainBean;
    }
}
