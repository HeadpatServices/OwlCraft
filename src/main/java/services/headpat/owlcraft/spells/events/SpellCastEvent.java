package services.headpat.owlcraft.spells.events;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Entity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import services.headpat.owlcraft.spells.Spell;

public class SpellCastEvent extends SpellEvent implements Cancellable {
	@Getter
	@Setter
	private boolean cancelled = false;
	@Getter
	@Setter
	private int level;
	@Nullable
	private final ItemStack glyphStack;

	public SpellCastEvent(Spell power, int level, @Nullable ItemStack glyphStack, Entity what) {
		super(power, what);
		this.level = level;
		this.glyphStack = glyphStack;
	}

	public ItemStack getGlyphStack() {
		if (glyphStack == null)
			return null;

		return glyphStack.clone();
	}

	/* Generated for Bukkit */
	private static final HandlerList handlers = new HandlerList();

	public static HandlerList getHandlerList() {
		return (handlers);
	}

	@Override
	public @NotNull HandlerList getHandlers() {
		return (handlers);
	}
}
