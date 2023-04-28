package proggroup.advprogmt;

public class DatabaseRequests extends Database{
    public DatabaseRequests(String fileName){
        super(fileName);
    }

    public String[] getUsers(){
        return getPart1();
    }

    public String[] getBooks(){
        return getPart2();
    }
}
