package example.com.stackoverflowapiproject.networking.questions;

import com.google.gson.annotations.SerializedName;

import java.util.Collections;
import java.util.List;

public class QuestionDetailsResponseSchema {

    @SerializedName("items")
    private final List<QuestionsSchema> mQuestions;

    public QuestionDetailsResponseSchema(QuestionsSchema mQuestions) {
        this.mQuestions = Collections.singletonList(mQuestions);
    }

    public QuestionsSchema getmQuestions() {
        return mQuestions.get(0);
    }
}
