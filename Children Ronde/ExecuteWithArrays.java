public class ExecuteWithArrays {

    public static int ChildrenRonde(int[] C, int s) {
        int size = C.length;
        for(int i=0; i<size; i++){
            C[i]=1;
        }
        int i=0;
        int current = size;
        int counter = 0;
        while(current>1){
            while(counter!=s){
                if(size==i) i=0;
                if(C[i]==1)counter++;
                if(counter==s)C[i]=0;
                i++;
            }
            counter=0;
            current--;
        }
        for(i=0; i<size; i++){
            System.out.print(C[i]);
        }
        i=0;
        boolean found = false;
        while(!found){
            if(C[i]==1)found = true;
            i++;
        }
        return i;
    }
    
    public static void main(String[] args) {
        int size = Integer.valueOf(args[0]);
        int step = Integer.valueOf(args[1]);
        int[] children = new int[size];
        int last = ChildrenRonde(children, step);
        System.out.println(last);
    }
}