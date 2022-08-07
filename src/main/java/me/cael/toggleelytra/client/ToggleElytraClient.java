package me.cael.toggleelytra.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.entity.event.v1.EntityElytraEvents;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

@Environment(EnvType.CLIENT)
public class ToggleElytraClient implements ClientModInitializer {

    public static boolean elytraToggle = true;

    private static final KeyBinding elytraToggleKey = new KeyBinding(
            "key.toggleelytra.toggle",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_H,
            "key.categories.gameplay"
    );

    @Override
    public void onInitializeClient() {
        KeyBindingHelper.registerKeyBinding(elytraToggleKey);
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (elytraToggleKey.wasPressed()) {
                elytraToggle = !elytraToggle;
                client.options.write();
                client.player.sendMessage(ScreenTexts.composeToggleText(Text.translatable("message.toggleelytra"), elytraToggle), true);
            }
        });
        EntityElytraEvents.ALLOW.register(entity -> elytraToggle);
    }
}