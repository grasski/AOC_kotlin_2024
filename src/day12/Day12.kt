package day12

import readInput

data class Region(
    var borders: Int = 0,
    var type: String = "",
    var coords: MutableList<Pair<Int, Int>> = mutableListOf()
)


fun main() {
    fun part1(input: List<String>): Int {
        val grid = input.map { it.chunked(1).map { it } }

        val list = mutableListOf<Region>()
        grid.forEachIndexed { indexX, strings ->
            strings.forEachIndexed { indexY, s ->
                val filter = list.filter { it.type == s }
                if (filter.isEmpty()){
                    list.add(Region(borders = 4, type = s, coords = mutableListOf(Pair(indexX, indexY))))
                } else{
                    val hasRegion = filter.find { it.coords.contains(Pair(indexX - 1, indexY)) } ?: filter.find { it.coords.contains(Pair(indexX, indexY - 1)) } // First needs to check TOP side to see, if it can join with top region, then check left side

                    hasRegion?.let { region ->
                        val index = list.indexOf(region)
                        region.coords.add(Pair(indexX, indexY))
                        val touches = if (region.coords.contains(Pair(indexX-1, indexY)) && region.coords.contains(Pair(indexX, indexY-1))) 2 else 1 // Either both sides are touching or just one
                        region.borders += (4 - 2*touches)

                        // Check if not joined region is the same type and is touching from left side
                        val notJoinedRegion = list.find { it.coords.contains(Pair(indexX, indexY -1)) && it != region && it.type == s }
                        if (notJoinedRegion != null){
                            region.coords.addAll(notJoinedRegion.coords)
                            region.borders += notJoinedRegion.borders - 2
                            list[index] = region
                            list.remove(notJoinedRegion)
                        } else{
                            list[index] = region
                        }
                    } ?: run{
                        // Does not touch any other type - create new
                        list.add(Region(borders = 4, type = s, coords = mutableListOf(Pair(indexX, indexY))))
                    }
                }
            }
        }
        var sum = 0
        list.forEach {
            sum += it.coords.size * it.borders
        }
        return sum
    }
    
    fun part2(input: List<String>): Int {
        return 0
    }
    
    val testInput = readInput("Day12_test")
    val input = readInput("Day12")

    check(part1(testInput) == 1930)
    println(part1(input))
//
//    check(part2(testInput) == 0)
//    println(part2(input))
}
