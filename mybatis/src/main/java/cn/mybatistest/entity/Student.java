package cn.mybatistest.entity;

public class Student {
    String  Sno;
    String  Sdept;
    String  Sname;
    String  Clbum;
    String  Sex;
    Integer  Age;
    String  DormID;
    String  PoiticsStatus;
    String  HomeAddress;
    String  Rest;

    public Student() {

    }

    public Student(String sno, String sdept, String sname, String clbum, String sex, Integer age, String dormID, String poiticsStatus, String homeAddress, String rest) {
        Sno = sno;
        Sdept = sdept;
        Sname = sname;
        Clbum = clbum;
        Sex = sex;
        Age = age;
        DormID = dormID;
        PoiticsStatus = poiticsStatus;
        HomeAddress = homeAddress;
        Rest = rest;
    }

    public String getSno() {
        return Sno;
    }

    public void setSno(String sno) {
        Sno = sno;
    }

    public String getSdept() {
        return Sdept;
    }

    public void setSdept(String sdept) {
        Sdept = sdept;
    }

    public String getSname() {
        return Sname;
    }

    public void setSname(String sname) {
        Sname = sname;
    }

    public String getClbum() {
        return Clbum;
    }

    public void setClbum(String clbum) {
        Clbum = clbum;
    }

    public String getSex() {
        return Sex;
    }

    public void setSex(String sex) {
        Sex = sex;
    }

    public Integer getAge() {
        return Age;
    }

    public void setAge(Integer age) {
        Age = age;
    }

    public String getDormID() {
        return DormID;
    }

    public void setDormID(String dormID) {
        DormID = dormID;
    }

    public String getPoiticsStatus() {
        return PoiticsStatus;
    }

    public void setPoiticsStatus(String poiticsStatus) {
        PoiticsStatus = poiticsStatus;
    }

    public String getHomeAddress() {
        return HomeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        HomeAddress = homeAddress;
    }

    public String getRest() {
        return Rest;
    }

    public void setRest(String rest) {
        Rest = rest;
    }
}
