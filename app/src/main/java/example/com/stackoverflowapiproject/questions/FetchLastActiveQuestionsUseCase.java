package example.com.stackoverflowapiproject.questions;

import java.util.ArrayList;
import java.util.List;

import example.com.stackoverflowapiproject.common.BaseObservable;
import example.com.stackoverflowapiproject.common.Constants;
import example.com.stackoverflowapiproject.networking.StackOverflowApi;
import example.com.stackoverflowapiproject.networking.questions.QuestionsListResponseSchema;
import example.com.stackoverflowapiproject.networking.questions.QuestionsSchema;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FetchLastActiveQuestionsUseCase extends BaseObservable<FetchLastActiveQuestionsUseCase.Listener> {

    private final StackOverflowApi mStackOverflowApi;

    public interface Listener{

        void onQuestionsFetchFailure();

        void onLastactiveQuestionsFetched(List<Question> questions);
    }

    public FetchLastActiveQuestionsUseCase(StackOverflowApi mStackOverflowApi) {
        this.mStackOverflowApi = mStackOverflowApi;
    }


    public void fetchLastActiveQuestions(){
        mStackOverflowApi.fetchLastActiveQuestions(Constants.QUESTIONS_LIST_PAGE_SIZE)
                .enqueue(new Callback<QuestionsListResponseSchema>() {
                    @Override
                    public void onResponse(Call<QuestionsListResponseSchema> call, Response<QuestionsListResponseSchema> response) {
                        if (response.isSuccessful()){
                            notifySucess(response.body().getQuestions());
                        }else {
                            notifyFailure();
                        }
                    }

                    @Override
                    public void onFailure(Call<QuestionsListResponseSchema> call, Throwable t) {
                        notifyFailure();
                    }
                });
    }

    private void notifySucess(List<QuestionsSchema> getmQuestions) {
        List<Question> questions = new ArrayList<>(getmQuestions.size());
        for (QuestionsSchema questionsSchema : getmQuestions){
            questions.add(new Question(questionsSchema.getmId(),questionsSchema.getmTitle()));
        }

        for (Listener listener : getListeners()){
            listener.onLastactiveQuestionsFetched(questions);
        }

    }

    private void notifyFailure() {

        for (Listener listener : getListeners()){
            listener.onQuestionsFetchFailure();
        }
    }


}
