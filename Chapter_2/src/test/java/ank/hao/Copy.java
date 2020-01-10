package ank.hao;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.Serializable;

/**
 * 深拷贝和浅拷贝
 * 浅拷贝：实现Cloneable接口，直接用对象clone()方法获得，基本类型拷贝，引用类型使用原来对象地址
 * 深拷贝：实现方式————先序列化再反序列化，或者重写clone()
 */
public class Copy {

    private static Panda aa;
    private static Panda aaa;
    private static Panda bb;

    @BeforeAll
    public static void before(){
        aa = new Panda();
        aa.setAge(22);
        aa.setName("aa");

        aaa = new Panda();
        aaa.setName("aaa");
        aaa.setAge(23);

        bb = new Panda();
        bb.setName("bb");
        bb.setAge(3);
        bb.setMom(aa);
    }

    @Test
    public void test_shallow() throws CloneNotSupportedException {

        Panda cc = (Panda) bb.clone();
        cc.setName("cc");
        cc.setAge(4);
        cc.getMom().setMom(aaa);

        System.out.println("bb is: "+bb);
        System.out.println("cc is: "+cc);
    }

    @Test
    public void test_deap() throws IOException {
        String bb_json = JSON.toJSONString(bb);
//        FileWriter fileWriter = new FileWriter("d:\\bb");
//        fileWriter.write(bb_json);
//        fileWriter.flush();
//        Panda dd = JSON.parseObject(new FileInputStream("d:\\bb"), Panda.class);
        Panda dd = JSON.parseObject(bb_json, Panda.class);

        dd.setName("dd");
        dd.setAge(5);
        dd.setMom(aaa);
        System.out.println("bb is: "+bb);
        System.out.println("dd is: "+dd);
    }

    @Test
    public void test_deap2() throws CloneNotSupportedException {
        Panda ee = (Panda) bb.clone();
        ee.setName("ee");
        ee.setAge(6);
        ee.setMom(aaa);

        System.out.println("bb is: "+bb);
        System.out.println("ee is: "+ee);
    }

}

@Data
class Panda implements Cloneable,Serializable {
    private String name;
    private Integer age;
    private Panda mom;

    @Override
    public String toString() {
        return "Panda{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", mom=" + mom +
                '}';
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
//        Panda panda = (Panda) super.clone();
//        panda.mom = (Panda) this.mom.clone();
//        return panda;
        return super.clone();
    }
}
