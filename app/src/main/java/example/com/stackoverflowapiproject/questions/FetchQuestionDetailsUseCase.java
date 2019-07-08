package example.com.stackoverflowapiproject.questions;


import example.com.stackoverflowapiproject.networking.StackOverflowApi;
import example.com.stackoverflowapiproject.screens.questionList.common.BaseObservableViewMvc;
import example.com.stackoverflowapiproject.networking.questions.QuestionDetailsResponseSchema;
import example.com.stackoverflowapiproject.networking.questions.QuestionsSchema;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FetchQuestionDetailsUseCase extends BaseObservableViewMvc<FetchQuestionDetailsUseCase.Listener> {

    private final StackOverflowApi mStackOverflowApi;

    public FetchQuestionDetailsUseCase(StackOverflowApi mStackOverflowApi) {
        this.mStackOverflowApi = mStackOverflowApi;
    }

    public interface Listener{

        void onQuestionDetailsFetched(QuestionDetails questionDetails);

        void onQuestionDetailsFailed();

    }

    public void fetchQuestionDetailsAndNotify(String questionId){
        mStackOverflowApi.fetchQuestionDetails(questionId).enqueue(new Callback<QuestionDetailsResponseSchema>() {
            @Override
            public void onResponse(Call<QuestionDetailsResponseSchema> call, Response<QuestionDetailsResponseSchema> response) {

                if (response.isSuccessful()){

                    notifySuccess(response.body().getmQuestions());

                }else {
                    notifyFailure();
                }
            }

            @Override
            public void onFailure(Call<QuestionDetailsResponseSchema> call, Throwable t) {
                notifyFailure();
            }
        });
    }

    private void notifyFailure() {

        for (Listener listener : getListeners()){
            listener.onQuestionDetailsFailed();
        }
    }

    private void notifySuccess(QuestionsSchema getmQuestions) {

        for (Listener listener : getListeners()){

            listener.onQuestionDetailsFetched(new QuestionDetails(getmQuestions.getmId(),getmQuestions.getmTitle()
                    ,getmQuestions.getmBody()));
        }
    }
}
