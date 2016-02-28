

import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileReader;
// import java.util.zip.StringZipInputStream;
// import java.util.zip.StringZipOutputStream;

public class MyCompressTest	{
    final  int MAX = 1024;
    String inputFileName 	= "words.txt";
    String outputFileName 	= "words_compress.txt";
    String uncompressed 	= "words_uncompress.txt";

    void compress()	{	
	try {
		String aWord;
		BufferedReader input = new BufferedReader(new FileReader(inputFileName));
		StringZipOutputStream aStringZipOutputStream = new StringZipOutputStream( new FileOutputStream(outputFileName));

		while (  ( aWord = input.readLine() )  != null ) {
				//System.out.println("write:	" + aWord);
				aStringZipOutputStream.write(aWord);
		}
		aStringZipOutputStream.close();
		input.close();
		
	} catch ( Exception e )	{
		System.out.println("\nError: File not found. Please provide correct file path.");
		System.exit(1);
	}
    }
    void unCompress()	{	
	try {
		String aWord;
		byte[] buffer = new byte[MAX];

		BufferedWriter uncompress = new BufferedWriter(new FileWriter(uncompressed));
		StringZipInputStream aStringZipInputStream = new StringZipInputStream( new FileInputStream(outputFileName));
		String theWord;

		while (  ( theWord = aStringZipInputStream.read() ) != null ) {
			uncompress.write(theWord, 0, theWord.length());
		}
		aStringZipInputStream.close();
		uncompress.close();
		
	} catch ( Exception e )	{
		System.out.println("\nError: File not found. Please provide correct file path.");
		System.exit(1);
	}
    }
    public static void main( String args[] ) {
	MyCompressTest aMyCompressTest = new MyCompressTest();
	System.out.println("Compression started ...");	
    long startTime = System.currentTimeMillis();
	aMyCompressTest.compress();
	System.out.println("File compressed successfully!");	
	System.out.println("\nNow Decompression started ...");
	aMyCompressTest.unCompress();
	System.out.println("File decompressed successfully!");
	long endTime   = System.currentTimeMillis();
	long totalTime = endTime - startTime;
	System.out.println("\nTotalTime taken: "+(totalTime/1000)+"seconds");	
    }
}