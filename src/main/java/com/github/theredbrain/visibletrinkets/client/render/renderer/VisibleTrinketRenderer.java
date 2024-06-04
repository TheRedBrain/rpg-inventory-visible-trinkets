package com.github.theredbrain.visibletrinkets.client.render.renderer;

import com.github.theredbrain.visibletrinkets.client.render.model.VisibleTrinketModel;
import com.github.theredbrain.visibletrinkets.item.VisibleTrinketItem;
import net.minecraft.util.Identifier;

public class VisibleTrinketRenderer extends AbstractVisibleTrinketRenderer<VisibleTrinketItem> {
    public VisibleTrinketRenderer(Identifier assetSubPath, boolean slim) {
        super(new VisibleTrinketModel(assetSubPath, slim));
    }
}
