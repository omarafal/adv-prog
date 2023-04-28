package proggroup.advprogmt;

public class DatabaseLoans extends Database{
    private String fileName;
    private String name;
    private String book;
    public DatabaseLoans(String fileName){
        super(fileName, 'w');
    }

    public void add(String name, String book){
        super.saveP1P2(name, book);
    }
}
