package fi.tuska.beerclock.domain

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

internal class UserStore {

    var state: UserStore by mutableStateOf(initialState())
        private set

    fun onSetGender(gender: Gender) {
        setState { copy(gender = gender) }
    }

    /**
     * @param weight weight, in kilograms
     */
    fun onSetWeight(weight: Float) {
        setState { copy(weightKg = weight) }
    }

    private fun initialState(): UserStore =
        UserStore()

    private inline fun setState(update: UserStore.() -> UserStore) {
        state = state.update()
    }

    data class UserStore(
        /** User gender. Affects BAC calculation formula. */
        val gender: Gender = Gender.MALE,
        /** User weight, in kilograms. Affects BAC calculation formula. */
        val weightKg: Float = 70f,
    )
}

enum class Gender {
    MALE,
    FEMALE
}