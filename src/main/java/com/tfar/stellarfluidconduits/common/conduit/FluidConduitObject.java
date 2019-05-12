package com.tfar.stellarfluidconduits.common.conduit;

import com.enderio.core.common.util.NullHelper;
import crazypants.enderio.api.IModObject;
import crazypants.enderio.api.IModTileEntity;
import crazypants.enderio.base.EnderIOTab;
 import crazypants.enderio.base.init.IModObjectBase;
import crazypants.enderio.base.init.ModObjectRegistry;
import crazypants.enderio.base.init.RegisterModObject;
import com.tfar.stellarfluidconduits.ReferenceVariables;
 import net.minecraft.block.Block;
 import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.function.BiFunction;
import java.util.function.Function;

public enum FluidConduitObject implements IModObjectBase {
    itemFluidConduit(ItemStellarFluidConduit::create),
;
    public static void registerBlocksEarly(@Nonnull RegisterModObject event) {
        event.register(FluidConduitObject.class);
    }

    @Nonnull
    protected final String unlocalisedName;

    @Nullable
    protected Block block;
    @Nullable
    protected Item item;

    @Nullable
    protected final Function<IModObject, Block> blockMaker;

    @Nullable
    protected final BiFunction<IModObject, Block, Item> itemMaker;

    @Nullable
    protected final IModTileEntity modTileEntity;

    FluidConduitObject(@Nonnull BiFunction<IModObject, Block, Item> itemMaker) {
        this(null, itemMaker, null);
    }

    FluidConduitObject(@Nonnull Function<IModObject, Block> blockMaker) {
        this(blockMaker, null, null);
    }

    FluidConduitObject(@Nonnull Function<IModObject, Block> blockMaker,
                       @Nonnull BiFunction<IModObject, Block, Item> itemMaker) {
        this(blockMaker, itemMaker, null);
    }

    FluidConduitObject(@Nonnull Function<IModObject, Block> blockMaker, @Nonnull IModTileEntity modTileEntity) {
        this(blockMaker, null, modTileEntity);
    }

    FluidConduitObject(@Nullable Function<IModObject, Block> blockMaker,
                     @Nullable BiFunction<IModObject, Block, Item> itemMaker, @Nullable IModTileEntity modTileEntity) {
        this.unlocalisedName = ModObjectRegistry.sanitizeName(NullHelper.notnullJ(name(), "Enum.name()"));
        this.blockMaker = blockMaker;
        this.itemMaker = itemMaker;
        if (blockMaker == null && itemMaker == null) {
            throw new RuntimeException(this + " unexpectedly is neither a Block nor an Item.");
        }
        this.modTileEntity = null;
    }

    @Nullable
    @Override
    @Deprecated
    public final Class<?> getClazz() {
        return null;
    }

    @Override
    public final void setItem(@Nullable Item obj) {
        this.item = obj;
    }

    @Override
    public final void setBlock(@Nullable Block obj) {
        this.block = obj;
    }

    @Nonnull
    @Override
    public final String getUnlocalisedName() {
        return unlocalisedName;
    }

    @Nonnull
    @Override
    public ResourceLocation getRegistryName() {
        return new ResourceLocation(ReferenceVariables.MOD_ID, getUnlocalisedName());
    }

    @Nullable
    @Override
    public final Block getBlock() {
        return block;
    }

    @Nullable
    @Override
    public final Item getItem() {
        return item;
    }

    @Override
    @Nullable
    public IModTileEntity getTileEntity() {
        return modTileEntity;
    }

    @Nonnull
    @Override
    public final <B extends Block> B apply(@Nonnull B blockIn) {
        blockIn.setCreativeTab(EnderIOTab.tabEnderIOConduits);
        return IModObjectBase.super.apply(blockIn);
    }

    @Nonnull
    @Override
    public Function<IModObject, Block> getBlockCreator() {
        return blockMaker != null ? blockMaker : mo -> null;
    }

    @Nonnull
    @Override
    public BiFunction<IModObject, Block, Item> getItemCreator() {
        return NullHelper.first(itemMaker, IModObject.WithBlockItem.itemCreator);
    }

    @Override
    @Nullable
    @Deprecated
    public final String getBlockMethodName() {
        return null;
    }

    @Override
    @Nullable
    @Deprecated
    public final String getItemMethodName() {
        return null;
    }
}