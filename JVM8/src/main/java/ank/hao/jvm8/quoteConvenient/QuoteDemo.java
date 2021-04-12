package ank.hao.jvm8.quoteConvenient;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 便捷引用类的方法或构造器
 */
public class QuoteDemo {
    public static void main(String[] args) {
        Quote1Factory<Quote1> quote1Quote1Factory = Quote1::new;
        Quote1 quote1 = quote1Quote1Factory.create("jdk",8);
        Supplier<String> supplier = quote1::getName;
        System.out.println(supplier.get());

        Function<String,Integer> toStringFunction = Integer::valueOf;
        System.out.println(toStringFunction.apply("23"));
        Function<String,String> backToStr = toStringFunction.andThen(String::valueOf);
        System.out.println(backToStr.apply("34"));

        BiFunction<String,Integer,Quote1> biFunction = Quote1::new;
        System.out.println(biFunction.apply("dijia",3000));

        String aa = null;
        String bb = "ks";
        Optional<String> optional = Optional.of(bb);
        Optional<String> optional1 = Optional.ofNullable(aa);
        System.out.println(optional.isPresent());
        System.out.println(optional1.isPresent());
        optional.ifPresent(s -> System.out.println("null"));

        Arrays.asList("ksd","ekd","dsk").stream().count();

    }
}

@Getter
@Setter
@ToString
class Quote1{
    private String name;
    private Integer age;

    public Quote1(String name,Integer age){
        this.name = name;
        this.age = age;
    }
}

interface Quote1Factory<P extends Quote1>{
    P create(String name,Integer age);
}
