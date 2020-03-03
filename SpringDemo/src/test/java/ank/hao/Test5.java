package ank.hao;

import org.junit.Test;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Test5 {

    @Test
    public void test(){
        try {
            Integer s = null;
            System.out.println(s/22);
        }catch (NullPointerException ex){
            System.out.println("catch it.");
        }
    }

    @Test
    public void test2(){

        Integer s = null;
        System.out.println(s/22);

    }

    @Test
    public void test3(){
        try {
            Integer s = null;
            System.out.println(s/22);
            return;
        }catch (NullPointerException ex){
            System.out.println("catch it.");
            return;
        }finally {
            System.out.println("finally run");
        }
    }

    @Test
    public void test4(){
        System.out.println(3*0.1==0.3);

        System.out.println(0.0==0);
    }

    @Test
    public void test5(){
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        lock.writeLock().lock();
        lock.writeLock().unlock();

        lock.readLock().lock();
        lock.readLock().unlock();
    }
}
