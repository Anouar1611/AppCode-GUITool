

public class Foo {
    private String str;

    public Foo(String str){
        this.str=str;
    }

    public static void main(String[] args) {
        Foo foo = new Foo("foo");
        System.out.println(foo.str);
        System.out.println("ttt");
        System.out.println(foo.str);
    }
}