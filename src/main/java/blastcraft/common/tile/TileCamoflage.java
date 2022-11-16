package blastcraft.common.tile;

import blastcraft.registers.BlastcraftBlockTypes;
import blastcraft.registers.BlastcraftBlocks;
import electrodynamics.prefab.tile.GenericTile;
import electrodynamics.prefab.tile.components.ComponentType;
import electrodynamics.prefab.tile.components.type.ComponentPacketHandler;
import electrodynamics.prefab.tile.components.type.ComponentTickable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class TileCamoflage extends GenericTile {

	public Block block = BlastcraftBlocks.blockCamoflage;

	public TileCamoflage(BlockPos worldPosition, BlockState blockState) {
		super(BlastcraftBlockTypes.TILE_CAMOFLAGE.get(), worldPosition, blockState);
		addComponent(new ComponentTickable().tickCommon(this::tickCommon));
		addComponent(new ComponentPacketHandler().addCustomPacketReader(this::readCustomPacket).addCustomPacketWriter(this::writeCustomPacket));
	}

	@Override
	public void saveAdditional(CompoundTag compound) {
		compound.putString("blockId", block == null ? "null" : Registry.BLOCK.getKey(block).toString());
		super.saveAdditional(compound);
	}

	@Override
	public void load(CompoundTag compound) {
		super.load(compound);
		String read = compound.getString("blockId");
		if (!"null".equals(read)) {
			block = Registry.BLOCK.get(new ResourceLocation(read));
		}
	}

	public void readCustomPacket(CompoundTag nbt) {
		String read = nbt.getString("blockId");
		if (!"null".equals(read)) {
			block = Registry.BLOCK.get(new ResourceLocation(read));
		}
	}

	public void writeCustomPacket(CompoundTag nbt) {
		saveAdditional(nbt);
	}

	public void tickCommon(ComponentTickable component) {
		if (component.getTicks() % 20 == 0) {
			this.<ComponentPacketHandler>getComponent(ComponentType.PacketHandler).sendCustomPacket();
		}
	}
}
