/*
Abbey Silson - 1315323
Curtis Barnes - 1299191
*/

import java.util.Scanner;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;

public class LZunpack{
	public static void main(String[] args){
		int pCount = 1;
		int pLog = 0;
		int b;
		int storage = 0;
		byte mm;
		int pNum = 0;
		int mask = 0;
		int used = 0;

		try{			
			while((b = System.in.read()) != -1){
				String binary = String.format("%8s", Integer.toBinaryString(b)).replace(' ', '0');
				//add b to storage
				mask = b;
				mask <<= used;
				storage |= mask;

				used += 8;	//increment used by 8 (one byte)

								
				if(used > 24){ //if storage is full - poo out some bytes to make pairs				
					pLog = (int) Math.ceil(Math.log(pCount)/Math.log(2.0));	//calculates the number of bits that the phrase number will be using 
					String n = "";	
					if(pLog != 0){	
						for(int i = 0; i < pLog; i++){//create the mask for the phrase number
							n += 1;
						}					
						int p = Integer.parseInt(n, 2);
						
						mask = p;

						pNum = storage & mask;	//set phrase number variable to the anded storage and mask (getting the phrase number)

					}
					//shift storage along by masked out amount
					storage >>>= pLog;
					used -= pLog;	//decrement used by the number of bits that have just been removed

					mm = (byte) storage;	//get the next byte from storage (mismatched byte will always be 8 bits)
					
					storage >>>= 8;	//shift storage along by 8 - getting rid of the bits just read out

					used -= 8;	//decrement used by 8

					if(pNum == 0 && mm == 0)
						break;
					System.out.println(pNum + " , " + mm);	//print the pair to the console/file

					//increment pCount
					pCount++;
				}
				
			}
			while(used > 0){	//if there are still bits in storage when finished reading the file, read these out					
					pLog = (int) Math.ceil(Math.log(pCount)/Math.log(2.0));
					String n = "";
					if(pLog != 0){
						for(int i = 0; i < pLog; i++){	//create the mask for pn
							n += 1;
						}
					
						int p = Integer.parseInt(n, 2);
						
						mask = p;

						pNum = storage & mask;	//get phrase num out of storage

					}
					//shift storage along by masked out amount
					storage >>>= pLog;
					used -= pLog;

					mm = (byte) storage;	//get mismatched byte out of storage
					
					storage >>>= 8;

					used -= 8;
					if(pNum == 0 && mm == 0)
						break;
					System.out.println(pNum + " , " + mm);

					//increment pCount
					pCount++;
			}	
			
		}
		catch(Exception e){
			e.printStackTrace();
		}

	}
}

