package arcrow.contentium.core.handlers;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import arcrow.contentium.Contentium;
import arcrow.contentium.ModBlocks;
import arcrow.contentium.blocks.PrismarineBlocks;
import arcrow.contentium.client.OpenGLHelper;
import arcrow.contentium.client.PrismarineIcon;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.client.event.RenderPlayerEvent.SetArmorModel;
import net.minecraftforge.client.event.TextureStitchEvent;

public class ClientEventHandler {

	public static final ClientEventHandler INSTANCE = new ClientEventHandler();

	private ClientEventHandler() {
	}

	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void loadTextures(TextureStitchEvent.Pre event) {
		if (Contentium.enablePrismarine)
			if (event.map.getTextureType() == 0) {
				TextureAtlasSprite icon = new PrismarineIcon("prismarine_rough");
				if (event.map.setTextureEntry("prismarine_rough", icon))
					((PrismarineBlocks) ModBlocks.prismarine).setIcon(0, icon);
				else
					((PrismarineBlocks) ModBlocks.prismarine).setIcon(0, event.map.registerIcon("prismarine_rough"));
			}
	}

	@SubscribeEvent
	public void renderPlayerEventPre(RenderPlayerEvent.Pre event) {
		if (Contentium.enableTransparentAmour) {
			OpenGLHelper.enableBlend();
			OpenGLHelper.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		}
	}

	@SubscribeEvent
	public void renderPlayerSetArmour(SetArmorModel event) {
		if (Contentium.enableTransparentAmour) {
			OpenGLHelper.enableBlend();
			OpenGLHelper.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		}
	}

	@SubscribeEvent
	public void renderPlayerEventPost(RenderPlayerEvent.Post event) {
		if (Contentium.enableTransparentAmour)
			OpenGLHelper.disableBlend();
	}
}