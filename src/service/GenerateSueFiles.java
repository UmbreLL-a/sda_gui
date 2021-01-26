package service;

import pojo.Coordinate;
import pojo.CppSimModule;

import java.io.*;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @program: software_define_app_v1
 * @description: 生成CPPSIM网表文件
 * @author: LYT
 * @create: 2021-01-13 22:26
 **/

public class GenerateSueFiles {

    String CppSimHome = System.getenv("CppSimHome");

    int distance = 80;

    public void GenerateSueFile(String libName, String cellName, int num, List<CppSimModule> cppSimModules) throws IOException {
        String sueName = cellName+num+".sue";

        String sueFilePath = CppSimHome+"\\SueLib\\"+libName+"\\"+sueName;
        File file = new File(sueFilePath);
        if(!file.exists()){
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
        FileWriter fileWriter = new FileWriter(file);
        String line;

        //先把constant和vco加入
        line = "proc "+"SCHEMATIC_Simulation"+num+" {} {\n";
        fileWriter.write(line);
        line = "make constant -name xi0 -origin {0 0}\n";
        fileWriter.write(line);
        line = "make vco -name xi1 -freq 20e6 -kvco 1 -origin {190 0}\n";
        fileWriter.write(line);
        line = "make_wire 40 0 120 0\n";
        fileWriter.write(line);
        //根据CppSimModules把剩余的表述补全
        Coordinate prevSineOut = new Coordinate(190+70, 20);
        Coordinate prevSquareOut = new Coordinate(190+70,-20);
        Coordinate sineOut = null;
        Coordinate squareOut = null;
        for(int i=0;i<cppSimModules.size();i++){
            CppSimModule module = cppSimModules.get(i);
            StringBuilder builder = new StringBuilder("make "+module.getName());
            Map<String, String> param = module.getParam();
            Set<String> set = param.keySet();
            //拼接模块的参数
            Iterator<String> iterator = set.iterator();
            while (iterator.hasNext()){
                String key = iterator.next();
                String value = param.get(key);
                if(key.equals("name")){
                    builder.append(" -name "+"xi"+(i+2));
                }else
                    builder.append(" -"+key+" "+value);
            }
            //取出input节点的参数
            Map<String, Coordinate> input = module.getInput();
            String inputName = null;
            for(String s:input.keySet()){
                inputName = s;
            }
            Coordinate inputCoordinate = input.get(inputName);
            //取出output节点的参数
            Map<String, Coordinate> output = module.getOutput();
            String squareOutName = null;
            String sineOutName = null;
            if(output.keySet().size()==1){
                for(String s: output.keySet()){
                    sineOutName = squareOutName = s;
                    sineOut = squareOut = output.get(squareOutName);
                }
            }else{
                for(String s:output.keySet()){
                    if(s.contains("square")||s.equals("out")){
                        squareOutName = s;
                        squareOut = output.get(squareOutName);
                    }
                    if(s.contains("sin")){
                        sineOutName = s;
                        sineOut = output.get(sineOutName);
                    }
                }
            }
            //计算出origin的参数
            int originX;
            int originY;
            if(output.keySet().size()==1 && !prevSquareOut.equals(prevSineOut) && i==cppSimModules.size()-1){
                originX = prevSineOut.getX()+distance-inputCoordinate.getX();
                originY = prevSineOut.getY();
            }else{
                originX = prevSquareOut.getX()+distance-inputCoordinate.getX();
                originY = prevSquareOut.getY();
            }
            builder.append(" -origin {"+originX+" "+originY+"}\n");
            fileWriter.write(builder.toString());
            //make wire
            if(output.keySet().size()==1 && !prevSquareOut.equals(prevSineOut) && i==cppSimModules.size()-1){
                line = "make_wire "+prevSineOut.getX()+" "+prevSineOut.getY()+" "+(prevSineOut.getX()+distance)+" "+prevSineOut.getY()+"\n";
                prevSineOut = new Coordinate(prevSineOut.getX()+distance-inputCoordinate.getX()+ sineOut.getX(),prevSineOut.getY()- inputCoordinate.getY()+ sineOut.getY());
                prevSquareOut = prevSineOut;
            }else{
                line = "make_wire "+prevSquareOut.getX()+" "+prevSquareOut.getY()+" "+(prevSquareOut.getX()+distance)+" "+prevSquareOut.getY()+"\n";
                prevSineOut = new Coordinate(prevSquareOut.getX()+distance-inputCoordinate.getX()+ sineOut.getX(),prevSquareOut.getY()- inputCoordinate.getY()+ sineOut.getY());
                prevSquareOut = new Coordinate(prevSquareOut.getX()+distance-inputCoordinate.getX()+ squareOut.getX(),prevSquareOut.getY()- inputCoordinate.getY()+ squareOut.getY());
            }
            fileWriter.write(line);
        }
        //加入output结点
        if(prevSineOut.equals(prevSquareOut)){
            line = "make output -name sine_out -origin {"+prevSineOut.getX()+" "+prevSineOut.getY()+"}\n";
            fileWriter.write(line);
        }else{
            line = "make output -name sine_out -origin {"+prevSineOut.getX()+" "+prevSineOut.getY()+"}\n";
            fileWriter.write(line);
            line = "make output -name square_out -origin {"+prevSquareOut.getX()+" "+prevSquareOut.getY()+"}\n";
            fileWriter.write(line);
        }
        //补上结尾
        fileWriter.write("}");
        fileWriter.close();
    }
    public void addLibNameToSueLib(String libName) throws IOException {
        String SueLibPath = CppSimHome+"\\Sue2\\sue.lib";
        BufferedReader bufferedReader = new BufferedReader(new FileReader(SueLibPath));
        String line;
        while((line = bufferedReader.readLine())!=null){
            if(line.contains(libName)){
                bufferedReader.close();
                return;
            }
        }
        FileWriter fileWriter = new FileWriter(SueLibPath, true);
        fileWriter.write("\n"+libName);
        fileWriter.close();
    }
}
