package model;

public class Task {
    private String description;
    private double reward;

    public Task(String description, double reward) {
        this.description = description;
        this.reward = reward;
    }

    public String getDescription() {
        return description;
    }

    public double getReward() {
        return reward;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setReward(double reward) {
        this.reward = reward;
    }

    @Override
    public String toString() {
        return "Task: "+description +" reward:" + reward +"$";
    }
}