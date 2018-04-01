package com.flansmod.common;

import com.flansmod.common.FlansMod;
import com.flansmod.common.PlayerHandler;
import com.flansmod.common.teams.TeamsManager;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.EventBus;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;

public class CommonTickHandler {
    public CommonTickHandler() {
        FMLCommonHandler.instance().bus().register((Object)this);
    }

    @SubscribeEvent
    public void tick(TickEvent.ClientTickEvent event) {
        switch (event.phase) {
            case START: {
                break;
            }
            case END: {
                FlansMod.playerHandler.clientTick();
            }
        }
    }

    @SubscribeEvent
    public void tick(TickEvent.ServerTickEvent event) {
        switch (event.phase) {
            case START: {
                break;
            }
            case END: {
                TeamsManager.getInstance().tick();
                FlansMod.playerHandler.serverTick();
                ++FlansMod.ticker;
            }
        }
    }

}

