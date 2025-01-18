package kr.cosine.menu.data

import org.bukkit.entity.Player

data class Sound(
    val name: String,
    val volume: Float,
    val pitch: Float
) : Action {
    override suspend fun action(player: Player) {
        playSound(player)
    }

    fun playSound(player: Player) {
        player.playSound(player.location, name, volume, pitch)
    }
}