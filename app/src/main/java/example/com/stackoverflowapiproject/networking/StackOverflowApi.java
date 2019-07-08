package example.com.stackoverflowapiproject.networking;

import example.com.stackoverflowapiproject.common.Constants;
import example.com.stackoverflowapiproject.networking.questions.QuestionDetailsResponseSchema;
import example.com.stackoverflowapiproject.networking.questions.QuestionsListResponseSchema;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface StackOverflowApi {

    @GET("/questions/{questionId}?key=" + Constants.STACKOVERFLOW_API_KEY + "&site=stackoverflow&filter=withbody")
    Call<QuestionDetailsResponseSchema> fetchQuestionDetails(@Path("questionId") String questionId);

    @GET("/questions?key=" + Constants.STACKOVERFLOW_API_KEY + "&sort=activity&order=desc&site=stackoverflow&filter=withbody")
    Call<QuestionsListResponseSchema> fetchLastActiveQuestions(@Query("pagesize") Integer pageSize);

}