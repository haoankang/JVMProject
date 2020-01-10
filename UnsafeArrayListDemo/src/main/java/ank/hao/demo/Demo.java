package ank.hao.demo;

import com.alibaba.fastjson.JSONArray;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Demo {

    public static void main(String[] args) throws InterruptedException {
        new Demo().demo();
    }

    void demo() throws InterruptedException {
        final ThreadPoolExecutor executor = new ThreadPoolExecutor(10,100,1000L, TimeUnit.MILLISECONDS,new LinkedBlockingDeque<>(Integer.MAX_VALUE)
        );


        int num = 100;
        JSONArray jsonArray = new JSONArray(num);
        ArrayList arrayList = new ArrayList(num);
        AtomicInteger count = new AtomicInteger(0);
        CountDownLatch countDownLatch = new CountDownLatch(num);
        while(count.intValue()<num){
            executor.execute(new Task(count.intValue(),jsonArray,countDownLatch,arrayList));
            count.incrementAndGet();
        }
        countDownLatch.await();
        System.out.println(jsonArray.contains(null));
//        System.out.println(jsonArray);
//
//        System.out.println(arrayList.contains(null));

    }
}

class Task implements Runnable{

    private Integer count;
    private JSONArray jsonArray;
    private CountDownLatch countDownLatch;
    private ArrayList arrayList;

    Task(Integer count,JSONArray jsonArray,CountDownLatch countDownLatch,ArrayList arrayList){
        this.count = count;
        this.jsonArray = jsonArray;
        this.countDownLatch = countDownLatch;
        this.arrayList = arrayList;
    }

    @Override
    public void run() {
        //jsonArray.set(count,count);
        jsonArray.add(count,count);
        countDownLatch.countDown();
    }
}
