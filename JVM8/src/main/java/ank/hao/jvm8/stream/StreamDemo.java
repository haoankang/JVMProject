package ank.hao.jvm8.stream;

import lombok.Getter;
import lombok.Setter;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

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

        List<String> nameList = list.stream().filter(tree -> tree.getId()%3!=0)
                .sorted((x,y) -> y.getId()-x.getId())
                .map(Tree::getName)
                .collect(Collectors.toList());
        System.out.println(nameList);

        Random random = new Random();
        random.doubles().limit(10).forEach(System.out::println);
    }
}
