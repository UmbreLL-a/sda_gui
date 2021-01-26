package service;

import pojo.Coordinate;
import pojo.CppSimModule;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: software_define_app_v1
 * @description: 根据选择文件夹导入CPPSIM仿真模型
 * @author: LYT
 * @create: 2021-01-11 21:54
 **/

public class ImportCppSimModules {
    Map<String,CppSimModule> result = new HashMap<>();

    public Map<String,CppSimModule> getCppSimModules(String path) throws Exception {
        getModules(path);
        return result;
    }
    //根据路径添加CppSimModule
    public void getModules(String path) throws Exception {
        File file = new File(path);
        File[] fileList = file.listFiles();
        for(File f:fileList){
            if(f.isDirectory()){
                getModules(f.getPath());
            }else{
                String name = f.getName();
                if(name.substring(name.length()-3).equals("sue")){
                    result.put(getModule(f).getName(),getModule(f));
                }
            }
        }
    }
    //返回CppSimModule对象
    public CppSimModule getModule(File file) throws Exception {
        CppSimModule module = new CppSimModule();
        module.setParam(getParams(file));
        Map<String, Coordinate>[] inputsAndOutputs = getInputsAndOutputs(file);
        module.setInput(inputsAndOutputs[0]);
        module.setOutput(inputsAndOutputs[1]);
        String name = file.getName();
        module.setName(name.substring(0,name.length()-4));
        return module;
    }
    //返回文件中param的数据
    public Map<String,String> getParams(File file) throws Exception {
        Map<String,String> map = new HashMap<>();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String line;
        if(!bufferedReader.ready()){
            throw new Exception("该文件暂时无法被读取！");
        }
        //按行读取文件内容
        while((line = bufferedReader.readLine())!=null){
            if(line.contains("icon_setup")){
                //找到记录params信息的一行，把该行分割并去掉分割后的String数组的“”
                String[] sp = line.split("[^a-zA-Z0-9\\./_\\-\\+\\(\\)]");
                List<String> list = new ArrayList<>();
                boolean flag = false;
                for(String s:sp){
                    if(s.equals("name"))
                        flag = !flag;
                    if(flag && !s.equals(""))
                        list.add(s);
                }
                String[] params = list.toArray(new String[0]);
                //提取参数到Map中
                for(int i=0;i<params.length;i+=2){
                    if(i<params.length)
                        map.put(params[i],params[i+1]);
                }
                return map;
            }
        }
        return map;
    }
    //返回文件中的input和output的数据
    public Map<String, Coordinate>[] getInputsAndOutputs(File file) throws Exception {
        Map<String,Coordinate> inputs = new HashMap<>();
        Map<String,Coordinate> outputs = new HashMap<>();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String line;
        if(!bufferedReader.ready()){
            throw new Exception("该文件暂时无法被读取！");
        }
        while((line = bufferedReader.readLine())!=null){
            if(line.contains("icon_term")){
                String[] split = line.split("[^a-zA-Z0-9\\.\\-_/]");
                List<String> list = new ArrayList<>();
                boolean flag = false;
                for(String s:split){
                    if(s.contains("type"))
                        flag = !flag;
                    if(flag && !s.equals(""))
                        list.add(s);
                }
                String[] ports = list.toArray(new String[0]);
                if(ports[1].equals("input")){
                    inputs.put(ports[6],new Coordinate(Integer.parseInt(ports[3]),Integer.parseInt(ports[4])));
                }else if(ports[1].equals("output")){
                    outputs.put(ports[6],new Coordinate(Integer.parseInt(ports[3]),Integer.parseInt(ports[4])));
                }
            }
        }
        return new Map[]{inputs, outputs};
    }
}
