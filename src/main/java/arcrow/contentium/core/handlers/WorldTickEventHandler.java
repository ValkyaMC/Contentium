package arcrow.contentium.core.handlers;

import java.util.HashMap;
import java.util.Map;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;
import cpw.mods.fml.common.gameevent.TickEvent.WorldTickEvent;
import cpw.mods.fml.relauncher.Side;
import arcrow.contentium.Contentium;
import arcrow.contentium.IConfigurable;
import arcrow.contentium.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class WorldTickEventHandler {

	private static Map<Block, Block> replacements;
	private boolean isReplacing = false;

	@SubscribeEvent
	public void tick(WorldTickEvent event) {
		if (event.side != Side.SERVER || event.phase != Phase.END || isReplacing)
			return;

		if (replacements == null) {
			replacements = new HashMap<Block, Block>();
			if (Contentium.enableBrewingStands)
				replacements.put(Blocks.brewing_stand, ModBlocks.brewing_stand);
			if (Contentium.enableColourfulBeacons)
				replacements.put(Blocks.beacon, ModBlocks.beacon);
			if (Contentium.enableEnchants)
				replacements.put(Blocks.enchanting_table, ModBlocks.enchantment_table);
			if (Contentium.enableInvertedDaylightSensor)
				replacements.put(Blocks.daylight_detector, ModBlocks.daylight_sensor);
		}

		if (replacements.isEmpty())
			return;

		isReplacing = true;
		World world = event.world;

		for (int i = 0; i < world.loadedTileEntityList.size(); i++) {
			TileEntity tile = (TileEntity) world.loadedTileEntityList.get(i);
			int x = tile.xCoord;
			int y = tile.yCoord;
			int z = tile.zCoord;
			Block replacement = replacements.get(world.getBlock(x, y, z));
			if (replacement != null && ((IConfigurable) replacement).isEnabled()) {
				NBTTagCompound nbt = new NBTTagCompound();
				tile.writeToNBT(nbt);
				if (tile instanceof IInventory) {
					IInventory invt = (IInventory) tile;
					for (int j = 0; j < invt.getSizeInventory(); j++)
						invt.setInventorySlotContents(j, null);
				}
				world.setBlock(x, y, z, replacement);
				TileEntity newTile = world.getTileEntity(x, y, z);
				newTile.readFromNBT(nbt);
				break;
			}
		}
		isReplacing = false;
	}
}