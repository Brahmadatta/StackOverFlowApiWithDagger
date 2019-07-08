package example.com.stackoverflowapiproject.questions;

public class Question {

    private final String mId;

    private final String mTitle;

    public Question(String mId, String mTitle) {
        this.mId = mId;
        this.mTitle = mTitle;
    }

    public String getmId() {
        return mId;
    }

    public String getmTitle() {
        return mTitle;
    }
}
