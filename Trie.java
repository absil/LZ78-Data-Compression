/*
Abbey Silson 1315323
Curtis Barnes 1299191
*/
import java.util.ArrayList;

public class Trie{
	class TrieNode{
		int phraseNum;
		byte mismatch;
		ArrayList<TrieNode> children = new ArrayList<TrieNode>();	
		int parentPN;	
		
		//constructor for a trieNode
		public TrieNode(int pn, byte mm){
			phraseNum = pn;
			mismatch = mm;		
		}

		//returns the number of children for the current trie node
		public int childSize(){
			return children.size();
		}

		//adds a child to the current trie node
		public void addChild(TrieNode n){
			children.add(n);
			n.parentPN = this.phraseNum;
		}

		//return the phrase number of this node's parent
		public int getParent(){
			return parentPN; 			
		}

		//print all the children that the current node has (used for debugging)
		public void printChildren(){
			for(int i = 0 ; i < children.size();i++){
				System.out.println(children.get(i).phraseNum + " , " + children.get(i).mismatch);
			}
		}			
		
		//checks if the current trie node has a child with mismatched byte 'b', returns this child
		public TrieNode checkChildren(byte b){
			for(int i = 0; i < children.size(); i++){
				if(children.get(i).mismatch == b){
					return children.get(i);
				}
			}
			return null;
		}	
	}

	TrieNode root;
	int phraseNum = 0;
	String[] pairList;
	TrieNode current = root;
	TrieNode papa;

	//constructor for the trie
	public Trie(){
		root = new TrieNode(phraseNum, (byte)0);	
		phraseNum++;
	}

	//adds a new node to the trie
	public void addNode(byte mm, TrieNode parent){
		TrieNode n = new TrieNode(phraseNum, mm); 
		phraseNum++;
		parent.addChild(n);
	}

	//prints the contents of the trie
	public void printAll(TrieNode c){
		System.out.println(c.phraseNum + "children :");
		c.printChildren();
		for(TrieNode t: c.children){
			printAll(t);
		}
	}

	//returns the size of the trie
	public int getSize(){
		return phraseNum;
	}

	//finds the parent of the trie node 'c'
	public void findParent(int parentPn, TrieNode c){
		if(c.phraseNum == parentPn){
			papa = c;
			return;
		}
		for(int i = 0;i < c.children.size(); i++){
			if(c.children.get(i).phraseNum == parentPn){
				//System.out.println("child is: " + c.children.get(i).phraseNum);
				papa = c.children.get(i);
				return;
			}else{
				findParent(parentPn, c.children.get(i));		
			}
		}
	}

	//checks if the phrase 'phrase' is currently in the trie
	public boolean findPhrase(byte[] phrase, int l){
		current = root;	
		for(int i = 0;i < l; i++){
			byte b = phrase[i];	//gets the byte at the ith position in the byte array (phrase
			TrieNode foundNode = current.checkChildren(b);
			if(foundNode == null){	//if there is no matching child
				return false;
			}
			current = foundNode;
		}
		return true;
	}

	//returns the entire trie (as pairs of phrase numbers and mismatched bytes) as an array of string
	public String[] returnPairs(){
		pairList = new String[phraseNum-1];
		createPairs(root);
		return pairList;
	}
	
	//creates pairs from the trie
	public void createPairs(TrieNode n){
		if(n != root){
			pairList[n.phraseNum-1] = n.getParent() + " , " + n.mismatch;
		}
		for(TrieNode t : n.children){
			createPairs(t);
		}		
	}	

	//adds a child to the node with phrase number 'parentpn'
	public void addToPapa(int parentPn, byte mm){
		findParent(parentPn, root);
		addNode(mm, papa);		
	}

	//builds the phrase from the inputted pair
	public String buildPhrase(int ppn, byte mm){
		String phrase = "";
		TrieNode t = papa;
		while(ppn > 0){
			phrase += (char) t.mismatch;
			ppn = t.getParent();
			findParent(ppn, root);
			t = papa;
		}

		phrase = reverseString(phrase);
		phrase += (char) mm;
		return phrase;
	}

	//reverses a string
	public String reverseString(String phrase){
			
		String reverse = "";
		for(int i = phrase.length() - 1; i >= 0; i--){
			reverse += phrase.charAt(i);
		}
		return reverse;
	}
}
