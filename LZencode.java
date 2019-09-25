/*
Abbey Silson - 1315323
Curtis Barnes - 1299191
Takes a stream of input as bytes from standard input, uses LZ78 to output pairs consisting of a phrase number and a mismatched byte, one per line of output*/

import java.util.Scanner;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
public class LZencode{
	public static void main(String[] args){
		
		try{
			int buffSize = 256;
			byte[] buffer = new byte[buffSize];
			Byte b;		
			ArrayList<String> pair = new ArrayList<String>();
			int bLength = 0;
			Trie dict = new Trie();

			while((b = (byte) System.in.read()) != -1){	//read in a byte from the input stream
				if(b == 10 && System.in.available() == 0)
					break;
				for(int i = 0; i < buffer.length; i++){	//find the next position to add the byte to the buffer
					if(buffer[i] == 0){	
						buffer[i] = (byte)b;			//add the byte into buffer array						
						bLength = i + 1;
						i = buffer.length;
					}
				}
				if(dict.findPhrase(buffer, bLength) == false){	//if the phrase isn't in the dictionary, add it
					dict.addNode((byte)b, dict.current);
					buffer = new byte[buffSize];
				}
				
			}

			String[] pairs = dict.returnPairs();	//get a string array of pairs

			for(String s: pairs){	//print all the pairs to standard out
				System.out.println(s);
			}
			if(buffer[0] != 0){	//if there is still something in the buffer
				System.out.println(dict.current.getParent() + " , " + dict.current.mismatch);
			}
		
		}catch(Exception e){
			e.printStackTrace();
		}

	}
}
