/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wordcount;
import java.io.*;

import java.util.*;

/**
 *
 * @author moe
 */
public class WordCount {
    private static Integer base=1;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception{
        HashMap<String, Integer> wordlist = new HashMap<>(); // hash map to represent a phone book
       Scanner input=new Scanner(System.in);
       String filename="/Users/moe/Desktop/lyrics.txt";
       Scanner scanner=new Scanner(new File(filename));
       read(wordlist,scanner);
       Iterator iterator = wordlist.keySet().iterator();

HashMap<String, Integer> sortedMapAsc = WordCount.sortByValue(wordlist);

        // Report frequencies
        for (String word : sortedMapAsc.keySet()) {
            int count = sortedMapAsc.get(word);
            System.out.println(count + ": " + word);
        }
    }

       
    public static HashMap<String, Integer> sortByValue(HashMap<String, Integer> hm) {

      // Create a list from elements of HashMap
      java.util.List<Map.Entry<String, Integer> > list = new LinkedList<Map.Entry<String, Integer> >(hm.entrySet());

      // Sort the list
      Collections.sort(list, new java.util.Comparator<Map.Entry<String, Integer> >() {
        public int compare(Map.Entry<String, Integer> o1,
                           Map.Entry<String, Integer> o2) {
          return (o2.getValue()).compareTo(o1.getValue());
        }
      });

      // put data from sorted list to hashmap
      HashMap<String, Integer> temp = new LinkedHashMap<String, Integer>();
      for (Map.Entry<String, Integer> aa : list) {
        temp.put(aa.getKey(), aa.getValue());
      }
      return temp;
    }
    
    public static void read(HashMap hm, Scanner scan){
        while(scan.hasNextLine()){
        String line=scan.nextLine();
        Scanner lineScanner=new Scanner(line);
        while(lineScanner.hasNext()){
            String word=lineScanner.next();
            String clean=word.replaceAll("\\p{Punct}", "").toLowerCase();
            if(hm.containsKey(clean)==false){
                
                hm.put(clean,base);
             }
            else if(hm.containsKey(clean)==true){
                increase(hm,clean);
            }
            
        }
    }
 }
    
    public static void increase(HashMap hm, String word){
        int num= (int)hm.get(word);
        int newnum=num+1;
        hm.replace(word,newnum);
    }
    
}
