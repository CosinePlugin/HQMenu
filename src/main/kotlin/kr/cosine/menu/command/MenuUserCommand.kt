package kr.cosine.menu.command

import kr.cosine.menu.service.MenuViewService
import kr.hqservice.framework.global.core.component.Bean
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter
import org.bukkit.entity.Player

@Bean
class MenuUserCommand(
    private val menuViewService: MenuViewService
) : CommandExecutor, TabCompleter {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        val player = sender as? Player ?: return true
        menuViewService.openDeafultMenuView(player)
        return true
    }

    override fun onTabComplete(sender: CommandSender, command: Command, label: String, args: Array<out String>): List<String> {
        return emptyList()
    }
}