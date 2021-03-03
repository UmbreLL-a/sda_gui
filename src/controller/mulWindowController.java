package controller;

/**
 * @program: sda_gui
 * @description:
 * @author: LYT
 * @create: 2021-03-03 16:50
 **/

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pojo.CppSimModule;
import utils.FileObjectConvert;

import java.io.File;
import java.net.URL;
import java.util.*;

public class mulWindowController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="order"
    private TextField order; // Value injected by FXMLLoader

    @FXML // fx:id="mut_val"
    private TextField mut_val; // Value injected by FXMLLoader

    @FXML // fx:id="save"
    private Button save; // Value injected by FXMLLoader

    @FXML // fx:id="cancel"
    private Button cancel; // Value injected by FXMLLoader

    @FXML
    void cancel(ActionEvent event) {
        Stage stage = (Stage) cancel.getScene().getWindow();
        stage.close();
    }

    @FXML
    void save(ActionEvent event) {
        Map<String,CppSimModule> modules = (Map<String, CppSimModule>) FileObjectConvert.file2Object(new File("resources/CppSimModules.txt"));
        CppSimModule mul = modules.get("mul");
        Map<String,String> params = new HashMap<>();
        if(mut_val.getText().equals("")){
            System.out.println("请输入mut_val：如10");
            return;
        }
        params.put("mut_val", mut_val.getText());
        if(order.getText().equals("")){
            System.out.println("请输入次序：例如1，2，3...");
            return;
        }
        mul.setOrder(Integer.parseInt(order.getText()));
        mul.setParam(params);
        List<CppSimModule> outputList = (List<CppSimModule>) FileObjectConvert.file2Object(new File("resources/OutputList.txt"));
        if(outputList==null){
            outputList = new ArrayList<>();
        }
        outputList.add(mul);
        FileObjectConvert.object2File(outputList,new File("resources/OutputList.txt"));
        System.out.println("成功添加MUL模块实例，参数为：");
        Set<String> set = params.keySet();
        System.out.println("order = "+mul.getOrder());
        for(String s:set){
            System.out.println(s+" = "+params.get(s));
        }
        //获取当前按钮的stage并关闭
        Stage stage = (Stage) save.getScene().getWindow();
        stage.close();
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert order != null : "fx:id=\"order\" was not injected: check your FXML file 'mulWindow.fxml'.";
        assert mut_val != null : "fx:id=\"mut_val\" was not injected: check your FXML file 'mulWindow.fxml'.";
        assert save != null : "fx:id=\"save\" was not injected: check your FXML file 'mulWindow.fxml'.";
        assert cancel != null : "fx:id=\"cancel\" was not injected: check your FXML file 'mulWindow.fxml'.";

    }
}

