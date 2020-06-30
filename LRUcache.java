import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class LRUcache {
	static int memory=256;
	
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
		Block[] block;
		    
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
	public Cache2 createCache(int cache_size, int block_size,int lines)
	{if(cache_size <= 0)
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
      Cache2 cache = new Cache2();
		cache.hits = 0;
		cache.misses = 0;
		cache.reads = 0;
		cache.writes = 0;
        //cache.write_policy = write_policy;
        cache.cache_size = cache_size;
		cache.block_size = block_size;
        cache.numLines = lines;
        cache.block=null;
		
		return cache;}}
	
	
	void destroyCache(Cache2 cache)
	{if(cache != null)
	    {
	        cache=null; 
	    }return;}
        
public int LRUreplacement(Cache2 cache,int S,int CL,int B,int[] arr1,String[] arr2,String[] arr3,int count) {
		ArrayList<Integer> r=new ArrayList<Integer>();
		//int y=S/CL;
		int[][] arr=new int[CL][1000];
		if(CL==4) {
		int g=0;
		int count0 = 0,count1=0,count2=0,count3=0;
        for(int i=0;i<arr1.length;i++){
        	if(arr3[i].compareTo("wt")==0)
		    {
		        System.out.println("Write Policy: Write Through\n");
		    }
		    else if(arr3[i].compareTo("wb")==0)
		    {
		        cache.writes++;
		        System.out.println("Write Policy: Write Back\n");
		    }
        	
        	if(arr2[i].compareTo("R")==0) {
        		cache.reads++;
        	}
        	else if(arr2[i].compareTo("W")==0) {
        		cache.writes++;
        	}
        if (count!=0 && r.contains(arr1[i])){
         cache.hits++;
         System.out.println(" ");
         System.out.println(count+" "+"Cache obtained a hit ");
         count3++;count2++;count1++;count0++;count++;}
     
      else{
        cache.misses++;
        System.out.println(" ");
        System.out.println(count+" " + "Cache obtained a miss ");
        //System.out.println(count1);
		if(g==0) {
			arr[0][count]=arr1[i];
			r.add(arr1[i]);count0++;g++;}
		else if(g==1) {
			arr[1][count]=arr1[i];
			r.add(arr1[i]);
			count1++;
            count0++;
			g++;
			
		}
		else if(g==2) {
			arr[2][count]=arr1[i];
			r.add(arr1[i]);
			count2++;
            count1++;
            count0++;
			g++;
			
		}
		else if(g==3) {
			arr[3][count]=arr1[i];
			r.add(arr1[i]);
             count3++;
			 count2++;
             count1++;
             count0++;
			g++;
			
		}
		else {
			System.out.println(" ");
			System.out.println(count+" "+"A replacement was carried out ");
			System.out.println("Replaced:"+arr1[i]);
			int a=Math.max(Math.max(count2, count3),Math.max(count0, count1));
			//System.out.println(count0);
			
			//System.out.println(count1);
			if(a==count0) {
			arr[0][count]=arr1[i];
            count0=0;
            int y1=r.remove(0);
            System.out.println("Removed: "+y1);count3++;count2++;count1++;}
			else if(a==count1) {
				arr[1][count]=arr1[i];
				count1=0;int y1=r.remove(0);System.out.println("Removed: "+y1);count0++;count3++;count2++;
			}
			else if(a==count2) {
				arr[2][count]=arr1[i];count2=0;
				int y1=r.remove(0);System.out.println("Removed: "+y1);count1++;count0++;count3++;
			}
			else if(a==count3) {
				arr[3][count]=arr1[i];count3=0;
				int y1=r.remove(0);System.out.println("Removed: "+y1);
            count2++;count1++;count0++;}r.add(arr1[i]);}count++;}
        System.out.println(" ");
        System.out.println("Num Lines:"+" " +count);
        System.out.println("CACHE HITS:" +" "+ cache.hits);
		System.out.println("CACHE MISSES:" +" "+ cache.misses);
		System.out.println("MEMORY READS:" +" "+ cache.reads);
		System.out.println("MEMORY WRITES:" +" "+ cache.writes);
		System.out.println("CACHE SIZE:" +" "+ cache.cache_size);
		System.out.println("BLOCK SIZE:" +" "+ cache.block_size);
		System.out.println("NUMBER OF LINES:" +" "+ cache.numLines);
      }
	}
		else if(CL==16) {
			int g=0;
			int count0 = 0,count1=0,count2=0,count3=0,count4=0, count5=0,count6=0, count7=0,count8 = 0,
					count9=0,count10=0,count11=0,count15=0, count14=0,count12=0, count13=0;
	        for(int i=0;i<arr1.length;i++){
	        	if(arr3[i].compareTo("wt")==0)
			    {
			        System.out.println("Write Policy: Write Through\n");
			    }
			    else if(arr3[i].compareTo("wb")==0)
			    {
			        cache.writes++;
			        System.out.println("Write Policy: Write Back\n");
			    }
	        	
	        	if(arr2[i].compareTo("R")==0) {
	        		cache.reads++;
	        	}
	        	else if(arr2[i].compareTo("W")==0) {
	        		cache.writes++;
	        	}
	        if (count!=0 && r.contains(arr1[i])){
	         cache.hits++;
	         System.out.println(" ");
	         System.out.println(count+" "+"Cache obtained a hit ");
	         count7++;count8++;count9++;count10++;count11++;count12++;count13++;
	         count5++;count14++;count15++;
			 count6++;count5++;
	         count4++;count3++;count2++;count1++;count0++;count++;
	      
	      }
	     
	      else{
	        cache.misses++;
	        System.out.println(" ");
	        System.out.println(count+" " + "Cache obtained a miss ");
	        //System.out.println(count1);
			if(g==0) {
				arr[0][count]=arr1[i];
				r.add(arr1[i]);
				count0++;
				g++;
				
			}
			else if(g==1) {
				arr[1][count]=arr1[i];
				r.add(arr1[i]);
				count1++; count0++;g++;}
			else if(g==2) {
				arr[2][count]=arr1[i];
				r.add(arr1[i]);count2++;count1++;count0++;g++;}
			else if(g==3) {
				arr[3][count]=arr1[i];
				r.add(arr1[i]);
	             count3++;count2++;count1++;count0++;g++;}
			else if(g==4) {
				arr[4][count]=arr1[i];
				r.add(arr1[i]);count4++;count3++;count2++;count1++;
	             count0++;g++;}
			else if(g==5) {
				arr[5][count]=arr1[i];
				r.add(arr1[i]);count5++;count4++;count3++;count2++;
	             count1++;count0++;g++;}
			else if(g==6) {
				arr[6][count]=arr1[i];
				 count6++;count5++;
				 count4++;count3++;
				 count2++;count1++;count0++;g++;}
				
			else if(g==7) {
				arr[7][count]=arr1[i];
				r.add(arr1[i]);
				 count7++;
				 count6++;count5++;
				 count4++;count3++;
				 count2++;count1++;count0++;g++;
				
			}
			else if(g==8) {
				arr[8][count]=arr1[i];
				r.add(arr1[i]);
				 count7++;count8++;
				 count6++;count5++;
				 count4++;count3++;
				 count2++;count1++;count0++;g++;
				
			}
			else if(g==9) {
				arr[9][count]=arr1[i];r.add(arr1[i]);
				count7++;count8++;count6++;count9++;
				 count5++;count4++;count3++;
				 count2++;count1++;count0++;g++;
				
			}
			else if(g==10) {
				arr[10][count]=arr1[i];
				r.add(arr1[i]);
				 count7++;count8++;count6++;count9++;
				 count5++;count10++;count4++;
	             count3++;count2++;count1++;count0++;g++;}	
				else if(g==11) {
				arr[11][count]=arr1[i];
				r.add(arr1[i]);
				 count7++;count8++;count6++;count9++;
				 count5++;count10++;count4++;count11++;
	             count3++;count2++;count1++;count0++;g++;}
			else if(g==12) {
				arr[12][count]=arr1[i];
				r.add(arr1[i]);
				count7++;count8++;count6++;count9++;count5++;count10++;
				 count4++;count11++;
	             count3++;count12++;count2++;count1++;count0++;g++;}
			else if(g==13) {
				arr[13][count]=arr1[i];
				r.add(arr1[i]);
				count7++;count8++;count6++;count9++;count5++;count10++;
				 count4++;count11++;count3++;count12++;
				 count2++;count13++;count1++;count0++;g++;}
			else if(g==14) {
				arr[14][count]=arr1[i];
				r.add(arr1[i]);
				count7++;count8++;count14++;count6++;count9++;
				 count5++;count10++;count4++;count11++;
	             count3++;count12++;count2++;count13++;count1++;count0++;g++;}
			else if(g==15) {
				arr[15][count]=arr1[i];
				r.add(arr1[i]);
				 count7++;count8++;count15++;
				 count6++;count9++;count14++;count5++;count10++;
				 count4++;count11++;count3++;count12++;
				 count2++;count13++;count1++;count0++;
				 g++;
				
			}
			else {
				System.out.println(" ");
				System.out.println(count+" "+"A replacement was carried out ");
				System.out.println("Replaced:"+arr1[i]);
				int b=Math.max(Math.max(Math.max(count8, count9),Math.max(count10, count11)),Math.max(Math.max(count12, count13),Math.max(count14, count15)));
				int a=Math.max(Math.max(Math.max(count4, count5),Math.max(count6, count7)),Math.max(Math.max(count2, count3),Math.max(count0, count1)));
				a=Math.max(a, b);
				if(a==count0) {
				arr[0][count]=arr1[i];
	            count0=0;
	            int y1=r.remove(0);
	            System.out.println("Removed: "+y1);
				 count7++;count8++;count15++;
				 count6++;count9++;count14++;
				 count5++;count10++;count4++;count11++;
	             count3++;count12++;count2++;count13++;count1++;
					
				}
				else if(a==count1) {
					arr[1][count]=arr1[i];
					count1=0;
					int y1=r.remove(0);
					System.out.println("Removed: "+y1);
					 count7++;count8++;count15++;count6++;count9++;count14++;
					 count5++;count10++;count4++;count11++;
		             count3++;count12++;count2++;count13++;count0++;
				}
				else if(a==count2) {
					arr[2][count]=arr1[i];
					count2=0;
					int y1=r.remove(0);
					System.out.println("Removed: "+y1);
					 count7++;count8++;count15++;
					 count6++;count9++;count5++;count10++;
					 count4++;count11++;count3++;count12++;
					 count13++;count14++; count1++;count0++;
				}
				else if(a==count3) {
					arr[3][count]=arr1[i];
					count3=0;
					int y1=r.remove(0);
					System.out.println("Removed: "+y1);
					 count7++;count8++;count15++;
					 count6++;count9++;count5++;count10++;count4++;count11++;
		             count12++;count14++;count13++;count1++;count0++;
				}
				else if(a==count4) {
					arr[4][count]=arr1[i];
					count4=0;int y1=r.remove(0);System.out.println("Removed: "+y1);
					 count7++;count8++;count15++;
					 count6++;count9++;count5++;count10++;
					 count11++;count14++;count3++;count12++;count2++;count13++;
		             count1++;count0++;
				}
				else if(a==count5) {
					arr[5][count]=arr1[i];
					count5=0;
					int y1=r.remove(0);
					System.out.println("Removed: "+y1);
					count7++;count8++;count15++;
					 count6++;count9++;count10++;count14++;count4++;count11++;
		             count3++;count12++;count2++;count13++;count1++;count0++;
				}
				else if(a==count6) {
					arr[6][count]=arr1[i];
					count6=0;
					int y1=r.remove(0);
					System.out.println("Removed: "+y1);
					count7++;count8++;count15++;
					 count9++;count5++;count10++;
					 count4++;count11++;count14++;count3++;count12++;
					 count2++;count13++;
		             count1++;count0++;
				}
				else if(a==count7) {
					arr[7][count]=arr1[i];
					count7=0;
					int y1=r.remove(0);
					System.out.println("Removed: "+y1);
					count8++;count15++;
					 count6++;count9++;count14++;
					 count5++;count10++;
					 count4++;count11++;
		             count3++;count12++;
					 count2++;count13++;
		             count1++;count0++;
				}
				else if(a==count8) {
					arr[8][count]=arr1[i];
					count8=0;
					int y1=r.remove(0);
					System.out.println("Removed: "+y1);
					count7++;count15++;
					 count6++;count9++;count14++;
					 count5++;count10++;
					 count4++;count11++;
		             count3++;count12++;
					 count2++;count13++;
		             count1++;count0++;
				}
				else if(a==count9) {
					arr[9][count]=arr1[i];
					count9=0;
					int y1=r.remove(0);
					System.out.println("Removed: "+y1);
					count7++;count8++;count15++;
					 count6++;count14++;count5++;count10++;count4++;count11++;count3++;count12++;
					 count2++;count13++;count1++;count0++;}
				else if(a==count10) {
					arr[10][count]=arr1[i];
					count10=0;
					int y1=r.remove(0);System.out.println("Removed: "+y1);
					count7++;count8++;count15++;
					 count6++;count9++;count14++;
					 count5++;count10++;count4++;count11++;count3++;count12++;
					 count2++;count13++;count1++;count0++;}
				else if(a==count11) {
					arr[11][count]=arr1[i];
					count11=0;
					int y1=r.remove(0);
					System.out.println("Removed: "+y1);
					count7++;count8++;count15++;
					 count6++;count9++;count5++;count10++;
					 count4++;count14++;count3++;count12++;
					 count2++;count13++;count1++;count0++;}
				else if(a==count14) {
					arr[14][count]=arr1[i];
					count14=0;
					int y1=r.remove(0);
					System.out.println("Removed: "+y1);
					count7++;count8++;count15++;count6++;count9++;
					 count5++;count10++;count4++;count11++;
		             count3++;count12++;count2++;count13++;count1++;count0++;
				}
				else if(a==count13) {
					arr[13][count]=arr1[i];
					count13=0;
					int y1=r.remove(0);
					System.out.println("Removed: "+y1);
					count0++;count7++;count4++;count5++;count6++;
	                count3++;count8++;count10++;count14++;count12++;
				    count2++;count9++;count11++;count15++;
				}
				else if(a==count15) {
					arr[15][count]=arr1[i];
					count15=0;
					int y1=r.remove(0);
					System.out.println("Removed: "+y1);
					count0++;count7++;count4++;count5++;count6++;
	                count3++;count8++;count10++;count14++;count12++;
				    count2++;count9++;count11++;count13++;
				}
				else if(a==count12) {
					arr[12][count]=arr1[i];
					count12=0;
					int y1=r.remove(0);
					System.out.println("Removed: "+y1);
					count0++;count7++;count4++;count5++;count6++;
	                count3++;count8++;count10++;count14++;count15++;
				    count2++;count9++;count11++;count13++;}r.add(arr1[i]);}count++;}
	        System.out.println(" ");
	        System.out.println("Num Lines:"+" " +count);
	        System.out.println("CACHE HITS:" +" "+ cache.hits);
			System.out.println("CACHE MISSES:" +" "+ cache.misses);
			System.out.println("MEMORY READS:" +" "+ cache.reads);
			System.out.println("MEMORY WRITES:" +" "+ cache.writes);
			System.out.println("CACHE SIZE:" +" "+ cache.cache_size);
			System.out.println("BLOCK SIZE:" +" "+ cache.block_size);
			System.out.println("NUMBER OF LINES:" +" "+ cache.numLines);
	      }
		}
		else if(CL==8) {
			int g=0;
			int count0 = 0,count1=0,count2=0,count3=0,count4=0, count5=0,count6=0, count7=0;
	        for(int i=0;i<arr1.length;i++){
	        	if(arr3[i].compareTo("wt")==0)
			    {
			        System.out.println("Write Policy: Write Through\n");
			    }
			    else if(arr3[i].compareTo("wb")==0)
			    {
			        cache.writes++;
			        System.out.println("Write Policy: Write Back\n");
			    }
	        	
	        	if(arr2[i].compareTo("R")==0) {
	        		cache.reads++;
	        	}
	        	else if(arr2[i].compareTo("W")==0) {
	        		cache.writes++;
	        	}
	        if (count!=0 && r.contains(arr1[i])){
	         cache.hits++;
	         System.out.println(" ");
	         System.out.println(count+" "+"Cache obtained a hit ");
	         count7++;count6++;count5++;count4++;count3++;
			 count2++;count1++;count0++;count++;}
	     else{
	        cache.misses++;
	        System.out.println(" ");
	        System.out.println(count+" " + "Cache obtained a miss ");
	        //System.out.println(count1);
			if(g==0) {
				arr[0][count]=arr1[i];r.add(arr1[i]);count0++;g++;
				
			}
			else if(g==1) {
				arr[1][count]=arr1[i];r.add(arr1[i]);count1++;count0++;g++;
				
			}
			else if(g==2) {
				arr[2][count]=arr1[i];
				r.add(arr1[i]);
				count2++;
	            count1++;
	            count0++;
				g++;
				
			}
			else if(g==3) {
				arr[3][count]=arr1[i];
				r.add(arr1[i]);count3++;count2++;count1++;count0++;g++;
				
			}
			else if(g==4) {
				arr[4][count]=arr1[i];
				r.add(arr1[i]);
				 count4++;count3++;count2++;count1++;count0++;g++;
				
			}
			else if(g==5) {
				arr[5][count]=arr1[i];r.add(arr1[i]);count5++;count4++;
	            count3++;count2++;count1++;count0++;g++;
				
			}
			else if(g==6) {
				arr[6][count]=arr1[i];
				r.add(arr1[i]);
				 count6++;count5++;count4++;
	             count3++;count2++;count1++; count0++;g++;
				
			}
			else if(g==7) {
				arr[7][count]=arr1[i];r.add(arr1[i]);
				 count7++;
				 count6++;count5++;count4++;
	             count3++;count2++;count1++;count0++;g++;
				
			}
			else {
				System.out.println(" ");
				System.out.println(count+" "+"A replacement was carried out ");
				System.out.println("Replaced:"+arr1[i]);
				int a=Math.max(Math.max(Math.max(count4, count5),Math.max(count6, count7)),Math.max(Math.max(count2, count3),Math.max(count0, count1)));
				if(a==count0) {arr[0][count]=arr1[i];
	            count0=0;int y1=r.remove(0);System.out.println("Removed: "+y1);
	            count4++;count5++;count6++;
	            count3++;count7++;count2++;count1++;
					
				}
				else if(a==count1) {
					arr[1][count]=arr1[i];
					count1=0;
					int y1=r.remove(0);
					System.out.println("Removed: "+y1);
					count4++;count5++;count6++;
		            count3++;count7++;
					count2++;
		            count0++;
				}
				else if(a==count2) {
					arr[2][count]=arr1[i];
					count2=0;
					int y1=r.remove(0);
					System.out.println("Removed: "+y1);
					count4++;count5++;count6++;
		            count3++;count7++;
					count0++;
		            count1++;
				}
				else if(a==count4) {
					arr[4][count]=arr1[i];
					count4=0;
					int y1=r.remove(0);System.out.println("Removed: "+y1);
					count5++;count6++;
		            count3++;count7++;
					count2++;
		            count1++;
	                count0++;
				}
				else if(a==count5) {
					arr[5][count]=arr1[i];
					count5=0;
					int y1=r.remove(0);
					System.out.println("Removed: "+y1);
					count4++;count0++;count6++;
		            count3++;count7++;
					count2++;
		            count1++;
				}
				else if(a==count6) {
					arr[6][count]=arr1[i];
					count6=0;int y1=r.remove(0);
					System.out.println("Removed: "+y1);
					count4++;count5++;count0++;
		            count3++;count7++;count2++;count1++;
				}
				else if(a==count7) {
					arr[7][count]=arr1[i];
					count7=0;int y1=r.remove(0);
					System.out.println("Removed: "+y1);
					count4++;count5++;count6++;
		            count3++;count0++;count2++;count1++;
				}r.add(arr1[i]);}count++;}
	        System.out.println(" ");
	        System.out.println("Num Lines:"+" " +count);
	        System.out.println("CACHE HITS:" +" "+ cache.hits);
			System.out.println("CACHE MISSES:" +" "+ cache.misses);
			System.out.println("MEMORY READS:" +" "+ cache.reads);
			System.out.println("MEMORY WRITES:" +" "+ cache.writes);
			System.out.println("CACHE SIZE:" +" "+ cache.cache_size);
			System.out.println("BLOCK SIZE:" +" "+ cache.block_size);
			System.out.println("NUMBER OF LINES:" +" "+ cache.numLines);
	      }}return count;}
   
	public static void main(String args[]) throws IOException {
		Reader.init(System.in);
		int S=Reader.nextInt();
		int CL=Reader.nextInt();
		int B=Reader.nextInt();
		LRUcache obj=new LRUcache();
		int offset=(int)(Math.log(B) / Math.log(2)); 
        int tag=((int)(Math.log(memory) / Math.log(2))-offset);
        if(tag<=0||offset<=0) {
        	System.out.println("Error:Enter appropriate values for creating cache" );}
		Cache2 cache = obj.createCache(S,B,CL);
		FileReader fr=new FileReader("testout.txt");
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
		String[] arr1=new String[lines.length];
		String[] arr2=new String[lines.length];
		int count=0;
		for(int i=0;i<lines.length;i++) {
			if(tag>0 && offset>0) {
	        String[] x=lines[i].split(" ");	
			arr1[i]=x[1];
			arr2[i]=x[0];
			int dec=Integer.valueOf(x[2]);
			arr[i]=dec;
			
		}
		}
		count=obj.LRUreplacement(cache,S, CL, B, arr,arr1,arr2, count);
		obj.destroyCache(cache);
	}
}


