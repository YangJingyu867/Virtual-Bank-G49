package model;

public class SavingsGoal {
    private String description;
    private double targetAmount;
    private double currentProgress;
    private boolean completed;

    public SavingsGoal(String description, double targetAmount) {
        this.description = description;
        this.targetAmount = targetAmount;
        this.currentProgress = 0.0;
        this.completed = false;
    }

    public String getDescription() {
        return description;
    }

    public double getTargetAmount() {
        return targetAmount;
    }

    public double getCurrentProgress() {
        return currentProgress;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void update(double amount) {
        currentProgress =1.0*amount/targetAmount*100;
        if (currentProgress >= 100) {
        	currentProgress=100;
            completed = true;
        }
    }
    
    public void setCurrentProgress(double currentProgress) {
        this.currentProgress = currentProgress;
    }

    @Override
    public String toString() {
        return "SavingsGoal: " + description +" target amount: " + targetAmount+
                " currentProgress: " +String.format("%.02f",currentProgress)+"%";
    }
}
