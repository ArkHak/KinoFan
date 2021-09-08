package com.example.kinofan.ui.model

data class Film(
    val title: String = "???",
    val genre: String = "???",
    val rating: Double = 0.0,
    val yearСreation: String = "????"
)

fun getReleasedFilms(): List<Film> {
    return listOf(
        Film("Бесконечность", "фантастика, боевик, триллер", 7.5, "2021"),
        Film("Анатомия страсти", "драма", 8.2, "2005"),
        Film("Шан-Чи и легенда десяти колец", "боевик, приключения, фэнтези", 8.0, "2021"),
        Film("Не дыши 2", "триллер, ужасы", 7.7, "2021"),
        Film(
            "Отряд самоубийц: Миссия навылет",
            "боевик, приключения, фэнтези, комедия",
            8.0,
            "2021"
        )
    )
}

fun getUpcomingFilms(): List<Film> {
    return listOf(
        Film("Дюна", "приключения, драма, фантастика", yearСreation = "2021"),
        Film("Веном 2", "фантастика, боевик", yearСreation = "2021"),
        Film("Не время умирать", "приключения, боевик, триллер", yearСreation = "2021"),
        Film("Холодный расчет", "драма, триллер, боевик", yearСreation = "2021"),
        Film("Семейка Аддамс: Горящий тур", "мультфильм, комедия, семейный", yearСreation = "2021"),
    )
}