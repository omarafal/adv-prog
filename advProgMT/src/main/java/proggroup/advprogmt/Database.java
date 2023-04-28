package proggroup.advprogmt;
import java.io.BufferedReader;
import java.io.FileReader;

public class Database {
    private BufferedReader file;
    private String[] part1;
    private String[] part2;
    private int partsLength = 0;
    private String[] temp = new String[100]; // will store part1 at even and part2 at odd
    private static String path = "/home/omarafal/repos/adv-prog/advProgMT/src/main/java/proggroup/advprogmt/Database/";

    public Database(String fileName){
        try{
            file = new BufferedReader(new FileReader(path + fileName));
            String currentLine = file.readLine();

            for(int i = 0; currentLine != null; i+=2, currentLine = file.readLine()){
                temp[i] = currentLine.split("@")[0];
                temp[i+1] = currentLine.split("@")[1];
            }
            for(String entry: temp){
                if(entry != null)
                    partsLength++;
            }
            partsLength/=2;
        }
        catch(Exception exception){
            System.out.println("Exception: " + exception);
        }
    }
    // part1@part2
    public String[] getPart1(){
        part1 = new String[partsLength];
        int j = 0;
        for(int i = 0; temp[i] != null; i+=2, j++){
            part1[j] = temp[i];
        }
        return part1;
    }

    public String[] getPart2(){
        part2 = new String[partsLength];
        int j = 0;
        for(int i = 1; temp[i] != null; i+=2, j++){
            part2[j] = temp[i];
        }
        return part2;
    }

    public static void setPath(String path){
        Database.path = path;
    }
}
