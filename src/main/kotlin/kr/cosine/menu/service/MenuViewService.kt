package kr.cosine.menu.service

import kr.cosine.menu.config.SettingConfig
import kr.cosine.menu.data.Menu
import kr.cosine.menu.registry.MenuRegistry
import kr.cosine.menu.view.MenuView
import kr.hqservice.framework.global.core.component.Service
import org.bukkit.entity.Player

@Service
class MenuViewService(
    private val settingConfig: SettingConfig,
    private val menuRegistry: MenuRegistry
) {
    fun openDeafultMenuView(player: Player) {
        val menu = menuRegistry.findMenu(settingConfig.defaultMenuName) ?: run {
            player.sendMessage("§c메뉴가 존재하지 않습니다. 관리자에게 문의해주세요.")
            return
        }
        openMenuView(player, menu)
    }

    fun openMenuView(player: Player, menu: Menu) {
        MenuView(menu, player).open(player)
    }
}