import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.*;
import java.util.concurrent.*;

public class Main
{
    public static void main(String[] args) {
        File folder = new File("C:\\Users\\Lenovo\\IdeaProjects\\workShop9\\assets");
        File[] filesList = folder.listFiles();
        ArrayList<MyTask> tasks = new ArrayList<>();
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (File file : filesList) {
            MyTask t = new MyTask(file);
             Thread thread = new Thread(t);
            executorService.execute(thread);
            tasks.add(t);
        }
        executorService.shutdown();
        Scanner scan = new Scanner(System.in);
        try {
            while (!(executorService.awaitTermination(1, TimeUnit.MINUTES))) {
                System.out.println("wait!");
            }
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
        ArrayList words = new ArrayList<>();
        for(MyTask k : tasks){
           // System.out.println(k.getWords().get(0));
          words.addAll(k.getWords());

        }
        ArrayList<String> l =removeDuplicated(words);
        printMenu();
        int f = scan.nextInt();
        while (f != 5) {
            if (f == 1) {
                System.out.println(l.size() + "\n");
            } else if (f == 2) {
                LongestWord(words);
            } else if (f == 3) {
                shortestWord(words);
            } else if (f == 4) {
                average(words);
            }
            printMenu();
            f = scan.nextInt();
        }
    }

    public static void printMenu() {
        System.out.println("1.Words Count\n2.Longest word and its length\n3.Shortest word and its length\n" +
                "4.All words length average\n" + "5.exit");
    }

    public static ArrayList<String> removeDuplicated(ArrayList<String> list) {
        ArrayList<String> l = new ArrayList<>();
        l = list;
        for (int i = 0; i < l.size() - 1; i++) {
            for (int j = i + 1; j < l.size(); j++) {
                if (l.get(i).equals(l.get(j))) {
                    l.remove(j);
                }
            }
        }
        return l;
    }

    public static void LongestWord(ArrayList<String> list) {
        int i = 0;
        ArrayList<String> m = new ArrayList<>();
        for (String word : list) {
            if (word.length() > i) {
                i = word.length();
            }
        }
        print(list,i);
    }

    public static void shortestWord(ArrayList<String> list) {
        int i = list.get(0).length();
        ArrayList<String> m = new ArrayList<>();
        for (String word : list) {
            if (word.length() < i) {
                i = word.length();
            }
        }
        print(list,i);
    }
    public static void print(ArrayList<String> list,int i){
        ArrayList<String> m =new ArrayList<>();
        for(String word : list){
            if(word.length() == i){
                m.add(word);
            }
        }
        System.out.println("length: " + i );
        System.out.println("words:");
        for(String n : m){
            System.out.println("   "+n);
        }
        System.out.println();
    }
    public static void average(ArrayList<String> list) {
        int l = 0;
        for (String word : list) {
            l += word.length();
        }
        double d;
        d = (double)l / (list.size());
        System.out.println(d + "\n");
    }
}