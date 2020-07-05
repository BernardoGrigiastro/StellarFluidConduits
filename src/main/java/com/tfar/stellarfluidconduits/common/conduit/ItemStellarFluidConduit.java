package com.tfar.stellarfluidconduits.common.conduit;

import com.enderio.core.api.client.gui.IAdvancedTooltipProvider;
import com.enderio.core.client.handlers.SpecialTooltipHandler;
import com.tfar.stellarfluidconduits.common.conduit.stellar.StellarFluidConduit;
import com.tfar.stellarfluidconduits.common.conduit.stellar.StellarFluidConduitRenderer;
import com.tfar.stellarfluidconduits.common.config.StellarFluidConduitConfig;
import crazypants.enderio.api.IModObject;
import crazypants.enderio.base.conduit.IConduit;
import crazypants.enderio.base.conduit.IServerConduit;
import crazypants.enderio.conduits.conduit.AbstractItemConduit;
import crazypants.enderio.conduits.conduit.ItemConduitSubtype;
import crazypants.enderio.conduits.conduit.liquid.ILiquidConduit;
import crazypants.enderio.conduits.render.ConduitBundleRenderManager;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class ItemStellarFluidConduit extends AbstractItemConduit implements IAdvancedTooltipProvider {


  public static ItemStellarFluidConduit create(@Nonnull IModObject modObject, @Nullable Block block) {
    return new ItemStellarFluidConduit(modObject);
  }

  public ItemStellarFluidConduit(@Nonnull IModObject modObject) {
    super(modObject, new ItemConduitSubtype(modObject.getUnlocalisedName(), modObject.getRegistryName().toString()));
  }

  @Override
  @SideOnly(Side.CLIENT)
  public void registerRenderers(@Nonnull IModObject modObject) {
    super.registerRenderers(modObject);
    ConduitBundleRenderManager.instance.getConduitBundleRenderer().registerRenderer(new StellarFluidConduitRenderer());
  }

  @Override
  public @Nonnull Class<? extends IConduit> getBaseConduitType() {
    return ILiquidConduit.class;
  }

  @Override
  @SideOnly(Side.CLIENT)
  public void addCommonEntries(@Nonnull ItemStack itemstack, @Nullable EntityPlayer entityplayer, @Nonnull List<String> list, boolean flag) {
  }

  @Override
  @SideOnly(Side.CLIENT)
  public void addBasicEntries(@Nonnull ItemStack itemstack, @Nullable EntityPlayer entityplayer, @Nonnull List<String> list, boolean flag) {

  }

  @Override
  public void addDetailedEntries(@Nonnull ItemStack itemStack, @Nullable EntityPlayer entityPlayer, @Nonnull List<String> list, boolean b) {
    int extractRate = StellarFluidConduitConfig.extractRate.get();
    int maxIo = StellarFluidConduitConfig.maxIO.get();
    String mbt = new TextComponentTranslation("stellarfluidconduits.fluid.millibuckets_tick").getUnformattedComponentText();
    list.add(new TextComponentTranslation("stellarfluidconduits.item_fluid_conduit.tooltip.max_extract").getUnformattedComponentText() + " " + extractRate + mbt);
    list.add(new TextComponentTranslation("stellarfluidconduits.item_fluid_conduit.tooltip.max_io").getUnformattedComponentText() + " " + maxIo + mbt);
    SpecialTooltipHandler.addDetailedTooltipFromResources(list, "enderio.item_liquid_conduit_ender");
  }

  @Override
  public IServerConduit createConduit(@Nonnull ItemStack item, @Nonnull EntityPlayer player) {
    return new StellarFluidConduit();
  }

  @Override
  public boolean shouldHideFacades(@Nonnull ItemStack stack, @Nonnull EntityPlayer player) {
    return true;
  }
}
