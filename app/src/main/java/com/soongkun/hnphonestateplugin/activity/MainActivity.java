package com.soongkun.hnphonestateplugin.activity;

import android.Manifest;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.soongkun.hnphonestateplugin.R;
import com.soongkun.hnphonestateplugin.service.PhoneStateService;
import com.soongkun.hnphonestateplugin.utils.CommonUtils;

import java.util.ArrayList;
import java.util.List;

import me.weyye.hipermission.HiPermission;
import me.weyye.hipermission.PermissionCallback;
import me.weyye.hipermission.PermissionItem;

public class MainActivity extends AppCompatActivity {

    private Button mBtnAction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handlePermission();

        mBtnAction = (Button) findViewById(R.id.button);

        initListener();
    }

    private void handlePermission() {
        List<PermissionItem> permissionItems = new ArrayList<>();
        permissionItems.add(new PermissionItem(Manifest.permission.READ_PHONE_STATE, "获取电话状态", R.drawable.permission_ic_phone));
        permissionItems.add(new PermissionItem(Manifest.permission.PROCESS_OUTGOING_CALLS, "获取拨打电话号码", R.drawable.permission_ic_location));

        HiPermission.create(this)
                .permissions(permissionItems)
                .checkMutiPermission(new PermissionCallback() {
                    @Override
                    public void onClose() {
                    }

                    @Override
                    public void onFinish() {
                    }

                    @Override
                    public void onDeny(String permission, int position) {
                    }

                    @Override
                    public void onGuarantee(String permission, int position) {

                    }
                });
    }


    @Override
    protected void onResume() {
        super.onResume();
        initView();
    }

    private void initView() {
        boolean isPhoneStateServiceRunning = CommonUtils.isServiceRunning(this, PhoneStateService.class.getName());
        if(isPhoneStateServiceRunning){
            disableBtn();
        }else{
            mBtnAction.setText(R.string.to_open_listener);
            mBtnAction.setEnabled(true);
        }
    }

    private void initListener() {
        mBtnAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startPhoneStateService();
            }
        });
    }

    private void startPhoneStateService() {
        Intent intent = new Intent(MainActivity.this,PhoneStateService.class);
        startService(intent);
        disableBtn();
    }


    private void disableBtn(){
        mBtnAction.setText(R.string.to_open_listener_disable);
        mBtnAction.setEnabled(false);
    }


}
