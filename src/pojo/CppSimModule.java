package pojo;

import java.io.Serializable;
import java.util.Map;

/**
 * @program: software_define_app_v1
 * @description: 用来封装cppsim元件的属性值
 * @author: LYT
 * @create: 2021-01-11 21:04
 **/

public class CppSimModule implements Serializable {
    private String name;//模块的名称

    private Map<String,Coordinate> input;

    private Map<String,Coordinate> output;//模块的输出引脚和其对应的偏置位置

    private Map<String,String> param;//模块中的参数

    private int order;

    public CppSimModule(){}

    public CppSimModule(String name, Map<String, Coordinate> input, Map<String, Coordinate> output, Map<String, String> param) {
        this.name = name;
        this.input = input;
        this.output = output;
        this.param = param;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Coordinate> getInput() {
        return input;
    }

    public void setInput(Map<String, Coordinate> input) {
        this.input = input;
    }

    public Map<String, Coordinate> getOutput() {
        return output;
    }

    public void setOutput(Map<String, Coordinate> output) {
        this.output = output;
    }

    public Map<String, String> getParam() {
        return param;
    }

    public void setParam(Map<String, String> param) {
        this.param = param;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}
