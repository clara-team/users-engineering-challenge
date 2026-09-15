package com.clara.taskly.task;

public enum TaskStatus {
    TODO, IN_PROGRESS, DONE;

    public boolean canTransitionTo(TaskStatus next) {
        return switch (this) {
            case TODO -> next == IN_PROGRESS;
            case IN_PROGRESS -> next == DONE;
            case DONE -> false;
        };
    }
}
