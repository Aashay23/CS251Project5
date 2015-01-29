import java.util.*;

public class BurrowsWheeler {
  public static String[] starray;
  public static String[] array;
  public static String[] carray;
  public static int len;
  public static int number;
  public static int index;
  public static int[] ind;
  public static int[] next;
  public static String in;
  public static String encoded = "";
  public static String decoded = "";
  
    // apply Burrows-Wheeler encoding, reading from standard input 
    // and writing to standard output
  public static void encode(){
    int encodeIndex;
    int originalPos=0;
    ind = new int[in.length()];
    SuffixArray suffix = new SuffixArray(in);
    for(int i=0;i<ind.length;i++){
      index = suffix.index(i);
      ind[i] = index;
    }
    for(int j=0;j<ind.length;j++){
      if(ind[j] == 0){
        encodeIndex = starray.length-1;
      }else{
        encodeIndex = ind[j]-1;
      }
      encoded += array[encodeIndex];
    }
    for(int k=0;k<ind.length;k++){
      if(ind[k] == 0){
        originalPos = k;
      }
    }
    BinaryStdOut.write(originalPos);
    BinaryStdOut.flush();
    BinaryStdOut.write(encoded);
    BinaryStdOut.flush();
  }

    // apply Burrows-Wheeler decoding, reading from standard input 
    // and writing to standard output
  public static void decode(){
    array = new String[in.length()];
    next = new int[in.length()];
    for(int i=0;i<in.length();i++){
      array[i] = String.valueOf(in.charAt(i));
      array[i] += String.valueOf(i);
    }
    LSD.sort(array, 1);
    for(int j=0;j<next.length;j++){
      next[j] = Integer.parseInt(array[j].substring(1));
    }
    int di = number;
    for(int step=0; step<next.length;step++){
      di = next[di];
      decoded += in.charAt(di);
    }
    BinaryStdOut.write(decoded);
    BinaryStdOut.flush();
  }

    // if args[0] is '-', apply Burrows-Wheeler encoding
    // if args[0] is '+', apply Burrows-Wheeler decoding
    public static void main(String[] args){
      if(args[0].equals("-")){
        in = BinaryStdIn.readString();
        len = in.length();
        starray = new String[len];
        array = new String[len];
        for(int i=0;i<len;i++){
          starray[i] = String.valueOf(in.charAt(i));
          array[i] = starray[i];
        }
        encode();
      }else if(args[0].equals("+")){
        number = BinaryStdIn.readInt();
        in = BinaryStdIn.readString();
        decode();
      }
    }
}