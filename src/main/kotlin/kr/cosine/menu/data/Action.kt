package kr.cosine.menu.data

import kr.cosine.menu.enums.Permission
import org.bukkit.entity.Player

interface Action {
    companion object {
        fun of(line: String): Action? {
            when (val actionType = line.substringBefore(":").uppercase()) {
                "PLAYER", "CONSOLE" -> {
                    val permission = Permission.valueOf(actionType)
                    return Command(permission, line.substringAfter(":"))
                }

                "DELAY" -> {
                    return Delay(line.substringAfter(":").toLong())
                }

                "SOUND" -> {
                    val sound = line.substringAfter(":")
                    val name = sound.substringBefore(":")
                    val volume = sound.substringAfter(":").substringBefore(":").toFloat()
                    val pitch = sound.substringAfterLast(":").toFloat()
                    return Sound(name, volume, pitch)
                }

                else -> {
                    return null
                }
            }
        }
    }

    suspend fun action(player: Player)
}