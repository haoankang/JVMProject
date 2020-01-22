package ank.hao.intern;

/**
 * string.intern()
 * 使用方法：new String().intern()
 * jdk7之后常量池不在perm区了，放在heap堆中
 *    这个方法的意思：去查询常量池中是否有当前字符串，如果有直接返回；如果没有，将该字符串对象地址放到常量池中；
 * 用处：可以当作字符串缓存，在大量创建字符串对象时使用，可以节省空间；
 * 缺点：大量可变字符串使用，会严重影响存储效率；默认的 StringPool 的长度是1009且不可变的。
 * 因此一旦常量池中的字符串达到的一定的规模后，性能会急剧下降。
 */

public class InternDemo {

    public static void main(String[] args) {
        String a = "11";
        a.intern();
        String b = "11";
        System.out.println(a==b);

        String c = "2" + "2";
        System.out.println(c);
        c.intern();
        String d = "22";
        System.out.println(c==d);
    }
}
