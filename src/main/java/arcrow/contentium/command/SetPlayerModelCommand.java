package arcrow.contentium.command;

import arcrow.contentium.Contentium;
import arcrow.contentium.lib.Reference;
import arcrow.contentium.network.SetPlayerModelMessage;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

public class SetPlayerModelCommand extends CommandBase {

	public static final String MODEL_KEY = Reference.MOD_ID + "_model";
	public static final int MODEL_DATA_KEY = 31;

	@Override
	public int getRequiredPermissionLevel() {
		return 0;
	}

	@Override
	public String getCommandName() {
		return "setPlayerModel";
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		return "setPlayerModel <alex/steve>";
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) {
		if (args.length != 1 || !"alex".equals(args[0].toLowerCase()) && !"steve".equals(args[0].toLowerCase()))
			throw new WrongUsageException(getCommandUsage(sender));

		if (sender instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) sender;
			NBTTagCompound nbt = player.getEntityData();
			boolean isAlex = "alex".equals(args[0].toLowerCase());
			nbt.setBoolean(MODEL_KEY, isAlex);
			Contentium.networkWrapper.sendToAll(new SetPlayerModelMessage(player, isAlex));
		}
	}
}