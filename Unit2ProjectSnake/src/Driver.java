public class Driver {
    public static void main(String[] args) {
       Snake slither = new Snake();
       System.out.println(slither);
       System.out.println(slither.getSize());
       System.out.println("Dir" + slither.dir);
       //slither.reverse();
       //System.out.println(slither);
       slither.update("MM");
       System.out.println(slither);
    }
}
