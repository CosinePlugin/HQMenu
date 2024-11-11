package kr.cosine.menu.data

data class Menu(
    val title: String,
    val size: Int,
    val font: String,
    val backspaces: Int,
    val buttons: List<Button>
)
