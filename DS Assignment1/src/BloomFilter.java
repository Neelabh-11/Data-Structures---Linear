import java.util.BitSet;

//Bloom filter is a bitset array. Initially, 0 is assigned to all the bits.
//Bloom filter does two things; adds in the array and checks if it is present; even though it may not be present it may still return true.

public class BloomFilter {


	BitSet bitSet;//BloomFilter array of bits
	CustomHashFunction customHashF;
	int colCount = 0;
	int hf1, hf2; //hash functions 
	int size; //size of the bloom filter
	int totalNoHF; //total number of hash functions 
	
	public BloomFilter(int s, int n) {
		/*** Constructor
		 * s indicate the size of bloom filter and n indicate the number of hash functions
		 */
		bitSet = new BitSet(s);
		bitSet.clear();
		customHashF = new CustomHashFunction();
		size = bitSet.size();
		totalNoHF = n;
	}
	public void add(String x) {
		/***
		 * Build the bloom filter by adding the elements to it
		 */
		//if(isPresent(x)) { 
		//	colCount++;
		//}
		
		hf1 = customHashF.generateHash1(x);
		hf2 = customHashF.generateHash2(x);
		for(int i = 1; i <= totalNoHF; i++) {
			bitSet.set(Math.abs((hf1 + i *hf2 + i*i) % size));
		}		
	}
	boolean isPresent(String x) {
		// TODO Auto-generated method stub
		hf1 = customHashF.generateHash1(x);
		hf2 = customHashF.generateHash2(x);
		for(int i = 1; i <= totalNoHF; i++) {
			if(bitSet.get(Math.abs((hf1 + i *hf2 + i*i) % size))) {
				continue;
			}else {
				return false;
			}
		}
		
		return true;
	}
}
