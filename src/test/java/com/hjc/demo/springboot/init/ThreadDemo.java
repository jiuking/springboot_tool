package com.hjc.demo.springboot.init;

/**
 * @author : Administrator
 * @date : 2018/12/21 0021 10:59
 * @description : 多线程异常测试
 */
//书写售票的示例
class Demo implements Runnable{
    //定义变量记录剩余的票数
    int num = 100;

    //创建一个对象，用作同步中的锁对象
    Object obj = new Object();

    //实现run方法
    public void run()    {

        //实现售票的过程
        while( true )        {
            //   t1  t2  t3
            //判断当前有没有线程正在if中操作num，如果有当前线程就在这里临时等待
            //这种思想称为线程的同步
            //当票数小等于0的时候，就不再售票了
            //使用同步代码块把线程要执行的任务代码可以同步起来
//            synchronized( obj )  //t1   在进入同步之前线程要先获取锁
            /*
                当某个线程执行到synchronized关键字之后，这时JVM会判断当前
                同步上的这个对象有没有已经被其他线程获取走了，如果这时没有其他
                线程获取这个对象，这时就会把当前同步上的这个对象交给当前正要进入
                同步的这个线程。
            */
//            {
                if( num > 0 )
                {
                    //t0
                    try{Thread.sleep(2);}catch( InterruptedException e ){}
                    System.out.println(Thread.currentThread().getName()+"....."+num);
                    num--;
                }else
                    break;
            }//线程执行完同步之后，那么这时当前这个线程就会把锁释放掉
//        }
    }
}
public class ThreadDemo {
    public static void main(String[] args)     {
        //创建线程任务
        Demo d = new Demo();
        //创建线程对象
        Thread t = new Thread( d );
        Thread t2 = new Thread(d);
        Thread t3 = new Thread(d);
        Thread t4 = new Thread(d);
        //开启线程
        t.start();
        t2.start();
        t3.start();
        t4.start();
    }
}
