import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class MainFunction {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub

		BufferedReader brValidVotersListfile = null;
		//BufferedReader brEVM_votesCast = null;
		
		// Read from file1 (voter id only)
		//String validVotersListfile = "C:\\Users\\User\\Desktop\\Upgrad Documents 31_12_2018\\validVotersList.txt";
		File validVotersListfile = new File("C:\\Users\\User\\Desktop\\Upgrad Documents 31_12_2018\\validVotersList.txt");
		Scanner sValidVotersListfile = new Scanner(validVotersListfile);
		try {
			brValidVotersListfile = new BufferedReader(new FileReader(validVotersListfile));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Read from file2 (all voters and candidates)
		//String EVM_votesCast ="C:\\Users\\User\\Desktop\\Upgrad Documents 31_12_2018\\votersCandList.txt";
		File EVM_votesCast = new File("C:\\Users\\User\\Desktop\\Upgrad Documents 31_12_2018\\votersCandList.txt");
		Scanner sEVM_votesCast = new Scanner(EVM_votesCast);
		validVotersArray[] validVotersArr = new validVotersArray[400000];
		for (int q = 0; q<400000 ; q++)
		{
			int a = sValidVotersListfile.nextInt();
			validVotersArr[q] = new validVotersArray(a);
		}
		//Scanner sValidVotersListfile;
		//while(sValidVotersListfile.hasNextInt()) {
			//validVotersList.add(sValidVotersListfile.nextInt());
		//}
		BloomFilter bloomFilter = new BloomFilter(49021770, 7);
		try {
			int counter = 400000; //size of the valid voters 
			int numFalsePos = 0;
			String line = null;
			while((line = brValidVotersListfile.readLine()) != null) {
				for(int i=0; i<counter/2; i++) {
					bloomFilter.add(line);//adding half of the valid voters to bloom filter
					counter--;
				}
				if(bloomFilter.isPresent(line)) {
					numFalsePos++;
				}
			}
			System.out.println("Total number of false positives are " + numFalsePos);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//Readable sEVM_votesCast = null;
		//Scanner sc11 = new Scanner(sEVM_votesCast);
		
		//Storing the first value of the file wiz 600000 to the size of the array
    	int n = sEVM_votesCast.nextInt();
		ADT[] arr = new ADT[n];
		for (int i = 0; i < n; i++) {
			int a = sEVM_votesCast.nextInt(); // a is the voter ID 
			int b = sEVM_votesCast.nextInt(); // b is the Candidate ID 
			arr[i] = new ADT(a, b);
		}
		sEVM_votesCast.close();
		
		Scanner src = new Scanner(System.in);
		int input_voter_id;
		System.out.println("Enter the voter id to check for which candidate the vote was cast");
		input_voter_id = src.nextInt();
		//boolean check1 = validVotersList.contains(input_voter_id);
		for(validVotersArray value: validVotersArr ) {
			if(value.a == input_voter_id) {
				for(ADT check1:arr) {
					if(check1.a==input_voter_id) {
						System.out.println("The candidate ID is "+check1.b);
						break;
			}
		}
		}
			//else 
				//System.out.println("The voter ID is not valid");
		}
		
		System.out.println("Enter the candidate ID to check the number of valid votes");
		int input_candidateId = src.nextInt();
		//countVotes cv = new countVotes();
		//int s = cv.countVotesFromValidList;
		int votesCount = 0;
		for(ADT votes: arr ) {
			if(votes.b==input_candidateId) {
				if(votes.a>=400000 && votes.a<=600000 ) {
					votesCount = 0;
				}
				else 
					votesCount++;
			}
		}
		System.out.println("The number of votes for the given candidate is " + votesCount);
		
		System.out.println("total number of collisions while building a bloom filter are " + bloomFilter.colCount);
		//System.out.println("Total number of false positives are " + numFalsePos);
	}
}




