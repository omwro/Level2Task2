package nl.omererdem.madlevel2task2

data class Question (
    var question: String,
    var answer: Boolean,
    var result: Boolean = false
) {
    companion object {
        val questions: List<Question> = listOf(
            Question("A 'var' and 'val' are the same", false),
            Question("Mobile Application Development grants 12 ECTS", true),
            Question("Math is related to Science", true),
            Question("I will get a sufficient for this task", true)
        )
    }
}