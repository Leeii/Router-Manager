package com.leeiidesu.oa.router;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.leeiidesu.lib.core.android.Logger;
import com.leeiidesu.lib.router.DRouter;
import com.leeiidesu.oa.router.manager.RouterManager;
import com.leeiidesu.oa.router.manager.RouterService;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void toMain2(View view) {

    }

    public void toMain3(View view) {
        RouterManager.getService().routerToMain3("liyi");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0x200 && resultCode == RESULT_OK) {
            Toast.makeText(this, "name = " + data.getStringExtra("name"), Toast.LENGTH_LONG).show();
            Logger.e("TAG", "name = " + data.getStringExtra("name"));
        }
    }

    public void toMain4(View view) {

    }
}
