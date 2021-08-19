package arrayList;

public class HashCode {

    public static String getRandomString(){
        int length = 0;
        while (true){
            length = (int)( Math.random() *10);
            if (length>1 && length<11){
                break;
            }
        }

        String str = "";
        char c = 0;

        for (int j = 0; j < length; j++) {
            int i = (int)(Math.random()*(2 + 1));
            switch(i) {
                case 0:
                    c = (char)(Math.random()*('9' - '0' + 1) + '0');
                    break;
                case 1:
                    c = (char)(Math.random()*('z' - 'a' + 1) + 'a');
                    break;
                case 2:
                    c = (char)(Math.random()*('Z' - 'A' + 1) + 'A');
            }
            str += c;
        }
        return str;

    }

    public static int toHashCode(String str){
        char[] cs = str.toCharArray();
        int i1 = 0;
        for (int i=0;i<cs.length;i++){
            i1 += cs[i];
        }
        int i2 = i1*23;
        if (i2>1999){
            i2 = i2%2000;
        }else if (i2<0){
            i2 = -1 * i2;
        }
        return i2;
    }

    public static void main(String[] args){
        for (int i=0;i<100;i++){
            System.out.println(toHashCode(getRandomString()));
        }
    }

}
