package com.ddmeng.hellolayouttransitiondemo;

import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.container)
    ViewGroup containerView;
    @BindView(R.id.tab1)
    TextView tab1;
    @BindView(R.id.tab2)
    TextView tab2;
    @BindView(R.id.tab3)
    TextView tab3;
    @BindView(R.id.tab4)
    TextView tab4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initLayoutTransition();
    }

    @OnClick({R.id.tab1, R.id.tab2, R.id.tab3, R.id.tab4})
    void onTabClick(View view) {
        view.setVisibility(View.GONE);
    }

    @OnClick(R.id.reset)
    void onResetClick() {
        tab1.setVisibility(View.VISIBLE);
        tab2.setVisibility(View.VISIBLE);
        tab3.setVisibility(View.VISIBLE);
        tab4.setVisibility(View.VISIBLE);
    }

    private void initLayoutTransition(){
        LayoutTransition layoutTransition = new LayoutTransition();
        PropertyValuesHolder pvhLeft = PropertyValuesHolder.ofInt("left", 0, 1);
        PropertyValuesHolder pvhRight = PropertyValuesHolder.ofInt("right", 0, 1);

        ObjectAnimator changingAnimation = ObjectAnimator.ofPropertyValuesHolder(containerView,
                pvhLeft, pvhRight);
        changingAnimation.setInterpolator(new DecelerateInterpolator());

        layoutTransition.setAnimator(LayoutTransition.CHANGE_APPEARING, changingAnimation);
        layoutTransition.setAnimator(LayoutTransition.CHANGE_DISAPPEARING, changingAnimation);
        layoutTransition.setStartDelay(LayoutTransition.CHANGE_DISAPPEARING, 0);
        layoutTransition.setDuration(5000);
        containerView.setLayoutTransition(layoutTransition);
    }
}
