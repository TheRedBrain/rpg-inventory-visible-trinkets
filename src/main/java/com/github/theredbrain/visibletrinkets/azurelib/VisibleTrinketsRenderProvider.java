package com.github.theredbrain.visibletrinkets.azurelib;

//import mod.azure.azurelib.common.api.common.animatable.GeoItem;
//import mod.azure.azurelib.common.internal.client.RenderProvider;
import mod.azure.azurelib.animatable.GeoItem;
import mod.azure.azurelib.animatable.client.RenderProvider;
import net.minecraft.client.model.Model;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public interface VisibleTrinketsRenderProvider extends RenderProvider {
    VisibleTrinketsRenderProvider VISIBLE_TRINKETS_DEFAULT = new VisibleTrinketsRenderProvider() {};

    static VisibleTrinketsRenderProvider of(ItemStack itemStack) {
        return of(itemStack.getItem());
    }

    static VisibleTrinketsRenderProvider of(Item item) {
        if(item instanceof GeoItem geoItem){
            return (VisibleTrinketsRenderProvider)geoItem.getRenderProvider().get();
        }

        return VISIBLE_TRINKETS_DEFAULT;
    }

    default Model getGenericTrinketModel(LivingEntity livingEntity, ItemStack itemStack, String slotGroup, String slotName,  BipedEntityModel<LivingEntity> original, boolean slim) {
        BipedEntityModel<LivingEntity> replacement = getHumanoidTrinketModel(livingEntity, itemStack, slotGroup, slotName, original);

        if (replacement != original) {
            original.copyBipedStateTo(replacement);
            return replacement;
        }

        return original;
    }
    default BipedEntityModel<LivingEntity> getHumanoidTrinketModel(LivingEntity livingEntity, ItemStack itemStack, String slotGroup, String slotName, BipedEntityModel<LivingEntity> original) {
        return original;
    }
}
