//------------------------------------------------------------------------------
// 4190.308                     Computer Architecture                  Fall 2015
//
// Cache Simulator Lab
//
// File: cache.c
//
// (C) 2015 Computer Systems and Platforms Laboratory, Seoul National University
//
// Changelog
// 20151119   bernhard    created
//

#include <assert.h>
#include <limits.h>
#include <stdio.h>
#include <stdlib.h>
#include "cache.h"

#include <math.h> //In order to compute S and B exponentially

//To hold a mem address in uint32
typedef unsigned int mem_addr_t;

char RP_STR[RP_MAX+1][32] = {
        "round robin", "random", "LRU (least-recently used)",
        "MRU (most-recently used)", "LFU (least-frequently used)",
        "MFU (most-frequently used)"
};

char WP_STR[2][20] = {
        "write-allocate", "no write-allocate"
};


Cache* create_cache(uint32 capacity, uint32 blocksize, uint32 ways,
                    uint32 rp, uint32 wp, uint32 verbosity)
{
    // TODO
    //
    // 1. check cache parameters
    //    - capacity, blocksize, and ways must be powers of 2
    //    - capacity must be > blocksize
    //    - number of ways must be >= the number of blocks
    // 2. allocate cache and initialize them
    //    - use the above data structures Cache, Set, and Line
    // 3. print cache configuration
    // 4. return cache

    Cache newCache;
    Set set;
    Line line;
    Cache* createCache;

    uint32 setIndex;
    uint32 lineIndex;
    uint32 num_sets;
    uint32 num_blocks;
    uint32 num_lines;

    // To count number of Blocks = Capacity / BlockSize
    num_blocks = capacity / blocksize;

    // To count number of Sets = #Blocks / #ways
    num_sets = num_blocks / ways;

    // To count number of lines we use C = B * E * S
    num_lines = capacity / blocksize / num_sets;

    // As it is said in the instructions, use malloc function to allocate storage
    newCache.set = (Set *)malloc(sizeof(Set) * num_sets);

    // cache is an array of s sets with index 0 to s-1
    for  (setIndex = 0; setIndex < num_sets; setIndex++) {
        // Create E lines for each set
        set.way = (Line *) malloc(sizeof(Line) * num_lines);
        newCache.set[setIndex] = set;

        // Create b block offset bits for each line
        for (lineIndex = 0; lineIndex < num_lines; lineIndex++) {
            line.access_count = 0;
            line.valid = 0;
            line.tag = 0;
            set.way[lineIndex] = line;
        }
    }



    // 3. print cache configuration
    printf("Cache configuration:\n"
                   "  capacity:        %6u\n"
                   "  blocksize:       %6u\n"
                   "  ways:            %6u\n"
                   "  sets:            %6u\n"
                   "  tag shift:       %6u\n"
                   "  replacement:     %s\n"
                   "  on write miss:   %s\n"
                   "\n",
           0, 0, 0, 0, 0, "", ""); // TODO

    // 4. return cache
    createCache = newCache;
    return newCache;
}

void delete_cache(Cache *c)
{
    // TODO
    //
    // clean-up the allocated memory
}

void line_access(Cache *c, Line *l)
{
    // TODO
    //
    // update data structures to reflect access to a cache line
}


void line_alloc(Cache *c, Line *l, uint32 tag)
{
    // TODO
    //
    // update data structures to reflect allocation of a new block into a line
}

uint32 set_find_victim(Cache *c, Set *s)
{
    // TODO
    //
    // for a given set, return the victim line where to place the new block
    return 0;
}

void cache_access(Cache *c, uint32 type, uint32 address, uint32 length)
{
    // TODO
    //
    // simulate a cache access
    // 1. compute set & tag
    // 2. check if we have a cache hit
    // 3. on a cache miss, find a victim block and allocate according to the
    //    current policies
    // 4. update statistics (# accesses, # hits, # misses)
}
