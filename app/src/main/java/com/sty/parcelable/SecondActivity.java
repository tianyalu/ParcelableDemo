package com.sty.parcelable;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.sty.parcelable.model.MainBean;
import com.sty.parcelable.model.SecondBean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity {
    private TextView tvContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        tvContent = findViewById(R.id.tv_content);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            MainBean mainBean = bundle.getParcelable("MainBean");
            tvContent.setText(mainBean.toString());
        }
    }

}
