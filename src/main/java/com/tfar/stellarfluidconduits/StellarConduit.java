package com.tfar.stellarfluidconduits;

import com.tfar.stellarfluidconduits.common.conduit.StellarFluidConduitObject;
import com.tfar.stellarfluidconduits.common.conduit.stellar.StellarFluidConduit;
import com.tfar.stellarfluidconduits.common.config.Config;
import com.tfar.stellarfluidconduits.common.network.PacketHandler;
import crazypants.enderio.api.addon.IEnderIOAddon;
import crazypants.enderio.base.EnderIO;
import crazypants.enderio.base.conduit.IConduitTexture;
import crazypants.enderio.base.conduit.registry.ConduitDefinition;
import crazypants.enderio.base.conduit.registry.ConduitRegistry;
import crazypants.enderio.base.config.ConfigHandlerEIO;
import crazypants.enderio.base.init.RegisterModObject;
import crazypants.enderio.base.render.registry.TextureRegistry;
import crazypants.enderio.conduits.render.ConduitTexture;
import info.loenwind.autoconfig.ConfigHandler;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.UUID;

@Mod.EventBusSubscriber(modid = ReferenceVariables.MOD_ID)

@Mod(modid = ReferenceVariables.MOD_ID, name = ReferenceVariables.MOD_NAME, version = ReferenceVariables.VERSION, dependencies = ReferenceVariables.DEPENDENCIES)
public class StellarConduit implements IEnderIOAddon {

        public static final IConduitTexture ICON_KEY = new ConduitTexture(
                TextureRegistry.registerTexture("stellarfluidconduits:blocks/liquid_conduit", false), ConduitTexture.arm(0));
        public static final IConduitTexture ICON_CORE_KEY = new ConduitTexture(
                TextureRegistry.registerTexture("stellarfluidconduits:blocks/conduit_core_1", false), ConduitTexture.core());

    private static ConfigHandler configHandler;


    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        configHandler = new ConfigHandlerEIO(event, Config.F);
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        ConduitRegistry.injectMember(new ConduitDefinition(ConduitRegistry.getNetwork(UUID.nameUUIDFromBytes(
                (new ResourceLocation(EnderIO.DOMAIN, "fluid"))
                        .toString().getBytes())),
                UUID.nameUUIDFromBytes("Random UUID".getBytes()),
                StellarFluidConduit.class,
                StellarFluidConduit.class));
        PacketHandler.init(event);
    }

    @SubscribeEvent
    public static void registerBlocksEarly(@Nonnull RegisterModObject event) {
        StellarFluidConduitObject.registerBlocksEarly(event);
    }

    @Override
    @Nullable
    public Configuration getConfiguration() {
        return Config.F.getConfig();
    }
}
