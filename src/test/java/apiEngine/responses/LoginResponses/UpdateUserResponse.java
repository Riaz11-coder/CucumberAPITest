package apiEngine.responses.LoginResponses;

public class UpdateUserResponse {

    private String name;
    private String job;
    private String updatedAt;

    public UpdateUserResponse() {
    }

    public UpdateUserResponse(String job, String name, String updatedAt) {
        this.job = job;
        this.name = name;
        this.updatedAt = updatedAt;
    }

    public String getName() {
        return name;
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

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updateAt) {
        this.updatedAt = updateAt;
    }

}
