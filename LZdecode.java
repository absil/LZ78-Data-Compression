/*
Abbey Silson - 1315323
Curtis Barnes - 1299191
Takes a stream of input as bytes from standard input, uses LZ78 to output pairs consisting of a phrase number and a mismatched byte, one per line of output*/

import java.util.Scanner;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
public class LZdecode{
	public static void main(String[] args){
		try{
			Scanner s = new Scanner(System.in);
			Trie dict = new Trie();
			String[] pair = new String[2];
			String output = "";
			byte mm;
			int mmI;
			String line;
			int pn;
			while(s.hasNext()){
				line = s.nextLine();	//read in a line
				pair = line.split(",");	//split the line on each comma (into phrase num and mismatch)
				mmI = Integer.parseInt(pair[1].substring(1));	//get the byte for the mismatched character (remove the space from the beginning)
				pn = Integer.parseInt(pair[0].replaceAll("\\s", ""));	//get the phrase number from the pair array, removing the space at the beginnning
				mm = (byte) mmI;		//convert the int mismatch into a byte
				//System.out.println(pair[0].replaceAll("\\s", "") +" , " + mm);
				dict.addToPapa(pn, mm);	//add the phrase number and mismatched pair to a trie
				output += dict.buildPhrase(pn, mm);	//append the phrase to the output
			}
			System.out.println(output);
		}
		catch(Exception e){
			e.printStackTrace();		
		}
	}
}
