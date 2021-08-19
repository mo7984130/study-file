package methods;

import java.io.File;

public class getFile {

    public static String getFileName(File f){
        String filepath  = String.valueOf(f);
        String[] str = filepath.split("/");
        String fileAllName = str[str.length-1];
        int position = 0;
        char[] cs = fileAllName.toCharArray();
        for (int i = 0;i<cs.length;i++){
            if (cs[i] == '.'){
                position = i;
            }
        }
        String fileName = "";
        for (int i = 0;i<position;i++){
            fileName += String.valueOf(cs[i]);
        }
        return  fileName;
    }

    public static String getFileExtension(File f){
        String filepath  = String.valueOf(f);
        String[] str = filepath.split("/");
        String fileAllName = str[str.length-1];
        int position = 0;
        char[] cs = fileAllName.toCharArray();
        for (int i = 0;i<cs.length;i++){
            if (cs[i] == '.'){
                position = i;
            }
        }
        String fileExtension = "";
        for (int i = position;i<cs.length;i++){
            fileExtension += String.valueOf(cs[i]);
        }
        return  fileExtension;
    }

}
