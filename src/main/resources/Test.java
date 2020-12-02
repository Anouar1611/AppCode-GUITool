import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Test {
    private String str;

    public Test(){
        this.str="nasro";
    }

    public static void main(String[] args) throws IOException {
        Test test = new Test();
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter first test string : ");
        String str= input.readLine();
        System.out.println("Test.str: "+ test.str +"\t str: " + str );
        System.out.println("Test.str: "+ test.str);
        
    }
}

