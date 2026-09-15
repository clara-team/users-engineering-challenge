package com.clara.taskly.member;

public enum MemberStatus {
    PENDING, ACTIVE, OFFBOARDED;

    public boolean canTransitionTo(MemberStatus next) {
        return switch (this) {
            case PENDING     -> next == ACTIVE;
            case ACTIVE      -> next == OFFBOARDED;
            case OFFBOARDED  -> false;
        };
    }
}
