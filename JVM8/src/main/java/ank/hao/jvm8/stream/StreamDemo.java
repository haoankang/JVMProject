package ank.hao.jvm8.stream;

import lombok.Getter;
import lombok.Setter;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class StreamDemo {

    @Getter
    @Setter
    class Tree{
        int id;
        String name;
        String addr;
    }

    @Test
    public void test(){
        List<Tree> list = new ArrayList<>();
        for(int i=1;i<6;i++){
            Tree tree = new Tree();
            tree.setId(i);
            tree.setName("树苗"+i);
            tree.setAddr("地址"+i);
            list.add(tree);
        }

    }
}
