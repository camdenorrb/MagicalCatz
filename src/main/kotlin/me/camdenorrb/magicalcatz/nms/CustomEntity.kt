package me.camdenorrb.magicalcatz.nms

import me.camdenorrb.magicalcatz.abilities.Ability
import net.minecraft.server.v1_12_R1.Entity
import net.minecraft.server.v1_12_R1.EntityTypes
import net.minecraft.server.v1_12_R1.MinecraftKey
import org.bukkit.Location
import org.bukkit.craftbukkit.v1_12_R1.CraftWorld
import org.bukkit.entity.EntityType
import kotlin.reflect.KClass


interface CustomEntity {

	val entity: Entity

	val abilityList: MutableList<Ability>


	fun spawn(loc: Location): Entity  {
		entity.setLocation(loc.x, loc.y, loc.z, loc.yaw, loc.pitch)
		(loc.world as CraftWorld).handle.addEntity(entity)
		return entity
	}


	companion object {
		fun register(vararg entities: Pair<KClass<out Entity>, EntityType>) {
			for ((clazz, type) in entities) {
				val key = MinecraftKey(type.name)
				EntityTypes.b.a(type.typeId.toInt(), key, clazz.java)
				EntityTypes.d.add(key)
			}
		}
	}

}