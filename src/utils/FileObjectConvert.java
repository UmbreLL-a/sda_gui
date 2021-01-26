package utils;

import java.io.*;

/**
 * @program: sda_gui
 * @description:
 * @author: LYT
 * @create: 2021-01-24 22:40
 **/

public class FileObjectConvert {
    public static void object2File(Object obj, File outFile){
        ObjectOutputStream oos = null;
        FileOutputStream fos = null;
        try {
            if(!outFile.exists()){
                outFile.createNewFile();
            }
            fos = new FileOutputStream(outFile);
            oos = new ObjectOutputStream(fos);

            oos.writeObject(obj);
        }catch (Exception e) {
            e.printStackTrace();
        }finally{
            if (oos != null) {
                try {
                    oos.close();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            if(fos != null){
                try {
                    fos.close();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
    }

    public static Object file2Object(File inFile){
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = new FileInputStream(inFile);
            ois = new ObjectInputStream(fis);


            return ois.readObject();
        } catch (Exception e) {
            // TODO: handle exception
        }finally{
            if(ois!= null){
                try {
                    ois.close();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (Exception e2) {
                    // TODO: handle exception
                    e2.printStackTrace();
                }
            }
        }
        return null;
    }

}
