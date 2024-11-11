package kr.cosine.mczone.menu.command

import kr.cosine.mczone.core.command.component.BukkitCommand
import kr.cosine.mczone.menu.service.MenuViewService
import kr.hqservice.framework.global.core.component.Component
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

@Component
class MenuUserCommand(
    private val menuViewService: MenuViewService
) : BukkitCommand("메뉴") {
    override fun onCommand(sender: CommandSender, args: Array<out String>): Boolean {
        val player = sender as? Player ?: return true
        menuViewService.openDeafultMenuView(player)
        return true
    }
}