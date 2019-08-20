import java.util.stream.Collectors
import kotlin.random.Random

fun main() {
    val iterationForAverage = 10

    println("List size,Kotlin Sequence,Kotlin,Java Map,Kotlin Java Stream,Kotlin Java Parallel Stream, Kotlin Hash Map")
    for (i in 1..100) {
        val size = i * 100000
        print(size)
        print(",")
        val myList = List(size) { Random.nextInt(0, 9) }

        val startTime0 = System.nanoTime()
        for (j in 1..iterationForAverage) {
            getElementsFrequenciesKotlinSequence(myList)
        }
        val endTime0 = System.nanoTime()
        print((endTime0 - startTime0) / (1000 * iterationForAverage))
        print(",")

        val startTime1 = System.nanoTime()
        for (j in 1..iterationForAverage) {
            getElementsFrequenciesKotlin(myList)
        }
        val endTime1 = System.nanoTime()
        print((endTime1 - startTime1) / (1000 * iterationForAverage))
        print(",")

        val startTime2 = System.nanoTime()
        for (j in 1..iterationForAverage) {
            Counter.getElementsFrequenciesMap(myList)
        }
        val endTime2 = System.nanoTime()
        print((endTime2 - startTime2) / (1000 * iterationForAverage))
        print(",")

        val startTime3 = System.nanoTime()
        for (j in 1..iterationForAverage) {
            getElementsFrequenciesKotlinStream(myList)
        }
        val endTime3 = System.nanoTime()
        print((endTime3 - startTime3) / (1000 * iterationForAverage))
        print(",")

        val startTime4 = System.nanoTime()
        for (j in 1..iterationForAverage) {
            getElementsFrequenciesParallelKotlinStream(myList)
        }
        val endTime4 = System.nanoTime()
        print((endTime4 - startTime4) / (1000 * iterationForAverage))
        print(",")

        val startTime5 = System.nanoTime()
        for (j in 1..iterationForAverage) {
            getElementsFrequenciesKotlinMap(myList)
        }
        val endTime5 = System.nanoTime()
        print((endTime5 - startTime5) / (1000 * iterationForAverage))
        println()
    }
}

fun getElementsFrequenciesKotlin(collection: Iterable<Int>): Map<Int, Int> =
    collection.groupBy { it }.mapValues { (key, similarElements) -> similarElements.count() }

fun getElementsFrequenciesKotlinSequence(collection: Iterable<Int>): Map<Int, Int> =
    collection.asSequence().groupBy { it }.mapValues { (key, similarElements) -> similarElements.count() }

fun getElementsFrequenciesKotlinMap(collection: Collection<Int>): Map<Int, Int> {
    val frequency = HashMap<Int, Int>()
    for (element in collection) {
        frequency[element] = frequency.getOrDefault(element, 0) + 1
    }
    return frequency
}

fun getElementsFrequenciesParallelKotlinStream(elements: Collection<Int>): Map<Int, Long> {
    return elements.parallelStream().collect(Collectors.groupingBy({ element: Int -> element }, Collectors.counting()))
}

fun getElementsFrequenciesKotlinStream(elements: Collection<Int>): Map<Int, Long> {
    return elements.stream().collect(Collectors.groupingBy({ element: Int -> element }, Collectors.counting()))
}