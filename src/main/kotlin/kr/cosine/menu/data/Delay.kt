package kr.cosine.menu.data

import kr.hqservice.framework.bukkit.core.coroutine.bukkitDelay
import org.bukkit.entity.Player

class Delay(
    private val tick: Long
) : Action {
    override suspend fun action(player: Player) {
        bukkitDelay(tick)
    }
}