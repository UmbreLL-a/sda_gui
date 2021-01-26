package service;

import java.io.File;
import java.io.IOException;
import java.util.Locale;

/**
 * @program: software_define_app_v1
 * @description: 对生成的网表文件进行仿真
 * @author: LYT
 * @create: 2021-01-13 22:26
 **/

public class Simulation_old {
    public void CppSim(String path) throws IOException {
        //获取环境变量的地址并处理
        String CppSimHome = System.getenv("CppSimHome");
        CppSimHome = CppSimHome.replaceAll("\\\\", "/");
        CppSimHome = CppSimHome.replaceAll("C:", "c:");
        CppSimHome = CppSimHome.replaceAll(" ", "");
        String CppSimSharedHome = System.getenv("CppSimSharedHome");
        CppSimSharedHome = CppSimSharedHome.replaceAll("\\\\", "/");
        CppSimSharedHome = CppSimSharedHome.replaceAll("C:", "c:");
        CppSimSharedHome = CppSimSharedHome.replaceAll(" ", "");
        //对仿真目录进行检查
        File file = new File(path);
        int i = path.toLowerCase(Locale.ROOT).indexOf("\\simruns\\");
        String library = path.substring(i+9);
        if(library==""){
            System.out.println("Error:  you need to run the cppsim.m script in a directory");
            System.out.println("    of form '' ...../SimRuns/Library_name/Cell_name''");
            System.out.println("    -> in this case, you ran in directory:");
            System.out.println(path);
            return;
        }
        String[] split = library.split("\\\\");
        if(split[1]==""){
            System.out.println("Error:  you need to run the cppsim.m script in a directory");
            System.out.println("    of form '' ...../SimRuns/Library_name/Cell_name''");
            System.out.println("    -> in this case, you ran in directory:");
            System.out.println(path);
            return;
        }
        //仿真
        String fileName = split[1];
        String simFile = "test.par";
        System.out.println("... netlisting ...");
        String runProgram = String.format("%s/Sue2/bin/win32/sue_cppsim_netlister %s %s/Sue2/sue.lib %s/Netlist/netlist.cppsim",CppSimSharedHome,fileName,CppSimHome,CppSimHome);
        Process status = Runtime.getRuntime().exec(runProgram);
        if(!status.isAlive()){
            System.out.println("************** ERROR:  exited CppSim run prematurely! ****************");
        }
        System.out.println("... running net2code ...");
        runProgram = String.format("%s/bin/win32/net2code -cpp %s", CppSimSharedHome,simFile);
        status = Runtime.getRuntime().exec(runProgram);
        if(!status.isAlive()){
            System.out.println("************** ERROR:  exited CppSim run prematurely! ****************");
        }
        System.out.println("... compiling ...");
        runProgram = String.format("%s/Msys/bin/make",CppSimSharedHome);
        status = Runtime.getRuntime().exec(runProgram);
        if(!status.isAlive()){
            System.out.println("************** ERROR:  exited CppSim run prematurely! ****************");
        }
        System.out.println(CppSimHome);
        System.out.println(CppSimSharedHome);
    }

}
