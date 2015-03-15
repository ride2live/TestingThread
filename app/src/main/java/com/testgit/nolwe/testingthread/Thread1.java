package com.testgit.nolwe.testingthread;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/**
 * Created by Nolwe on 13.03.2015.
 */

public class Thread1 implements Runnable {
    Handler inThreadHandler;
    Handler handlerFromworkThread;
    public Thread1(Handler handlerFromworkThread) {
        this.handlerFromworkThread = handlerFromworkThread;
        this.inThreadHandler = inThreadHandler;

    }

    @Override
    public void run() {
        Looper.prepare();
        inThreadHandler = new Handler()
        {
            @Override
            public void handleMessage(Message msg) {
                System.out.println("handle from UI "+msg.arg1);
            }
        };
        Looper.loop();
        try {
        int i = 0;
            Message m = new Message();
            while (true && !Thread.currentThread().isInterrupted())
            {


                i++;
                m.arg1 = i;

                handlerFromworkThread.sendMessage(m);



                Thread.sleep(2000l);


            }
        }
        catch (InterruptedException e)
        {
            System.out.println("InterruptedException");
            return;
        }
        finally {
            System.out.println("finally");
            return;
        }

    }
    public Handler getInThreadHandler( ) {
        return inThreadHandler;
    }
}
