package kr.cosine.mczone.menu.service

import kr.cosine.mczone.menu.config.SettingConfig
import kr.cosine.mczone.menu.data.Menu
import kr.cosine.mczone.menu.registry.MenuRegistry
import kr.cosine.mczone.menu.view.MenuView
import kr.hqservice.framework.bukkit.core.HQBukkitPlugin
import kr.hqservice.framework.global.core.component.Service
import org.bukkit.entity.Player

@Service
class MenuViewService(
    private val plugin: HQBukkitPlugin,
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
        MenuView(plugin, menu, player).open(player)
    }
}