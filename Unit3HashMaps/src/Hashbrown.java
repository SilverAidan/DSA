public class Hashbrown {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello".hashCode());
        for(char c:"Hello".toCharArray()){
            System.out.println((int)c);
        }
    }
}
