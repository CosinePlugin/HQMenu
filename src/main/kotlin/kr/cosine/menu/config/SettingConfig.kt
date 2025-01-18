package kr.cosine.menu.config

import kr.cosine.menu.data.*
import kr.cosine.menu.registry.MenuRegistry
import kr.hqservice.framework.bukkit.core.HQBukkitPlugin
import kr.hqservice.framework.bukkit.core.extension.colorize
import kr.hqservice.framework.global.core.component.Bean
import kr.hqservice.framework.yaml.config.HQYamlConfiguration
import kr.hqservice.framework.yaml.config.HQYamlConfigurationSection
import kr.hqservice.framework.yaml.extension.yaml
import org.bukkit.Material
import org.bukkit.configuration.file.YamlConfiguration
import java.io.File
import java.util.logging.Logger

@Bean
class SettingConfig(
    private val plugin: HQBukkitPlugin,
    private val logger: Logger,
    private val config: HQYamlConfiguration,
    private val menuRegistry: MenuRegistry
) {
    val defaultMenuName get() = config.getString("default-menu").removeSuffix(".yml")

    fun load() {
        val folder = File(plugin.dataFolder, "menu")
        if (!folder.exists()) {
            folder.mkdirs()
            plugin.saveResource("example.yml", false)
            val file = File(plugin.dataFolder, "example.yml")
            file.renameTo(File(folder, "example.yml"))
            file.delete()
        }

        val menuMap = mutableMapOf<String, Menu>()
        fun readFile(it: File) {
            val yaml = it.yaml()
            yaml.apply {
                val title = getString("title").colorize()
                val size = getInt("size")

                val font = getString("font")
                val backspaces = getInt("backspaces")

                val buttons = mutableListOf<Button>()
                getSection("button")?.apply {
                    getKeys().forEach buttonForEach@ { key ->
                        getSection(key)?.apply {
                            val slots = getSlots("slots")
                            val material = findMaterial("material") ?: return
                            val displayName = getString("display-name").colorize()
                            val lore = getStringList("lore").map(String::colorize)
                            val customModelData = getInt("custom-model-data")
                            val sound = findSound("sound")
                            val isCloseInventoryIfClicked = getBoolean("close-inventory-if-clicked")
                            val actions = getStringList("commands").mapNotNull(Action::of)
                            val button = Button(slots, material, displayName, lore, customModelData, sound, isCloseInventoryIfClicked, actions)
                            buttons.add(button)
                        }
                    }
                }
                val menu = Menu(title, size, font, backspaces, buttons)
                menuMap[it.name.removeSuffix(".yml")] = menu
            }
        }

        fun readFolder(it: File) {
            it.listFiles()?.forEach { file ->
                if (file.isDirectory) {
                    readFolder(file)
                } else {
                    readFile(file)
                }
            }
        }

        readFolder(folder)
        menuRegistry.setMenuMap(menuMap)
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