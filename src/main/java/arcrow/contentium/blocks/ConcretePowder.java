package arcrow.contentium.blocks;

import java.util.Random;

import arcrow.contentium.Contentium;
import arcrow.contentium.IConfigurable;
import arcrow.contentium.ModBlocks;
import arcrow.contentium.core.utils.Utils;
import arcrow.contentium.lib.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDynamicLiquid;
import net.minecraft.block.BlockStaticLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class ConcretePowder extends BlockGeneric implements IConfigurable {
	public static boolean fallInstantly;
    int b = 1;

	public static final int WHITE = 1;
	public static final int ORANGE = 2;
	public static final int MAGENTA = 3;
	public static final int LIGHT_BLUE = 4;
	public static final int YELLOW = 5;
	public static final int LIME = 6;
	public static final int PINK = 7;
	public static final int GRAY = 8;
	public static final int LIGHT_GRAY = 9;
	public static final int CYAN = 10;
	public static final int PURPLE = 11;
	public static final int BLUE = 12;
	public static final int BROWN = 13;
	public static final int GREEN = 14;
	public static final int RED = 15;
	public static final int BLACK = 16;

	public ConcretePowder() {
		super(Material.sand, "", "white_concrete_powder", "orange_concrete_powder", "magenta_concrete_powder",
				"light_blue_concrete_powder", "yellow_concrete_powder", "lime_concrete_powder", "pink_concrete_powder",
				"gray_concrete_powder", "light_gray_concrete_powder", "cyan_concrete_powder", "purple_concrete_powder",
				"blue_concrete_powder", "brown_concrete_powder", "green_concrete_powder", "red_concrete_powder",
				"black_concrete_powder");
		startMeta = 1;
		setHardness(0.5F);
		setStepSound(soundTypeSand);
		setBlockTextureName(Reference.MOD_ID + ":concrete/");
		setBlockName(Utils.getUnlocalisedName("powder"));
		setCreativeTab(CreativeTabs.tabBlock);
	}

	@Override
	public boolean isEnabled() {
		return Contentium.enableConcretePowder;
	}
	
	//Sys Falling
	
	 /**
     * Called whenever the block is added into the world. Args: world, x, y, z
     */
    public void onBlockAdded(World p_149726_1_, int p_149726_2_, int p_149726_3_, int p_149726_4_)
    {
        p_149726_1_.scheduleBlockUpdate(p_149726_2_, p_149726_3_, p_149726_4_, this, this.tickRate(p_149726_1_));
    }

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor Block
     */
    public void onNeighborBlockChange(World p_149695_1_, int p_149695_2_, int p_149695_3_, int p_149695_4_, Block p_149695_5_)
    {
    	p_149695_1_.scheduleBlockUpdate(p_149695_2_, p_149695_3_, p_149695_4_, this, this.tickRate(p_149695_1_)); // A PATCH

    	Block b0 = p_149695_1_.getBlock(p_149695_2_+1, p_149695_3_, p_149695_4_);
		Block b1 = p_149695_1_.getBlock(p_149695_2_, p_149695_3_+1, p_149695_4_);
		Block b2 = p_149695_1_.getBlock(p_149695_2_, p_149695_3_, p_149695_4_+1);
		Block b3 = p_149695_1_.getBlock(p_149695_2_-1, p_149695_3_, p_149695_4_);
		Block b4 = p_149695_1_.getBlock(p_149695_2_, p_149695_3_-1, p_149695_4_);
		Block b5 = p_149695_1_.getBlock(p_149695_2_, p_149695_3_, p_149695_4_-1);
		int data = p_149695_1_.getBlockMetadata(p_149695_2_, p_149695_3_,  p_149695_4_);
		
		if((b0 instanceof BlockStaticLiquid || b0 instanceof BlockDynamicLiquid) && b0.getMaterial() == Material.water) {
			p_149695_1_.setBlock(p_149695_2_, p_149695_3_,  p_149695_4_, ModBlocks.concrete, data, 1);
			p_149695_1_.markBlockForUpdate(p_149695_2_, p_149695_3_, p_149695_4_);
		}else if((b1 instanceof BlockStaticLiquid || b1 instanceof BlockDynamicLiquid) && b1.getMaterial() == Material.water) {
			p_149695_1_.setBlock(p_149695_2_, p_149695_3_,  p_149695_4_, ModBlocks.concrete, data, 1);
			p_149695_1_.markBlockForUpdate(p_149695_2_, p_149695_3_, p_149695_4_);
		}else if((b2 instanceof BlockStaticLiquid || b2 instanceof BlockDynamicLiquid) && b2.getMaterial() == Material.water) {
			p_149695_1_.setBlock(p_149695_2_, p_149695_3_,  p_149695_4_, ModBlocks.concrete, data, 1);
			p_149695_1_.markBlockForUpdate(p_149695_2_, p_149695_3_, p_149695_4_);
		}else if((b3 instanceof BlockStaticLiquid || b3 instanceof BlockDynamicLiquid) && b3.getMaterial() == Material.water) {
			p_149695_1_.setBlock(p_149695_2_, p_149695_3_,  p_149695_4_, ModBlocks.concrete, data, 1);
			p_149695_1_.markBlockForUpdate(p_149695_2_, p_149695_3_, p_149695_4_);
		}else if((b4 instanceof BlockStaticLiquid || b4 instanceof BlockDynamicLiquid) && b4.getMaterial() == Material.water) {
			p_149695_1_.setBlock(p_149695_2_, p_149695_3_,  p_149695_4_, ModBlocks.concrete, data, 1);
			p_149695_1_.markBlockForUpdate(p_149695_2_, p_149695_3_, p_149695_4_);
		}else if((b5 instanceof BlockStaticLiquid || b5 instanceof BlockDynamicLiquid) && b5.getMaterial() == Material.water) {
			p_149695_1_.setBlock(p_149695_2_, p_149695_3_,  p_149695_4_, ModBlocks.concrete, data, 1);
			p_149695_1_.markBlockForUpdate(p_149695_2_, p_149695_3_, p_149695_4_);
		}
		
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World p_149674_1_, int p_149674_2_, int p_149674_3_, int p_149674_4_, Random p_149674_5_)
    {
        if (!p_149674_1_.isRemote)
        {
            this.func_149830_m(p_149674_1_, p_149674_2_, p_149674_3_, p_149674_4_);
        }
    }

    private void func_149830_m(World p_149830_1_, int p_149830_2_, int p_149830_3_, int p_149830_4_)
    {
        if (func_149831_e(p_149830_1_, p_149830_2_, p_149830_3_ - 1, p_149830_4_) && p_149830_3_ >= 0)
        {
            byte b0 = 32;

            if (!fallInstantly && p_149830_1_.checkChunksExist(p_149830_2_ - b0, p_149830_3_ - b0, p_149830_4_ - b0, p_149830_2_ + b0, p_149830_3_ + b0, p_149830_4_ + b0))
            {
                if (!p_149830_1_.isRemote)
                {
                    EntityFallingBlock entityfallingblock = new EntityFallingBlock(p_149830_1_, (double)((float)p_149830_2_ + 0.5F), (double)((float)p_149830_3_ + 0.5F), (double)((float)p_149830_4_ + 0.5F), this, p_149830_1_.getBlockMetadata(p_149830_2_, p_149830_3_, p_149830_4_));
                    this.func_149829_a(entityfallingblock);
                    p_149830_1_.spawnEntityInWorld(entityfallingblock);
                }
            }
            else
            {
                p_149830_1_.setBlockToAir(p_149830_2_, p_149830_3_, p_149830_4_);

                while (func_149831_e(p_149830_1_, p_149830_2_, p_149830_3_ - 1, p_149830_4_) && p_149830_3_ > 0)
                {
                    --p_149830_3_;
                }

                if (p_149830_3_ > 0)
                {
                    p_149830_1_.setBlock(p_149830_2_, p_149830_3_, p_149830_4_, this);
                }
            }
        }
    }

    protected void func_149829_a(EntityFallingBlock p_149829_1_) {}

    /**
     * How many world ticks before ticking
     */
    public int tickRate(World p_149738_1_)
    {
        return 2;
    }

    public static boolean func_149831_e(World p_149831_0_, int p_149831_1_, int p_149831_2_, int p_149831_3_)
    {
        Block block = p_149831_0_.getBlock(p_149831_1_, p_149831_2_, p_149831_3_);

        if (block.isAir(p_149831_0_, p_149831_1_, p_149831_2_, p_149831_3_))
        {
            return true;
        }
        else if (block == Blocks.lava)
        {
            return true;
        }
        else
        {
            //TODO: King, take a look here when doing liquids!
            Material material = block.getMaterial();
            return material == Material.water ? true : material == Material.lava;
        }
    }

    public void func_149828_a(World p_149828_1_, int p_149828_2_, int p_149828_3_, int p_149828_4_, int p_149828_5_) {}
       
    
    
    @Override
	public int onBlockPlaced(World w, int x, int y, int z, int p_149660_5_,	float p_149660_6_, float p_149660_7_, float p_149660_8_, int p_149660_9_) {
		Block b0 = w.getBlock(x+1, y, z);
		Block b1 = w.getBlock(x, y+1, z);
		Block b2= w.getBlock(x, y, z+1);
		Block b3 = w.getBlock(x-1, y, z);
		Block b4 = w.getBlock(x, y-1, z);
		Block b5 = w.getBlock(x, y, z-1);
		int data = w.getBlockMetadata(x, y, z);
				
		if((b0 instanceof BlockStaticLiquid || b0 instanceof BlockDynamicLiquid) && b0.getMaterial() == Material.water) {
			w.setBlock(x, y, z, ModBlocks.concrete, data, 1);
		}else
		if((b1 instanceof BlockStaticLiquid || b1 instanceof BlockDynamicLiquid) && b1.getMaterial() == Material.water) {
			w.setBlock(x, y, z, ModBlocks.concrete, data, 1);
		}else
		if((b2 instanceof BlockStaticLiquid || b2 instanceof BlockDynamicLiquid) && b2.getMaterial() == Material.water) {
			w.setBlock(x, y, z, ModBlocks.concrete, data, 1);
		}else
		if((b3 instanceof BlockStaticLiquid || b3 instanceof BlockDynamicLiquid) && b3.getMaterial() == Material.water) {
			w.setBlock(x, y, z, ModBlocks.concrete, data, 1);
		}else
		if((b4 instanceof BlockStaticLiquid || b4 instanceof BlockDynamicLiquid) && b4.getMaterial() == Material.water) {
			w.setBlock(x, y, z, ModBlocks.concrete, data, 1);
		}else
		if((b5 instanceof BlockStaticLiquid || b5 instanceof BlockDynamicLiquid) && b5.getMaterial() == Material.water) {
			w.setBlock(x, y, z, ModBlocks.concrete, data, 1);
		}
		return p_149660_9_;
	}

}