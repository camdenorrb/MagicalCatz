package me.camdenorrb.magicalcatz.pathfinding

import me.camdenorrb.magicalcatz.abilities.Ability
import net.minecraft.server.v1_12_R1.EntityInsentient
import net.minecraft.server.v1_12_R1.IRangedEntity
import net.minecraft.server.v1_12_R1.PathfinderGoal


class MagicPathAttack(val abilities: MutableList<Ability>, val iRangedEntity: IRangedEntity) : PathfinderGoal() {

	var index: Int = 0

	val entityInsentient = iRangedEntity as EntityInsentient


	//Called to check if should continue.
	override fun a() = entityInsentient.goalTarget != null


	//Called every tick
	override fun e() {
		abilities[index++].use()
	}

}