package com.matsss2.golosimod.events;

import com.matsss2.golosimod.GolosiMod;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = GolosiMod.MOD_ID)
public class FrstJoin {
	
	private static final String NBT_KEY = GolosiMod.MOD_ID + ".first_joined";
	
	@SubscribeEvent
    public static void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent event) {
        PlayerEntity player = event.getPlayer();
        if (player instanceof ServerPlayerEntity) {

            CompoundNBT data = player.getPersistentData();
            CompoundNBT persistent;
            if (!data.contains(PlayerEntity.PERSISTED_NBT_TAG)) {
                data.put(PlayerEntity.PERSISTED_NBT_TAG, (persistent = new CompoundNBT()));
            } else {
                persistent = data.getCompound(PlayerEntity.PERSISTED_NBT_TAG);
            }

            if (!persistent.contains(NBT_KEY)) {
                persistent.putBoolean(NBT_KEY, true);
                
                player.sendMessage(new StringTextComponent("\u00A7a" + "\u00A7o" + player.getDisplayName().getString() + "\u00A72" + " joined the for the first time!"));
            } else {
 
                player.sendMessage(new StringTextComponent("\u00A72" + "Welcome back, " + "\u00A7a" + "\u00A7o" + player.getDisplayName().getString() + "!"));
            }
        }
    }
}
