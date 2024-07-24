package apiEngine.responses.LoginResponses;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class CreateUserResponse {

    private String name;
    private String job;
    private String id;
    private String createdAt;

    public String getName() {
        return name;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }




    public String getCreatedUser() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("name", name);
        jsonObject.addProperty("job", job);
        jsonObject.addProperty("id", id);
        jsonObject.addProperty("createdAt", createdAt);

        return new Gson().toJson(jsonObject);
    }



}
