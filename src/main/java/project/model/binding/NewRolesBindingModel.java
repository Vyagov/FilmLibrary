package project.model.binding;

import javax.validation.constraints.Pattern;

public class NewRolesBindingModel {
    private String roleAdmin = "0";
    private String userId;

    public NewRolesBindingModel() {
    }

    @Pattern(regexp = "[01]", message = "Input value is not correct!")
    public String getRoleAdmin() {
        return roleAdmin;
    }

    public void setRoleAdmin(String roleAdmin) {
        this.roleAdmin = roleAdmin;
    }

    @Pattern(regexp = "^[a-zA-Z0-9\\s+,.!\"-_]+$", message = "Id is not valid!")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
