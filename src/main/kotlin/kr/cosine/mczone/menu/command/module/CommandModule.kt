package kr.cosine.mczone.menu.command.module

import kr.cosine.mczone.menu.command.MenuUserCommand
import kr.hqservice.framework.bukkit.core.HQBukkitPlugin
import kr.hqservice.framework.bukkit.core.component.module.Module
import kr.hqservice.framework.bukkit.core.component.module.Setup

@Module
class CommandModule(
    private val plugin: HQBukkitPlugin,
    private val menuUserCommand: MenuUserCommand
) {
    @Setup
    fun setup() {
        plugin.getCommand("메뉴")?.setExecutor(menuUserCommand)
    }
}