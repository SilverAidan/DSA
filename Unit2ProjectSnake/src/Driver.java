public class Driver {
    public static void main(String[] args) {
       Snake slither = new Snake();
       System.out.println(slither);
       System.out.println(slither.getSize());
       slither.reverse();
       System.out.println(slither);
       slither.update('M');
       System.out.println(slither);
    }
}
