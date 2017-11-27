package app.model;

public enum TaskProgress {
    DONE("Done"), IN_PROGRESS("In progress"), NOT_STARTED("Not started");

    private String nameAsString;

    private TaskProgress(String nameAsString) {
        this.nameAsString = nameAsString;
    }

    @Override
    public String toString() {
        return this.nameAsString;
    }
}
