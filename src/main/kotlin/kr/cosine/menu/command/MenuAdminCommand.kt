package kr.cosine.menu.command

import kr.cosine.menu.config.SettingConfig
import kr.cosine.menu.data.Menu
import kr.cosine.menu.service.MenuViewService
import kr.cosine.menu.view.MenuView
import kr.hqservice.framework.command.ArgumentLabel
import kr.hqservice.framework.command.Command
import kr.hqservice.framework.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.ConsoleCommandSender
import org.bukkit.entity.Player

@Command(label = "메뉴관리", isOp = true)
class MenuAdminCommand(
    private val settingConfig: SettingConfig,
    private val menuViewService: MenuViewService
) {
    @CommandExecutor("열기", "메뉴를 오픈합니다.", priority = 1)
    fun openMenuView(
        sender: CommandSender,
        @ArgumentLabel("메뉴") menu: Menu,
        @ArgumentLabel("유저") target: Player?
    ) {
        val viewer = target ?: sender
        if (viewer is ConsoleCommandSender) {
            sender.sendMessage("§c콘솔에서 실행할 수 없는 명령어입니다. 인게임에서 실행하거나 타겟을 지정해주세요.")
            return
        }
        menuViewService.openMenuView(viewer as Player, menu)
    }

    @CommandExecutor("리로드", "config.yml을 리로드합니다.", priority = 2)
    fun reload(sender: CommandSender) {
        settingConfig.reload()
        sender.sendMessage("§aconfig.yml을 리로드하였습니다.")
    }
}