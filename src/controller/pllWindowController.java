/**
 * Sample Skeleton for 'pllWindow.fxml' Controller Class
 */

package controller;

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

public class pllWindowController {


    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="div_val"
    private TextField div_val; // Value injected by FXMLLoader

    @FXML // fx:id="fp"
    private TextField fp; // Value injected by FXMLLoader

    @FXML // fx:id="fc"
    private TextField fc; // Value injected by FXMLLoader

    @FXML // fx:id="kv"
    private TextField kv; // Value injected by FXMLLoader

    @FXML // fx:id="fz"
    private TextField fz; // Value injected by FXMLLoader

    @FXML // fx:id="gain"
    private TextField gain; // Value injected by FXMLLoader

    @FXML // fx:id="save"
    private Button save; // Value injected by FXMLLoader

    @FXML // fx:id="cancel"
    private Button cancel; // Value injected by FXMLLoader

    @FXML // fx:id="order"
    private TextField order; // Value injected by FXMLLoader

    @FXML
    void cancel(ActionEvent event) {
        Stage stage = (Stage) cancel.getScene().getWindow();
        stage.close();
    }

    @FXML
    void save(ActionEvent event) {
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName());
        Map<String,CppSimModule> modules = (Map<String, CppSimModule>) FileObjectConvert.file2Object(new File("resources/CppSimModules.txt"));
        CppSimModule pll = modules.get("pll_v1");
        Map<String,String> params = new HashMap<>();
        if(div_val.getText().equals("")){
            System.out.println("请输入div_val：如100");
            return;
        }
        params.put("div_val", div_val.getText());
        if(fp.getText().equals("")){
            System.out.println("请输入fp：例如1e9");
            return;
        }
        params.put("fp", fp.getText());
        if(fc.getText().equals("")){
            System.out.println("请输入fc：例如1e9");
            return;
        }
        params.put("fc",fc.getText());
        if(kv.getText().equals("")){
            System.out.println("请输入kv：例如30e6");
            return;
        }
        params.put("kv",kv.getText());
        if(fz.getText().equals("")){
            System.out.println("请输入fz：例如1e9");
            return;
        }
        params.put("fz",fz.getText());
        if(gain.getText().equals("")){
            System.out.println("请输入gain");
            return;
        }
        params.put("gain",gain.getText());
        if(order.getText().equals("")){
            System.out.println("请输入次序：例如1，2，3...");
            return;
        }
        pll.setOrder(Integer.parseInt(order.getText()));
        pll.setParam(params);
        List<CppSimModule> outputList = (List<CppSimModule>) FileObjectConvert.file2Object(new File("resources/OutputList.txt"));
        if(outputList==null){
            outputList = new ArrayList<>();
        }
        outputList.add(pll);
        FileObjectConvert.object2File(outputList,new File("resources/OutputList.txt"));
        System.out.println("成功添加PLL模块实例，参数为：");
        Set<String> set = params.keySet();
        System.out.println(pll.getOrder());
        for(String s:set){
            System.out.println(s+" = "+params.get(s));
        }
        //获取当前按钮的stage并关闭
        Stage stage = (Stage) save.getScene().getWindow();
        stage.close();
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert fp != null : "fx:id=\"fp\" was not injected: check your FXML file 'pllWindow.fxml'.";
        assert fc != null : "fx:id=\"fc\" was not injected: check your FXML file 'pllWindow.fxml'.";
        assert kv != null : "fx:id=\"kv\" was not injected: check your FXML file 'pllWindow.fxml'.";
        assert fz != null : "fx:id=\"fz\" was not injected: check your FXML file 'pllWindow.fxml'.";
        assert gain != null : "fx:id=\"gain\" was not injected: check your FXML file 'pllWindow.fxml'.";
        assert save != null : "fx:id=\"save\" was not injected: check your FXML file 'pllWindow.fxml'.";
        assert cancel != null : "fx:id=\"cancel\" was not injected: check your FXML file 'pllWindow.fxml'.";
    }


}
