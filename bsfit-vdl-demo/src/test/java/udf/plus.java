package udf;//package cn.com.bsfit.vdl.udf;
//
//import cn.com.bsfit.frms.obj.*;
//import org.apache.spark.sql.api.java.UDF2;
//
//public class plus implements UDF2{
//    @Override
//    public Double call(Object int1, Object int2) throws Exception {
//        return function.plus(int1,int2);
//    }
//    public double getNumber(Object obj) {
//        /**
//         * 获取数值
//         *
//         * @param obj
//         *            Number/Mergeable/String
//         * @return Number
//         */
//        double item = -1D;
//        if (obj instanceof Number & !(obj instanceof MergeableNumber)) {
//            item = Double.parseDouble(String.valueOf(obj));
//        }
//        if (obj instanceof SumNumber) {
//            item = ((SumNumber) obj).doubleValue();
//        }
//        if (obj instanceof CountNumber) {
//            item = ((CountNumber) obj).doubleValue();
//        }
//        if (obj instanceof AvgNumber) {
//            item = ((AvgNumber) obj).doubleValue();
//        }
//        if (obj instanceof DecreaseCountNumber) {
//            item = ((DecreaseCountNumber) obj).doubleValue();
//        }
//        if (obj instanceof IncreaseCountNumber) {
//            item = ((IncreaseCountNumber) obj).doubleValue();
//        }
//        if (obj instanceof MaxNumber) {
//            item = ((MaxNumber) obj).doubleValue();
//        }
//        if (obj instanceof MinNumber) {
//            item = ((MinNumber) obj).doubleValue();
//        }
//        if (obj instanceof DistinctedListObject) {
//            item = ((DistinctedListObject) obj).getSet().size();
//        }
//        if (obj instanceof MaxContinuousCountNumber) {
//            item = ((MaxContinuousCountNumber) obj).doubleValue();
//        }
//        if (obj instanceof MaxDecreaseCountNumber) {
//            item = ((MaxDecreaseCountNumber) obj).doubleValue();
//        }
//        if (obj instanceof MaxIncreaseCountNumber) {
//            item = ((MaxIncreaseCountNumber) obj).doubleValue();
//        }
//        if (obj instanceof VarpNumber) {
//            item = ((VarpNumber) obj).doubleValue();
//        }
//        if (obj instanceof VarsNumber) {
//            item = ((VarsNumber) obj).doubleValue();
//        }
//        if (obj instanceof ReplacedObject) {
//            Object rObject = ((ReplacedObject) obj).getObject();
//            if (rObject instanceof Number)
//                item = Double.parseDouble(String.valueOf(rObject));
//        }
//        if (obj instanceof String) {
//            String str = (String) obj;
//            if (str.matches("^[+-]?([0-9]*\\.?[0-9]+|[0-9]+\\.?[0-9]*)([eE][+-]?[0-9]+)?$"))
//                item = Double.parseDouble(str);
//        }
//        return item;
//    }
//}
//
