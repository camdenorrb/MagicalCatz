package me.camdenorrb.magicalcatz

import me.camdenorrb.magicalcatz.nms.CustomEntity
import me.camdenorrb.magicalcatz.nms.CustomOcelot
import net.minecraft.server.v1_12_R1.Entity
import org.bukkit.Material.RAW_FISH
import org.bukkit.entity.EntityType.OCELOT
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority.HIGHEST
import org.bukkit.event.Listener
import org.bukkit.event.block.Action.LEFT_CLICK_BLOCK
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.plugin.java.JavaPlugin


class MagicalCatz : Listener, JavaPlugin() {

	val entities = mutableSetOf<Entity>()


	override fun onLoad() = Unit

	override fun onEnable() {
		server.pluginManager.registerEvents(this, this)
		CustomEntity.register(CustomOcelot::class to OCELOT)
	}

	override fun onDisable() = entities.forEach { it.killEntity() }



	@EventHandler(ignoreCancelled = true, priority = HIGHEST)
	fun PlayerInteractEvent.onInteract() {
		if (action != LEFT_CLICK_BLOCK || item?.type != RAW_FISH) return
		entities.add(CustomOcelot(player.world).spawn(player.location))
		isCancelled = true
	}

}