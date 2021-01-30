package controller;
/**
 * Sample Skeleton for 'ddsWindow.fxml' Controller Class
 */

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

public class ddsWindowController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="order"
    private TextField order; // Value injected by FXMLLoader

    @FXML // fx:id="n"
    private TextField n; // Value injected by FXMLLoader

    @FXML // fx:id="ftw"
    private TextField ftw; // Value injected by FXMLLoader

    @FXML // fx:id="ptd"
    private TextField ptd; // Value injected by FXMLLoader

    @FXML // fx:id="symbol_period"
    private TextField symbol_period; // Value injected by FXMLLoader

    @FXML // fx:id="num_filt_taps"
    private TextField num_filt_taps; // Value injected by FXMLLoader

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
        CppSimModule dds = modules.get("dds_v2");
        Map<String,String> params = new HashMap<>();
        if(n.getText().equals("")){
            System.out.println("请输入n：如32");
            return;
        }
        params.put("n", n.getText());
        if(ftw.getText().equals("")){
            System.out.println("请输入ftw：例如1");
            return;
        }
        params.put("ftw", ftw.getText());
        if(ptd.getText().equals("")){
            System.out.println("请输入ptd：例如10");
            return;
        }
        params.put("ptd",ptd.getText());
        if(symbol_period.getText().equals("")){
            System.out.println("请输入symbol_period：例如20e6");
            return;
        }
        params.put("symbol_period",symbol_period.getText());
        if(num_filt_taps.getText().equals("")){
            System.out.println("请输入num_filt_taps：例如1e9");
            return;
        }
        params.put("num_filt_taps",num_filt_taps.getText());
        if(order.getText().equals("")){
            System.out.println("请输入次序：例如1，2，3...");
            return;
        }
        dds.setOrder(Integer.parseInt(order.getText()));
        dds.setParam(params);
        List<CppSimModule> outputList = (List<CppSimModule>) FileObjectConvert.file2Object(new File("resources/OutputList.txt"));
        if(outputList==null){
            outputList = new ArrayList<>();
        }
        outputList.add(dds);
        FileObjectConvert.object2File(outputList,new File("resources/OutputList.txt"));
        System.out.println("成功添加DDS模块实例，参数为：");
        Set<String> set = params.keySet();
        System.out.println("order = "+dds.getOrder());
        for(String s:set){
            System.out.println(s+" = "+params.get(s));
        }
        //获取当前按钮的stage并关闭
        Stage stage = (Stage) save.getScene().getWindow();
        stage.close();
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert order != null : "fx:id=\"order\" was not injected: check your FXML file 'ddsWindow.fxml'.";
        assert n != null : "fx:id=\"n\" was not injected: check your FXML file 'ddsWindow.fxml'.";
        assert ftw != null : "fx:id=\"ftw\" was not injected: check your FXML file 'ddsWindow.fxml'.";
        assert ptd != null : "fx:id=\"ptd\" was not injected: check your FXML file 'ddsWindow.fxml'.";
        assert symbol_period != null : "fx:id=\"symbol_period\" was not injected: check your FXML file 'ddsWindow.fxml'.";
        assert num_filt_taps != null : "fx:id=\"num_filt_taps\" was not injected: check your FXML file 'ddsWindow.fxml'.";
        assert save != null : "fx:id=\"save\" was not injected: check your FXML file 'ddsWindow.fxml'.";
        assert cancel != null : "fx:id=\"cancel\" was not injected: check your FXML file 'ddsWindow.fxml'.";

    }
}
