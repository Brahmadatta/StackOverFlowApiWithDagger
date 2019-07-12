package example.com.stackoverflowapiproject.screens.questionList.common.controllers;

public interface BackPressDispatcher {

    void registerListener(BackPressedListener listener);

    void unregisterListener(BackPressedListener listener);
}
