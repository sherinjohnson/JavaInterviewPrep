public class FibonacciSeries {
    public static void main (String[]args){
    int a=1;
    int b=2;
    System.out.println(a);
    System.out.println(b);
    for(int i=2;i<=10;i++){
        int c =a+b;
        a=b;
        b=c;
        System.out.println(c);
    }

 }
}
