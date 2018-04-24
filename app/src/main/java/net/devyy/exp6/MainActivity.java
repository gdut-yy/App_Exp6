package net.devyy.exp6;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText et_num1;
    private EditText et_num2;
    private Button bt_bind;
    private Button bt_max;
    private Button bt_unbind;
    private TextView tv_show;

    // 本地服务
    private MyService myService;

    // 远程服务
//    private IMyAidlInterface myService;

    private ServiceConnection serviceConnection;
    private boolean isBound=false;

    public MainActivity( ) {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_num1=(EditText)findViewById(R.id.et_num1);
        et_num2=(EditText)findViewById(R.id.et_num2);
        bt_bind=(Button)findViewById(R.id.bt_bind);
        bt_max=(Button)findViewById(R.id.bt_max);
        bt_unbind=(Button)findViewById(R.id.bt_unbind);
        tv_show=(TextView)findViewById(R.id.tv_show);

        serviceConnection=new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                // 本地服务
                myService=((MyService.MyBinder)service).getService();

                // 远程服务
//                myService=IMyAidlInterface.Stub.asInterface(service);
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                myService=null;
            }
        };

        bt_bind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!isBound){
                    // 本地服务
                    final Intent serviceIntent = new Intent(MainActivity.this,MyService.class);

                    // 远程服务
//                    final Intent serviceIntent = new Intent(MainActivity.this,AidlService.class);
//                    final Intent serviceIntent = new Intent();
//                    serviceIntent.setAction("net.devyy.exp6.AidlService");
//
                    bindService(serviceIntent,serviceConnection, Context.BIND_AUTO_CREATE);
                    isBound=true;
                }
            }
        });

        bt_max.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(myService==null){
                    tv_show.setText("未绑定服务");
                    return;
                }

                String num1=et_num1.getText().toString();
                String num2=et_num2.getText().toString();
                int int1=Integer.valueOf(num1);
                int int2=Integer.valueOf(num2);

                int max=0;
                // 本地服务
                max= myService.Compare(int1,int2);

                // 远程服务
//                try {
//                    max= myService.Compare(int1,int2);
//                }catch (RemoteException e){
//                    e.printStackTrace();
//                }


                tv_show.setText(String.valueOf(max));
            }
        });

        bt_unbind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isBound){
                    isBound=false;
                    unbindService(serviceConnection);
                    myService=null;
                }
            }
        });
    }
}
