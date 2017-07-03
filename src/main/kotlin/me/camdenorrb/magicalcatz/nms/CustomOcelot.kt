package me.camdenorrb.magicalcatz.nms

import me.camdenorrb.magicalcatz.abilities.Ability
import me.camdenorrb.magicalcatz.ext.getPrivateField
import net.minecraft.server.v1_12_R1.*
import org.bukkit.World
import org.bukkit.craftbukkit.v1_12_R1.CraftWorld


class CustomOcelot(world: World) : CustomEntity, IRangedEntity, EntityOcelot((world as CraftWorld).handle) {

	override val entity = this

	override val abilityList = mutableListOf<Ability>()


	init {

		getPrivateField<PathfinderGoalSelector, MutableSet<*>>("b", goalSelector)?.clear()
		getPrivateField<PathfinderGoalSelector, MutableSet<*>>("c", goalSelector)?.clear()
		getPrivateField<PathfinderGoalSelector, MutableSet<*>>("b", targetSelector)?.clear()
		getPrivateField<PathfinderGoalSelector, MutableSet<*>>("c", targetSelector)?.clear()

		goalSelector.a(1, PathfinderGoalFloat(this))
		goalSelector.a(2, PathfinderGoalArrowAttack(this, 1.0, 20, 60, 15.0F))
		goalSelector.a(3, PathfinderGoalRandomStrollLand(this, 0.8, 1F))
		goalSelector.a(4, PathfinderGoalAvoidTarget(this, EntityHuman::class.java, 6.0f, 1.0, 1.2))
		goalSelector.a(5, PathfinderGoalRandomLookaround(this))
		goalSelector.a(7, PathfinderGoalJumpOnBlock(this, 1.0))
		goalSelector.a(8, PathfinderGoalLookAtPlayer(this, EntityHuman::class.java, 10.0F))
		goalSelector.a(9, PathfinderGoalRandomLookaround(this))

		//targetSelector.a(1, PathfinderGoalHurtByTarget(this, false))
		targetSelector.a(1, PathfinderGoalNearestAttackableTarget(this, EntityHuman::class.java, false, false))
		targetSelector.a(2, PathfinderGoalNearestAttackableTarget(this, EntityWolf::class.java, false, false))
	}


	override fun p(p0: Boolean) = super.p(p0)

	override fun a(target: EntityLiving, f: Float) {

		val aimX = target.locX - this.locX
		val aimY = target.boundingBox.b + (target.length / 3) - this.locY
		val aimZ = target.locZ - this.locZ

		val aimYaw = Math.sqrt(aimX * aimX + aimZ * aimZ)

		val arrow = EntityTippedArrow(world, this).apply { a(target, f); a(f) }
		arrow.shoot(aimX, aimY + aimYaw * 0.2, aimZ, 1.6F, 2F)

		world.addEntity(arrow)
	}



/*	public void a(EntityLiving entityliving, float f) {
		EntityArrow entityarrow = this.a(f);
		double d0 = entityliving.locX - this.locX;
		double d1 = entityliving.getBoundingBox().b + (double)(entityliving.length / 3.0F) - entityarrow.locY;
		double d2 = entityliving.locZ - this.locZ;
		double d3 = (double)MathHelper.sqrt(d0 * d0 + d2 * d2);
		entityarrow.shoot(d0, d1 + d3 * 0.20000000298023224D, d2, 1.6F, (float)(14 - this.world.getDifficulty().a() * 4));
		EntityShootBowEvent event = CraftEventFactory.callEntityShootBowEvent(this, this.getItemInMainHand(), entityarrow, 0.8F);
		if(event.isCancelled()) {
			event.getProjectile().remove();
		} else {
			if(event.getProjectile() == entityarrow.getBukkitEntity()) {
				this.world.addEntity(entityarrow);
			}

			this.a(SoundEffects.gW, 1.0F, 1.0F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
		}
	}*/


}