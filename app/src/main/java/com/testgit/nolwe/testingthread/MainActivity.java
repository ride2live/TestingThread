package com.testgit.nolwe.testingthread;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;




public class MainActivity extends ActionBarActivity {

    Thread t1;
    Thread1 r1;
    Thread t2;
    Thread1 r2;
    Handler handlerFromWorkThread;
    Handler nextoneworkthread;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println ("add some code in main");
    }

    public void start(View V)
    {

        handlerFromWorkThread = new Handler()
        {
            @Override
            public void handleMessage(Message msg) {
                System.out.println ("working thread" +msg.arg1);
            }
        };

        nextoneworkthread = new Handler()
        {
            @Override
            public void handleMessage(Message msg) {
                System.out.println ("next one work thread" +msg.arg1);
            }
        };
        r1 = new Thread1(handlerFromWorkThread);
        t1 = new Thread(r1);

        r2 = new Thread1(nextoneworkthread);
        t2 = new Thread(r2);
        t1.start();


    }
    public void stop(View V)
    {

        t1.interrupt();
        //t2.interrupt();
    }
    public void send(View V)
    {
        Message m = new Message();
        m.arg1 = 100;
        Handler inThreadHandler = r1.getInThreadHandler();
        inThreadHandler.sendMessage(m);
        //inThreadHandler.getLooper().quit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("on stop called, removing callbacks");
        handlerFromWorkThread.removeCallbacks(r1);
        nextoneworkthread.removeCallbacks(r2);
    }


}
