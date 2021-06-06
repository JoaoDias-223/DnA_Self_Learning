package com.slearning.pokedex.model.resources

enum class TypeNames {
    FIGHTING, FIRE, WATER, SPECIAL, ELECTRIC;

    companion object {
        inline fun <reified T : Enum<*>> enumValueOrNull(name: String): T? =
            T::class.java.enumConstants.firstOrNull { it.name == name }
    }
}