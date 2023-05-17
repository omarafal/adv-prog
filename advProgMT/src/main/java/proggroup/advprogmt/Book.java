package proggroup.advprogmt;

import java.io.*;

public class Book {
    private String title;
    public Book(String title){
        this.title=title;
    }
    public Book(){}
    public void addBook(String title){
        BufferedWriter bw;
        {
            try {
                bw = new BufferedWriter(new FileWriter("src/main/java/proggroup/advprogmt/Database/Books.txt",true));
                bw.write(title);
                bw.newLine();
                bw.flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public static void removeBook(String bookName){
        String[] users;
        int size = 0;

        try {
            BufferedReader br = new BufferedReader(new FileReader("src/main/java/proggroup/advprogmt/Database/Books.txt"));

            String line = br.readLine();
            while(line != null){
                line = br.readLine();
                size++;
            }
            System.out.println(size);
            users = new String[size-1];
            br = new BufferedReader(new FileReader("src/main/java/proggroup/advprogmt/Database/Books.txt"));

            line = br.readLine();
            for(int i = 0; line != null;){
                if(!(bookName.equals(line.split(",")[0]))){
                    users[i] = line;
                    i++;
                }
                line = br.readLine();
            }
            br.close();
            BufferedWriter bw = new BufferedWriter(new FileWriter("src/main/java/proggroup/advprogmt/Database/Books.txt"));

            for(int i = 0; i < size-1; i++){
                bw.write(users[i]);
                bw.newLine();
            }
            bw.close();

        }catch (IOException e){
            System.out.println(e);
        }
    }
    public String getTitle() {
        return title;
    }
    public static void rentBook(String bookName){
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter("src/main/java/proggroup/advprogmt/Database/Requests.txt", true));
            bw.write(User.getUserName() + "@" + bookName);
//            System.out.println(User.getUserName() + "@" + bookName);
            bw.newLine();
            bw.flush();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }


}
