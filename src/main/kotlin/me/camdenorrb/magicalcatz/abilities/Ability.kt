package me.camdenorrb.magicalcatz.abilities

import net.minecraft.server.v1_12_R1.Entity


abstract class Ability(val sender: Entity, val name: String) {

	fun use(): Boolean = if (isReady()) { onUse(); true } else false


	open fun isReady() = true

	protected open fun onUse() = Unit

}