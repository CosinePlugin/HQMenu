package kr.cosine.menu.view

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kr.cosine.menu.data.Menu
import kr.cosine.menu.enums.Backspaces
import kr.hqservice.framework.bukkit.core.coroutine.bukkitDelay
import kr.hqservice.framework.bukkit.core.coroutine.extension.BukkitMain
import kr.hqservice.framework.inventory.button.HQButtonBuilder
import kr.hqservice.framework.inventory.container.HQContainer
import kr.hqservice.framework.nms.extension.virtual
import net.md_5.bungee.api.chat.TextComponent
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.inventory.ClickType
import org.bukkit.inventory.Inventory
import kotlin.coroutines.CoroutineContext

class MenuView(
    private val menu: Menu,
    private val player: Player
) : HQContainer(menu.size, ""), CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.BukkitMain

    override fun initialize(inventory: Inventory) {
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
                    launch {
                        bukkitDelay(1)
                        button.executeCommands(player)
                    }
                }
            }.build().setSlot(this, *button.slots.toIntArray())
        }
    }

    override fun onOpen(vararg players: Player) {
        players.forEach { player ->
            player.virtual {
                inventory {
                    val backspaces = menu.backspaces
                    val font = menu.font

                    val createTextComponent: (String) -> TextComponent = {
                        TextComponent(it).apply {
                            if (font.isNotEmpty()) {
                                this.font = font
                            }
                        }
                    }

                    setTitle(
                        TextComponent().apply {
                            if (backspaces != 0) {
                                addExtra(createTextComponent(Backspaces[backspaces]))
                            }
                            addExtra(createTextComponent(menu.title))
                        }
                    )
                }
            }
        }
    }
}