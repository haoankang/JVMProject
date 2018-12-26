package three.six;

public class MinorGCDemo3 {

//    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) {
//        byte[] allocation1,allocation2,allocation3,allocation4;

//        allocation1 = new byte[2 * _1MB];
//        allocation2 = new byte[2 * _1MB];
//        allocation3 = new byte[2 * _1MB];
        byte[] bytes = new byte[256 *1024];
        byte[] bytes1 = new byte[4 * 1024 *1024];
        byte[] bytes2 = new byte[4 * 1024 *1024];
        bytes2 = null;
        bytes2 = new byte[4 * 1024 *1024];
    }
}
