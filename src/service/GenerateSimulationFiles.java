package service;

import pojo.CppSimModule;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * @program: software_define_app_v1
 * @description: 生成仿真文件
 * @author: LYT
 * @create: 2021-01-13 22:28
 **/

public class GenerateSimulationFiles {
    String cppSimHome = System.getenv("CppSimHome");

    public void generateTestParFile(String libraryName, String cellName, int num, List<CppSimModule> cppSimModules,String numSimSteps,double Ts) throws IOException {
        File cell = new File(cppSimHome+"\\SimRuns\\"+libraryName+"\\"+cellName+num+"\\test.par");
        if(!cell.exists()){
            cell.getParentFile().mkdirs();
            cell.createNewFile();
        }
        FileWriter fileWriter = new FileWriter(cell);
        String line;
        line = "num_sim_steps: "+numSimSteps+"\n";
        fileWriter.write(line);
        line = "Ts: "+Ts+"\n";
        fileWriter.write(line);
        line = "output: test\n";
        fileWriter.write(line);
        CppSimModule last = cppSimModules.get(cppSimModules.size()-1);
        if(last.getOutput().size()==1){
            line = "probe: sine_out\n";
        }else{
            line = "probe: sine_out square_out";
        }
        fileWriter.write(line);
        fileWriter.close();
    }
}
