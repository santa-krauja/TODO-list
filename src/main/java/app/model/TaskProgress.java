package app.model;

public enum TaskProgress {
    NOT_STARTED("Not started"), IN_PROGRESS("In progress"), DONE("Done"),  WONT_DO("Won't do");

    private String nameAsString;

    TaskProgress(String nameAsString) {
        this.nameAsString = nameAsString;
    }

    public String getName() {
        return this.nameAsString;
    }

}