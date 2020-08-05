package user;

public enum UserGroup {
    UNDEFINED,
    ADMIN,
    TEACHER,
    STUDENT;

    public String getDbTableName() throws Exception {
        if (this != UNDEFINED)
            return this.toString().toLowerCase();
        else
            throw new Exception("userGroup not defined");
    }
}
