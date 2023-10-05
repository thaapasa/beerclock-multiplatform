package fi.tuska.beerclock.settings

enum class Gender {
    MALE, FEMALE;

    companion object {
        fun safeValueOf(s: String): Gender? = try {
            Gender.valueOf(s)
        } catch (e: IllegalArgumentException) {
            // Thrown if s doesn't match
            null
        }

    }
}
