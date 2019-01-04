package ank.hao.test;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

/**
 * System.in在junit中不起作用
 */
public class MyTest {

    @Test
    public void test(){
        System.out.println("start..");
        Scanner scanner = new Scanner(System.in);

        String string = scanner.nextLine();

        System.out.println(string);

        System.out.println("end..");
    }

    @Test
    public void test1() throws IOException {
        System.out.println("start..1");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String line = bufferedReader.readLine();
        while(line != null){
            System.out.println("heyhey.."+line);
        }
    }

    @Test
    public void test2() {
        Set<String> set = new HashSet<>();
        set.add("ka");
        set.add("ba");
        set.add("ca");
        Iterator<String> iterator = set.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
            iterator.remove();
        }
    }

    public static void main(String[] args) throws IOException {
//        System.out.println("please input..");
//        int i=0;
//        while(i!=-1){
//            i = System.in.read();
//            System.out.println(i);
//        }
    }
}
