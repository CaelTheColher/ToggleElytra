package me.cael.toggleelytra.client.mixin;

import me.cael.toggleelytra.client.ToggleElytraClient;
import net.minecraft.client.option.GameOptions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameOptions.class)
public class MixinGameOptions {
    
    @Inject(at = @At("HEAD"), method = "accept")
    public void accept(GameOptions.Visitor visitor, CallbackInfo ci) {
        ToggleElytraClient.elytraToggle = visitor.visitBoolean("toggleelytra", ToggleElytraClient.elytraToggle);
    }

}