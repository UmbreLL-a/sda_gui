package controller;

/**
 * @program: sda_gui
 * @description:
 * @author: LYT
 * @create: 2021-01-27 15:17
 **/

/**
 * Sample Skeleton for 'globalParamWindow.fxml' Controller Class
 */


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.Enumeration;
import java.util.Properties;
import java.util.ResourceBundle;

public class globalParamWindow {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="Ts"
    private TextField Ts; // Value injected by FXMLLoader

    @FXML // fx:id="num_filt_taps"
    private TextField num_filt_taps; // Value injected by FXMLLoader

    @FXML // fx:id="save"
    private Button save; // Value injected by FXMLLoader

    @FXML // fx:id="cancel"
    private Button cancel; // Value injected by FXMLLoader

    @FXML
    void cancel(ActionEvent event) {
        Stage stage = (Stage)cancel.getScene().getWindow();
        stage.close();
    }

    @FXML
    void save(ActionEvent event) {
        try {
            Properties properties = new Properties();
            OutputStream outputStream = new FileOutputStream(new File("resources/globalParams.properties"));
            if(Ts.getText().equals("")){
                System.out.println("请输入Ts：如1/20e6");
                return;
            }
            properties.setProperty("Ts", Ts.getText());
            if(num_filt_taps.getText().equals("")){
                System.out.println("请输入num_filt_taps：如1e7");
                return;
            }
            properties.setProperty("num_filt_taps", num_filt_taps.getText());
            properties.store(outputStream, "yes");
            InputStream inputStream = new FileInputStream(new File("resources/globalParams.properties"));
            properties.load(inputStream);
            Enumeration<?> enumeration = properties.propertyNames();
            System.out.println("成功修改全局变量为：");
            while (enumeration.hasMoreElements()){
                String key = (String)enumeration.nextElement();
                String value = properties.getProperty(key);
                System.out.println(key+"="+value);
            }
            //获取当前按钮的stage并关闭
            Stage stage = (Stage) save.getScene().getWindow();
            stage.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert Ts != null : "fx:id=\"Ts\" was not injected: check your FXML file 'globalParamWindow.fxml'.";
        assert num_filt_taps != null : "fx:id=\"num_filt_taps\" was not injected: check your FXML file 'globalParamWindow.fxml'.";
        assert save != null : "fx:id=\"save\" was not injected: check your FXML file 'globalParamWindow.fxml'.";
        assert cancel != null : "fx:id=\"cancel\" was not injected: check your FXML file 'globalParamWindow.fxml'.";

    }
}

