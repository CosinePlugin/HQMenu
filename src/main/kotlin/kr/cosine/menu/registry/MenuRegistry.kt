package kr.cosine.menu.registry

import kr.cosine.menu.data.Menu
import kr.hqservice.framework.global.core.component.Bean

@Bean
class MenuRegistry {
    private val menuMap = mutableMapOf<String, Menu>()

    fun setMenuMap(menuMap: Map<String, Menu>) {
        this.menuMap.clear()
        this.menuMap.putAll(menuMap)
    }

    fun findMenu(name: String): Menu? {
        return menuMap[name]
    }

    fun getMenuNames(): List<String> {
        return menuMap.keys.toList()
    }

    fun clear() {
        menuMap.clear()
    }
}