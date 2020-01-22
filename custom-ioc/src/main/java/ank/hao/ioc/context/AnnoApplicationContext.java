package ank.hao.ioc.context;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class AnnoApplicationContext implements BeanFactory {

    private final static Map<String, Class> context = new HashMap<>();

    public AnnoApplicationContext(Class c){
        String ss = c.getPackage().getName();
        System.out.println(ss);

        File file = new File(c.getResource("/").getPath());
        System.out.println(file.getAbsolutePath());

        String basePackage = file.getAbsolutePath()+"\\"+ss.replace(".","\\");
        System.out.println(basePackage);

        File file1 = new File(basePackage);
        handler(file1, ss);
        //File rootFile = new File(Scanner.class.getResource())
    }

    @Override
    public Object getBean(String name) {
        try {
            return context.get(name).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void putBean(String name, Class c) {
        context.put(name, c);
    }

    private void handler(File file, String basePackage){

        File[] files = file.listFiles( (File dir, String name) -> {
            return name.endsWith(".class");
        } );

//        System.out.println(files.length);

        for(File file1: files){
            try {
//                System.out.println(file1.getName());
                //Class cc = classLoader.loadClass(file1.getName());
                Class c = Class.forName(basePackage + "." + file1.getName().replace(".class",""));
//                Annotation[] aa = c.getDeclaredAnnotations();
//                //Annotation[] bb = c.getDeclaredAnnotations();
//                for(Annotation annotation:aa){
//                    if(annotation.annotationType() == MyBean.class){
//                        System.out.println(annotation.toString());;
//                    }
//                }
                MyBean myBean = (MyBean) c.getAnnotation(MyBean.class);
                if(myBean!=null){
                    context.put(myBean.name(),c);
                }
                System.out.println("dsg");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
