package kr.cosine.menu.config.module

import kr.cosine.menu.config.SettingConfig
import kr.hqservice.framework.bukkit.core.component.module.Module
import kr.hqservice.framework.bukkit.core.component.module.Setup

@Module
class ConfigModule(
    private val settingConfig: SettingConfig
) {
    @Setup
    fun setup() {
        settingConfig.load()
    }
}