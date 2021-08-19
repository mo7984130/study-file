package SomeLittleProgram;

import java.net.URL;

public class url {
    public static void main(String[] args){
        try {
            for (int i =3915990;i<3916000;i++){
                URL url = new URL("https://www.biquwx.la/0_376/" + i + ".html");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
