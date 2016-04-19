package wsh.popupwindow;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button toPopUpWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.toPopUpWindow).setOnClickListener(this);
    }


    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.toPopUpWindow:
                showLeftMenu(MainActivity.this, view);
                break;
            default:
                break;
        }
    }

    public void showLeftMenu(Context context,View view) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
        View PopupWindow = inflater.inflate(R.layout.popupwindow, null);
        final android.widget.PopupWindow pw = new PopupWindow(PopupWindow, 330, ViewGroup.LayoutParams.MATCH_PARENT, true);
        pw.setAnimationStyle(R.style.AnimationFade);
        pw.showAtLocation(view, Gravity.LEFT, 0, 0);

        Button toOtherActivity = (Button) PopupWindow.findViewById(R.id.toOtherActivity);
        toOtherActivity.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                pw.dismiss();
                Intent intent = new Intent();
                intent.setClass(v.getContext(), OtherActivity.class);
                v.getContext().startActivity(intent);
            }
        });

        PopupWindow.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                if (pw != null && pw.isShowing()) {
                    pw.dismiss();
                }
                return false;
            }
        });

    }
}
