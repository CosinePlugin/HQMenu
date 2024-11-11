package kr.cosine.mczone.menu.data

import org.bukkit.entity.Player

class Command(
    private val permission: kr.cosine.mczone.menu.enums.Permission,
    private val command: String
) {

    fun execute(player: Player) {
        val finalCommand = command.replace("%player%", player.name)
        if (permission == kr.cosine.mczone.menu.enums.Permission.CONSOLE) {
            val server = player.server
            server.dispatchCommand(server.consoleSender, finalCommand)
        } else {
            player.performCommand(finalCommand)
        }
    }
}