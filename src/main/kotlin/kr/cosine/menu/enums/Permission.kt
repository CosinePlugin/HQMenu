package kr.cosine.menu.enums

enum class Permission {
    CONSOLE,
    PLAYER;

    companion object {
        val values = values()

        fun of(text: String): Permission? {
            return values.find { it.name == text.uppercase() }
        }
    }
}