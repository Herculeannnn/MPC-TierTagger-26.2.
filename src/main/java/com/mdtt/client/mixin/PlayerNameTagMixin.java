
package com.mdtt.client.mixin;

import com.mdtt.client.hud.NameTagRenderer;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.player.AvatarRenderer;
import net.minecraft.client.renderer.entity.state.AvatarRenderState;
import net.minecraft.client.renderer.state.CameraRenderState;
import com.mojang.blaze3d.vertex.PoseStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AvatarRenderer.class)
public class PlayerNameTagMixin {

    @Inject(method = "submitNameTag", at = @At("TAIL"))
    private void mdtt$addTierTag(
            AvatarRenderState state,
            PoseStack poseStack,
            SubmitNodeCollector collector,
            CameraRenderState cameraState,
            CallbackInfo ci
    ) {
        if (state.playerName == null) {
            return;
        }

        String username = state.playerName.getString();

        var tierLabel = NameTagRenderer.getTierLabel(username);

        if (tierLabel != null && !tierLabel.equals(net.minecraft.network.chat.Component.empty())) {
            collector.submitNameTag(
                    poseStack,
                    null,
                    10,
                    tierLabel,
                    false,
                    0xF000F0,
                    0.0,
                    cameraState
            );
        }
    }
}
