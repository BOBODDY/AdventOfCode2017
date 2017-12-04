package day4

import java.io.File
import java.io.InputStream

val filename = "input"

fun main(args: Array<String>) {
    val inputStream: InputStream = File("src/day4/" + filename).inputStream()

    val inputString = inputStream.bufferedReader().use { it.readText() }
    val phrases = inputString.split("\r\n")
    print(countValidPassphrases2(phrases))
}

fun countValidPassphrases2(phrases: List<String>): Int {
    var count = 0

    for(phrase in phrases) {
        if(isValidPassphrase2(phrase)) {
            count += 1
        }
    }

    return count
}

fun isValidPassphrase2(phrase: String): Boolean {
    val words = phrase.split(" ")

    val sortedWords = sortWords(words)

    for(i in 0 until sortedWords.size) {
        val iWord = sortedWords[i]
        for(j in i until sortedWords.size) {
            val jWord = sortedWords[j]
            if(iWord == jWord && i != j) {
                return false
            }
        }
    }

    return true
}

fun sortWords(words: List<String>): List<String> {
    var sorted: MutableList<String> = mutableListOf()

    for(word in words) {
        val chars = word.toCharArray()
        val sortedChars = chars.sortedArray()
        val sortedWord = String(sortedChars)
        sorted.add(sortedWord)
    }

    return sorted
}