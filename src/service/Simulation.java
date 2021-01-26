package service;

import java.io.*;
import java.util.LinkedList;

/**
 * @program: software_define_app_v1
 * @description: 调用matlab函数进行仿真
 * @author: LYT
 * @create: 2021-01-15 21:40
 **/

public class Simulation {
    //要求之前代码已经把目录层次建立好
    public void runSimulations(String libraryPath) throws Exception {
        File library = new File(libraryPath);
        if(!library.isDirectory()){
            System.out.println("library的路径参数有误");
            return;
        }
        File[] cells = library.listFiles();
        int libraryNums = 0;
        int num = cells.length;
        for(int i=0;i<num;i++){
            if(cells[i].isDirectory())
                libraryNums++;
        }
        for(int i=0;i<num;i++){
            if(!cells[i].isDirectory())
                continue;
            System.out.println("======================The "+i+" of "+libraryNums+"th simulations is running======================");
            if(!runSimulation(cells[i]))
                break;
        }
    }
    public boolean runSimulation(File cell) throws Exception {
        modifyAndMoveSimFile(cell);
        if(!invokeCmd(cell))
            return false;
        return true;
    }
    public void modifyAndMoveSimFile(File cell) throws Exception {
        File simulationFile = new File("resources/SimulationFile.java");
        //建立字符输出和输入流
        FileReader fileReader = new FileReader(simulationFile);
        FileWriter fileWriter = new FileWriter(cell.getAbsolutePath()+"\\SimulationFile.java");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        while((line = bufferedReader.readLine())!=null){
            if(!line.contains("String") || !line.contains("classpath")){
                fileWriter.write(line+"\n");
            }else{
                String[] split = line.split("[=]");
                split[1] = "\""+cell.getAbsolutePath().replace("\\","\\\\")+"\";";
                String newStr = split[0] + "=" + split[1];
                fileWriter.write(newStr+"\n");
            }
        }
        fileReader.close();
        fileWriter.close();
    }

    public boolean invokeCmd(File cell) throws IOException, InterruptedException {
        String CppSim = new File("resources/cppsim.jar").getAbsolutePath();
        String javaBuilder = new File("resources/javabuilder.jar").getAbsolutePath();
        String cmd = "cd "+cell.getAbsolutePath()+" & javac SimulationFile.java & java -classpath .;"+CppSim+";"+javaBuilder+" SimulationFile";
        Process process = Runtime.getRuntime().exec("cmd /c" + cmd);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line = null;
        LinkedList<String> queue = new LinkedList<>();
        while((line=bufferedReader.readLine())!=null){
            queue.add(line);
        }
        bufferedReader.close();
        if(!queue.getLast().contains("CppSim run completed!")){
            for(String s:queue){
                System.out.println(s);
            }
            return false;
        }
        if(process.waitFor()==1){
            System.out.println("************** ERROR:  exited CppSim run prematurely! ****************");
            return false;
        }
        System.out.println("======================Simulation of module "+cell.getName()+" has accomplished======================");
        return true;
    }
}
