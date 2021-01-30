/**
 * Sample Skeleton for 'mainWindow.fxml' Controller Class
 */

package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import pojo.CppSimModule;
import pojo.DDS;
import pojo.Pll;
import service.ImportCppSimModules;
import utils.FileObjectConvert;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URL;
import java.util.*;

public class mainWindowController {

    Map<String, CppSimModule> cppSimModules = new HashMap<>();

    public PrintStream printStream;

    //tableview的数据容器
    private final ObservableList<Pll> pllData = FXCollections.observableArrayList();
    private final ObservableList<DDS> ddsData = FXCollections.observableArrayList();

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="selectDirectory"
    private Button selectDirectory; // Value injected by FXMLLoader

    @FXML // fx:id="importModules"
    private Button importModules; // Value injected by FXMLLoader

    @FXML // fx:id="getPath"
    private TextField getPath; // Value injected by FXMLLoader

    @FXML // fx:id="globalParam"
    private Button globalParam; // Value injected by FXMLLoader

    @FXML // fx:id="createDDS"
    private Button createDDS; // Value injected by FXMLLoader

    @FXML // fx:id="deleteModuleDDS"
    private Button deleteModuleDDS; // Value injected by FXMLLoader

    @FXML // fx:id="ddsTable"
    private TableView<DDS> ddsTable; // Value injected by FXMLLoader

    @FXML // fx:id="ddsOrderCol"
    private TableColumn<?, ?> ddsOrderCol; // Value injected by FXMLLoader

    @FXML // fx:id="ddsNCol"
    private TableColumn<?, ?> ddsNCol; // Value injected by FXMLLoader

    @FXML // fx:id="ddsFtwCol"
    private TableColumn<?, ?> ddsFtwCol; // Value injected by FXMLLoader

    @FXML // fx:id="ddsPtdCol"
    private TableColumn<?, ?> ddsPtdCol; // Value injected by FXMLLoader

    @FXML // fx:id="ddsSymbolPeriodCol"
    private TableColumn<?, ?> ddsSymbolPeriodCol; // Value injected by FXMLLoader

    @FXML // fx:id="ddsNumFiltTapsCol"
    private TableColumn<?, ?> ddsNumFiltTapsCol; // Value injected by FXMLLoader

    @FXML // fx:id="pllTable"
    private TableView<Pll> pllTable; // Value injected by FXMLLoader

    @FXML // fx:id="pllOrderCol"
    private TableColumn<?, ?> pllOrderCol; // Value injected by FXMLLoader

    @FXML // fx:id="pllDiv_valCol"
    private TableColumn<?, ?> pllDiv_valCol; // Value injected by FXMLLoader

    @FXML // fx:id="pllFcCol"
    private TableColumn<?, ?> pllFcCol; // Value injected by FXMLLoader

    @FXML // fx:id="pllKvCol"
    private TableColumn<?, ?> pllKvCol; // Value injected by FXMLLoader

    @FXML // fx:id="pllFpCol"
    private TableColumn<?, ?> pllFpCol; // Value injected by FXMLLoader

    @FXML // fx:id="pllFzCol"
    private TableColumn<?, ?> pllFzCol; // Value injected by FXMLLoader

    @FXML // fx:id="pllGainCol"
    private TableColumn<?, ?> pllGainCol; // Value injected by FXMLLoader

    @FXML // fx:id="createPLL"
    private Button createPLL; // Value injected by FXMLLoader

    @FXML // fx:id="deleteModulePLL"
    private Button deleteModulePLL; // Value injected by FXMLLoader

    @FXML // fx:id="createDIV"
    private Button createDIV; // Value injected by FXMLLoader

    @FXML // fx:id="deleteModuleDIV"
    private Button deleteModuleDIV; // Value injected by FXMLLoader

    @FXML // fx:id="createMUL"
    private Button createMUL; // Value injected by FXMLLoader

    @FXML // fx:id="deleteModuleMUL"
    private Button deleteModuleMUL; // Value injected by FXMLLoader

    @FXML // fx:id="generateSimFiles"
    private Button generateSimFiles; // Value injected by FXMLLoader

    @FXML // fx:id="runSimulation"
    private Button runSimulation; // Value injected by FXMLLoader

    @FXML // fx:id="outputText"
    private TextArea outputText; // Value injected by FXMLLoader

    @FXML
    void createDDS(ActionEvent event) {
        try {
            //将新的界面加载进来
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/view/ddsWindow.fxml")));
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            //当这个stage被关闭时触发如下内容
            stage.setOnHiding(event1 -> {
                //清理ObservableList，并读取OutputList文件中的相关数据，导入ObservableList
                ddsData.clear();
                List<CppSimModule> list = (List<CppSimModule>) FileObjectConvert.file2Object(new File("resources/OutputList.txt"));
                if(list!=null){
                    for (CppSimModule module:list){
                        if(module.getName().equals("dds_v2")){
                            Map<String, String> param = module.getParam();
                            DDS dds = new DDS();
                            dds.setOrder(String.valueOf(module.getOrder()));
                            dds.setN(param.get("n"));
                            dds.setFtw(param.get("ftw"));
                            dds.setPtd(param.get("ptd"));
                            dds.setSymbol_period(param.get("symbol_period"));
                            dds.setNum_filt_taps(param.get("num_filt_taps"));
                            ddsData.add(dds);
                        }
                    }
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void createDIV(ActionEvent event) {

    }

    @FXML
    void createMUL(ActionEvent event) {

    }

    @FXML
    void createPLL(ActionEvent event) {
        try {
            //将新的界面加载进来
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/view/pllWindow.fxml")));
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            //当这个stage被关闭时触发如下内容
            stage.setOnHiding(event1 -> {
                //清理ObservableList，并读取OutputList文件中的相关数据，导入ObservableList
                pllData.clear();
                List<CppSimModule> list = (List<CppSimModule>) FileObjectConvert.file2Object(new File("resources/OutputList.txt"));
                if(list!=null){
                    for (CppSimModule module:list){
                        if(module.getName().equals("pll_v1")){
                            Map<String, String> param = module.getParam();
                            Pll pll = new Pll();
                            pll.setDiv_val(param.get("div_val"));
                            pll.setOrder(String.valueOf(module.getOrder()));
                            pll.setFc(param.get("fc"));
                            pll.setKv(param.get("kv"));
                            pll.setFp(param.get("fp"));
                            pll.setFz(param.get("fz"));
                            pll.setGain(param.get("gain"));
                            pllData.add(pll);
                        }
                    }
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void deleteModule(ActionEvent event) {
        //从event事件中获取button的信息
        String button = ((Button) event.getSource()).getId();
        //找到对应的tableview，找到选定的行，删除OutputList.txt文件中的这个数据和ObservableList中的数据
        if(button.contains("PLL")){
            Pll selectedItem = pllTable.getSelectionModel().getSelectedItem();
            if(selectedItem!=null){
                List<CppSimModule> list = (List<CppSimModule>) FileObjectConvert.file2Object(new File("resources/OutputList.txt"));
                Iterator<CppSimModule> iterator = list.iterator();
                while(iterator.hasNext()){
                    if(iterator.next().getOrder()==Integer.parseInt(selectedItem.getOrder())){
                        pllData.remove(selectedItem);
                        iterator.remove();
                    }
                }
                FileObjectConvert.object2File(list,new File("resources/OutputList.txt"));
            }
        }
        if(button.contains("DDS")){
            DDS selectedItem = ddsTable.getSelectionModel().getSelectedItem();
            if(selectedItem!=null){
                List<CppSimModule> list = (List<CppSimModule>) FileObjectConvert.file2Object(new File("resources/OutputList.txt"));
                Iterator<CppSimModule> iterator = list.iterator();
                while(iterator.hasNext()){
                    if(iterator.next().getOrder()==Integer.parseInt(selectedItem.getOrder())){
                        ddsData.remove(selectedItem);
                        iterator.remove();
                    }
                }
                FileObjectConvert.object2File(list,new File("resources/OutputList.txt"));
            }
        }
    }

    @FXML
    void generateSimFiles(ActionEvent event) {

    }

    @FXML
    void importModules(ActionEvent event) {
        ImportCppSimModules importCppSimModules = new ImportCppSimModules();
        String path = getPath.getText();
        try {
            Map<String, CppSimModule> result = importCppSimModules.getCppSimModules(path);
            for(String s:result.keySet()){
                cppSimModules.put(s,result.get(s));
            }
            FileObjectConvert.object2File(this.cppSimModules, new File("resources/CppSimModules.txt"));
            System.out.println("=======================成功导入模块=======================");
            Set<String> set = this.cppSimModules.keySet();
            for (String s:set) {
                System.out.println(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void runSimulation(ActionEvent event) {

    }

    @FXML
    void selectDirectory(ActionEvent event) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Choose Folder");
        File directory = directoryChooser.showDialog(new Stage());
        if (directory != null) {
            getPath.setText(directory.getAbsolutePath());
        }
    }

    @FXML
    void setGlobalParam(ActionEvent event) {

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert selectDirectory != null : "fx:id=\"selectDirectory\" was not injected: check your FXML file 'mainWindow.fxml'.";
        assert importModules != null : "fx:id=\"importModules\" was not injected: check your FXML file 'mainWindow.fxml'.";
        assert getPath != null : "fx:id=\"getPath\" was not injected: check your FXML file 'mainWindow.fxml'.";
        assert createDDS != null : "fx:id=\"createDDS\" was not injected: check your FXML file 'mainWindow.fxml'.";
        assert deleteModuleDDS != null : "fx:id=\"deleteModuleDDS\" was not injected: check your FXML file 'mainWindow.fxml'.";
        assert ddsTable != null : "fx:id=\"ddsTable\" was not injected: check your FXML file 'mainWindow.fxml'.";
        assert ddsOrderCol != null : "fx:id=\"ddsOrderCol\" was not injected: check your FXML file 'mainWindow.fxml'.";
        assert ddsNCol != null : "fx:id=\"ddsNCol\" was not injected: check your FXML file 'mainWindow.fxml'.";
        assert ddsFtwCol != null : "fx:id=\"ddsFtwCol\" was not injected: check your FXML file 'mainWindow.fxml'.";
        assert ddsPtdCol != null : "fx:id=\"ddsPtdCol\" was not injected: check your FXML file 'mainWindow.fxml'.";
        assert ddsSymbolPeriodCol != null : "fx:id=\"ddsSymbolPeriodCol\" was not injected: check your FXML file 'mainWindow.fxml'.";
        assert ddsNumFiltTapsCol != null : "fx:id=\"ddsNumFiltTapsCol\" was not injected: check your FXML file 'mainWindow.fxml'.";
        assert pllTable != null : "fx:id=\"pllTable\" was not injected: check your FXML file 'mainWindow.fxml'.";
        assert pllOrderCol != null : "fx:id=\"pllOrderCol\" was not injected: check your FXML file 'mainWindow.fxml'.";
        assert pllDiv_valCol != null : "fx:id=\"pllDiv_valCol\" was not injected: check your FXML file 'mainWindow.fxml'.";
        assert pllFcCol != null : "fx:id=\"pllFcCol\" was not injected: check your FXML file 'mainWindow.fxml'.";
        assert pllKvCol != null : "fx:id=\"pllKvCol\" was not injected: check your FXML file 'mainWindow.fxml'.";
        assert pllFpCol != null : "fx:id=\"pllFpCol\" was not injected: check your FXML file 'mainWindow.fxml'.";
        assert pllFzCol != null : "fx:id=\"pllFzCol\" was not injected: check your FXML file 'mainWindow.fxml'.";
        assert pllGainCol != null : "fx:id=\"pllGainCol\" was not injected: check your FXML file 'mainWindow.fxml'.";
        assert createPLL != null : "fx:id=\"createPLL\" was not injected: check your FXML file 'mainWindow.fxml'.";
        assert deleteModulePLL != null : "fx:id=\"deleteModulePLL\" was not injected: check your FXML file 'mainWindow.fxml'.";
        assert createDIV != null : "fx:id=\"createDIV\" was not injected: check your FXML file 'mainWindow.fxml'.";
        assert deleteModuleDIV != null : "fx:id=\"deleteModuleDIV\" was not injected: check your FXML file 'mainWindow.fxml'.";
        assert createMUL != null : "fx:id=\"createMUL\" was not injected: check your FXML file 'mainWindow.fxml'.";
        assert deleteModuleMUL != null : "fx:id=\"deleteModuleMUL\" was not injected: check your FXML file 'mainWindow.fxml'.";
        assert generateSimFiles != null : "fx:id=\"generateSimFiles\" was not injected: check your FXML file 'mainWindow.fxml'.";
        assert runSimulation != null : "fx:id=\"runSimulation\" was not injected: check your FXML file 'mainWindow.fxml'.";
        assert outputText != null : "fx:id=\"outputText\" was not injected: check your FXML file 'mainWindow.fxml'.";

        //每次打开都会清理OutputList.txt
        File OutputList = new File("resources/OutputList.txt");
        try{
            if(!OutputList.exists())
                OutputList.createNewFile();
            else{
                OutputList.delete();
                OutputList.createNewFile();
            }
        }catch (IOException e){
            e.printStackTrace();
        }

        //初始化的时候需要将控制台的信息重定向到javaFX的TextArea
        printStream = new ConsolePrint(outputText);
        System.setErr(printStream);
        System.setOut(printStream);

        //绑定tableview中的tableColumn和对象中的属性，设置table的数据集
        pllOrderCol.setCellValueFactory(new PropertyValueFactory<>("order"));
        pllDiv_valCol.setCellValueFactory(new PropertyValueFactory<>("div_val"));
        pllKvCol.setCellValueFactory(new PropertyValueFactory<>("kv"));
        pllFcCol.setCellValueFactory(new PropertyValueFactory<>("fc"));
        pllFpCol.setCellValueFactory(new PropertyValueFactory<>("fp"));
        pllFzCol.setCellValueFactory(new PropertyValueFactory<>("fz"));
        pllGainCol.setCellValueFactory(new PropertyValueFactory<>("gain"));
        pllTable.setEditable(true);
        pllTable.setItems(pllData);

        ddsOrderCol.setCellValueFactory(new PropertyValueFactory<>("order"));
        ddsNCol.setCellValueFactory(new PropertyValueFactory<>("n"));
        ddsFtwCol.setCellValueFactory(new PropertyValueFactory<>("ftw"));
        ddsPtdCol.setCellValueFactory(new PropertyValueFactory<>("ptd"));
        ddsSymbolPeriodCol.setCellValueFactory(new PropertyValueFactory<>("symbol_period"));
        ddsNumFiltTapsCol.setCellValueFactory(new PropertyValueFactory<>("num_filt_taps"));
        ddsTable.setEditable(true);
        ddsTable.setItems(ddsData);

    }

    public class ConsolePrint extends PrintStream {

        TextArea console;

        public ConsolePrint(TextArea console) {
            super(new ByteArrayOutputStream());
            this.console = console;
        }

        @Override
        public void write(byte[] buf, int off, int len) {
            print(new String(buf, off, len));
        }

        @Override
        public void print(String s) {
            console.appendText(s);
        }
    }

}
