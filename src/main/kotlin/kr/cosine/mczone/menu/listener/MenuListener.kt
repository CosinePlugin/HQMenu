package kr.cosine.mczone.menu.listener

import kr.cosine.mczone.core.enums.HotKey
import kr.cosine.mczone.core.event.PlayerHotKeyPressedEvent
import kr.cosine.mczone.menu.service.MenuViewService
import kr.hqservice.framework.bukkit.core.HQBukkitPlugin
import kr.hqservice.framework.bukkit.core.listener.Listener
import kr.hqservice.framework.bukkit.core.listener.Subscribe

@Listener
class MenuListener(
    private val plugin: HQBukkitPlugin,
    private val menuViewService: MenuViewService
) {
    @Subscribe
    fun onPlayerHotKeyPressed(event: PlayerHotKeyPressedEvent) {
        if (event.hotKey == HotKey.MENU) {
            menuViewService.openDeafultMenuView(event.player)
        }
    }
}