package com.hjc.demo.springboot.init.entity;

public class KryoTestEntity {
    private String type;
    private String tableName;
    private Object object;

    @Override
    public String toString() {
        if (object instanceof User) {
            this.object = (User)object;
        }
        return "KryoTestEntity{" +
                "type='" + type + '\'' +
                ", tableName='" + tableName + '\'' +
                ", object=" + object +
                '}';
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
