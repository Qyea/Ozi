package by.fpmibsu.ozi.entity;

import org.bouncycastle.util.encoders.Hex;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;

public class User implements Serializable, Cloneable
{
    private Integer id;

    private String phone;

    private String email;

    private String password;

    private String name;

    private String surname;

    private Date birthday;

    private String sex;

    private String country;

    private String city;

    private String about;

    private java.sql.Blob image;

    public User()
    {

    }

    public User(
            Integer id,
            String phone,
            String email,
            String password,
            String name,
            String surname,
            Date birthday,
            String sex
            ) {
        this.id = id;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.birthday = birthday;
        this.sex = sex;
        this.country = null;
        this.city = null;
        this.about = null;
        this.image = null;
    }

    public User(
            Integer id,
            String phone,
            String email,
            String password,
            String name,
            String surname,
            String birthday,
            String sex
    ) {
        this.id = id;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        java.util.Date tmp = new java.util.Date(birthday);
        this.birthday = new Date(tmp.getYear(), tmp.getMonth(), tmp.getDay());
        this.sex = sex;
        this.country = null;
        this.city = null;
        this.about = null;
        this.image = null;
    }

    public User(
            Integer id,
            String phone,
            String email,
            String password,
            String name,
            String surname,
            Date birthday,
            String sex,
            String country,
            String city,
            String about,
            java.sql.Blob image
    ) {
        this.id = id;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.birthday = birthday;
        this.sex = sex;
        this.country = country;
        this.city = city;
        this.about = about;
        this.image = image;
    }

    public User(
            Integer id,
            String phone,
            String email,
            String password,
            String name,
            String surname,
            String birthday,
            String sex,
            String country,
            String city,
            String about,
            java.sql.Blob image
    ) {
        this.id = id;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        long d = java.util.Date.parse(birthday);
        this.birthday = new Date(d);
        this.sex = sex;
        this.country = country;
        this.city = city;
        this.about = about;
        this.image = image;
    }

    public Integer getId() {
        return id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password){
        this.password = password;
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

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public static @NotNull String makeHash(String password) throws NoSuchAlgorithmException
    {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
        return new String(Hex.encode(hash));
    }

    public java.sql.Blob getImage() {
        return image;
    }

    public void setImage(java.sql.Blob image) {
        this.image = image;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(id).append(' ');
        stringBuilder.append(phone).append(' ');
        stringBuilder.append(email).append(' ');
        stringBuilder.append(password).append(' ');
        stringBuilder.append(name).append(' ');
        stringBuilder.append(surname).append(' ');
        stringBuilder.append(birthday).append(' ');
        stringBuilder.append(sex).append(' ');
        stringBuilder.append(country).append(' ');
        stringBuilder.append(city).append(' ');
        stringBuilder.append(about).append(' ');
        stringBuilder.append(image).append(' ');
        return stringBuilder.toString();
    }

    public Boolean equals(User user)
    {
        return this.getId().equals(user.getId());
    }
}
