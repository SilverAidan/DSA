public class offThe {
   public static void main(String[] args){
    Chain chain = new Chain(5, 4, 2);
    System.out.println(chain);
    Chain gold = new Chain();
    gold.addNode(0, 0);
    System.out.println(gold);
    gold.addNode(2, 1);
    System.out.println(gold);
    gold.addNode(3, 4);
    System.out.println(gold);
   }
}
