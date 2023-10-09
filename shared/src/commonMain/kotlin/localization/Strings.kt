package fi.tuska.beerclock.common.localization

import androidx.compose.ui.text.intl.Locale
import fi.tuska.beerclock.common.settings.Gender

val strings: Strings = when (Locale.current.language) {
    "fi" -> FiStrings
    "en" -> EnStrings
    else -> EnStrings
}

interface Strings {
    val appName: String

    val home: HomeStrings
    val menu: MenuStrings
    val settings: SettingsStrings

    val gender: GenderStrings

    interface HomeStrings {
        val addDrink: String
    }

    interface MenuStrings {
        val main: String
        val settings: String
        val drinks: String
        val statistics: String
    }

    interface SettingsStrings {
        val title: String
        val weightLabel: String
        val genderLabel: String
    }

    interface GenderStrings {
        val male: String
        val female: String
    }

    fun forGender(g: Gender): String =
        when (g) {
            Gender.MALE -> gender.male
            Gender.FEMALE -> gender.female
        }

}