package com.github.theredbrain.visibletrinkets.client.render.model;

import com.github.theredbrain.visibletrinkets.item.VisibleTrinketItem;
import mod.azure.azurelib.model.GeoModel;
import net.minecraft.util.Identifier;

public class VisibleTrinketModel extends GeoModel<VisibleTrinketItem> {
    private final Identifier assetSubPath;
    private final boolean slim;

    public VisibleTrinketModel(Identifier assetSubPath, boolean slim) {
        super();
        this.assetSubPath = assetSubPath;
        this.slim = slim;
    }

    @Override
    public Identifier getModelResource(VisibleTrinketItem animatable) {
        return Identifier.of(assetSubPath.getNamespace(),"geo/item/" + assetSubPath.getPath() + (slim ? "_slim" : "") + ".geo.json");
    }

    @Override
    public Identifier getTextureResource(VisibleTrinketItem animatable) {
        return Identifier.of(assetSubPath.getNamespace(),"textures/item/" + assetSubPath.getPath() + (slim ? "_slim" : "") + ".png");
    }

    @Override
    public Identifier getAnimationResource(VisibleTrinketItem animatable) {
        return null;
    }
}
