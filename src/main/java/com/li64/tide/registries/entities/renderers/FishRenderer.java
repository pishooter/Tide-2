package com.li64.tide.registries.entities.renderers;

import com.li64.tide.Tide;
import com.li64.tide.client.TideRenderTypes;
import com.li64.tide.data.fishing.FishData;
import com.li64.tide.registries.entities.fish.ShinyFish;
import com.li64.tide.registries.entities.models.FishModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Mob;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class FishRenderer<M extends FishModel> extends MobRenderer<Mob, FishModel> {
    private final ResourceLocation texLocation;
    private final double xTiltScale;
    private final float swimAnimSpeed;
    private final float swimAnimScale;
    private final boolean renderTranslucent;

    public FishRenderer(String key, M model, EntityRendererProvider.Context context) {
        super(context, model, model.shadowRadius());
        this.texLocation = Tide.resource("textures/entity/fish/" + key + ".png");
        this.xTiltScale = model.xTiltScale();
        this.swimAnimSpeed = model.swimAnimSpeed();
        this.swimAnimScale = model.swimAnimScale();
        this.renderTranslucent = model.renderTranslucent();
    }

    public @NotNull ResourceLocation getTextureLocation() {
        return this.texLocation;
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull Mob entity) {
        if (entity instanceof ShinyFish shiny && shiny.tide$isShiny() && hasCustomShinySprite(entity)) {
                return this.getTextureLocation().withPath(path ->
                        path.replace(".png", "_shiny.png"));
        }
        return this.getTextureLocation();
    }

    @Override
    //? if >=1.21 {
    protected void setupRotations(Mob fish, PoseStack poseStack, float bob, float yBodyRot, float partialTick, float scale) {
        super.setupRotations(fish, poseStack, bob, yBodyRot, partialTick, scale);
    //?} else {
    /*protected void setupRotations(Mob fish, PoseStack poseStack, float bob, float yBodyRot, float partialTick) {
        super.setupRotations(fish, poseStack, bob, yBodyRot, partialTick);
    *///?}
        float f = swimAnimScale * 4.3f * Mth.sin(swimAnimSpeed * (fish.tickCount + partialTick));
        poseStack.mulPose(Axis.YP.rotationDegrees(f));
        if (!(fish.isInWaterOrBubble() || fish.isInLava()) && flipInAir()) {
            poseStack.translate(0.1f, 0.1f, -0.1f);
            poseStack.mulPose(Axis.ZP.rotationDegrees(90f));
        }
        else {
            // temp workaround for a bug with alex's caves
            float tilt = Mth.clamp((float) (fish.getDeltaMovement().y() * 1.5 * xTiltScale), -Mth.HALF_PI, Mth.HALF_PI);
            if (Tide.PLATFORM.isModLoaded("alexscaves")) poseStack.mulPose(Axis.XP.rotation(tilt));
            else {
                fish.setXRot(tilt);
                poseStack.mulPose(Axis.XP.rotation(Mth.lerp(partialTick, fish.xRotO, fish.getXRot())));
            }
        }
    }

    private boolean flipInAir() {
        return model.flipInAir();
    }

    @Override
    protected @Nullable RenderType getRenderType(@NotNull Mob fish, boolean isVisible, boolean renderTranslucent, boolean appearsGlowing) {
        ResourceLocation texture = this.getTextureLocation(fish);
        if (fish instanceof ShinyFish shiny && shiny.tide$isShiny()) {
            if (!hasCustomShinySprite(fish)) return TideRenderTypes.shinyEntity(texture);
        }
        if (this.renderTranslucent) return RenderType.entityTranslucent(texture);
        return super.getRenderType(fish, isVisible, renderTranslucent, appearsGlowing);
    }

    private static boolean hasCustomShinySprite(@NotNull Mob fish) {
        FishData data = FishData.get(fish).orElse(null);
        return data != null && data.shinyData().sprite().isPresent();
    }

    @Override
    protected int getBlockLightLevel(@NotNull Mob fish, @NotNull BlockPos pos) {
        if (fish instanceof ShinyFish shiny && shiny.tide$isShiny()) return 15;
        return Math.max(super.getBlockLightLevel(fish, pos), model.brightnessOverride());
    }
}
