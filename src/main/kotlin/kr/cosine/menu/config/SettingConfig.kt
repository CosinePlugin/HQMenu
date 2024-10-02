package kr.cosine.menu.config

import kr.cosine.menu.data.Button
import kr.cosine.menu.data.Menu
import kr.cosine.menu.data.Sound
import kr.cosine.menu.registry.MenuRegistry
import kr.hqservice.framework.bukkit.core.extension.colorize
import kr.hqservice.framework.global.core.component.Bean
import kr.hqservice.framework.yaml.config.HQYamlConfiguration
import kr.hqservice.framework.yaml.config.HQYamlConfigurationSection
import org.bukkit.Material
import java.util.logging.Logger

@Bean
class SettingConfig(
    private val logger: Logger,
    private val config: HQYamlConfiguration,
    private val menuRegistry: MenuRegistry
) {
    val defaultMenuName get() = config.getString("default-menu")

    fun load() {
        config.getSection("menu")?.apply {
            val menuMap = mutableMapOf<String, Menu>()
            getKeys().forEach { menuName ->
                getSection(menuName)?.apply {
                    val title = getString("title").colorize()
                    val size = getInt("size")

                    val font = getString("font")
                    val backspaces = getInt("backspaces")

                    val buttons = mutableListOf<Button>()
                    getSection("button")?.apply {
                        getKeys().forEach buttonForEach@ {
                            getSection(it)?.apply {
                                val slots = getSlots("slots")
                                val material = findMaterial("material") ?: return@forEach
                                val displayName = getString("display-name").colorize()
                                val lore = getStringList("lore").map(String::colorize)
                                val customModelData = getInt("custom-model-data")
                                val sound = findSound("sound")
                                val isCloseInventoryIfClicked = getBoolean("close-inventory-if-clicked")
                                val commands = getStringList("commands")
                                val button = Button(slots, material, displayName, lore, customModelData, sound, isCloseInventoryIfClicked, commands)
                                buttons.add(button)
                            }
                        }
                    }
                    val menu = Menu(title, size, font, backspaces, buttons)
                    menuMap[menuName] = menu
                }
            }
            menuRegistry.setMenuMap(menuMap)
        }
    }

    private fun HQYamlConfigurationSection.getSlots(path: String): List<Int> {
        return getString(path).split(", ").map(String::toInt)
    }

    private fun HQYamlConfigurationSection.findMaterial(path: String): Material? {
        val materialText = getString(path)
        val material = Material.getMaterial(materialText.uppercase()) ?: run {
            logger.warning("${materialText}은(는) 존재하지 않는 Material입니다.")
            return null
        }
        return material
    }

    private fun HQYamlConfigurationSection.findSound(path: String): Sound? {
        return getSection(path)?.let {
            Sound(
                it.getString("name"),
                it.getDouble("volume").toFloat(),
                it.getDouble("pitch").toFloat()
            )
        }
    }

    fun reload() {
        menuRegistry.clear()
        config.reload()
        load()
    }
}