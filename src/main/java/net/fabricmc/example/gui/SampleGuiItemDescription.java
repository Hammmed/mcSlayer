package net.fabricmc.example.gui;

import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.widget.WButton;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.WLabel;
import net.fabricmc.example.extensions.PlayerEntityExt;
import net.minecraft.text.LiteralText;

import java.util.Random;

public class SampleGuiItemDescription extends LightweightGuiDescription {

    String[] taskListArr = {"chicken", "cow", "sheep"};

    public SampleGuiItemDescription(PlayerEntityExt user) {
        WGridPanel wgp = new WGridPanel();
        this.setRootPanel(wgp);
        wgp.setSize(200, 150);

        WLabel headerLabel = new WLabel(new LiteralText("Slayer Dingus Inc."), 0xde852c);
        wgp.add(headerLabel, 0, 0, 2, 1);

        WLabel currTaskLabel = new WLabel(new LiteralText("Current task: " + user.getSlayerTaskCount() + " " + user.getSlayerTask()), 0xde852c);
        wgp.add(currTaskLabel, 5, 1, 10, 2);

        WButton button = new WButton();
        button.setLabel(new LiteralText("New Task"));
        button.setOnClick(() -> {
            // This is where I'm trying to update the player vars to give them a new task
//            System.out.println(user.getSlayerTaskCount() +  " " + user.getSlayerTask());

            user.setSlayerTask(getRandomString(taskListArr));
            user.setSlayerTaskCount(((int) (Math.random() * (10 - 1)) + 1));

            if (user.getSlayerTaskCount() <= 0 ) {
                user.setSlayerTask(getRandomString(taskListArr));
                user.setSlayerTaskCount(((int) (Math.random() * (10 - 1)) + 1));

//                currPlayer.sendMessage(new LiteralText("Fight me " + currPlayer.getSlayerTaskCount() + " " + currPlayer.getSlayerTask()), false);
            }
        });

        wgp.add(button, 0, 1, 3, 1);


        wgp.validate(this);
    }

    public String getRandomString(String[] arr) {
        return arr[(new Random()).nextInt(arr.length)];
    }

}
