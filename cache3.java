import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class cache3 {
	static int memory=32;  //memory size is taken in words

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


	public Cache2 createCache(int cache_size, int block_size,int lines)
	{
		/* Local Variables */
		/* Validate Inputs */
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
		/* Lets make a cache! */
		Cache2 cache = new Cache2();
		cache.hits = 0;
		cache.misses = 0;
		cache.reads = 0;
		cache.writes = 0;
		//cache.write_policy = write_policy;
		cache.cache_size = cache_size;
		cache.block_size = block_size;
		cache.numLines = lines;
		cache.blocks=null;

		return cache;
	}


	void destroyCache(Cache2 cache)
	{

		if(cache != null)
		{
			cache=null; 
		}
		return;
	}

	public void LRUreplacement(Cache2 cache,int S,int CL,int B,int[] arr1,String[] arr2,String[] arr3,int count,int k) {
		ArrayList<Integer> r=new ArrayList<Integer>();
		ArrayList<Integer> r1=new ArrayList<Integer>();
		ArrayList<Integer> r2=new ArrayList<Integer>();
		ArrayList<Integer> r3=new ArrayList<Integer>();
		//int y=S/CL;
		int[][] arr=new int[CL][1000];
		int g=0;
		int g1=0;
		int g2=0;
		int g3=0;
		int count0 = 0,count1=0,count2=0,count3=0,count4 = 0,count5=0,count6=0,count7=0;
		int count8 = 0,count9=0,count10=0,count11=0,count14 = 0,count15=0,count12=0,count13=0;
		//int p=CL/k;

		for(int i=0;i<arr1.length;i++){
			if(CL==4 && k==2) {
				int set_no=arr1[i]%k;
				System.out.println("set_no:"+set_no);
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
				if (count!=0 && (r.contains(arr1[i])||r1.contains(arr1[i]))){
					cache.hits++;
					System.out.println(" ");
					System.out.println(count+" "+"Cache obtained a hit ");
					if(k==2) {
						if(r.contains(arr1[i])) {
							count1++;
							count0++; 
						}
						else if(r1.contains(arr1[i])) {
							count3++;
							count2++;}}count++;}

				else{
					cache.misses++;
					System.out.println(" ");
					System.out.println(count+" " + "Cache obtained a miss ");
					//System.out.println(count1);
					if(set_no==0 && g==0) {
						arr[0][count]=arr1[i];
						r.add(arr1[i]);
						count0++;
						g++;
					}
					else if(set_no==0 && g==1) {
						arr[1][count]=arr1[i];
						r.add(arr1[i]);
						count0++;
						count1++;
						g++;
					}
					else if(set_no==1 && g1==0) {
						arr[2][count]=arr1[i];
						r1.add(arr1[i]);

						count2++;
						g1++;
					}
					else if(set_no==1 && g1==1) {
						arr[3][count]=arr1[i];
						r1.add(arr1[i]);

						count2++;
						count3++;
						g1++;
					}

					else {
						System.out.println(" ");
						System.out.println(count+" "+"A replacement was carried out ");
						System.out.println("Replaced:"+arr1[i]);
						int m=arr1[i]%k;
						int a=0;
						if(m==0) {
							a= Math.max(count0, count1);if(a==count0) {
								arr[0][count]=arr1[i];
								count0=0;int y1=r.remove(0);System.out.println("Removed: "+y1);
								count1++;r.add(arr1[i]);}else if(a==count1) {
									arr[1][count]=arr1[i];
									count1=0;int y1=r.remove(0);
									System.out.println("Removed: "+y1);
									count0++;r.add(arr1[i]);

								}
						}
						else if(m==1) {
							a=Math.max(count2, count3);if(a==count2) {arr[2][count]=arr1[i];
							count2=0;int y1=r1.remove(0);
							System.out.println("Removed: "+y1);count3++;r1.add(arr1[i]);
							}else if(a==count3) {
								arr[3][count]=arr1[i];
								count3=0;int y1=r1.remove(0);System.out.println("Removed: "+y1);count2++;r1.add(arr1[i]);

							}
						}

					}count++;}
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

			else if(k==2 && CL==8) {
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

				if (count!=0 && (r.contains(arr1[i])||r1.contains(arr1[i])||r2.contains(arr1[i])||r3.contains(arr1[i]))){
					cache.hits++;
					System.out.println(" ");
					System.out.println(count+" "+"Cache obtained a hit ");
					if(r.contains(arr1[i])) {count0++;count1++;}
					else if(r1.contains(arr1[i])) {count3++;count2++;}
					else if(r2.contains(arr1[i])) {count4++;count5++;}
					else if(r3.contains(arr1[i])) {count6++;count7++;}
					count++;
				} 
				else{
					cache.misses++;
					System.out.println(" ");
					System.out.println(count+" " + "Cache obtained a miss ");
					String f="";
					if(memory==64) {
						f="000000";
					}
					else if(memory==128) {
						f="0000000";
					}
					else if(memory==256) {
						f="00000000";
					}
					int set_no=CL/k;
					int setbits=(int)(Math.log(set_no)/Math.log(2));
					String j=f.substring(Integer.toBinaryString(arr1[i]).length())+ Integer.toBinaryString(arr1[i]);
					int offset=(int)(Math.log(B) / Math.log(2));
					//String offset1=j.substring(j.length()-offset,j.length()-1);
					String set=j.substring(j.length()-offset-setbits,j.length()-offset);
					int set1=Integer.parseInt(set,2);
					System.out.println("set no:"+set1);
					if(set1==0 && g==0) {arr[0][count]=arr1[i];
					r.add(arr1[i]);count0++;g++;}

					else if(set1==0 && g==1){arr[1][count]=arr1[i];
					r.add(arr1[i]);count0++;count1++;g++;}


					else if(set1==1 && g1==0) {arr[2][count]=arr1[i];
					r1.add(arr1[i]);count2++;g1++;}

					else if(set1==1 && g1==1){arr[3][count]=arr1[i];
					r1.add(arr1[i]);count2++;count3++;g1++;}


					else if(set1==2 && g2==0) {arr[4][count]=arr1[i];
					r2.add(arr1[i]);count4++;g2++;}
					else if(set1==1 && g2==1){arr[5][count]=arr1[i];
					r2.add(arr1[i]);count4++;count5++;g2++;}


					else if(set1==3 && g3==0) {arr[4][count]=arr1[i];
					r3.add(arr1[i]);count4++;g3++;}
					else if(set1==3 && g3==1){arr[5][count]=arr1[i];}

					else {
						System.out.println(" ");
						System.out.println(count+" "+"A replacement was carried out ");
						System.out.println("Replaced:"+arr1[i]);
						int a;
						if(set1==0) {a=Math.max(count0,count1);
						if(a==count0) {arr[0][count]=arr1[i];count0=0;int y1=r.remove(0);
						System.out.println("Removed: "+y1);count1++;r.add(arr1[i]);}
						else if(a==count1) {arr[1][count]=arr1[i];count1=0;int y1=r.remove(0);
						System.out.println("Removed: "+y1);
						count0++;r.add(arr1[i]);}} 
						else if(set1==1) 
						{a=Math.max(count2,count3);if(a==count2) {arr[2][count]=arr1[i];count2=0;int y1=r1.remove(0);
						System.out.println("Removed: "+y1);r1.add(arr1[i]);count3++;}else if(a==count3) {arr[3][count]=arr1[i];count3=0;int y1=r1.remove(0);
						System.out.println("Removed: "+y1);r1.add(arr1[i]);count2++;}}
						else if(set1==2) {a=Math.max(count4,count5);
						if(a==count4) {arr[4][count]=arr1[i];count4=0;int y1=r2.remove(0);
						System.out.println("Removed: "+y1);r2.add(arr1[i]);count5++;}else if(a==count5) {arr[5][count]=arr1[i];count5=0;int y1=r2.remove(0);
						System.out.println("Removed: "+y1);r2.add(arr1[i]);count4++;}}
						else if(set1==4) {a=Math.max(count6,count7);
						if(a==count6) {arr[6][count]=arr1[i];count6=0;int y1=r3.remove(0);
						System.out.println("Removed: "+y1);r3.add(arr1[i]);count7++;}else if(a==count7) {arr[7][count]=arr1[i];count7=0;int y1=r2.remove(0);
						System.out.println("Removed: "+y1);r3.add(arr1[i]);count6++;}}}


				}
				count++;
				System.out.println(" ");

				System.out.println("CACHE HITS:" +" "+ cache.hits);
				System.out.println("CACHE MISSES:" +" "+ cache.misses);
				System.out.println("MEMORY READS:" +" "+ cache.reads);
				System.out.println("MEMORY WRITES:" +" "+ cache.writes);
				System.out.println("CACHE SIZE:" +" "+ cache.cache_size);
				System.out.println("BLOCK SIZE:" +" "+ cache.block_size);
				System.out.println("NUMBER OF LINES:" +" "+ cache.numLines);
			}
			else if(k==4 && CL==8) {

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

				if (count!=0 && (r.contains(arr1[i])||r1.contains(arr1[i]))){
					cache.hits++;
					System.out.println(" ");
					System.out.println(count+" "+"Cache obtained a hit ");
					if(r.contains(arr1[i])) {count0++;count1++;count3++;count2++;}
					else if(r1.contains(arr1[i])) {count4++;count5++;count6++;count7++;}count++;
				} 
				else{
					cache.misses++;
					System.out.println(" ");
					System.out.println(count+" " + "Cache obtained a miss ");
					String f="";
					if(memory==64) {
						f="000000";
					}
					else if(memory==128) {
						f="0000000";
					}
					else if(memory==256) {
						f="00000000";
					}
					int set_no=CL/k;
					int setbits=(int)(Math.log(set_no)/Math.log(2));
					String j=f.substring(Integer.toBinaryString(arr1[i]).length())+ Integer.toBinaryString(arr1[i]);
					int offset=(int)(Math.log(B) / Math.log(2));
					//String offset1=j.substring(j.length()-offset,j.length()-1);
					String set=j.substring(j.length()-offset-setbits,j.length()-offset);
					int set1=Integer.parseInt(set,2);
					System.out.println("set no:"+set1);
					if(set1==0 && g==0) {arr[0][count]=arr1[i];
					r.add(arr1[i]);count0++;g++;}else if(set1==0 && g==1){arr[1][count]=arr1[i];
					r.add(arr1[i]);count0++;count1++;g++;}else if(set1==0 && g==2){arr[2][count]=arr1[i];
					r.add(arr1[i]);count0++;count1++;count2++;g++;}else if(set1==0 && g==3){arr[3][count]=arr1[i];
					r.add(arr1[i]);count0++;count1++;count2++;count3++;g++;}
					else if(set1==1 && g1==0) {arr[4][count]=arr1[i];r1.add(arr1[i]);count4++;g1++;}
					else if(set1==1 && g1==1) {arr[5][count]=arr1[i];r1.add(arr1[i]);count5++;count4++;g1++;}
					else if(set1==1 && g1==2) {arr[6][count]=arr1[i];r1.add(arr1[i]);
					count6++;count5++;count4++;g1++;}else if(set1==1 && g1==3) {arr[7][count]=arr1[i];r1.add(arr1[i]);
					count7++;count6++;count5++;count4++;g1++;}
					else {System.out.println(" ");
					System.out.println(count+" "+"A replacement was carried out ");
					System.out.println("Replaced:"+arr1[i]);
					int a=0;
					if(set1==0) {a=Math.max(Math.max(count0,count1),Math.max(count2,count3));
					if(a==count0) {arr[0][count]=arr1[i];count0=0;int y1=r.remove(0);
					System.out.println("Removed: "+y1);count1++;count2++;count3++;r.add(arr1[i]);}else if(a==count1) {arr[1][count]=arr1[i];count1=0;
					int y1=r.remove(0);System.out.println("Removed: "+y1);count0++;count2++;count3++;r.add(arr1[i]);}}
					else if(a==count2) {arr[2][count]=arr1[i];count2=0;int y1=r.remove(0);
					System.out.println("Removed: "+y1);count1++;count0++;count3++;r.add(arr1[i]);}else if(a==count3) {arr[3][count]=arr1[i];count3=0;
					int y1=r.remove(0);System.out.println("Removed: "+y1);count0++;count2++;count1++;r.add(arr1[i]);}
					else if(set1==1) {{a=Math.max(Math.max(count4,count5),Math.max(count6,count7));
					if(a==count4) {arr[4][count]=arr1[i];count4=0;int y1=r1.remove(0);
					System.out.println("Removed: "+y1);count5++;count6++;count7++;r1.add(arr1[i]);}else if(a==count5) {arr[5][count]=arr1[i];count5=0;
					int y1=r1.remove(0);System.out.println("Removed: "+y1);count4++;count6++;count7++;r1.add(arr1[i]);}
					else if(a==count6) {arr[6][count]=arr1[i];count6=0;int y1=r1.remove(0);
					System.out.println("Removed: "+y1);count5++;count7++;count4++;r1.add(arr1[i]);}else if(a==count7) {arr[7][count]=arr1[i];count7=0;
					int y1=r1.remove(0);System.out.println("Removed: "+y1);count4++;count5++;count6++;r1.add(arr1[i]);}}
					}
					count++;
					System.out.println(" ");

					System.out.println("CACHE HITS:" +" "+ cache.hits);
					System.out.println("CACHE MISSES:" +" "+ cache.misses);
					System.out.println("MEMORY READS:" +" "+ cache.reads);
					System.out.println("MEMORY WRITES:" +" "+ cache.writes);
					System.out.println("CACHE SIZE:" +" "+ cache.cache_size);
					System.out.println("BLOCK SIZE:" +" "+ cache.block_size);
					System.out.println("NUMBER OF LINES:" +" "+ cache.numLines);
					}}}
				else if(k==4 && CL==16) {
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
					if (count!=0 && (r.contains(arr1[i])||r1.contains(arr1[i])||r2.contains(arr1[i])||r3.contains(arr1[i]))){
						cache.hits++;
						System.out.println(" ");
						System.out.println(count+" "+"Cache obtained a hit ");
						if(r.contains(arr1[i])) {count0++;count1++;count3++;count2++;}
						else if(r1.contains(arr1[i])) {count4++;count5++;count6++;count7++;}
						else if(r2.contains(arr1[i])) {count8++;count9++;count10++;count11++;}
						else if(r3.contains(arr1[i])) {count13++;count15++;count12++;count14++;}
						count++;}
					else{
						cache.misses++;
						System.out.println(" ");
						System.out.println(count+" " + "Cache obtained a miss ");
						String f="";
						if(memory==64) {
							f="000000";
						}
						else if(memory==128) {
							f="0000000";
						}
						else if(memory==256) {
							f="00000000";
						}
						int set_no=CL/k;
						int setbits=(int)(Math.log(set_no)/Math.log(2));
						String j=f.substring(Integer.toBinaryString(arr1[i]).length())+ Integer.toBinaryString(arr1[i]);
						int offset=(int)(Math.log(B) / Math.log(2));
						//String offset1=j.substring(j.length()-offset,j.length()-1);
						String set=j.substring(j.length()-offset-setbits,j.length()-offset);
						int set1=Integer.parseInt(set,2);
						System.out.println("set no:"+set1);
						if(set1==0 && g==0) {arr[0][count]=arr1[i];
						r.add(arr1[i]);count0++;g++;}else if(set1==0 && g==1){arr[1][count]=arr1[i];
						r.add(arr1[i]);count0++;count1++;g++;}else if(set1==0 && g==2){arr[2][count]=arr1[i];
						r.add(arr1[i]);count0++;count1++;count2++;g++;}else if(set1==0 && g==3){arr[3][count]=arr1[i];
						r.add(arr1[i]);count0++;count1++;count2++;count3++;g++;}
						else if(set1==1 && g1==0) {arr[4][count]=arr1[i];r1.add(arr1[i]);count4++;g1++;}
						else if(set1==1 && g1==1) {arr[5][count]=arr1[i];r1.add(arr1[i]);count5++;count4++;g1++;}
						else if(set1==1 && g1==2) {arr[6][count]=arr1[i];r1.add(arr1[i]);
						count6++;count5++;count4++;g1++;}else if(set1==1 && g1==3) {arr[7][count]=arr1[i];r1.add(arr1[i]);
						count7++;count6++;count5++;count4++;g1++;}else if(set1==2 && g2==0) {arr[8][count]=arr1[i];r2.add(arr1[i]);count8++;g2++;}
						else if(set1==2 && g2==1) {arr[9][count]=arr1[i];r2.add(arr1[i]);count8++;count9++;g2++;}
						else if(set1==2 && g2==2) {arr[10][count]=arr1[i];r2.add(arr1[i]);
						count10++;count9++;count8++;g2++;}else if(set1==2 && g1==3) {arr[11][count]=arr1[i];r2.add(arr1[i]);
						count11++;count10++;count9++;count8++;g2++;}
						else if(set1==3 && g3==0) {arr[12][count]=arr1[i];r3.add(arr1[i]);count12++;g3++;}
						else if(set1==3 && g3==1) {arr[13][count]=arr1[i];r3.add(arr1[i]);count12++;count13++;g3++;}
						else if(set1==3 && g3==2) {arr[14][count]=arr1[i];r3.add(arr1[i]);
						count12++;count3++;count4++;g2++;}else if(set1==3 && g3==3) {arr[15][count]=arr1[i];r3.add(arr1[i]);
						count15++;count13++;count12++;count14++;g2++;}

						else {
							System.out.println(" ");
						System.out.println(count+" "+"A replacement was carried out ");
						System.out.println("Replaced:"+arr1[i]);
						int a=0;
						if(set1==0) {a=Math.max(Math.max(count0,count1),Math.max(count2,count3));
						if(a==count0) {arr[0][count]=arr1[i];count0=0;int y1=r.remove(0);
						System.out.println("Removed: "+y1);count1++;count2++;count3++;r.add(arr1[i]);}else if(a==count1) {arr[1][count]=arr1[i];count1=0;
						int y1=r.remove(0);System.out.println("Removed: "+y1);count0++;count2++;count3++;r.add(arr1[i]);}}
						else if(a==count2) {arr[2][count]=arr1[i];count2=0;int y1=r.remove(0);
						System.out.println("Removed: "+y1);count1++;count0++;count3++;r.add(arr1[i]);}else if(a==count3) {arr[3][count]=arr1[i];count3=0;
						int y1=r.remove(0);System.out.println("Removed: "+y1);count0++;count2++;count1++;r.add(arr1[i]);}
						else if(set1==1) {{a=Math.max(Math.max(count4,count5),Math.max(count6,count7));
						if(a==count4) {arr[4][count]=arr1[i];count4=0;int y1=r1.remove(0);
						System.out.println("Removed: "+y1);count5++;count6++;count7++;r1.add(arr1[i]);}else if(a==count5) {arr[5][count]=arr1[i];count5=0;
						int y1=r1.remove(0);System.out.println("Removed: "+y1);count4++;count6++;count7++;r1.add(arr1[i]);}
						else if(a==count6) {arr[6][count]=arr1[i];count6=0;int y1=r1.remove(0);
						System.out.println("Removed: "+y1);count5++;count7++;count4++;r1.add(arr1[i]);}else if(a==count7) {arr[7][count]=arr1[i];count7=0;
						int y1=r1.remove(0);System.out.println("Removed: "+y1);count4++;count5++;count6++;r1.add(arr1[i]);}}}

						else if(set1==2) {{a=Math.max(Math.max(count9,count8),Math.max(count10,count11));
						if(a==count8) {arr[8][count]=arr1[i];count8=0;int y1=r2.remove(0);
						System.out.println("Removed: "+y1);count9++;count10++;count11++;r2.add(arr1[i]);}else if(a==count9) {arr[9][count]=arr1[i];count9=0;
						int y1=r2.remove(0);System.out.println("Removed: "+y1);count10++;count11++;count8++;r2.add(arr1[i]);}
						else if(a==count10) {arr[10][count]=arr1[i];count10=0;int y1=r1.remove(0);
						System.out.println("Removed: "+y1);count11++;count9++;count8++;r2.add(arr1[i]);}else if(a==count11) {arr[11][count]=arr1[i];count11=0;
						int y1=r2.remove(0);System.out.println("Removed: "+y1);count10++;count9++;count8++;r2.add(arr1[i]);}}}


						else if(set1==3) {{a=Math.max(Math.max(count12,count13),Math.max(count14,count15));
						if(a==count12) {arr[12][count]=arr1[i];count12=0;int y1=r3.remove(0);
						System.out.println("Removed: "+y1);count13++;count14++;count15++;r3.add(arr1[i]);}else if(a==count13) {arr[13][count]=arr1[i];count13=0;
						int y1=r3.remove(0);System.out.println("Removed: "+y1);count14++;count12++;count15++;r3.add(arr1[i]);}}}
						else if(a==count14) {arr[14][count]=arr1[i];count14=0;int y1=r3.remove(0);
						System.out.println("Removed: "+y1);count15++;count12++;count13++;r3.add(arr1[i]);}else if(a==count15) {arr[15][count]=arr1[i];count15=0;
						int y1=r3.remove(0);System.out.println("Removed: "+y1);count14++;count13++;count12++;r3.add(arr1[i]);}}}
					count++;
					System.out.println(" ");

					System.out.println("CACHE HITS:" +" "+ cache.hits);
					System.out.println("CACHE MISSES:" +" "+ cache.misses);
					System.out.println("MEMORY READS:" +" "+ cache.reads);
					System.out.println("MEMORY WRITES:" +" "+ cache.writes);
					System.out.println("CACHE SIZE:" +" "+ cache.cache_size);
					System.out.println("BLOCK SIZE:" +" "+ cache.block_size);
					System.out.println("NUMBER OF LINES:" +" "+ cache.numLines);
				}}}
		public static void main(String args[]) throws IOException {
			Reader.init(System.in);
			int S=Reader.nextInt();
			int CL=Reader.nextInt();
			int B=Reader.nextInt();
			int k=Reader.nextInt();
			cache3 obj=new cache3();
			int no_sets=CL/k;
			int offset=(int)(Math.log(B) / Math.log(2)); 
			int set_no=(int)(Math.log(no_sets)/Math.log(2));
			int tag=((int)(Math.log(memory) / Math.log(2))-set_no-offset);
			if(tag<=0||set_no<=0||offset<=0) {
				System.out.println("Error:Enter appropriate values for creating cache" );
			}
			Cache2 cache = obj.createCache(S,B,CL);
			FileReader fr=new FileReader("testout.txt");
			BufferedReader br=new BufferedReader(fr);
			StringBuffer sb=new StringBuffer();
			String line;

			while((line=br.readLine())!=null)  
			{  
				sb.append(line);      
				sb.append("\n");      
			}  
			fr.close();     
			System.out.println("Contents of File: ");  
			System.out.println(sb.toString());   
			String[] lines = sb.toString().split("\\n");
			int[] arr=new int[lines.length];
			String[] arr1=new String[lines.length];
			String[] arr2=new String[lines.length];
			int count=0;
			for(int i=0;i<lines.length;i++) {
				
					String[] x=lines[i].split(" ");	
					arr1[i]=x[1];
					arr2[i]=x[0];
					int dec=Integer.valueOf(x[2]);
					arr[i]=dec;}
			if(tag>0 && offset>0 && set_no>0) {
			obj.LRUreplacement(cache,S, CL, B, arr,arr1,arr2, count,k);
			}
			obj.destroyCache(cache);}}
