public enum Stages {
    STAGE1("1"),
    STAGE2 ("2"),
    STAGE3 ("3"),
    STAGE4 ("4");

    private String numberOfStage;

    Stages(String numberOfStage) {
        this.numberOfStage = numberOfStage;
    }

    public String toString() {
        return numberOfStage;
    }
}
