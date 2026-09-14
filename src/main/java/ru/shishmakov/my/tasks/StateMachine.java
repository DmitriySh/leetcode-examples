package ru.shishmakov.my.tasks;

import java.util.List;

import static ru.shishmakov.my.tasks.State.START;

public class StateMachine {
    private State currentState = START;

    public void processEvent(String event) {
        this.currentState = currentState.handle(event);
    }

    public static void main(String[] args) {
        var stateMachine = new StateMachine();
        System.out.println("Current state = " + stateMachine.currentState);

        List.of("event1", "event2", "end").forEach(event -> {
                    stateMachine.processEvent(event);
                    System.out.println("Current state = " + stateMachine.currentState);
                }
        );
    }
}

enum State {
    START {
        @Override
        State handle(String event) {
            if ("event1".equals(event)) {
                return STATE_1;
            }
            return this;
        }
    },
    STATE_1 {
        @Override
        State handle(String event) {
            if ("event2".equals(event)) {
                return STATE_2;
            }
            return this;
        }
    },
    STATE_2 {
        @Override
        State handle(String event) {
            if ("end".equals(event)) {
                return END;
            }
            return this;
        }
    },
    END {
        @Override
        State handle(String event) {
            // nothing to do
            return this;
        }
    };

    abstract State handle(String event);
}