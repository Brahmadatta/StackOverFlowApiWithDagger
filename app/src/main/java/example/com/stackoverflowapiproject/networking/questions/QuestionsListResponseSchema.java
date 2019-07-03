package example.com.stackoverflowapiproject.networking.questions;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class QuestionsListResponseSchema {

    @SerializedName("items")
    private final List<QuestionsSchema> mQuestions;

    public QuestionsListResponseSchema(List<QuestionsSchema> questions) {
        mQuestions = questions;
    }

    public List<QuestionsSchema> getQuestions() {
        return mQuestions;
    }
}
