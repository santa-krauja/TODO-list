package app.model;

public enum TaskProgress {
    DONE("Done"), IN_PROGRESS("In progress"), NOT_STARTED("Not started"), WONT_DO("Won't do");

    private String nameAsString;

    TaskProgress(String nameAsString) {
        this.nameAsString = nameAsString;
    }

    public String getName() {
        return this.nameAsString;
    }

}