import com.mathworks.toolbox.javabuilder.MWException;
import cppsim.CppSimRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: software_define_app_v1
 * @description: 仿真文件
 * @author: LYT
 * @create: 2021-01-15 21:41
 **/

public class SimulationFile {
    public static void main(String[] args) {
        CppSimRunner runner = null;
        try {
            runner = new CppSimRunner();
            List<Object> lhs = new ArrayList<>();
            List<String> rhs = new ArrayList<>();
            String simFile = "test.par";
            String classpath = "C:\\CppSim\\SimRuns\\test\\dds_v2_test_copy";
            rhs.add(simFile);
            rhs.add(classpath);
            runner.cppsim(lhs,rhs );
        } catch (MWException e) {
            e.printStackTrace();
        } finally {
            runner.dispose();
        }
    }
}
