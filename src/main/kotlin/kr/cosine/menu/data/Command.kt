package kr.cosine.menu.data

import kr.cosine.menu.enums.Permission
import org.bukkit.entity.Player

class Command(
    private val permission: Permission,
    private val command: String
) {

    fun execute(player: Player) {
        val finalCommand = command.replace("%player%", player.name)
        if (permission == Permission.CONSOLE) {
            val server = player.server
            server.dispatchCommand(server.consoleSender, finalCommand)
        } else {
            player.performCommand(finalCommand)
        }
    }
}