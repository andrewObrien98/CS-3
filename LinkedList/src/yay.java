import java.time.Year;

public class yay {


    public static void main(String[] args) {
        yay ya = new yay();
        ya.create();
    }

    public void create(){
        String yep = "";
        for(int i = 0; i < 10; i++){
            addToString(yep);
        }
        System.out.println(yep);
    }

    public void addToString(String ya){
        ya += "1";
    }
}
