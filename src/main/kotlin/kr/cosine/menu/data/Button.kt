package kr.cosine.menu.data

import kr.hqservice.framework.bukkit.core.extension.editMeta
import me.clip.placeholderapi.PlaceholderAPI
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

data class Button(
    val slots: List<Int>,
    private val material: Material,
    private val displayName: String,
    private val lore: List<String>,
    private val customModelData: Int,
    private val sound: Sound? = null,
    private val isCloseInventoryIfClicked: Boolean,
    private val commands: List<String>
) {
    fun toItemStack(player: Player): ItemStack {
        return ItemStack(material).editMeta {
            val newDisplayName = PlaceholderAPI.setPlaceholders(player, this@Button.displayName)
            if (newDisplayName.isNotEmpty()) {
                setDisplayName(newDisplayName)
            }
            val newLore = PlaceholderAPI.setPlaceholders(player, this@Button.lore)
            if (newLore.isNotEmpty()) {
                lore = newLore
            }
            val newCustomModelData = this@Button.customModelData
            if (newCustomModelData != 0) {
                setCustomModelData(newCustomModelData)
            }
        }
    }

    fun closeInventoryIfClicked(player: Player) {
        if (isCloseInventoryIfClicked) {
            player.closeInventory()
        }
    }

    fun executeCommands(player: Player) {
        val server = player.server
        commands.forEach { command ->
            val finalCommand = command.replace("%player%", player.name)
            server.dispatchCommand(server.consoleSender, finalCommand)
        }
    }

    fun playSound(player: Player) {
        sound?.playSound(player)
    }
}