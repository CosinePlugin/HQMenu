package kr.cosine.mczone.menu.data

data class Menu(
    val title: String,
    val size: Int,
    val backspaces: Int,
    val buttons: List<Button>
)
