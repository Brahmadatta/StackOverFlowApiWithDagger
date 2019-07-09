package example.com.stackoverflowapiproject.networking.questions;

import com.google.gson.annotations.SerializedName;

import example.com.stackoverflowapiproject.networking.users.UserSchema;

public class QuestionsSchema {

    @SerializedName("title")
    private final String mTitle;

    @SerializedName("question_id")
    private final String mId;

    @SerializedName("body")
    private final String mBody;

    @SerializedName("owner")
    private final UserSchema mOwner;


    public QuestionsSchema(String mTitle, String mId, String mBody, UserSchema mOwner) {
        this.mTitle = mTitle;
        this.mId = mId;
        this.mBody = mBody;
        this.mOwner = mOwner;
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmId() {
        return mId;
    }

    public String getmBody() {
        return mBody;
    }

    public UserSchema getmOwner() {
        return mOwner;
    }
}
