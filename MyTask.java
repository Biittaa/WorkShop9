import com.sun.nio.sctp.SctpSocketOption;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CopyOnWriteArrayList;

public class MyTask implements Runnable {
    private File file;
    private  ArrayList<String> words = new ArrayList<>();
    public MyTask(File f){
        this.file = f;
    }
    public   ArrayList<String> getWords() {
        return words;
    }

    public void run(){
        String i;
        try (Scanner sc = new Scanner(new FileReader(file))){
            while (sc.hasNext()) {
                i = sc.nextLine();
                words.add(i);
            }
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }
}
