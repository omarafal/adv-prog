package proggroup.advprogmt;

public class Test {
    public static void main(String[] args){
        Database data = new Database("test-requests.txt");
        for(String entry: data.getPart1()){
            System.out.println(entry);
        }
    }
}
