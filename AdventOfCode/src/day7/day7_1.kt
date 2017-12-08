package day7

import java.io.File
import java.io.InputStream
import kotlin.collections.HashMap

val filename = "testInput"

var programsMap: HashMap<String, Program> = HashMap()

fun main(args: Array<String>) {
    val inputStream: InputStream = File("src/day7/" + filename).inputStream()

    val inputString = inputStream.bufferedReader().use { it.readText() }

    val programs = inputString.split("\n")

    val root = findRoot(programs)
    println("Root program = " + root)
    val wrongWeight = calculateWrongWeight(root)
    println("Wrong weight = " + wrongWeight)
}

fun calculateWrongWeight(root: String): Int {
    var weight = 0

    var children = programsMap.get(root)!!.programIds

    var childWeights = mutableListOf<Pair<Program, Int>>()
    for(childId in children) {
        val childProgram = programsMap[childId]
        val childWeight = day7.weight(childId)
        childWeights.add(Pair(childProgram!!, childWeight))
    }

    for(x in childWeights) {
        val childrenWeight = x.second
        val nodeWeight = x.first.weight
        println("Children: " + childrenWeight + " Parent: " + nodeWeight + " = " + (childrenWeight + nodeWeight))
    }

    return weight
}

fun findRoot(programs: List<String>): String {
    programs
            .map { convertToProgram(it) }
            .forEach { programsMap.put(it.id, it) }

    var root = programsMap.entries.first { it.value.programIds.isNotEmpty() }.value.id

    var nextValToCheck: String? = root
    while(nextValToCheck != null) {
        val temp = programsMap.entries.firstOrNull { it.value.programIds.contains(nextValToCheck!!) }
        if(temp != null) {
            root = temp.value.id
        }

        nextValToCheck = temp?.value?.id
    }

    return root
}
fun weight(node: String): Int {
    val program = programsMap.get(node)

    var weight = 0
    if(program!!.programIds.isNotEmpty()) {
        for(child in program!!.programIds) {
            val childProgram = programsMap.get(child)
            weight += weight(childProgram!!.id)
        }
    }

    return program.weight + weight
}

fun convertToProgram(s: String): Program {
    val listSplit = s.split("->")
    val data = listSplit[0].split(" ")

    var otherPrograms = mutableListOf<String>()
    if(listSplit.size > 1 && listSplit[1].isNotEmpty()) {
        otherPrograms.addAll(listSplit[1].split(","))
    }

    val pr = otherPrograms.map { it.replace(" ", "") }

    val id = data[0]
    val weight = data[1].replace("(", "").replace(")", "")

    return Program(id, weight.toInt(), pr)
}

data class Program(val id: String, val weight: Int, val programIds: List<String>)