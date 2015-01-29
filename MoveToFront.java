import java.util.*;

public class MoveToFront {
  public static char[] asciiTable = new char[256];
  public static char in;
  public static int asciiIndex;
  public static int ind = 0;
  public static int[] out = new int[1];
  public static String decoded="";
  
    // apply move-to-front encoding, reading from standard input 
    // and writing to standard output
  public static void encode(){
    int index=0;
    for(int i=0;i<256;i++){
      asciiTable[i] = ((char)i);
    }
    while(!(BinaryStdIn.isEmpty())){
      in = BinaryStdIn.readChar();
      int j=0;
      while(in != asciiTable[j]){
        j++;
      }
      index = j;
      mtf(index);
      out[ind] = index;
      resize(out.length+1);
      ind++;
    }
  }
  
  public static void resize(int size){
    int[] temp = new int[size];
    for(int i=0;i<out.length;i++){
      temp[i] = out[i];
    }
    out = temp;
  }
  
  public static void mtf(int x){
    char temp = asciiTable[x];
    for(int i=x;i>0;i--){
      asciiTable[i] = asciiTable[i-1];
    }
    asciiTable[0] = temp;
  }

    // apply move-to-front decoding, reading from standard input 
    // and writing to standard output
  public static void decode(){
    for(int i=0;i<256;i++){
      asciiTable[i] = ((char)i);
    }
    while(!(BinaryStdIn.isEmpty())){
      in = BinaryStdIn.readChar();
      decoded += asciiTable[(int)in];
      mtf((int)in);
    }
    BinaryStdOut.write(decoded);
    BinaryStdOut.flush();
  }

    // if args[0] is '-', apply move-to-front encoding
    // if args[0] is '+', apply move-to-front decoding
  public static void main(String[] args){
    if(args[0].equals("-")){
      encode();
      for(int i=0;i<out.length-1;i++){
        BinaryStdOut.write((char)(out[i]));
        BinaryStdOut.flush();
      }
    }else if(args[0].equals("+")){
      decode();
    }
  }
}