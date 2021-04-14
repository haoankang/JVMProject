//package vdl;
//
//import cn.com.bsfit.vdl.VdlToScScript;
//import cn.com.bsfit.vdl.VdlToScript;
//import cn.com.bsfit.vdl.core.dto.VDLDto;
//import cn.com.bsfit.vdl.exp.FeatureDefinitionException;
//import cn.com.bsfit.vdl.exp.GeneratorException;
//import cn.com.bsfit.vdl.exp.VDLSchemaException;
//import com.alibaba.fastjson.JSON;
//import org.apache.spark.sql.AnalysisException;
//import org.junit.Test;
//
//import java.io.FileReader;
//import java.io.IOException;
//import java.net.URL;
//
//public class VdlToScScriptTest {
//
//    private String vdlGenerator(String testFDLFile) throws IOException, FeatureDefinitionException, VDLSchemaException, GeneratorException {
//        URL url = getClass().getClassLoader().getResource(testFDLFile);
//        FileReader reader = new FileReader(url.getFile());
//        StringBuilder resultString = new StringBuilder();
//        char[] chars = new char[1024];
//        int m;
//        while ((m = reader.read(chars)) != -1) {
//            resultString.append(chars, 0, m);
//        }
//        String json = resultString.toString();
//        VDLDto dto = JSON.parseObject(json, VDLDto.class);
//        VdlToScript vdl = new VdlToScScript(dto);
//        String script = vdl.getScript();
//        System.out.println(script);
//        return script;
//    }
//
//
//    @Test
//    public void avgTest() throws VDLSchemaException, FeatureDefinitionException, IOException, GeneratorException, AnalysisException {
//        String testFDLFile = "udwaf/avg.json";
//
//        vdlGenerator(testFDLFile);
//    }
//
//    @Test
//    public void collectTest() throws VDLSchemaException, FeatureDefinitionException, IOException, GeneratorException, AnalysisException {
//        String testFDLFile = "udwaf/collect.json";
//
//        vdlGenerator(testFDLFile);
//    }
//    @Test
//    public void sumTest() throws VDLSchemaException, FeatureDefinitionException, IOException, GeneratorException, AnalysisException {
//        String testFDLFile = "udwaf/sum.json";
//
//        vdlGenerator(testFDLFile);
//    }
//    @Test
//    public void countTest() throws VDLSchemaException, FeatureDefinitionException, IOException, GeneratorException, AnalysisException {
//        String testFDLFile = "udwaf/count.json";
//
//        vdlGenerator(testFDLFile);
//    }
//    @Test
//    public void distinctCountTest() throws VDLSchemaException, FeatureDefinitionException, IOException, GeneratorException, AnalysisException {
//        String testFDLFile = "udwaf/distinctCount.json";
//
//        vdlGenerator(testFDLFile);
//    }
//    @Test
//    public void distinctListTest() throws VDLSchemaException, FeatureDefinitionException, IOException, GeneratorException, AnalysisException {
//        String testFDLFile = "udwaf/distinctList.json";
//        vdlGenerator(testFDLFile);
//    }
//
//    @Test
//    public void maxTest() throws VDLSchemaException, FeatureDefinitionException, IOException, GeneratorException, AnalysisException {
//        String testFDLFile = "udwaf/max.json";
//
//        vdlGenerator(testFDLFile);
//    }
//    @Test
//    public void minTest() throws VDLSchemaException, FeatureDefinitionException, IOException, GeneratorException, AnalysisException {
//        String testFDLFile = "udwaf/min.json";
//        vdlGenerator(testFDLFile);
//    }
//
//
//    @Test
//    public void sortedListTest() throws VDLSchemaException, FeatureDefinitionException, IOException, GeneratorException, AnalysisException {
//        String testFDLFile = "udwaf/sortedList.json";
//        vdlGenerator(testFDLFile);
//    }
//
//    @Test
//    public void cenmon3Test() throws VDLSchemaException, FeatureDefinitionException, IOException, GeneratorException, AnalysisException {
//        String testFDLFile = "udwaf/cenmon3.json";
//        vdlGenerator(testFDLFile);
//    }
//
//    @Test
//    public void cenmon4Test() throws VDLSchemaException, FeatureDefinitionException, IOException, GeneratorException, AnalysisException {
//        String testFDLFile = "udwaf/cenmon4.json";
//        vdlGenerator(testFDLFile);
//    }
//
//
//    @Test
//    public void increaseCountTest() throws VDLSchemaException, FeatureDefinitionException, IOException, GeneratorException, AnalysisException {
//        String testFDLFile = "udwaf/increaseCount.json";
//        vdlGenerator(testFDLFile);
//    }
//
//
//
//    @Test
//    public void maxContinuousCountTest() throws VDLSchemaException, FeatureDefinitionException, IOException, GeneratorException, AnalysisException {
//        String testFDLFile = "udwaf/maxContinuousCount.json";
//        vdlGenerator(testFDLFile);
//    }
//
//    @Test
//    public void maxIncreaseCountTest() throws VDLSchemaException, FeatureDefinitionException, IOException, GeneratorException, AnalysisException {
//        String testFDLFile = "udwaf/maxIncreaseCount.json";
//        vdlGenerator(testFDLFile);
//    }
//
//    @Test
//    public void occupiedTest() throws VDLSchemaException, FeatureDefinitionException, IOException, GeneratorException, AnalysisException {
//        String testFDLFile = "udwaf/occupied.json";
//        vdlGenerator(testFDLFile);
//    }
//
//    @Test
//    public void replacedTest() throws VDLSchemaException, FeatureDefinitionException, IOException, GeneratorException, AnalysisException {
//        String testFDLFile = "udwaf/replaced.json";
//
//        vdlGenerator(testFDLFile);
//    }
//
//
//    @Test
//    public void varpTest() throws VDLSchemaException, FeatureDefinitionException, IOException, GeneratorException, AnalysisException {
//        String testFDLFile = "udwaf/varp.json";
//        vdlGenerator(testFDLFile);
//    }
//
//    @Test
//    public void varsTest() throws VDLSchemaException, FeatureDefinitionException, IOException, GeneratorException, AnalysisException {
//        String testFDLFile = "udwaf/vars.json";
//        vdlGenerator(testFDLFile);
//    }
//
//    @Test
//    public void maxDecreaseCountTest() throws VDLSchemaException, FeatureDefinitionException, IOException, GeneratorException, AnalysisException {
//        String testFDLFile = "udwaf/maxDecreaseCount.json";
//        vdlGenerator(testFDLFile);
//    }
//
//    @Test
//    public void decreaseCountTest() throws VDLSchemaException, FeatureDefinitionException, IOException, GeneratorException, AnalysisException {
//        String testFDLFile = "udwaf/decreaseCount.json";
//        vdlGenerator(testFDLFile);
//    }
//
//
//
//    @Test
//    public void mergeableMapObjectTest() throws VDLSchemaException, FeatureDefinitionException, IOException, GeneratorException, AnalysisException {
//        String testFDLFile = "udwaf/mergeableMapObject.json";
//        vdlGenerator(testFDLFile);
//    }
//
//
//}
