package fi.tuska.beerclock.ui

import androidx.compose.ui.text.intl.Locale
import fi.tuska.beerclock.domain.Gender

val strings = when(Locale.current.language) {
    "fi" -> FiStrings
    "en" -> EnStrings
    else -> EnStrings
}
interface Strings {
    val appName: String
    val menu: MenuStrings
    val settings: SettingsStrings

    fun forGender(g: Gender): String =
        when (g) {
            Gender.MALE -> gender.MALE
            Gender.FEMALE -> gender.FEMALE
        }

    val gender: GenderStrings

    interface MenuStrings {
        val main: String
        val settings: String
        val drinks: String
        val statistics: String
    }

    interface SettingsStrings {
        val ageLabel: String
        val genderLabel: String
    }

    interface GenderStrings {
        val MALE: String
        val FEMALE: String
    }
}

object EnStrings : Strings {
    override val appName = "Beer Clock"
    override val menu = Menu

    object Menu : Strings.MenuStrings {
        override val main = "Menu"
        override val settings = "Settings"
        override val drinks = "Drinks"
        override val statistics = "Statistics"
    }

    override val settings = Settings
    object Settings : Strings.SettingsStrings {
        override val ageLabel = "Age"
        override val genderLabel = "Gender"
    }

    override val gender = Gender
    object Gender : Strings.GenderStrings {
        override val MALE = "Male"
        override val FEMALE = "Female"
    }
}

object FiStrings : Strings {
    override val appName = "Kaljakello"
    override val menu = Menu

    object Menu : Strings.MenuStrings {
        override val main = "Valikko"
        override val settings = "Asetukset"
        override val drinks = "Juomat"
        override val statistics = "Tilastot"
    }

    override val settings = Settings
    object Settings : Strings.SettingsStrings {
        override val ageLabel = "Ikä"
        override val genderLabel = "Sukupuoli"
    }

    override val gender = Gender
    object Gender : Strings.GenderStrings {
        override val MALE = "Mies"
        override val FEMALE = "Nainen"
    }
}