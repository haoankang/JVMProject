package oome;

import java.util.ArrayList;
import java.util.List;

public class HeapDemo {
    public static void main(String[] args) {
        List<Object> list = new ArrayList<Object>();
        while(true){
            list.add(new HeapDemo());
        }
    }
}
