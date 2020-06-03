package plugin.random;

import org.crandor.cache.def.impl.ItemDefinition;
import org.crandor.game.interaction.OptionHandler;
import org.crandor.game.node.Node;
//import org.crandor.game.node.entity.npc.drop.;
import org.crandor.game.node.entity.npc.drop.DropFrequency;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.item.ChanceItem;
import org.crandor.game.node.item.Item;
import org.crandor.game.world.repository.Repository;
import org.crandor.plugin.Plugin;
import org.crandor.tools.RandomFunction;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.tools.StringUtils;
import org.graalvm.compiler.nodes.extended.ValueAnchorNode;

/**
 * Handles the mystery box item.
 * @author Vexia
 * @author Splinter
 */
@InitializablePlugin
public final class MysteryBoxPlugin extends OptionHandler {

	/**
     * The rewards recieved from a mystery box.
     */
	private final static double COMMON = 600;
	private final static double UNCOMMON = 300;
	private final static double RARE = 120;
	private final static double VERY_RARE = 30;
	private final static double SUPER_RARE = 10;
	private final static double IMPOSSIBLE_RARE = 1;
	private static final ChanceItem[] REWARDS = new ChanceItem[] {
	

    	new ChanceItem(555, 10, 100, COMMON),//water rune
    	new ChanceItem(557, 10, 100, COMMON),//earth rune
    	new ChanceItem(556, 20, 180, COMMON),//air rune
    	new ChanceItem(7937, 1, 150, COMMON),//noted pure ess
    	new ChanceItem(232, 1, 20, COMMON),//snape grass noted
    	new ChanceItem(8779, 1, 15, COMMON),//oak planks
    	new ChanceItem(10476, 1, 25, COMMON),//purple sweets
    	new ChanceItem(226, 1, 3,COMMON),//limpwurt root
    	new ChanceItem(1776, 1, 15, COMMON),//molten glass

		new ChanceItem(8008, 1, 1, UNCOMMON),//lumbridge tab
		new ChanceItem(8007, 1, 1, UNCOMMON),//varrock tab
		new ChanceItem(8009, 1, 1, UNCOMMON),//falador tab
		new ChanceItem(8010, 1, 1, UNCOMMON),//camelot tab
    	new ChanceItem(12158, 5, 18, UNCOMMON),//green charm
    	new ChanceItem(12160, 5, 18, UNCOMMON),//crimson charm
    	new ChanceItem(565, 1, 20, UNCOMMON),//blood rune
    	new ChanceItem(5302, 1, 5, UNCOMMON),//lantadyme seed
    	new ChanceItem(5300, 1, 7, UNCOMMON),//snap seed
    	new ChanceItem(390, 1, 3, UNCOMMON),//raw manta ray
    	new ChanceItem(396, 1, 5, UNCOMMON),//raw sea turtle
    	new ChanceItem(5297, 1, 7, UNCOMMON),//irit seed
    	new ChanceItem(12183, 25, 200, UNCOMMON),//spirit shard
    	new ChanceItem(11255, 1, 1, UNCOMMON),//ninja impling jar
    	new ChanceItem(2358, 1, 10, UNCOMMON),//noted gold bar
    	new ChanceItem(450, 1, 10, UNCOMMON),//addy ore
    	new ChanceItem(8781, 1, 55, UNCOMMON),//teak plank
    	new ChanceItem(8427, 1, 1, UNCOMMON),//bagged yew tree
    	new ChanceItem(6688, 1, 1, UNCOMMON),//sara brew(3)
    	
    	new ChanceItem(985, 1, 1, RARE),//half key
    	new ChanceItem(987, 1, 1, RARE),//half key
		new ChanceItem(5315, 1, 1, RARE),//yew seed
		new ChanceItem(8787, 1, 1, RARE),//marble block
		new ChanceItem(12163, 5, 11,RARE),//blue charm
		new ChanceItem(8429, 1, 1, RARE),//bagged magic tree
		new ChanceItem(1631, 1, 1, RARE),//uncut dstone
		new ChanceItem(9194, 10, 18, RARE),//onyx bolt tips
		new ChanceItem(10394, 1, 1, RARE),//flared trousers



		new ChanceItem(989, 1, 1,  VERY_RARE),//full key
		new ChanceItem(1149, 1, 1, VERY_RARE),//dragon med helm
		new ChanceItem(1319, 1, 1, VERY_RARE),//rune 2h
		new ChanceItem(1359, 1, 1, VERY_RARE),//rune axe
		new ChanceItem(2363, 1, 2, VERY_RARE),//rune bar
		new ChanceItem(5316, 1, 1, VERY_RARE),//magic seed
		new ChanceItem(10398, 1, 1, VERY_RARE),//sleeping cap
		new ChanceItem(13107, 1, 1, VERY_RARE),//sheep mask
		new ChanceItem(10508, 1, 1, VERY_RARE),//wintumber tree
		new ChanceItem(1333, 1, 1,  VERY_RARE),//rune scimitar

		new ChanceItem(4153, 1, 1, SUPER_RARE),//granite maul
		new ChanceItem(1127, 1, 1,  SUPER_RARE),//rune pl8
		new ChanceItem(6809, 1, 1,  SUPER_RARE),//granite legs
		new ChanceItem(3122, 1, 1,  SUPER_RARE),//granite shield
		new ChanceItem(2366, 1, 1,  SUPER_RARE),//shield left half
		new ChanceItem(11256, 1, 1, SUPER_RARE),//dragon impling jar
		new ChanceItem(11902, 1, 1, SUPER_RARE),//enchanted robe set
		new ChanceItem(4131, 1, 1,  SUPER_RARE),//rune booties
		new ChanceItem(12922, 1, 1, SUPER_RARE),//rune spikeshield
		new ChanceItem(12929, 1, 1, SUPER_RARE),//rune berserker shield

		new ChanceItem(1305, 1, 1, IMPOSSIBLE_RARE),//dragon longsword
		new ChanceItem(3204, 1, 1, IMPOSSIBLE_RARE),//dragon halberd
    	new ChanceItem(2577, 1, 1, IMPOSSIBLE_RARE),//ranger boots


	
	};

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ItemDefinition.forId(6199).getConfigurations().put("option:open", this);
		return this;
	}

	@Override
	public boolean handle(Player player, Node node, String option) {
		final ChanceItem item = RandomFunction.getChanceItem(REWARDS);
		final String name = item.getName().toLowerCase();
		final Item box = (Item) node;
		if (player.getInventory().remove(box, box.getSlot(), true)) {
			player.lock(1);
			player.getInventory().add(new Item(item.getId(), RandomFunction.random(item.getMinimumAmount(), item.getMaximumAmount())));
			player.sendMessage("Inside the box you find " + (item.getId() == 995 ? "some" : (StringUtils.isPlusN(name) ? "an" : "a")) + " " + name + "!");
			if(item.getChanceRate() <= 30){
				Repository.sendNews(player.getUsername()+" has just recieved "+item.getAmount()+" x "+item.getName()+" from a Mystery box.");
			}
		}
		return true;
	}
	

	@Override
	public boolean isWalk() {
		return false;
	}

}
