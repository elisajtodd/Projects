public class ExecuteWithCList {
    
    public static void main(String[] args) {

        int size = Integer.valueOf(args[0]);
        int step = Integer.valueOf(args[1]);
        
        CList<Child> ronde = new CList<Child>();
        Child C;
        for (int i=0 ; i<size; i++) {
            C = new Child("child", i+1);  
            ronde.addDataAtEnd(C);
        }
        ronde.print();
        ronde.ChildrenRonde(step);
    }
}