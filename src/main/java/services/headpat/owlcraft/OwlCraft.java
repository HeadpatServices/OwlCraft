package services.headpat.owlcraft;

import lombok.Getter;
import org.bstats.bukkit.Metrics;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import services.headpat.owlcraft.external.GlowAPIController;
import services.headpat.owlcraft.external.MetricsListener;
import services.headpat.owlcraft.spells.SpellManager;
import services.headpat.owlcraft.spells.implementation.fire.FireballSpell;
import services.headpat.owlcraft.spells.implementation.ice.IceSpell;

import java.util.HashMap;

public final class OwlCraft extends JavaPlugin {
	@Getter
	private static OwlCraft instance;
	@Getter
	private SpellManager spellManager;
	@Getter
	private GlowAPIController glowAPIController;
	@Getter
	private Metrics metrics;
	@Getter
	private MetricsListener metricsListener;

	@Override
	public void onLoad() {
		instance = this;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void onEnable() {
		metrics = new Metrics(this, 9241);
		metricsListener = new MetricsListener();
		metrics.addCustomChart(new Metrics.AdvancedPie("spells_crafted", () -> {
			HashMap<String, Integer> map = (HashMap<String, Integer>) metricsListener.getSpellsCrafted().clone();
			metricsListener.getSpellsCrafted().clear();
			return map;
		}));
		metrics.addCustomChart(new Metrics.AdvancedPie("spells_casted", () -> {
			HashMap<String, Integer> map = (HashMap<String, Integer>) metricsListener.getSpellsCasted().clone();
			metricsListener.getSpellsCrafted().clear();
			return map;
		}));
		spellManager = new SpellManager();
		glowAPIController = new GlowAPIController();
		loadSpells(spellManager);
	}

	private static void loadSpells(@NotNull SpellManager spellManager) {
		spellManager.add(new FireballSpell());
		spellManager.add(new IceSpell());
	}
}
