/*
Abbey Silson - 1315323
Curtis Barnes - 1299191
*/

import java.util.Scanner;

public class LZpack{
	public static void main(String[] args){
		try{
			Scanner s = new Scanner(System.in);
			int phraseNum;
			int mm;
			int mask;
			int packedPair = 0;
			int used = 0;
			int counter = 1;
			int counterLog = 0;
			int length = 0;
			int i;
			String format = "";

			phraseNum = s.nextInt();	//get the first phrase number
			s.next();	//skip the comma
			mm = (byte) s.nextInt();	//get the first mismatched byte
			while(1 == 1){
				if(counter != 1){	//if it's not the first byte
					counterLog = (int) Math.ceil(Math.log(counter)/Math.log(2.0));	//get the log 2 of counter
				}
				else{ 
					counterLog = 0;					
				}	
				mask = phraseNum;	//set mask to the phrase number
				mask <<= used;		//shift the mask across by used
				packedPair |= mask;	//store the mask in the packedpair int
				
				used += counterLog;	//add the number of bits that the phrase number requires to used

				mask = mm;	//set mask to the mismatched byte
				mask <<= used;		//shift the mask across by used
				packedPair |= mask;	//store the mask(mm byte) into the packedpair
				
				used += 8;	//increment used by 8 (one byte) worth of bits

				while(used >= 8){	//if there is more than one byte in packedpair
					System.out.write(packedPair);	//write out the byte which is on the far right
					packedPair >>>= 8;		//shift out the bits that have just been written out
					used -= 8;			//decrement used by the 8 bits that have just been removed
				}
				
				if(s.hasNextInt()){
					phraseNum = s.nextInt();	//get the first phrase number
					s.next();			//skip the comma
					mm = s.nextInt();		//get the first mismatched byte
					counter++;			//increment the counter
				}
				else{
					System.out.write(packedPair);
					break;
				}				
			}
			System.out.flush();		//flush the output stream to write out the packed pairs
		}
		catch(Exception e){
			e.printStackTrace();		
		}
	}
}
