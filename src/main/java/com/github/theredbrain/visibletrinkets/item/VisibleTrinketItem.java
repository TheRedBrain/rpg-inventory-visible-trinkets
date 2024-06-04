package com.github.theredbrain.visibletrinkets.item;

import com.github.theredbrain.visibletrinkets.azurelib.VisibleTrinketsRenderProvider;
import com.github.theredbrain.visibletrinkets.client.render.renderer.AbstractVisibleTrinketRenderer;
import com.github.theredbrain.visibletrinkets.client.render.renderer.VisibleTrinketRenderer;
import dev.emi.trinkets.api.TrinketItem;
import mod.azure.azurelib.animatable.GeoItem;
import mod.azure.azurelib.core.animatable.instance.AnimatableInstanceCache;
import mod.azure.azurelib.core.animation.AnimatableManager;
import mod.azure.azurelib.util.AzureLibUtil;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

import java.util.function.Consumer;
import java.util.function.Supplier;

public abstract class VisibleTrinketItem extends TrinketItem implements GeoItem {
    public final Identifier assetSubPath;
    private final AnimatableInstanceCache cache = AzureLibUtil.createInstanceCache(this);
    private final Supplier<Object> renderProvider = GeoItem.makeRenderer(this);

    public VisibleTrinketItem(Identifier assetSubPath, Settings settings) {
        super(settings);
        this.assetSubPath = assetSubPath;
    }

    @Override
    public Supplier<Object> getRenderProvider() {
        return renderProvider;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {}

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    public void createRenderer(Consumer<Object> consumer) {
        consumer.accept(new VisibleTrinketsRenderProvider() {
            private AbstractVisibleTrinketRenderer<?> renderer;
            @Override
            public BipedEntityModel<LivingEntity> getGenericTrinketModel(LivingEntity livingEntity, ItemStack itemStack, String slotGroup, String slotName, BipedEntityModel<LivingEntity> original, boolean slim) {
                if (this.renderer == null) {
                    this.renderer = new VisibleTrinketRenderer(VisibleTrinketItem.this.assetSubPath, slim);
                }
                this.renderer.prepForRender(livingEntity, itemStack, slotGroup, slotName, original);
                return this.renderer;
            }
        });
    }
}
