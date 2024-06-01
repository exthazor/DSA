//Test Case: 
//3 4
//1 2 3
//2 3 1
//1 3 7
//2 1 5

//1 2 -> 1
//2 3 -> 1 -> 2

//1 -> 2 (3)
//1 -> 3 (7)


fun minPriceFlightRoute(n: Int, paths: Array<IntArray>): Int {
    
    var answer = 0
    var adjacencyList = Array(n + 1) { mutableListOf<Pair<Int, Int>>() }
    paths.forEach { path -> adjacencyList[path[0]].add(path[1] to path[2]) }
    var currentMinDistance = mutableListOf<Int>()
    
    fun dfs(node: Int, cost: Int, visited: MutableSet<Int>) {
        
        if(node in visited) return
        visited.add(node)
        currentMinDistance.add(cost)
        if (node == n) {
            val maxValue = currentMinDistance.maxOrNull() ?: 0
            currentMinDistance[currentMinDistance.indexOf(maxValue)] = maxValue / 2
            answer = maxOf(answer, currentMinDistance.sum())
            visited.remove(node)
            currentMinDistance.removeAt(currentMinDistance.lastIndex)
            return
        }
        
        for(neighbour in adjacencyList[node]){
            dfs(neighbour.first, neighbour.second, visited)
        }
        visited.remove(node)
        currentMinDistance.removeAt(currentMinDistance.lastIndex)
        
    }
    dfs(1, 0, mutableSetOf())
    return answer
}

fun main() {
    val n = 3
    val paths = arrayOf(
        intArrayOf(1, 2, 9),
        intArrayOf(2, 3, 1),
        intArrayOf(1, 3, 7),
        intArrayOf(2, 2, 5)
    )

    println(minPriceFlightRoute(n, paths))
}
