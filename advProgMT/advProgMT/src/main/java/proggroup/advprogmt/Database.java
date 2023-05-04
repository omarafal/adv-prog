package proggroup.advprogmt;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public abstract class Database {
    private BufferedReader fileR;
    private BufferedWriter fileW;
    private String[] part1;
    private String[] part2;
    private int partsLength = 0;
    private String[] temp = new String[100]; // will store part1 at even and part2 at odd
    private static String path = "src/main/java/proggroup/advprogmt/Database/";

    public Database(){}
    public Database(String fileName, char mode){
        try{
            if(mode == 'r'){
                fileR = new BufferedReader(new FileReader(path + fileName));
                String currentLine = fileR.readLine();

                for(int i = 0; currentLine != null; i+=2, currentLine = fileR.readLine()){
                    temp[i] = currentLine.split("@")[0];
                    temp[i+1] = currentLine.split("@")[1];
                }
                for(String entry: temp){
                    if(entry != null)
                        partsLength++;
                }
                partsLength/=2;
            }
            else if(mode == 'w'){
                fileW = new BufferedWriter(new FileWriter(path + fileName, true));
            }
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

    public void saveP1P2(String name, String book){
        try{
            fileW.write(name+"@"+book);
            fileW.newLine();
            fileW.close();
        }
        catch(Exception exception){
            System.out.println("Exception: " + exception);
        }
    }

    public static void setPath(String path){
        Database.path = path;
    }
}
