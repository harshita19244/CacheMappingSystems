# CacheMappingSystems
:desktop_computer:
The given project is an implementation of the three kinds of cache mapping systems- Direct, Associative and set Associative Mapping. The code has been provided in Java. The three kinds of cache mappings have been implemented for a single level cache system and multilevel cache system.

## What is Cache Memory?
Cache Memory is a special very high-speed memory. 
It is used to speed up and synchronizing with high-speed CPU. Cache memory is costlier than main memory or disk memory but economical than CPU registers.Cache memory is an extremely fast memory type that acts as a buffer between RAM and the CPU. It holds frequently requested data and instructions so that they are immediately available to the CPU when needed.

## What are the 3 kinds of Cache mapping?
1. Direct Mapping : The simplest technique, known as direct mapping, maps each block of main memory into only one possible cache line. If a line is previously taken up by a memory block when a new block needs to be loaded, the old block is trashed.
2. Associative Mapping : In this type of mapping, the associative memory is used to store content and addresses of the memory word. Any block can go into any line of the cacheIt is considered to be the fastest and the most flexible mapping form.
3. Set Associative Mapping : Set-associative mapping allows that each word that is present in the cache can have two or more words in the main memory for the same index address. Set associative cache mapping combines the best of direct and associative cache mapping techniques.


### Path to mapping techniques
1. Direct Mapping : `/cache.java`
2. Associative Mapping : `/LRUcache.java`
3. Set Associative Mapping : `/cache3.java` 


