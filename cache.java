/* Direct Cache Mapping*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.io.FileReader;  

public class cache {
	static int memory=64;  
	

	class Block {
		
		char[] tag;
		char[] index;
		char[] offset;
		
	}
static class Cache2 {
		int hits;
		int misses;
		int reads;
		int writes;
		int cache_size;
		int block_size;
		int numLines;
		int write_policy;
		Block[] blocks;    
	}

	static class Reader{
		static BufferedReader reader;
		static StringTokenizer tokenizer;

		/** call this method to initialize reader for InputStream */
		static void init(InputStream input) {
			reader = new BufferedReader(
					new InputStreamReader(input) );
			tokenizer = new StringTokenizer("");
		}

		/** get next word */
		static String next() throws IOException {
			while ( ! tokenizer.hasMoreTokens() ) {
				//TODO add check for eof if necessary
				tokenizer = new StringTokenizer(
						reader.readLine() );
			}
			return tokenizer.nextToken();
		}

		static int nextInt() throws IOException {
			return Integer.parseInt( next() );
		}

		static double nextDouble() throws IOException {
			return Double.parseDouble( next() );
		}
	}

	public String binary(int n) {
		String a="00000000".substring(Integer.toBinaryString(n).length())+ Integer.toBinaryString(n);
		System.out.println(a);
		    return a;
	}
	
public Cache2 createCache(int cache_size, int block_size,int lines)
	{
		if(cache_size <= 0)
		{
			System.out.println("Cache size must be greater than 0 bytes...\n");
			return null;
		}

		if(block_size <= 0)
		{
			System.out.println("Block size must be greater than 0 bytes...\n");
			return null;
		}
		else {
        /* Lets make a cache! */
		Cache2 cache = new Cache2();cache.hits = 0;
		cache.misses = 0;
		cache.reads = 0;
		cache.writes = 0;
        //cache.write_policy = write_policy;
        cache.cache_size = cache_size;
		cache.block_size = block_size;
        cache.numLines = lines;
        cache.blocks = new Block [cache.numLines] ;
		
		return cache;
		}
	}
  
	void destroyCache(Cache2 cache)
	{
	    int i;
	    
	    if(cache != null)
	    {
	        for( i = 0; i < cache.numLines; i++ )
	        {
	        	cache.blocks[i]=null;
	        }
                
	    }
	    return;
	}
    
	static void generateAllBinaryStrings(int n,char[] m, int i) 
	{ 
		if (i == n)  
		{ 

			return; 
		} 

		m[i] = 0; 
		generateAllBinaryStrings(n, m, i + 1); 

		m[i] = 1; 
		generateAllBinaryStrings(n, m, i + 1); 
	} 
	public Cache2 readFromCache(Cache2 cache,int CL,int TAG,int INDEX, int OFFSET,int dec,int write_policy,int[] arr2)
	{
	    
	    char[] bstring;
	    int i;Block blocks;
	    int number=1;String f="";
	    String[] arr=new String[arr2.length];
	    if(memory==64) {
	    	f="000000";
	    }
	    else if(memory==128) {
	    	f="0000000";
	    }
	    else if(memory==256) {
	    	f="00000000";
	    }
	    for(int i5=0;i5<arr2.length;i5++) {
	    	arr[i5]=f.substring(Integer.toBinaryString(arr2[i5]).length())+ Integer.toBinaryString(arr2[i5]);
	    }
	    
	    /* Validate inputs */
	    if(cache == null)
	    {
	        System.out.println("Error: Must supply a valid cache to write to.\n");
	        return cache;
	    }
	    String h=f.substring(Integer.toBinaryString(dec).length())+ Integer.toBinaryString(dec);
	    bstring =h.toCharArray();
	    //System.out.println(h);
	    i = 0;
	    
	    char[] tag =new char[TAG];
	    for(i=0;i<TAG;i++) {
	    	tag[i]=bstring[i];
	    	//System.out.println(tag[i]);
	    }
	
	    char[] index=new char[INDEX];
	    
	    for(i = TAG; i < INDEX + TAG; i++)
	    {
	        index[i - TAG] = bstring[i];
	        //System.out.println(index[i-TAG]);
	    }
	    
	    char[] offset = new char [OFFSET];
	    
	    for(i = INDEX + TAG; i < OFFSET + INDEX + TAG; i++)
	    	
	    {    
	    	//System.out.println(i-INDEX-TAG);
	        offset[i - INDEX - TAG] = bstring[i];
	        //System.out.println(offset[i-INDEX-TAG]);
	    }
	    int p1=0;
	       
	    
    	if(INDEX==2) {
    		p1+=(Character.getNumericValue(index[0])*2)+(Character.getNumericValue(index[1]));
    	}
    	else if(INDEX==3) {
    		p1+=(Character.getNumericValue(index[0])*4)+(Character.getNumericValue(index[1])*2)+(Character.getNumericValue(index[2]));
    	}
    	
    	StringBuilder sb = new StringBuilder();
    	for(int i3=0;i3<TAG;i3++) {
    	sb.append(tag[i3]);
    	}
    	
    	String str = sb.toString();
    	//System.out.println(str);
    	StringBuilder sb1 = new StringBuilder();
    	for(int i3=0;i3<INDEX;i3++) {
    	sb1.append(index[i3]);
    	}
    	
    	String stringn = sb1.toString();
    	//System.out.println(stringn);
         blocks = cache.blocks[p1];
	     // generateAllBinaryStrings(TAG,block,0);
          
           
            for(i=0;i<arr2.length;i++) {
            String p=arr[i];
           // System.out.println(p.substring(0,TAG));
           // System.out.println(p.substring(TAG,INDEX+TAG));
        	if(p.substring(0,TAG).equals(str) && p.substring(TAG,INDEX+TAG).equals(stringn)) {
        		number=0;
        		break;
        		
        	}
            } if (number==0 ) {cache.hits++;
	        System.out.println("Cache obtained a hit");cache.reads++;
	        if(write_policy == 1)
	        {
	            cache.writes++;
	            
	        }
        }
	    
        else{        
	        cache.misses++;
	        System.out.println("Cache obtained a miss");
	        cache.reads++;
	        
	        if(write_policy == 1)
	        {
	            cache.writes++;
	            
	        }
	        
	      //block.tag = tag;
	    }
        int g=dec%CL;
	    System.out.println("Block will be at cache line:" + " "+ g);
	     return cache;
	}
	
public Cache2 writeToCache(Cache2 cache,int CL,int TAG,int INDEX, int OFFSET,int dec)
	{
	    
	    char [] bstring;
	    int i;
	    String f="";
	   /* Validate inputs */
	    if(cache == null)
	    {
	        System.out.println("Error: Must supply a valid cache to write to.\n");
	        return cache;
	  
	    }
	    if(memory==64) {
	    	f="000000";
	    }
	    else if(memory==128) {
	    	f="0000000";
	    }
	    else if(memory==256) {
	    	f="00000000";
	    }
	    String h=f.substring(Integer.toBinaryString(dec).length())+ Integer.toBinaryString(dec);
	    bstring =h.toCharArray();
	    //System.out.println(bstring.length);
	    i = 0;
	    
	    char[] tag =new char[TAG];
	    for(i=0;i<TAG;i++) {
	    	tag[i]=bstring[i];
	    	//System.out.println(tag[i]);
	    }
	
	    char[] index=new char[INDEX];
	    
	    for(i = TAG; i < INDEX + TAG; i++)
	    {
	        index[i - TAG] = bstring[i];
	        //System.out.println(index[i-TAG]);
	    }
	    
	    char[] offset = new char [OFFSET];
	    
	    for(i = INDEX + TAG; i < OFFSET + INDEX + TAG; i++)
	    	
	    {    
	    	//System.out.println(i-INDEX-TAG);
	        offset[i - INDEX - TAG] = bstring[i];
	        //System.out.println(offset[i-INDEX-TAG]);
	    }
	       int p1=0;
	       
	    
	    	if(INDEX==2) {
	    		p1+=(Character.getNumericValue(index[0])*2)+(Character.getNumericValue(index[1]));
	    	}
	    	else if(INDEX==3) {
	    		p1+=(Character.getNumericValue(index[0])*4)+(Character.getNumericValue(index[1])*2)+(Character.getNumericValue(index[2]));
	    	}
	   
	    //System.out.println(p1);
           Block block = cache.blocks[p1];
//        cache.blocks[p1].tag=tag;
//        cache.blocks[p1].index=index;
//        cache.blocks[p1].offset=offset;
	    cache.writes++;
	    int g=dec%CL;
	    System.out.println("Block will be at cache line:" + " "+ g);
	    return cache;
	    
	}
public static void main(String args[]) throws IOException {
		Reader.init(System.in);
		int S=Reader.nextInt();
		int CL=Reader.nextInt();
		int B=Reader.nextInt();
		
		//int dec=200;
		cache obj=new cache();
		/* Local Variables */
		
		
		int write_policy = 0;
		int counter = 0;
		int index=(int)(Math.log(CL) / Math.log(2)); 
        int offset=(int)(Math.log(B) / Math.log(2)); 
        int tag=((int)(Math.log(memory) / Math.log(2))-index-offset);
        if(tag<=0||index<=0||offset<=0) {
        	System.out.println("Error:Enter appropriate values for creating cache" );
        }
		Cache2 cache = obj.createCache(S,B,CL);
	    FileReader fr=new FileReader("testout1.txt");
		BufferedReader br=new BufferedReader(fr);
		StringBuffer sb=new StringBuffer();
		String line;
		
		while((line=br.readLine())!=null)  
		{  
		sb.append(line);      //appends line to string buffer  
		sb.append("\n");     //line feed   
		}  
		fr.close();    //closes the stream and release the resources  
		System.out.println("Contents of File: ");  
		System.out.println(sb.toString());   //returns a string that textually represents the object  
		String[] lines = sb.toString().split("\\n");
		int[] arr=new int[lines.length];
		for(int i=0;i<lines.length;i++) {
		if(tag>0 && index>0 && offset>0) {
        String[] x=lines[i].split(" ");	
		String mode=x[1];
		 int dec=Integer.valueOf(x[2]);
		 arr[i]=dec;
		    /* Write Policy */
		    if(x[0].compareTo("wt")==0)
		    {
		        write_policy = 0;
		        System.out.println("Write Policy: Write Through\n");
		    }
		    else if(x[0].compareTo("wb")==0)
		    {
		        write_policy = 1;
		        System.out.println("Write Policy: Write Back\n");
		    }
		    
				//address[j] = '\0';

				System.out.println(counter+" "+ mode+" " );

				if(mode.equals("R"))
				{
					int[] arr2=Arrays.copyOfRange(arr, 0, i);
					cache=obj.readFromCache(cache,CL,tag,index,offset,dec,write_policy,arr2);
					
				}
				else if(mode.equals("W"))
				{
					cache=obj.writeToCache(cache,CL,tag,index,offset,dec);
				}
				else
				{
					System.out.println("i:"+" "+ counter +" " +"ERROR");
					//fr.close();
					obj.destroyCache(cache);
					cache = null;

					//return 0;
				}
				counter++;
			
		System.out.println("Num Lines:"+" " +counter);
        System.out.println("CACHE HITS:" +" "+ cache.hits);
		System.out.println("CACHE MISSES:" +" "+ cache.misses);
		System.out.println("MEMORY READS:" +" "+ cache.reads);
		System.out.println("MEMORY WRITES:" +" "+ cache.writes);
		System.out.println("CACHE SIZE:" +" "+ cache.cache_size);
		System.out.println("BLOCK SIZE:" +" "+ cache.block_size);
		System.out.println("NUMBER OF LINES:" +" "+ cache.numLines);
		
		}
		}
		obj.destroyCache(cache);
		
	}
}
