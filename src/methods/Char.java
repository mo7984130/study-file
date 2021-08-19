package methods;

public class Char {

    public static Character getUppercaseChar(){
        return  (char)(Math.random()*('Z' - 'A' + 1) + 'A');
    }

}
