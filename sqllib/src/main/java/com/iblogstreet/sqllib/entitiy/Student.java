package com.iblogstreet.sqllib.entitiy;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

//告诉GreenDao该对象为实体,只有被@Entity注释的Bean类才能被dao类操作
//在Entity中我们可以配置许多信息，比如nameInDb是声明了该表数据库中的表名。
//indexes 用于建立索引，索引的应用场景可用于，当你的表有多个主键的时候，来标志一条数据的唯一性，配合unique。
@Entity
public class Student {
    // id自增长
    //(autoincrement = true)表示主键会自增，如果false就会使用旧值
    @Id(autoincrement = true)
    //学员id,注意这里的stuId只能是Long类型
    @Property(nameInDb = "stuId") @NotNull
    private Long stuId;
    // 学员编号---这里的意思是学员编号stuNo具有唯一性即数据库中不能有两个一样的stuNo
    //如果数据库中有两个相同的stuNo会报错违反unique规则
    //注意这里的@Index(unique =true)只是针对stuNo的,和下面的stuName stuSex stuScore没有什么关系
    @Index(unique =true)
    @Property(nameInDb = "stuNo") @NotNull
    private String stuNo;
    // 学员姓名
    //@Property：在数据库中，会对应生成一个字段，nameInDb：StudentName(字段名称)
    //@NotNull 标志这个字段不能是null
    //@Property 如果定义了这个属性，那么nameInDb的值就是该列在数据表里面，该列的名称。
    // 下面的例子,stuName的值存储在数据表里面的StudentName那一列。
    //@Transient 表示不存储在数据库中
    @Property(nameInDb = "stuName") @NotNull
    private String stuName;
    // 学员性别
    @Property(nameInDb = "stuSex") @NotNull
    private String stuSex;
    // 学员成绩
    @Property(nameInDb = "stuScore") @NotNull
    private String stuScore;
    @Generated(hash = 1080531301)
    public Student(@NotNull Long stuId, @NotNull String stuNo,
            @NotNull String stuName, @NotNull String stuSex,
            @NotNull String stuScore) {
        this.stuId = stuId;
        this.stuNo = stuNo;
        this.stuName = stuName;
        this.stuSex = stuSex;
        this.stuScore = stuScore;
    }
    @Generated(hash = 1556870573)
    public Student() {
    }
    public Long getStuId() {
        return this.stuId;
    }
    public void setStuId(Long stuId) {
        this.stuId = stuId;
    }
    public String getStuNo() {
        return this.stuNo;
    }
    public void setStuNo(String stuNo) {
        this.stuNo = stuNo;
    }
    public String getStuName() {
        return this.stuName;
    }
    public void setStuName(String stuName) {
        this.stuName = stuName;
    }
    public String getStuSex() {
        return this.stuSex;
    }
    public void setStuSex(String stuSex) {
        this.stuSex = stuSex;
    }
    public String getStuScore() {
        return this.stuScore;
    }
    public void setStuScore(String stuScore) {
        this.stuScore = stuScore;
    }
}
