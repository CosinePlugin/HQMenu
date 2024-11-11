package kr.cosine.mczone.menu.enums

enum class Permission {
    CONSOLE,
    PLAYER;

    companion object {
        private val values = values()

        fun of(text: String): Permission? {
            return values.find { it.name == text.uppercase() }
        }
    }
}