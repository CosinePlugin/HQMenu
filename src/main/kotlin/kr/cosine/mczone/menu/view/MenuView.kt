package kr.cosine.mczone.menu.view

import kr.cosine.mczone.core.extension.delayLaunch
import kr.cosine.mczone.core.view.View
import kr.cosine.mczone.menu.data.Menu
import kr.hqservice.framework.bukkit.core.HQBukkitPlugin
import kr.hqservice.framework.inventory.button.HQButtonBuilder
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.inventory.ClickType

class MenuView(
    private val plugin: HQBukkitPlugin,
    private val menu: Menu,
    private val player: Player
) : View(menu.title, menu.size, backspaces = menu.backspaces.takeIf { it != 0 } ?: DEFAULT_BACKSPACES) {
    override fun onCreate() {
        menu.buttons.forEach { button ->
            val itemStack = button.toItemStack(player)
            HQButtonBuilder(itemStack).apply {
                if (itemStack.type == Material.PLAYER_HEAD) {
                    setOwningPlayer(player.uniqueId)
                }
                setClickFunction { event ->
                    if (event.getClickType() != ClickType.LEFT) return@setClickFunction
                    val player = event.getWhoClicked()
                    button.playSound(player)
                    button.closeInventoryIfClicked(player)
                    plugin.delayLaunch {
                        button.executeCommands(player)
                    }
                }
            }.build().setSlot(this, *button.slots.toIntArray())
        }
    }
}