package com.testgit.nolwe.testingthread;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/**
 * Created by Nolwe on 13.03.2015.
 */

public class Thread1 implements Runnable {
    Handler inThreadHandler;
    Handler handlerFromWorkThread;
    public Thread1(Handler handlerFromWorkThread) {
        this.handlerFromWorkThread = handlerFromWorkThread;
    }

    @Override
    public void run() {
        Looper.prepare();
        inThreadHandler = new Handler()
        {
            @Override
            public void handleMessage(Message msg) {
                System.out.println("handle from UI "+msg.arg1);
                longOperationStart();
            }
        };
        Looper.loop();



        return;
    }

    private void longOperationStart() {
        try {
            int i = 0;
            Message m = new Message();

            while (true && !Thread.currentThread().isInterrupted())
            {

                System.out.println("running while");
                i++;

                m.arg1 = i;

                handlerFromWorkThread.sendMessage(m);




                Thread.sleep(2000l);


            }

        }
        catch (InterruptedException e)
        {
            System.out.println("InterruptedException");

        }
        finally {
            System.out.println("finally");

        }
    }

    public Handler getInThreadHandler( ) {
        return inThreadHandler;
    }
}
