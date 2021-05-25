package net.fabricmc.example.extensions;

public interface PlayerEntityExt {
    public void reduceTaskCount(int amt);

    public void increaseSlayerPoints(int amt);

    public void reduceSlayerPoints(int amt);

    public int getSlayerTaskCount();

    public void setSlayerTaskCount(int taskCount);

    public String getSlayerTask();

    public void setSlayerTask(String task);

    public int getSlayerPoints();

    public void setRandomTask();

}
