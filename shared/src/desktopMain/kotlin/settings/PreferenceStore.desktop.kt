package fi.tuska.beerclock.settings

actual object PreferenceProvider {

    actual fun getPrefs(): PreferenceStore {
        return Prefs()
    }
}

private class Prefs: PreferenceStore {
    
    override fun getString(key: String, defaultValue: String): String =
        defaultValue

    override fun setString(key: String, value: String) {
    }
}