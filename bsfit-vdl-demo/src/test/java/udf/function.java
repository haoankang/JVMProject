package udf;

/**
 * @program: bsfit-fdl
 * @description: 方法
 * @author: WangLH
 * @create: 2018-09-13  13:-52
 **/
public class function {
    public static double plus(Object obj1,Object obj2) {
        if (obj1 == null || obj2 == null)
            return -1D;
        double item1 = 0D, item2 = 0D;
        item1 = Double.parseDouble(obj1.toString());
        item2 = Double.parseDouble(obj2.toString());
        return item1 + item2;
    }
}
