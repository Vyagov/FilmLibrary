package project.model.service;

public class NewRolesServiceModel extends BaseServiceModel {
    private String roleAdmin;
    private String userId;

    public NewRolesServiceModel() {
    }

    public String getRoleAdmin() {
        return roleAdmin;
    }

    public void setRoleAdmin(String roleAdmin) {
        this.roleAdmin = roleAdmin;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
