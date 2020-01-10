package vdl;

import cn.com.bsfit.vdl.VdlToWindowsSQL;
import cn.com.bsfit.vdl.config.SparkUdwafRegister;
import cn.com.bsfit.vdl.core.dto.VDLDto;
import cn.com.bsfit.vdl.exp.FeatureDefinitionException;
import cn.com.bsfit.vdl.exp.GeneratorException;
import cn.com.bsfit.vdl.exp.VDLSchemaException;
import com.alibaba.fastjson.JSON;
import org.apache.spark.SparkConf;
import org.apache.spark.sql.AnalysisException;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class VdlToWindowsSQLTest {

    private SparkSession spark;

    @Before
    public void BeforeTest() {
        if (spark != null) {
            return;
        }
        SparkConf sparkConf = new SparkConf()
                .setMaster("local");
        spark = SparkSession
                .builder()
                .appName("Java Spark SQL")
                .config(sparkConf)
                .getOrCreate();
        SparkUdwafRegister.registUdwaf(spark);
    }


    private void testSQL(final Map<String, String> resultVal, String testFDLFile) throws IOException, VDLSchemaException, FeatureDefinitionException, GeneratorException {

        Dataset<Row> testData = spark.read().format("csv").option("header", true).load("testData_window.csv");
        testData.createOrReplaceTempView("testData");
        Dataset<Row> df = spark.sql("select cast(transTime as long) as transTime,cast(acctId as long) as acctId,cast(merId as long) as merId,cast(transAmt as double) as transAmt,cast(city as string) as city,cast(bizCode as string) as bizCode,cast(stat as string) as stat,cast(stat1 as boolean) as stat1 from testData");

        URL url = getClass().getClassLoader().getResource(testFDLFile);
        FileReader reader = new FileReader(url.getFile());
        StringBuilder resultString = new StringBuilder();
        char[] chars = new char[1024];
        int m;
        while ((m = reader.read(chars)) != -1) {
            resultString.append(chars, 0, m);
        }

        String json = resultString.toString();
        VDLDto dto = JSON.parseObject(json, VDLDto.class);

        VdlToWindowsSQL windowsSQL = new VdlToWindowsSQL(dto);
        String sqlScript = windowsSQL.getScript();
        System.out.println(""+sqlScript);

        df.createOrReplaceTempView(dto.getUse());
        Dataset<Row> sqlDF = spark.sql(sqlScript);
        sqlDF.show();
        //sqlDF.select(
        if (resultVal != null)
            sqlDF.foreach(row -> {
                Assert.assertEquals(resultVal.get(String.valueOf(row.get(0))), String.valueOf(row.get(8)));
            });
    }


    @Test
    public void avgTest() throws VDLSchemaException, FeatureDefinitionException, IOException, GeneratorException, AnalysisException {
        String testFDLFile = "udwaf/avg.json";

        Map<String, String> resultVal = new HashMap<>();

        resultVal.put("1524462981038", "0.0");
        resultVal.put("1524462981138", "0.0");
        resultVal.put("1524462981238", "1.0");
        resultVal.put("1524462981338", "1.0");
        resultVal.put("1524462981438", "1.0");
        resultVal.put("1524462981538", "2.3333333333333335");
        resultVal.put("1524462981638", "3.25");
        resultVal.put("1524462981738", "10.6");
        resultVal.put("1524462981838", "13.833333333333334");
        resultVal.put("1524462981938", "14.714285714285714");
        testSQL(resultVal, testFDLFile);
    }
    @Test
    public void sumTest() throws VDLSchemaException, FeatureDefinitionException, IOException, GeneratorException, AnalysisException {
        String testFDLFile = "udwaf/sum.json";
        Map<String, String> resultVal = new HashMap<>();
        resultVal.put("1524462981038", "0.0");
        resultVal.put("1524462981138", "1.0");
        resultVal.put("1524462981238", "2.0");
        resultVal.put("1524462981338", "5.0");
        resultVal.put("1524462981438", "6.0");
        resultVal.put("1524462981538", "11.0");
        resultVal.put("1524462981638", "17.0");
        resultVal.put("1524462981738", "57.0");
        resultVal.put("1524462981838", "87.0");
        resultVal.put("1524462981938", "107.0");
        testSQL(resultVal, testFDLFile);
    }
    @Test
    public void countTest() throws VDLSchemaException, FeatureDefinitionException, IOException, GeneratorException, AnalysisException {
        String testFDLFile = "udwaf/count.json";
        Map<String, String> resultVal = new HashMap<>();
        resultVal.put("1524462981038", "0");
        resultVal.put("1524462981138", "1");
        resultVal.put("1524462981238", "2");
        resultVal.put("1524462981338", "3");
        resultVal.put("1524462981438", "4");
        resultVal.put("1524462981538", "5");
        resultVal.put("1524462981638", "6");
        resultVal.put("1524462981738", "7");
        resultVal.put("1524462981838", "8");
        resultVal.put("1524462981938", "9");
        testSQL(resultVal, testFDLFile);
    }
    @Test
    public void distinctCountTest() throws VDLSchemaException, FeatureDefinitionException, IOException, GeneratorException, AnalysisException {
        String testFDLFile = "udwaf/distinctCount.json";
        Map<String, String> resultVal = new HashMap<>();
        resultVal.put("1524462981038", "0");
        resultVal.put("1524462981138", "1");
        resultVal.put("1524462981238", "2");
        resultVal.put("1524462981338", "3");
        resultVal.put("1524462981438", "3");
        resultVal.put("1524462981538", "3");
        resultVal.put("1524462981638", "4");
        resultVal.put("1524462981738", "4");
        resultVal.put("1524462981838", "4");
        resultVal.put("1524462981938", "4");
        testSQL(resultVal, testFDLFile);
    }
    @Test
    public void distinctListTest() throws VDLSchemaException, FeatureDefinitionException, IOException, GeneratorException, AnalysisException {
        String testFDLFile = "udwaf/distinctList.json";
        Map<String, String> resultVal = new HashMap<>();
        resultVal.put("1524462981038", "0");
        resultVal.put("1524462981138", "a");
        resultVal.put("1524462981238", "a, b");
        resultVal.put("1524462981338", "a, b, c");
        resultVal.put("1524462981438", "a, b, c");
        resultVal.put("1524462981538", "a, b, c");
        resultVal.put("1524462981638", "a, b, c, e");
        resultVal.put("1524462981738", "a, b, c, e");
        resultVal.put("1524462981838", "a, b, c, e");
        resultVal.put("1524462981938", "a, b, c, e");
        testSQL(resultVal, testFDLFile);
    }

    @Test
    public void maxTest() throws VDLSchemaException, FeatureDefinitionException, IOException, GeneratorException, AnalysisException {
        String testFDLFile = "udwaf/max.json";
        Map<String, String> resultVal = new HashMap<>();
        resultVal.put("1524462981038", "0.0");
        resultVal.put("1524462981138", "0.0");
        resultVal.put("1524462981238", "1.0");
        resultVal.put("1524462981338", "1.0");
        resultVal.put("1524462981438", "1.0");
        resultVal.put("1524462981538", "5.0");
        resultVal.put("1524462981638", "6.0");
        resultVal.put("1524462981738", "40.0");
        resultVal.put("1524462981838", "40.0");
        resultVal.put("1524462981938", "40.0");
        testSQL(resultVal, testFDLFile);
    }
    @Test
    public void minTest() throws VDLSchemaException, FeatureDefinitionException, IOException, GeneratorException, AnalysisException {
        String testFDLFile = "udwaf/min.json";
        Map<String, String> resultVal = new HashMap<>();

        resultVal.put("1524462981038", "0.0");
        resultVal.put("1524462981138", "0.0");
        resultVal.put("1524462981238", "1.0");
        resultVal.put("1524462981338", "1.0");
        resultVal.put("1524462981438", "1.0");
        resultVal.put("1524462981538", "1.0");
        resultVal.put("1524462981638", "1.0");
        resultVal.put("1524462981738", "1.0");
        resultVal.put("1524462981838", "1.0");
        resultVal.put("1524462981938", "1.0");

        testSQL(resultVal, testFDLFile);
    }


    @Test
    public void sortedListTest() throws VDLSchemaException, FeatureDefinitionException, IOException, GeneratorException, AnalysisException {
        String testFDLFile = "udwaf/sortedList.json";
        Map<String, String> resultVal = new HashMap<>();
        resultVal.put("1524462981038", "0");
        resultVal.put("1524462981138", "1.0");
        resultVal.put("1524462981238", "1.0");
        resultVal.put("1524462981338", "3.0, 1.0");
        resultVal.put("1524462981438", "3.0, 1.0");
        resultVal.put("1524462981538", "5.0, 3.0, 1.0");
        resultVal.put("1524462981638", "6.0, 5.0, 3.0");
        resultVal.put("1524462981738", "40.0, 6.0, 5.0");
        resultVal.put("1524462981838", "40.0, 30.0, 6.0");
        resultVal.put("1524462981938", "40.0, 30.0, 20.0");
        testSQL(resultVal, testFDLFile);
    }

    @Test
    public void cenmon3Test() throws VDLSchemaException, FeatureDefinitionException, IOException, GeneratorException, AnalysisException {
        String testFDLFile = "udwaf/cenmon3.json";
        Map<String, String> resultVal = new HashMap<>();
        resultVal.put("1524462981038", "0.0");
        resultVal.put("1524462981138", "0.0");
        resultVal.put("1524462981238", "0.0");
        resultVal.put("1524462981338", "0.5925925925925922");
        resultVal.put("1524462981438", "0.7499999999999997");
        resultVal.put("1524462981538", "3.456000000000001");
        resultVal.put("1524462981638", "3.9074074074074088");
        resultVal.put("1524462981738", "4437.271137026238");
        resultVal.put("1524462981838", "3500.636718749999");
        resultVal.put("1524462981938", "2620.2194787379967");
        testSQL(resultVal, testFDLFile);
    }

    @Test
    public void cenmon4Test() throws VDLSchemaException, FeatureDefinitionException, IOException, GeneratorException, AnalysisException {
        String testFDLFile = "udwaf/cenmon4.json";
        Map<String, String> resultVal = new HashMap<>();
        resultVal.put("1524462981038", "0.0");
        resultVal.put("1524462981138", "0.0");
        resultVal.put("1524462981238", "0.0");
        resultVal.put("1524462981338", "1.1851851851851862");
        resultVal.put("1524462981438", "1.3125000000000004");
        resultVal.put("1524462981538", "13.619200000000006");
        resultVal.put("1524462981638", "26.08101851851852");
        resultVal.put("1524462981738", "148371.98417326107");
        resultVal.put("1524462981838", "110933.62817382808");
        resultVal.put("1524462981938", "87584.87608596246");
        testSQL(resultVal, testFDLFile);
    }


    @Test
    public void increaseCountTest() throws VDLSchemaException, FeatureDefinitionException, IOException, GeneratorException, AnalysisException {
        String testFDLFile = "udwaf/increaseCount.json";
        Map<String, String> resultVal = new HashMap<>();
        resultVal.put("1524462981038", "0");
        resultVal.put("1524462981138", "0");
        resultVal.put("1524462981238", "0");
        resultVal.put("1524462981338", "1");
        resultVal.put("1524462981438", "1");
        resultVal.put("1524462981538", "2");
        resultVal.put("1524462981638", "3");
        resultVal.put("1524462981738", "4");
        resultVal.put("1524462981838", "4");
        resultVal.put("1524462981938", "4");
        testSQL(resultVal, testFDLFile);
    }



    @Test
    public void maxContinuousCountTest() throws VDLSchemaException, FeatureDefinitionException, IOException, GeneratorException, AnalysisException {
        String testFDLFile = "udwaf/maxContinuousCount.json";
        Map<String, String> resultVal = new HashMap<>();
        resultVal.put("1524462981038", "0");
        resultVal.put("1524462981138", "1");
        resultVal.put("1524462981238", "2");
        resultVal.put("1524462981338", "3");
        resultVal.put("1524462981438", "4");
        resultVal.put("1524462981538", "5");
        resultVal.put("1524462981638", "6");
        resultVal.put("1524462981738", "7");
        resultVal.put("1524462981838", "8");
        resultVal.put("1524462981938", "9");
        testSQL(resultVal, testFDLFile);
    }



    @Test
    public void maxIncreaseCountTest() throws VDLSchemaException, FeatureDefinitionException, IOException, GeneratorException, AnalysisException {
        String testFDLFile = "udwaf/maxIncreaseCount.json";
        Map<String, String> resultVal = new HashMap<>();
        resultVal.put("1524462981038", "0");
        resultVal.put("1524462981138", "0");
        resultVal.put("1524462981238", "0");
        resultVal.put("1524462981338", "1");
        resultVal.put("1524462981438", "1");
        resultVal.put("1524462981538", "1");
        resultVal.put("1524462981638", "2");
        resultVal.put("1524462981738", "3");
        resultVal.put("1524462981838", "3");
        resultVal.put("1524462981938", "3");
        testSQL(resultVal, testFDLFile);
    }


    @Test
    public void occupiedTest() throws VDLSchemaException, FeatureDefinitionException, IOException, GeneratorException, AnalysisException {
        String testFDLFile = "udwaf/occupied.json";
        Map<String, String> resultVal = new HashMap<>();
        resultVal.put("1524462981038", "0");
        resultVal.put("1524462981138", "a");
        resultVal.put("1524462981238", "a");
        resultVal.put("1524462981338", "a");
        resultVal.put("1524462981438", "a");
        resultVal.put("1524462981538", "a");
        resultVal.put("1524462981638", "a");
        resultVal.put("1524462981738", "a");
        resultVal.put("1524462981838", "a");
        resultVal.put("1524462981938", "a");
        testSQL(resultVal, testFDLFile);
    }


    @Test
    public void replacedTest() throws VDLSchemaException, FeatureDefinitionException, IOException, GeneratorException, AnalysisException {
        String testFDLFile = "udwaf/replaced.json";
        Map<String, String> resultVal = new HashMap<>();
        resultVal.put("1524462981038", "0");
        resultVal.put("1524462981138", "a");
        resultVal.put("1524462981238", "b");
        resultVal.put("1524462981338", "c");
        resultVal.put("1524462981438", "a");
        resultVal.put("1524462981538", "c");
        resultVal.put("1524462981638", "e");
        resultVal.put("1524462981738", "e");
        resultVal.put("1524462981838", "a");
        resultVal.put("1524462981938", "c");
        testSQL(resultVal, testFDLFile);
    }


    @Test
    public void varpTest() throws VDLSchemaException, FeatureDefinitionException, IOException, GeneratorException, AnalysisException {
        String testFDLFile = "udwaf/varp.json";
        Map<String, String> resultVal = new HashMap<>();
        resultVal.put("1524462981038", "0.0");
        resultVal.put("1524462981138", "0.0");
        resultVal.put("1524462981238", "0.0");
        resultVal.put("1524462981338", "0.888888888888889");
        resultVal.put("1524462981438", "0.75");
        resultVal.put("1524462981538", "2.5599999999999996");
        resultVal.put("1524462981638", "4.138888888888888");
        resultVal.put("1524462981738", "172.6938775510204");
        resultVal.put("1524462981838", "203.35937499999997");
        resultVal.put("1524462981938", "188.98765432098764");
        testSQL(resultVal, testFDLFile);
    }

    @Test
    public void varsTest() throws VDLSchemaException, FeatureDefinitionException, IOException, GeneratorException, AnalysisException {
        String testFDLFile = "udwaf/vars.json";
        Map<String, String> resultVal = new HashMap<>();
        resultVal.put("1524462981038", "0.0");
        resultVal.put("1524462981138", "0.0");
        resultVal.put("1524462981238", "0.0");
        resultVal.put("1524462981338", "0.9428090415820634");
        resultVal.put("1524462981438", "0.8660254037844386");
        resultVal.put("1524462981538", "1.5999999999999999");
        resultVal.put("1524462981638", "2.034425935955617");
        resultVal.put("1524462981738", "13.141304256085862");
        resultVal.put("1524462981838", "14.260412862186003");
        resultVal.put("1524462981938", "13.74727806953026");
        testSQL(resultVal, testFDLFile);
    }

    @Test
    public void maxDecreaseCountTest() throws VDLSchemaException, FeatureDefinitionException, IOException, GeneratorException, AnalysisException {
        String testFDLFile = "udwaf/maxDecreaseCount.json";
        Map<String, String> resultVal = new HashMap<>();
        resultVal.put("1524462981238", "0");
        resultVal.put("1524462981338", "0");
        resultVal.put("1524462981538", "1");
        resultVal.put("1524462981738", "1");
        resultVal.put("1524462981838", "1");
        resultVal.put("1524462981038", "0");
        resultVal.put("1524462981138", "0");
        resultVal.put("1524462981438", "0");
        resultVal.put("1524462981638", "0");
        resultVal.put("1524462981938", "0");
        testSQL(resultVal, testFDLFile);
    }

    @Test
    public void decreaseCountTest() throws VDLSchemaException, FeatureDefinitionException, IOException, GeneratorException, AnalysisException {
        String testFDLFile = "udwaf/decreaseCount.json";
        Map<String, String> resultVal = new HashMap<>();
        resultVal.put("1524462981238", "0");
        resultVal.put("1524462981338", "0");
        resultVal.put("1524462981538", "1");
        resultVal.put("1524462981738", "1");
        resultVal.put("1524462981838", "1");
        resultVal.put("1524462981038", "0");
        resultVal.put("1524462981138", "0");
        resultVal.put("1524462981438", "0");
        resultVal.put("1524462981638", "0");
        resultVal.put("1524462981938", "0");
        testSQL(resultVal, testFDLFile);
    }

}