package fi.tuska.beerclock.ui

import androidx.compose.ui.text.intl.Locale

val strings = when(Locale.current.language) {
    "fi" -> FiStrings
    "en" -> EnStrings
    else -> EnStrings
}

interface Strings {
    val appName: String
    val menu: String
    val menuSettings: String
    val menuDrinks: String
    val menuStatistics: String
}

object EnStrings : Strings {
    override val appName = "Beer Clock"
    override val menu = "Menu"
    override val menuSettings = "Settings"
    override val menuDrinks = "Drinks"
    override val menuStatistics = "Statistics"
}

object FiStrings : Strings {
    override val appName = "Kaljakello"
    override val menu = "Valikko"
    override val menuSettings = "Asetukset"
    override val menuDrinks = "Juomat"
    override val menuStatistics = "Tilastot"
}