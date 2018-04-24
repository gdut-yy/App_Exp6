package net.devyy.exp6;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.widget.Toast;

public class AidlService extends Service {
    public AidlService( ) {
    }

    private final IMyAidlInterface.Stub mbinder = new IMyAidlInterface.Stub() {
        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public int Compare(int a, int b) throws RemoteException {
            return Math.max(a, b);
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
//        throw new UnsupportedOperationException("Not yet implemented");
        Toast.makeText(this,"远程绑定：MyService",Toast.LENGTH_SHORT).show();
        return mbinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Toast.makeText(this,"取消远程绑定：MyService",Toast.LENGTH_SHORT).show();
        return super.onUnbind(intent);
    }
}
