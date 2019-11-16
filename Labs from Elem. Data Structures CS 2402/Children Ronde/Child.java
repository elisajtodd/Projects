    public class Child {
    private String name;
    private int number;
    
    public Child() {}
    
    public Child(String n) {
        name = n;
    }

    public Child(String n, int num) {
        name = n;
        number = num;
    }
    
    public String toString() {
        return "Child #" + number + ": " + name;   
    }
}