package kr.cosine.menu.command.provider

import kr.cosine.menu.data.Menu
import kr.cosine.menu.registry.MenuRegistry
import kr.hqservice.framework.command.CommandArgumentProvider
import kr.hqservice.framework.command.CommandContext
import kr.hqservice.framework.command.argument.exception.ArgumentFeedback
import kr.hqservice.framework.global.core.component.Component
import org.bukkit.Location

@Component
class MenuArgumentProvider(
    private val menuRegistry: MenuRegistry
) : CommandArgumentProvider<Menu> {
    override suspend fun cast(context: CommandContext, argument: String?): Menu {
        if (argument == null) {
            throw ArgumentFeedback.Message("§c메뉴를 입력해주세요.")
        }
        return menuRegistry.findMenu(argument)
            ?: throw ArgumentFeedback.Message("§c존재하지 않는 메뉴입니다.")
    }

    override suspend fun getTabComplete(context: CommandContext, location: Location?): List<String> {
        return menuRegistry.getMenuNames()
    }
}