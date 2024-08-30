package model.user;

public class Student {

    private String name;
    private String surname;
    private String nickname;
    private String password;
    private AccountStatus accountStatus;

    public Student(String name,String surname,String nickname,String password,AccountStatus accountStatus){
        this.name = name;
        this.surname = surname;
        this.nickname = nickname;
        this.password = password;
        this.accountStatus = accountStatus;
    }

    public Student(String name,String surname,String nickname,String password){
        this.name = name;
        this.surname = surname;
        this.nickname = nickname;
        this.password = password;
        this.accountStatus = AccountStatus.REQUESTED;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public String getNickname() {
        return nickname;
    }


    public String getPassword() {
        return password;
    }


    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

}
