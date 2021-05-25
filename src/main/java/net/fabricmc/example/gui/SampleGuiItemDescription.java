package net.fabricmc.example.gui;

import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.widget.WButton;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.WLabel;
import net.fabricmc.example.extensions.PlayerEntityExt;
import net.minecraft.text.LiteralText;

public class SampleGuiItemDescription extends LightweightGuiDescription {


    public SampleGuiItemDescription(PlayerEntityExt user) {
        WGridPanel wgp = new WGridPanel();
        this.setRootPanel(wgp);
        wgp.setSize(200, 150);

        WLabel headerLabel = new WLabel(new LiteralText("Hello I'm MC Steve"), 0xde852c);
        wgp.add(headerLabel, 0, 0, 2, 1);

        WLabel currTaskLabel = new WLabel(new LiteralText("Current task: " + user.getSlayerTaskCount() + " " + user.getSlayerTask()), 0xde852c);
        wgp.add(currTaskLabel, 5, 1, 10, 2);

        WButton button = new WButton();
        button.setLabel(new LiteralText("New Task"));

        button.setOnClick(() -> {
            user.setRandomTask();
            user.reduceSlayerPoints(50);
        });

        wgp.add(button, 0, 1, 3, 1);

        wgp.validate(this);
    }

}
