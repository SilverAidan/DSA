public class Driver {
    public static void main(String[] args) {
        Body B4 = new Body(4,3,null);
        Body B3 = new Body(3,3,B4);
        Body B2 = new Body(2,3,B3);
        Body B1 = new Body(2,2,B2);
        Snake Python = new Snake(1,2,1,B1);
        System.out.println(Python);
        
        System.out.println("Step1 LMUMMR");
        Python.update("LMUMMR");
        System.out.println(Python);
        System.out.println("Step2 MMMEMFMM");
        Python.update("MMMEMFMM");
        System.out.println(Python);
    }
}
