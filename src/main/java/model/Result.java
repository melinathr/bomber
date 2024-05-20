package model;

public class Result {
    private final boolean isSuccessful;
    private final String message;

    public Result(boolean isSuccessful, String message) {
        this.isSuccessful = isSuccessful;
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }

    public boolean isSuccessful() {
        return isSuccessful;
    }
}