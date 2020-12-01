import java.io.IOException;

//TODO: Make location descriptions of errors clickable, so users can navigate to the exact cursor positions in code.
public class Zero {
    private String str;

    public Zero(){
        this.str="nasro";
    }

    public static void main(String[] args) {

        double x;
        System.out.println("Output:::");
        try {
            x = 5/0;
        }catch (Exception e){
            e.printStackTrace();
        }



    }
}