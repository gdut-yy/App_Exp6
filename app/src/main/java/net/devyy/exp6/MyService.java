package net.devyy.exp6;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

public class MyService extends Service {

    private final IBinder iBinder = new MyBinder();

    public MyService( ) {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
//        throw new UnsupportedOperationException("Not yet implemented");
        Toast.makeText(this,"本地绑定：MyService",Toast.LENGTH_SHORT).show();
        return iBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Toast.makeText(this,"取消本地绑定：MyService",Toast.LENGTH_SHORT).show();
        return super.onUnbind(intent);
    }

    @Override
    public void onCreate( ) {
        super.onCreate();
//        Toast.makeText(this,"(1)调用onCreate( )",Toast.LENGTH_LONG).show();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this,"(2)调用onStartCommand()",Toast.LENGTH_SHORT).show();

//        double randomDouble = Math.random();
//        String msg="随机数:"+String.valueOf(randomDouble);
//        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy( ) {
        super.onDestroy();
//        Toast.makeText(this,"(3)调用onDestroy( )",Toast.LENGTH_SHORT).show();
    }
    public class MyBinder extends Binder {
        MyService getService(){
            return MyService.this;
        }
    }

    public int Compare(int integer1,int integer2){
        return Math.max(integer1,integer2);
    }

    public int Compare(String string1, String string2) {
        return Math.max(Integer.valueOf(string1),Integer.valueOf(string2));
    }
}
